package norwegiangambit.engine.fen;

import norwegiangambit.engine.base.IConst;


public enum PieceType {
    WHITE_PAWN(100, 'P', IConst.WP),
    WHITE_KNIGHT(300, 'N', IConst.WN),
    WHITE_BISHOP(300, 'B', IConst.WB),
    WHITE_ROOK(500, 'R', IConst.WR),
    WHITE_QUEEN(900, 'Q', IConst.WQ),
    WHITE_KING(20000, 'K', IConst.WK),
    BLACK_PAWN(-100, 'p', IConst.BP),
    BLACK_KNIGHT(-300, 'n', IConst.BN),
    BLACK_BISHOP(-300, 'b', IConst.BB),
    BLACK_ROOK(-500, 'r', IConst.BR),
    BLACK_QUEEN(-900, 'q', IConst.BQ),
    BLACK_KING(-20000, 'k', IConst.BK);

    final public int weight;

    final public char fen;

    final public int bitmap;


    final public static PieceType[] types = assign();

    PieceType(int weight, char fen, int bitmap) {
        this.weight = weight;
        this.fen = fen;
        this.bitmap = bitmap;
    }

    private static PieceType[] assign() {
    	PieceType[] pcs=new PieceType[16];
    	PieceType[] values = PieceType.values();
    	for (PieceType pt : values)
			pcs[pt.bitmap]=pt;
    	return pcs;
	}

	final public static PieceType lookup(char fen) {
        for (PieceType type : PieceType.values()) {
            if (type.fen == fen)
                return type;
        }
        return null;
    }


}