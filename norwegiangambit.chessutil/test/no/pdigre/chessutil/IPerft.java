package no.pdigre.chessutil;

import java.util.HashMap;

public interface IPerft {

	public abstract int perft(String fen, int depth);

	public HashMap<String,Integer> divide(String fen, int depth);

}
