/*
* generated by Xtext
*/
package norwegiangambit.psqt.services;

import com.google.inject.Singleton;
import com.google.inject.Inject;

import java.util.List;

import org.eclipse.xtext.*;
import org.eclipse.xtext.service.GrammarProvider;
import org.eclipse.xtext.service.AbstractElementFinder.*;

import org.eclipse.xtext.common.services.TerminalsGrammarAccess;

@Singleton
public class PSQTGrammarAccess extends AbstractGrammarElementFinder {
	
	
	public class PSQT_ModelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "PSQT_Model");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cFDescriptionParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Assignment cTablesAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cTablesTableParserRuleCall_1_0 = (RuleCall)cTablesAssignment_1.eContents().get(0);
		
		//PSQT_Model:
		//	FDescription tables+=Table*;
		public ParserRule getRule() { return rule; }

		//FDescription tables+=Table*
		public Group getGroup() { return cGroup; }

		//FDescription
		public RuleCall getFDescriptionParserRuleCall_0() { return cFDescriptionParserRuleCall_0; }

		//tables+=Table*
		public Assignment getTablesAssignment_1() { return cTablesAssignment_1; }

		//Table
		public RuleCall getTablesTableParserRuleCall_1_0() { return cTablesTableParserRuleCall_1_0; }
	}

	public class FDescriptionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "FDescription");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPSQTTerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNameIDTerminalRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final RuleCall cBASETerminalRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		private final Assignment cBaseAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cBaseINTTerminalRuleCall_3_0 = (RuleCall)cBaseAssignment_3.eContents().get(0);
		private final RuleCall cMIDLIMITTerminalRuleCall_4 = (RuleCall)cGroup.eContents().get(4);
		private final Assignment cMglAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cMglINTTerminalRuleCall_5_0 = (RuleCall)cMglAssignment_5.eContents().get(0);
		private final RuleCall cENDLIMITTerminalRuleCall_6 = (RuleCall)cGroup.eContents().get(6);
		private final Assignment cEglAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cEglINTTerminalRuleCall_7_0 = (RuleCall)cEglAssignment_7.eContents().get(0);
		
		////enum RowNumber : R1='1'|R2='2'|R3='3'|R4='4'|R5='5'|R6='6'|R7='7'|R8='8';
		//FDescription:
		//	PSQT name=ID BASE base=INT MIDLIMIT mgl=INT ENDLIMIT egl=INT;
		public ParserRule getRule() { return rule; }

		//PSQT name=ID BASE base=INT MIDLIMIT mgl=INT ENDLIMIT egl=INT
		public Group getGroup() { return cGroup; }

		//PSQT
		public RuleCall getPSQTTerminalRuleCall_0() { return cPSQTTerminalRuleCall_0; }

		//name=ID
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//ID
		public RuleCall getNameIDTerminalRuleCall_1_0() { return cNameIDTerminalRuleCall_1_0; }

		//BASE
		public RuleCall getBASETerminalRuleCall_2() { return cBASETerminalRuleCall_2; }

		//base=INT
		public Assignment getBaseAssignment_3() { return cBaseAssignment_3; }

		//INT
		public RuleCall getBaseINTTerminalRuleCall_3_0() { return cBaseINTTerminalRuleCall_3_0; }

		//MIDLIMIT
		public RuleCall getMIDLIMITTerminalRuleCall_4() { return cMIDLIMITTerminalRuleCall_4; }

		//mgl=INT
		public Assignment getMglAssignment_5() { return cMglAssignment_5; }

		//INT
		public RuleCall getMglINTTerminalRuleCall_5_0() { return cMglINTTerminalRuleCall_5_0; }

		//ENDLIMIT
		public RuleCall getENDLIMITTerminalRuleCall_6() { return cENDLIMITTerminalRuleCall_6; }

		//egl=INT
		public Assignment getEglAssignment_7() { return cEglAssignment_7; }

		//INT
		public RuleCall getEglINTTerminalRuleCall_7_0() { return cEglINTTerminalRuleCall_7_0; }
	}

	public class TableElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Table");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cTDescriptionParserRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final RuleCall cTHeaderParserRuleCall_1 = (RuleCall)cGroup.eContents().get(1);
		private final Assignment cRAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cRRowParserRuleCall_2_0 = (RuleCall)cRAssignment_2.eContents().get(0);
		private final Assignment cRAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cRRowParserRuleCall_3_0 = (RuleCall)cRAssignment_3.eContents().get(0);
		private final Assignment cRAssignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cRRowParserRuleCall_4_0 = (RuleCall)cRAssignment_4.eContents().get(0);
		private final Assignment cRAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cRRowParserRuleCall_5_0 = (RuleCall)cRAssignment_5.eContents().get(0);
		private final Assignment cRAssignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cRRowParserRuleCall_6_0 = (RuleCall)cRAssignment_6.eContents().get(0);
		private final Assignment cRAssignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cRRowParserRuleCall_7_0 = (RuleCall)cRAssignment_7.eContents().get(0);
		private final Assignment cRAssignment_8 = (Assignment)cGroup.eContents().get(8);
		private final RuleCall cRRowParserRuleCall_8_0 = (RuleCall)cRAssignment_8.eContents().get(0);
		private final Assignment cRAssignment_9 = (Assignment)cGroup.eContents().get(9);
		private final RuleCall cRRowParserRuleCall_9_0 = (RuleCall)cRAssignment_9.eContents().get(0);
		
		//Table:
		//	TDescription THeader r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row;
		public ParserRule getRule() { return rule; }

		//TDescription THeader r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row
		public Group getGroup() { return cGroup; }

		//TDescription
		public RuleCall getTDescriptionParserRuleCall_0() { return cTDescriptionParserRuleCall_0; }

		//THeader
		public RuleCall getTHeaderParserRuleCall_1() { return cTHeaderParserRuleCall_1; }

		//r+=Row
		public Assignment getRAssignment_2() { return cRAssignment_2; }

		//Row
		public RuleCall getRRowParserRuleCall_2_0() { return cRRowParserRuleCall_2_0; }

		//r+=Row
		public Assignment getRAssignment_3() { return cRAssignment_3; }

		//Row
		public RuleCall getRRowParserRuleCall_3_0() { return cRRowParserRuleCall_3_0; }

		//r+=Row
		public Assignment getRAssignment_4() { return cRAssignment_4; }

		//Row
		public RuleCall getRRowParserRuleCall_4_0() { return cRRowParserRuleCall_4_0; }

		//r+=Row
		public Assignment getRAssignment_5() { return cRAssignment_5; }

		//Row
		public RuleCall getRRowParserRuleCall_5_0() { return cRRowParserRuleCall_5_0; }

		//r+=Row
		public Assignment getRAssignment_6() { return cRAssignment_6; }

		//Row
		public RuleCall getRRowParserRuleCall_6_0() { return cRRowParserRuleCall_6_0; }

		//r+=Row
		public Assignment getRAssignment_7() { return cRAssignment_7; }

		//Row
		public RuleCall getRRowParserRuleCall_7_0() { return cRRowParserRuleCall_7_0; }

		//r+=Row
		public Assignment getRAssignment_8() { return cRAssignment_8; }

		//Row
		public RuleCall getRRowParserRuleCall_8_0() { return cRRowParserRuleCall_8_0; }

		//r+=Row
		public Assignment getRAssignment_9() { return cRAssignment_9; }

		//Row
		public RuleCall getRRowParserRuleCall_9_0() { return cRRowParserRuleCall_9_0; }
	}

	public class TDescriptionElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "TDescription");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final RuleCall cPIECETerminalRuleCall_0 = (RuleCall)cGroup.eContents().get(0);
		private final Assignment cNameAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cNamePieceTypeEnumRuleCall_1_0 = (RuleCall)cNameAssignment_1.eContents().get(0);
		private final RuleCall cMIDGAMETerminalRuleCall_2 = (RuleCall)cGroup.eContents().get(2);
		private final Assignment cMgAssignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cMgINTTerminalRuleCall_3_0 = (RuleCall)cMgAssignment_3.eContents().get(0);
		private final RuleCall cENDGAMETerminalRuleCall_4 = (RuleCall)cGroup.eContents().get(4);
		private final Assignment cEgAssignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cEgINTTerminalRuleCall_5_0 = (RuleCall)cEgAssignment_5.eContents().get(0);
		
		//TDescription:
		//	PIECE name=PieceType MIDGAME mg=INT ENDGAME eg=INT;
		public ParserRule getRule() { return rule; }

		//PIECE name=PieceType MIDGAME mg=INT ENDGAME eg=INT
		public Group getGroup() { return cGroup; }

		//PIECE
		public RuleCall getPIECETerminalRuleCall_0() { return cPIECETerminalRuleCall_0; }

		//name=PieceType
		public Assignment getNameAssignment_1() { return cNameAssignment_1; }

		//PieceType
		public RuleCall getNamePieceTypeEnumRuleCall_1_0() { return cNamePieceTypeEnumRuleCall_1_0; }

		//MIDGAME
		public RuleCall getMIDGAMETerminalRuleCall_2() { return cMIDGAMETerminalRuleCall_2; }

		//mg=INT
		public Assignment getMgAssignment_3() { return cMgAssignment_3; }

		//INT
		public RuleCall getMgINTTerminalRuleCall_3_0() { return cMgINTTerminalRuleCall_3_0; }

		//ENDGAME
		public RuleCall getENDGAMETerminalRuleCall_4() { return cENDGAMETerminalRuleCall_4; }

		//eg=INT
		public Assignment getEgAssignment_5() { return cEgAssignment_5; }

		//INT
		public RuleCall getEgINTTerminalRuleCall_5_0() { return cEgINTTerminalRuleCall_5_0; }
	}

	public class THeaderElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "THeader");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Keyword cAKeyword_0 = (Keyword)cGroup.eContents().get(0);
		private final Keyword cBKeyword_1 = (Keyword)cGroup.eContents().get(1);
		private final Keyword cCKeyword_2 = (Keyword)cGroup.eContents().get(2);
		private final Keyword cDKeyword_3 = (Keyword)cGroup.eContents().get(3);
		private final Keyword cEKeyword_4 = (Keyword)cGroup.eContents().get(4);
		private final Keyword cFKeyword_5 = (Keyword)cGroup.eContents().get(5);
		private final Keyword cGKeyword_6 = (Keyword)cGroup.eContents().get(6);
		private final Keyword cHKeyword_7 = (Keyword)cGroup.eContents().get(7);
		
		//THeader:
		//	"A" "B" "C" "D" "E" "F" "G" "H";
		public ParserRule getRule() { return rule; }

		//"A" "B" "C" "D" "E" "F" "G" "H"
		public Group getGroup() { return cGroup; }

		//"A"
		public Keyword getAKeyword_0() { return cAKeyword_0; }

		//"B"
		public Keyword getBKeyword_1() { return cBKeyword_1; }

		//"C"
		public Keyword getCKeyword_2() { return cCKeyword_2; }

		//"D"
		public Keyword getDKeyword_3() { return cDKeyword_3; }

		//"E"
		public Keyword getEKeyword_4() { return cEKeyword_4; }

		//"F"
		public Keyword getFKeyword_5() { return cFKeyword_5; }

		//"G"
		public Keyword getGKeyword_6() { return cGKeyword_6; }

		//"H"
		public Keyword getHKeyword_7() { return cHKeyword_7; }
	}

	public class RowElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "Row");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cNameAssignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cNameRowLabelParserRuleCall_0_0 = (RuleCall)cNameAssignment_0.eContents().get(0);
		private final Assignment cMidrowAssignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cMidrowMRowParserRuleCall_1_0 = (RuleCall)cMidrowAssignment_1.eContents().get(0);
		private final Assignment cEndrowAssignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cEndrowMRowParserRuleCall_2_0 = (RuleCall)cEndrowAssignment_2.eContents().get(0);
		
		//Row:
		//	name=RowLabel midrow=MRow endrow=MRow;
		public ParserRule getRule() { return rule; }

		//name=RowLabel midrow=MRow endrow=MRow
		public Group getGroup() { return cGroup; }

		//name=RowLabel
		public Assignment getNameAssignment_0() { return cNameAssignment_0; }

		//RowLabel
		public RuleCall getNameRowLabelParserRuleCall_0_0() { return cNameRowLabelParserRuleCall_0_0; }

		//midrow=MRow
		public Assignment getMidrowAssignment_1() { return cMidrowAssignment_1; }

		//MRow
		public RuleCall getMidrowMRowParserRuleCall_1_0() { return cMidrowMRowParserRuleCall_1_0; }

		//endrow=MRow
		public Assignment getEndrowAssignment_2() { return cEndrowAssignment_2; }

		//MRow
		public RuleCall getEndrowMRowParserRuleCall_2_0() { return cEndrowMRowParserRuleCall_2_0; }
	}

	public class RowLabelElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "RowLabel");
		private final RuleCall cINTTerminalRuleCall = (RuleCall)rule.eContents().get(1);
		
		//RowLabel returns ecore::EString:
		//	INT;
		public ParserRule getRule() { return rule; }

		//INT
		public RuleCall getINTTerminalRuleCall() { return cINTTerminalRuleCall; }
	}

	public class MRowElements extends AbstractParserRuleElementFinder {
		private final ParserRule rule = (ParserRule) GrammarUtil.findRuleForName(getGrammar(), "MRow");
		private final Group cGroup = (Group)rule.eContents().get(1);
		private final Assignment cC1Assignment_0 = (Assignment)cGroup.eContents().get(0);
		private final RuleCall cC1INTTerminalRuleCall_0_0 = (RuleCall)cC1Assignment_0.eContents().get(0);
		private final Assignment cC2Assignment_1 = (Assignment)cGroup.eContents().get(1);
		private final RuleCall cC2INTTerminalRuleCall_1_0 = (RuleCall)cC2Assignment_1.eContents().get(0);
		private final Assignment cC3Assignment_2 = (Assignment)cGroup.eContents().get(2);
		private final RuleCall cC3INTTerminalRuleCall_2_0 = (RuleCall)cC3Assignment_2.eContents().get(0);
		private final Assignment cC4Assignment_3 = (Assignment)cGroup.eContents().get(3);
		private final RuleCall cC4INTTerminalRuleCall_3_0 = (RuleCall)cC4Assignment_3.eContents().get(0);
		private final Assignment cC5Assignment_4 = (Assignment)cGroup.eContents().get(4);
		private final RuleCall cC5INTTerminalRuleCall_4_0 = (RuleCall)cC5Assignment_4.eContents().get(0);
		private final Assignment cC6Assignment_5 = (Assignment)cGroup.eContents().get(5);
		private final RuleCall cC6INTTerminalRuleCall_5_0 = (RuleCall)cC6Assignment_5.eContents().get(0);
		private final Assignment cC7Assignment_6 = (Assignment)cGroup.eContents().get(6);
		private final RuleCall cC7INTTerminalRuleCall_6_0 = (RuleCall)cC7Assignment_6.eContents().get(0);
		private final Assignment cC8Assignment_7 = (Assignment)cGroup.eContents().get(7);
		private final RuleCall cC8INTTerminalRuleCall_7_0 = (RuleCall)cC8Assignment_7.eContents().get(0);
		
		//MRow:
		//	c1=INT c2=INT c3=INT c4=INT c5=INT c6=INT c7=INT c8=INT;
		public ParserRule getRule() { return rule; }

		//c1=INT c2=INT c3=INT c4=INT c5=INT c6=INT c7=INT c8=INT
		public Group getGroup() { return cGroup; }

		//c1=INT
		public Assignment getC1Assignment_0() { return cC1Assignment_0; }

		//INT
		public RuleCall getC1INTTerminalRuleCall_0_0() { return cC1INTTerminalRuleCall_0_0; }

		//c2=INT
		public Assignment getC2Assignment_1() { return cC2Assignment_1; }

		//INT
		public RuleCall getC2INTTerminalRuleCall_1_0() { return cC2INTTerminalRuleCall_1_0; }

		//c3=INT
		public Assignment getC3Assignment_2() { return cC3Assignment_2; }

		//INT
		public RuleCall getC3INTTerminalRuleCall_2_0() { return cC3INTTerminalRuleCall_2_0; }

		//c4=INT
		public Assignment getC4Assignment_3() { return cC4Assignment_3; }

		//INT
		public RuleCall getC4INTTerminalRuleCall_3_0() { return cC4INTTerminalRuleCall_3_0; }

		//c5=INT
		public Assignment getC5Assignment_4() { return cC5Assignment_4; }

		//INT
		public RuleCall getC5INTTerminalRuleCall_4_0() { return cC5INTTerminalRuleCall_4_0; }

		//c6=INT
		public Assignment getC6Assignment_5() { return cC6Assignment_5; }

		//INT
		public RuleCall getC6INTTerminalRuleCall_5_0() { return cC6INTTerminalRuleCall_5_0; }

		//c7=INT
		public Assignment getC7Assignment_6() { return cC7Assignment_6; }

		//INT
		public RuleCall getC7INTTerminalRuleCall_6_0() { return cC7INTTerminalRuleCall_6_0; }

		//c8=INT
		public Assignment getC8Assignment_7() { return cC8Assignment_7; }

		//INT
		public RuleCall getC8INTTerminalRuleCall_7_0() { return cC8INTTerminalRuleCall_7_0; }
	}
	
	
	public class PieceTypeElements extends AbstractEnumRuleElementFinder {
		private final EnumRule rule = (EnumRule) GrammarUtil.findRuleForName(getGrammar(), "PieceType");
		private final Alternatives cAlternatives = (Alternatives)rule.eContents().get(1);
		private final EnumLiteralDeclaration cPAWNEnumLiteralDeclaration_0 = (EnumLiteralDeclaration)cAlternatives.eContents().get(0);
		private final Keyword cPAWNPawnKeyword_0_0 = (Keyword)cPAWNEnumLiteralDeclaration_0.eContents().get(0);
		private final EnumLiteralDeclaration cKNIGHTEnumLiteralDeclaration_1 = (EnumLiteralDeclaration)cAlternatives.eContents().get(1);
		private final Keyword cKNIGHTKnightKeyword_1_0 = (Keyword)cKNIGHTEnumLiteralDeclaration_1.eContents().get(0);
		private final EnumLiteralDeclaration cBISHOPEnumLiteralDeclaration_2 = (EnumLiteralDeclaration)cAlternatives.eContents().get(2);
		private final Keyword cBISHOPBishopKeyword_2_0 = (Keyword)cBISHOPEnumLiteralDeclaration_2.eContents().get(0);
		private final EnumLiteralDeclaration cROOKEnumLiteralDeclaration_3 = (EnumLiteralDeclaration)cAlternatives.eContents().get(3);
		private final Keyword cROOKRookKeyword_3_0 = (Keyword)cROOKEnumLiteralDeclaration_3.eContents().get(0);
		private final EnumLiteralDeclaration cQUEENEnumLiteralDeclaration_4 = (EnumLiteralDeclaration)cAlternatives.eContents().get(4);
		private final Keyword cQUEENQueenKeyword_4_0 = (Keyword)cQUEENEnumLiteralDeclaration_4.eContents().get(0);
		private final EnumLiteralDeclaration cKINGEnumLiteralDeclaration_5 = (EnumLiteralDeclaration)cAlternatives.eContents().get(5);
		private final Keyword cKINGKingKeyword_5_0 = (Keyword)cKINGEnumLiteralDeclaration_5.eContents().get(0);
		
		//enum PieceType:
		//	PAWN="Pawn" | KNIGHT="Knight" | BISHOP="Bishop" | ROOK="Rook" | QUEEN="Queen" | KING="King";
		public EnumRule getRule() { return rule; }

		//PAWN="Pawn" | KNIGHT="Knight" | BISHOP="Bishop" | ROOK="Rook" | QUEEN="Queen" | KING="King"
		public Alternatives getAlternatives() { return cAlternatives; }

		//PAWN="Pawn"
		public EnumLiteralDeclaration getPAWNEnumLiteralDeclaration_0() { return cPAWNEnumLiteralDeclaration_0; }

		//"Pawn"
		public Keyword getPAWNPawnKeyword_0_0() { return cPAWNPawnKeyword_0_0; }

		//KNIGHT="Knight"
		public EnumLiteralDeclaration getKNIGHTEnumLiteralDeclaration_1() { return cKNIGHTEnumLiteralDeclaration_1; }

		//"Knight"
		public Keyword getKNIGHTKnightKeyword_1_0() { return cKNIGHTKnightKeyword_1_0; }

		//BISHOP="Bishop"
		public EnumLiteralDeclaration getBISHOPEnumLiteralDeclaration_2() { return cBISHOPEnumLiteralDeclaration_2; }

		//"Bishop"
		public Keyword getBISHOPBishopKeyword_2_0() { return cBISHOPBishopKeyword_2_0; }

		//ROOK="Rook"
		public EnumLiteralDeclaration getROOKEnumLiteralDeclaration_3() { return cROOKEnumLiteralDeclaration_3; }

		//"Rook"
		public Keyword getROOKRookKeyword_3_0() { return cROOKRookKeyword_3_0; }

		//QUEEN="Queen"
		public EnumLiteralDeclaration getQUEENEnumLiteralDeclaration_4() { return cQUEENEnumLiteralDeclaration_4; }

		//"Queen"
		public Keyword getQUEENQueenKeyword_4_0() { return cQUEENQueenKeyword_4_0; }

		//KING="King"
		public EnumLiteralDeclaration getKINGEnumLiteralDeclaration_5() { return cKINGEnumLiteralDeclaration_5; }

		//"King"
		public Keyword getKINGKingKeyword_5_0() { return cKINGKingKeyword_5_0; }
	}
	
	private PSQT_ModelElements pPSQT_Model;
	private PieceTypeElements unknownRulePieceType;
	private FDescriptionElements pFDescription;
	private TableElements pTable;
	private TDescriptionElements pTDescription;
	private THeaderElements pTHeader;
	private RowElements pRow;
	private RowLabelElements pRowLabel;
	private MRowElements pMRow;
	private TerminalRule tINT;
	private TerminalRule tPSQT;
	private TerminalRule tBASE;
	private TerminalRule tPIECE;
	private TerminalRule tMIDLIMIT;
	private TerminalRule tENDLIMIT;
	private TerminalRule tMIDGAME;
	private TerminalRule tENDGAME;
	
	private final Grammar grammar;

	private TerminalsGrammarAccess gaTerminals;

	@Inject
	public PSQTGrammarAccess(GrammarProvider grammarProvider,
		TerminalsGrammarAccess gaTerminals) {
		this.grammar = internalFindGrammar(grammarProvider);
		this.gaTerminals = gaTerminals;
	}
	
	protected Grammar internalFindGrammar(GrammarProvider grammarProvider) {
		Grammar grammar = grammarProvider.getGrammar(this);
		while (grammar != null) {
			if ("norwegiangambit.psqt.PSQT".equals(grammar.getName())) {
				return grammar;
			}
			List<Grammar> grammars = grammar.getUsedGrammars();
			if (!grammars.isEmpty()) {
				grammar = grammars.iterator().next();
			} else {
				return null;
			}
		}
		return grammar;
	}
	
	
	public Grammar getGrammar() {
		return grammar;
	}
	

	public TerminalsGrammarAccess getTerminalsGrammarAccess() {
		return gaTerminals;
	}

	
	//PSQT_Model:
	//	FDescription tables+=Table*;
	public PSQT_ModelElements getPSQT_ModelAccess() {
		return (pPSQT_Model != null) ? pPSQT_Model : (pPSQT_Model = new PSQT_ModelElements());
	}
	
	public ParserRule getPSQT_ModelRule() {
		return getPSQT_ModelAccess().getRule();
	}

	//enum PieceType:
	//	PAWN="Pawn" | KNIGHT="Knight" | BISHOP="Bishop" | ROOK="Rook" | QUEEN="Queen" | KING="King";
	public PieceTypeElements getPieceTypeAccess() {
		return (unknownRulePieceType != null) ? unknownRulePieceType : (unknownRulePieceType = new PieceTypeElements());
	}
	
	public EnumRule getPieceTypeRule() {
		return getPieceTypeAccess().getRule();
	}

	////enum RowNumber : R1='1'|R2='2'|R3='3'|R4='4'|R5='5'|R6='6'|R7='7'|R8='8';
	//FDescription:
	//	PSQT name=ID BASE base=INT MIDLIMIT mgl=INT ENDLIMIT egl=INT;
	public FDescriptionElements getFDescriptionAccess() {
		return (pFDescription != null) ? pFDescription : (pFDescription = new FDescriptionElements());
	}
	
	public ParserRule getFDescriptionRule() {
		return getFDescriptionAccess().getRule();
	}

	//Table:
	//	TDescription THeader r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row r+=Row;
	public TableElements getTableAccess() {
		return (pTable != null) ? pTable : (pTable = new TableElements());
	}
	
	public ParserRule getTableRule() {
		return getTableAccess().getRule();
	}

	//TDescription:
	//	PIECE name=PieceType MIDGAME mg=INT ENDGAME eg=INT;
	public TDescriptionElements getTDescriptionAccess() {
		return (pTDescription != null) ? pTDescription : (pTDescription = new TDescriptionElements());
	}
	
	public ParserRule getTDescriptionRule() {
		return getTDescriptionAccess().getRule();
	}

	//THeader:
	//	"A" "B" "C" "D" "E" "F" "G" "H";
	public THeaderElements getTHeaderAccess() {
		return (pTHeader != null) ? pTHeader : (pTHeader = new THeaderElements());
	}
	
	public ParserRule getTHeaderRule() {
		return getTHeaderAccess().getRule();
	}

	//Row:
	//	name=RowLabel midrow=MRow endrow=MRow;
	public RowElements getRowAccess() {
		return (pRow != null) ? pRow : (pRow = new RowElements());
	}
	
	public ParserRule getRowRule() {
		return getRowAccess().getRule();
	}

	//RowLabel returns ecore::EString:
	//	INT;
	public RowLabelElements getRowLabelAccess() {
		return (pRowLabel != null) ? pRowLabel : (pRowLabel = new RowLabelElements());
	}
	
	public ParserRule getRowLabelRule() {
		return getRowLabelAccess().getRule();
	}

	//MRow:
	//	c1=INT c2=INT c3=INT c4=INT c5=INT c6=INT c7=INT c8=INT;
	public MRowElements getMRowAccess() {
		return (pMRow != null) ? pMRow : (pMRow = new MRowElements());
	}
	
	public ParserRule getMRowRule() {
		return getMRowAccess().getRule();
	}

	//terminal INT returns ecore::EInt:
	//	"-"? "0".."9"+;
	public TerminalRule getINTRule() {
		return (tINT != null) ? tINT : (tINT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "INT"));
	} 

	//terminal PSQT:
	//	"PSQT:";
	public TerminalRule getPSQTRule() {
		return (tPSQT != null) ? tPSQT : (tPSQT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "PSQT"));
	} 

	//terminal BASE:
	//	"Base:";
	public TerminalRule getBASERule() {
		return (tBASE != null) ? tBASE : (tBASE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "BASE"));
	} 

	//terminal PIECE:
	//	"Piece:";
	public TerminalRule getPIECERule() {
		return (tPIECE != null) ? tPIECE : (tPIECE = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "PIECE"));
	} 

	//terminal MIDLIMIT:
	//	"MidLimit:";
	public TerminalRule getMIDLIMITRule() {
		return (tMIDLIMIT != null) ? tMIDLIMIT : (tMIDLIMIT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "MIDLIMIT"));
	} 

	//terminal ENDLIMIT:
	//	"EndLimit:";
	public TerminalRule getENDLIMITRule() {
		return (tENDLIMIT != null) ? tENDLIMIT : (tENDLIMIT = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "ENDLIMIT"));
	} 

	//terminal MIDGAME:
	//	"MidGame:";
	public TerminalRule getMIDGAMERule() {
		return (tMIDGAME != null) ? tMIDGAME : (tMIDGAME = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "MIDGAME"));
	} 

	//terminal ENDGAME:
	//	"EndGame:";
	public TerminalRule getENDGAMERule() {
		return (tENDGAME != null) ? tENDGAME : (tENDGAME = (TerminalRule) GrammarUtil.findRuleForName(getGrammar(), "ENDGAME"));
	} 

	//terminal ID:
	//	"^"? ("a".."z" | "A".."Z" | "_") ("a".."z" | "A".."Z" | "_" | "0".."9")*;
	public TerminalRule getIDRule() {
		return gaTerminals.getIDRule();
	} 

	//terminal STRING:
	//	"\"" ("\\" ("b" | "t" | "n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\""))* "\"" | "\'" ("\\" ("b" | "t" |
	//	"n" | "f" | "r" | "u" | "\"" | "\'" | "\\") | !("\\" | "\'"))* "\'";
	public TerminalRule getSTRINGRule() {
		return gaTerminals.getSTRINGRule();
	} 

	//terminal ML_COMMENT:
	//	"/ *"->"* /";
	public TerminalRule getML_COMMENTRule() {
		return gaTerminals.getML_COMMENTRule();
	} 

	//terminal SL_COMMENT:
	//	"//" !("\n" | "\r")* ("\r"? "\n")?;
	public TerminalRule getSL_COMMENTRule() {
		return gaTerminals.getSL_COMMENTRule();
	} 

	//terminal WS:
	//	(" " | "\t" | "\r" | "\n")+;
	public TerminalRule getWSRule() {
		return gaTerminals.getWSRule();
	} 

	//terminal ANY_OTHER:
	//	.;
	public TerminalRule getANY_OTHERRule() {
		return gaTerminals.getANY_OTHERRule();
	} 
}
