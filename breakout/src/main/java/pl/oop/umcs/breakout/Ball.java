package pl.oop.umcs.breakout;

import javafx.geometry.Point2D;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class Ball extends GraphicsItem{
    private Point2D moveVector = new Point2D(1, -1).normalize();    //-1 bo pkt odniesienia jest lewy gorny pkt ekranu. Normalizujemy, bo chcemy aby dlugos wektora wynosila 1

    public Ball() {
        x = 0.5 * canvasWidth;
        y = 0.88 * canvasHeight;
        width = 0.015 * canvasHeight;
        height = 0.015 * canvasHeight;
    }

    @Override
    public void draw(GraphicsContext graphicsContext) {
        graphicsContext.setFill(Color.WHITE);
        graphicsContext.fillOval(x, y, width, height);
    }

    public void updatePosition(long difference){
        x += moveVector.getX() * difference;
        y += moveVector.getY() * difference;
    }

}
