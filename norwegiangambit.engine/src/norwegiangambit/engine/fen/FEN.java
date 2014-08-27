package norwegiangambit.engine.fen;

import norwegiangambit.engine.base.IConst;
import norwegiangambit.engine.base.KingSafe;

public class FEN implements IConst {

    public static char type2fen(int type) {
        return PieceType.types[type].fen;
    }

    public static String type2name(int type) {
        return PieceType.types[type].name();
    }

    public static int[] fen2board(String fen_board) {
        int[] board = new int[64];
        int y = 56;
        int x = 0;
        for (int i = 0; i < fen_board.length(); i++) {
            char c = fen_board.charAt(i);
            if (c == '/') {
                y -= 8;
                x = 0;
            } else if (c == ' ') {
                break;
            } else if (c >= '0' && c <= '9') {
                x += Integer.parseInt(String.valueOf(c));
            } else if (c >= 'A' && c <= 'z') {
                board[x + y] = PieceType.lookup(c).bitmap;
                x++;
            }
        }
        return board;
    }

    /**
     * Standard Forsyth–Edwards Notation
     * 
     * @return
     */
    final public static String getFen(Position pos) {
        StringBuilder fen = new StringBuilder();
        fen.append(FEN.board2fen(pos));
        fen.append(" ");
        fen.append(pos.whiteNext() ? "w" : "b");
        fen.append(" ");
        fen.append(FEN.getFenCastling(pos));
        fen.append(" ");
        fen.append(FEN.pos2string(BITS.getEnpassant(pos.getBitmap())));
        fen.append(" ");
        fen.append(BITS.halfMoves(pos.getBitmap()));
        fen.append(" ");
        fen.append(pos.totalMoves());
        return fen.toString();
    }

    final public static String pos2string(int pos) {
        if (pos == -1)
            return "-";
        StringBuilder sb = new StringBuilder();
        sb.append("abcdefgh".charAt(pos & 7));
        sb.append("12345678".charAt(pos >> 3));
        return sb.toString();
    }

    final public static int text2pos(String pos) {
        if (pos == null || pos.length() != 2)
            return -1;
        return "abcdefgh".indexOf(pos.charAt(0)) + 8 * (pos.charAt(1) - '1');
    }

    final public static String board2string(Position pos) {
    	return board2string(pos.bb_bit1, pos.bb_bit2, pos.bb_bit3, pos.bb_black);
    }

	public static String board2string(long b1, long b2, long b3, long bb) {
		StringBuilder fen = new StringBuilder();
        for (int y = 8; y-- > 0;) {
            fen.append("\n");
            fen.append(String.valueOf(y+1)+" ");
            for (int x = 0; x < 8; x++) {
                int sq = y * 8 + x;
				long bit=1L<<sq;
				int p=((bit&b1)==0?0:1)+((bit&b2)==0?0:2)+((bit&b3)==0?0:4)+((bit&bb)==0?0:8);
				PieceType type = PieceType.types[p];
                fen.append(type == null ? (p==0?".":"X"): type.fen);
            }
        }
        fen.append("\n  ABCDEFGH");
        return fen.toString();
	}

	public static String board2string(long b1) {
		StringBuilder fen = new StringBuilder();
        for (int y = 8; y-- > 0;) {
            fen.append("\n");
            fen.append(String.valueOf(y+1)+" ");
            for (int x = 0; x < 8; x++) {
                int sq = y * 8 + x;
				long bit=1L<<sq;
                fen.append((bit&b1)==0 ? "." : "x");
            }
        }
        fen.append("\n  ABCDEFGH");
        return fen.toString();
	}

    final public static String board2string(int[] brd) {
        StringBuilder fen = new StringBuilder();
        for (int y = 8; y-- > 0;) {
            fen.append(String.valueOf(y+1)+" ");
            for (int x = 0; x < 8; x++) {
                PieceType type = PieceType.types[brd[y * 8 + x]];
                fen.append(type == null ? "." : type.fen);
            }
            fen.append("\n");
        }
        fen.append("  ABCDEFGH\n");
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

    final public static String getFenCastling(Position move) {
        StringBuilder sb = new StringBuilder();
        long state = BITS.getCastlingState(move.getBitmap());
        if ((state & IConst.CANCASTLE_WHITEKING) != 0)
            sb.append("K");
        if ((state & IConst.CANCASTLE_WHITEQUEEN) != 0)
            sb.append("Q");
        if ((state & IConst.CANCASTLE_BLACKKING) != 0)
            sb.append("k");
        if ((state & IConst.CANCASTLE_BLACKQUEEN) != 0)
            sb.append("q");
        return sb.toString();
    }

    public static void printPiece(int type, int pos) {
        PieceType pt = PieceType.types[type];
        System.out.println(pt == null ? "<none>" : pt.toString() + " " + pos2string(pos));
    }

    public static String printMove(Position pos) {
    	long bitmap = pos.getBitmap();
        StringBuilder sb = new StringBuilder();
        sb.append(PieceType.types[(int) (bitmap & PIECE)]);
        sb.append(" from " + FEN.pos2string(BITS.getFrom(bitmap)) + " to "
            + FEN.pos2string(BITS.getTo(bitmap)));
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
        String p = piecePrefix((int) (bitmap & PIECETYPE));
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

    public static String notation(int bitmap) {
        int from = BITS.getFrom(bitmap);
        int to = BITS.getTo(bitmap);
        String capture = ((bitmap >> _CAPTURE) & 7) != 0 ? "x" : "";
        String p = piecePrefix(bitmap & PIECETYPE);
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
        return prefix + " " + suffix;
    }

    private static String piecePrefix(int type) {
        switch (type) {
            case IConst.WK:
                return "K";
            case IConst.WQ:
                return "Q";
            case IConst.WR:
                return "R";
            case IConst.WB:
                return "B";
            case IConst.WN:
                return "N";
        }
        return "";
    }

	public final static String move2literal(long bitmap) {
		return FEN.pos2string(IConst.BITS.getFrom(bitmap))+FEN.pos2string(IConst.BITS.getTo(bitmap));
	}

	public static String addHorizontal(String a, String b) {
		StringBuffer sb=new StringBuffer();
		String[] al=a.split("\n");
		String[] bl=b.split("\n");
		for (int i = 0; i < al.length; i++) {
			sb.append(al[i]);
			if(bl.length>i){
				sb.append(" ");
				sb.append(bl[i]);
			}
			sb.append("\n");
		}
		return sb.toString();
	}

}
