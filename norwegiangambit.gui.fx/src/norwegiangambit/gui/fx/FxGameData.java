package norwegiangambit.gui.fx;

import java.util.ArrayList;

import javafx.application.Platform;

import org.eclipse.swt.widgets.Display;

import norwegiangambit.profile.GameData;
import norwegiangambit.profile.Marking;

public class FxGameData extends GameData {
	FxChessCanvas canvas;
	public Display display;

	public void setCanvas(FxChessCanvas canvas) {
		this.canvas = canvas;
	}

	@Override
	public void updateBoard() {
		ArrayList<Marking> markers = getMarkers();
		if (display != null) {
			display.asyncExec(new Runnable() {

				@Override
				public void run() {
					canvas.drawBoard(getBoard(), markers);
				}
			});
		} else {
			Platform.runLater(new Runnable() {

				@Override
				public void run() {
					canvas.drawBoard(getBoard(), markers);
				}
			});
		}
	}

}
