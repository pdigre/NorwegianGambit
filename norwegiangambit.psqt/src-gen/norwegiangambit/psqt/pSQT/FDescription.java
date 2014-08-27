/**
 */
package norwegiangambit.psqt.pSQT;

import org.eclipse.emf.common.util.EList;

/**
 * <!-- begin-user-doc -->
 * A representation of the model object '<em><b>FDescription</b></em>'.
 * <!-- end-user-doc -->
 *
 * <p>
 * The following features are supported:
 * <ul>
 *   <li>{@link norwegiangambit.psqt.pSQT.FDescription#getTables <em>Tables</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.FDescription#getName <em>Name</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.FDescription#getBase <em>Base</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.FDescription#getMgl <em>Mgl</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.FDescription#getEgl <em>Egl</em>}</li>
 * </ul>
 * </p>
 *
 * @see norwegiangambit.psqt.pSQT.PSQTPackage#getFDescription()
 * @model
 * @generated
 */
public interface FDescription extends PSQT_Model
{
  /**
   * Returns the value of the '<em><b>Tables</b></em>' containment reference list.
   * The list contents are of type {@link norwegiangambit.psqt.pSQT.Table}.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Tables</em>' containment reference list isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Tables</em>' containment reference list.
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getFDescription_Tables()
   * @model containment="true"
   * @generated
   */
  EList<Table> getTables();

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
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getFDescription_Name()
   * @model
   * @generated
   */
  String getName();

  /**
   * Sets the value of the '{@link norwegiangambit.psqt.pSQT.FDescription#getName <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Name</em>' attribute.
   * @see #getName()
   * @generated
   */
  void setName(String value);

  /**
   * Returns the value of the '<em><b>Base</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Base</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Base</em>' attribute.
   * @see #setBase(int)
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getFDescription_Base()
   * @model
   * @generated
   */
  int getBase();

  /**
   * Sets the value of the '{@link norwegiangambit.psqt.pSQT.FDescription#getBase <em>Base</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Base</em>' attribute.
   * @see #getBase()
   * @generated
   */
  void setBase(int value);

  /**
   * Returns the value of the '<em><b>Mgl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Mgl</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Mgl</em>' attribute.
   * @see #setMgl(int)
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getFDescription_Mgl()
   * @model
   * @generated
   */
  int getMgl();

  /**
   * Sets the value of the '{@link norwegiangambit.psqt.pSQT.FDescription#getMgl <em>Mgl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Mgl</em>' attribute.
   * @see #getMgl()
   * @generated
   */
  void setMgl(int value);

  /**
   * Returns the value of the '<em><b>Egl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of the '<em>Egl</em>' attribute isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @return the value of the '<em>Egl</em>' attribute.
   * @see #setEgl(int)
   * @see norwegiangambit.psqt.pSQT.PSQTPackage#getFDescription_Egl()
   * @model
   * @generated
   */
  int getEgl();

  /**
   * Sets the value of the '{@link norwegiangambit.psqt.pSQT.FDescription#getEgl <em>Egl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @param value the new value of the '<em>Egl</em>' attribute.
   * @see #getEgl()
   * @generated
   */
  void setEgl(int value);

} // FDescription
