package norwegiangambit.util;

import java.util.HashMap;

import static norwegiangambit.util.ShortIntPairs.*;

public class PSQT implements IConst{
	
	private int[][] PSQT_ARR;
	private int[] PVAL;
    
	public int psqt(int sq, int piece) {
    	return PSQT_ARR[piece][sq];
    }

	final private int[] get(int[][] psqt,int piece,int mid_end){
		int[] ret=new int[64];
		for (int i = 0; i < 64; i++)
			ret[i]=psqt[piece][i]+mid_end;
		return ret;
	}

	/**
	 * @param pt - piecetype
	 * @param val - S(mid,end) shortint - pair piece value
	 * @param psqt2 - S(mid,end)[] Positional adjustments for [piece][sq]
	 */
	public PSQT(int[] pt, int[] val, int[][] psqt2) {
		PVAL=new int[16];
		PSQT_ARR=new int[16][];
		for (int i = 0; i < pt.length; i++){
			int p=pt[i];
			PVAL[p]=val[i];
			int[] fill = get(psqt2,i,SS(val[i]));
			PSQT_ARR[p]=fill;
			PSQT_ARR[p+8]=invert(fill);
		}
	}

	private int[] invert(int[] w) {
        int[] b = new int[64];
	        for (int r = 0; r < 8; r++)
	            for (int c = 0; c < 8; c++){
	                int o2 = w[8 * r + c];
					b[8 * (7 - r) + c] = S(-M(o2),-E(o2));
	            }
        return b;
	}

	public int pVal(int type) {
		return PVAL[type];
	}
	
	HashMap<String, String> params=new HashMap<String, String>();
	
	/** Comma and Equal separated list of parameters name-value
	 * 
	 * @param params
	 */
	public void init(String params) {
		for (String nvs : params.split(",")) {
			String[] nv = nvs.split("=");
			this.params.put(nv[0], nv[1]);
		}
	}

}
