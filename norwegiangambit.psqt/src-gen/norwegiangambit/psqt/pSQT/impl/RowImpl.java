/**
 */
package norwegiangambit.psqt.pSQT.impl;

import norwegiangambit.psqt.pSQT.MRow;
import norwegiangambit.psqt.pSQT.PSQTPackage;
import norwegiangambit.psqt.pSQT.Row;

import org.eclipse.emf.common.notify.Notification;
import org.eclipse.emf.common.notify.NotificationChain;

import org.eclipse.emf.ecore.EClass;
import org.eclipse.emf.ecore.InternalEObject;

import org.eclipse.emf.ecore.impl.ENotificationImpl;
import org.eclipse.emf.ecore.impl.MinimalEObjectImpl;

/**
 * <!-- begin-user-doc -->
 * An implementation of the model object '<em><b>Row</b></em>'.
 * <!-- end-user-doc -->
 * <p>
 * The following features are implemented:
 * <ul>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.RowImpl#getName <em>Name</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.RowImpl#getMidrow <em>Midrow</em>}</li>
 *   <li>{@link norwegiangambit.psqt.pSQT.impl.RowImpl#getEndrow <em>Endrow</em>}</li>
 * </ul>
 * </p>
 *
 * @generated
 */
public class RowImpl extends MinimalEObjectImpl.Container implements Row
{
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
   * The cached value of the '{@link #getMidrow() <em>Midrow</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getMidrow()
   * @generated
   * @ordered
   */
  protected MRow midrow;

  /**
   * The cached value of the '{@link #getEndrow() <em>Endrow</em>}' containment reference.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #getEndrow()
   * @generated
   * @ordered
   */
  protected MRow endrow;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  protected RowImpl()
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
    return PSQTPackage.Literals.ROW;
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
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.ROW__NAME, oldName, name));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MRow getMidrow()
  {
    return midrow;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetMidrow(MRow newMidrow, NotificationChain msgs)
  {
    MRow oldMidrow = midrow;
    midrow = newMidrow;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PSQTPackage.ROW__MIDROW, oldMidrow, newMidrow);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setMidrow(MRow newMidrow)
  {
    if (newMidrow != midrow)
    {
      NotificationChain msgs = null;
      if (midrow != null)
        msgs = ((InternalEObject)midrow).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PSQTPackage.ROW__MIDROW, null, msgs);
      if (newMidrow != null)
        msgs = ((InternalEObject)newMidrow).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PSQTPackage.ROW__MIDROW, null, msgs);
      msgs = basicSetMidrow(newMidrow, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.ROW__MIDROW, newMidrow, newMidrow));
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public MRow getEndrow()
  {
    return endrow;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public NotificationChain basicSetEndrow(MRow newEndrow, NotificationChain msgs)
  {
    MRow oldEndrow = endrow;
    endrow = newEndrow;
    if (eNotificationRequired())
    {
      ENotificationImpl notification = new ENotificationImpl(this, Notification.SET, PSQTPackage.ROW__ENDROW, oldEndrow, newEndrow);
      if (msgs == null) msgs = notification; else msgs.add(notification);
    }
    return msgs;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public void setEndrow(MRow newEndrow)
  {
    if (newEndrow != endrow)
    {
      NotificationChain msgs = null;
      if (endrow != null)
        msgs = ((InternalEObject)endrow).eInverseRemove(this, EOPPOSITE_FEATURE_BASE - PSQTPackage.ROW__ENDROW, null, msgs);
      if (newEndrow != null)
        msgs = ((InternalEObject)newEndrow).eInverseAdd(this, EOPPOSITE_FEATURE_BASE - PSQTPackage.ROW__ENDROW, null, msgs);
      msgs = basicSetEndrow(newEndrow, msgs);
      if (msgs != null) msgs.dispatch();
    }
    else if (eNotificationRequired())
      eNotify(new ENotificationImpl(this, Notification.SET, PSQTPackage.ROW__ENDROW, newEndrow, newEndrow));
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
      case PSQTPackage.ROW__MIDROW:
        return basicSetMidrow(null, msgs);
      case PSQTPackage.ROW__ENDROW:
        return basicSetEndrow(null, msgs);
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
      case PSQTPackage.ROW__NAME:
        return getName();
      case PSQTPackage.ROW__MIDROW:
        return getMidrow();
      case PSQTPackage.ROW__ENDROW:
        return getEndrow();
    }
    return super.eGet(featureID, resolve, coreType);
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public void eSet(int featureID, Object newValue)
  {
    switch (featureID)
    {
      case PSQTPackage.ROW__NAME:
        setName((String)newValue);
        return;
      case PSQTPackage.ROW__MIDROW:
        setMidrow((MRow)newValue);
        return;
      case PSQTPackage.ROW__ENDROW:
        setEndrow((MRow)newValue);
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
      case PSQTPackage.ROW__NAME:
        setName(NAME_EDEFAULT);
        return;
      case PSQTPackage.ROW__MIDROW:
        setMidrow((MRow)null);
        return;
      case PSQTPackage.ROW__ENDROW:
        setEndrow((MRow)null);
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
      case PSQTPackage.ROW__NAME:
        return NAME_EDEFAULT == null ? name != null : !NAME_EDEFAULT.equals(name);
      case PSQTPackage.ROW__MIDROW:
        return midrow != null;
      case PSQTPackage.ROW__ENDROW:
        return endrow != null;
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
    result.append(')');
    return result.toString();
  }

} //RowImpl
