package norwegiangambit.profile;

import java.lang.Thread.State;
import java.util.ArrayList;

import norwegiangambit.engine.fen.PositionWithLog;
import norwegiangambit.engine.fen.StartGame;
import norwegiangambit.profile.IPlayer.Players;

public abstract class GameData {

    public PositionWithLog pos;

    private Player[] players=new Player[2];

	public Thread thread;

    public void setPlayer(Players profile,boolean white){
    	Player player = profile.getInstance();
    	setPlayer(player, white);
    }
    
    public void setPlayer(Player player,boolean white){
    	players[white?0:1]=player;
    	player.setGameData(this);
    }
    
    public void setupFEN(String fen) {
        pos = new StartGame(fen);
        getPlayer().prepare();
		updateBoard();
    }

    public void run() {
        final Player player = getPlayer();
        player.prepare();
        thread = new Thread(new Runnable() {

            @Override
            public void run() {
                player.run();
            }
        });
        thread.start();
    }

    protected Player getPlayer() {
        return players[pos.whiteNext() ? 0 : 1];
    }

    public void clickSquare(int i) {
        Player player = getPlayer();
        int move=player.clickSquare(i);
        if(move>-1)
            makeMove(move);
        else
        	updateBoard();
    }

    public void makeMove(long bitmap) {
        pos = new PositionWithLog(pos, bitmap);
        System.out.println("\n<=======================================>\n"+pos.toString());
		updateBoard();
        run();
    }

    public abstract void updateBoard();

    public ArrayList<Marking> getMarkers() {
        return getPlayer().getMarkers();
    }

    public void start(String fen, Players p_white, Players p_black) {
        setPlayer(p_white, true);
        setPlayer(p_black, false);
        setupFEN(fen);
        run();
    }

    public void start(String fen, Player p_white, Player p_black) {
        setPlayer(p_white, true);
        setPlayer(p_black, false);
        setupFEN(fen);
        run();
    }

    @SuppressWarnings("deprecation")
    public void startEvent(String fen, Players p_white, Players p_black) {
        if (thread.isAlive()) {
            State state = thread.getState();
            if (state == State.RUNNABLE) {
                thread.suspend();
            } else {
                thread.resume();
            }
        } else {
            start(fen, p_white, p_black);
        }
    }

	/**
	 * @param bitmaps
	 * @param scores
	 * @return
	 */
	@SuppressWarnings("static-method")
    public int findBest(int[] bitmaps, int[] scores) {
		return bitmaps[0];
	}

}
