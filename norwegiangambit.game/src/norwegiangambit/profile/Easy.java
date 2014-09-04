package norwegiangambit.profile;

import norwegiangambit.util.EngineStockfish;


public class Easy extends Player {

	EngineStockfish engine=new EngineStockfish(EngineStockfish.DEFAULT_EXEPATH);

	@Override
    public String getDescription() {
        return "Easy 8 iterations Stockfish";
    }
    
    @Override
    public void run() {
    	String move=engine.play(game.fen,8);
		game.makeMove(move);
    }

}
