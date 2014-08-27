package norwegiangambit.profile;

import java.util.ArrayList;

import norwegiangambit.engine.base.IConst;
import norwegiangambit.engine.base.NodeUtil;
import norwegiangambit.engine.fen.FEN;
import norwegiangambit.profile.Marking.MarkingType;

public class Manual extends Player {
 
    public Integer from = -1;

    @Override
    public void run() { 
    	System.out.println("MANUAL:"+FEN.getFen(getPosition()));
		game.updateBoard();
    }

    @Override
    public int clickSquare(int i) {
    	long[] bitmaps = moves.getBitmaps();
        if(from>-1){
        	long[] avail = NodeUtil.filterTo(NodeUtil.filterFrom(bitmaps, from), i);
            from=-1;
            if(avail.length>0)
                return (int) avail[0];
        }
        if(from==-1){
        	long[] avail = NodeUtil.filterFrom(bitmaps, i);
            if(avail.length>0)
                from=i;
        } 
        return -1;
    }

    @Override
    public ArrayList<Marking> getMarkers() {
        if(moves==null)
            return super.getMarkers();
        ArrayList<Marking> list = new ArrayList<Marking>();
        long[] bitmaps = moves.getBitmaps();
        if(from == -1) {
            int best = IConst.BITS.getFrom(bitmaps[0]);
            for (long n : bitmaps) {
                int fr = IConst.BITS.getFrom(n);
                list.add(new Marking(fr == best ? MarkingType.BestMoveFrom : MarkingType.MoveFrom, fr));
            }
        } else {
            list.add(new Marking(MarkingType.MarkFrom, from));
            for (long n : bitmaps) {
                if (IConst.BITS.getFrom(n) == from)
                    list.add(new Marking(MarkingType.MoveTo, IConst.BITS.getTo(n), 0));
            }
        }
        return list;
    }
}
