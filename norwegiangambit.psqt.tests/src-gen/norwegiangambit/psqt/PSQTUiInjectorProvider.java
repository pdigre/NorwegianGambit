/*
* generated by Xtext
*/
package norwegiangambit.psqt;

import org.eclipse.xtext.junit4.IInjectorProvider;

import com.google.inject.Injector;

public class PSQTUiInjectorProvider implements IInjectorProvider {
	
	public Injector getInjector() {
		return norwegiangambit.psqt.ui.internal.PSQTActivator.getInstance().getInjector("norwegiangambit.psqt.PSQT");
	}
	
}