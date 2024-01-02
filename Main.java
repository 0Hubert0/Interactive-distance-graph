package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        List<Circle> circles = new ArrayList<>();
        List<Integer> distances = new ArrayList<>();

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 1200, 900);
        scene.setFill(Color.BLACK);

        Rectangle background = new Rectangle(0, 0, scene.getWidth(), scene.getHeight());
        background.setFill(Color.DARKGRAY);

        Canvas canvas = new Canvas(scene.getWidth(), 400);
        canvas.setLayoutY(scene.getHeight()-canvas.getHeight());

        Button add = new Button("add point");
        add.setLayoutY(scene.getHeight()-50);
        add.setLayoutX(300);
        add.setOnAction(event -> {
            Circle c = new Circle(Math.random()*(scene.getWidth()-50)+50,
                    Math.random()*(scene.getHeight()-canvas.getHeight()-50)+50, 2);
            c.setFill(Color.rgb((int)(Math.random()*127), (int)(Math.random()*127), (int)(Math.random()*127)));
            circles.add(c);
            root.getChildren().add(c);
        });

        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.beginPath();

        root.getChildren().addAll(background, canvas, add);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(16), event -> {
            background.setWidth(scene.getWidth());
            background.setHeight(scene.getHeight());
            gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gr.moveTo(100, 300);
            gr.lineTo(canvas.getWidth()-100, 300);
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
