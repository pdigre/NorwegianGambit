package norwegiangambit.engine.fen;

import norwegiangambit.engine.base.KingSafe;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.PieceType;

public class FEN_POS implements IConst {

    /**
     * Standard Forsyth–Edwards Notation
     * 
     * @return
     */
    final public static String getFen(Position pos) {
        StringBuilder fen = new StringBuilder();
        fen.append(FEN_POS.board2fen(pos));
        fen.append(" ");
        fen.append(pos.whiteNext() ? "w" : "b");
        fen.append(" ");
        fen.append(FEN.getFenCastling(pos.getBitmap()));
        fen.append(" ");
        fen.append(norwegiangambit.util.FEN.pos2string(BITS.getEnpassant(pos.getBitmap())));
        fen.append(" ");
        fen.append(BITS.halfMoves(pos.getBitmap()));
        fen.append(" ");
        fen.append(pos.totalMoves());
        return fen.toString();
    }

    final public static String board2fen(Position pos) {
        StringBuilder fen = new StringBuilder();
        for (int y = 8; y-- > 0;) {
            int i = 0;
            if (y != 7)
                fen.append("/");
            for (int x = 0; x < 8; x++) {
                PieceType type = PieceType.types[pos.getPiece(y * 8 + x)];
                if (type == null) {
                    i++;
                } else {
                    if (i > 0) {
                        fen.append(i);
                        i = 0;
                    }
                    fen.append(type.fen);
                }
            }
            if (i > 0)
                fen.append(i);
        }
        return fen.toString();
    }

    public static String printMove(Position pos) {
    	long bitmap = pos.getBitmap();
        StringBuilder sb = new StringBuilder();
        sb.append(PieceType.types[(int) (bitmap & PIECE)]);
        sb.append(" from " + norwegiangambit.util.FEN.pos2string(BITS.getFrom(bitmap)) + " to "
            + norwegiangambit.util.FEN.pos2string(BITS.getTo(bitmap)));
        long capture = ((bitmap >> _CAPTURE) & 7);
        if (capture != 0)
            sb.append(" beats " + PieceType.types[(int) (capture | ((bitmap & BLACK) ^ BLACK))]);
        if (BITS.isEnpassant(bitmap))
            sb.append(" enpassant");
        if (BITS.isCastling(bitmap))
            sb.append(" castling");
        if (BITS.isPromotion(bitmap))
            sb.append(" promoted");
        switch (KingSafe.getCheckState(pos)) {
            case IConst.CHECK:
                sb.append(" check");
                break;
            case IConst.MATE:
                sb.append(" checkmate");
                break;
        }
        sb.append(", " + notation(pos));
        return sb.toString();
    }

    public static String notation(Position pos) {
    	long bitmap = pos.getBitmap();
        int from = BITS.getFrom(bitmap);
        int to = BITS.getTo(bitmap);
        String capture = ((bitmap >> _CAPTURE) & 7) != 0 ? "x" : "";
        String p = norwegiangambit.util.FEN.piecePrefix((int) (bitmap & PIECETYPE));
        String prefix = capture + norwegiangambit.util.FEN.pos2string(from);
        String suffix = capture + norwegiangambit.util.FEN.pos2string(to);
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
