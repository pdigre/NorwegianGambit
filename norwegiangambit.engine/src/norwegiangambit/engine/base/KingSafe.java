package norwegiangambit.engine.base;

import norwegiangambit.engine.fen.Position;
import norwegiangambit.util.FEN;
import norwegiangambit.util.IConst;

public class KingSafe implements IConst {

	public static KingSafe pos(Position pos) {
		return new KingSafe(pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
	}

	public static KingSafe pos(Position pos,MOVEDATA md) {
		return new KingSafe(pos.get64black()^md.b_black, pos.get64bit1()^md.b_bit1, pos.get64bit2()^md.b_bit2, pos.get64bit3()^md.b_bit3);
	}

	final private long bb_black;
	final private long bb_piece;
	final private long bb_bit1;
	final private long bb_bit2;
	final private long bb_bit3;

	public KingSafe(long bb_black, long bb_bit1, long bb_bit2, long bb_bit3) {
		this.bb_bit1 = bb_bit1;
		this.bb_bit2 = bb_bit2;
		this.bb_bit3 = bb_bit3;
		this.bb_piece = bb_bit1 | bb_bit2 | bb_bit3;
		this.bb_black = bb_black;
	}

	final public boolean isSafeWhite() {
		return isSafeWhite(Long.numberOfTrailingZeros(bb_bit1 & bb_bit2 & ~bb_bit3 & ~bb_black));
	}
	
	final public boolean isSafeBlack() {
		return isSafeBlack(Long.numberOfTrailingZeros(bb_bit1 & bb_bit2 & ~bb_bit3 & bb_black));
	}
	
	final public boolean isSafeWhite(int king) {
		if(king==64){
			errorKing();
			return false;
		}
		SQATK rev = BASE.REV[king];
		long e=bb_black;
		if (((~bb_bit1 & bb_bit2 & ~bb_bit3 & e) & rev.RN) != 0)
			return false;
		if (((bb_bit1 & bb_bit2 & ~bb_bit3 & e) & rev.RK) != 0)
			return false;
		MWQ x = MWQ.WQ[king];
		long slider=bb_bit3 & e;
		if((slider & rev.RQ) !=0){
			if ((bb_bit1 & slider & rev.RB) != 0) {
				if(bdiag(x.UL)||bdiag(x.UR)||bdiag(x.DL)||bdiag(x.DR))
					return false;
			}
			if ((bb_bit2 & slider & rev.RR) != 0) {
				if(bline(x.U)||bline(x.D)||bline(x.L)||bline(x.R))
					return false;
			}
		}
		if (((bb_bit1 & ~bb_bit2 & ~bb_bit3 & e) & MBP.REV[king]) != 0)
			return false;
		if (((bb_bit1 & bb_bit2 & ~bb_bit3 & e) & rev.RK) != 0)
			return false;
		return true;
	}

	public void errorKing() {
		System.out.println("ERROR KINGPOS:"+toString());
	}

	final public boolean isSafeBlack(int king) {
		if(king==64)
			errorKing();
		SQATK rev = BASE.REV[king];
		long e=~bb_black;
		if (((~bb_bit1 & bb_bit2 & ~bb_bit3 & e) & rev.RN) != 0)
			return false;
		if (((bb_bit1 & bb_bit2 & ~bb_bit3 & e) & rev.RK) != 0)
			return false;
		MWQ x = MWQ.WQ[king];
		long slider=bb_bit3 & e;
		if((slider & rev.RQ) !=0){
			if ((bb_bit1 & slider & rev.RB) != 0) {
				if(wdiag(x.UL)||wdiag(x.UR)||wdiag(x.DL)||wdiag(x.DR))
					return false;
			}
			if ((bb_bit2 & slider & rev.RR) != 0) {
				if(wline(x.U)||wline(x.D)||wline(x.L)||wline(x.R))
					return false;
			}
		}
		if (((bb_bit1 & ~bb_bit2 & ~bb_bit3 & e) & MWP.REV[king]) != 0)
			return false;
		if (((bb_bit1 & bb_bit2 & ~bb_bit3 & e) & rev.RK) != 0)
			return false;
		return true;
	}

	private boolean wdiag(MOVEDATA[] s) {
		for (MOVEDATA m : s) {
			long bit = m.bto;
			if ((bb_piece & bit) != 0)
				return (bb_black & bit) == 0 && (bb_bit1 & bit) != 0 &&  (bb_bit3 & bit) != 0;
		}
		return false;
	}

	private boolean wline(MOVEDATA[] s) {
		for (MOVEDATA m : s) {
			long bit = m.bto;
			if ((bb_piece & bit) != 0)
				return (bb_black & bit) == 0 && (bb_bit2 & bit) != 0 &&  (bb_bit3 & bit) != 0;
		}
		return false;
	}
	
	private boolean bdiag(MOVEDATA[] s) {
		for (MOVEDATA m : s) {
			long bit = m.bto;
			if ((bb_piece & bit) != 0)
				return (bb_black & bit) != 0 && (bb_bit1 & bit) != 0 &&  (bb_bit3 & bit) != 0;
		}
		return false;
	}

	private boolean bline(MOVEDATA[] s) {
		for (MOVEDATA m : s) {
			long bit = m.bto;
			if ((bb_piece & bit) != 0)
				return (bb_black & bit) != 0 && (bb_bit2 & bit) != 0 &&  (bb_bit3 & bit) != 0;
		}
		return false;
	}
	
	@Override
	public String toString() {
		return FEN.board2string(bb_bit1,bb_bit2,bb_bit3,bb_black);
	}
}
