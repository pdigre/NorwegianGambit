package norwegiangambit.game;

import java.io.File;

import org.eclipse.jface.preference.StringButtonFieldEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;


public class GFileFieldEditor extends StringButtonFieldEditor {

    private String fileBrowserLabel;
    private String fileBrowserFilterExtensions[] = {"*.*"};
    private String fileBrowserFilterNames[] = {"All files(*.*)"};

    private String lastPath;

    protected void setFileBrowserLabel(String label) {
        this.fileBrowserLabel = label;
    }

    protected void setfileBrowserFilterExtension(String[] filterExtensions) {
        this.fileBrowserFilterExtensions = filterExtensions;
    }

    protected void setFileBrowserFilterName(String[] filterNames) {
        this.fileBrowserFilterNames = filterNames;
    }

    /**
     * Creates a file field editor with a Browse button opening a file chooser
     *
     * @param name the name of the preference this field editor works on
     * @param labelText the label text of the field editor
     * @param parent the parent of the field editor's control
     */
    protected GFileFieldEditor(String name, String labelText, Composite parent) {
        super(name, labelText,parent);
   }

    @Override
    protected String changePressed() {
        FileDialog dialog = new FileDialog(getShell());
        if (fileBrowserLabel != null) {
            dialog.setText(fileBrowserLabel);
        }
        if (lastPath != null) {
            if (new File(lastPath).exists()) {
                dialog.setFilterPath(lastPath);
            }
        }
        if (fileBrowserFilterExtensions != null)
            dialog.setFilterExtensions(fileBrowserFilterExtensions);
        if (fileBrowserFilterNames != null)
            dialog.setFilterNames(fileBrowserFilterNames);
        String dir = dialog.open();
        if (dir != null) {
            dir = dir.trim();
            if (dir.length() == 0) {
                return null;
            }
            lastPath = dir;
        }
        return dir;
    }

}
