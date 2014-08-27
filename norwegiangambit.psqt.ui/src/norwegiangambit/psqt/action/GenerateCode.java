package norwegiangambit.psqt.action;

import org.eclipse.core.commands.AbstractHandler;
import org.eclipse.core.commands.ExecutionEvent;
import org.eclipse.core.commands.ExecutionException;
import org.eclipse.core.resources.IFile;
import org.eclipse.jface.viewers.TreeSelection;
import org.eclipse.ui.PlatformUI;

import com.google.inject.Injector;

public class GenerateCode extends AbstractHandler {

	@Override
	public Object execute(ExecutionEvent event) throws ExecutionException {
		Injector injector = new PSQTUiInjectorProvider().getInjector();
		TestPSQT instance = injector.getInstance(TestPSQT.class);
		TreeSelection sel = (TreeSelection) PlatformUI.getWorkbench().getActiveWorkbenchWindow().getActivePage().getSelection();
		IFile res = (IFile) sel.getFirstElement();
		String path = res.getRawLocation().toString();
		try {
			instance.createCodeFile(path);
			res.getParent().refreshLocal(1, null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null ;
	}
}
