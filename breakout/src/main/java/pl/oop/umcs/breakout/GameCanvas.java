package pl.oop.umcs.breakout;

import javafx.animation.AnimationTimer;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GameCanvas extends Canvas {
    private GraphicsContext graphicsContext;
    private Paddle paddle;
    private Ball ball;
    private boolean running = false;
    private AnimationTimer animationTimer = new AnimationTimer() {
        private long before;    //ostatni update

        @Override
        public void start() {
            super.start();
            this.before = System.nanoTime();    //Odczytujemy biezacy czas
        }

        @Override
        public void handle(long now) {    //now to biezacy czas, w ktorym wykonujemy dzialanie
            long difference = (now - before) / 5_000_000;
            before = now;
            ball.updatePosition(difference);
            draw();
        }
    };

    public GameCanvas() {
        super(640, 700);
    }

    public void initialize(){   //Metoda initialize wywoluje sie na poczatku dzialania klasy
        graphicsContext = this.getGraphicsContext2D();
        GraphicsItem.setCanvasSize(getWidth(), getHeight());
        this.paddle = new Paddle();
        this.ball = new Ball();
        this.setOnMouseMoved(mouseEvent -> {    //Wykrywamy ruch myszy
            paddle.setX(mouseEvent.getX());
//            if(running){
//                ball.updatePosition();
//            }
            draw();
        });
        this.setOnMouseClicked(mouseEvent -> {
            running = true;
            animationTimer.start();
        });
        draw();
    }
    public void draw(){
        graphicsContext.setFill(Color.BLACK);
        graphicsContext.fillRect(0,0,640,700);
        paddle.draw(graphicsContext);
        ball.draw(graphicsContext);
    }

}
