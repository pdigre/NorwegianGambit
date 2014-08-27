/**
 */
package norwegiangambit.psqt.pSQT;

import org.eclipse.emf.ecore.EAttribute;
import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.EEnum;
import org.eclipse.emf.ecore.EPackage;
import org.eclipse.emf.ecore.EReference;

/**
 * <!-- begin-user-doc -->
 * The <b>Package</b> for the model.
 * It contains accessors for the meta objects to represent
 * <ul>
 *   <li>each class,</li>
 *   <li>each feature of each class,</li>
 *   <li>each enum,</li>
 *   <li>and each data type</li>
 * </ul>
 * <!-- end-user-doc -->
 * @see norwegiangambit.psqt.pSQT.PSQTFactory
 * @model kind="package"
 * @generated
 */
public interface PSQTPackage extends EPackage
{
  /**
   * The package name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNAME = "pSQT";

  /**
   * The package namespace URI.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_URI = "http://www.norwegiangambit.com/PSQT";

  /**
   * The package namespace name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  String eNS_PREFIX = "pSQT";

  /**
   * The singleton instance of the package.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  PSQTPackage eINSTANCE = norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl.init();

  /**
   * The meta object id for the '{@link norwegiangambit.psqt.pSQT.impl.PSQT_ModelImpl <em>PSQT Model</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see norwegiangambit.psqt.pSQT.impl.PSQT_ModelImpl
   * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getPSQT_Model()
   * @generated
   */
  int PSQT_MODEL = 0;

  /**
   * The number of structural features of the '<em>PSQT Model</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int PSQT_MODEL_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link norwegiangambit.psqt.pSQT.impl.FDescriptionImpl <em>FDescription</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see norwegiangambit.psqt.pSQT.impl.FDescriptionImpl
   * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getFDescription()
   * @generated
   */
  int FDESCRIPTION = 1;

  /**
   * The feature id for the '<em><b>Tables</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FDESCRIPTION__TABLES = PSQT_MODEL_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FDESCRIPTION__NAME = PSQT_MODEL_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Base</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FDESCRIPTION__BASE = PSQT_MODEL_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Mgl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FDESCRIPTION__MGL = PSQT_MODEL_FEATURE_COUNT + 3;

  /**
   * The feature id for the '<em><b>Egl</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FDESCRIPTION__EGL = PSQT_MODEL_FEATURE_COUNT + 4;

  /**
   * The number of structural features of the '<em>FDescription</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int FDESCRIPTION_FEATURE_COUNT = PSQT_MODEL_FEATURE_COUNT + 5;

  /**
   * The meta object id for the '{@link norwegiangambit.psqt.pSQT.impl.TableImpl <em>Table</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see norwegiangambit.psqt.pSQT.impl.TableImpl
   * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getTable()
   * @generated
   */
  int TABLE = 2;

  /**
   * The number of structural features of the '<em>Table</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TABLE_FEATURE_COUNT = 0;

  /**
   * The meta object id for the '{@link norwegiangambit.psqt.pSQT.impl.TDescriptionImpl <em>TDescription</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see norwegiangambit.psqt.pSQT.impl.TDescriptionImpl
   * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getTDescription()
   * @generated
   */
  int TDESCRIPTION = 3;

  /**
   * The feature id for the '<em><b>R</b></em>' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TDESCRIPTION__R = TABLE_FEATURE_COUNT + 0;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TDESCRIPTION__NAME = TABLE_FEATURE_COUNT + 1;

  /**
   * The feature id for the '<em><b>Mg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TDESCRIPTION__MG = TABLE_FEATURE_COUNT + 2;

  /**
   * The feature id for the '<em><b>Eg</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TDESCRIPTION__EG = TABLE_FEATURE_COUNT + 3;

  /**
   * The number of structural features of the '<em>TDescription</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int TDESCRIPTION_FEATURE_COUNT = TABLE_FEATURE_COUNT + 4;

  /**
   * The meta object id for the '{@link norwegiangambit.psqt.pSQT.impl.RowImpl <em>Row</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see norwegiangambit.psqt.pSQT.impl.RowImpl
   * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getRow()
   * @generated
   */
  int ROW = 4;

