package com.ufal.rsawork;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Main extends Application {

    public static final String APP_NAME = "PVS Crypt";
    public static final String BASE_PATH = "/com/ufal/rsawork/images/";

    @Override
    public void start(Stage primaryStage) {
        try {
            Parent root = FXMLLoader.load(getClass().getResource("layouts/main_screen.fxml"));
            primaryStage.setScene(new Scene(root));
            primaryStage.setTitle(Main.APP_NAME);
            primaryStage.getIcons().add(new Image(Main.BASE_PATH + "icon.png"));
            primaryStage.setResizable(false);
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        launch(args);
    }
}
