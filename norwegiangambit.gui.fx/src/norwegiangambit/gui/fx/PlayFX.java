package norwegiangambit.gui.fx;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.PlatformUI;

public class PlayFX extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Display display = PlatformUI.getWorkbench().getDisplay();
		
		try {
			new FxChessSWT(display);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null ;
	}
}