  /**
   * The feature id for the '<em><b>Name</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROW__NAME = 0;

  /**
   * The feature id for the '<em><b>Midrow</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROW__MIDROW = 1;

  /**
   * The feature id for the '<em><b>Endrow</b></em>' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROW__ENDROW = 2;

  /**
   * The number of structural features of the '<em>Row</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int ROW_FEATURE_COUNT = 3;

  /**
   * The meta object id for the '{@link norwegiangambit.psqt.pSQT.impl.MRowImpl <em>MRow</em>}' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see norwegiangambit.psqt.pSQT.impl.MRowImpl
   * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getMRow()
   * @generated
   */
  int MROW = 5;

  /**
   * The feature id for the '<em><b>C1</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C1 = 0;

  /**
   * The feature id for the '<em><b>C2</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C2 = 1;

  /**
   * The feature id for the '<em><b>C3</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C3 = 2;

  /**
   * The feature id for the '<em><b>C4</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C4 = 3;

  /**
   * The feature id for the '<em><b>C5</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C5 = 4;

  /**
   * The feature id for the '<em><b>C6</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C6 = 5;

  /**
   * The feature id for the '<em><b>C7</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C7 = 6;

  /**
   * The feature id for the '<em><b>C8</b></em>' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW__C8 = 7;

  /**
   * The number of structural features of the '<em>MRow</em>' class.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   * @ordered
   */
  int MROW_FEATURE_COUNT = 8;

  /**
   * The meta object id for the '{@link norwegiangambit.psqt.pSQT.PieceType <em>Piece Type</em>}' enum.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see norwegiangambit.psqt.pSQT.PieceType
   * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getPieceType()
   * @generated
   */
  int PIECE_TYPE = 6;


  /**
   * Returns the meta object for class '{@link norwegiangambit.psqt.pSQT.PSQT_Model <em>PSQT Model</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>PSQT Model</em>'.
   * @see norwegiangambit.psqt.pSQT.PSQT_Model
   * @generated
   */
  EClass getPSQT_Model();

  /**
   * Returns the meta object for class '{@link norwegiangambit.psqt.pSQT.FDescription <em>FDescription</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>FDescription</em>'.
   * @see norwegiangambit.psqt.pSQT.FDescription
   * @generated
   */
  EClass getFDescription();

  /**
   * Returns the meta object for the containment reference list '{@link norwegiangambit.psqt.pSQT.FDescription#getTables <em>Tables</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>Tables</em>'.
   * @see norwegiangambit.psqt.pSQT.FDescription#getTables()
   * @see #getFDescription()
   * @generated
   */
  EReference getFDescription_Tables();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.FDescription#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see norwegiangambit.psqt.pSQT.FDescription#getName()
   * @see #getFDescription()
   * @generated
   */
  EAttribute getFDescription_Name();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.FDescription#getBase <em>Base</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Base</em>'.
   * @see norwegiangambit.psqt.pSQT.FDescription#getBase()
   * @see #getFDescription()
   * @generated
   */
  EAttribute getFDescription_Base();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.FDescription#getMgl <em>Mgl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mgl</em>'.
   * @see norwegiangambit.psqt.pSQT.FDescription#getMgl()
   * @see #getFDescription()
   * @generated
   */
  EAttribute getFDescription_Mgl();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.FDescription#getEgl <em>Egl</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Egl</em>'.
   * @see norwegiangambit.psqt.pSQT.FDescription#getEgl()
   * @see #getFDescription()
   * @generated
   */
  EAttribute getFDescription_Egl();

  /**
   * Returns the meta object for class '{@link norwegiangambit.psqt.pSQT.Table <em>Table</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Table</em>'.
   * @see norwegiangambit.psqt.pSQT.Table
   * @generated
   */
  EClass getTable();

  /**
   * Returns the meta object for class '{@link norwegiangambit.psqt.pSQT.TDescription <em>TDescription</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>TDescription</em>'.
   * @see norwegiangambit.psqt.pSQT.TDescription
   * @generated
   */
  EClass getTDescription();

