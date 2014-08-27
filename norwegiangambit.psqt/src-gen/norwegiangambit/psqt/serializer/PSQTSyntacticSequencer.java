package norwegiangambit.psqt.serializer;

import com.google.inject.Inject;
import java.util.List;
import norwegiangambit.psqt.services.PSQTGrammarAccess;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.IGrammarAccess;
import org.eclipse.xtext.RuleCall;
import org.eclipse.xtext.nodemodel.INode;
import org.eclipse.xtext.serializer.analysis.GrammarAlias.AbstractElementAlias;
import org.eclipse.xtext.serializer.analysis.ISyntacticSequencerPDAProvider.ISynTransition;
import org.eclipse.xtext.serializer.sequencer.AbstractSyntacticSequencer;

@SuppressWarnings("all")
public class PSQTSyntacticSequencer extends AbstractSyntacticSequencer {

	protected PSQTGrammarAccess grammarAccess;
	
	@Inject
	protected void init(IGrammarAccess access) {
		grammarAccess = (PSQTGrammarAccess) access;
	}
	
	@Override
	protected String getUnassignedRuleCallToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if(ruleCall.getRule() == grammarAccess.getBASERule())
			return getBASEToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getENDGAMERule())
			return getENDGAMEToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getENDLIMITRule())
			return getENDLIMITToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getMIDGAMERule())
			return getMIDGAMEToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getMIDLIMITRule())
			return getMIDLIMITToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getPIECERule())
			return getPIECEToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getPSQTRule())
			return getPSQTToken(semanticObject, ruleCall, node);
		else if(ruleCall.getRule() == grammarAccess.getTHeaderRule())
			return getTHeaderToken(semanticObject, ruleCall, node);
		return "";
	}
	
	/**
	 * terminal BASE: 'Base:';
	 */
	protected String getBASEToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "Base:";
	}
	
	/**
	 * terminal ENDGAME: 'EndGame:';
	 */
	protected String getENDGAMEToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "EndGame:";
	}
	
	/**
	 * terminal ENDLIMIT: 'EndLimit:';
	 */
	protected String getENDLIMITToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "EndLimit:";
	}
	
	/**
	 * terminal MIDGAME: 'MidGame:';
	 */
	protected String getMIDGAMEToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "MidGame:";
	}
	
	/**
	 * terminal MIDLIMIT: 'MidLimit:';
	 */
	protected String getMIDLIMITToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "MidLimit:";
	}
	
	/**
	 * terminal PIECE: 'Piece:';
	 */
	protected String getPIECEToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "Piece:";
	}
	
	/**
	 * terminal PSQT: 'PSQT:';
	 */
	protected String getPSQTToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "PSQT:";
	}
	
	/**
	 * THeader: 'A' 'B' 'C' 'D' 'E' 'F' 'G' 'H';
	 */
	protected String getTHeaderToken(EObject semanticObject, RuleCall ruleCall, INode node) {
		if (node != null)
			return getTokenText(node);
		return "ABCDEFGH";
	}
	
	@Override
	protected void emitUnassignedTokens(EObject semanticObject, ISynTransition transition, INode fromNode, INode toNode) {
		if (transition.getAmbiguousSyntaxes().isEmpty()) return;
		List<INode> transitionNodes = collectNodes(fromNode, toNode);
		for (AbstractElementAlias syntax : transition.getAmbiguousSyntaxes()) {
			List<INode> syntaxNodes = getNodesFor(transitionNodes, syntax);
			acceptNodes(getLastNavigableState(), syntaxNodes);
		}
	}

}
