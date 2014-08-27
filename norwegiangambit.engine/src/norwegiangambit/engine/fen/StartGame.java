package norwegiangambit.engine.fen;

import norwegiangambit.engine.evaluate.IEvaluator;

public class StartGame extends PositionWithLog {

	private final int fullMoves;

	public StartGame(String fen) {
		super();
		String[] split = fen.split(" ");
		setBoard(FEN.fen2board(split[0]));
		fullMoves = Integer.parseInt(split[5]);
		bitmap = getBitmap(FEN.text2pos(split[3]),"w".equalsIgnoreCase(split[1]),Integer.parseInt(split[4]),getCastlingState(split[2]));
	}

	@Override
	public int totalMoves() {
		return fullMoves;
	}

	private long getCastlingState(String castling) {
		return (castling.contains("K") ? CANCASTLE_WHITEKING:0)
				| (castling.contains("Q") ? CANCASTLE_WHITEQUEEN:0)
				| (castling.contains("k") ? CANCASTLE_BLACKKING:0)
				| (castling.contains("q") ? CANCASTLE_BLACKQUEEN:0);
	}

    private long getBitmap(int enpassant,boolean white,int halfMoves,long castling) {
        int enp=0;
        if(enpassant!=-1){
            if(white){
                enp=SPECIAL|WP|(enpassant+8)<<_FROM|(enpassant-8)<<_TO;
            }else {
                enp=SPECIAL|WP|(enpassant-8)<<_FROM|(enpassant+8)<<_TO;
            }
        }
        return (halfMoves<<_HALFMOVES)|castling|(white?BLACK:0)|enp;
    }

    @Override
    public String toString() {
        return FEN.printMove(this)+"\n"+FEN.board2string(this);
    }

	@Override
	public long getZobristKey() {
		return 0L;
	}

	@Override
	public int getScore() {
		return IEvaluator.FULL.score(this, 0);
	}

	@Override
	public int getQuality() {
		return 0;
	}

	@Override
	public int compareTo(Position o) {
		return Integer.compare(getScore(), o.getScore());
	}
}
