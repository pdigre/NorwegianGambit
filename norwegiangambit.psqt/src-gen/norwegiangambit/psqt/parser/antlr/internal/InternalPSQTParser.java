package norwegiangambit.psqt.parser.antlr.internal; 

import org.eclipse.xtext.*;
import org.eclipse.xtext.parser.*;
import org.eclipse.xtext.parser.impl.*;
import org.eclipse.emf.ecore.util.EcoreUtil;
import org.eclipse.emf.ecore.EObject;
import org.eclipse.emf.common.util.Enumerator;
import org.eclipse.xtext.parser.antlr.AbstractInternalAntlrParser;
import org.eclipse.xtext.parser.antlr.XtextTokenStream;
import org.eclipse.xtext.parser.antlr.XtextTokenStream.HiddenTokens;
import org.eclipse.xtext.parser.antlr.AntlrDatatypeRuleToken;
import norwegiangambit.psqt.services.PSQTGrammarAccess;



import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPSQTParser extends AbstractInternalAntlrParser {
    public static final String[] tokenNames = new String[] {
        "<invalid>", "<EOR>", "<DOWN>", "<UP>", "RULE_PSQT", "RULE_ID", "RULE_BASE", "RULE_INT", "RULE_MIDLIMIT", "RULE_ENDLIMIT", "RULE_PIECE", "RULE_MIDGAME", "RULE_ENDGAME", "RULE_STRING", "RULE_ML_COMMENT", "RULE_SL_COMMENT", "RULE_WS", "RULE_ANY_OTHER", "'A'", "'B'", "'C'", "'D'", "'E'", "'F'", "'G'", "'H'", "'Pawn'", "'Knight'", "'Bishop'", "'Rook'", "'Queen'", "'King'"
    };
    public static final int RULE_MIDGAME=11;
    public static final int RULE_ENDGAME=12;
    public static final int RULE_PIECE=10;
    public static final int RULE_STRING=13;
    public static final int RULE_SL_COMMENT=15;
    public static final int T__19=19;
    public static final int RULE_MIDLIMIT=8;
    public static final int RULE_BASE=6;
    public static final int T__18=18;
    public static final int RULE_ENDLIMIT=9;
    public static final int EOF=-1;
    public static final int T__30=30;
    public static final int T__31=31;
    public static final int RULE_ID=5;
    public static final int RULE_WS=16;
    public static final int RULE_ANY_OTHER=17;
    public static final int RULE_PSQT=4;
    public static final int T__26=26;
    public static final int T__27=27;
    public static final int T__28=28;
    public static final int RULE_INT=7;
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
    public String getGrammarFileName() { return "../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g"; }



     	private PSQTGrammarAccess grammarAccess;
     	
        public InternalPSQTParser(TokenStream input, PSQTGrammarAccess grammarAccess) {
            this(input);
            this.grammarAccess = grammarAccess;
            registerRules(grammarAccess.getGrammar());
        }
        
        @Override
        protected String getFirstRuleName() {
        	return "PSQT_Model";	
       	}
       	
       	@Override
       	protected PSQTGrammarAccess getGrammarAccess() {
       		return grammarAccess;
       	}



    // $ANTLR start "entryRulePSQT_Model"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:68:1: entryRulePSQT_Model returns [EObject current=null] : iv_rulePSQT_Model= rulePSQT_Model EOF ;
    public final EObject entryRulePSQT_Model() throws RecognitionException {
        EObject current = null;

        EObject iv_rulePSQT_Model = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:69:2: (iv_rulePSQT_Model= rulePSQT_Model EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:70:2: iv_rulePSQT_Model= rulePSQT_Model EOF
            {
             newCompositeNode(grammarAccess.getPSQT_ModelRule()); 
            pushFollow(FOLLOW_rulePSQT_Model_in_entryRulePSQT_Model75);
            iv_rulePSQT_Model=rulePSQT_Model();

            state._fsp--;

             current =iv_rulePSQT_Model; 
            match(input,EOF,FOLLOW_EOF_in_entryRulePSQT_Model85); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRulePSQT_Model"


    // $ANTLR start "rulePSQT_Model"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:77:1: rulePSQT_Model returns [EObject current=null] : (this_FDescription_0= ruleFDescription ( (lv_tables_1_0= ruleTable ) )* ) ;
    public final EObject rulePSQT_Model() throws RecognitionException {
        EObject current = null;

        EObject this_FDescription_0 = null;

        EObject lv_tables_1_0 = null;


         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:80:28: ( (this_FDescription_0= ruleFDescription ( (lv_tables_1_0= ruleTable ) )* ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:81:1: (this_FDescription_0= ruleFDescription ( (lv_tables_1_0= ruleTable ) )* )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:81:1: (this_FDescription_0= ruleFDescription ( (lv_tables_1_0= ruleTable ) )* )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:82:5: this_FDescription_0= ruleFDescription ( (lv_tables_1_0= ruleTable ) )*
            {
             
                    newCompositeNode(grammarAccess.getPSQT_ModelAccess().getFDescriptionParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleFDescription_in_rulePSQT_Model132);
            this_FDescription_0=ruleFDescription();

            state._fsp--;

             
                    current = this_FDescription_0; 
                    afterParserOrEnumRuleCall();
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:90:1: ( (lv_tables_1_0= ruleTable ) )*
            loop1:
            do {
                int alt1=2;
                int LA1_0 = input.LA(1);

                if ( (LA1_0==RULE_PIECE) ) {
                    alt1=1;
                }


                switch (alt1) {
            	case 1 :
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:91:1: (lv_tables_1_0= ruleTable )
            	    {
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:91:1: (lv_tables_1_0= ruleTable )
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:92:3: lv_tables_1_0= ruleTable
            	    {
            	     
            	    	        newCompositeNode(grammarAccess.getPSQT_ModelAccess().getTablesTableParserRuleCall_1_0()); 
            	    	    
            	    pushFollow(FOLLOW_ruleTable_in_rulePSQT_Model152);
            	    lv_tables_1_0=ruleTable();

            	    state._fsp--;


            	    	        if (current==null) {
            	    	            current = createModelElementForParent(grammarAccess.getPSQT_ModelRule());
            	    	        }
            	           		add(
            	           			current, 
            	           			"tables",
            	            		lv_tables_1_0, 
            	            		"Table");
            	    	        afterParserOrEnumRuleCall();
            	    	    

            	    }


            	    }
            	    break;

            	default :
            	    break loop1;
                }
            } while (true);


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePSQT_Model"


    // $ANTLR start "entryRuleFDescription"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:116:1: entryRuleFDescription returns [EObject current=null] : iv_ruleFDescription= ruleFDescription EOF ;
    public final EObject entryRuleFDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleFDescription = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:117:2: (iv_ruleFDescription= ruleFDescription EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:118:2: iv_ruleFDescription= ruleFDescription EOF
            {
             newCompositeNode(grammarAccess.getFDescriptionRule()); 
            pushFollow(FOLLOW_ruleFDescription_in_entryRuleFDescription189);
            iv_ruleFDescription=ruleFDescription();

            state._fsp--;

             current =iv_ruleFDescription; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleFDescription199); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleFDescription"


    // $ANTLR start "ruleFDescription"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:125:1: ruleFDescription returns [EObject current=null] : (this_PSQT_0= RULE_PSQT ( (lv_name_1_0= RULE_ID ) ) this_BASE_2= RULE_BASE ( (lv_base_3_0= RULE_INT ) ) this_MIDLIMIT_4= RULE_MIDLIMIT ( (lv_mgl_5_0= RULE_INT ) ) this_ENDLIMIT_6= RULE_ENDLIMIT ( (lv_egl_7_0= RULE_INT ) ) ) ;
    public final EObject ruleFDescription() throws RecognitionException {
        EObject current = null;

        Token this_PSQT_0=null;
        Token lv_name_1_0=null;
        Token this_BASE_2=null;
        Token lv_base_3_0=null;
        Token this_MIDLIMIT_4=null;
        Token lv_mgl_5_0=null;
        Token this_ENDLIMIT_6=null;
        Token lv_egl_7_0=null;

         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:128:28: ( (this_PSQT_0= RULE_PSQT ( (lv_name_1_0= RULE_ID ) ) this_BASE_2= RULE_BASE ( (lv_base_3_0= RULE_INT ) ) this_MIDLIMIT_4= RULE_MIDLIMIT ( (lv_mgl_5_0= RULE_INT ) ) this_ENDLIMIT_6= RULE_ENDLIMIT ( (lv_egl_7_0= RULE_INT ) ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:129:1: (this_PSQT_0= RULE_PSQT ( (lv_name_1_0= RULE_ID ) ) this_BASE_2= RULE_BASE ( (lv_base_3_0= RULE_INT ) ) this_MIDLIMIT_4= RULE_MIDLIMIT ( (lv_mgl_5_0= RULE_INT ) ) this_ENDLIMIT_6= RULE_ENDLIMIT ( (lv_egl_7_0= RULE_INT ) ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:129:1: (this_PSQT_0= RULE_PSQT ( (lv_name_1_0= RULE_ID ) ) this_BASE_2= RULE_BASE ( (lv_base_3_0= RULE_INT ) ) this_MIDLIMIT_4= RULE_MIDLIMIT ( (lv_mgl_5_0= RULE_INT ) ) this_ENDLIMIT_6= RULE_ENDLIMIT ( (lv_egl_7_0= RULE_INT ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:129:2: this_PSQT_0= RULE_PSQT ( (lv_name_1_0= RULE_ID ) ) this_BASE_2= RULE_BASE ( (lv_base_3_0= RULE_INT ) ) this_MIDLIMIT_4= RULE_MIDLIMIT ( (lv_mgl_5_0= RULE_INT ) ) this_ENDLIMIT_6= RULE_ENDLIMIT ( (lv_egl_7_0= RULE_INT ) )
            {
            this_PSQT_0=(Token)match(input,RULE_PSQT,FOLLOW_RULE_PSQT_in_ruleFDescription235); 
             
                newLeafNode(this_PSQT_0, grammarAccess.getFDescriptionAccess().getPSQTTerminalRuleCall_0()); 
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:133:1: ( (lv_name_1_0= RULE_ID ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:134:1: (lv_name_1_0= RULE_ID )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:134:1: (lv_name_1_0= RULE_ID )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:135:3: lv_name_1_0= RULE_ID
            {
            lv_name_1_0=(Token)match(input,RULE_ID,FOLLOW_RULE_ID_in_ruleFDescription251); 

            			newLeafNode(lv_name_1_0, grammarAccess.getFDescriptionAccess().getNameIDTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getFDescriptionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"ID");
            	    

            }


            }

            this_BASE_2=(Token)match(input,RULE_BASE,FOLLOW_RULE_BASE_in_ruleFDescription267); 
             
                newLeafNode(this_BASE_2, grammarAccess.getFDescriptionAccess().getBASETerminalRuleCall_2()); 
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:155:1: ( (lv_base_3_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:156:1: (lv_base_3_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:156:1: (lv_base_3_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:157:3: lv_base_3_0= RULE_INT
            {
            lv_base_3_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleFDescription283); 

            			newLeafNode(lv_base_3_0, grammarAccess.getFDescriptionAccess().getBaseINTTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getFDescriptionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"base",
                    		lv_base_3_0, 
                    		"INT");
            	    

            }


            }

            this_MIDLIMIT_4=(Token)match(input,RULE_MIDLIMIT,FOLLOW_RULE_MIDLIMIT_in_ruleFDescription299); 
             
                newLeafNode(this_MIDLIMIT_4, grammarAccess.getFDescriptionAccess().getMIDLIMITTerminalRuleCall_4()); 
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:177:1: ( (lv_mgl_5_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:178:1: (lv_mgl_5_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:178:1: (lv_mgl_5_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:179:3: lv_mgl_5_0= RULE_INT
            {
            lv_mgl_5_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleFDescription315); 

            			newLeafNode(lv_mgl_5_0, grammarAccess.getFDescriptionAccess().getMglINTTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getFDescriptionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"mgl",
                    		lv_mgl_5_0, 
                    		"INT");
            	    

            }


            }

            this_ENDLIMIT_6=(Token)match(input,RULE_ENDLIMIT,FOLLOW_RULE_ENDLIMIT_in_ruleFDescription331); 
             
                newLeafNode(this_ENDLIMIT_6, grammarAccess.getFDescriptionAccess().getENDLIMITTerminalRuleCall_6()); 
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:199:1: ( (lv_egl_7_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:200:1: (lv_egl_7_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:200:1: (lv_egl_7_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:201:3: lv_egl_7_0= RULE_INT
            {
            lv_egl_7_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleFDescription347); 

            			newLeafNode(lv_egl_7_0, grammarAccess.getFDescriptionAccess().getEglINTTerminalRuleCall_7_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getFDescriptionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"egl",
                    		lv_egl_7_0, 
                    		"INT");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleFDescription"


    // $ANTLR start "entryRuleTable"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:225:1: entryRuleTable returns [EObject current=null] : iv_ruleTable= ruleTable EOF ;
    public final EObject entryRuleTable() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTable = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:226:2: (iv_ruleTable= ruleTable EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:227:2: iv_ruleTable= ruleTable EOF
            {
             newCompositeNode(grammarAccess.getTableRule()); 
            pushFollow(FOLLOW_ruleTable_in_entryRuleTable388);
            iv_ruleTable=ruleTable();

            state._fsp--;

             current =iv_ruleTable; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTable398); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTable"


    // $ANTLR start "ruleTable"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:234:1: ruleTable returns [EObject current=null] : (this_TDescription_0= ruleTDescription ruleTHeader ( (lv_r_2_0= ruleRow ) ) ( (lv_r_3_0= ruleRow ) ) ( (lv_r_4_0= ruleRow ) ) ( (lv_r_5_0= ruleRow ) ) ( (lv_r_6_0= ruleRow ) ) ( (lv_r_7_0= ruleRow ) ) ( (lv_r_8_0= ruleRow ) ) ( (lv_r_9_0= ruleRow ) ) ) ;
    public final EObject ruleTable() throws RecognitionException {
        EObject current = null;

        EObject this_TDescription_0 = null;

        EObject lv_r_2_0 = null;

        EObject lv_r_3_0 = null;

        EObject lv_r_4_0 = null;

        EObject lv_r_5_0 = null;

        EObject lv_r_6_0 = null;

        EObject lv_r_7_0 = null;

        EObject lv_r_8_0 = null;

        EObject lv_r_9_0 = null;


         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:237:28: ( (this_TDescription_0= ruleTDescription ruleTHeader ( (lv_r_2_0= ruleRow ) ) ( (lv_r_3_0= ruleRow ) ) ( (lv_r_4_0= ruleRow ) ) ( (lv_r_5_0= ruleRow ) ) ( (lv_r_6_0= ruleRow ) ) ( (lv_r_7_0= ruleRow ) ) ( (lv_r_8_0= ruleRow ) ) ( (lv_r_9_0= ruleRow ) ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:238:1: (this_TDescription_0= ruleTDescription ruleTHeader ( (lv_r_2_0= ruleRow ) ) ( (lv_r_3_0= ruleRow ) ) ( (lv_r_4_0= ruleRow ) ) ( (lv_r_5_0= ruleRow ) ) ( (lv_r_6_0= ruleRow ) ) ( (lv_r_7_0= ruleRow ) ) ( (lv_r_8_0= ruleRow ) ) ( (lv_r_9_0= ruleRow ) ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:238:1: (this_TDescription_0= ruleTDescription ruleTHeader ( (lv_r_2_0= ruleRow ) ) ( (lv_r_3_0= ruleRow ) ) ( (lv_r_4_0= ruleRow ) ) ( (lv_r_5_0= ruleRow ) ) ( (lv_r_6_0= ruleRow ) ) ( (lv_r_7_0= ruleRow ) ) ( (lv_r_8_0= ruleRow ) ) ( (lv_r_9_0= ruleRow ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:239:5: this_TDescription_0= ruleTDescription ruleTHeader ( (lv_r_2_0= ruleRow ) ) ( (lv_r_3_0= ruleRow ) ) ( (lv_r_4_0= ruleRow ) ) ( (lv_r_5_0= ruleRow ) ) ( (lv_r_6_0= ruleRow ) ) ( (lv_r_7_0= ruleRow ) ) ( (lv_r_8_0= ruleRow ) ) ( (lv_r_9_0= ruleRow ) )
            {
             
                    newCompositeNode(grammarAccess.getTableAccess().getTDescriptionParserRuleCall_0()); 
                
            pushFollow(FOLLOW_ruleTDescription_in_ruleTable445);
            this_TDescription_0=ruleTDescription();

            state._fsp--;

             
                    current = this_TDescription_0; 
                    afterParserOrEnumRuleCall();
                
             
                    newCompositeNode(grammarAccess.getTableAccess().getTHeaderParserRuleCall_1()); 
                
            pushFollow(FOLLOW_ruleTHeader_in_ruleTable460);
            ruleTHeader();

            state._fsp--;

             
                    afterParserOrEnumRuleCall();
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:255:1: ( (lv_r_2_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:256:1: (lv_r_2_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:256:1: (lv_r_2_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:257:3: lv_r_2_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable480);
            lv_r_2_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_2_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:273:2: ( (lv_r_3_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:274:1: (lv_r_3_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:274:1: (lv_r_3_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:275:3: lv_r_3_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_3_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable501);
            lv_r_3_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_3_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:291:2: ( (lv_r_4_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:292:1: (lv_r_4_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:292:1: (lv_r_4_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:293:3: lv_r_4_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_4_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable522);
            lv_r_4_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_4_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:309:2: ( (lv_r_5_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:310:1: (lv_r_5_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:310:1: (lv_r_5_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:311:3: lv_r_5_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_5_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable543);
            lv_r_5_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_5_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:327:2: ( (lv_r_6_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:328:1: (lv_r_6_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:328:1: (lv_r_6_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:329:3: lv_r_6_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_6_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable564);
            lv_r_6_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_6_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:345:2: ( (lv_r_7_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:346:1: (lv_r_7_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:346:1: (lv_r_7_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:347:3: lv_r_7_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_7_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable585);
            lv_r_7_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_7_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:363:2: ( (lv_r_8_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:364:1: (lv_r_8_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:364:1: (lv_r_8_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:365:3: lv_r_8_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_8_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable606);
            lv_r_8_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_8_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:381:2: ( (lv_r_9_0= ruleRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:382:1: (lv_r_9_0= ruleRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:382:1: (lv_r_9_0= ruleRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:383:3: lv_r_9_0= ruleRow
            {
             
            	        newCompositeNode(grammarAccess.getTableAccess().getRRowParserRuleCall_9_0()); 
            	    
            pushFollow(FOLLOW_ruleRow_in_ruleTable627);
            lv_r_9_0=ruleRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTableRule());
            	        }
                   		add(
                   			current, 
                   			"r",
                    		lv_r_9_0, 
                    		"Row");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTable"


    // $ANTLR start "entryRuleTDescription"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:407:1: entryRuleTDescription returns [EObject current=null] : iv_ruleTDescription= ruleTDescription EOF ;
    public final EObject entryRuleTDescription() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleTDescription = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:408:2: (iv_ruleTDescription= ruleTDescription EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:409:2: iv_ruleTDescription= ruleTDescription EOF
            {
             newCompositeNode(grammarAccess.getTDescriptionRule()); 
            pushFollow(FOLLOW_ruleTDescription_in_entryRuleTDescription663);
            iv_ruleTDescription=ruleTDescription();

            state._fsp--;

             current =iv_ruleTDescription; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTDescription673); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTDescription"


    // $ANTLR start "ruleTDescription"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:416:1: ruleTDescription returns [EObject current=null] : (this_PIECE_0= RULE_PIECE ( (lv_name_1_0= rulePieceType ) ) this_MIDGAME_2= RULE_MIDGAME ( (lv_mg_3_0= RULE_INT ) ) this_ENDGAME_4= RULE_ENDGAME ( (lv_eg_5_0= RULE_INT ) ) ) ;
    public final EObject ruleTDescription() throws RecognitionException {
        EObject current = null;

        Token this_PIECE_0=null;
        Token this_MIDGAME_2=null;
        Token lv_mg_3_0=null;
        Token this_ENDGAME_4=null;
        Token lv_eg_5_0=null;
        Enumerator lv_name_1_0 = null;


         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:419:28: ( (this_PIECE_0= RULE_PIECE ( (lv_name_1_0= rulePieceType ) ) this_MIDGAME_2= RULE_MIDGAME ( (lv_mg_3_0= RULE_INT ) ) this_ENDGAME_4= RULE_ENDGAME ( (lv_eg_5_0= RULE_INT ) ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:420:1: (this_PIECE_0= RULE_PIECE ( (lv_name_1_0= rulePieceType ) ) this_MIDGAME_2= RULE_MIDGAME ( (lv_mg_3_0= RULE_INT ) ) this_ENDGAME_4= RULE_ENDGAME ( (lv_eg_5_0= RULE_INT ) ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:420:1: (this_PIECE_0= RULE_PIECE ( (lv_name_1_0= rulePieceType ) ) this_MIDGAME_2= RULE_MIDGAME ( (lv_mg_3_0= RULE_INT ) ) this_ENDGAME_4= RULE_ENDGAME ( (lv_eg_5_0= RULE_INT ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:420:2: this_PIECE_0= RULE_PIECE ( (lv_name_1_0= rulePieceType ) ) this_MIDGAME_2= RULE_MIDGAME ( (lv_mg_3_0= RULE_INT ) ) this_ENDGAME_4= RULE_ENDGAME ( (lv_eg_5_0= RULE_INT ) )
            {
            this_PIECE_0=(Token)match(input,RULE_PIECE,FOLLOW_RULE_PIECE_in_ruleTDescription709); 
             
                newLeafNode(this_PIECE_0, grammarAccess.getTDescriptionAccess().getPIECETerminalRuleCall_0()); 
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:424:1: ( (lv_name_1_0= rulePieceType ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:425:1: (lv_name_1_0= rulePieceType )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:425:1: (lv_name_1_0= rulePieceType )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:426:3: lv_name_1_0= rulePieceType
            {
             
            	        newCompositeNode(grammarAccess.getTDescriptionAccess().getNamePieceTypeEnumRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_rulePieceType_in_ruleTDescription729);
            lv_name_1_0=rulePieceType();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getTDescriptionRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_1_0, 
                    		"PieceType");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            this_MIDGAME_2=(Token)match(input,RULE_MIDGAME,FOLLOW_RULE_MIDGAME_in_ruleTDescription740); 
             
                newLeafNode(this_MIDGAME_2, grammarAccess.getTDescriptionAccess().getMIDGAMETerminalRuleCall_2()); 
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:446:1: ( (lv_mg_3_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:447:1: (lv_mg_3_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:447:1: (lv_mg_3_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:448:3: lv_mg_3_0= RULE_INT
            {
            lv_mg_3_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleTDescription756); 

            			newLeafNode(lv_mg_3_0, grammarAccess.getTDescriptionAccess().getMgINTTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTDescriptionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"mg",
                    		lv_mg_3_0, 
                    		"INT");
            	    

            }


            }

            this_ENDGAME_4=(Token)match(input,RULE_ENDGAME,FOLLOW_RULE_ENDGAME_in_ruleTDescription772); 
             
                newLeafNode(this_ENDGAME_4, grammarAccess.getTDescriptionAccess().getENDGAMETerminalRuleCall_4()); 
                
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:468:1: ( (lv_eg_5_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:469:1: (lv_eg_5_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:469:1: (lv_eg_5_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:470:3: lv_eg_5_0= RULE_INT
            {
            lv_eg_5_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleTDescription788); 

            			newLeafNode(lv_eg_5_0, grammarAccess.getTDescriptionAccess().getEgINTTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getTDescriptionRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"eg",
                    		lv_eg_5_0, 
                    		"INT");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTDescription"


    // $ANTLR start "entryRuleTHeader"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:494:1: entryRuleTHeader returns [String current=null] : iv_ruleTHeader= ruleTHeader EOF ;
    public final String entryRuleTHeader() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleTHeader = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:495:2: (iv_ruleTHeader= ruleTHeader EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:496:2: iv_ruleTHeader= ruleTHeader EOF
            {
             newCompositeNode(grammarAccess.getTHeaderRule()); 
            pushFollow(FOLLOW_ruleTHeader_in_entryRuleTHeader830);
            iv_ruleTHeader=ruleTHeader();

            state._fsp--;

             current =iv_ruleTHeader.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleTHeader841); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleTHeader"


    // $ANTLR start "ruleTHeader"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:503:1: ruleTHeader returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : (kw= 'A' kw= 'B' kw= 'C' kw= 'D' kw= 'E' kw= 'F' kw= 'G' kw= 'H' ) ;
    public final AntlrDatatypeRuleToken ruleTHeader() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token kw=null;

         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:506:28: ( (kw= 'A' kw= 'B' kw= 'C' kw= 'D' kw= 'E' kw= 'F' kw= 'G' kw= 'H' ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:507:1: (kw= 'A' kw= 'B' kw= 'C' kw= 'D' kw= 'E' kw= 'F' kw= 'G' kw= 'H' )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:507:1: (kw= 'A' kw= 'B' kw= 'C' kw= 'D' kw= 'E' kw= 'F' kw= 'G' kw= 'H' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:508:2: kw= 'A' kw= 'B' kw= 'C' kw= 'D' kw= 'E' kw= 'F' kw= 'G' kw= 'H'
            {
            kw=(Token)match(input,18,FOLLOW_18_in_ruleTHeader879); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getAKeyword_0()); 
                
            kw=(Token)match(input,19,FOLLOW_19_in_ruleTHeader892); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getBKeyword_1()); 
                
            kw=(Token)match(input,20,FOLLOW_20_in_ruleTHeader905); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getCKeyword_2()); 
                
            kw=(Token)match(input,21,FOLLOW_21_in_ruleTHeader918); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getDKeyword_3()); 
                
            kw=(Token)match(input,22,FOLLOW_22_in_ruleTHeader931); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getEKeyword_4()); 
                
            kw=(Token)match(input,23,FOLLOW_23_in_ruleTHeader944); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getFKeyword_5()); 
                
            kw=(Token)match(input,24,FOLLOW_24_in_ruleTHeader957); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getGKeyword_6()); 
                
            kw=(Token)match(input,25,FOLLOW_25_in_ruleTHeader970); 

                    current.merge(kw);
                    newLeafNode(kw, grammarAccess.getTHeaderAccess().getHKeyword_7()); 
                

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleTHeader"


    // $ANTLR start "entryRuleRow"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:563:1: entryRuleRow returns [EObject current=null] : iv_ruleRow= ruleRow EOF ;
    public final EObject entryRuleRow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleRow = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:564:2: (iv_ruleRow= ruleRow EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:565:2: iv_ruleRow= ruleRow EOF
            {
             newCompositeNode(grammarAccess.getRowRule()); 
            pushFollow(FOLLOW_ruleRow_in_entryRuleRow1010);
            iv_ruleRow=ruleRow();

            state._fsp--;

             current =iv_ruleRow; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRow1020); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRow"


    // $ANTLR start "ruleRow"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:572:1: ruleRow returns [EObject current=null] : ( ( (lv_name_0_0= ruleRowLabel ) ) ( (lv_midrow_1_0= ruleMRow ) ) ( (lv_endrow_2_0= ruleMRow ) ) ) ;
    public final EObject ruleRow() throws RecognitionException {
        EObject current = null;

        AntlrDatatypeRuleToken lv_name_0_0 = null;

        EObject lv_midrow_1_0 = null;

        EObject lv_endrow_2_0 = null;


         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:575:28: ( ( ( (lv_name_0_0= ruleRowLabel ) ) ( (lv_midrow_1_0= ruleMRow ) ) ( (lv_endrow_2_0= ruleMRow ) ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:576:1: ( ( (lv_name_0_0= ruleRowLabel ) ) ( (lv_midrow_1_0= ruleMRow ) ) ( (lv_endrow_2_0= ruleMRow ) ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:576:1: ( ( (lv_name_0_0= ruleRowLabel ) ) ( (lv_midrow_1_0= ruleMRow ) ) ( (lv_endrow_2_0= ruleMRow ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:576:2: ( (lv_name_0_0= ruleRowLabel ) ) ( (lv_midrow_1_0= ruleMRow ) ) ( (lv_endrow_2_0= ruleMRow ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:576:2: ( (lv_name_0_0= ruleRowLabel ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:577:1: (lv_name_0_0= ruleRowLabel )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:577:1: (lv_name_0_0= ruleRowLabel )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:578:3: lv_name_0_0= ruleRowLabel
            {
             
            	        newCompositeNode(grammarAccess.getRowAccess().getNameRowLabelParserRuleCall_0_0()); 
            	    
            pushFollow(FOLLOW_ruleRowLabel_in_ruleRow1066);
            lv_name_0_0=ruleRowLabel();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRowRule());
            	        }
                   		set(
                   			current, 
                   			"name",
                    		lv_name_0_0, 
                    		"RowLabel");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:594:2: ( (lv_midrow_1_0= ruleMRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:595:1: (lv_midrow_1_0= ruleMRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:595:1: (lv_midrow_1_0= ruleMRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:596:3: lv_midrow_1_0= ruleMRow
            {
             
            	        newCompositeNode(grammarAccess.getRowAccess().getMidrowMRowParserRuleCall_1_0()); 
            	    
            pushFollow(FOLLOW_ruleMRow_in_ruleRow1087);
            lv_midrow_1_0=ruleMRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRowRule());
            	        }
                   		set(
                   			current, 
                   			"midrow",
                    		lv_midrow_1_0, 
                    		"MRow");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:612:2: ( (lv_endrow_2_0= ruleMRow ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:613:1: (lv_endrow_2_0= ruleMRow )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:613:1: (lv_endrow_2_0= ruleMRow )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:614:3: lv_endrow_2_0= ruleMRow
            {
             
            	        newCompositeNode(grammarAccess.getRowAccess().getEndrowMRowParserRuleCall_2_0()); 
            	    
            pushFollow(FOLLOW_ruleMRow_in_ruleRow1108);
            lv_endrow_2_0=ruleMRow();

            state._fsp--;


            	        if (current==null) {
            	            current = createModelElementForParent(grammarAccess.getRowRule());
            	        }
                   		set(
                   			current, 
                   			"endrow",
                    		lv_endrow_2_0, 
                    		"MRow");
            	        afterParserOrEnumRuleCall();
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRow"


    // $ANTLR start "entryRuleRowLabel"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:638:1: entryRuleRowLabel returns [String current=null] : iv_ruleRowLabel= ruleRowLabel EOF ;
    public final String entryRuleRowLabel() throws RecognitionException {
        String current = null;

        AntlrDatatypeRuleToken iv_ruleRowLabel = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:639:2: (iv_ruleRowLabel= ruleRowLabel EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:640:2: iv_ruleRowLabel= ruleRowLabel EOF
            {
             newCompositeNode(grammarAccess.getRowLabelRule()); 
            pushFollow(FOLLOW_ruleRowLabel_in_entryRuleRowLabel1145);
            iv_ruleRowLabel=ruleRowLabel();

            state._fsp--;

             current =iv_ruleRowLabel.getText(); 
            match(input,EOF,FOLLOW_EOF_in_entryRuleRowLabel1156); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleRowLabel"


    // $ANTLR start "ruleRowLabel"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:647:1: ruleRowLabel returns [AntlrDatatypeRuleToken current=new AntlrDatatypeRuleToken()] : this_INT_0= RULE_INT ;
    public final AntlrDatatypeRuleToken ruleRowLabel() throws RecognitionException {
        AntlrDatatypeRuleToken current = new AntlrDatatypeRuleToken();

        Token this_INT_0=null;

         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:650:28: (this_INT_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:651:5: this_INT_0= RULE_INT
            {
            this_INT_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleRowLabel1195); 

            		current.merge(this_INT_0);
                
             
                newLeafNode(this_INT_0, grammarAccess.getRowLabelAccess().getINTTerminalRuleCall()); 
                

            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleRowLabel"


    // $ANTLR start "entryRuleMRow"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:666:1: entryRuleMRow returns [EObject current=null] : iv_ruleMRow= ruleMRow EOF ;
    public final EObject entryRuleMRow() throws RecognitionException {
        EObject current = null;

        EObject iv_ruleMRow = null;


        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:667:2: (iv_ruleMRow= ruleMRow EOF )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:668:2: iv_ruleMRow= ruleMRow EOF
            {
             newCompositeNode(grammarAccess.getMRowRule()); 
            pushFollow(FOLLOW_ruleMRow_in_entryRuleMRow1239);
            iv_ruleMRow=ruleMRow();

            state._fsp--;

             current =iv_ruleMRow; 
            match(input,EOF,FOLLOW_EOF_in_entryRuleMRow1249); 

            }

        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "entryRuleMRow"


    // $ANTLR start "ruleMRow"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:675:1: ruleMRow returns [EObject current=null] : ( ( (lv_c1_0_0= RULE_INT ) ) ( (lv_c2_1_0= RULE_INT ) ) ( (lv_c3_2_0= RULE_INT ) ) ( (lv_c4_3_0= RULE_INT ) ) ( (lv_c5_4_0= RULE_INT ) ) ( (lv_c6_5_0= RULE_INT ) ) ( (lv_c7_6_0= RULE_INT ) ) ( (lv_c8_7_0= RULE_INT ) ) ) ;
    public final EObject ruleMRow() throws RecognitionException {
        EObject current = null;

        Token lv_c1_0_0=null;
        Token lv_c2_1_0=null;
        Token lv_c3_2_0=null;
        Token lv_c4_3_0=null;
        Token lv_c5_4_0=null;
        Token lv_c6_5_0=null;
        Token lv_c7_6_0=null;
        Token lv_c8_7_0=null;

         enterRule(); 
            
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:678:28: ( ( ( (lv_c1_0_0= RULE_INT ) ) ( (lv_c2_1_0= RULE_INT ) ) ( (lv_c3_2_0= RULE_INT ) ) ( (lv_c4_3_0= RULE_INT ) ) ( (lv_c5_4_0= RULE_INT ) ) ( (lv_c6_5_0= RULE_INT ) ) ( (lv_c7_6_0= RULE_INT ) ) ( (lv_c8_7_0= RULE_INT ) ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:679:1: ( ( (lv_c1_0_0= RULE_INT ) ) ( (lv_c2_1_0= RULE_INT ) ) ( (lv_c3_2_0= RULE_INT ) ) ( (lv_c4_3_0= RULE_INT ) ) ( (lv_c5_4_0= RULE_INT ) ) ( (lv_c6_5_0= RULE_INT ) ) ( (lv_c7_6_0= RULE_INT ) ) ( (lv_c8_7_0= RULE_INT ) ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:679:1: ( ( (lv_c1_0_0= RULE_INT ) ) ( (lv_c2_1_0= RULE_INT ) ) ( (lv_c3_2_0= RULE_INT ) ) ( (lv_c4_3_0= RULE_INT ) ) ( (lv_c5_4_0= RULE_INT ) ) ( (lv_c6_5_0= RULE_INT ) ) ( (lv_c7_6_0= RULE_INT ) ) ( (lv_c8_7_0= RULE_INT ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:679:2: ( (lv_c1_0_0= RULE_INT ) ) ( (lv_c2_1_0= RULE_INT ) ) ( (lv_c3_2_0= RULE_INT ) ) ( (lv_c4_3_0= RULE_INT ) ) ( (lv_c5_4_0= RULE_INT ) ) ( (lv_c6_5_0= RULE_INT ) ) ( (lv_c7_6_0= RULE_INT ) ) ( (lv_c8_7_0= RULE_INT ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:679:2: ( (lv_c1_0_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:680:1: (lv_c1_0_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:680:1: (lv_c1_0_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:681:3: lv_c1_0_0= RULE_INT
            {
            lv_c1_0_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1291); 

            			newLeafNode(lv_c1_0_0, grammarAccess.getMRowAccess().getC1INTTerminalRuleCall_0_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c1",
                    		lv_c1_0_0, 
                    		"INT");
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:697:2: ( (lv_c2_1_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:698:1: (lv_c2_1_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:698:1: (lv_c2_1_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:699:3: lv_c2_1_0= RULE_INT
            {
            lv_c2_1_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1313); 

            			newLeafNode(lv_c2_1_0, grammarAccess.getMRowAccess().getC2INTTerminalRuleCall_1_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c2",
                    		lv_c2_1_0, 
                    		"INT");
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:715:2: ( (lv_c3_2_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:716:1: (lv_c3_2_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:716:1: (lv_c3_2_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:717:3: lv_c3_2_0= RULE_INT
            {
            lv_c3_2_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1335); 

            			newLeafNode(lv_c3_2_0, grammarAccess.getMRowAccess().getC3INTTerminalRuleCall_2_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c3",
                    		lv_c3_2_0, 
                    		"INT");
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:733:2: ( (lv_c4_3_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:734:1: (lv_c4_3_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:734:1: (lv_c4_3_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:735:3: lv_c4_3_0= RULE_INT
            {
            lv_c4_3_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1357); 

            			newLeafNode(lv_c4_3_0, grammarAccess.getMRowAccess().getC4INTTerminalRuleCall_3_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c4",
                    		lv_c4_3_0, 
                    		"INT");
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:751:2: ( (lv_c5_4_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:752:1: (lv_c5_4_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:752:1: (lv_c5_4_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:753:3: lv_c5_4_0= RULE_INT
            {
            lv_c5_4_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1379); 

            			newLeafNode(lv_c5_4_0, grammarAccess.getMRowAccess().getC5INTTerminalRuleCall_4_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c5",
                    		lv_c5_4_0, 
                    		"INT");
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:769:2: ( (lv_c6_5_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:770:1: (lv_c6_5_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:770:1: (lv_c6_5_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:771:3: lv_c6_5_0= RULE_INT
            {
            lv_c6_5_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1401); 

            			newLeafNode(lv_c6_5_0, grammarAccess.getMRowAccess().getC6INTTerminalRuleCall_5_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c6",
                    		lv_c6_5_0, 
                    		"INT");
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:787:2: ( (lv_c7_6_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:788:1: (lv_c7_6_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:788:1: (lv_c7_6_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:789:3: lv_c7_6_0= RULE_INT
            {
            lv_c7_6_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1423); 

            			newLeafNode(lv_c7_6_0, grammarAccess.getMRowAccess().getC7INTTerminalRuleCall_6_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c7",
                    		lv_c7_6_0, 
                    		"INT");
            	    

            }


            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:805:2: ( (lv_c8_7_0= RULE_INT ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:806:1: (lv_c8_7_0= RULE_INT )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:806:1: (lv_c8_7_0= RULE_INT )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:807:3: lv_c8_7_0= RULE_INT
            {
            lv_c8_7_0=(Token)match(input,RULE_INT,FOLLOW_RULE_INT_in_ruleMRow1445); 

            			newLeafNode(lv_c8_7_0, grammarAccess.getMRowAccess().getC8INTTerminalRuleCall_7_0()); 
            		

            	        if (current==null) {
            	            current = createModelElement(grammarAccess.getMRowRule());
            	        }
                   		setWithLastConsumed(
                   			current, 
                   			"c8",
                    		lv_c8_7_0, 
                    		"INT");
            	    

            }


            }


            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "ruleMRow"


    // $ANTLR start "rulePieceType"
    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:831:1: rulePieceType returns [Enumerator current=null] : ( (enumLiteral_0= 'Pawn' ) | (enumLiteral_1= 'Knight' ) | (enumLiteral_2= 'Bishop' ) | (enumLiteral_3= 'Rook' ) | (enumLiteral_4= 'Queen' ) | (enumLiteral_5= 'King' ) ) ;
    public final Enumerator rulePieceType() throws RecognitionException {
        Enumerator current = null;

        Token enumLiteral_0=null;
        Token enumLiteral_1=null;
        Token enumLiteral_2=null;
        Token enumLiteral_3=null;
        Token enumLiteral_4=null;
        Token enumLiteral_5=null;

         enterRule(); 
        try {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:833:28: ( ( (enumLiteral_0= 'Pawn' ) | (enumLiteral_1= 'Knight' ) | (enumLiteral_2= 'Bishop' ) | (enumLiteral_3= 'Rook' ) | (enumLiteral_4= 'Queen' ) | (enumLiteral_5= 'King' ) ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:834:1: ( (enumLiteral_0= 'Pawn' ) | (enumLiteral_1= 'Knight' ) | (enumLiteral_2= 'Bishop' ) | (enumLiteral_3= 'Rook' ) | (enumLiteral_4= 'Queen' ) | (enumLiteral_5= 'King' ) )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:834:1: ( (enumLiteral_0= 'Pawn' ) | (enumLiteral_1= 'Knight' ) | (enumLiteral_2= 'Bishop' ) | (enumLiteral_3= 'Rook' ) | (enumLiteral_4= 'Queen' ) | (enumLiteral_5= 'King' ) )
            int alt2=6;
            switch ( input.LA(1) ) {
            case 26:
                {
                alt2=1;
                }
                break;
            case 27:
                {
                alt2=2;
                }
                break;
            case 28:
                {
                alt2=3;
                }
                break;
            case 29:
                {
                alt2=4;
                }
                break;
            case 30:
                {
                alt2=5;
                }
                break;
            case 31:
                {
                alt2=6;
                }
                break;
            default:
                NoViableAltException nvae =
                    new NoViableAltException("", 2, 0, input);

                throw nvae;
            }

            switch (alt2) {
                case 1 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:834:2: (enumLiteral_0= 'Pawn' )
                    {
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:834:2: (enumLiteral_0= 'Pawn' )
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:834:4: enumLiteral_0= 'Pawn'
                    {
                    enumLiteral_0=(Token)match(input,26,FOLLOW_26_in_rulePieceType1500); 

                            current = grammarAccess.getPieceTypeAccess().getPAWNEnumLiteralDeclaration_0().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_0, grammarAccess.getPieceTypeAccess().getPAWNEnumLiteralDeclaration_0()); 
                        

                    }


                    }
                    break;
                case 2 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:840:6: (enumLiteral_1= 'Knight' )
                    {
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:840:6: (enumLiteral_1= 'Knight' )
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:840:8: enumLiteral_1= 'Knight'
                    {
                    enumLiteral_1=(Token)match(input,27,FOLLOW_27_in_rulePieceType1517); 

                            current = grammarAccess.getPieceTypeAccess().getKNIGHTEnumLiteralDeclaration_1().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_1, grammarAccess.getPieceTypeAccess().getKNIGHTEnumLiteralDeclaration_1()); 
                        

                    }


                    }
                    break;
                case 3 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:846:6: (enumLiteral_2= 'Bishop' )
                    {
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:846:6: (enumLiteral_2= 'Bishop' )
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:846:8: enumLiteral_2= 'Bishop'
                    {
                    enumLiteral_2=(Token)match(input,28,FOLLOW_28_in_rulePieceType1534); 

                            current = grammarAccess.getPieceTypeAccess().getBISHOPEnumLiteralDeclaration_2().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_2, grammarAccess.getPieceTypeAccess().getBISHOPEnumLiteralDeclaration_2()); 
                        

                    }


                    }
                    break;
                case 4 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:852:6: (enumLiteral_3= 'Rook' )
                    {
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:852:6: (enumLiteral_3= 'Rook' )
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:852:8: enumLiteral_3= 'Rook'
                    {
                    enumLiteral_3=(Token)match(input,29,FOLLOW_29_in_rulePieceType1551); 

                            current = grammarAccess.getPieceTypeAccess().getROOKEnumLiteralDeclaration_3().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_3, grammarAccess.getPieceTypeAccess().getROOKEnumLiteralDeclaration_3()); 
                        

                    }


                    }
                    break;
                case 5 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:858:6: (enumLiteral_4= 'Queen' )
                    {
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:858:6: (enumLiteral_4= 'Queen' )
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:858:8: enumLiteral_4= 'Queen'
                    {
                    enumLiteral_4=(Token)match(input,30,FOLLOW_30_in_rulePieceType1568); 

                            current = grammarAccess.getPieceTypeAccess().getQUEENEnumLiteralDeclaration_4().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_4, grammarAccess.getPieceTypeAccess().getQUEENEnumLiteralDeclaration_4()); 
                        

                    }


                    }
                    break;
                case 6 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:864:6: (enumLiteral_5= 'King' )
                    {
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:864:6: (enumLiteral_5= 'King' )
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:864:8: enumLiteral_5= 'King'
                    {
                    enumLiteral_5=(Token)match(input,31,FOLLOW_31_in_rulePieceType1585); 

                            current = grammarAccess.getPieceTypeAccess().getKINGEnumLiteralDeclaration_5().getEnumLiteral().getInstance();
                            newLeafNode(enumLiteral_5, grammarAccess.getPieceTypeAccess().getKINGEnumLiteralDeclaration_5()); 
                        

                    }


                    }
                    break;

            }


            }

             leaveRule(); 
        }
         
            catch (RecognitionException re) { 
                recover(input,re); 
                appendSkippedTokens();
            } 
        finally {
        }
        return current;
    }
    // $ANTLR end "rulePieceType"

    // Delegated rules


 

    public static final BitSet FOLLOW_rulePSQT_Model_in_entryRulePSQT_Model75 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRulePSQT_Model85 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleFDescription_in_rulePSQT_Model132 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_ruleTable_in_rulePSQT_Model152 = new BitSet(new long[]{0x0000000000000402L});
    public static final BitSet FOLLOW_ruleFDescription_in_entryRuleFDescription189 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleFDescription199 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_PSQT_in_ruleFDescription235 = new BitSet(new long[]{0x0000000000000020L});
    public static final BitSet FOLLOW_RULE_ID_in_ruleFDescription251 = new BitSet(new long[]{0x0000000000000040L});
    public static final BitSet FOLLOW_RULE_BASE_in_ruleFDescription267 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleFDescription283 = new BitSet(new long[]{0x0000000000000100L});
    public static final BitSet FOLLOW_RULE_MIDLIMIT_in_ruleFDescription299 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleFDescription315 = new BitSet(new long[]{0x0000000000000200L});
    public static final BitSet FOLLOW_RULE_ENDLIMIT_in_ruleFDescription331 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleFDescription347 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTable_in_entryRuleTable388 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTable398 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTDescription_in_ruleTable445 = new BitSet(new long[]{0x0000000000040000L});
    public static final BitSet FOLLOW_ruleTHeader_in_ruleTable460 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable480 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable501 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable522 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable543 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable564 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable585 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable606 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleRow_in_ruleTable627 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTDescription_in_entryRuleTDescription663 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTDescription673 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_PIECE_in_ruleTDescription709 = new BitSet(new long[]{0x00000000FC000000L});
    public static final BitSet FOLLOW_rulePieceType_in_ruleTDescription729 = new BitSet(new long[]{0x0000000000000800L});
    public static final BitSet FOLLOW_RULE_MIDGAME_in_ruleTDescription740 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleTDescription756 = new BitSet(new long[]{0x0000000000001000L});
    public static final BitSet FOLLOW_RULE_ENDGAME_in_ruleTDescription772 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleTDescription788 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleTHeader_in_entryRuleTHeader830 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleTHeader841 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_18_in_ruleTHeader879 = new BitSet(new long[]{0x0000000000080000L});
    public static final BitSet FOLLOW_19_in_ruleTHeader892 = new BitSet(new long[]{0x0000000000100000L});
    public static final BitSet FOLLOW_20_in_ruleTHeader905 = new BitSet(new long[]{0x0000000000200000L});
    public static final BitSet FOLLOW_21_in_ruleTHeader918 = new BitSet(new long[]{0x0000000000400000L});
    public static final BitSet FOLLOW_22_in_ruleTHeader931 = new BitSet(new long[]{0x0000000000800000L});
    public static final BitSet FOLLOW_23_in_ruleTHeader944 = new BitSet(new long[]{0x0000000001000000L});
    public static final BitSet FOLLOW_24_in_ruleTHeader957 = new BitSet(new long[]{0x0000000002000000L});
    public static final BitSet FOLLOW_25_in_ruleTHeader970 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRow_in_entryRuleRow1010 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRow1020 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRowLabel_in_ruleRow1066 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleMRow_in_ruleRow1087 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_ruleMRow_in_ruleRow1108 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleRowLabel_in_entryRuleRowLabel1145 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleRowLabel1156 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleRowLabel1195 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_ruleMRow_in_entryRuleMRow1239 = new BitSet(new long[]{0x0000000000000000L});
    public static final BitSet FOLLOW_EOF_in_entryRuleMRow1249 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1291 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1313 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1335 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1357 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1379 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1401 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1423 = new BitSet(new long[]{0x0000000000000080L});
    public static final BitSet FOLLOW_RULE_INT_in_ruleMRow1445 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_26_in_rulePieceType1500 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_27_in_rulePieceType1517 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_28_in_rulePieceType1534 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_29_in_rulePieceType1551 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_30_in_rulePieceType1568 = new BitSet(new long[]{0x0000000000000002L});
    public static final BitSet FOLLOW_31_in_rulePieceType1585 = new BitSet(new long[]{0x0000000000000002L});

}