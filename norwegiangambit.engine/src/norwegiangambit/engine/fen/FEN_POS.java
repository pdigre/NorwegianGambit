package norwegiangambit.engine.fen;

import norwegiangambit.engine.base.KingSafe;
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
		switch (KingSafe.getCheckState(pos)) {
		    case IConst.CHECK:
		        suffix += "+";
		        break;
		    case IConst.MATE:
		        suffix += "++";
		        break;
		}
		return prefix + " " + suffix;
    }
}
