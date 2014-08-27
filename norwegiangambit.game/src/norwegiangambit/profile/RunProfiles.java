package norwegiangambit.profile;

import norwegiangambit.engine.base.IConst.BITS;
import norwegiangambit.engine.fen.FEN;

import org.junit.Assert;


public class RunProfiles {

	public static void testMove(Player player,String fen,String expected) {
	    Player.debug=true;
	    final StringBuffer results=new StringBuffer();
		GameData game = new GameData(){

			@Override
			public void updateBoard() {
				//
			}
        	
			@Override
			public void run() {
            	getPlayer().run();
			}
			
			@Override
			public void makeMove(long bitmap) {
//				System.out.println(">"+FEN.printMove(pos.move(bitmap)));
				int to = BITS.getTo(bitmap);
				results.append(FEN.pos2string(to));
			}
			
			@Override
			public int findBest(int[] bitmaps, int[] scores) {
//				for (int bitmap : bitmaps)
//					System.out.println(":"+FEN.printMove(pos.move(bitmap)));
				return super.findBest(bitmaps, scores);
			}
        };
		game.start(fen, player, player);
		Assert.assertEquals("Wrong result",expected,results.toString());
	}
	
}
