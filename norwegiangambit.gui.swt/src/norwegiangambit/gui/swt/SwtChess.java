package norwegiangambit.gui.swt;

import norwegiangambit.engine.fen.StartingGames;
import norwegiangambit.profile.IPlayer.Players;

import org.eclipse.swt.layout.GridLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class SwtChess {

    public static void main(String[] args) {
        new SwtChess();
    }

    public SwtChess() {
        Shell shell = new Shell(new Display());
        shell.setLayout(new GridLayout(2, false));
        shell.setSize(500, 370);
        SwtGameData game = new SwtGameData();
        new SwtChessDialog(shell,game);
        game.start(StartingGames.FEN_GAMES[0], Players.MANUAL, Players.EASY);
        runDisplay(shell);
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
