package norwegiangambit.gui.swt;

import norwegiangambit.engine.fen.StartingGames;
import norwegiangambit.profile.IPlayer.Players;

import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SwtChess {

    private Shell shell;
	private SwtChessDialog dialog;

	public static void main(String[] args) {
        SwtChess game = new SwtChess(new Display());
        runDisplay(game.shell);
    }

    public SwtChess(Display display) {
		shell = new Shell(display);
        shell.setLayout(new GridLayout(2, false));
        shell.setSize(500, 370);
        SwtGameData game = new SwtGameData();
        dialog = new SwtChessDialog(shell,game);
        game.start(StartingGames.FEN_GAMES[0], Players.MANUAL, Players.EASY);
        shell.addShellListener(new ShellListener() {
			
			@Override
			public void shellIconified(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellDeiconified(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellDeactivated(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void shellClosed(ShellEvent e) {
				dialog.dispose();
				shell.dispose();
			}
			
			@Override
			public void shellActivated(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
		});;
    }
    

    public static void runDisplay(Shell shell) {
        Display display = shell.getDisplay();
        while (!shell.isDisposed()) {
            if (!display.readAndDispatch())
                display.sleep();
        }
        display.dispose();
    }

}
