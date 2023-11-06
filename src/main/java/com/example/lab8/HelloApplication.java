package com.example.lab8;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.*;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import static java.util.Collections.shuffle;

public class HelloApplication extends Application {
    GridPane gridPane = new GridPane();
    ArrayList<Piece> listePiece = new ArrayList<>();

    ArrayList<Piece> imageComplete = new ArrayList<>();

    Boolean fait;

    Dialog dialog = new Dialog();

    @Override
    public void start(Stage stage) throws IOException {

        Scene scene = new Scene(gridPane);
        stage.setTitle("Casse-tête!");
        //Initialisation de la liste
        for (int i = 0;i < 9;i++) {
            listePiece.add(new Piece(i,new ImageView("file:mario" + i + ".jpg"),listePiece,imageComplete,this));
            imageComplete.add(new Piece(i,new ImageView("file:mario" + i + ".jpg"),listePiece,imageComplete,this));
        }
        melanger();
        //Control M
        scene.setOnKeyPressed(event ->{
            if (event.getCode() == KeyCode.M && event.isControlDown()) {
                melanger();
            }});
        for (int i = 0;i < 9;i++){

        //Deplacer les pieces
        scene.setRoot(gridPane);
        stage.setScene(scene);
        stage.show();
        }


        //Creer l'onglet terminé
        Label label = new Label("Bravo vous avez gagnez!!");
        Button button = new Button("Recommencer");
        button.setOnAction(actionEvent -> {
            melanger();
            dialog.close();
        });
        VBox vBox = new VBox(label,button);
        dialog.getDialogPane().setContent(vBox);
        dialog.getDialogPane().getButtonTypes().add(new ButtonType("Voir", ButtonBar.ButtonData.CANCEL_CLOSE));
      }

    public static void main(String[] args) {
        launch();
    }
    public void melanger() {
        List<Image> ok = new ArrayList<>();
        for (int i = 0;i < 9;i++) {
            ok.add(imageComplete.get(i).getImageReference().getImage());
        }
        shuffle(ok);
        gridPane.getChildren().clear();
        for (int i = 0;i < 9;i++) {
            listePiece.get(i).getImageReference().setImage(ok.get(i));
            gridPane.add(listePiece.get(i).getImageReference(), i % 3, i / 3);
        }
    }
    public void terminer() {
        dialog.show();

    }
}