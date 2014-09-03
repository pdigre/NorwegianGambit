package norwegiangambit.game;

import java.io.File;
import java.util.ArrayList;
import java.util.StringTokenizer;

import org.eclipse.jface.preference.ListEditor;
import org.eclipse.swt.widgets.Composite;
import org.eclipse.swt.widgets.FileDialog;

public class FileListFieldEditor extends ListEditor {

    private String fileChooserLabelText;

    private String lastPath;

    FileListFieldEditor(String name, String labelText,
        String fileChooserLabelText, Composite parent) {
        super(name, labelText, parent);
        this.fileChooserLabelText = fileChooserLabelText;
    }

    @Override
    protected String createList(String[] items) {
        StringBuffer path = new StringBuffer("");//$NON-NLS-1$

        for (int i = 0; i < items.length; i++) {
            path.append(items[i]);
            path.append(File.pathSeparator);
        }
        return path.toString();
    }

    @Override
    protected String getNewInputObject() {
        FileDialog dialog = new FileDialog(getShell());
        if (fileChooserLabelText != null) {
            dialog.setText(fileChooserLabelText);
        }
        if (lastPath != null) {
            if (new File(lastPath).exists()) {
                dialog.setFilterPath(lastPath);
            }
        }
        dialog.setFilterExtensions(new String[]{"*.jar","*.*"});
        dialog.setFilterNames(new String[]{"JAR files(*.jar)","All files(*.*)"});
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

    @Override
    protected String[] parseString(String stringList) {
        StringTokenizer st = new StringTokenizer(stringList, File.pathSeparator
            + "\n\r");//$NON-NLS-1$
        ArrayList<String> v = new ArrayList<String>();
        while (st.hasMoreElements()) {
            v.add((String) st.nextElement());
        }
        return v.toArray(new String[v.size()]);
    }

    
}
