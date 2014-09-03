package norwegiangambit.profile;

import java.util.ArrayList;

import norwegiangambit.profile.Marking.MarkingType;
import norwegiangambit.util.FEN;

public class Manual extends Player {
 
    public Integer from = -1;

    @Override
    public void run() { 
    	System.out.println("MANUAL:"+getFen());
		game.updateBoard();
    }

    @Override
    public String clickSquare(int i) {
    	String[] moves = getMoves();
        if(from>-1){
        	String[] avail = FEN.filterTo(FEN.filterFrom(moves, from), i);
            from=-1;
            if(avail.length>0)
                return avail[0];
        }
        if(from==-1){
        	String[] avail = FEN.filterFrom(moves, i);
            if(avail.length>0)
                from=i;
        } 
        return null;
    }

    @Override
    public ArrayList<Marking> getMarkers() {
        ArrayList<Marking> list = new ArrayList<Marking>();
        if(from == -1) {
            int best = FEN.text2pos(mvs[0].substring(0,2));
            for (String n : mvs) {
                int fr = FEN.text2pos(n.substring(0,2));
                list.add(new Marking(fr == best ? MarkingType.BestMoveFrom : MarkingType.MoveFrom, fr));
            }
        } else {
            list.add(new Marking(MarkingType.MarkFrom, from));
            for (String n : mvs) {
                if (FEN.text2pos(n.substring(0,2)) == from)
                    list.add(new Marking(MarkingType.MoveTo, FEN.text2pos(n.substring(2,4)), 0));
            }
        }
        return list;
    }
}
