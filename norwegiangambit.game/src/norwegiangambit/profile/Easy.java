package norwegiangambit.profile;

import norwegiangambit.util.EngineStockfish;


public class Easy extends Player {

	static EngineStockfish engine=new EngineStockfish("C:/chess/stockfish.exe");

	@Override
    public String getDescription() {
        return "Easy 5 iterations Stockfish";
    }
    
    @Override
    public void run() {
    	String move=engine.play(game.fen,8);
		game.makeMove(move);
    }

}
