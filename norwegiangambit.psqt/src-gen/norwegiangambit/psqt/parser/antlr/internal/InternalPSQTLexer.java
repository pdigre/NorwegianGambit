package norwegiangambit.psqt.parser.antlr.internal;

// Hack: Use our own Lexer superclass by means of import. 
// Currently there is no other way to specify the superclass for the lexer.
import org.eclipse.xtext.parser.antlr.Lexer;


import org.antlr.runtime.*;
import java.util.Stack;
import java.util.List;
import java.util.ArrayList;

@SuppressWarnings("all")
public class InternalPSQTLexer extends Lexer {
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

    public InternalPSQTLexer() {;} 
    public InternalPSQTLexer(CharStream input) {
        this(input, new RecognizerSharedState());
    }
    public InternalPSQTLexer(CharStream input, RecognizerSharedState state) {
        super(input,state);

    }
    public String getGrammarFileName() { return "../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g"; }

    // $ANTLR start "T__18"
    public final void mT__18() throws RecognitionException {
        try {
            int _type = T__18;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:11:7: ( 'A' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:11:9: 'A'
            {
            match('A'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__18"

    // $ANTLR start "T__19"
    public final void mT__19() throws RecognitionException {
        try {
            int _type = T__19;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:12:7: ( 'B' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:12:9: 'B'
            {
            match('B'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__19"

    // $ANTLR start "T__20"
    public final void mT__20() throws RecognitionException {
        try {
            int _type = T__20;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:13:7: ( 'C' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:13:9: 'C'
            {
            match('C'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__20"

    // $ANTLR start "T__21"
    public final void mT__21() throws RecognitionException {
        try {
            int _type = T__21;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:14:7: ( 'D' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:14:9: 'D'
            {
            match('D'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__21"

    // $ANTLR start "T__22"
    public final void mT__22() throws RecognitionException {
        try {
            int _type = T__22;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:15:7: ( 'E' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:15:9: 'E'
            {
            match('E'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__22"

    // $ANTLR start "T__23"
    public final void mT__23() throws RecognitionException {
        try {
            int _type = T__23;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:16:7: ( 'F' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:16:9: 'F'
            {
            match('F'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__23"

    // $ANTLR start "T__24"
    public final void mT__24() throws RecognitionException {
        try {
            int _type = T__24;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:17:7: ( 'G' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:17:9: 'G'
            {
            match('G'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__24"

    // $ANTLR start "T__25"
    public final void mT__25() throws RecognitionException {
        try {
            int _type = T__25;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:18:7: ( 'H' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:18:9: 'H'
            {
            match('H'); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__25"

    // $ANTLR start "T__26"
    public final void mT__26() throws RecognitionException {
        try {
            int _type = T__26;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:19:7: ( 'Pawn' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:19:9: 'Pawn'
            {
            match("Pawn"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__26"

    // $ANTLR start "T__27"
    public final void mT__27() throws RecognitionException {
        try {
            int _type = T__27;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:20:7: ( 'Knight' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:20:9: 'Knight'
            {
            match("Knight"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__27"

    // $ANTLR start "T__28"
    public final void mT__28() throws RecognitionException {
        try {
            int _type = T__28;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:21:7: ( 'Bishop' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:21:9: 'Bishop'
            {
            match("Bishop"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__28"

    // $ANTLR start "T__29"
    public final void mT__29() throws RecognitionException {
        try {
            int _type = T__29;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:22:7: ( 'Rook' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:22:9: 'Rook'
            {
            match("Rook"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__29"

    // $ANTLR start "T__30"
    public final void mT__30() throws RecognitionException {
        try {
            int _type = T__30;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:23:7: ( 'Queen' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:23:9: 'Queen'
            {
            match("Queen"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__30"

    // $ANTLR start "T__31"
    public final void mT__31() throws RecognitionException {
        try {
            int _type = T__31;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:24:7: ( 'King' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:24:9: 'King'
            {
            match("King"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "T__31"

    // $ANTLR start "RULE_INT"
    public final void mRULE_INT() throws RecognitionException {
        try {
            int _type = RULE_INT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:873:10: ( ( '-' )? ( '0' .. '9' )+ )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:873:12: ( '-' )? ( '0' .. '9' )+
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:873:12: ( '-' )?
            int alt1=2;
            int LA1_0 = input.LA(1);

            if ( (LA1_0=='-') ) {
                alt1=1;
            }
            switch (alt1) {
                case 1 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:873:12: '-'
                    {
                    match('-'); 

                    }
                    break;

            }

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:873:17: ( '0' .. '9' )+
            int cnt2=0;
            loop2:
            do {
                int alt2=2;
                int LA2_0 = input.LA(1);

                if ( ((LA2_0>='0' && LA2_0<='9')) ) {
                    alt2=1;
                }


                switch (alt2) {
            	case 1 :
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:873:18: '0' .. '9'
            	    {
            	    matchRange('0','9'); 

            	    }
            	    break;

            	default :
            	    if ( cnt2 >= 1 ) break loop2;
                        EarlyExitException eee =
                            new EarlyExitException(2, input);
                        throw eee;
                }
                cnt2++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_INT"

    // $ANTLR start "RULE_PSQT"
    public final void mRULE_PSQT() throws RecognitionException {
        try {
            int _type = RULE_PSQT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:875:11: ( 'PSQT:' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:875:13: 'PSQT:'
            {
            match("PSQT:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_PSQT"

    // $ANTLR start "RULE_BASE"
    public final void mRULE_BASE() throws RecognitionException {
        try {
            int _type = RULE_BASE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:877:11: ( 'Base:' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:877:13: 'Base:'
            {
            match("Base:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_BASE"

    // $ANTLR start "RULE_PIECE"
    public final void mRULE_PIECE() throws RecognitionException {
        try {
            int _type = RULE_PIECE;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:879:12: ( 'Piece:' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:879:14: 'Piece:'
            {
            match("Piece:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_PIECE"

    // $ANTLR start "RULE_MIDLIMIT"
    public final void mRULE_MIDLIMIT() throws RecognitionException {
        try {
            int _type = RULE_MIDLIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:881:15: ( 'MidLimit:' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:881:17: 'MidLimit:'
            {
            match("MidLimit:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_MIDLIMIT"

    // $ANTLR start "RULE_ENDLIMIT"
    public final void mRULE_ENDLIMIT() throws RecognitionException {
        try {
            int _type = RULE_ENDLIMIT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:883:15: ( 'EndLimit:' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:883:17: 'EndLimit:'
            {
            match("EndLimit:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ENDLIMIT"

    // $ANTLR start "RULE_MIDGAME"
    public final void mRULE_MIDGAME() throws RecognitionException {
        try {
            int _type = RULE_MIDGAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:885:14: ( 'MidGame:' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:885:16: 'MidGame:'
            {
            match("MidGame:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_MIDGAME"

    // $ANTLR start "RULE_ENDGAME"
    public final void mRULE_ENDGAME() throws RecognitionException {
        try {
            int _type = RULE_ENDGAME;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:887:14: ( 'EndGame:' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:887:16: 'EndGame:'
            {
            match("EndGame:"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ENDGAME"

    // $ANTLR start "RULE_ID"
    public final void mRULE_ID() throws RecognitionException {
        try {
            int _type = RULE_ID;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:889:9: ( ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )* )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:889:11: ( '^' )? ( 'a' .. 'z' | 'A' .. 'Z' | '_' ) ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:889:11: ( '^' )?
            int alt3=2;
            int LA3_0 = input.LA(1);

            if ( (LA3_0=='^') ) {
                alt3=1;
            }
            switch (alt3) {
                case 1 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:889:11: '^'
                    {
                    match('^'); 

                    }
                    break;

            }

            if ( (input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
                input.consume();

            }
            else {
                MismatchedSetException mse = new MismatchedSetException(null,input);
                recover(mse);
                throw mse;}

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:889:40: ( 'a' .. 'z' | 'A' .. 'Z' | '_' | '0' .. '9' )*
            loop4:
            do {
                int alt4=2;
                int LA4_0 = input.LA(1);

                if ( ((LA4_0>='0' && LA4_0<='9')||(LA4_0>='A' && LA4_0<='Z')||LA4_0=='_'||(LA4_0>='a' && LA4_0<='z')) ) {
                    alt4=1;
                }


                switch (alt4) {
            	case 1 :
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:
            	    {
            	    if ( (input.LA(1)>='0' && input.LA(1)<='9')||(input.LA(1)>='A' && input.LA(1)<='Z')||input.LA(1)=='_'||(input.LA(1)>='a' && input.LA(1)<='z') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop4;
                }
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ID"

    // $ANTLR start "RULE_STRING"
    public final void mRULE_STRING() throws RecognitionException {
        try {
            int _type = RULE_STRING;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:13: ( ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' ) )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:15: ( '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"' | '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\'' )
            int alt7=2;
            int LA7_0 = input.LA(1);

            if ( (LA7_0=='\"') ) {
                alt7=1;
            }
            else if ( (LA7_0=='\'') ) {
                alt7=2;
            }
            else {
                NoViableAltException nvae =
                    new NoViableAltException("", 7, 0, input);

                throw nvae;
            }
            switch (alt7) {
                case 1 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:16: '\"' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )* '\"'
                    {
                    match('\"'); 
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:20: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\"' ) ) )*
                    loop5:
                    do {
                        int alt5=3;
                        int LA5_0 = input.LA(1);

                        if ( (LA5_0=='\\') ) {
                            alt5=1;
                        }
                        else if ( ((LA5_0>='\u0000' && LA5_0<='!')||(LA5_0>='#' && LA5_0<='[')||(LA5_0>=']' && LA5_0<='\uFFFF')) ) {
                            alt5=2;
                        }


                        switch (alt5) {
                    	case 1 :
                    	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:21: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:66: ~ ( ( '\\\\' | '\"' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='!')||(input.LA(1)>='#' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop5;
                        }
                    } while (true);

                    match('\"'); 

                    }
                    break;
                case 2 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:86: '\\'' ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )* '\\''
                    {
                    match('\''); 
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:91: ( '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' ) | ~ ( ( '\\\\' | '\\'' ) ) )*
                    loop6:
                    do {
                        int alt6=3;
                        int LA6_0 = input.LA(1);

                        if ( (LA6_0=='\\') ) {
                            alt6=1;
                        }
                        else if ( ((LA6_0>='\u0000' && LA6_0<='&')||(LA6_0>='(' && LA6_0<='[')||(LA6_0>=']' && LA6_0<='\uFFFF')) ) {
                            alt6=2;
                        }


                        switch (alt6) {
                    	case 1 :
                    	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:92: '\\\\' ( 'b' | 't' | 'n' | 'f' | 'r' | 'u' | '\"' | '\\'' | '\\\\' )
                    	    {
                    	    match('\\'); 
                    	    if ( input.LA(1)=='\"'||input.LA(1)=='\''||input.LA(1)=='\\'||input.LA(1)=='b'||input.LA(1)=='f'||input.LA(1)=='n'||input.LA(1)=='r'||(input.LA(1)>='t' && input.LA(1)<='u') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;
                    	case 2 :
                    	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:891:137: ~ ( ( '\\\\' | '\\'' ) )
                    	    {
                    	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='&')||(input.LA(1)>='(' && input.LA(1)<='[')||(input.LA(1)>=']' && input.LA(1)<='\uFFFF') ) {
                    	        input.consume();

                    	    }
                    	    else {
                    	        MismatchedSetException mse = new MismatchedSetException(null,input);
                    	        recover(mse);
                    	        throw mse;}


                    	    }
                    	    break;

                    	default :
                    	    break loop6;
                        }
                    } while (true);

                    match('\''); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_STRING"

    // $ANTLR start "RULE_ML_COMMENT"
    public final void mRULE_ML_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_ML_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:893:17: ( '/*' ( options {greedy=false; } : . )* '*/' )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:893:19: '/*' ( options {greedy=false; } : . )* '*/'
            {
            match("/*"); 

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:893:24: ( options {greedy=false; } : . )*
            loop8:
            do {
                int alt8=2;
                int LA8_0 = input.LA(1);

                if ( (LA8_0=='*') ) {
                    int LA8_1 = input.LA(2);

                    if ( (LA8_1=='/') ) {
                        alt8=2;
                    }
                    else if ( ((LA8_1>='\u0000' && LA8_1<='.')||(LA8_1>='0' && LA8_1<='\uFFFF')) ) {
                        alt8=1;
                    }


                }
                else if ( ((LA8_0>='\u0000' && LA8_0<=')')||(LA8_0>='+' && LA8_0<='\uFFFF')) ) {
                    alt8=1;
                }


                switch (alt8) {
            	case 1 :
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:893:52: .
            	    {
            	    matchAny(); 

            	    }
            	    break;

            	default :
            	    break loop8;
                }
            } while (true);

            match("*/"); 


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ML_COMMENT"

    // $ANTLR start "RULE_SL_COMMENT"
    public final void mRULE_SL_COMMENT() throws RecognitionException {
        try {
            int _type = RULE_SL_COMMENT;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:17: ( '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )? )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:19: '//' (~ ( ( '\\n' | '\\r' ) ) )* ( ( '\\r' )? '\\n' )?
            {
            match("//"); 

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:24: (~ ( ( '\\n' | '\\r' ) ) )*
            loop9:
            do {
                int alt9=2;
                int LA9_0 = input.LA(1);

                if ( ((LA9_0>='\u0000' && LA9_0<='\t')||(LA9_0>='\u000B' && LA9_0<='\f')||(LA9_0>='\u000E' && LA9_0<='\uFFFF')) ) {
                    alt9=1;
                }


                switch (alt9) {
            	case 1 :
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:24: ~ ( ( '\\n' | '\\r' ) )
            	    {
            	    if ( (input.LA(1)>='\u0000' && input.LA(1)<='\t')||(input.LA(1)>='\u000B' && input.LA(1)<='\f')||(input.LA(1)>='\u000E' && input.LA(1)<='\uFFFF') ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    break loop9;
                }
            } while (true);

            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:40: ( ( '\\r' )? '\\n' )?
            int alt11=2;
            int LA11_0 = input.LA(1);

            if ( (LA11_0=='\n'||LA11_0=='\r') ) {
                alt11=1;
            }
            switch (alt11) {
                case 1 :
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:41: ( '\\r' )? '\\n'
                    {
                    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:41: ( '\\r' )?
                    int alt10=2;
                    int LA10_0 = input.LA(1);

                    if ( (LA10_0=='\r') ) {
                        alt10=1;
                    }
                    switch (alt10) {
                        case 1 :
                            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:895:41: '\\r'
                            {
                            match('\r'); 

                            }
                            break;

                    }

                    match('\n'); 

                    }
                    break;

            }


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_SL_COMMENT"

    // $ANTLR start "RULE_WS"
    public final void mRULE_WS() throws RecognitionException {
        try {
            int _type = RULE_WS;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:897:9: ( ( ' ' | '\\t' | '\\r' | '\\n' )+ )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:897:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            {
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:897:11: ( ' ' | '\\t' | '\\r' | '\\n' )+
            int cnt12=0;
            loop12:
            do {
                int alt12=2;
                int LA12_0 = input.LA(1);

                if ( ((LA12_0>='\t' && LA12_0<='\n')||LA12_0=='\r'||LA12_0==' ') ) {
                    alt12=1;
                }


                switch (alt12) {
            	case 1 :
            	    // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:
            	    {
            	    if ( (input.LA(1)>='\t' && input.LA(1)<='\n')||input.LA(1)=='\r'||input.LA(1)==' ' ) {
            	        input.consume();

            	    }
            	    else {
            	        MismatchedSetException mse = new MismatchedSetException(null,input);
            	        recover(mse);
            	        throw mse;}


            	    }
            	    break;

            	default :
            	    if ( cnt12 >= 1 ) break loop12;
                        EarlyExitException eee =
                            new EarlyExitException(12, input);
                        throw eee;
                }
                cnt12++;
            } while (true);


            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_WS"

    // $ANTLR start "RULE_ANY_OTHER"
    public final void mRULE_ANY_OTHER() throws RecognitionException {
        try {
            int _type = RULE_ANY_OTHER;
            int _channel = DEFAULT_TOKEN_CHANNEL;
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:899:16: ( . )
            // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:899:18: .
            {
            matchAny(); 

            }

            state.type = _type;
            state.channel = _channel;
        }
        finally {
        }
    }
    // $ANTLR end "RULE_ANY_OTHER"

    public void mTokens() throws RecognitionException {
        // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:8: ( T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | RULE_INT | RULE_PSQT | RULE_BASE | RULE_PIECE | RULE_MIDLIMIT | RULE_ENDLIMIT | RULE_MIDGAME | RULE_ENDGAME | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER )
        int alt13=28;
        alt13 = dfa13.predict(input);
        switch (alt13) {
            case 1 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:10: T__18
                {
                mT__18(); 

                }
                break;
            case 2 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:16: T__19
                {
                mT__19(); 

                }
                break;
            case 3 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:22: T__20
                {
                mT__20(); 

                }
                break;
            case 4 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:28: T__21
                {
                mT__21(); 

                }
                break;
            case 5 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:34: T__22
                {
                mT__22(); 

                }
                break;
            case 6 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:40: T__23
                {
                mT__23(); 

                }
                break;
            case 7 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:46: T__24
                {
                mT__24(); 

                }
                break;
            case 8 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:52: T__25
                {
                mT__25(); 

                }
                break;
            case 9 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:58: T__26
                {
                mT__26(); 

                }
                break;
            case 10 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:64: T__27
                {
                mT__27(); 

                }
                break;
            case 11 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:70: T__28
                {
                mT__28(); 

                }
                break;
            case 12 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:76: T__29
                {
                mT__29(); 

                }
                break;
            case 13 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:82: T__30
                {
                mT__30(); 

                }
                break;
            case 14 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:88: T__31
                {
                mT__31(); 

                }
                break;
            case 15 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:94: RULE_INT
                {
                mRULE_INT(); 

                }
                break;
            case 16 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:103: RULE_PSQT
                {
                mRULE_PSQT(); 

                }
                break;
            case 17 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:113: RULE_BASE
                {
                mRULE_BASE(); 

                }
                break;
            case 18 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:123: RULE_PIECE
                {
                mRULE_PIECE(); 

                }
                break;
            case 19 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:134: RULE_MIDLIMIT
                {
                mRULE_MIDLIMIT(); 

                }
                break;
            case 20 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:148: RULE_ENDLIMIT
                {
                mRULE_ENDLIMIT(); 

                }
                break;
            case 21 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:162: RULE_MIDGAME
                {
                mRULE_MIDGAME(); 

                }
                break;
            case 22 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:175: RULE_ENDGAME
                {
                mRULE_ENDGAME(); 

                }
                break;
            case 23 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:188: RULE_ID
                {
                mRULE_ID(); 

                }
                break;
            case 24 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:196: RULE_STRING
                {
                mRULE_STRING(); 

                }
                break;
            case 25 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:208: RULE_ML_COMMENT
                {
                mRULE_ML_COMMENT(); 

                }
                break;
            case 26 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:224: RULE_SL_COMMENT
                {
                mRULE_SL_COMMENT(); 

                }
                break;
            case 27 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:240: RULE_WS
                {
                mRULE_WS(); 

                }
                break;
            case 28 :
                // ../norwegiangambit.psqt/src-gen/norwegiangambit/psqt/parser/antlr/internal/InternalPSQT.g:1:248: RULE_ANY_OTHER
                {
                mRULE_ANY_OTHER(); 

                }
                break;

        }

    }


    protected DFA13 dfa13 = new DFA13(this);
    static final String DFA13_eotS =
        "\1\uffff\1\27\1\33\1\34\1\35\1\37\1\40\1\41\1\42\4\30\1\26\1\uffff"+
        "\1\30\1\26\1\uffff\3\26\4\uffff\2\30\3\uffff\1\30\4\uffff\7\30\1"+
        "\uffff\1\30\4\uffff\17\30\1\114\3\30\1\120\1\121\4\30\1\uffff\2"+
        "\30\2\uffff\2\30\2\uffff\1\132\2\30\1\135\2\30\1\uffff\1\140\1\uffff"+
        "\2\30\1\uffff\2\30\1\uffff\3\30\1\uffff\1\30\3\uffff";
    static final String DFA13_eofS =
        "\151\uffff";
    static final String DFA13_minS =
        "\1\0\10\60\1\123\1\151\1\157\1\165\1\60\1\uffff\1\151\1\101\1\uffff"+
        "\2\0\1\52\4\uffff\2\163\3\uffff\1\144\4\uffff\1\167\1\121\1\145"+
        "\1\151\1\156\1\157\1\145\1\uffff\1\144\4\uffff\1\150\1\145\1\107"+
        "\1\156\1\124\1\143\2\147\1\153\1\145\1\107\1\157\1\72\1\151\1\141"+
        "\1\60\1\72\1\145\1\150\2\60\1\156\1\151\1\141\1\160\1\uffff\2\155"+
        "\2\uffff\1\72\1\164\2\uffff\1\60\2\155\1\60\1\151\1\145\1\uffff"+
        "\1\60\1\uffff\1\151\1\145\1\uffff\1\164\1\72\1\uffff\1\164\2\72"+
        "\1\uffff\1\72\3\uffff";
    static final String DFA13_maxS =
        "\1\uffff\10\172\1\151\1\156\1\157\1\165\1\71\1\uffff\1\151\1\172"+
        "\1\uffff\2\uffff\1\57\4\uffff\2\163\3\uffff\1\144\4\uffff\1\167"+
        "\1\121\1\145\1\151\1\156\1\157\1\145\1\uffff\1\144\4\uffff\1\150"+
        "\1\145\1\114\1\156\1\124\1\143\2\147\1\153\1\145\1\114\1\157\1\72"+
        "\1\151\1\141\1\172\1\72\1\145\1\150\2\172\1\156\1\151\1\141\1\160"+
        "\1\uffff\2\155\2\uffff\1\72\1\164\2\uffff\1\172\2\155\1\172\1\151"+
        "\1\145\1\uffff\1\172\1\uffff\1\151\1\145\1\uffff\1\164\1\72\1\uffff"+
        "\1\164\2\72\1\uffff\1\72\3\uffff";
    static final String DFA13_acceptS =
        "\16\uffff\1\17\2\uffff\1\27\3\uffff\1\33\1\34\1\1\1\27\2\uffff"+
        "\1\2\1\3\1\4\1\uffff\1\5\1\6\1\7\1\10\7\uffff\1\17\1\uffff\1\30"+
        "\1\31\1\32\1\33\31\uffff\1\21\2\uffff\1\11\1\20\2\uffff\1\16\1\14"+
        "\6\uffff\1\22\1\uffff\1\15\2\uffff\1\13\2\uffff\1\12\3\uffff\1\26"+
        "\1\uffff\1\25\1\24\1\23";
    static final String DFA13_specialS =
        "\1\2\21\uffff\1\1\1\0\125\uffff}>";
    static final String[] DFA13_transitionS = {
            "\11\26\2\25\2\26\1\25\22\26\1\25\1\26\1\22\4\26\1\23\5\26\1"+
            "\15\1\26\1\24\12\16\7\26\1\1\1\2\1\3\1\4\1\5\1\6\1\7\1\10\2"+
            "\21\1\12\1\21\1\17\2\21\1\11\1\14\1\13\10\21\3\26\1\20\1\21"+
            "\1\26\32\21\uff85\26",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\1\32\7\30\1\31\21"+
            "\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\15\30\1\36\14\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\44\15\uffff\1\43\7\uffff\1\45",
            "\1\47\4\uffff\1\46",
            "\1\50",
            "\1\51",
            "\12\52",
            "",
            "\1\53",
            "\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "\0\54",
            "\0\54",
            "\1\55\4\uffff\1\56",
            "",
            "",
            "",
            "",
            "\1\60",
            "\1\61",
            "",
            "",
            "",
            "\1\62",
            "",
            "",
            "",
            "",
            "\1\63",
            "\1\64",
            "\1\65",
            "\1\66",
            "\1\67",
            "\1\70",
            "\1\71",
            "",
            "\1\72",
            "",
            "",
            "",
            "",
            "\1\73",
            "\1\74",
            "\1\76\4\uffff\1\75",
            "\1\77",
            "\1\100",
            "\1\101",
            "\1\102",
            "\1\103",
            "\1\104",
            "\1\105",
            "\1\107\4\uffff\1\106",
            "\1\110",
            "\1\111",
            "\1\112",
            "\1\113",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\115",
            "\1\116",
            "\1\117",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\122",
            "\1\123",
            "\1\124",
            "\1\125",
            "",
            "\1\126",
            "\1\127",
            "",
            "",
            "\1\130",
            "\1\131",
            "",
            "",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\133",
            "\1\134",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "\1\136",
            "\1\137",
            "",
            "\12\30\7\uffff\32\30\4\uffff\1\30\1\uffff\32\30",
            "",
            "\1\141",
            "\1\142",
            "",
            "\1\143",
            "\1\144",
            "",
            "\1\145",
            "\1\146",
            "\1\147",
            "",
            "\1\150",
            "",
            "",
            ""
    };

    static final short[] DFA13_eot = DFA.unpackEncodedString(DFA13_eotS);
    static final short[] DFA13_eof = DFA.unpackEncodedString(DFA13_eofS);
    static final char[] DFA13_min = DFA.unpackEncodedStringToUnsignedChars(DFA13_minS);
    static final char[] DFA13_max = DFA.unpackEncodedStringToUnsignedChars(DFA13_maxS);
    static final short[] DFA13_accept = DFA.unpackEncodedString(DFA13_acceptS);
    static final short[] DFA13_special = DFA.unpackEncodedString(DFA13_specialS);
    static final short[][] DFA13_transition;

    static {
        int numStates = DFA13_transitionS.length;
        DFA13_transition = new short[numStates][];
        for (int i=0; i<numStates; i++) {
            DFA13_transition[i] = DFA.unpackEncodedString(DFA13_transitionS[i]);
        }
    }

    class DFA13 extends DFA {

        public DFA13(BaseRecognizer recognizer) {
            this.recognizer = recognizer;
            this.decisionNumber = 13;
            this.eot = DFA13_eot;
            this.eof = DFA13_eof;
            this.min = DFA13_min;
            this.max = DFA13_max;
            this.accept = DFA13_accept;
            this.special = DFA13_special;
            this.transition = DFA13_transition;
        }
        public String getDescription() {
            return "1:1: Tokens : ( T__18 | T__19 | T__20 | T__21 | T__22 | T__23 | T__24 | T__25 | T__26 | T__27 | T__28 | T__29 | T__30 | T__31 | RULE_INT | RULE_PSQT | RULE_BASE | RULE_PIECE | RULE_MIDLIMIT | RULE_ENDLIMIT | RULE_MIDGAME | RULE_ENDGAME | RULE_ID | RULE_STRING | RULE_ML_COMMENT | RULE_SL_COMMENT | RULE_WS | RULE_ANY_OTHER );";
        }
        public int specialStateTransition(int s, IntStream _input) throws NoViableAltException {
            IntStream input = _input;
        	int _s = s;
            switch ( s ) {
                    case 0 : 
                        int LA13_19 = input.LA(1);

                        s = -1;
                        if ( ((LA13_19>='\u0000' && LA13_19<='\uFFFF')) ) {s = 44;}

                        else s = 22;

                        if ( s>=0 ) return s;
                        break;
                    case 1 : 
                        int LA13_18 = input.LA(1);

                        s = -1;
                        if ( ((LA13_18>='\u0000' && LA13_18<='\uFFFF')) ) {s = 44;}

                        else s = 22;

                        if ( s>=0 ) return s;
                        break;
                    case 2 : 
                        int LA13_0 = input.LA(1);

                        s = -1;
                        if ( (LA13_0=='A') ) {s = 1;}

                        else if ( (LA13_0=='B') ) {s = 2;}

                        else if ( (LA13_0=='C') ) {s = 3;}

                        else if ( (LA13_0=='D') ) {s = 4;}

                        else if ( (LA13_0=='E') ) {s = 5;}

                        else if ( (LA13_0=='F') ) {s = 6;}

                        else if ( (LA13_0=='G') ) {s = 7;}

                        else if ( (LA13_0=='H') ) {s = 8;}

                        else if ( (LA13_0=='P') ) {s = 9;}

                        else if ( (LA13_0=='K') ) {s = 10;}

                        else if ( (LA13_0=='R') ) {s = 11;}

                        else if ( (LA13_0=='Q') ) {s = 12;}

                        else if ( (LA13_0=='-') ) {s = 13;}

                        else if ( ((LA13_0>='0' && LA13_0<='9')) ) {s = 14;}

                        else if ( (LA13_0=='M') ) {s = 15;}

                        else if ( (LA13_0=='^') ) {s = 16;}

                        else if ( ((LA13_0>='I' && LA13_0<='J')||LA13_0=='L'||(LA13_0>='N' && LA13_0<='O')||(LA13_0>='S' && LA13_0<='Z')||LA13_0=='_'||(LA13_0>='a' && LA13_0<='z')) ) {s = 17;}

                        else if ( (LA13_0=='\"') ) {s = 18;}

                        else if ( (LA13_0=='\'') ) {s = 19;}

                        else if ( (LA13_0=='/') ) {s = 20;}

                        else if ( ((LA13_0>='\t' && LA13_0<='\n')||LA13_0=='\r'||LA13_0==' ') ) {s = 21;}

                        else if ( ((LA13_0>='\u0000' && LA13_0<='\b')||(LA13_0>='\u000B' && LA13_0<='\f')||(LA13_0>='\u000E' && LA13_0<='\u001F')||LA13_0=='!'||(LA13_0>='#' && LA13_0<='&')||(LA13_0>='(' && LA13_0<=',')||LA13_0=='.'||(LA13_0>=':' && LA13_0<='@')||(LA13_0>='[' && LA13_0<=']')||LA13_0=='`'||(LA13_0>='{' && LA13_0<='\uFFFF')) ) {s = 22;}

                        if ( s>=0 ) return s;
                        break;
            }
            NoViableAltException nvae =
                new NoViableAltException(getDescription(), 13, _s, input);
            error(nvae);
            throw nvae;
        }
    }
 

}