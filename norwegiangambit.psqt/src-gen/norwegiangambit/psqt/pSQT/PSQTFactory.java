/**
 */
package norwegiangambit.psqt.pSQT;

import org.eclipse.emf.ecore.EFactory;

/**
 * <!-- begin-user-doc -->
 * The <b>Factory</b> for the model.
 * It provides a create method for each non-abstract class of the model.
 * <!-- end-user-doc -->
 * @see norwegiangambit.psqt.pSQT.PSQTPackage
 * @generated
 */
public interface PSQTFactory extends EFactory
{
  /**
   * The singleton instance of the factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PSQTFactory eINSTANCE = norwegiangambit.psqt.pSQT.impl.PSQTFactoryImpl.init();

  /**
   * Returns a new object of class '<em>PSQT Model</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>PSQT Model</em>'.
   * @generated
   */
  PSQT_Model createPSQT_Model();

  /**
   * Returns a new object of class '<em>FDescription</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>FDescription</em>'.
   * @generated
   */
  FDescription createFDescription();

  /**
   * Returns a new object of class '<em>Table</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Table</em>'.
   * @generated
   */
  Table createTable();

  /**
   * Returns a new object of class '<em>TDescription</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>TDescription</em>'.
   * @generated
   */
  TDescription createTDescription();

  /**
   * Returns a new object of class '<em>Row</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>Row</em>'.
   * @generated
   */
  Row createRow();

  /**
   * Returns a new object of class '<em>MRow</em>'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return a new object of class '<em>MRow</em>'.
   * @generated
   */
  MRow createMRow();

  /**
   * Returns the package supported by this factory.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the package supported by this factory.
   * @generated
   */
  PSQTPackage getPSQTPackage();

} //PSQTFactory
