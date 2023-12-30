package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 1200, 900);

        Canvas canvas = new Canvas(400, 400);
        canvas.setLayoutY(scene.getHeight()-canvas.getHeight());

        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.beginPath();

        root.getChildren().addAll(canvas);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gr.moveTo(100, 300);
            gr.lineTo(300, 300);
            gr.moveTo(100, 300);
            gr.lineTo(100, 100);
            gr.stroke();
        }));

        timeline.setCycleCount(-1);
        timeline.play();

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
