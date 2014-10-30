package norwegiangambit.engine.iterate;

import norwegiangambit.engine.fen.Position;
import norwegiangambit.engine.movegen.MBase;
import norwegiangambit.util.IConst;

public class FullEval implements IEvaluator {

    @Override
    public int score(Position pos, int total) {
        int bk = 0;
        int wk = 0;
        int pcs = 0;
        int score = 0;
        for (int i = 0; i < 64; i++) {
            int p = pos.getPiece(i);
            if (p == IConst.BK) {
                bk = i;
            } else if (p == IConst.WK) {
                wk = i;
            } else if (p > 0) {
                score += MBase.psqt(i, p)[0];
                pcs++;
            }
        }
        score += MBase.psqt(wk,IConst.WK)[pcs < 15 && false?1:0];
        score += MBase.psqt(wk,IConst.BK)[pcs < 15 && false?1:0];
        return score;
    }
}