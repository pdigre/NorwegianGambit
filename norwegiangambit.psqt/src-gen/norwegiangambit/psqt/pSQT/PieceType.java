/**
 */
package norwegiangambit.psqt.pSQT;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.eclipse.emf.common.util.Enumerator;

/**
 * <!-- begin-user-doc -->
 * A representation of the literals of the enumeration '<em><b>Piece Type</b></em>',
 * and utility methods for working with them.
 * <!-- end-user-doc -->
 * @see norwegiangambit.psqt.pSQT.PSQTPackage#getPieceType()
 * @model
 * @generated
 */
public enum PieceType implements Enumerator
{
  /**
   * The '<em><b>PAWN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #PAWN_VALUE
   * @generated
   * @ordered
   */
  PAWN(0, "PAWN", "Pawn"),

  /**
   * The '<em><b>KNIGHT</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #KNIGHT_VALUE
   * @generated
   * @ordered
   */
  KNIGHT(1, "KNIGHT", "Knight"),

  /**
   * The '<em><b>BISHOP</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #BISHOP_VALUE
   * @generated
   * @ordered
   */
  BISHOP(2, "BISHOP", "Bishop"),

  /**
   * The '<em><b>ROOK</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #ROOK_VALUE
   * @generated
   * @ordered
   */
  ROOK(3, "ROOK", "Rook"),

  /**
   * The '<em><b>QUEEN</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #QUEEN_VALUE
   * @generated
   * @ordered
   */
  QUEEN(4, "QUEEN", "Queen"),

  /**
   * The '<em><b>KING</b></em>' literal object.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @see #KING_VALUE
   * @generated
   * @ordered
   */
  KING(5, "KING", "King");

  /**
   * The '<em><b>PAWN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>PAWN</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #PAWN
   * @model literal="Pawn"
   * @generated
   * @ordered
   */
  public static final int PAWN_VALUE = 0;

  /**
   * The '<em><b>KNIGHT</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>KNIGHT</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #KNIGHT
   * @model literal="Knight"
   * @generated
   * @ordered
   */
  public static final int KNIGHT_VALUE = 1;

  /**
   * The '<em><b>BISHOP</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>BISHOP</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #BISHOP
   * @model literal="Bishop"
   * @generated
   * @ordered
   */
  public static final int BISHOP_VALUE = 2;

  /**
   * The '<em><b>ROOK</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>ROOK</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #ROOK
   * @model literal="Rook"
   * @generated
   * @ordered
   */
  public static final int ROOK_VALUE = 3;

  /**
   * The '<em><b>QUEEN</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>QUEEN</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #QUEEN
   * @model literal="Queen"
   * @generated
   * @ordered
   */
  public static final int QUEEN_VALUE = 4;

  /**
   * The '<em><b>KING</b></em>' literal value.
   * <!-- begin-user-doc -->
   * <p>
   * If the meaning of '<em><b>KING</b></em>' literal object isn't clear,
   * there really should be more of a description here...
   * </p>
   * <!-- end-user-doc -->
   * @see #KING
   * @model literal="King"
   * @generated
   * @ordered
   */
  public static final int KING_VALUE = 5;

  /**
   * An array of all the '<em><b>Piece Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private static final PieceType[] VALUES_ARRAY =
    new PieceType[]
    {
      PAWN,
      KNIGHT,
      BISHOP,
      ROOK,
      QUEEN,
      KING,
    };

  /**
   * A public read-only list of all the '<em><b>Piece Type</b></em>' enumerators.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static final List<PieceType> VALUES = Collections.unmodifiableList(Arrays.asList(VALUES_ARRAY));

  /**
   * Returns the '<em><b>Piece Type</b></em>' literal with the specified literal value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PieceType get(String literal)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      PieceType result = VALUES_ARRAY[i];
      if (result.toString().equals(literal))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Piece Type</b></em>' literal with the specified name.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PieceType getByName(String name)
  {
    for (int i = 0; i < VALUES_ARRAY.length; ++i)
    {
      PieceType result = VALUES_ARRAY[i];
      if (result.getName().equals(name))
      {
        return result;
      }
    }
    return null;
  }

  /**
   * Returns the '<em><b>Piece Type</b></em>' literal with the specified integer value.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public static PieceType get(int value)
  {
    switch (value)
    {
      case PAWN_VALUE: return PAWN;
      case KNIGHT_VALUE: return KNIGHT;
      case BISHOP_VALUE: return BISHOP;
      case ROOK_VALUE: return ROOK;
      case QUEEN_VALUE: return QUEEN;
      case KING_VALUE: return KING;
    }
    return null;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final int value;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String name;

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private final String literal;

  /**
   * Only this class can construct instances.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  private PieceType(int value, String name, String literal)
  {
    this.value = value;
    this.name = name;
    this.literal = literal;
  }

  /**
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  public int getValue()
  {
    return value;
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
  public String getLiteral()
  {
    return literal;
  }

  /**
   * Returns the literal value of the enumerator, which is its string representation.
   * <!-- begin-user-doc -->
   * <!-- end-user-doc -->
   * @generated
   */
  @Override
  public String toString()
  {
    return literal;
  }
  
} //PieceType
