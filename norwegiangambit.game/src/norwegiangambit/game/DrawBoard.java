package norwegiangambit.game;

import norwegiangambit.util.FEN;

import org.eclipse.swt.graphics.GC;

public class DrawBoard {

	public static void drawFen(GC gc, String fen) {
		int[] brd = FEN.fen2board(fen);
        gc.drawImage(FenGraphics.graphics.getBoardImg(gc.getDevice()),0,0);
        for (int i = 0; i < 64; i++) {
            int piece = brd[i];
            if(piece!=0)
                gc.drawImage(FenGraphics.graphics.getImage(piece, gc.getDevice()),(i%8)*26,(7-i/8)*26);
        }
	}

}
