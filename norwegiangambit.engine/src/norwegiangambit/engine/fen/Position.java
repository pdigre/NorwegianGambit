package norwegiangambit.engine.fen;

import norwegiangambit.engine.movegen.BASE;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.util.BITS;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;
import norwegiangambit.util.PieceType;
import norwegiangambit.util.polyglot.ZobristKey;

public class Position implements IConst, Comparable<Position> {

	public long bb_bit1;
	public long bb_bit2;
	public long bb_bit3;
	public long bb_black;
	public int wking;
	public int bking;
	public long bitmap;
	public long zobrist=0L;
	public int score=0;
	public int quality=0;
	public long castling=0L;
	public Position parent;
	
	public Position() {
		super();
	}

    public int totalMoves(){
    	return 0;
    }

	public void setBoard(int[] board) {
		for (int i = 0; i < 64; i++) {
			int p = board[i];
			if (p != 0) {
				switch (p) {
				case WK:
					wking = i;
					break;
				case BK:
					bking = i;
					break;
				}
				long bit = 1L << i;
				bb_bit1 |= ((p & 1) == 0 ? 0 : bit);
				bb_bit2 |= ((p & 2) == 0 ? 0 : bit);
				bb_bit3 |= ((p & 4) == 0 ? 0 : bit);
				bb_black |= ((p & 8) == 0 ? 0 : bit);
			}
		}
	}
	
	public Position(boolean whiteNext, long bitmap,int wking, int bking, long bb_black, long bb_bit1, long bb_bit2, long bb_bit3, long zobrist,int score) {
		this.bitmap = bitmap;
		this.wking = wking;
		this.bking = bking;
		this.bb_black = bb_black;
		this.bb_bit1 = bb_bit1;
		this.bb_bit2 = bb_bit2;
		this.bb_bit3 = bb_bit3;
		this.zobrist=zobrist;
		this.score=score;
	}

	public Position(boolean whiteNext, long bitmap, int wking, int bking, long bb_black, long bb_bit1, long bb_bit2, long bb_bit3) {
		this.bitmap = bitmap;
		this.wking = wking;
		this.bking = bking;
		this.bb_black = bb_black;
		this.bb_bit1 = bb_bit1;
		this.bb_bit2 = bb_bit2;
		this.bb_bit3 = bb_bit3;
		this.zobrist=0L;
		this.score=0;
	}

	public Position(Position pos) {
		super();
		this.bitmap = pos.bitmap;
		this.wking = pos.wking;
		this.bking = pos.bking;
		this.bb_black = pos.bb_black;
		this.bb_bit1 = pos.bb_bit1;
		this.bb_bit2 = pos.bb_bit2;
		this.bb_bit3 = pos.bb_bit3;
		this.zobrist=pos.zobrist;
		this.score=pos.score;
		this.parent=pos;
	}

	public boolean whiteNext() {
		return BITS.black(bitmap);
	}

	public int getWKpos() {
		return wking;
	}

	public int getBKpos() {
		return bking;
	}

	public long getBitmap() {
		return bitmap;
	}

	public long get64black() {
		return bb_black;
	}

	public long get64bit1() {
		return bb_bit1;
	}

	public long get64bit2() {
		return bb_bit2;
	}

	public long get64bit3() {
		return bb_bit3;
	}

	public int getPiece(int i) {
		long bit = 1L << i;
		return ((bb_bit1 & bit) == 0 ? 0 : 1) | ((bb_bit2 & bit) == 0 ? 0 : 2) | ((bb_bit3 & bit) == 0 ? 0 : 4) | ((bb_black & bit) == 0 ? 0 : 8);
	}

	public int[] getBoard() {
		int[] board = new int[64];
		for (int i = 0; i < board.length; i++)
			board[i] = getPiece(i);
		return board;
	}

	public String toString() {
		String string = FEN.board2string(this.bb_bit1, this.bb_bit2, this.bb_bit3, this.bb_black) + "\n " +(" << "+FEN.move2literal(bitmap)+"              ").substring(0,10) + "\n";
		return  parent==null?string:FEN.addHorizontal(string, parent.toString());
	}

	public long getZobristKey() {
		if(zobrist==0L){
			long bitmap = getBitmap();
			boolean isWhite = BITS.whiteNext(bitmap);
			long castling=bitmap&CASTLING_STATE;
			int enpassant = BITS.getEnpassant(bitmap);
			int[] brd = FEN.boardFrom64(bb_bit1, bb_bit2, bb_bit3, bb_black);
			zobrist=ZobristKey.getKey(isWhite, castling, enpassant, brd);
		}
		return zobrist;
	}


