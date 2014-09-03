package norwegiangambit.game;

import java.util.ResourceBundle;

// Prompt for name on
public enum Preferences {
    Stockfish("C:/chess/stockfish.exe".replace("/", "\\")),
    ROCE("C:/chess/roce39.exe".replace("/", "\\")),
    QPERFT("C:/chess/qperft.exe".replace("/", "\\"));

    public static final String PREFIX = "pref";

    Object def;

    private Preferences() {
    }

    private Preferences(Object def) {
        this.def = def;
    }

    public Object getDefault() {
        return def;
    }

    private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(Preferences.class.getName()
        .toLowerCase());

    public static String getString(String key) {
        if (RESOURCE_BUNDLE.containsKey(key))
            return RESOURCE_BUNDLE.getString(key);
        return "";
    }

    public String getLabel() {
        return getString(name() + ".Label");
    }

    public String getDescription() {
        String string = getString(name() + ".Description");
        return string;
    }

    public String getID() {
        return PREFIX + name();
    }

    public boolean is() {
        return Activator.getDefault().getPreferenceStore().getBoolean(getID());
    }

    public int intValue() {
        return Activator.getDefault().getPreferenceStore().getInt(getID());
    }

    public String stringValue() {
        return Activator.getDefault().getPreferenceStore().getString(getID());
    }

    public boolean booleanValue() {
        return Activator.getDefault().getPreferenceStore().getBoolean(getID());
    }

    public void setBoolean(boolean bool) {
    	Activator.getDefault().getPreferenceStore().setValue(getID(), bool);
    }

    public void setString(String text) {
    	Activator.getDefault().getPreferenceStore().setValue(getID(), text);
    }
}
