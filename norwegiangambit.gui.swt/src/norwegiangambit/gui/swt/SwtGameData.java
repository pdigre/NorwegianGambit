package norwegiangambit.gui.swt;

import java.util.ArrayList;

import norwegiangambit.profile.GameData;
import norwegiangambit.profile.Marking;


public class SwtGameData extends GameData {

    SwtChessCanvas canvas;
    
    public void setCanvas(SwtChessCanvas canvas){
        this.canvas=canvas;
    }
    
    @Override
    public void updateBoard() {
        Runnable runnable = new Runnable() {

            @Override
            public void run() {
                ArrayList<Marking> markers = getMarkers();
                canvas.drawBoard(pos, markers);
                canvas.redraw();
                canvas.update();
            }

        };
        canvas.getDisplay().syncExec(runnable);
    }
}
