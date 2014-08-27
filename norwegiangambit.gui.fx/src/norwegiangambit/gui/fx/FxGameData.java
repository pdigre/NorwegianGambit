package norwegiangambit.gui.fx;

import java.util.ArrayList;

import norwegiangambit.profile.GameData;
import norwegiangambit.profile.Marking;


public class FxGameData extends GameData{
    FxChessCanvas canvas;
    
    public void setCanvas(FxChessCanvas canvas){
        this.canvas=canvas;
    }
    
    @Override
    public void updateBoard() {
        ArrayList<Marking> markers = getMarkers();
        int[] board = pos.getBoard();
        canvas.drawBoard(board, markers);
    }


}
