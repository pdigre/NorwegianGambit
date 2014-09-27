package norwegiangambit.engine.iterate;

import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.engine.movegen.MOVEDATA;
import norwegiangambit.engine.movegen.Movegen;

public class MovegenUtil {

	public static String[] getLegalMoves(String fen) {
		StartGame pos = new StartGame(fen);
		Movegen mg = new Movegen();
		mg.set(pos.whiteNext(), pos.getBitmap(), pos.getWKpos(), pos.getBKpos(), pos.get64black(), pos.get64bit1(), pos.get64bit2(), pos.get64bit3());
		MOVEDATA[] mds = mg.legalmoves();
		String[] moves=new String[mds.length];
		for (int i = 0; i < mds.length; i++)
			moves[i]=mds[i].id();
		return moves;
	}
	
}
