package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Cursor;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.ArrayList;
import java.util.List;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        final double[] cursorX = new double[1];
        final double[] cursorY = new double[1];
        final int[] x = new int[1];
        final int[] step = {4};
        List<Point> circles = new ArrayList<>();

        AnchorPane root = new AnchorPane();

        Scene scene = new Scene(root, 1200, 900);
        scene.setFill(Color.BLACK);
        scene.setOnMouseMoved(event -> {
            cursorX[0] = event.getX();
            cursorY[0] = event.getY();
        });

        Rectangle background = new Rectangle(0, 0, scene.getWidth(), scene.getHeight());
        background.setFill(Color.DARKGRAY);

        Canvas canvas = new Canvas(scene.getWidth(), 400);
        canvas.setLayoutY(scene.getHeight()-canvas.getHeight());

        GraphicsContext gr = canvas.getGraphicsContext2D();
        gr.beginPath();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            for (Point p : circles) {
                p.getDistances().add(Math.sqrt(Math.pow(cursorX[0]-p.getCenterX(), 2)+Math.pow(cursorY[0]-p.getCenterY(), 2)));
            }
            x[0] =100;
            background.setWidth(scene.getWidth());
            background.setHeight(scene.getHeight());
            gr.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
            gr.moveTo(x[0], 300);
            gr.lineTo(canvas.getWidth()-100, 300);
            gr.moveTo(x[0], 300);
            gr.lineTo(x[0], 100);
            gr.stroke();
            gr.moveTo(x[0], 300);
            for (int i = 0; i <circles.get(0).getDistances().size(); i++) {
                x[0]+= step[0];
                gr.lineTo(x[0], 300-circles.get(0).getDistances().get(i)/10);
            }
            gr.stroke();
            if(x[0]>canvas.getWidth()) step[0]--;
        }));

        Button add = new Button("add point");
        add.setLayoutY(scene.getHeight()-50);
        add.setLayoutX(300);
        add.setOnAction(event -> {
            Point c = new Point(Math.random()*(scene.getWidth()-50)+50,
                    Math.random()*(scene.getHeight()-canvas.getHeight()-50)+50, 2);
            c.setFill(Color.rgb((int)(Math.random()*127), (int)(Math.random()*127), (int)(Math.random()*127)));
            circles.add(c);
            root.getChildren().add(c);
            if(circles.size()==1)timeline.play();
        });

        root.getChildren().addAll(background, canvas, add);

        timeline.setCycleCount(-1);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
