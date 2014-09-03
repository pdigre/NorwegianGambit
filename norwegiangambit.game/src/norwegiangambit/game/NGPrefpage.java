package norwegiangambit.game;


public class NGPrefpage extends GAbstractPrefPage {

    @Override
    public void createFieldEditors() {
    	addFileField(Preferences.Stockfish);
    	addFileField(Preferences.ROCE);
    	addFileField(Preferences.QPERFT);
    }

}
