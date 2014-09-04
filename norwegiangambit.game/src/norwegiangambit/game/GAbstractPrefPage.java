package norwegiangambit.game;

import java.util.EnumMap;
import java.util.Map.Entry;

import org.eclipse.jface.preference.BooleanFieldEditor;
import org.eclipse.jface.preference.FieldEditor;
import org.eclipse.jface.preference.FieldEditorPreferencePage;
import org.eclipse.jface.preference.IPreferenceStore;
import org.eclipse.jface.preference.IntegerFieldEditor;
import org.eclipse.jface.preference.RadioGroupFieldEditor;
import org.eclipse.jface.preference.StringFieldEditor;
import org.eclipse.jface.resource.ImageDescriptor;
import org.eclipse.swt.SWT;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.GridData;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.ui.IWorkbench;
import org.eclipse.ui.IWorkbenchPreferencePage;
import org.eclipse.ui.PlatformUI;

public abstract class GAbstractPrefPage extends FieldEditorPreferencePage implements IWorkbenchPreferencePage {

    protected IPreferenceStore store;


    protected GAbstractPrefPage() {
        super(GRID);
        initor();
    }

    protected GAbstractPrefPage(int style) {
        super(style);
        initor();
    }

    private void initor() {
        setPreferenceStore(Activator.getDefault().getPreferenceStore());
        setDescription(Preferences.getString(this.getClass().getSimpleName() + ".Description"));
    }

    protected GAbstractPrefPage(String title, ImageDescriptor image, int style) {
        super(title, image, style);
        initor();
    }

    protected GAbstractPrefPage(String title, int style) {
        super(title, style);
        initor();
    }

    @Override
    public void init(IWorkbench workbench) {
        // unused
    }

    public BooleanFieldEditor addCheckBox(Preferences pref) {
        BooleanFieldEditor editor = new BooleanFieldEditor(pref.getID(), pref.getLabel(), getFieldEditorParent());
        addField(editor,pref);
        return editor;
    }

    public StringFieldEditor addStringEditor(Preferences pref) {
        StringFieldEditor editor = new StringFieldEditor(pref.getID(), pref.getLabel() + ":", getFieldEditorParent());
        addField(editor,pref);
        return editor;
    }

    public IntegerFieldEditor addIntegerEditor(Preferences pref) {
        IntegerFieldEditor editor = new IntegerFieldEditor(pref.getID(), pref.getLabel() + ":", getFieldEditorParent());
        addField(editor,pref);
        return editor;
    }

    public FileListFieldEditor addFileList(Preferences pref) {
        FileListFieldEditor editor = new FileListFieldEditor(pref.getID(), pref.getLabel(), pref.getDescription(), getFieldEditorParent());
        addField(editor,pref);
        return editor;
    }

    public GFileFieldEditor addFileField(Preferences pref) {
        GFileFieldEditor editor = new GFileFieldEditor(pref.getID(), pref.getLabel() + ":", getFieldEditorParent());
        addField(editor,pref);
        return editor;
    }

    @Override
    public boolean performOk() {
        boolean performOk = super.performOk();
        Preferences.apply();
        return performOk;
    }

    protected void addField(FieldEditor editor, Preferences pref) {
        super.addField(editor);
        tooltips.put(pref,editor);
    }
    
    protected void addRadioGroup(FieldEditor editor, Preferences pref,String[][] legal) {
        super.addField(editor);
        tooltips.put(pref,editor);
		Composite radioGroup = new Composite(getFieldEditorParent(), SWT.NULL);
		radioGroup.setLayoutData(new GridData(SWT.FILL, SWT.FILL, true, false));
		editor = new RadioGroupFieldEditor(pref.getID(), pref.getLabel() + ":", 1,legal,radioGroup, true);
    }

    
    EnumMap<Preferences,FieldEditor> tooltips=new EnumMap<Preferences,FieldEditor>(Preferences.class);

    @Override
    public void createControl(Composite parent) {
        super.createControl(parent);
        Composite fieldEditorParent = getFieldEditorParent();
        for (Entry<Preferences, FieldEditor> e : tooltips.entrySet()) {
            Preferences pref = e.getKey();
            FieldEditor editor = e.getValue();
            if(editor instanceof BooleanFieldEditor)
                ((BooleanFieldEditor) editor).getDescriptionControl(fieldEditorParent).setToolTipText(pref.getDescription());
            else
                editor.getLabelControl(fieldEditorParent).setToolTipText(pref.getDescription());
        }
        tooltips.clear();
    }
    
    @Override
    public Point computeSize() {
        PlatformUI.getWorkbench().getHelpSystem().setHelp(getControl(), getHelpId());
        return super.computeSize();
    }
    
    public String getHelpId(){
        return Activator.PLUGIN_ID+"."+getClass().getSimpleName();
    }
}