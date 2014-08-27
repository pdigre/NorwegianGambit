package norwegiangambit.engine.evaluate;

import norwegiangambit.engine.base.IConst;
import norwegiangambit.engine.base.IConst.BITS;
import norwegiangambit.engine.fen.Position;

public abstract interface IEvaluator {

    /**
     * Calculated gain in positional evaluation based on last move
     * @param total TODO
     * 
     * @return
     */
    public abstract int score(Position pos, int total);

    public final static IEvaluator BASIC = new IEvaluator() {
        public final int value(final int type) {
            switch (type) {
                case IConst.WP:
                    return 100;
                case IConst.WN:
                    return 320;
                case IConst.WB:
                    return 330;
                case IConst.WR:
                    return 500;
                case IConst.WQ:
                    return 900;
                case IConst.WK:
                    return 100000;
                case IConst.BP:
                    return -100;
                case IConst.BN:
                    return -300;
                case IConst.BB:
                    return -325;
                case IConst.BR:
                    return -500;
                case IConst.BQ:
                    return -900;
                case IConst.BK:
                    return -100000;
                default:
                    return 0;
            }
        }
        
        public final int tacticValue(long bitmap) {
            int val = value(BITS.getCapturedType(bitmap));
            if(BITS.isPromotion(bitmap))
                val+=value(BITS.getType(bitmap));
            return val;
        }

        @Override
        public int score(Position pos, int total) {
            return tacticValue(pos.getBitmap());
        }

    };

    public static IEvaluator FULL = new FullEval();
    public static IEvaluator TACTICAL = new TacticEval();
}
