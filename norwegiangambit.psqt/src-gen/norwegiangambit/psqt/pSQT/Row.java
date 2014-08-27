/**
 */
package norwegiangambit.psqt.pSQT;

import org.eclipse.emf.ecore.EObject;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link norwegiangambit.psqt.pSQT.Row#getName <em>Name</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.Row#getMidrow <em>Midrow</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.Row#getEndrow <em>Endrow</em>}</li>
 * </ul>
 * </p>
 *
 * @see norwegiangambit.psqt.pSQT.PSQTPackage#getRow()
 * @model
 * @generated
 */
public interface Row extends EObject
{
  /**
   * Returns the value of the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Name</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Name</em>' attribute.
   * @see #setName(String)
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getRow_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link norwegiangambit.psqt.pSQT.Row#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Midrow</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Midrow</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Midrow</em>' containment reference.
   * @see #setMidrow(MRow)
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getRow_Midrow()
   * @model containment="true"
   * @generated
   */
  MRow getMidrow();

  /**
   * Sets the value of the '{@link norwegiangambit.psqt.pSQT.Row#getMidrow <em>Midrow</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Midrow</em>' containment reference.
   * @see #getMidrow()
   * @generated
   */
  void setMidrow(MRow value);

  /**
   * Returns the value of the '<em><b>Endrow</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Endrow</em>' containment reference isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Endrow</em>' containment reference.
   * @see #setEndrow(MRow)
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getRow_Endrow()
   * @model containment="true"
   * @generated
   */
  MRow getEndrow();

  /**
   * Sets the value of the '{@link norwegiangambit.psqt.pSQT.Row#getEndrow <em>Endrow</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Endrow</em>' containment reference.
   * @see #getEndrow()
   * @generated
   */
  void setEndrow(MRow value);

} // Row