	public int getScore() {
		return score;
	}

	public int getQuality() {
		return quality;
	}

	public int compareTo(Position o) {
		return Integer.compare(score, o.getScore());
	}

	public static Position fen2pos(String fen){
		String[] split = fen.split(" ");
		String fenboard = split[0];
		boolean white = "w".equalsIgnoreCase(split[1]);
		String castling = split[2];
		int enpassant = norwegiangambit.util.FEN.text2pos(split[3]);
		int halfMoves = Integer.parseInt(split[4]);
//		int fullMoves = Integer.parseInt(split[5]);

        long enp=0;
        if(enpassant!=-1){
            if(white){
                enp=SPECIAL|WP|(enpassant+8)<<_FROM|(enpassant-8)<<_TO;
            }else {
                enp=SPECIAL|WP|(enpassant-8)<<_FROM|(enpassant+8)<<_TO;
            }
        }
		long cstl = (castling.contains("K") ? CANCASTLE_WHITEKING:0)
				| (castling.contains("Q") ? CANCASTLE_WHITEQUEEN:0)
				| (castling.contains("k") ? CANCASTLE_BLACKKING:0)
				| (castling.contains("q") ? CANCASTLE_BLACKQUEEN:0);
		long bitmap=(halfMoves<<_HALFMOVES)|cstl|(white?BLACK:0)|enp;


        /*********************************
         * Read board
         * *******************************/
		int[] board = new int[64];
        int y = 56;
        int x = 0;
        for (int i = 0; i < fenboard.length(); i++) {
            char c = fenboard.charAt(i);
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

        
        /*********************************
         * Assign bitmaps
         * *******************************/
        int wking = 0;
		int bking = 0;
		long bb_bit1=0L;
		long bb_bit2=0L;
		long bb_bit3=0L;
		long bb_black=0L;
		for (int i = 0; i < 64; i++) {
			int p = board[i];
			if (p != 0) {
				switch (p) {
				case WK:
					wking = i;
					break;
				case BK:
					bking = i;
					break;
				}
				long bit = 1L << i;
				bb_bit1 |= ((p & 1) == 0 ? 0 : bit);
				bb_bit2 |= ((p & 2) == 0 ? 0 : bit);
				bb_bit3 |= ((p & 4) == 0 ? 0 : bit);
				bb_black |= ((p & 8) == 0 ? 0 : bit);
			}
		}
		return new Position(white, bitmap, wking, bking, bb_black, bb_bit1, bb_bit2, bb_bit3);
	}
	
	public void make(int i,long p_castling) {
		MOVEDATA m=BASE.ALL[i];
		bitmap=m.bitmap&(~CASTLING_STATE | p_castling);
		bb_black ^=m.b_black;
		bb_bit1 ^=m.b_bit1;
		bb_bit2 ^=m.b_bit2;
		bb_bit3 ^=m.b_bit3;
		int type = BITS.getPiece(bitmap);
		if(type==IConst.WK)
			wking=BITS.getTo(bitmap);
		else if(type==IConst.BK)
			bking=BITS.getTo(bitmap);
	}

	public void undo(MOVEDATA m,long bitmap) {
		this.bitmap=bitmap;
		bb_black ^=m.b_black;
		bb_bit1 ^=m.b_bit1;
		bb_bit2 ^=m.b_bit2;
		bb_bit3 ^=m.b_bit3;
		
		int type = BITS.getPiece(bitmap);
		if(type==IConst.WK)
			wking=BITS.getTo(bitmap);
		else if(type==IConst.BK)
			bking=BITS.getTo(bitmap);
	}
	
    final public String getFen() {
        StringBuilder fen = new StringBuilder();
        fen.append(FEN.board2fen(getBoard()));
        fen.append(" ");
        fen.append(whiteNext() ? "w" : "b");
        fen.append(" ");
        fen.append(FEN.getFenCastling(getBitmap()));
        fen.append(" ");
        fen.append(FEN.pos2string(BITS.getEnpassant(getBitmap())));
        fen.append(" ");
        fen.append(BITS.halfMoves(getBitmap()));
        fen.append(" ");
        fen.append(totalMoves());
        return fen.toString();
    }

}
