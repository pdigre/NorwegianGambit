package norwegiangambit.gui.fx;

import java.util.ArrayList;

import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import norwegiangambit.profile.GraphicsCommon;
import norwegiangambit.profile.Marking;
import norwegiangambit.profile.Square;


public class FxChessCanvas extends Canvas{

    private static GraphicsCommon common = new GraphicsCommon();

    private GraphicsContext gc;

    public FxChessCanvas(final FxGameData game) {
        super(270,270);
        game.setCanvas(this);
        gc = getGraphicsContext2D();
        setOnMousePressed(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                MouseEvent me = (MouseEvent)event;
                if(me.isPrimaryButtonDown()){
                    game.clickSquare(GraphicsCommon.findSquare((int)me.getX(), (int)me.getY()));
                }
            }
        });
    }
    
    public void drawBoard(int[] board, ArrayList<Marking> markers){
        Paint[] frame = new Paint[64];
        int[] score = new int[64];

        Paint color = Color.YELLOW;
        for (Marking mark : markers) {
            int i = mark.pos;
            switch (mark.type) {
                case BestMoveFrom:
                    frame[i] = Color.YELLOW;
                    break;
                case MoveFrom:
                    frame[i] = Color.GREEN;
                    break;
                case MarkFrom:
                    frame[i] = Color.RED;
                    break;
                case MoveTo:
                    frame[i] = color;
                    score[i] = mark.score;
                    break;
                default:
                    break;
            }
        }

        for (int i = 0; i < 64; i++) {
            Square square = common.setup(i);
            int piece = board[i];
            int[] r = GraphicsCommon.getRectangleXYWH(i);
            if (square.piece != piece || square.score != score[i]) {
                square.piece = piece;
                gc.setFill(getBGColor(i, null));
                gc.fillRect(r[0],r[1],r[2],r[3]);
                if (piece > 0) {
                    int[] xy = GraphicsCommon.toXY(i);
                    gc.drawImage(FxGraphics.graphics.getImage(piece), xy[0], xy[1]);
                }
            }
            if (square.marker != frame[i]) {
                square.marker = frame[i];
                gc.setStroke(getBGColor(i, frame[i]));
                gc.strokeRect(r[0],r[1],r[2],r[3]);
            }
            if (square.score != score[i]) {
                square.score = score[i];
                if (score[i] != 0) {
                    gc.setFill(Color.CYAN);
                    gc.fillText(Integer.toString(score[i]), r[0], r[1]);
                }
            }
        }
        
    }
    private static Paint getBGColor(int i, Paint color) {
        return color == null ? GraphicsCommon.isBlack(i) ? Color.DARKGRAY : Color.GRAY : color;
    }
}
