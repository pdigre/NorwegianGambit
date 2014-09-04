package norwegiangambit.game;

import java.util.Arrays;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.SWT;
import org.eclipse.swt.custom.StyledText;
import org.eclipse.swt.events.PaintEvent;
import org.eclipse.swt.events.PaintListener;
import org.eclipse.swt.graphics.Point;
import org.eclipse.swt.widgets.Control;
import org.eclipse.swt.widgets.Display;
import org.eclipse.swt.widgets.Shell;
import org.eclipse.ui.PlatformUI;

public class FenHover extends AbstractHandler {

	Point pos;
	boolean on=false;
	Shell pop;
	
	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		on=!on;
		cycleEvent();
		return null ;
	}

	public void cycleEvent() {
		if(on) {
			final Display display = PlatformUI.getWorkbench().getDisplay();
			display.timerExec(1000, new Runnable() {
				
				@Override
				public void run() {
					checkEvent(display);
					cycleEvent();
				}

			});
		}
	}

	public void checkEvent(Display display) {
		Point loc = display.getCursorLocation();
		if(pos!=null && pos.x==loc.x && pos.y==loc.y){
			Control c = display.getCursorControl();
			if(c instanceof StyledText){
				StyledText st=(StyledText) c;
				Point ll = st.toControl(loc);
				int offset = st.getOffsetAtLocation(ll);
				int max = Math.max(0,offset-40);
				int min = Math.min(st.getCharCount(),offset+80);
				String text = st.getText(max, min);
				String fen = getFen(text);
				if(fen!=null && pop==null){
					pop = new Shell(display,SWT.NO_TRIM);
					pop.setLocation(loc.x,loc.y+10);
					pop.setSize(208, 208);
					pop.addPaintListener(new PaintListener() {
						
						@Override
						public void paintControl(PaintEvent e) {
							DrawBoard.drawFen(e.gc,fen);
						}
					});
					pop.open();
					pop.redraw();
					pop.update();
				}
				System.out.println(fen);
			}
		} else {
			if(pop!=null){
				pop.close();
				pop.dispose();
				pop=null;
			}
		}
		pos = loc;
	}

	public String getFen(String text) {
		String[] split = text.replace("\n", " ").split(" ");
		int start=-1;
		for (int i = 0; i < split.length; i++) {
			if(split[i].split("/").length==8)
				start=i;
		}
		if(start==-1)
			return null;
		return String.join(" ", Arrays.copyOfRange(split, start, Math.min(split.length, start+6)));
	}
}
