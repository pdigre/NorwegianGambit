package norwegiangambit.profile;



public class ManualWithHelp extends Manual {

    @Override
    public void run() {
    	System.out.println(getFen());
		game.updateBoard();
    }
}
