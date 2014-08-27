package norwegiangambit.gui.fx;

import java.util.Arrays;

import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.effect.DropShadow;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import norwegiangambit.engine.fen.StartingGames;
import norwegiangambit.profile.IPlayer.Players;

import com.sun.javafx.collections.ObservableListWrapper;


public class FxChessDialog extends GridPane{

    ComboBox<String> wh_combo;
    ComboBox<String> bl_combo;
    ComboBox<String> fen_combo;

    public FxChessDialog(final FxGameData game) {
        super();
        ObservableList<ColumnConstraints> cols = getColumnConstraints();
        cols.add(new ColumnConstraints(270));
        cols.add(new ColumnConstraints(80));
        ObservableList<RowConstraints> rows = getRowConstraints();
        rows.add(new RowConstraints(30));
        rows.add(new RowConstraints(30));
        rows.add(new RowConstraints(30));
        rows.add(new RowConstraints(30));
        rows.add(new RowConstraints(30));
        rows.add(new RowConstraints(120));
        rows.add(new RowConstraints(40));
        FxChessCanvas canvas = new FxChessCanvas(game);
        add(canvas,0,0,1,6);
        add(createLabel("White"), 1, 0);
        wh_combo = createCombo("MANUAL");
        add(wh_combo,1,1);
        add(createLabel("Black"), 1, 2);
        bl_combo = createCombo("NOVICE");
        add(bl_combo,1,3);
        fen_combo = createFenCombo(StartingGames.FEN_GAMES[0]);
        add(fen_combo,0,6,2,1);
        Button button = new Button();
        button.setText("Start");
        button.setOnKeyPressed(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
//                Players p_white = Players.values()[wh_combo];
//                Players p_black = Players.values()[bl_combo];
//                game.startEvent(fen_combo.getText(), p_white, p_black);
                System.out.println("hi");
            }
        });
        button.setOnMouseClicked(new EventHandler<Event>() {

            @Override
            public void handle(Event event) {
                game.startEvent(fen_combo.getValue(), Players.valueOf(wh_combo.getValue()), Players.valueOf(bl_combo.getValue()));
            }
        });
        add(button,1,4);
    }

    public static ComboBox<String> createFenCombo(String txt) {
        ObservableListWrapper<String> olist = new ObservableListWrapper<String>(Arrays.asList(StartingGames.FEN_GAMES));
        ComboBox<String> combo=new ComboBox<String>(olist);
        combo.setValue(txt);
        combo.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hi");
            }
        });
        return combo;
    }

    public static ComboBox<String> createCombo(String txt) {
        ObservableListWrapper<String> olist = new ObservableListWrapper<String>(Arrays.asList(Players.NAMES));
        ComboBox<String> combo=new ComboBox<String>(olist);
        combo.setValue(txt);
        combo.setOnAction(new EventHandler<ActionEvent>() {
            
            @Override
            public void handle(ActionEvent event) {
                System.out.println("hi");
            }
        });
        return combo;
    }

    public static Text createLabel(String lbl) {
        Text t=new Text(lbl);
        t.setFont(Font.font("Arial", 10));
        t.setEffect(new DropShadow(2,3,3,Color.RED));
        return t;
    }

}
