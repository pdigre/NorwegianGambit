package norwegiangambit.game;

import org.eclipse.core.runtime.preferences.AbstractPreferenceInitializer;
import org.eclipse.jface.preference.IPreferenceStore;

/**
 * Class used to initialize default preference values.
 */
public class PreferenceInitializer extends AbstractPreferenceInitializer {

    @Override
    public void initializeDefaultPreferences() {
        IPreferenceStore store = Activator.getDefault().getPreferenceStore();
        for (Preferences pref : Preferences.values()) {
            Object def = pref.getDefault();
            if (def == null)
                continue;
            if (def instanceof Boolean)
                store.setDefault(pref.getID(), (Boolean) def);
            if (def instanceof Integer)
                store.setDefault(pref.getID(), (Integer) def);
            if (def instanceof String)
                store.setDefault(pref.getID(), (String) def);
        }
    }
    
    
}
