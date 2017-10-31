package com.ufal.rsawork.utils;

import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class AlertDialog {

    public static void display(String title, String message) {
        Stage current = new Stage();
        current.initModality(Modality.APPLICATION_MODAL);
        current.setTitle(title);
        current.setMinWidth(400);
        current.setMinHeight(200);

        Label label = new Label();
        label.setText(message);

        Button button = new Button("Ok!");
        button.setOnAction(e -> current.close());

        VBox box= new VBox(10);
        box.getChildren().addAll(label, button);
        box.setAlignment(Pos.CENTER);

        current.setScene(new Scene(box));
        current.showAndWait();
    }
}