  /**
   * Returns the meta object for the containment reference list '{@link norwegiangambit.psqt.pSQT.TDescription#getR <em>R</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference list '<em>R</em>'.
   * @see norwegiangambit.psqt.pSQT.TDescription#getR()
   * @see #getTDescription()
   * @generated
   */
  EReference getTDescription_R();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.TDescription#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see norwegiangambit.psqt.pSQT.TDescription#getName()
   * @see #getTDescription()
   * @generated
   */
  EAttribute getTDescription_Name();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.TDescription#getMg <em>Mg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Mg</em>'.
   * @see norwegiangambit.psqt.pSQT.TDescription#getMg()
   * @see #getTDescription()
   * @generated
   */
  EAttribute getTDescription_Mg();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.TDescription#getEg <em>Eg</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Eg</em>'.
   * @see norwegiangambit.psqt.pSQT.TDescription#getEg()
   * @see #getTDescription()
   * @generated
   */
  EAttribute getTDescription_Eg();

  /**
   * Returns the meta object for class '{@link norwegiangambit.psqt.pSQT.Row <em>Row</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>Row</em>'.
   * @see norwegiangambit.psqt.pSQT.Row
   * @generated
   */
  EClass getRow();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.Row#getName <em>Name</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>Name</em>'.
   * @see norwegiangambit.psqt.pSQT.Row#getName()
   * @see #getRow()
   * @generated
   */
  EAttribute getRow_Name();

  /**
   * Returns the meta object for the containment reference '{@link norwegiangambit.psqt.pSQT.Row#getMidrow <em>Midrow</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Midrow</em>'.
   * @see norwegiangambit.psqt.pSQT.Row#getMidrow()
   * @see #getRow()
   * @generated
   */
  EReference getRow_Midrow();

  /**
   * Returns the meta object for the containment reference '{@link norwegiangambit.psqt.pSQT.Row#getEndrow <em>Endrow</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the containment reference '<em>Endrow</em>'.
   * @see norwegiangambit.psqt.pSQT.Row#getEndrow()
   * @see #getRow()
   * @generated
   */
  EReference getRow_Endrow();

  /**
   * Returns the meta object for class '{@link norwegiangambit.psqt.pSQT.MRow <em>MRow</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for class '<em>MRow</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow
   * @generated
   */
  EClass getMRow();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC1 <em>C1</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C1</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC1()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C1();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC2 <em>C2</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C2</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC2()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C2();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC3 <em>C3</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C3</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC3()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C3();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC4 <em>C4</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C4</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC4()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C4();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC5 <em>C5</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C5</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC5()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C5();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC6 <em>C6</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C6</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC6()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C6();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC7 <em>C7</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C7</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC7()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C7();

  /**
   * Returns the meta object for the attribute '{@link norwegiangambit.psqt.pSQT.MRow#getC8 <em>C8</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for the attribute '<em>C8</em>'.
   * @see norwegiangambit.psqt.pSQT.MRow#getC8()
   * @see #getMRow()
   * @generated
   */
  EAttribute getMRow_C8();

  /**
   * Returns the meta object for enum '{@link norwegiangambit.psqt.pSQT.PieceType <em>Piece Type</em>}'.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the meta object for enum '<em>Piece Type</em>'.
   * @see norwegiangambit.psqt.pSQT.PieceType
   * @generated
   */
  EEnum getPieceType();

  /**
   * Returns the factory that creates the instances of the model.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @return the factory that creates the instances of the model.
   * @generated
   */
  PSQTFactory getPSQTFactory();

  /**
   * <!-- begin-user-doc -->
   * Defines literals for the meta objects that represent
   * <ul>
   *   <li>each class,</li>
   *   <li>each feature of each class,</li>
   *   <li>each enum,</li>
   *   <li>and each data type</li>
   * </ul>
   * <!-- end-user-doc -->
   * @generated
   */
  interface Literals
  {
    /**
     * The meta object literal for the '{@link norwegiangambit.psqt.pSQT.impl.PSQT_ModelImpl <em>PSQT Model</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see norwegiangambit.psqt.pSQT.impl.PSQT_ModelImpl
     * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getPSQT_Model()
     * @generated
     */
    EClass PSQT_MODEL = eINSTANCE.getPSQT_Model();

    /**
     * The meta object literal for the '{@link norwegiangambit.psqt.pSQT.impl.FDescriptionImpl <em>FDescription</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see norwegiangambit.psqt.pSQT.impl.FDescriptionImpl
     * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getFDescription()
     * @generated
     */
    EClass FDESCRIPTION = eINSTANCE.getFDescription();

