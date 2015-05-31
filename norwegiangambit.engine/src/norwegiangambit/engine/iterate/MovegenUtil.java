package norwegiangambit.engine.iterate;

import norwegiangambit.engine.Movegen;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movedata.MOVEDATA;

public class MovegenUtil {

	public static String[] getLegalMoves(String fen) {
		StartGame pos = new StartGame(fen);
		Movegen mg = new Movegen();
		mg.set(pos.whiteNext(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3(), pos.getBitmap());
		int[] mds = mg.legalmoves();
		String[] moves=new String[mds.length];
		for (int i = 0; i < mds.length; i++)
			moves[i]=MOVEDATA.ALL[mds[i]].id();
		return moves;
	}
	
}
