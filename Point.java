package sample;

import javafx.scene.shape.Circle;

import java.util.ArrayList;
import java.util.List;

public class Point extends Circle {
    private double currentDistance=-1, previousDistance=-1;
    private List<Double> distances = new ArrayList<>();

    public Point(double centerX, double centerY, double radius) {
        super(centerX, centerY, radius);
    }

    public double getCurrentDistance() {
        return currentDistance;
    }

    public void setCurrentDistance(double currentDistance) {
        this.currentDistance = currentDistance;
    }

    public double getPreviousDistance() {
        return previousDistance;
    }

    public void setPreviousDistance(double previousDistance) {
        this.previousDistance = previousDistance;
    }

    public List<Double> getDistances() {
        return distances;
    }

    public void setDistances(List<Double> distances) {
        this.distances = distances;
    }
}