    /**
     * The meta object literal for the '<em><b>Tables</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference FDESCRIPTION__TABLES = eINSTANCE.getFDescription_Tables();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FDESCRIPTION__NAME = eINSTANCE.getFDescription_Name();

    /**
     * The meta object literal for the '<em><b>Base</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FDESCRIPTION__BASE = eINSTANCE.getFDescription_Base();

    /**
     * The meta object literal for the '<em><b>Mgl</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FDESCRIPTION__MGL = eINSTANCE.getFDescription_Mgl();

    /**
     * The meta object literal for the '<em><b>Egl</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute FDESCRIPTION__EGL = eINSTANCE.getFDescription_Egl();

    /**
     * The meta object literal for the '{@link norwegiangambit.psqt.pSQT.impl.TableImpl <em>Table</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see norwegiangambit.psqt.pSQT.impl.TableImpl
     * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getTable()
     * @generated
     */
    EClass TABLE = eINSTANCE.getTable();

    /**
     * The meta object literal for the '{@link norwegiangambit.psqt.pSQT.impl.TDescriptionImpl <em>TDescription</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see norwegiangambit.psqt.pSQT.impl.TDescriptionImpl
     * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getTDescription()
     * @generated
     */
    EClass TDESCRIPTION = eINSTANCE.getTDescription();

    /**
     * The meta object literal for the '<em><b>R</b></em>' containment reference list feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference TDESCRIPTION__R = eINSTANCE.getTDescription_R();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TDESCRIPTION__NAME = eINSTANCE.getTDescription_Name();

    /**
     * The meta object literal for the '<em><b>Mg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TDESCRIPTION__MG = eINSTANCE.getTDescription_Mg();

    /**
     * The meta object literal for the '<em><b>Eg</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute TDESCRIPTION__EG = eINSTANCE.getTDescription_Eg();

    /**
     * The meta object literal for the '{@link norwegiangambit.psqt.pSQT.impl.RowImpl <em>Row</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see norwegiangambit.psqt.pSQT.impl.RowImpl
     * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getRow()
     * @generated
     */
    EClass ROW = eINSTANCE.getRow();

    /**
     * The meta object literal for the '<em><b>Name</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute ROW__NAME = eINSTANCE.getRow_Name();

    /**
     * The meta object literal for the '<em><b>Midrow</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ROW__MIDROW = eINSTANCE.getRow_Midrow();

    /**
     * The meta object literal for the '<em><b>Endrow</b></em>' containment reference feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EReference ROW__ENDROW = eINSTANCE.getRow_Endrow();

    /**
     * The meta object literal for the '{@link norwegiangambit.psqt.pSQT.impl.MRowImpl <em>MRow</em>}' class.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see norwegiangambit.psqt.pSQT.impl.MRowImpl
     * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getMRow()
     * @generated
     */
    EClass MROW = eINSTANCE.getMRow();

    /**
     * The meta object literal for the '<em><b>C1</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C1 = eINSTANCE.getMRow_C1();

    /**
     * The meta object literal for the '<em><b>C2</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C2 = eINSTANCE.getMRow_C2();

    /**
     * The meta object literal for the '<em><b>C3</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C3 = eINSTANCE.getMRow_C3();

    /**
     * The meta object literal for the '<em><b>C4</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C4 = eINSTANCE.getMRow_C4();

    /**
     * The meta object literal for the '<em><b>C5</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C5 = eINSTANCE.getMRow_C5();

    /**
     * The meta object literal for the '<em><b>C6</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C6 = eINSTANCE.getMRow_C6();

    /**
     * The meta object literal for the '<em><b>C7</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C7 = eINSTANCE.getMRow_C7();

    /**
     * The meta object literal for the '<em><b>C8</b></em>' attribute feature.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @generated
     */
    EAttribute MROW__C8 = eINSTANCE.getMRow_C8();

    /**
     * The meta object literal for the '{@link norwegiangambit.psqt.pSQT.PieceType <em>Piece Type</em>}' enum.
     * <!-- begin-user-doc -->
     * <!-- end-user-doc -->
     * @see norwegiangambit.psqt.pSQT.PieceType
     * @see norwegiangambit.psqt.pSQT.impl.PSQTPackageImpl#getPieceType()
     * @generated
     */
    EEnum PIECE_TYPE = eINSTANCE.getPieceType();

  }

} //PSQTPackage
