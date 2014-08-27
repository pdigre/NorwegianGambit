package norwegiangambit.gui.fx;

import javafx.embed.swt.FXCanvas;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import norwegiangambit.engine.fen.StartingGames;
import norwegiangambit.profile.IPlayer.Players;

import org.eclipse.swt.SWT;
import org.eclipse.swt.events.ShellEvent;
import org.eclipse.swt.events.ShellListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.layout.FillLayout;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;

public class FxChessSWT {

	private Shell shell;
	private FXCanvas canvas;


	public FxChessSWT(Display display) {
		shell = new Shell(display);
        shell.setLayout(new FillLayout());
        shell.setSize(500, 370);
        canvas = new FXCanvas(shell, SWT.NONE) {

			public Point computeSize(int wHint, int hHint, boolean changed) {
                Scene scene = getScene();
				scene.getWindow().sizeToScene();
                int width = (int) scene.getWidth();
                int height = (int) scene.getHeight();
                return new Point(width, height);
            }
        };
        FxGameData game = new FxGameData();
	    FxChessDialog dialog = new FxChessDialog(game);
        game.start(StartingGames.FEN_GAMES[0], Players.MANUAL, Players.EASY);
        game.start(StartingGames.FEN_GAMES[0], Players.MANUAL, Players.EASY);
        Scene scene = new Scene(dialog, Color.rgb(
                shell.getBackground().getRed(),
                shell.getBackground().getGreen(),
                shell.getBackground().getBlue()));
        canvas.setScene(scene);
        shell.layout();
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
				canvas.dispose();
				shell.dispose();
			}
			
			@Override
			public void shellActivated(ShellEvent e) {
				// TODO Auto-generated method stub
				
			}
		});;
        shell.open();
	}

}
