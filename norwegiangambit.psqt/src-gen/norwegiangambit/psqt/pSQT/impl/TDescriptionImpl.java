/**
 */
package norwegiangambit.psqt.pSQT.impl;

import java.util.Collection;

import norwegiangambit.psqt.pSQT.PSQTPackage;
import norwegiangambit.psqt.pSQT.PieceType;
import norwegiangambit.psqt.pSQT.Row;
import norwegiangambit.psqt.pSQT.TDescription;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.common.util.EList;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;

import org.eclipse.emf.ecore.util.EObjectContainmentEList;
import org.eclipse.emf.ecore.util.InternalEList;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>TDescription</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.TDescriptionImpl#getR <em>R</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.TDescriptionImpl#getName <em>Name</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.TDescriptionImpl#getMg <em>Mg</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.TDescriptionImpl#getEg <em>Eg</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class TDescriptionImpl extends TableImpl implements TDescription
{
  /**
   * The cached value of the '{@link #getR() <em>R</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getR()
   * @generated
   * @ordered
   */
  protected EList<Row> r;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final PieceType NAME_EDEFAULT = PieceType.PAWN;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected PieceType name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getMg() <em>Mg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMg()
   * @generated
   * @ordered
   */
  protected static final int MG_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getMg() <em>Mg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMg()
   * @generated
   * @ordered
   */
  protected int mg = MG_EDEFAULT;

  /**
   * The default value of the '{@link #getEg() <em>Eg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEg()
   * @generated
   * @ordered
   */
  protected static final int EG_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEg() <em>Eg</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEg()
   * @generated
   * @ordered
   */
  protected int eg = EG_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected TDescriptionImpl()
  {
    super();
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  protected EClass eStaticClass()
  {
    return PSQTPackage.Literals.TDESCRIPTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Row> getR()
  {
    if (r == null)
    {
      r = new EObjectContainmentEList<Row>(Row.class, this, PSQTPackage.TDESCRIPTION__R);
    }
    return r;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public PieceType getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(PieceType newName)
  {
    PieceType oldName = name;
    name = newName == null ? NAME_EDEFAULT : newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.TDESCRIPTION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getMg()
  {
    return mg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMg(int newMg)
  {
    int oldMg = mg;
    mg = newMg;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.TDESCRIPTION__MG, oldMg, mg));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getEg()
  {
    return eg;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEg(int newEg)
  {
    int oldEg = eg;
    eg = newEg;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.TDESCRIPTION__EG, oldEg, eg));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public NotificationChain eInverseRemove(InternalEObject otherEnd, int featureID, NotificationChain msgs)
  {
    switch (featureID)
    {
      case PSQTPackage.TDESCRIPTION__R:
        return ((InternalEList<?>)getR()).basicRemove(otherEnd, msgs);
    }
    return super.eInverseRemove(otherEnd, featureID, msgs);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public Object eGet(int featureID, boolean resolve, boolean coreType)
  {
    switch (featureID)
    {
      case PSQTPackage.TDESCRIPTION__R:
        return getR();
      case PSQTPackage.TDESCRIPTION__NAME:
        return getName();
      case PSQTPackage.TDESCRIPTION__MG:
        return getMg();
      case PSQTPackage.TDESCRIPTION__EG:
        return getEg();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @SuppressWarnings("unchecked")
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PSQTPackage.TDESCRIPTION__R:
        getR().clear();
        getR().addAll((Collection<? extends Row>)newValue);
        return;
      case PSQTPackage.TDESCRIPTION__NAME:
        setName((PieceType)newValue);
        return;
      case PSQTPackage.TDESCRIPTION__MG:
        setMg((Integer)newValue);
        return;
      case PSQTPackage.TDESCRIPTION__EG:
        setEg((Integer)newValue);
        return;
    }
    super.eSet(featureID, newValue);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eUnset(int featureID)
  {
    switch (featureID)
    {
      case PSQTPackage.TDESCRIPTION__R:
        getR().clear();
        return;
      case PSQTPackage.TDESCRIPTION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case PSQTPackage.TDESCRIPTION__MG:
        setMg(MG_EDEFAULT);
        return;
      case PSQTPackage.TDESCRIPTION__EG:
        setEg(EG_EDEFAULT);
        return;
    }
    super.eUnset(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public boolean eIsSet(int featureID)
  {
    switch (featureID)
    {
      case PSQTPackage.TDESCRIPTION__R:
        return r != null && !r.isEmpty();
      case PSQTPackage.TDESCRIPTION__NAME:
        return name != NAME_EDEFAULT;
      case PSQTPackage.TDESCRIPTION__MG:
        return mg != MG_EDEFAULT;
      case PSQTPackage.TDESCRIPTION__EG:
        return eg != EG_EDEFAULT;
    }
    return super.eIsSet(featureID);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    if (eIsProxy()) return super.toString();

    StringBuffer result = new StringBuffer(super.toString());
    result.append(" (name: ");
    result.append(name);
    result.append(", mg: ");
    result.append(mg);
    result.append(", eg: ");
    result.append(eg);
    result.append(')');
    return result.toString();
  }

} //TDescriptionImpl
