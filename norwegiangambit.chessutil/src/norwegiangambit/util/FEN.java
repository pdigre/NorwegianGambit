package norwegiangambit.util;




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

    final public static String board2string(String fen) {
    	return board2string(fen2board(fen));
    }

    /**
     * Standard Forsyth–Edwards Notation
     * 
     * @return
     */
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

    public static void printPiece(int type, int pos) {
        PieceType pt = PieceType.types[type];
        System.out.println(pt == null ? "<none>" : pt.toString() + " " + pos2string(pos));
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

    public static String piecePrefix(int type) {
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

    private static int promoType(String p) {
    	if("q".equalsIgnoreCase(p))
    		return IConst.WQ;
    	if("r".equalsIgnoreCase(p))
    		return IConst.WR;
    	if("b".equalsIgnoreCase(p))
    		return IConst.BK;
    	if("n".equalsIgnoreCase(p))
    		return IConst.WN;
        return 0;
    }

	public final static String move2literal(long bitmap) {
		int t = BITS.getTo(bitmap);
		int f = BITS.getFrom(bitmap);
		if(f==t)
			return "START";
		String fromto = FEN.pos2string(f)+FEN.pos2string(t);
		if(!BITS.isPromotion(bitmap))		
			return fromto;
		return fromto+FEN.piecePrefix(BITS.getType(bitmap)).toLowerCase();
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

	public static String make(String fen, String move) {
		int[] brd = fen2board(fen);
		String[] split = fen.split(" ");
		boolean isWhite = split[1].equals("w");
		String castling=split[2];
		String enpassant="-";
		int from=text2pos(move.substring(0,2));
		int to=text2pos(move.substring(2,4));
		int p=brd[from];
		int c=brd[to];
		brd[from]=0;
		if(move.length()==5) // promotion
			p=promoType(move.substring(4));
		brd[to]=p;
		// castling
		if(p==WK && from==WK_STARTPOS){
			if(to==from+2)
				castle(brd,WR_KING_STARTPOS,from+1);
			if(to==from-2)
				castle(brd,WR_QUEEN_STARTPOS,from-1);
		}
		if(p==BK && from==BK_STARTPOS){
			if(to==from+2)
				castle(brd,BR_KING_STARTPOS,from+1);
			if(to==from-2)
				castle(brd,BR_QUEEN_STARTPOS,from-1);
		}
		 // enpassant
		if(p==WP && c==0 && (to-from)%8!=0)
			brd[to-8]=0;
		if(p==BP && c==0 && (from-to)%8!=0)
			brd[to+8]=0;
		if(p==WP && to==from+16)
			enpassant=pos2string(from+8);
		if(p==BP && to==from-16)
			enpassant=pos2string(from-8);
		if(p==WK && from == WK_STARTPOS)
			castling=castling.replace("K", "").replace("Q", "");
		if(p==BK && from==BK_STARTPOS)
			castling=castling.replace("k", "").replace("q", "");
		if(from==WR_KING_STARTPOS || to==WR_KING_STARTPOS)
			castling=castling.replace("K", "");
		if(from==WR_QUEEN_STARTPOS || to==WR_QUEEN_STARTPOS)
			castling=castling.replace("Q", "");
		if(from==BR_KING_STARTPOS || to==BR_KING_STARTPOS)
			castling=castling.replace("k", "");
		if(from==BR_QUEEN_STARTPOS || to==BR_QUEEN_STARTPOS)
			castling=castling.replace("q", "");
		String fen2 = board2fen(brd);
		split[0]=fen2;
		split[1]=isWhite?"b":"w";
		split[2]=castling;
		split[3]=enpassant;
		if(split.length>4)
			split[4]=String.valueOf((Integer.parseInt(split[4])+1));
		if(split.length>5)
			split[5]=String.valueOf((Integer.parseInt(split[5])+1));
		String join = String.join(" ", split);
		return join;
	}

	private static void castle(int[] brd, int from, int to) {
		brd[to]=brd[from];
		brd[from]=0;
	}

    public static String getFenCastling(long bitmap) {
		StringBuilder sb = new StringBuilder();
		long state = BITS.getCastlingState(bitmap);
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

    final public static String board2fen(int[] brd) {
        StringBuilder fen = new StringBuilder();
        for (int y = 8; y-- > 0;) {
            int i = 0;
            if (y != 7)
                fen.append("/");
            for (int x = 0; x < 8; x++) {
                PieceType type = PieceType.types[brd[y * 8 + x]];
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


}
