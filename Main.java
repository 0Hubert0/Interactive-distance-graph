package sample;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.input.KeyCode;
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
        final int[] step = {3};
        final int[] x = {100};
        List<Point> circles = new ArrayList<>();
        final boolean[] isStopped = {true};
        x[0]-=step[0];

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
        gr.moveTo(x[0], 300);
        gr.lineTo(canvas.getWidth()-100, 300);
        gr.moveTo(x[0], 300);
        gr.lineTo(x[0], 100);
        gr.stroke();
        gr.setLineWidth(2);

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(50), event -> {
            for (Point p : circles) {
                if(p.getCurrentDistance()!=-1)p.setPreviousDistance(p.getCurrentDistance()/5);
                p.setCurrentDistance(Math.sqrt(Math.pow(cursorX[0]-p.getCenterX(), 2)+Math.pow(cursorY[0]-p.getCenterY(), 2)));
            }
            background.setWidth(scene.getWidth());
            background.setHeight(scene.getHeight());
            x[0]+=step[0];
            for (Point p : circles) {
                gr.beginPath();
                gr.setStroke(p.getFill());
                if(p.getPreviousDistance()!=-1) gr.moveTo(x[0] - step[0], 300 - p.getPreviousDistance());
                else gr.moveTo(x[0] - step[0], 300 - p.getCurrentDistance()/5);
                gr.lineTo(x[0], 300 - p.getCurrentDistance() / 5);
                gr.stroke();
            }
        }));

        timeline.setCycleCount((int)((canvas.getWidth()-100-x[0]))/step[0]);

        scene.setOnKeyPressed(event -> {
            if(event.getCode()==KeyCode.SPACE && circles.size()>0){
                isStopped[0]=!isStopped[0];
                if ((isStopped[0])) {
                    timeline.pause();
                } else {
                    timeline.play();
                }
            }
        });

        Button add = new Button("add point");
        add.setLayoutY(scene.getHeight()-50);
        add.setLayoutX(300);
        add.setOnAction(event -> {
            Point c = new Point(Math.random()*(scene.getWidth()-50)+50,
                    Math.random()*(scene.getHeight()-canvas.getHeight()-50)+50, 4);
            c.setFill(Color.rgb((int)(Math.random()*127), (int)(Math.random()*127), (int)(Math.random()*127)));
            circles.add(c);
            root.getChildren().add(c);
        });

        Button play = new Button("Play");
        play.setLayoutY(scene.getHeight()-50);
        play.setLayoutX(500);
        play.setOnAction(event -> {
            if(circles.size()>0) {
            timeline.play();
            isStopped[0]=false;
            }
        });

        Button pause = new Button("Pause");
        pause.setLayoutY(scene.getHeight()-50);
        pause.setLayoutX(700);
        pause.setOnAction(event -> {
            timeline.pause();
            isStopped[0] =true;
        });

        root.getChildren().addAll(background, canvas, add, play, pause);

        Stage stage = new Stage();
        stage.setScene(scene);
        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
