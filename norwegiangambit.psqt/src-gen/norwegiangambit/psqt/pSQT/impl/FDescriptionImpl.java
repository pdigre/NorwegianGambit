/**
 */
package norwegiangambit.psqt.pSQT.impl;

import java.util.Collection;

import norwegiangambit.psqt.pSQT.FDescription;
import norwegiangambit.psqt.pSQT.PSQTPackage;
import norwegiangambit.psqt.pSQT.Table;

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
 * An implementation of the model object '<em><b>FDescription</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.FDescriptionImpl#getTables <em>Tables</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.FDescriptionImpl#getName <em>Name</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.FDescriptionImpl#getBase <em>Base</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.FDescriptionImpl#getMgl <em>Mgl</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.FDescriptionImpl#getEgl <em>Egl</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class FDescriptionImpl extends PSQT_ModelImpl implements FDescription
{
  /**
   * The cached value of the '{@link #getTables() <em>Tables</em>}' containment reference list.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getTables()
   * @generated
   * @ordered
   */
  protected EList<Table> tables;

  /**
   * The default value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected static final String NAME_EDEFAULT = null;

  /**
   * The cached value of the '{@link #getName() <em>Name</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getName()
   * @generated
   * @ordered
   */
  protected String name = NAME_EDEFAULT;

  /**
   * The default value of the '{@link #getBase() <em>Base</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBase()
   * @generated
   * @ordered
   */
  protected static final int BASE_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getBase() <em>Base</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getBase()
   * @generated
   * @ordered
   */
  protected int base = BASE_EDEFAULT;

  /**
   * The default value of the '{@link #getMgl() <em>Mgl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMgl()
   * @generated
   * @ordered
   */
  protected static final int MGL_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getMgl() <em>Mgl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMgl()
   * @generated
   * @ordered
   */
  protected int mgl = MGL_EDEFAULT;

  /**
   * The default value of the '{@link #getEgl() <em>Egl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEgl()
   * @generated
   * @ordered
   */
  protected static final int EGL_EDEFAULT = 0;

  /**
   * The cached value of the '{@link #getEgl() <em>Egl</em>}' attribute.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEgl()
   * @generated
   * @ordered
   */
  protected int egl = EGL_EDEFAULT;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected FDescriptionImpl()
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
    return PSQTPackage.Literals.FDESCRIPTION;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public EList<Table> getTables()
  {
    if (tables == null)
    {
      tables = new EObjectContainmentEList<Table>(Table.class, this, PSQTPackage.FDESCRIPTION__TABLES);
    }
    return tables;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public String getName()
  {
    return name;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setName(String newName)
  {
    String oldName = name;
    name = newName;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.FDESCRIPTION__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getBase()
  {
    return base;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setBase(int newBase)
  {
    int oldBase = base;
    base = newBase;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.FDESCRIPTION__BASE, oldBase, base));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getMgl()
  {
    return mgl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMgl(int newMgl)
  {
    int oldMgl = mgl;
    mgl = newMgl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.FDESCRIPTION__MGL, oldMgl, mgl));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getEgl()
  {
    return egl;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEgl(int newEgl)
  {
    int oldEgl = egl;
    egl = newEgl;
    if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.FDESCRIPTION__EGL, oldEgl, egl));
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
      case PSQTPackage.FDESCRIPTION__TABLES:
        return ((InternalEList<?>)getTables()).basicRemove(otherEnd, msgs);
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
      case PSQTPackage.FDESCRIPTION__TABLES:
        return getTables();
      case PSQTPackage.FDESCRIPTION__NAME:
        return getName();
      case PSQTPackage.FDESCRIPTION__BASE:
        return getBase();
      case PSQTPackage.FDESCRIPTION__MGL:
        return getMgl();
      case PSQTPackage.FDESCRIPTION__EGL:
        return getEgl();
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
      case PSQTPackage.FDESCRIPTION__TABLES:
        getTables().clear();
        getTables().addAll((Collection<? extends Table>)newValue);
        return;
      case PSQTPackage.FDESCRIPTION__NAME:
        setName((String)newValue);
        return;
      case PSQTPackage.FDESCRIPTION__BASE:
        setBase((Integer)newValue);
        return;
      case PSQTPackage.FDESCRIPTION__MGL:
        setMgl((Integer)newValue);
        return;
      case PSQTPackage.FDESCRIPTION__EGL:
        setEgl((Integer)newValue);
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
      case PSQTPackage.FDESCRIPTION__TABLES:
        getTables().clear();
        return;
      case PSQTPackage.FDESCRIPTION__NAME:
        setName(NAME_EDEFAULT);
        return;
      case PSQTPackage.FDESCRIPTION__BASE:
        setBase(BASE_EDEFAULT);
        return;
      case PSQTPackage.FDESCRIPTION__MGL:
        setMgl(MGL_EDEFAULT);
        return;
      case PSQTPackage.FDESCRIPTION__EGL:
        setEgl(EGL_EDEFAULT);
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
      case PSQTPackage.FDESCRIPTION__TABLES:
        return tables != null && !tables.isEmpty();
      case PSQTPackage.FDESCRIPTION__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case PSQTPackage.FDESCRIPTION__BASE:
        return base != BASE_EDEFAULT;
      case PSQTPackage.FDESCRIPTION__MGL:
        return mgl != MGL_EDEFAULT;
      case PSQTPackage.FDESCRIPTION__EGL:
        return egl != EGL_EDEFAULT;
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
    result.append(", base: ");
    result.append(base);
    result.append(", mgl: ");
    result.append(mgl);
    result.append(", egl: ");
    result.append(egl);
    result.append(')');
    return result.toString();
  }

} //FDescriptionImpl
