/*
 * generated by Xtext
 */
package norwegiangambit.psqt.validation;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.emf.ecore.EPackage;

public class AbstractPSQTValidator extends org.eclipse.xtext.validation.AbstractDeclarativeValidator {

	@Override
	protected List<EPackage> getEPackages() {
	    List<EPackage> result = new ArrayList<EPackage>();
	    result.add(norwegiangambit.psqt.pSQT.PSQTPackage.eINSTANCE);
		return result;
	}
}
