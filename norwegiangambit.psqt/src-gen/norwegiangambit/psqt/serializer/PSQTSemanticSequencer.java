package norwegiangambit.psqt.serializer;

import com.google.inject.Inject;
import com.google.inject.Provider;
import norwegiangambit.psqt.pSQT.FDescription;
import norwegiangambit.psqt.pSQT.MRow;
import norwegiangambit.psqt.pSQT.PSQTPackage;
import norwegiangambit.psqt.pSQT.Row;
import norwegiangambit.psqt.pSQT.TDescription;
import norwegiangambit.psqt.services.PSQTGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.serializer.acceptor.ISemanticSequenceAcceptor;
import org.eclipse.xtext.serializer.acceptor.SequenceFeeder;
import org.eclipse.xtext.serializer.diagnostic.ISemanticSequencerDiagnosticProvider;
import org.eclipse.xtext.serializer.diagnostic.ISerializationDiagnostic.Acceptor;
import org.eclipse.xtext.serializer.sequencer.AbstractDelegatingSemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.GenericSequencer;
import org.eclipse.xtext.serializer.sequencer.ISemanticNodeProvider.INodesForEObjectProvider;
import org.eclipse.xtext.serializer.sequencer.ISemanticSequencer;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService;
import org.eclipse.xtext.serializer.sequencer.ITransientValueService.ValueTransient;

@SuppressWarnings("all")
public class PSQTSemanticSequencer extends AbstractDelegatingSemanticSequencer {

	@Inject
	private PSQTGrammarAccess grammarAccess;
	
	public void createSequence(EObject context, EObject semanticObject) {
		if(semanticObject.eClass().getEPackage() == PSQTPackage.eINSTANCE) switch(semanticObject.eClass().getClassifierID()) {
			case PSQTPackage.FDESCRIPTION:
				if(context == grammarAccess.getFDescriptionRule()) {
					sequence_FDescription(context, (FDescription) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getPSQT_ModelRule()) {
					sequence_FDescription_PSQT_Model(context, (FDescription) semanticObject); 
					return; 
				}
				else break;
			case PSQTPackage.MROW:
				if(context == grammarAccess.getMRowRule()) {
					sequence_MRow(context, (MRow) semanticObject); 
					return; 
				}
				else break;
			case PSQTPackage.ROW:
				if(context == grammarAccess.getRowRule()) {
					sequence_Row(context, (Row) semanticObject); 
					return; 
				}
				else break;
			case PSQTPackage.TDESCRIPTION:
				if(context == grammarAccess.getTDescriptionRule()) {
					sequence_TDescription(context, (TDescription) semanticObject); 
					return; 
				}
				else if(context == grammarAccess.getTableRule()) {
					sequence_TDescription_Table(context, (TDescription) semanticObject); 
					return; 
				}
				else break;
			}
		if (errorAcceptor != null) errorAcceptor.accept(diagnosticProvider.createInvalidContextOrTypeDiagnostic(semanticObject, context));
	}
	
	/**
	 * Constraint:
	 *     (name=ID base=INT mgl=INT egl=INT)
	 */
	protected void sequence_FDescription(EObject context, FDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (name=ID base=INT mgl=INT egl=INT tables+=Table*)
	 */
	protected void sequence_FDescription_PSQT_Model(EObject context, FDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         c1=INT 
	 *         c2=INT 
	 *         c3=INT 
	 *         c4=INT 
	 *         c5=INT 
	 *         c6=INT 
	 *         c7=INT 
	 *         c8=INT
	 *     )
	 */
	protected void sequence_MRow(EObject context, MRow semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C1) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C1));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C2) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C2));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C3) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C3));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C4) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C4));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C5) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C5));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C6) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C6));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C7) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C7));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.MROW__C8) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.MROW__C8));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getMRowAccess().getC1INTTerminalRuleCall_0_0(), semanticObject.getC1());
		feeder.accept(grammarAccess.getMRowAccess().getC2INTTerminalRuleCall_1_0(), semanticObject.getC2());
		feeder.accept(grammarAccess.getMRowAccess().getC3INTTerminalRuleCall_2_0(), semanticObject.getC3());
		feeder.accept(grammarAccess.getMRowAccess().getC4INTTerminalRuleCall_3_0(), semanticObject.getC4());
		feeder.accept(grammarAccess.getMRowAccess().getC5INTTerminalRuleCall_4_0(), semanticObject.getC5());
		feeder.accept(grammarAccess.getMRowAccess().getC6INTTerminalRuleCall_5_0(), semanticObject.getC6());
		feeder.accept(grammarAccess.getMRowAccess().getC7INTTerminalRuleCall_6_0(), semanticObject.getC7());
		feeder.accept(grammarAccess.getMRowAccess().getC8INTTerminalRuleCall_7_0(), semanticObject.getC8());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=RowLabel midrow=MRow endrow=MRow)
	 */
	protected void sequence_Row(EObject context, Row semanticObject) {
		if(errorAcceptor != null) {
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.ROW__NAME) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.ROW__NAME));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.ROW__MIDROW) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.ROW__MIDROW));
			if(transientValues.isValueTransient(semanticObject, PSQTPackage.Literals.ROW__ENDROW) == ValueTransient.YES)
				errorAcceptor.accept(diagnosticProvider.createFeatureValueMissing(semanticObject, PSQTPackage.Literals.ROW__ENDROW));
		}
		INodesForEObjectProvider nodes = createNodeProvider(semanticObject);
		SequenceFeeder feeder = createSequencerFeeder(semanticObject, nodes);
		feeder.accept(grammarAccess.getRowAccess().getNameRowLabelParserRuleCall_0_0(), semanticObject.getName());
		feeder.accept(grammarAccess.getRowAccess().getMidrowMRowParserRuleCall_1_0(), semanticObject.getMidrow());
		feeder.accept(grammarAccess.getRowAccess().getEndrowMRowParserRuleCall_2_0(), semanticObject.getEndrow());
		feeder.finish();
	}
	
	
	/**
	 * Constraint:
	 *     (name=PieceType mg=INT eg=INT)
	 */
	protected void sequence_TDescription(EObject context, TDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
	
	
	/**
	 * Constraint:
	 *     (
	 *         name=PieceType 
	 *         mg=INT 
	 *         eg=INT 
	 *         r+=Row 
	 *         r+=Row 
	 *         r+=Row 
	 *         r+=Row 
	 *         r+=Row 
	 *         r+=Row 
	 *         r+=Row 
	 *         r+=Row
	 *     )
	 */
	protected void sequence_TDescription_Table(EObject context, TDescription semanticObject) {
		genericSequencer.createSequence(context, semanticObject);
	}
}
