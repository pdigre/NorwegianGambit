package norwegiangambit.engine.base;

import norwegiangambit.engine.fen.StartGame;

public class MovegenUtil {

	public static String[] getLegalMoves(String fen) {
		StartGame pos = new StartGame(fen);
		Movegen mg = new Movegen(pos);
		MOVEDATA[] mds = mg.legalmoves();
		String[] moves=new String[mds.length];
		for (int i = 0; i < mds.length; i++)
			moves[i]=mds[i].id();
		return moves;
	}
	
}
