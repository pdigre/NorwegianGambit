package norwegiangambit.profile;


public class ManualWithHelp extends Manual {

    @Override
    public void run() {
    	printFEN();
		game.updateBoard();
    }
}
