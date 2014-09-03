package norwegiangambit.engine;

import norwegiangambit.profile.GameData;
import norwegiangambit.profile.Player;
import norwegiangambit.util.FEN;

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
			public void makeMove(String bitmap) {
//				System.out.println(">"+FEN.printMove(pos.move(bitmap)));
				int to = FEN.text2pos(bitmap.substring(2, 4));
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
