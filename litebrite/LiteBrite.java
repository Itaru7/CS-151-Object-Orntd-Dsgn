/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package litebrite;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.ColorPicker;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

/**
 *
 * @author Itaru Kishikawa
 */

public class LiteBrite extends Application {
    
    @Override
    public void start(final Stage stage) throws Exception {
        int rows = 50;
        int columns = 50;

        stage.setTitle("Enjoy your game");

        GridPane grid = new GridPane();
        grid.getStyleClass().add("game-grid");

        final ColorPicker color = new ColorPicker();

        for(int i = 0; i < columns; i++) {
            ColumnConstraints column = new ColumnConstraints(10);
            grid.getColumnConstraints().add(column);
        }

        for(int i = 0; i < rows; i++) {
            RowConstraints row = new RowConstraints(10);
            grid.getRowConstraints().add(row);
        }

        for (int i = 0; i < columns; i++) {
            for (int j = 0; j < rows; j++) {
                Pane pane = new Pane();
                pane.setOnMouseReleased(e -> {
                    if(pane.getChildren().isEmpty())
                        pane.getChildren().add(Anims.getAtoms(color));
                    else
                        pane.getChildren().clear();
                });
                pane.getStyleClass().add("game-grid-cell");
                if (i == 0) {
                    pane.getStyleClass().add("first-column");
                }
                if (j == 0) {
                    pane.getStyleClass().add("first-row");
                }
                grid.add(pane, i, j);
            }
        }
        
        VBox box = new VBox();
        box.getChildren().addAll(color,grid);
        
        Scene scene = new Scene(box, (columns * 10)+20, (rows * 10) + 50);
        scene.getStylesheets().add(LiteBrite.class.getResource("resources/game.css").toExternalForm());
        stage.setScene(scene);
        stage.show();
    }

    public static class Anims {
        public static Node getAtoms(final ColorPicker c) {
            //TODO: Add code to create a colored 
            Circle circle = new Circle(5);
            circle.setFill(c.getValue());
            circle.setCenterX(5);
            circle.setCenterY(5);
            return circle;
        }
    }

    public static void main(final String[] arguments) {
        Application.launch(arguments);
    }   
}
