package com.example.lab8;

import javafx.application.Application;
import javafx.scene.Node;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.Clipboard;
import javafx.scene.input.ClipboardContent;
import javafx.scene.input.Dragboard;
import javafx.scene.input.TransferMode;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Stack;

public class Piece {

    int reference;
    ImageView imageReference;

    boolean fait1 = true;

    public Piece(int reference, ImageView image, ArrayList<Piece> puzzleBouger, ArrayList<Piece> puzzleFait,HelloApplication helloApplication) {
        this.reference = reference;
        this.imageReference = image;
        imageReference.setOnDragDetected(event -> {
            Dragboard dragboard = imageReference.startDragAndDrop(TransferMode.MOVE);
            ClipboardContent contenu = new ClipboardContent();
            contenu.putImage(imageReference.getImage());
            dragboard.setContent(contenu);
        });
        imageReference.setOnDragOver(event -> event.acceptTransferModes(TransferMode.MOVE
        ));
        imageReference.setOnDragDropped(dragEvent -> {
            fait1 = true;
            Image content =((ImageView) (dragEvent.getGestureSource())).getImage();
            ((ImageView) (dragEvent.getGestureSource())).setImage(imageReference.getImage());
            imageReference.setImage(content);

            //Verification
            for (int i = 0; i < puzzleBouger.size();i++) {
                if (!Objects.equals(puzzleBouger.get(i).getImageReference().getImage().getUrl(), puzzleFait.get(i).getImageReference().getImage().getUrl())) {
                    fait1 = false;
                    break;
                }
            }
            if (fait1)
                helloApplication.terminer();
        });
        imageReference.setId("Piece" + reference);

    }

    public ImageView getImageReference() {
        return imageReference;
    }
    public String toString() {
        return "piece" + imageReference;
    }

}