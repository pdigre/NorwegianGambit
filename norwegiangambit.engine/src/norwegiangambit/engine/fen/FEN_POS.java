package norwegiangambit.engine.fen;

import norwegiangambit.engine.base.Movegen;
import norwegiangambit.engine.iterate.NodeGen;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;

public class FEN_POS implements IConst {

    public static String notation(String fen) {
    	StartGame pos = new StartGame(fen);
		long bitmap = pos.getBitmap();
		int from = BITS.getFrom(bitmap);
		int to = BITS.getTo(bitmap);
		String capture = ((bitmap >> _CAPTURE) & 7) != 0 ? "x" : "";
		String p = FEN.piecePrefix((int) (bitmap & PIECETYPE));
		String prefix = capture + FEN.pos2string(from);
		String suffix = capture + FEN.pos2string(to);
		if (BITS.isPromotion(bitmap)) {
		    suffix += "=" + p;
		} else {
		    prefix = p + prefix;
		    suffix = p + suffix;
		    if (BITS.isEnpassant(bitmap))
		        suffix += "e.p.";
		    if (BITS.isCastling(bitmap)) {
		        int col = from & 7;
		        suffix = col == 2 ? "O-O-O" : "O-O";
		    }
		}
		boolean white = pos.whiteNext();
		int king = white?pos.wking:pos.bking;
		if(!Movegen.isSafe(white,king,pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3())){
	        suffix += "+";
			if(NodeGen.getLegalMoves64(pos).length==0)
		        suffix += "+";
		}
		return prefix + " " + suffix;
    }
}
