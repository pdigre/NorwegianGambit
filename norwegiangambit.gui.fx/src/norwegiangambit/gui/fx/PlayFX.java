package norwegiangambit.gui.fx;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.jface.text.TextSelection;
import org.eclipse.jface.viewers.ISelection;
import org.eclipse.swt.widgets.Display;
import org.eclipse.ui.IWorkbenchPart;
import org.eclipse.ui.PlatformUI;

public class PlayFX extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		String fen = null;
		IWorkbenchPart activePart = PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getActivePart();
		if (activePart != null) {
			ISelection selection = activePart.getSite().getSelectionProvider().getSelection();
			if (selection instanceof TextSelection) {
				fen = ((TextSelection) selection).getText();
			}
		}
		Display display = PlatformUI.getWorkbench().getDisplay();
		
		try {
			new FxChessSWT(display,fen);
		} catch (Throwable e) {
			e.printStackTrace();
		}
		return null ;
	}
}