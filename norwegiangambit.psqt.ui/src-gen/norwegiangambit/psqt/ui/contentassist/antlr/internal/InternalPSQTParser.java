package norwegiangambit.psqt.ui.contentassist.antlr.internal; 

import java.io.InputStream;
import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.AbstractInternalContentAssistParser;
import org.eclipse.xtext.ui.editor.contentassist.antlr.internal.DFA;
import norwegiangambit.psqt.services.PSQTGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPSQTParser extends AbstractInternalContentAssistParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_INT", "RULE_PSQT", "RULE_BASE", "RULE_MIDLIMIT", "RULE_ENDLIMIT", "RULE_PIECE", "RULE_MIDGAME", "RULE_ENDGAME", "RULE_ID", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'Pawn'", "'Knight'", "'Bishop'", "'Rook'", "'Queen'", "'King'", "'A'", "'B'", "'C'", "'D'", "'E'", "'F'", "'G'", "'H'"
    };
    public static final int RULE_MIDGAME=10;
    public static final int RULE_ENDGAME=11;
    public static final int RULE_PIECE=9;
    public static final int RULE_STRING=13;
    public static final int RULE_SL_COMMENT=15;
    public static final int T__19=19;
    public static final int RULE_MIDLIMIT=7;
    public static final int RULE_BASE=6;
    public static final int T__18=18;
    public static final int RULE_ENDLIMIT=8;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_ID=12;
    public static final int RULE_WS=16;
    public static final int RULE_ANY_OTHER=17;
    public static final int RULE_PSQT=5;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=4;
    public static final int T__29=29;
    public static final int T__22=22;
    public static final int RULE_ML_COMMENT=14;
    public static final int T__23=23;
    public static final int T__24=24;
    public static final int T__25=25;
    public static final int T__20=20;
    public static final int T__21=21;

    // delegates
    // delegators


        public InternalPSQTParser(TokenStream input) {
            this(input, new RecognizerSharedState());
        }
        public InternalPSQTParser(TokenStream input, RecognizerSharedState state) {
            super(input, state);
             
        }
        

    public String[] getTokenNames() { return InternalPSQTParser.tokenNames; }
    public String getGrammarFileName() { return "../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g"; }


     
     	private PSQTGrammarAccess grammarAccess;
     	
        public void setGrammarAccess(PSQTGrammarAccess grammarAccess) {
        	this.grammarAccess = grammarAccess;
        }
        
        @Override
        protected Grammar getGrammar() {
        	return grammarAccess.getGrammar();
        }
        
        @Override
        protected String getValueForTokenName(String tokenName) {
        	return tokenName;
        }




    // $ANTLR start "entryRulePSQT_Model"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:60:1: entryRulePSQT_Model : rulePSQT_Model EOF ;
    public final void entryRulePSQT_Model() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:61:1: ( rulePSQT_Model EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:62:1: rulePSQT_Model EOF
            {
             before(grammarAccess.getPSQT_ModelRule()); 
            pushFollow(FOLLOW_rulePSQT_Model_in_entryRulePSQT_Model61);
            rulePSQT_Model();

            state._fsp--;

             after(grammarAccess.getPSQT_ModelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRulePSQT_Model68); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRulePSQT_Model"


    // $ANTLR start "rulePSQT_Model"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:69:1: rulePSQT_Model : ( ( rule__PSQT_Model__Group__0 ) ) ;
    public final void rulePSQT_Model() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:73:2: ( ( ( rule__PSQT_Model__Group__0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:74:1: ( ( rule__PSQT_Model__Group__0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:74:1: ( ( rule__PSQT_Model__Group__0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:75:1: ( rule__PSQT_Model__Group__0 )
            {
             before(grammarAccess.getPSQT_ModelAccess().getGroup()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:76:1: ( rule__PSQT_Model__Group__0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:76:2: rule__PSQT_Model__Group__0
            {
            pushFollow(FOLLOW_rule__PSQT_Model__Group__0_in_rulePSQT_Model94);
            rule__PSQT_Model__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getPSQT_ModelAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePSQT_Model"


    // $ANTLR start "entryRuleFDescription"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:88:1: entryRuleFDescription : ruleFDescription EOF ;
    public final void entryRuleFDescription() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:89:1: ( ruleFDescription EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:90:1: ruleFDescription EOF
            {
             before(grammarAccess.getFDescriptionRule()); 
            pushFollow(FOLLOW_ruleFDescription_in_entryRuleFDescription121);
            ruleFDescription();

            state._fsp--;

             after(grammarAccess.getFDescriptionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFDescription128); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleFDescription"


    // $ANTLR start "ruleFDescription"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:97:1: ruleFDescription : ( ( rule__FDescription__Group__0 ) ) ;
    public final void ruleFDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:101:2: ( ( ( rule__FDescription__Group__0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:102:1: ( ( rule__FDescription__Group__0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:102:1: ( ( rule__FDescription__Group__0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:103:1: ( rule__FDescription__Group__0 )
            {
             before(grammarAccess.getFDescriptionAccess().getGroup()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:104:1: ( rule__FDescription__Group__0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:104:2: rule__FDescription__Group__0
            {
            pushFollow(FOLLOW_rule__FDescription__Group__0_in_ruleFDescription154);
            rule__FDescription__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getFDescriptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleFDescription"


    // $ANTLR start "entryRuleTable"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:116:1: entryRuleTable : ruleTable EOF ;
    public final void entryRuleTable() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:117:1: ( ruleTable EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:118:1: ruleTable EOF
            {
             before(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_ruleTable_in_entryRuleTable181);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getTableRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTable188); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTable"


    // $ANTLR start "ruleTable"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:125:1: ruleTable : ( ( rule__Table__Group__0 ) ) ;
    public final void ruleTable() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:129:2: ( ( ( rule__Table__Group__0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:130:1: ( ( rule__Table__Group__0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:130:1: ( ( rule__Table__Group__0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:131:1: ( rule__Table__Group__0 )
            {
             before(grammarAccess.getTableAccess().getGroup()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:132:1: ( rule__Table__Group__0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:132:2: rule__Table__Group__0
            {
            pushFollow(FOLLOW_rule__Table__Group__0_in_ruleTable214);
            rule__Table__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTable"


    // $ANTLR start "entryRuleTDescription"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:144:1: entryRuleTDescription : ruleTDescription EOF ;
    public final void entryRuleTDescription() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:145:1: ( ruleTDescription EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:146:1: ruleTDescription EOF
            {
             before(grammarAccess.getTDescriptionRule()); 
            pushFollow(FOLLOW_ruleTDescription_in_entryRuleTDescription241);
            ruleTDescription();

            state._fsp--;

             after(grammarAccess.getTDescriptionRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTDescription248); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTDescription"


    // $ANTLR start "ruleTDescription"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:153:1: ruleTDescription : ( ( rule__TDescription__Group__0 ) ) ;
    public final void ruleTDescription() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:157:2: ( ( ( rule__TDescription__Group__0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:158:1: ( ( rule__TDescription__Group__0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:158:1: ( ( rule__TDescription__Group__0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:159:1: ( rule__TDescription__Group__0 )
            {
             before(grammarAccess.getTDescriptionAccess().getGroup()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:160:1: ( rule__TDescription__Group__0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:160:2: rule__TDescription__Group__0
            {
            pushFollow(FOLLOW_rule__TDescription__Group__0_in_ruleTDescription274);
            rule__TDescription__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTDescriptionAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTDescription"


    // $ANTLR start "entryRuleTHeader"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:172:1: entryRuleTHeader : ruleTHeader EOF ;
    public final void entryRuleTHeader() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:173:1: ( ruleTHeader EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:174:1: ruleTHeader EOF
            {
             before(grammarAccess.getTHeaderRule()); 
            pushFollow(FOLLOW_ruleTHeader_in_entryRuleTHeader301);
            ruleTHeader();

            state._fsp--;

             after(grammarAccess.getTHeaderRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTHeader308); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleTHeader"


    // $ANTLR start "ruleTHeader"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:181:1: ruleTHeader : ( ( rule__THeader__Group__0 ) ) ;
    public final void ruleTHeader() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:185:2: ( ( ( rule__THeader__Group__0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:186:1: ( ( rule__THeader__Group__0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:186:1: ( ( rule__THeader__Group__0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:187:1: ( rule__THeader__Group__0 )
            {
             before(grammarAccess.getTHeaderAccess().getGroup()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:188:1: ( rule__THeader__Group__0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:188:2: rule__THeader__Group__0
            {
            pushFollow(FOLLOW_rule__THeader__Group__0_in_ruleTHeader334);
            rule__THeader__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getTHeaderAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleTHeader"


    // $ANTLR start "entryRuleRow"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:200:1: entryRuleRow : ruleRow EOF ;
    public final void entryRuleRow() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:201:1: ( ruleRow EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:202:1: ruleRow EOF
            {
             before(grammarAccess.getRowRule()); 
            pushFollow(FOLLOW_ruleRow_in_entryRuleRow361);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getRowRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRow368); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRow"


    // $ANTLR start "ruleRow"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:209:1: ruleRow : ( ( rule__Row__Group__0 ) ) ;
    public final void ruleRow() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:213:2: ( ( ( rule__Row__Group__0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:214:1: ( ( rule__Row__Group__0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:214:1: ( ( rule__Row__Group__0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:215:1: ( rule__Row__Group__0 )
            {
             before(grammarAccess.getRowAccess().getGroup()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:216:1: ( rule__Row__Group__0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:216:2: rule__Row__Group__0
            {
            pushFollow(FOLLOW_rule__Row__Group__0_in_ruleRow394);
            rule__Row__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getRowAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRow"


    // $ANTLR start "entryRuleRowLabel"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:228:1: entryRuleRowLabel : ruleRowLabel EOF ;
    public final void entryRuleRowLabel() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:229:1: ( ruleRowLabel EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:230:1: ruleRowLabel EOF
            {
             before(grammarAccess.getRowLabelRule()); 
            pushFollow(FOLLOW_ruleRowLabel_in_entryRuleRowLabel421);
            ruleRowLabel();

            state._fsp--;

             after(grammarAccess.getRowLabelRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRowLabel428); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleRowLabel"


    // $ANTLR start "ruleRowLabel"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:237:1: ruleRowLabel : ( RULE_INT ) ;
    public final void ruleRowLabel() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:241:2: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:242:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:242:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:243:1: RULE_INT
            {
             before(grammarAccess.getRowLabelAccess().getINTTerminalRuleCall()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleRowLabel454); 
             after(grammarAccess.getRowLabelAccess().getINTTerminalRuleCall()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleRowLabel"


    // $ANTLR start "entryRuleMRow"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:256:1: entryRuleMRow : ruleMRow EOF ;
    public final void entryRuleMRow() throws RecognitionException {
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:257:1: ( ruleMRow EOF )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:258:1: ruleMRow EOF
            {
             before(grammarAccess.getMRowRule()); 
            pushFollow(FOLLOW_ruleMRow_in_entryRuleMRow480);
            ruleMRow();

            state._fsp--;

             after(grammarAccess.getMRowRule()); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMRow487); 

            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {
        }
        return ;
    }
    // $ANTLR end "entryRuleMRow"


    // $ANTLR start "ruleMRow"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:265:1: ruleMRow : ( ( rule__MRow__Group__0 ) ) ;
    public final void ruleMRow() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:269:2: ( ( ( rule__MRow__Group__0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:270:1: ( ( rule__MRow__Group__0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:270:1: ( ( rule__MRow__Group__0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:271:1: ( rule__MRow__Group__0 )
            {
             before(grammarAccess.getMRowAccess().getGroup()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:272:1: ( rule__MRow__Group__0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:272:2: rule__MRow__Group__0
            {
            pushFollow(FOLLOW_rule__MRow__Group__0_in_ruleMRow513);
            rule__MRow__Group__0();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getGroup()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "ruleMRow"


    // $ANTLR start "rulePieceType"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:285:1: rulePieceType : ( ( rule__PieceType__Alternatives ) ) ;
    public final void rulePieceType() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:289:1: ( ( ( rule__PieceType__Alternatives ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:290:1: ( ( rule__PieceType__Alternatives ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:290:1: ( ( rule__PieceType__Alternatives ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:291:1: ( rule__PieceType__Alternatives )
            {
             before(grammarAccess.getPieceTypeAccess().getAlternatives()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:292:1: ( rule__PieceType__Alternatives )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:292:2: rule__PieceType__Alternatives
            {
            pushFollow(FOLLOW_rule__PieceType__Alternatives_in_rulePieceType550);
            rule__PieceType__Alternatives();

            state._fsp--;


            }

             after(grammarAccess.getPieceTypeAccess().getAlternatives()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rulePieceType"


    // $ANTLR start "rule__PieceType__Alternatives"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:303:1: rule__PieceType__Alternatives : ( ( ( 'Pawn' ) ) | ( ( 'Knight' ) ) | ( ( 'Bishop' ) ) | ( ( 'Rook' ) ) | ( ( 'Queen' ) ) | ( ( 'King' ) ) );
    public final void rule__PieceType__Alternatives() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:307:1: ( ( ( 'Pawn' ) ) | ( ( 'Knight' ) ) | ( ( 'Bishop' ) ) | ( ( 'Rook' ) ) | ( ( 'Queen' ) ) | ( ( 'King' ) ) )
            int alt1=6;
            switch ( input.LA(1) ) {
            case 18:
                {
                alt1=1;
                }
                break;
            case 19:
                {
                alt1=2;
                }
                break;
            case 20:
                {
                alt1=3;
                }
                break;
            case 21:
                {
                alt1=4;
                }
                break;
            case 22:
                {
                alt1=5;
                }
                break;
            case 23:
                {
                alt1=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 1, 0, input);

                throw nvae;
            }

            switch (alt1) {
                case 1 :
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:308:1: ( ( 'Pawn' ) )
                    {
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:308:1: ( ( 'Pawn' ) )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:309:1: ( 'Pawn' )
                    {
                     before(grammarAccess.getPieceTypeAccess().getPAWNEnumLiteralDeclaration_0()); 
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:310:1: ( 'Pawn' )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:310:3: 'Pawn'
                    {
                    match(input,18,FOLLOW_18_in_rule__PieceType__Alternatives586); 

                    }

                     after(grammarAccess.getPieceTypeAccess().getPAWNEnumLiteralDeclaration_0()); 

                    }


                    }
                    break;
                case 2 :
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:315:6: ( ( 'Knight' ) )
                    {
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:315:6: ( ( 'Knight' ) )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:316:1: ( 'Knight' )
                    {
                     before(grammarAccess.getPieceTypeAccess().getKNIGHTEnumLiteralDeclaration_1()); 
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:317:1: ( 'Knight' )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:317:3: 'Knight'
                    {
                    match(input,19,FOLLOW_19_in_rule__PieceType__Alternatives607); 

                    }

                     after(grammarAccess.getPieceTypeAccess().getKNIGHTEnumLiteralDeclaration_1()); 

                    }


                    }
                    break;
                case 3 :
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:322:6: ( ( 'Bishop' ) )
                    {
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:322:6: ( ( 'Bishop' ) )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:323:1: ( 'Bishop' )
                    {
                     before(grammarAccess.getPieceTypeAccess().getBISHOPEnumLiteralDeclaration_2()); 
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:324:1: ( 'Bishop' )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:324:3: 'Bishop'
                    {
                    match(input,20,FOLLOW_20_in_rule__PieceType__Alternatives628); 

                    }

                     after(grammarAccess.getPieceTypeAccess().getBISHOPEnumLiteralDeclaration_2()); 

                    }


                    }
                    break;
                case 4 :
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:329:6: ( ( 'Rook' ) )
                    {
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:329:6: ( ( 'Rook' ) )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:330:1: ( 'Rook' )
                    {
                     before(grammarAccess.getPieceTypeAccess().getROOKEnumLiteralDeclaration_3()); 
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:331:1: ( 'Rook' )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:331:3: 'Rook'
                    {
                    match(input,21,FOLLOW_21_in_rule__PieceType__Alternatives649); 

                    }

                     after(grammarAccess.getPieceTypeAccess().getROOKEnumLiteralDeclaration_3()); 

                    }


                    }
                    break;
                case 5 :
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:336:6: ( ( 'Queen' ) )
                    {
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:336:6: ( ( 'Queen' ) )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:337:1: ( 'Queen' )
                    {
                     before(grammarAccess.getPieceTypeAccess().getQUEENEnumLiteralDeclaration_4()); 
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:338:1: ( 'Queen' )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:338:3: 'Queen'
                    {
                    match(input,22,FOLLOW_22_in_rule__PieceType__Alternatives670); 

                    }

                     after(grammarAccess.getPieceTypeAccess().getQUEENEnumLiteralDeclaration_4()); 

                    }


                    }
                    break;
                case 6 :
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:343:6: ( ( 'King' ) )
                    {
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:343:6: ( ( 'King' ) )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:344:1: ( 'King' )
                    {
                     before(grammarAccess.getPieceTypeAccess().getKINGEnumLiteralDeclaration_5()); 
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:345:1: ( 'King' )
                    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:345:3: 'King'
                    {
                    match(input,23,FOLLOW_23_in_rule__PieceType__Alternatives691); 

                    }

                     after(grammarAccess.getPieceTypeAccess().getKINGEnumLiteralDeclaration_5()); 

                    }


                    }
                    break;

            }
        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PieceType__Alternatives"


    // $ANTLR start "rule__PSQT_Model__Group__0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:357:1: rule__PSQT_Model__Group__0 : rule__PSQT_Model__Group__0__Impl rule__PSQT_Model__Group__1 ;
    public final void rule__PSQT_Model__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:361:1: ( rule__PSQT_Model__Group__0__Impl rule__PSQT_Model__Group__1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:362:2: rule__PSQT_Model__Group__0__Impl rule__PSQT_Model__Group__1
            {
            pushFollow(FOLLOW_rule__PSQT_Model__Group__0__Impl_in_rule__PSQT_Model__Group__0724);
            rule__PSQT_Model__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__PSQT_Model__Group__1_in_rule__PSQT_Model__Group__0727);
            rule__PSQT_Model__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PSQT_Model__Group__0"


    // $ANTLR start "rule__PSQT_Model__Group__0__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:369:1: rule__PSQT_Model__Group__0__Impl : ( ruleFDescription ) ;
    public final void rule__PSQT_Model__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:373:1: ( ( ruleFDescription ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:374:1: ( ruleFDescription )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:374:1: ( ruleFDescription )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:375:1: ruleFDescription
            {
             before(grammarAccess.getPSQT_ModelAccess().getFDescriptionParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleFDescription_in_rule__PSQT_Model__Group__0__Impl754);
            ruleFDescription();

            state._fsp--;

             after(grammarAccess.getPSQT_ModelAccess().getFDescriptionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PSQT_Model__Group__0__Impl"


    // $ANTLR start "rule__PSQT_Model__Group__1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:386:1: rule__PSQT_Model__Group__1 : rule__PSQT_Model__Group__1__Impl ;
    public final void rule__PSQT_Model__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:390:1: ( rule__PSQT_Model__Group__1__Impl )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:391:2: rule__PSQT_Model__Group__1__Impl
            {
            pushFollow(FOLLOW_rule__PSQT_Model__Group__1__Impl_in_rule__PSQT_Model__Group__1783);
            rule__PSQT_Model__Group__1__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PSQT_Model__Group__1"


    // $ANTLR start "rule__PSQT_Model__Group__1__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:397:1: rule__PSQT_Model__Group__1__Impl : ( ( rule__PSQT_Model__TablesAssignment_1 )* ) ;
    public final void rule__PSQT_Model__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:401:1: ( ( ( rule__PSQT_Model__TablesAssignment_1 )* ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:402:1: ( ( rule__PSQT_Model__TablesAssignment_1 )* )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:402:1: ( ( rule__PSQT_Model__TablesAssignment_1 )* )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:403:1: ( rule__PSQT_Model__TablesAssignment_1 )*
            {
             before(grammarAccess.getPSQT_ModelAccess().getTablesAssignment_1()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:404:1: ( rule__PSQT_Model__TablesAssignment_1 )*
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( (LA2_0==RULE_PIECE) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:404:2: rule__PSQT_Model__TablesAssignment_1
            	    {
            	    pushFollow(FOLLOW_rule__PSQT_Model__TablesAssignment_1_in_rule__PSQT_Model__Group__1__Impl810);
            	    rule__PSQT_Model__TablesAssignment_1();

            	    state._fsp--;


            	    }
            	    break;

            	default :
            	    break loop2;
                }
            } while (true);

             after(grammarAccess.getPSQT_ModelAccess().getTablesAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PSQT_Model__Group__1__Impl"


    // $ANTLR start "rule__FDescription__Group__0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:418:1: rule__FDescription__Group__0 : rule__FDescription__Group__0__Impl rule__FDescription__Group__1 ;
    public final void rule__FDescription__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:422:1: ( rule__FDescription__Group__0__Impl rule__FDescription__Group__1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:423:2: rule__FDescription__Group__0__Impl rule__FDescription__Group__1
            {
            pushFollow(FOLLOW_rule__FDescription__Group__0__Impl_in_rule__FDescription__Group__0845);
            rule__FDescription__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FDescription__Group__1_in_rule__FDescription__Group__0848);
            rule__FDescription__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__0"


    // $ANTLR start "rule__FDescription__Group__0__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:430:1: rule__FDescription__Group__0__Impl : ( RULE_PSQT ) ;
    public final void rule__FDescription__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:434:1: ( ( RULE_PSQT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:435:1: ( RULE_PSQT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:435:1: ( RULE_PSQT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:436:1: RULE_PSQT
            {
             before(grammarAccess.getFDescriptionAccess().getPSQTTerminalRuleCall_0()); 
            match(input,RULE_PSQT,FOLLOW_RULE_PSQT_in_rule__FDescription__Group__0__Impl875); 
             after(grammarAccess.getFDescriptionAccess().getPSQTTerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__0__Impl"


    // $ANTLR start "rule__FDescription__Group__1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:447:1: rule__FDescription__Group__1 : rule__FDescription__Group__1__Impl rule__FDescription__Group__2 ;
    public final void rule__FDescription__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:451:1: ( rule__FDescription__Group__1__Impl rule__FDescription__Group__2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:452:2: rule__FDescription__Group__1__Impl rule__FDescription__Group__2
            {
            pushFollow(FOLLOW_rule__FDescription__Group__1__Impl_in_rule__FDescription__Group__1904);
            rule__FDescription__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FDescription__Group__2_in_rule__FDescription__Group__1907);
            rule__FDescription__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__1"


    // $ANTLR start "rule__FDescription__Group__1__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:459:1: rule__FDescription__Group__1__Impl : ( ( rule__FDescription__NameAssignment_1 ) ) ;
    public final void rule__FDescription__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:463:1: ( ( ( rule__FDescription__NameAssignment_1 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:464:1: ( ( rule__FDescription__NameAssignment_1 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:464:1: ( ( rule__FDescription__NameAssignment_1 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:465:1: ( rule__FDescription__NameAssignment_1 )
            {
             before(grammarAccess.getFDescriptionAccess().getNameAssignment_1()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:466:1: ( rule__FDescription__NameAssignment_1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:466:2: rule__FDescription__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__FDescription__NameAssignment_1_in_rule__FDescription__Group__1__Impl934);
            rule__FDescription__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getFDescriptionAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__1__Impl"


    // $ANTLR start "rule__FDescription__Group__2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:476:1: rule__FDescription__Group__2 : rule__FDescription__Group__2__Impl rule__FDescription__Group__3 ;
    public final void rule__FDescription__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:480:1: ( rule__FDescription__Group__2__Impl rule__FDescription__Group__3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:481:2: rule__FDescription__Group__2__Impl rule__FDescription__Group__3
            {
            pushFollow(FOLLOW_rule__FDescription__Group__2__Impl_in_rule__FDescription__Group__2964);
            rule__FDescription__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FDescription__Group__3_in_rule__FDescription__Group__2967);
            rule__FDescription__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__2"


    // $ANTLR start "rule__FDescription__Group__2__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:488:1: rule__FDescription__Group__2__Impl : ( RULE_BASE ) ;
    public final void rule__FDescription__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:492:1: ( ( RULE_BASE ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:493:1: ( RULE_BASE )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:493:1: ( RULE_BASE )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:494:1: RULE_BASE
            {
             before(grammarAccess.getFDescriptionAccess().getBASETerminalRuleCall_2()); 
            match(input,RULE_BASE,FOLLOW_RULE_BASE_in_rule__FDescription__Group__2__Impl994); 
             after(grammarAccess.getFDescriptionAccess().getBASETerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__2__Impl"


    // $ANTLR start "rule__FDescription__Group__3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:505:1: rule__FDescription__Group__3 : rule__FDescription__Group__3__Impl rule__FDescription__Group__4 ;
    public final void rule__FDescription__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:509:1: ( rule__FDescription__Group__3__Impl rule__FDescription__Group__4 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:510:2: rule__FDescription__Group__3__Impl rule__FDescription__Group__4
            {
            pushFollow(FOLLOW_rule__FDescription__Group__3__Impl_in_rule__FDescription__Group__31023);
            rule__FDescription__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FDescription__Group__4_in_rule__FDescription__Group__31026);
            rule__FDescription__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__3"


    // $ANTLR start "rule__FDescription__Group__3__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:517:1: rule__FDescription__Group__3__Impl : ( ( rule__FDescription__BaseAssignment_3 ) ) ;
    public final void rule__FDescription__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:521:1: ( ( ( rule__FDescription__BaseAssignment_3 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:522:1: ( ( rule__FDescription__BaseAssignment_3 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:522:1: ( ( rule__FDescription__BaseAssignment_3 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:523:1: ( rule__FDescription__BaseAssignment_3 )
            {
             before(grammarAccess.getFDescriptionAccess().getBaseAssignment_3()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:524:1: ( rule__FDescription__BaseAssignment_3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:524:2: rule__FDescription__BaseAssignment_3
            {
            pushFollow(FOLLOW_rule__FDescription__BaseAssignment_3_in_rule__FDescription__Group__3__Impl1053);
            rule__FDescription__BaseAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getFDescriptionAccess().getBaseAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__3__Impl"


    // $ANTLR start "rule__FDescription__Group__4"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:534:1: rule__FDescription__Group__4 : rule__FDescription__Group__4__Impl rule__FDescription__Group__5 ;
    public final void rule__FDescription__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:538:1: ( rule__FDescription__Group__4__Impl rule__FDescription__Group__5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:539:2: rule__FDescription__Group__4__Impl rule__FDescription__Group__5
            {
            pushFollow(FOLLOW_rule__FDescription__Group__4__Impl_in_rule__FDescription__Group__41083);
            rule__FDescription__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FDescription__Group__5_in_rule__FDescription__Group__41086);
            rule__FDescription__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__4"


    // $ANTLR start "rule__FDescription__Group__4__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:546:1: rule__FDescription__Group__4__Impl : ( RULE_MIDLIMIT ) ;
    public final void rule__FDescription__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:550:1: ( ( RULE_MIDLIMIT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:551:1: ( RULE_MIDLIMIT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:551:1: ( RULE_MIDLIMIT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:552:1: RULE_MIDLIMIT
            {
             before(grammarAccess.getFDescriptionAccess().getMIDLIMITTerminalRuleCall_4()); 
            match(input,RULE_MIDLIMIT,FOLLOW_RULE_MIDLIMIT_in_rule__FDescription__Group__4__Impl1113); 
             after(grammarAccess.getFDescriptionAccess().getMIDLIMITTerminalRuleCall_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__4__Impl"


    // $ANTLR start "rule__FDescription__Group__5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:563:1: rule__FDescription__Group__5 : rule__FDescription__Group__5__Impl rule__FDescription__Group__6 ;
    public final void rule__FDescription__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:567:1: ( rule__FDescription__Group__5__Impl rule__FDescription__Group__6 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:568:2: rule__FDescription__Group__5__Impl rule__FDescription__Group__6
            {
            pushFollow(FOLLOW_rule__FDescription__Group__5__Impl_in_rule__FDescription__Group__51142);
            rule__FDescription__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FDescription__Group__6_in_rule__FDescription__Group__51145);
            rule__FDescription__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__5"


    // $ANTLR start "rule__FDescription__Group__5__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:575:1: rule__FDescription__Group__5__Impl : ( ( rule__FDescription__MglAssignment_5 ) ) ;
    public final void rule__FDescription__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:579:1: ( ( ( rule__FDescription__MglAssignment_5 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:580:1: ( ( rule__FDescription__MglAssignment_5 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:580:1: ( ( rule__FDescription__MglAssignment_5 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:581:1: ( rule__FDescription__MglAssignment_5 )
            {
             before(grammarAccess.getFDescriptionAccess().getMglAssignment_5()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:582:1: ( rule__FDescription__MglAssignment_5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:582:2: rule__FDescription__MglAssignment_5
            {
            pushFollow(FOLLOW_rule__FDescription__MglAssignment_5_in_rule__FDescription__Group__5__Impl1172);
            rule__FDescription__MglAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getFDescriptionAccess().getMglAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__5__Impl"


    // $ANTLR start "rule__FDescription__Group__6"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:592:1: rule__FDescription__Group__6 : rule__FDescription__Group__6__Impl rule__FDescription__Group__7 ;
    public final void rule__FDescription__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:596:1: ( rule__FDescription__Group__6__Impl rule__FDescription__Group__7 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:597:2: rule__FDescription__Group__6__Impl rule__FDescription__Group__7
            {
            pushFollow(FOLLOW_rule__FDescription__Group__6__Impl_in_rule__FDescription__Group__61202);
            rule__FDescription__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__FDescription__Group__7_in_rule__FDescription__Group__61205);
            rule__FDescription__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__6"


    // $ANTLR start "rule__FDescription__Group__6__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:604:1: rule__FDescription__Group__6__Impl : ( RULE_ENDLIMIT ) ;
    public final void rule__FDescription__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:608:1: ( ( RULE_ENDLIMIT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:609:1: ( RULE_ENDLIMIT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:609:1: ( RULE_ENDLIMIT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:610:1: RULE_ENDLIMIT
            {
             before(grammarAccess.getFDescriptionAccess().getENDLIMITTerminalRuleCall_6()); 
            match(input,RULE_ENDLIMIT,FOLLOW_RULE_ENDLIMIT_in_rule__FDescription__Group__6__Impl1232); 
             after(grammarAccess.getFDescriptionAccess().getENDLIMITTerminalRuleCall_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__6__Impl"


    // $ANTLR start "rule__FDescription__Group__7"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:621:1: rule__FDescription__Group__7 : rule__FDescription__Group__7__Impl ;
    public final void rule__FDescription__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:625:1: ( rule__FDescription__Group__7__Impl )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:626:2: rule__FDescription__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__FDescription__Group__7__Impl_in_rule__FDescription__Group__71261);
            rule__FDescription__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__7"


    // $ANTLR start "rule__FDescription__Group__7__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:632:1: rule__FDescription__Group__7__Impl : ( ( rule__FDescription__EglAssignment_7 ) ) ;
    public final void rule__FDescription__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:636:1: ( ( ( rule__FDescription__EglAssignment_7 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:637:1: ( ( rule__FDescription__EglAssignment_7 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:637:1: ( ( rule__FDescription__EglAssignment_7 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:638:1: ( rule__FDescription__EglAssignment_7 )
            {
             before(grammarAccess.getFDescriptionAccess().getEglAssignment_7()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:639:1: ( rule__FDescription__EglAssignment_7 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:639:2: rule__FDescription__EglAssignment_7
            {
            pushFollow(FOLLOW_rule__FDescription__EglAssignment_7_in_rule__FDescription__Group__7__Impl1288);
            rule__FDescription__EglAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getFDescriptionAccess().getEglAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__Group__7__Impl"


    // $ANTLR start "rule__Table__Group__0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:665:1: rule__Table__Group__0 : rule__Table__Group__0__Impl rule__Table__Group__1 ;
    public final void rule__Table__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:669:1: ( rule__Table__Group__0__Impl rule__Table__Group__1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:670:2: rule__Table__Group__0__Impl rule__Table__Group__1
            {
            pushFollow(FOLLOW_rule__Table__Group__0__Impl_in_rule__Table__Group__01334);
            rule__Table__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__1_in_rule__Table__Group__01337);
            rule__Table__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__0"


    // $ANTLR start "rule__Table__Group__0__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:677:1: rule__Table__Group__0__Impl : ( ruleTDescription ) ;
    public final void rule__Table__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:681:1: ( ( ruleTDescription ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:682:1: ( ruleTDescription )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:682:1: ( ruleTDescription )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:683:1: ruleTDescription
            {
             before(grammarAccess.getTableAccess().getTDescriptionParserRuleCall_0()); 
            pushFollow(FOLLOW_ruleTDescription_in_rule__Table__Group__0__Impl1364);
            ruleTDescription();

            state._fsp--;

             after(grammarAccess.getTableAccess().getTDescriptionParserRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__0__Impl"


    // $ANTLR start "rule__Table__Group__1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:694:1: rule__Table__Group__1 : rule__Table__Group__1__Impl rule__Table__Group__2 ;
    public final void rule__Table__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:698:1: ( rule__Table__Group__1__Impl rule__Table__Group__2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:699:2: rule__Table__Group__1__Impl rule__Table__Group__2
            {
            pushFollow(FOLLOW_rule__Table__Group__1__Impl_in_rule__Table__Group__11393);
            rule__Table__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__2_in_rule__Table__Group__11396);
            rule__Table__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__1"


    // $ANTLR start "rule__Table__Group__1__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:706:1: rule__Table__Group__1__Impl : ( ruleTHeader ) ;
    public final void rule__Table__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:710:1: ( ( ruleTHeader ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:711:1: ( ruleTHeader )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:711:1: ( ruleTHeader )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:712:1: ruleTHeader
            {
             before(grammarAccess.getTableAccess().getTHeaderParserRuleCall_1()); 
            pushFollow(FOLLOW_ruleTHeader_in_rule__Table__Group__1__Impl1423);
            ruleTHeader();

            state._fsp--;

             after(grammarAccess.getTableAccess().getTHeaderParserRuleCall_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__1__Impl"


    // $ANTLR start "rule__Table__Group__2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:723:1: rule__Table__Group__2 : rule__Table__Group__2__Impl rule__Table__Group__3 ;
    public final void rule__Table__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:727:1: ( rule__Table__Group__2__Impl rule__Table__Group__3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:728:2: rule__Table__Group__2__Impl rule__Table__Group__3
            {
            pushFollow(FOLLOW_rule__Table__Group__2__Impl_in_rule__Table__Group__21452);
            rule__Table__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__3_in_rule__Table__Group__21455);
            rule__Table__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__2"


    // $ANTLR start "rule__Table__Group__2__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:735:1: rule__Table__Group__2__Impl : ( ( rule__Table__RAssignment_2 ) ) ;
    public final void rule__Table__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:739:1: ( ( ( rule__Table__RAssignment_2 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:740:1: ( ( rule__Table__RAssignment_2 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:740:1: ( ( rule__Table__RAssignment_2 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:741:1: ( rule__Table__RAssignment_2 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_2()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:742:1: ( rule__Table__RAssignment_2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:742:2: rule__Table__RAssignment_2
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_2_in_rule__Table__Group__2__Impl1482);
            rule__Table__RAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__2__Impl"


    // $ANTLR start "rule__Table__Group__3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:752:1: rule__Table__Group__3 : rule__Table__Group__3__Impl rule__Table__Group__4 ;
    public final void rule__Table__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:756:1: ( rule__Table__Group__3__Impl rule__Table__Group__4 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:757:2: rule__Table__Group__3__Impl rule__Table__Group__4
            {
            pushFollow(FOLLOW_rule__Table__Group__3__Impl_in_rule__Table__Group__31512);
            rule__Table__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__4_in_rule__Table__Group__31515);
            rule__Table__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__3"


    // $ANTLR start "rule__Table__Group__3__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:764:1: rule__Table__Group__3__Impl : ( ( rule__Table__RAssignment_3 ) ) ;
    public final void rule__Table__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:768:1: ( ( ( rule__Table__RAssignment_3 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:769:1: ( ( rule__Table__RAssignment_3 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:769:1: ( ( rule__Table__RAssignment_3 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:770:1: ( rule__Table__RAssignment_3 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_3()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:771:1: ( rule__Table__RAssignment_3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:771:2: rule__Table__RAssignment_3
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_3_in_rule__Table__Group__3__Impl1542);
            rule__Table__RAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__3__Impl"


    // $ANTLR start "rule__Table__Group__4"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:781:1: rule__Table__Group__4 : rule__Table__Group__4__Impl rule__Table__Group__5 ;
    public final void rule__Table__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:785:1: ( rule__Table__Group__4__Impl rule__Table__Group__5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:786:2: rule__Table__Group__4__Impl rule__Table__Group__5
            {
            pushFollow(FOLLOW_rule__Table__Group__4__Impl_in_rule__Table__Group__41572);
            rule__Table__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__5_in_rule__Table__Group__41575);
            rule__Table__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__4"


    // $ANTLR start "rule__Table__Group__4__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:793:1: rule__Table__Group__4__Impl : ( ( rule__Table__RAssignment_4 ) ) ;
    public final void rule__Table__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:797:1: ( ( ( rule__Table__RAssignment_4 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:798:1: ( ( rule__Table__RAssignment_4 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:798:1: ( ( rule__Table__RAssignment_4 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:799:1: ( rule__Table__RAssignment_4 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_4()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:800:1: ( rule__Table__RAssignment_4 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:800:2: rule__Table__RAssignment_4
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_4_in_rule__Table__Group__4__Impl1602);
            rule__Table__RAssignment_4();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__4__Impl"


    // $ANTLR start "rule__Table__Group__5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:810:1: rule__Table__Group__5 : rule__Table__Group__5__Impl rule__Table__Group__6 ;
    public final void rule__Table__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:814:1: ( rule__Table__Group__5__Impl rule__Table__Group__6 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:815:2: rule__Table__Group__5__Impl rule__Table__Group__6
            {
            pushFollow(FOLLOW_rule__Table__Group__5__Impl_in_rule__Table__Group__51632);
            rule__Table__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__6_in_rule__Table__Group__51635);
            rule__Table__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__5"


    // $ANTLR start "rule__Table__Group__5__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:822:1: rule__Table__Group__5__Impl : ( ( rule__Table__RAssignment_5 ) ) ;
    public final void rule__Table__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:826:1: ( ( ( rule__Table__RAssignment_5 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:827:1: ( ( rule__Table__RAssignment_5 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:827:1: ( ( rule__Table__RAssignment_5 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:828:1: ( rule__Table__RAssignment_5 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_5()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:829:1: ( rule__Table__RAssignment_5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:829:2: rule__Table__RAssignment_5
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_5_in_rule__Table__Group__5__Impl1662);
            rule__Table__RAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__5__Impl"


    // $ANTLR start "rule__Table__Group__6"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:839:1: rule__Table__Group__6 : rule__Table__Group__6__Impl rule__Table__Group__7 ;
    public final void rule__Table__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:843:1: ( rule__Table__Group__6__Impl rule__Table__Group__7 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:844:2: rule__Table__Group__6__Impl rule__Table__Group__7
            {
            pushFollow(FOLLOW_rule__Table__Group__6__Impl_in_rule__Table__Group__61692);
            rule__Table__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__7_in_rule__Table__Group__61695);
            rule__Table__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__6"


    // $ANTLR start "rule__Table__Group__6__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:851:1: rule__Table__Group__6__Impl : ( ( rule__Table__RAssignment_6 ) ) ;
    public final void rule__Table__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:855:1: ( ( ( rule__Table__RAssignment_6 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:856:1: ( ( rule__Table__RAssignment_6 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:856:1: ( ( rule__Table__RAssignment_6 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:857:1: ( rule__Table__RAssignment_6 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_6()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:858:1: ( rule__Table__RAssignment_6 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:858:2: rule__Table__RAssignment_6
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_6_in_rule__Table__Group__6__Impl1722);
            rule__Table__RAssignment_6();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__6__Impl"


    // $ANTLR start "rule__Table__Group__7"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:868:1: rule__Table__Group__7 : rule__Table__Group__7__Impl rule__Table__Group__8 ;
    public final void rule__Table__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:872:1: ( rule__Table__Group__7__Impl rule__Table__Group__8 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:873:2: rule__Table__Group__7__Impl rule__Table__Group__8
            {
            pushFollow(FOLLOW_rule__Table__Group__7__Impl_in_rule__Table__Group__71752);
            rule__Table__Group__7__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__8_in_rule__Table__Group__71755);
            rule__Table__Group__8();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__7"


    // $ANTLR start "rule__Table__Group__7__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:880:1: rule__Table__Group__7__Impl : ( ( rule__Table__RAssignment_7 ) ) ;
    public final void rule__Table__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:884:1: ( ( ( rule__Table__RAssignment_7 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:885:1: ( ( rule__Table__RAssignment_7 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:885:1: ( ( rule__Table__RAssignment_7 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:886:1: ( rule__Table__RAssignment_7 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_7()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:887:1: ( rule__Table__RAssignment_7 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:887:2: rule__Table__RAssignment_7
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_7_in_rule__Table__Group__7__Impl1782);
            rule__Table__RAssignment_7();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__7__Impl"


    // $ANTLR start "rule__Table__Group__8"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:897:1: rule__Table__Group__8 : rule__Table__Group__8__Impl rule__Table__Group__9 ;
    public final void rule__Table__Group__8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:901:1: ( rule__Table__Group__8__Impl rule__Table__Group__9 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:902:2: rule__Table__Group__8__Impl rule__Table__Group__9
            {
            pushFollow(FOLLOW_rule__Table__Group__8__Impl_in_rule__Table__Group__81812);
            rule__Table__Group__8__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Table__Group__9_in_rule__Table__Group__81815);
            rule__Table__Group__9();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__8"


    // $ANTLR start "rule__Table__Group__8__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:909:1: rule__Table__Group__8__Impl : ( ( rule__Table__RAssignment_8 ) ) ;
    public final void rule__Table__Group__8__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:913:1: ( ( ( rule__Table__RAssignment_8 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:914:1: ( ( rule__Table__RAssignment_8 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:914:1: ( ( rule__Table__RAssignment_8 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:915:1: ( rule__Table__RAssignment_8 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_8()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:916:1: ( rule__Table__RAssignment_8 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:916:2: rule__Table__RAssignment_8
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_8_in_rule__Table__Group__8__Impl1842);
            rule__Table__RAssignment_8();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_8()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__8__Impl"


    // $ANTLR start "rule__Table__Group__9"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:926:1: rule__Table__Group__9 : rule__Table__Group__9__Impl ;
    public final void rule__Table__Group__9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:930:1: ( rule__Table__Group__9__Impl )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:931:2: rule__Table__Group__9__Impl
            {
            pushFollow(FOLLOW_rule__Table__Group__9__Impl_in_rule__Table__Group__91872);
            rule__Table__Group__9__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__9"


    // $ANTLR start "rule__Table__Group__9__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:937:1: rule__Table__Group__9__Impl : ( ( rule__Table__RAssignment_9 ) ) ;
    public final void rule__Table__Group__9__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:941:1: ( ( ( rule__Table__RAssignment_9 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:942:1: ( ( rule__Table__RAssignment_9 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:942:1: ( ( rule__Table__RAssignment_9 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:943:1: ( rule__Table__RAssignment_9 )
            {
             before(grammarAccess.getTableAccess().getRAssignment_9()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:944:1: ( rule__Table__RAssignment_9 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:944:2: rule__Table__RAssignment_9
            {
            pushFollow(FOLLOW_rule__Table__RAssignment_9_in_rule__Table__Group__9__Impl1899);
            rule__Table__RAssignment_9();

            state._fsp--;


            }

             after(grammarAccess.getTableAccess().getRAssignment_9()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__Group__9__Impl"


    // $ANTLR start "rule__TDescription__Group__0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:974:1: rule__TDescription__Group__0 : rule__TDescription__Group__0__Impl rule__TDescription__Group__1 ;
    public final void rule__TDescription__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:978:1: ( rule__TDescription__Group__0__Impl rule__TDescription__Group__1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:979:2: rule__TDescription__Group__0__Impl rule__TDescription__Group__1
            {
            pushFollow(FOLLOW_rule__TDescription__Group__0__Impl_in_rule__TDescription__Group__01949);
            rule__TDescription__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TDescription__Group__1_in_rule__TDescription__Group__01952);
            rule__TDescription__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__0"


    // $ANTLR start "rule__TDescription__Group__0__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:986:1: rule__TDescription__Group__0__Impl : ( RULE_PIECE ) ;
    public final void rule__TDescription__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:990:1: ( ( RULE_PIECE ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:991:1: ( RULE_PIECE )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:991:1: ( RULE_PIECE )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:992:1: RULE_PIECE
            {
             before(grammarAccess.getTDescriptionAccess().getPIECETerminalRuleCall_0()); 
            match(input,RULE_PIECE,FOLLOW_RULE_PIECE_in_rule__TDescription__Group__0__Impl1979); 
             after(grammarAccess.getTDescriptionAccess().getPIECETerminalRuleCall_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__0__Impl"


    // $ANTLR start "rule__TDescription__Group__1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1003:1: rule__TDescription__Group__1 : rule__TDescription__Group__1__Impl rule__TDescription__Group__2 ;
    public final void rule__TDescription__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1007:1: ( rule__TDescription__Group__1__Impl rule__TDescription__Group__2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1008:2: rule__TDescription__Group__1__Impl rule__TDescription__Group__2
            {
            pushFollow(FOLLOW_rule__TDescription__Group__1__Impl_in_rule__TDescription__Group__12008);
            rule__TDescription__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TDescription__Group__2_in_rule__TDescription__Group__12011);
            rule__TDescription__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__1"


    // $ANTLR start "rule__TDescription__Group__1__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1015:1: rule__TDescription__Group__1__Impl : ( ( rule__TDescription__NameAssignment_1 ) ) ;
    public final void rule__TDescription__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1019:1: ( ( ( rule__TDescription__NameAssignment_1 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1020:1: ( ( rule__TDescription__NameAssignment_1 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1020:1: ( ( rule__TDescription__NameAssignment_1 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1021:1: ( rule__TDescription__NameAssignment_1 )
            {
             before(grammarAccess.getTDescriptionAccess().getNameAssignment_1()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1022:1: ( rule__TDescription__NameAssignment_1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1022:2: rule__TDescription__NameAssignment_1
            {
            pushFollow(FOLLOW_rule__TDescription__NameAssignment_1_in_rule__TDescription__Group__1__Impl2038);
            rule__TDescription__NameAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getTDescriptionAccess().getNameAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__1__Impl"


    // $ANTLR start "rule__TDescription__Group__2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1032:1: rule__TDescription__Group__2 : rule__TDescription__Group__2__Impl rule__TDescription__Group__3 ;
    public final void rule__TDescription__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1036:1: ( rule__TDescription__Group__2__Impl rule__TDescription__Group__3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1037:2: rule__TDescription__Group__2__Impl rule__TDescription__Group__3
            {
            pushFollow(FOLLOW_rule__TDescription__Group__2__Impl_in_rule__TDescription__Group__22068);
            rule__TDescription__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TDescription__Group__3_in_rule__TDescription__Group__22071);
            rule__TDescription__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__2"


    // $ANTLR start "rule__TDescription__Group__2__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1044:1: rule__TDescription__Group__2__Impl : ( RULE_MIDGAME ) ;
    public final void rule__TDescription__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1048:1: ( ( RULE_MIDGAME ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1049:1: ( RULE_MIDGAME )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1049:1: ( RULE_MIDGAME )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1050:1: RULE_MIDGAME
            {
             before(grammarAccess.getTDescriptionAccess().getMIDGAMETerminalRuleCall_2()); 
            match(input,RULE_MIDGAME,FOLLOW_RULE_MIDGAME_in_rule__TDescription__Group__2__Impl2098); 
             after(grammarAccess.getTDescriptionAccess().getMIDGAMETerminalRuleCall_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__2__Impl"


    // $ANTLR start "rule__TDescription__Group__3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1061:1: rule__TDescription__Group__3 : rule__TDescription__Group__3__Impl rule__TDescription__Group__4 ;
    public final void rule__TDescription__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1065:1: ( rule__TDescription__Group__3__Impl rule__TDescription__Group__4 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1066:2: rule__TDescription__Group__3__Impl rule__TDescription__Group__4
            {
            pushFollow(FOLLOW_rule__TDescription__Group__3__Impl_in_rule__TDescription__Group__32127);
            rule__TDescription__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TDescription__Group__4_in_rule__TDescription__Group__32130);
            rule__TDescription__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__3"


    // $ANTLR start "rule__TDescription__Group__3__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1073:1: rule__TDescription__Group__3__Impl : ( ( rule__TDescription__MgAssignment_3 ) ) ;
    public final void rule__TDescription__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1077:1: ( ( ( rule__TDescription__MgAssignment_3 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1078:1: ( ( rule__TDescription__MgAssignment_3 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1078:1: ( ( rule__TDescription__MgAssignment_3 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1079:1: ( rule__TDescription__MgAssignment_3 )
            {
             before(grammarAccess.getTDescriptionAccess().getMgAssignment_3()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1080:1: ( rule__TDescription__MgAssignment_3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1080:2: rule__TDescription__MgAssignment_3
            {
            pushFollow(FOLLOW_rule__TDescription__MgAssignment_3_in_rule__TDescription__Group__3__Impl2157);
            rule__TDescription__MgAssignment_3();

            state._fsp--;


            }

             after(grammarAccess.getTDescriptionAccess().getMgAssignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__3__Impl"


    // $ANTLR start "rule__TDescription__Group__4"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1090:1: rule__TDescription__Group__4 : rule__TDescription__Group__4__Impl rule__TDescription__Group__5 ;
    public final void rule__TDescription__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1094:1: ( rule__TDescription__Group__4__Impl rule__TDescription__Group__5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1095:2: rule__TDescription__Group__4__Impl rule__TDescription__Group__5
            {
            pushFollow(FOLLOW_rule__TDescription__Group__4__Impl_in_rule__TDescription__Group__42187);
            rule__TDescription__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__TDescription__Group__5_in_rule__TDescription__Group__42190);
            rule__TDescription__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__4"


    // $ANTLR start "rule__TDescription__Group__4__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1102:1: rule__TDescription__Group__4__Impl : ( RULE_ENDGAME ) ;
    public final void rule__TDescription__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1106:1: ( ( RULE_ENDGAME ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1107:1: ( RULE_ENDGAME )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1107:1: ( RULE_ENDGAME )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1108:1: RULE_ENDGAME
            {
             before(grammarAccess.getTDescriptionAccess().getENDGAMETerminalRuleCall_4()); 
            match(input,RULE_ENDGAME,FOLLOW_RULE_ENDGAME_in_rule__TDescription__Group__4__Impl2217); 
             after(grammarAccess.getTDescriptionAccess().getENDGAMETerminalRuleCall_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__4__Impl"


    // $ANTLR start "rule__TDescription__Group__5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1119:1: rule__TDescription__Group__5 : rule__TDescription__Group__5__Impl ;
    public final void rule__TDescription__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1123:1: ( rule__TDescription__Group__5__Impl )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1124:2: rule__TDescription__Group__5__Impl
            {
            pushFollow(FOLLOW_rule__TDescription__Group__5__Impl_in_rule__TDescription__Group__52246);
            rule__TDescription__Group__5__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__5"


    // $ANTLR start "rule__TDescription__Group__5__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1130:1: rule__TDescription__Group__5__Impl : ( ( rule__TDescription__EgAssignment_5 ) ) ;
    public final void rule__TDescription__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1134:1: ( ( ( rule__TDescription__EgAssignment_5 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1135:1: ( ( rule__TDescription__EgAssignment_5 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1135:1: ( ( rule__TDescription__EgAssignment_5 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1136:1: ( rule__TDescription__EgAssignment_5 )
            {
             before(grammarAccess.getTDescriptionAccess().getEgAssignment_5()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1137:1: ( rule__TDescription__EgAssignment_5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1137:2: rule__TDescription__EgAssignment_5
            {
            pushFollow(FOLLOW_rule__TDescription__EgAssignment_5_in_rule__TDescription__Group__5__Impl2273);
            rule__TDescription__EgAssignment_5();

            state._fsp--;


            }

             after(grammarAccess.getTDescriptionAccess().getEgAssignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__Group__5__Impl"


    // $ANTLR start "rule__THeader__Group__0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1159:1: rule__THeader__Group__0 : rule__THeader__Group__0__Impl rule__THeader__Group__1 ;
    public final void rule__THeader__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1163:1: ( rule__THeader__Group__0__Impl rule__THeader__Group__1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1164:2: rule__THeader__Group__0__Impl rule__THeader__Group__1
            {
            pushFollow(FOLLOW_rule__THeader__Group__0__Impl_in_rule__THeader__Group__02315);
            rule__THeader__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__THeader__Group__1_in_rule__THeader__Group__02318);
            rule__THeader__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__0"


    // $ANTLR start "rule__THeader__Group__0__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1171:1: rule__THeader__Group__0__Impl : ( 'A' ) ;
    public final void rule__THeader__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1175:1: ( ( 'A' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1176:1: ( 'A' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1176:1: ( 'A' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1177:1: 'A'
            {
             before(grammarAccess.getTHeaderAccess().getAKeyword_0()); 
            match(input,24,FOLLOW_24_in_rule__THeader__Group__0__Impl2346); 
             after(grammarAccess.getTHeaderAccess().getAKeyword_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__0__Impl"


    // $ANTLR start "rule__THeader__Group__1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1190:1: rule__THeader__Group__1 : rule__THeader__Group__1__Impl rule__THeader__Group__2 ;
    public final void rule__THeader__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1194:1: ( rule__THeader__Group__1__Impl rule__THeader__Group__2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1195:2: rule__THeader__Group__1__Impl rule__THeader__Group__2
            {
            pushFollow(FOLLOW_rule__THeader__Group__1__Impl_in_rule__THeader__Group__12377);
            rule__THeader__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__THeader__Group__2_in_rule__THeader__Group__12380);
            rule__THeader__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__1"


    // $ANTLR start "rule__THeader__Group__1__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1202:1: rule__THeader__Group__1__Impl : ( 'B' ) ;
    public final void rule__THeader__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1206:1: ( ( 'B' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1207:1: ( 'B' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1207:1: ( 'B' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1208:1: 'B'
            {
             before(grammarAccess.getTHeaderAccess().getBKeyword_1()); 
            match(input,25,FOLLOW_25_in_rule__THeader__Group__1__Impl2408); 
             after(grammarAccess.getTHeaderAccess().getBKeyword_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__1__Impl"


    // $ANTLR start "rule__THeader__Group__2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1221:1: rule__THeader__Group__2 : rule__THeader__Group__2__Impl rule__THeader__Group__3 ;
    public final void rule__THeader__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1225:1: ( rule__THeader__Group__2__Impl rule__THeader__Group__3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1226:2: rule__THeader__Group__2__Impl rule__THeader__Group__3
            {
            pushFollow(FOLLOW_rule__THeader__Group__2__Impl_in_rule__THeader__Group__22439);
            rule__THeader__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__THeader__Group__3_in_rule__THeader__Group__22442);
            rule__THeader__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__2"


    // $ANTLR start "rule__THeader__Group__2__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1233:1: rule__THeader__Group__2__Impl : ( 'C' ) ;
    public final void rule__THeader__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1237:1: ( ( 'C' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1238:1: ( 'C' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1238:1: ( 'C' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1239:1: 'C'
            {
             before(grammarAccess.getTHeaderAccess().getCKeyword_2()); 
            match(input,26,FOLLOW_26_in_rule__THeader__Group__2__Impl2470); 
             after(grammarAccess.getTHeaderAccess().getCKeyword_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__2__Impl"


    // $ANTLR start "rule__THeader__Group__3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1252:1: rule__THeader__Group__3 : rule__THeader__Group__3__Impl rule__THeader__Group__4 ;
    public final void rule__THeader__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1256:1: ( rule__THeader__Group__3__Impl rule__THeader__Group__4 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1257:2: rule__THeader__Group__3__Impl rule__THeader__Group__4
            {
            pushFollow(FOLLOW_rule__THeader__Group__3__Impl_in_rule__THeader__Group__32501);
            rule__THeader__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__THeader__Group__4_in_rule__THeader__Group__32504);
            rule__THeader__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__3"


    // $ANTLR start "rule__THeader__Group__3__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1264:1: rule__THeader__Group__3__Impl : ( 'D' ) ;
    public final void rule__THeader__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1268:1: ( ( 'D' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1269:1: ( 'D' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1269:1: ( 'D' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1270:1: 'D'
            {
             before(grammarAccess.getTHeaderAccess().getDKeyword_3()); 
            match(input,27,FOLLOW_27_in_rule__THeader__Group__3__Impl2532); 
             after(grammarAccess.getTHeaderAccess().getDKeyword_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__3__Impl"


    // $ANTLR start "rule__THeader__Group__4"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1283:1: rule__THeader__Group__4 : rule__THeader__Group__4__Impl rule__THeader__Group__5 ;
    public final void rule__THeader__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1287:1: ( rule__THeader__Group__4__Impl rule__THeader__Group__5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1288:2: rule__THeader__Group__4__Impl rule__THeader__Group__5
            {
            pushFollow(FOLLOW_rule__THeader__Group__4__Impl_in_rule__THeader__Group__42563);
            rule__THeader__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__THeader__Group__5_in_rule__THeader__Group__42566);
            rule__THeader__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__4"


    // $ANTLR start "rule__THeader__Group__4__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1295:1: rule__THeader__Group__4__Impl : ( 'E' ) ;
    public final void rule__THeader__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1299:1: ( ( 'E' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1300:1: ( 'E' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1300:1: ( 'E' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1301:1: 'E'
            {
             before(grammarAccess.getTHeaderAccess().getEKeyword_4()); 
            match(input,28,FOLLOW_28_in_rule__THeader__Group__4__Impl2594); 
             after(grammarAccess.getTHeaderAccess().getEKeyword_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__4__Impl"


    // $ANTLR start "rule__THeader__Group__5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1314:1: rule__THeader__Group__5 : rule__THeader__Group__5__Impl rule__THeader__Group__6 ;
    public final void rule__THeader__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1318:1: ( rule__THeader__Group__5__Impl rule__THeader__Group__6 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1319:2: rule__THeader__Group__5__Impl rule__THeader__Group__6
            {
            pushFollow(FOLLOW_rule__THeader__Group__5__Impl_in_rule__THeader__Group__52625);
            rule__THeader__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__THeader__Group__6_in_rule__THeader__Group__52628);
            rule__THeader__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__5"


    // $ANTLR start "rule__THeader__Group__5__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1326:1: rule__THeader__Group__5__Impl : ( 'F' ) ;
    public final void rule__THeader__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1330:1: ( ( 'F' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1331:1: ( 'F' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1331:1: ( 'F' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1332:1: 'F'
            {
             before(grammarAccess.getTHeaderAccess().getFKeyword_5()); 
            match(input,29,FOLLOW_29_in_rule__THeader__Group__5__Impl2656); 
             after(grammarAccess.getTHeaderAccess().getFKeyword_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__5__Impl"


    // $ANTLR start "rule__THeader__Group__6"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1345:1: rule__THeader__Group__6 : rule__THeader__Group__6__Impl rule__THeader__Group__7 ;
    public final void rule__THeader__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1349:1: ( rule__THeader__Group__6__Impl rule__THeader__Group__7 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1350:2: rule__THeader__Group__6__Impl rule__THeader__Group__7
            {
            pushFollow(FOLLOW_rule__THeader__Group__6__Impl_in_rule__THeader__Group__62687);
            rule__THeader__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__THeader__Group__7_in_rule__THeader__Group__62690);
            rule__THeader__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__6"


    // $ANTLR start "rule__THeader__Group__6__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1357:1: rule__THeader__Group__6__Impl : ( 'G' ) ;
    public final void rule__THeader__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1361:1: ( ( 'G' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1362:1: ( 'G' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1362:1: ( 'G' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1363:1: 'G'
            {
             before(grammarAccess.getTHeaderAccess().getGKeyword_6()); 
            match(input,30,FOLLOW_30_in_rule__THeader__Group__6__Impl2718); 
             after(grammarAccess.getTHeaderAccess().getGKeyword_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__6__Impl"


    // $ANTLR start "rule__THeader__Group__7"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1376:1: rule__THeader__Group__7 : rule__THeader__Group__7__Impl ;
    public final void rule__THeader__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1380:1: ( rule__THeader__Group__7__Impl )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1381:2: rule__THeader__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__THeader__Group__7__Impl_in_rule__THeader__Group__72749);
            rule__THeader__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__7"


    // $ANTLR start "rule__THeader__Group__7__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1387:1: rule__THeader__Group__7__Impl : ( 'H' ) ;
    public final void rule__THeader__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1391:1: ( ( 'H' ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1392:1: ( 'H' )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1392:1: ( 'H' )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1393:1: 'H'
            {
             before(grammarAccess.getTHeaderAccess().getHKeyword_7()); 
            match(input,31,FOLLOW_31_in_rule__THeader__Group__7__Impl2777); 
             after(grammarAccess.getTHeaderAccess().getHKeyword_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__THeader__Group__7__Impl"


    // $ANTLR start "rule__Row__Group__0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1422:1: rule__Row__Group__0 : rule__Row__Group__0__Impl rule__Row__Group__1 ;
    public final void rule__Row__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1426:1: ( rule__Row__Group__0__Impl rule__Row__Group__1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1427:2: rule__Row__Group__0__Impl rule__Row__Group__1
            {
            pushFollow(FOLLOW_rule__Row__Group__0__Impl_in_rule__Row__Group__02824);
            rule__Row__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Row__Group__1_in_rule__Row__Group__02827);
            rule__Row__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__Group__0"


    // $ANTLR start "rule__Row__Group__0__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1434:1: rule__Row__Group__0__Impl : ( ( rule__Row__NameAssignment_0 ) ) ;
    public final void rule__Row__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1438:1: ( ( ( rule__Row__NameAssignment_0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1439:1: ( ( rule__Row__NameAssignment_0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1439:1: ( ( rule__Row__NameAssignment_0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1440:1: ( rule__Row__NameAssignment_0 )
            {
             before(grammarAccess.getRowAccess().getNameAssignment_0()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1441:1: ( rule__Row__NameAssignment_0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1441:2: rule__Row__NameAssignment_0
            {
            pushFollow(FOLLOW_rule__Row__NameAssignment_0_in_rule__Row__Group__0__Impl2854);
            rule__Row__NameAssignment_0();

            state._fsp--;


            }

             after(grammarAccess.getRowAccess().getNameAssignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__Group__0__Impl"


    // $ANTLR start "rule__Row__Group__1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1451:1: rule__Row__Group__1 : rule__Row__Group__1__Impl rule__Row__Group__2 ;
    public final void rule__Row__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1455:1: ( rule__Row__Group__1__Impl rule__Row__Group__2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1456:2: rule__Row__Group__1__Impl rule__Row__Group__2
            {
            pushFollow(FOLLOW_rule__Row__Group__1__Impl_in_rule__Row__Group__12884);
            rule__Row__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__Row__Group__2_in_rule__Row__Group__12887);
            rule__Row__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__Group__1"


    // $ANTLR start "rule__Row__Group__1__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1463:1: rule__Row__Group__1__Impl : ( ( rule__Row__MidrowAssignment_1 ) ) ;
    public final void rule__Row__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1467:1: ( ( ( rule__Row__MidrowAssignment_1 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1468:1: ( ( rule__Row__MidrowAssignment_1 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1468:1: ( ( rule__Row__MidrowAssignment_1 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1469:1: ( rule__Row__MidrowAssignment_1 )
            {
             before(grammarAccess.getRowAccess().getMidrowAssignment_1()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1470:1: ( rule__Row__MidrowAssignment_1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1470:2: rule__Row__MidrowAssignment_1
            {
            pushFollow(FOLLOW_rule__Row__MidrowAssignment_1_in_rule__Row__Group__1__Impl2914);
            rule__Row__MidrowAssignment_1();

            state._fsp--;


            }

             after(grammarAccess.getRowAccess().getMidrowAssignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__Group__1__Impl"


    // $ANTLR start "rule__Row__Group__2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1480:1: rule__Row__Group__2 : rule__Row__Group__2__Impl ;
    public final void rule__Row__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1484:1: ( rule__Row__Group__2__Impl )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1485:2: rule__Row__Group__2__Impl
            {
            pushFollow(FOLLOW_rule__Row__Group__2__Impl_in_rule__Row__Group__22944);
            rule__Row__Group__2__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__Group__2"


    // $ANTLR start "rule__Row__Group__2__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1491:1: rule__Row__Group__2__Impl : ( ( rule__Row__EndrowAssignment_2 ) ) ;
    public final void rule__Row__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1495:1: ( ( ( rule__Row__EndrowAssignment_2 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1496:1: ( ( rule__Row__EndrowAssignment_2 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1496:1: ( ( rule__Row__EndrowAssignment_2 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1497:1: ( rule__Row__EndrowAssignment_2 )
            {
             before(grammarAccess.getRowAccess().getEndrowAssignment_2()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1498:1: ( rule__Row__EndrowAssignment_2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1498:2: rule__Row__EndrowAssignment_2
            {
            pushFollow(FOLLOW_rule__Row__EndrowAssignment_2_in_rule__Row__Group__2__Impl2971);
            rule__Row__EndrowAssignment_2();

            state._fsp--;


            }

             after(grammarAccess.getRowAccess().getEndrowAssignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__Group__2__Impl"


    // $ANTLR start "rule__MRow__Group__0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1514:1: rule__MRow__Group__0 : rule__MRow__Group__0__Impl rule__MRow__Group__1 ;
    public final void rule__MRow__Group__0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1518:1: ( rule__MRow__Group__0__Impl rule__MRow__Group__1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1519:2: rule__MRow__Group__0__Impl rule__MRow__Group__1
            {
            pushFollow(FOLLOW_rule__MRow__Group__0__Impl_in_rule__MRow__Group__03007);
            rule__MRow__Group__0__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MRow__Group__1_in_rule__MRow__Group__03010);
            rule__MRow__Group__1();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__0"


    // $ANTLR start "rule__MRow__Group__0__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1526:1: rule__MRow__Group__0__Impl : ( ( rule__MRow__C1Assignment_0 ) ) ;
    public final void rule__MRow__Group__0__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1530:1: ( ( ( rule__MRow__C1Assignment_0 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1531:1: ( ( rule__MRow__C1Assignment_0 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1531:1: ( ( rule__MRow__C1Assignment_0 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1532:1: ( rule__MRow__C1Assignment_0 )
            {
             before(grammarAccess.getMRowAccess().getC1Assignment_0()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1533:1: ( rule__MRow__C1Assignment_0 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1533:2: rule__MRow__C1Assignment_0
            {
            pushFollow(FOLLOW_rule__MRow__C1Assignment_0_in_rule__MRow__Group__0__Impl3037);
            rule__MRow__C1Assignment_0();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC1Assignment_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__0__Impl"


    // $ANTLR start "rule__MRow__Group__1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1543:1: rule__MRow__Group__1 : rule__MRow__Group__1__Impl rule__MRow__Group__2 ;
    public final void rule__MRow__Group__1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1547:1: ( rule__MRow__Group__1__Impl rule__MRow__Group__2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1548:2: rule__MRow__Group__1__Impl rule__MRow__Group__2
            {
            pushFollow(FOLLOW_rule__MRow__Group__1__Impl_in_rule__MRow__Group__13067);
            rule__MRow__Group__1__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MRow__Group__2_in_rule__MRow__Group__13070);
            rule__MRow__Group__2();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__1"


    // $ANTLR start "rule__MRow__Group__1__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1555:1: rule__MRow__Group__1__Impl : ( ( rule__MRow__C2Assignment_1 ) ) ;
    public final void rule__MRow__Group__1__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1559:1: ( ( ( rule__MRow__C2Assignment_1 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1560:1: ( ( rule__MRow__C2Assignment_1 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1560:1: ( ( rule__MRow__C2Assignment_1 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1561:1: ( rule__MRow__C2Assignment_1 )
            {
             before(grammarAccess.getMRowAccess().getC2Assignment_1()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1562:1: ( rule__MRow__C2Assignment_1 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1562:2: rule__MRow__C2Assignment_1
            {
            pushFollow(FOLLOW_rule__MRow__C2Assignment_1_in_rule__MRow__Group__1__Impl3097);
            rule__MRow__C2Assignment_1();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC2Assignment_1()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__1__Impl"


    // $ANTLR start "rule__MRow__Group__2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1572:1: rule__MRow__Group__2 : rule__MRow__Group__2__Impl rule__MRow__Group__3 ;
    public final void rule__MRow__Group__2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1576:1: ( rule__MRow__Group__2__Impl rule__MRow__Group__3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1577:2: rule__MRow__Group__2__Impl rule__MRow__Group__3
            {
            pushFollow(FOLLOW_rule__MRow__Group__2__Impl_in_rule__MRow__Group__23127);
            rule__MRow__Group__2__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MRow__Group__3_in_rule__MRow__Group__23130);
            rule__MRow__Group__3();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__2"


    // $ANTLR start "rule__MRow__Group__2__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1584:1: rule__MRow__Group__2__Impl : ( ( rule__MRow__C3Assignment_2 ) ) ;
    public final void rule__MRow__Group__2__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1588:1: ( ( ( rule__MRow__C3Assignment_2 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1589:1: ( ( rule__MRow__C3Assignment_2 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1589:1: ( ( rule__MRow__C3Assignment_2 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1590:1: ( rule__MRow__C3Assignment_2 )
            {
             before(grammarAccess.getMRowAccess().getC3Assignment_2()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1591:1: ( rule__MRow__C3Assignment_2 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1591:2: rule__MRow__C3Assignment_2
            {
            pushFollow(FOLLOW_rule__MRow__C3Assignment_2_in_rule__MRow__Group__2__Impl3157);
            rule__MRow__C3Assignment_2();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC3Assignment_2()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__2__Impl"


    // $ANTLR start "rule__MRow__Group__3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1601:1: rule__MRow__Group__3 : rule__MRow__Group__3__Impl rule__MRow__Group__4 ;
    public final void rule__MRow__Group__3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1605:1: ( rule__MRow__Group__3__Impl rule__MRow__Group__4 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1606:2: rule__MRow__Group__3__Impl rule__MRow__Group__4
            {
            pushFollow(FOLLOW_rule__MRow__Group__3__Impl_in_rule__MRow__Group__33187);
            rule__MRow__Group__3__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MRow__Group__4_in_rule__MRow__Group__33190);
            rule__MRow__Group__4();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__3"


    // $ANTLR start "rule__MRow__Group__3__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1613:1: rule__MRow__Group__3__Impl : ( ( rule__MRow__C4Assignment_3 ) ) ;
    public final void rule__MRow__Group__3__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1617:1: ( ( ( rule__MRow__C4Assignment_3 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1618:1: ( ( rule__MRow__C4Assignment_3 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1618:1: ( ( rule__MRow__C4Assignment_3 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1619:1: ( rule__MRow__C4Assignment_3 )
            {
             before(grammarAccess.getMRowAccess().getC4Assignment_3()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1620:1: ( rule__MRow__C4Assignment_3 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1620:2: rule__MRow__C4Assignment_3
            {
            pushFollow(FOLLOW_rule__MRow__C4Assignment_3_in_rule__MRow__Group__3__Impl3217);
            rule__MRow__C4Assignment_3();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC4Assignment_3()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__3__Impl"


    // $ANTLR start "rule__MRow__Group__4"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1630:1: rule__MRow__Group__4 : rule__MRow__Group__4__Impl rule__MRow__Group__5 ;
    public final void rule__MRow__Group__4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1634:1: ( rule__MRow__Group__4__Impl rule__MRow__Group__5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1635:2: rule__MRow__Group__4__Impl rule__MRow__Group__5
            {
            pushFollow(FOLLOW_rule__MRow__Group__4__Impl_in_rule__MRow__Group__43247);
            rule__MRow__Group__4__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MRow__Group__5_in_rule__MRow__Group__43250);
            rule__MRow__Group__5();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__4"


    // $ANTLR start "rule__MRow__Group__4__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1642:1: rule__MRow__Group__4__Impl : ( ( rule__MRow__C5Assignment_4 ) ) ;
    public final void rule__MRow__Group__4__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1646:1: ( ( ( rule__MRow__C5Assignment_4 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1647:1: ( ( rule__MRow__C5Assignment_4 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1647:1: ( ( rule__MRow__C5Assignment_4 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1648:1: ( rule__MRow__C5Assignment_4 )
            {
             before(grammarAccess.getMRowAccess().getC5Assignment_4()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1649:1: ( rule__MRow__C5Assignment_4 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1649:2: rule__MRow__C5Assignment_4
            {
            pushFollow(FOLLOW_rule__MRow__C5Assignment_4_in_rule__MRow__Group__4__Impl3277);
            rule__MRow__C5Assignment_4();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC5Assignment_4()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__4__Impl"


    // $ANTLR start "rule__MRow__Group__5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1659:1: rule__MRow__Group__5 : rule__MRow__Group__5__Impl rule__MRow__Group__6 ;
    public final void rule__MRow__Group__5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1663:1: ( rule__MRow__Group__5__Impl rule__MRow__Group__6 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1664:2: rule__MRow__Group__5__Impl rule__MRow__Group__6
            {
            pushFollow(FOLLOW_rule__MRow__Group__5__Impl_in_rule__MRow__Group__53307);
            rule__MRow__Group__5__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MRow__Group__6_in_rule__MRow__Group__53310);
            rule__MRow__Group__6();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__5"


    // $ANTLR start "rule__MRow__Group__5__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1671:1: rule__MRow__Group__5__Impl : ( ( rule__MRow__C6Assignment_5 ) ) ;
    public final void rule__MRow__Group__5__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1675:1: ( ( ( rule__MRow__C6Assignment_5 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1676:1: ( ( rule__MRow__C6Assignment_5 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1676:1: ( ( rule__MRow__C6Assignment_5 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1677:1: ( rule__MRow__C6Assignment_5 )
            {
             before(grammarAccess.getMRowAccess().getC6Assignment_5()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1678:1: ( rule__MRow__C6Assignment_5 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1678:2: rule__MRow__C6Assignment_5
            {
            pushFollow(FOLLOW_rule__MRow__C6Assignment_5_in_rule__MRow__Group__5__Impl3337);
            rule__MRow__C6Assignment_5();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC6Assignment_5()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__5__Impl"


    // $ANTLR start "rule__MRow__Group__6"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1688:1: rule__MRow__Group__6 : rule__MRow__Group__6__Impl rule__MRow__Group__7 ;
    public final void rule__MRow__Group__6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1692:1: ( rule__MRow__Group__6__Impl rule__MRow__Group__7 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1693:2: rule__MRow__Group__6__Impl rule__MRow__Group__7
            {
            pushFollow(FOLLOW_rule__MRow__Group__6__Impl_in_rule__MRow__Group__63367);
            rule__MRow__Group__6__Impl();

            state._fsp--;

            pushFollow(FOLLOW_rule__MRow__Group__7_in_rule__MRow__Group__63370);
            rule__MRow__Group__7();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__6"


    // $ANTLR start "rule__MRow__Group__6__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1700:1: rule__MRow__Group__6__Impl : ( ( rule__MRow__C7Assignment_6 ) ) ;
    public final void rule__MRow__Group__6__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1704:1: ( ( ( rule__MRow__C7Assignment_6 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1705:1: ( ( rule__MRow__C7Assignment_6 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1705:1: ( ( rule__MRow__C7Assignment_6 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1706:1: ( rule__MRow__C7Assignment_6 )
            {
             before(grammarAccess.getMRowAccess().getC7Assignment_6()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1707:1: ( rule__MRow__C7Assignment_6 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1707:2: rule__MRow__C7Assignment_6
            {
            pushFollow(FOLLOW_rule__MRow__C7Assignment_6_in_rule__MRow__Group__6__Impl3397);
            rule__MRow__C7Assignment_6();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC7Assignment_6()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__6__Impl"


    // $ANTLR start "rule__MRow__Group__7"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1717:1: rule__MRow__Group__7 : rule__MRow__Group__7__Impl ;
    public final void rule__MRow__Group__7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1721:1: ( rule__MRow__Group__7__Impl )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1722:2: rule__MRow__Group__7__Impl
            {
            pushFollow(FOLLOW_rule__MRow__Group__7__Impl_in_rule__MRow__Group__73427);
            rule__MRow__Group__7__Impl();

            state._fsp--;


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__7"


    // $ANTLR start "rule__MRow__Group__7__Impl"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1728:1: rule__MRow__Group__7__Impl : ( ( rule__MRow__C8Assignment_7 ) ) ;
    public final void rule__MRow__Group__7__Impl() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1732:1: ( ( ( rule__MRow__C8Assignment_7 ) ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1733:1: ( ( rule__MRow__C8Assignment_7 ) )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1733:1: ( ( rule__MRow__C8Assignment_7 ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1734:1: ( rule__MRow__C8Assignment_7 )
            {
             before(grammarAccess.getMRowAccess().getC8Assignment_7()); 
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1735:1: ( rule__MRow__C8Assignment_7 )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1735:2: rule__MRow__C8Assignment_7
            {
            pushFollow(FOLLOW_rule__MRow__C8Assignment_7_in_rule__MRow__Group__7__Impl3454);
            rule__MRow__C8Assignment_7();

            state._fsp--;


            }

             after(grammarAccess.getMRowAccess().getC8Assignment_7()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__Group__7__Impl"


    // $ANTLR start "rule__PSQT_Model__TablesAssignment_1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1762:1: rule__PSQT_Model__TablesAssignment_1 : ( ruleTable ) ;
    public final void rule__PSQT_Model__TablesAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1766:1: ( ( ruleTable ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1767:1: ( ruleTable )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1767:1: ( ruleTable )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1768:1: ruleTable
            {
             before(grammarAccess.getPSQT_ModelAccess().getTablesTableParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleTable_in_rule__PSQT_Model__TablesAssignment_13505);
            ruleTable();

            state._fsp--;

             after(grammarAccess.getPSQT_ModelAccess().getTablesTableParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__PSQT_Model__TablesAssignment_1"


    // $ANTLR start "rule__FDescription__NameAssignment_1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1777:1: rule__FDescription__NameAssignment_1 : ( RULE_ID ) ;
    public final void rule__FDescription__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1781:1: ( ( RULE_ID ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1782:1: ( RULE_ID )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1782:1: ( RULE_ID )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1783:1: RULE_ID
            {
             before(grammarAccess.getFDescriptionAccess().getNameIDTerminalRuleCall_1_0()); 
            match(input,RULE_ID,FOLLOW_RULE_ID_in_rule__FDescription__NameAssignment_13536); 
             after(grammarAccess.getFDescriptionAccess().getNameIDTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__NameAssignment_1"


    // $ANTLR start "rule__FDescription__BaseAssignment_3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1792:1: rule__FDescription__BaseAssignment_3 : ( RULE_INT ) ;
    public final void rule__FDescription__BaseAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1796:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1797:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1797:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1798:1: RULE_INT
            {
             before(grammarAccess.getFDescriptionAccess().getBaseINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__FDescription__BaseAssignment_33567); 
             after(grammarAccess.getFDescriptionAccess().getBaseINTTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__BaseAssignment_3"


    // $ANTLR start "rule__FDescription__MglAssignment_5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1807:1: rule__FDescription__MglAssignment_5 : ( RULE_INT ) ;
    public final void rule__FDescription__MglAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1811:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1812:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1812:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1813:1: RULE_INT
            {
             before(grammarAccess.getFDescriptionAccess().getMglINTTerminalRuleCall_5_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__FDescription__MglAssignment_53598); 
             after(grammarAccess.getFDescriptionAccess().getMglINTTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__MglAssignment_5"


    // $ANTLR start "rule__FDescription__EglAssignment_7"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1822:1: rule__FDescription__EglAssignment_7 : ( RULE_INT ) ;
    public final void rule__FDescription__EglAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1826:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1827:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1827:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1828:1: RULE_INT
            {
             before(grammarAccess.getFDescriptionAccess().getEglINTTerminalRuleCall_7_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__FDescription__EglAssignment_73629); 
             after(grammarAccess.getFDescriptionAccess().getEglINTTerminalRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__FDescription__EglAssignment_7"


    // $ANTLR start "rule__Table__RAssignment_2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1837:1: rule__Table__RAssignment_2 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1841:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1842:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1842:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1843:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_23660);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_2"


    // $ANTLR start "rule__Table__RAssignment_3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1852:1: rule__Table__RAssignment_3 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1856:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1857:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1857:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1858:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_3_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_33691);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_3"


    // $ANTLR start "rule__Table__RAssignment_4"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1867:1: rule__Table__RAssignment_4 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1871:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1872:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1872:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1873:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_4_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_43722);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_4"


    // $ANTLR start "rule__Table__RAssignment_5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1882:1: rule__Table__RAssignment_5 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1886:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1887:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1887:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1888:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_5_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_53753);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_5"


    // $ANTLR start "rule__Table__RAssignment_6"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1897:1: rule__Table__RAssignment_6 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1901:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1902:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1902:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1903:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_6_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_63784);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_6"


    // $ANTLR start "rule__Table__RAssignment_7"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1912:1: rule__Table__RAssignment_7 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1916:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1917:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1917:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1918:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_7_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_73815);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_7"


    // $ANTLR start "rule__Table__RAssignment_8"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1927:1: rule__Table__RAssignment_8 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_8() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1931:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1932:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1932:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1933:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_8_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_83846);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_8_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_8"


    // $ANTLR start "rule__Table__RAssignment_9"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1942:1: rule__Table__RAssignment_9 : ( ruleRow ) ;
    public final void rule__Table__RAssignment_9() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1946:1: ( ( ruleRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1947:1: ( ruleRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1947:1: ( ruleRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1948:1: ruleRow
            {
             before(grammarAccess.getTableAccess().getRRowParserRuleCall_9_0()); 
            pushFollow(FOLLOW_ruleRow_in_rule__Table__RAssignment_93877);
            ruleRow();

            state._fsp--;

             after(grammarAccess.getTableAccess().getRRowParserRuleCall_9_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Table__RAssignment_9"


    // $ANTLR start "rule__TDescription__NameAssignment_1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1957:1: rule__TDescription__NameAssignment_1 : ( rulePieceType ) ;
    public final void rule__TDescription__NameAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1961:1: ( ( rulePieceType ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1962:1: ( rulePieceType )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1962:1: ( rulePieceType )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1963:1: rulePieceType
            {
             before(grammarAccess.getTDescriptionAccess().getNamePieceTypeEnumRuleCall_1_0()); 
            pushFollow(FOLLOW_rulePieceType_in_rule__TDescription__NameAssignment_13908);
            rulePieceType();

            state._fsp--;

             after(grammarAccess.getTDescriptionAccess().getNamePieceTypeEnumRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__NameAssignment_1"


    // $ANTLR start "rule__TDescription__MgAssignment_3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1972:1: rule__TDescription__MgAssignment_3 : ( RULE_INT ) ;
    public final void rule__TDescription__MgAssignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1976:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1977:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1977:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1978:1: RULE_INT
            {
             before(grammarAccess.getTDescriptionAccess().getMgINTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__TDescription__MgAssignment_33939); 
             after(grammarAccess.getTDescriptionAccess().getMgINTTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__MgAssignment_3"


    // $ANTLR start "rule__TDescription__EgAssignment_5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1987:1: rule__TDescription__EgAssignment_5 : ( RULE_INT ) ;
    public final void rule__TDescription__EgAssignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1991:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1992:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1992:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:1993:1: RULE_INT
            {
             before(grammarAccess.getTDescriptionAccess().getEgINTTerminalRuleCall_5_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__TDescription__EgAssignment_53970); 
             after(grammarAccess.getTDescriptionAccess().getEgINTTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__TDescription__EgAssignment_5"


    // $ANTLR start "rule__Row__NameAssignment_0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2002:1: rule__Row__NameAssignment_0 : ( ruleRowLabel ) ;
    public final void rule__Row__NameAssignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2006:1: ( ( ruleRowLabel ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2007:1: ( ruleRowLabel )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2007:1: ( ruleRowLabel )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2008:1: ruleRowLabel
            {
             before(grammarAccess.getRowAccess().getNameRowLabelParserRuleCall_0_0()); 
            pushFollow(FOLLOW_ruleRowLabel_in_rule__Row__NameAssignment_04001);
            ruleRowLabel();

            state._fsp--;

             after(grammarAccess.getRowAccess().getNameRowLabelParserRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__NameAssignment_0"


    // $ANTLR start "rule__Row__MidrowAssignment_1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2017:1: rule__Row__MidrowAssignment_1 : ( ruleMRow ) ;
    public final void rule__Row__MidrowAssignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2021:1: ( ( ruleMRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2022:1: ( ruleMRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2022:1: ( ruleMRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2023:1: ruleMRow
            {
             before(grammarAccess.getRowAccess().getMidrowMRowParserRuleCall_1_0()); 
            pushFollow(FOLLOW_ruleMRow_in_rule__Row__MidrowAssignment_14032);
            ruleMRow();

            state._fsp--;

             after(grammarAccess.getRowAccess().getMidrowMRowParserRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__MidrowAssignment_1"


    // $ANTLR start "rule__Row__EndrowAssignment_2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2032:1: rule__Row__EndrowAssignment_2 : ( ruleMRow ) ;
    public final void rule__Row__EndrowAssignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2036:1: ( ( ruleMRow ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2037:1: ( ruleMRow )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2037:1: ( ruleMRow )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2038:1: ruleMRow
            {
             before(grammarAccess.getRowAccess().getEndrowMRowParserRuleCall_2_0()); 
            pushFollow(FOLLOW_ruleMRow_in_rule__Row__EndrowAssignment_24063);
            ruleMRow();

            state._fsp--;

             after(grammarAccess.getRowAccess().getEndrowMRowParserRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__Row__EndrowAssignment_2"


    // $ANTLR start "rule__MRow__C1Assignment_0"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2047:1: rule__MRow__C1Assignment_0 : ( RULE_INT ) ;
    public final void rule__MRow__C1Assignment_0() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2051:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2052:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2052:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2053:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC1INTTerminalRuleCall_0_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C1Assignment_04094); 
             after(grammarAccess.getMRowAccess().getC1INTTerminalRuleCall_0_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C1Assignment_0"


    // $ANTLR start "rule__MRow__C2Assignment_1"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2062:1: rule__MRow__C2Assignment_1 : ( RULE_INT ) ;
    public final void rule__MRow__C2Assignment_1() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2066:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2067:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2067:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2068:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC2INTTerminalRuleCall_1_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C2Assignment_14125); 
             after(grammarAccess.getMRowAccess().getC2INTTerminalRuleCall_1_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C2Assignment_1"


    // $ANTLR start "rule__MRow__C3Assignment_2"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2077:1: rule__MRow__C3Assignment_2 : ( RULE_INT ) ;
    public final void rule__MRow__C3Assignment_2() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2081:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2082:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2082:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2083:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC3INTTerminalRuleCall_2_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C3Assignment_24156); 
             after(grammarAccess.getMRowAccess().getC3INTTerminalRuleCall_2_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C3Assignment_2"


    // $ANTLR start "rule__MRow__C4Assignment_3"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2092:1: rule__MRow__C4Assignment_3 : ( RULE_INT ) ;
    public final void rule__MRow__C4Assignment_3() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2096:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2097:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2097:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2098:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC4INTTerminalRuleCall_3_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C4Assignment_34187); 
             after(grammarAccess.getMRowAccess().getC4INTTerminalRuleCall_3_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C4Assignment_3"


    // $ANTLR start "rule__MRow__C5Assignment_4"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2107:1: rule__MRow__C5Assignment_4 : ( RULE_INT ) ;
    public final void rule__MRow__C5Assignment_4() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2111:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2112:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2112:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2113:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC5INTTerminalRuleCall_4_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C5Assignment_44218); 
             after(grammarAccess.getMRowAccess().getC5INTTerminalRuleCall_4_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C5Assignment_4"


    // $ANTLR start "rule__MRow__C6Assignment_5"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2122:1: rule__MRow__C6Assignment_5 : ( RULE_INT ) ;
    public final void rule__MRow__C6Assignment_5() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2126:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2127:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2127:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2128:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC6INTTerminalRuleCall_5_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C6Assignment_54249); 
             after(grammarAccess.getMRowAccess().getC6INTTerminalRuleCall_5_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C6Assignment_5"


    // $ANTLR start "rule__MRow__C7Assignment_6"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2137:1: rule__MRow__C7Assignment_6 : ( RULE_INT ) ;
    public final void rule__MRow__C7Assignment_6() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2141:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2142:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2142:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2143:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC7INTTerminalRuleCall_6_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C7Assignment_64280); 
             after(grammarAccess.getMRowAccess().getC7INTTerminalRuleCall_6_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C7Assignment_6"


    // $ANTLR start "rule__MRow__C8Assignment_7"
    // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2152:1: rule__MRow__C8Assignment_7 : ( RULE_INT ) ;
    public final void rule__MRow__C8Assignment_7() throws RecognitionException {

        		int stackSize = keepStackSize();
            
        try {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2156:1: ( ( RULE_INT ) )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2157:1: ( RULE_INT )
            {
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2157:1: ( RULE_INT )
            // ../norwegiangambit.psqt.ui/src-gen/norwegiangambit/psqt/ui/contentassist/antlr/internal/InternalPSQT.g:2158:1: RULE_INT
            {
             before(grammarAccess.getMRowAccess().getC8INTTerminalRuleCall_7_0()); 
            match(input,RULE_INT,FOLLOW_RULE_INT_in_rule__MRow__C8Assignment_74311); 
             after(grammarAccess.getMRowAccess().getC8INTTerminalRuleCall_7_0()); 

            }


            }

        }
        catch (RecognitionException re) {
            reportError(re);
            recover(input,re);
        }
        finally {

            	restoreStackSize(stackSize);

        }
        return ;
    }
    // $ANTLR end "rule__MRow__C8Assignment_7"

    // Delegated rules


 

    public static final BitSet FOLLOW_rulePSQT_Model_in_entryRulePSQT_Model61 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePSQT_Model68 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PSQT_Model__Group__0_in_rulePSQT_Model94 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFDescription_in_entryRuleFDescription121 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFDescription128 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__0_in_ruleFDescription154 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_entryRuleTable181 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTable188 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__0_in_ruleTable214 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTDescription_in_entryRuleTDescription241 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTDescription248 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__Group__0_in_ruleTDescription274 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTHeader_in_entryRuleTHeader301 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTHeader308 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__0_in_ruleTHeader334 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_entryRuleRow361 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRow368 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Row__Group__0_in_ruleRow394 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRowLabel_in_entryRuleRowLabel421 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRowLabel428 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleRowLabel454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMRow_in_entryRuleMRow480 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMRow487 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__0_in_ruleMRow513 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PieceType__Alternatives_in_rulePieceType550 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_rule__PieceType__Alternatives586 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_19_in_rule__PieceType__Alternatives607 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_20_in_rule__PieceType__Alternatives628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_21_in_rule__PieceType__Alternatives649 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_22_in_rule__PieceType__Alternatives670 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_23_in_rule__PieceType__Alternatives691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PSQT_Model__Group__0__Impl_in_rule__PSQT_Model__Group__0724 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_rule__PSQT_Model__Group__1_in_rule__PSQT_Model__Group__0727 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFDescription_in_rule__PSQT_Model__Group__0__Impl754 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PSQT_Model__Group__1__Impl_in_rule__PSQT_Model__Group__1783 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__PSQT_Model__TablesAssignment_1_in_rule__PSQT_Model__Group__1__Impl810 = new BitSet(new long[]{0x0000000000000202L});
    public static final BitSet FOLLOW_rule__FDescription__Group__0__Impl_in_rule__FDescription__Group__0845 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_rule__FDescription__Group__1_in_rule__FDescription__Group__0848 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_PSQT_in_rule__FDescription__Group__0__Impl875 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__1__Impl_in_rule__FDescription__Group__1904 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_rule__FDescription__Group__2_in_rule__FDescription__Group__1907 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__NameAssignment_1_in_rule__FDescription__Group__1__Impl934 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__2__Impl_in_rule__FDescription__Group__2964 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FDescription__Group__3_in_rule__FDescription__Group__2967 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_BASE_in_rule__FDescription__Group__2__Impl994 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__3__Impl_in_rule__FDescription__Group__31023 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_rule__FDescription__Group__4_in_rule__FDescription__Group__31026 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__BaseAssignment_3_in_rule__FDescription__Group__3__Impl1053 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__4__Impl_in_rule__FDescription__Group__41083 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FDescription__Group__5_in_rule__FDescription__Group__41086 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_MIDLIMIT_in_rule__FDescription__Group__4__Impl1113 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__5__Impl_in_rule__FDescription__Group__51142 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_rule__FDescription__Group__6_in_rule__FDescription__Group__51145 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__MglAssignment_5_in_rule__FDescription__Group__5__Impl1172 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__6__Impl_in_rule__FDescription__Group__61202 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__FDescription__Group__7_in_rule__FDescription__Group__61205 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ENDLIMIT_in_rule__FDescription__Group__6__Impl1232 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__Group__7__Impl_in_rule__FDescription__Group__71261 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__FDescription__EglAssignment_7_in_rule__FDescription__Group__7__Impl1288 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__0__Impl_in_rule__Table__Group__01334 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_rule__Table__Group__1_in_rule__Table__Group__01337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTDescription_in_rule__Table__Group__0__Impl1364 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__1__Impl_in_rule__Table__Group__11393 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__2_in_rule__Table__Group__11396 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTHeader_in_rule__Table__Group__1__Impl1423 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__2__Impl_in_rule__Table__Group__21452 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__3_in_rule__Table__Group__21455 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_2_in_rule__Table__Group__2__Impl1482 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__3__Impl_in_rule__Table__Group__31512 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__4_in_rule__Table__Group__31515 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_3_in_rule__Table__Group__3__Impl1542 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__4__Impl_in_rule__Table__Group__41572 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__5_in_rule__Table__Group__41575 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_4_in_rule__Table__Group__4__Impl1602 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__5__Impl_in_rule__Table__Group__51632 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__6_in_rule__Table__Group__51635 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_5_in_rule__Table__Group__5__Impl1662 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__6__Impl_in_rule__Table__Group__61692 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__7_in_rule__Table__Group__61695 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_6_in_rule__Table__Group__6__Impl1722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__7__Impl_in_rule__Table__Group__71752 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__8_in_rule__Table__Group__71755 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_7_in_rule__Table__Group__7__Impl1782 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__8__Impl_in_rule__Table__Group__81812 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Table__Group__9_in_rule__Table__Group__81815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_8_in_rule__Table__Group__8__Impl1842 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__Group__9__Impl_in_rule__Table__Group__91872 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Table__RAssignment_9_in_rule__Table__Group__9__Impl1899 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__Group__0__Impl_in_rule__TDescription__Group__01949 = new BitSet(new long[]{0x0000000000FC0000L});
    public static final BitSet FOLLOW_rule__TDescription__Group__1_in_rule__TDescription__Group__01952 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_PIECE_in_rule__TDescription__Group__0__Impl1979 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__Group__1__Impl_in_rule__TDescription__Group__12008 = new BitSet(new long[]{0x0000000000000400L});
    public static final BitSet FOLLOW_rule__TDescription__Group__2_in_rule__TDescription__Group__12011 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__NameAssignment_1_in_rule__TDescription__Group__1__Impl2038 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__Group__2__Impl_in_rule__TDescription__Group__22068 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TDescription__Group__3_in_rule__TDescription__Group__22071 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_MIDGAME_in_rule__TDescription__Group__2__Impl2098 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__Group__3__Impl_in_rule__TDescription__Group__32127 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_rule__TDescription__Group__4_in_rule__TDescription__Group__32130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__MgAssignment_3_in_rule__TDescription__Group__3__Impl2157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__Group__4__Impl_in_rule__TDescription__Group__42187 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__TDescription__Group__5_in_rule__TDescription__Group__42190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ENDGAME_in_rule__TDescription__Group__4__Impl2217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__Group__5__Impl_in_rule__TDescription__Group__52246 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__TDescription__EgAssignment_5_in_rule__TDescription__Group__5__Impl2273 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__0__Impl_in_rule__THeader__Group__02315 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_rule__THeader__Group__1_in_rule__THeader__Group__02318 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_24_in_rule__THeader__Group__0__Impl2346 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__1__Impl_in_rule__THeader__Group__12377 = new BitSet(new long[]{0x0000000004000000L});
    public static final BitSet FOLLOW_rule__THeader__Group__2_in_rule__THeader__Group__12380 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_25_in_rule__THeader__Group__1__Impl2408 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__2__Impl_in_rule__THeader__Group__22439 = new BitSet(new long[]{0x0000000008000000L});
    public static final BitSet FOLLOW_rule__THeader__Group__3_in_rule__THeader__Group__22442 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rule__THeader__Group__2__Impl2470 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__3__Impl_in_rule__THeader__Group__32501 = new BitSet(new long[]{0x0000000010000000L});
    public static final BitSet FOLLOW_rule__THeader__Group__4_in_rule__THeader__Group__32504 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rule__THeader__Group__3__Impl2532 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__4__Impl_in_rule__THeader__Group__42563 = new BitSet(new long[]{0x0000000020000000L});
    public static final BitSet FOLLOW_rule__THeader__Group__5_in_rule__THeader__Group__42566 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rule__THeader__Group__4__Impl2594 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__5__Impl_in_rule__THeader__Group__52625 = new BitSet(new long[]{0x0000000040000000L});
    public static final BitSet FOLLOW_rule__THeader__Group__6_in_rule__THeader__Group__52628 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rule__THeader__Group__5__Impl2656 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__6__Impl_in_rule__THeader__Group__62687 = new BitSet(new long[]{0x0000000080000000L});
    public static final BitSet FOLLOW_rule__THeader__Group__7_in_rule__THeader__Group__62690 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rule__THeader__Group__6__Impl2718 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__THeader__Group__7__Impl_in_rule__THeader__Group__72749 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rule__THeader__Group__7__Impl2777 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Row__Group__0__Impl_in_rule__Row__Group__02824 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Row__Group__1_in_rule__Row__Group__02827 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Row__NameAssignment_0_in_rule__Row__Group__0__Impl2854 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Row__Group__1__Impl_in_rule__Row__Group__12884 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__Row__Group__2_in_rule__Row__Group__12887 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Row__MidrowAssignment_1_in_rule__Row__Group__1__Impl2914 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Row__Group__2__Impl_in_rule__Row__Group__22944 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__Row__EndrowAssignment_2_in_rule__Row__Group__2__Impl2971 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__0__Impl_in_rule__MRow__Group__03007 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__MRow__Group__1_in_rule__MRow__Group__03010 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C1Assignment_0_in_rule__MRow__Group__0__Impl3037 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__1__Impl_in_rule__MRow__Group__13067 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__MRow__Group__2_in_rule__MRow__Group__13070 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C2Assignment_1_in_rule__MRow__Group__1__Impl3097 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__2__Impl_in_rule__MRow__Group__23127 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__MRow__Group__3_in_rule__MRow__Group__23130 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C3Assignment_2_in_rule__MRow__Group__2__Impl3157 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__3__Impl_in_rule__MRow__Group__33187 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__MRow__Group__4_in_rule__MRow__Group__33190 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C4Assignment_3_in_rule__MRow__Group__3__Impl3217 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__4__Impl_in_rule__MRow__Group__43247 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__MRow__Group__5_in_rule__MRow__Group__43250 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C5Assignment_4_in_rule__MRow__Group__4__Impl3277 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__5__Impl_in_rule__MRow__Group__53307 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__MRow__Group__6_in_rule__MRow__Group__53310 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C6Assignment_5_in_rule__MRow__Group__5__Impl3337 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__6__Impl_in_rule__MRow__Group__63367 = new BitSet(new long[]{0x0000000000000010L});
    public static final BitSet FOLLOW_rule__MRow__Group__7_in_rule__MRow__Group__63370 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C7Assignment_6_in_rule__MRow__Group__6__Impl3397 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__Group__7__Impl_in_rule__MRow__Group__73427 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rule__MRow__C8Assignment_7_in_rule__MRow__Group__7__Impl3454 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_rule__PSQT_Model__TablesAssignment_13505 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_ID_in_rule__FDescription__NameAssignment_13536 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__FDescription__BaseAssignment_33567 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__FDescription__MglAssignment_53598 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__FDescription__EglAssignment_73629 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_23660 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_33691 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_43722 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_53753 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_63784 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_73815 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_83846 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_rule__Table__RAssignment_93877 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_rulePieceType_in_rule__TDescription__NameAssignment_13908 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__TDescription__MgAssignment_33939 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__TDescription__EgAssignment_53970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRowLabel_in_rule__Row__NameAssignment_04001 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMRow_in_rule__Row__MidrowAssignment_14032 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMRow_in_rule__Row__EndrowAssignment_24063 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C1Assignment_04094 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C2Assignment_14125 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C3Assignment_24156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C4Assignment_34187 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C5Assignment_44218 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C6Assignment_54249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C7Assignment_64280 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_rule__MRow__C8Assignment_74311 = new BitSet(new long[]{0x0000000000000002L});

}