package sample;

import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Point extends Circle {
    private List<Double> distances = new ArrayList<>();

    public Point(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
    }

    public List<Double> getDistances() {
        return distances;
    }

    public void setDistances(List<Double> distances) {
        this.distances = distances;
    }
}
