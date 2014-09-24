package norwegiangambit.engine.iterate;

import norwegiangambit.engine.base.PSQT;
import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.BITS;
import norwegiangambit.util.IConst;

public class TacticEval implements IEvaluator {

	@Override
	public int score(Position pos, int last) {
		return _score(pos,last);
	}

	private int _score(Position pos, int last) {
		long bitmap = pos.getBitmap();
		int piece = BITS.getPiece(bitmap);
		int from = BITS.getFrom(bitmap);
		int to = BITS.getTo(bitmap);
		if (BITS.isCapture(bitmap)) {
			if(BITS.isEnpassant(bitmap)){
				int captured = BITS.getCaptured(bitmap);
				int to2 = BITS.white(bitmap)?(to-8):(to+8);
				int pVal = PSQT.pVal(to2, captured)[0];
				last -= pVal;
			} else {
				int captured = BITS.getCaptured(bitmap);
				int pVal = PSQT.pVal(to, captured)[0];
				last -= pVal;
			}
		}
		if (BITS.isPromotion(bitmap)){
			int vt = PSQT.pVal(to, piece)[0];
			int vf = PSQT.pVal(from, BITS.white(bitmap)?IConst.WP:IConst.BP)[0];
			return last + vt - vf;
		}
		if (piece == IConst.BK) {
			return last + PSQT.BKING[0][to] - PSQT.BKING[0][from];
		} else if (piece == IConst.WK) {
			int vto = PSQT.KING[0][to];
			int vfrom = PSQT.KING[0][from];
			return last + vto - vfrom;
		} else if (piece > 0) {
			int vt = PSQT.pVal(to, piece)[0];
			int vf = PSQT.pVal(from, piece)[0];
			return last + vt - vf;
		}
		return last;
	}

	public static final int value(final int type) {
		switch (type) {
		case IConst.WP:
			return 100;
		case IConst.WN:
			return 320;
		case IConst.WB:
			return 330;
		case IConst.WR:
			return 500;
		case IConst.WQ:
			return 900;
		case IConst.WK:
			return 20000;
		case IConst.BP:
			return -100;
		case IConst.BN:
			return -320;
		case IConst.BB:
			return -330;
		case IConst.BR:
			return -500;
		case IConst.BQ:
			return -900;
		case IConst.BK:
			return -20000;
		default:
			return 0;
		}
	}

	public static final int promoValue(final int type) {
		switch (type) {
		case IConst.WN:
			return 220;
		case IConst.WB:
			return 230;
		case IConst.WR:
			return 400;
		case IConst.WQ:
			return 800;
		case IConst.BN:
			return -220;
		case IConst.BB:
			return -230;
		case IConst.BR:
			return -400;
		case IConst.BQ:
			return -800;
		default:
			return 0;
		}
	}
}