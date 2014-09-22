package norwegiangambit.engine.fen;

import norwegiangambit.engine.iterate.IEvaluator;
import norwegiangambit.util.FEN;


public class StartGame extends PositionWithLog {

	private int fullMoves;

	public StartGame(String fen) {
		super();
		try {
			setBoard(FEN.fen2board(fen));
			fullMoves = FEN.getFullMoves(fen);
			bitmap = getBitmap(FEN.getEnpassant(fen),FEN.whiteNext(fen),FEN.getHalfMoves(fen),FEN.getCastling(fen));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}


	@Override
	public int totalMoves() {
		return fullMoves;
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
        return FEN.board2string(this.bb_bit1, this.bb_bit2, this.bb_bit3, this.bb_black) + "\nSTART\n";
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
