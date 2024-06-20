package pl.oop.umcs.kolokwium22021;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DrawingServer extends Application {
    private static final int WINDOW_SIZE = 500;
    private static final int MOVE_OFFSET = 10;
    private double offsetX = 0;
    private double offsetY = 0;

    private Canvas canvas;
    private GraphicsContext gc;
    private List<Line> lines = new ArrayList<>();
    private Color currentColor = Color.BLACK;
    private Server server;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        canvas = new Canvas(WINDOW_SIZE, WINDOW_SIZE);
        gc = canvas.getGraphicsContext2D();
        clearCanvas();

        StackPane root = new StackPane();       //Zamiast tego można użyc GridPane
        root.getChildren().add(canvas);
        Scene scene = new Scene(root, WINDOW_SIZE, WINDOW_SIZE);

        scene.setOnKeyPressed(this::handleKeyPress);
        primaryStage.setTitle("Drawing Server");
        primaryStage.setScene(scene);
        primaryStage.show();

        server = new Server(12345, this);
        new Thread(server).start();
    }

    public synchronized void addLine(Line line) {
        lines.add(line);
        drawLine(line);
    }

    public void setColor(Color color) {
        this.currentColor = color;
    }

    public Color getCurrentColor() {
        return currentColor;
    }

    private void drawLine(Line line) {
        Platform.runLater(() -> {
            gc.setStroke(line.getColor());
            gc.strokeLine(line.getX1() + offsetX, line.getY1() + offsetY, line.getX2() + offsetX, line.getY2() + offsetY);
        });
    }

    private void handleKeyPress(KeyEvent event) {
        switch (event.getCode()) {
            case UP -> offsetY += MOVE_OFFSET;
            case DOWN -> offsetY -= MOVE_OFFSET;
            case LEFT -> offsetX += MOVE_OFFSET;
            case RIGHT -> offsetX -= MOVE_OFFSET;
        }
        redrawCanvas();
    }

    private void redrawCanvas() {
        clearCanvas();
        for (Line line : lines) {
            drawLine(line);
        }
    }

    private void clearCanvas() {
        gc.setFill(Color.WHITE);
        gc.fillRect(0, 0, WINDOW_SIZE, WINDOW_SIZE);
    }
}