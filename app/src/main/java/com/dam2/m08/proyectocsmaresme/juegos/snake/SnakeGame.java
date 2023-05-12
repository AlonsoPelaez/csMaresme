package com.dam2.m08.proyectocsmaresme.juegos.snake;

import android.content.DialogInterface;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatImageButton;

import com.dam2.m08.proyectocsmaresme.R;

import java.io.Console;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class SnakeGame extends AppCompatActivity implements SurfaceHolder.Callback {

    private SurfaceView surfaceView;
    private TextView scoreTv;

    private SurfaceHolder surfaceHolder;
    private final List<SnakePoints> snakePointsList = new ArrayList<>();
    private int score=0;
    private String movingPosition ="right";

    private static final int pointSize=28;
    private static final int defaultTalePoints= 3;
    private static final int snakeColor = Color.YELLOW;
    private static final int snakeMovingSpeed = 800;

    private int positionX, positionY;
    private Timer timer;
    private Canvas canvas = null;
    private Paint pointColor = null;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.snakegame);

        surfaceView = findViewById(R.id.surfaceview);
        scoreTv = findViewById(R.id.scoreTV);

        final AppCompatImageButton btnTop = findViewById(R.id.btnTop);
        final AppCompatImageButton btnBottom = findViewById(R.id.btnBottom);
        final AppCompatImageButton btnLeft = findViewById(R.id.btnLeft);
        final AppCompatImageButton btnRight = findViewById(R.id.btnRight);

        surfaceView.getHolder().addCallback(this);

        btnTop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!movingPosition.equals("bottom")){
                    movingPosition = "top";
                }
            }
        });

        btnBottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!movingPosition.equals("top")){
                    movingPosition = "bottom";
                }
            }
        });


        btnLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (!movingPosition.equals("right")){
                    movingPosition = "left";
                }
            }
        });

        btnRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!movingPosition.equals("left")){
                    movingPosition = "right";
                }
            }
        });

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        this.surfaceHolder = holder;

        init();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        if (timer != null) {
            timer.cancel();
            timer = null;
        }
        surfaceHolder = null;
    }
    private void init(){

        snakePointsList.clear();

        scoreTv.setText("0");

        score=0;

        movingPosition = "right";

        int startPositionX = (pointSize * defaultTalePoints);

        for (int i = 0; i < defaultTalePoints; i++) {
            SnakePoints snakePoints = new SnakePoints(startPositionX, pointSize);
            snakePointsList.add(snakePoints);

            startPositionX = startPositionX - (pointSize * 2 );
        }
        addPoint();
        moveSnake();
    }
    public void addPoint(){

        int surfaceWidth = surfaceView.getWidth() - (pointSize * 2);
        int surfaceHeight = surfaceView.getHeight() - (pointSize * 2);

        int randomXPosition = new Random().nextInt(surfaceWidth/pointSize);
        int randomYPosition = new Random().nextInt(surfaceHeight/pointSize);

        if ((randomXPosition % 2) != 0){
            randomXPosition = randomXPosition + 1;
        }
        if ((randomYPosition % 2) != 0){
            randomYPosition = randomYPosition + 1;
        }
        positionX = (pointSize * randomXPosition) + pointSize;
        positionY = (pointSize * randomYPosition) + pointSize;

    }
    public void moveSnake(){

        timer = new Timer();
        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                int headPositionX = snakePointsList.get(0).getPositionX();
                int headPositionY = snakePointsList.get(0).getPositionY();

                if (headPositionX == positionX && positionY == headPositionY){
                    growSnake();
                    addPoint();
                }
                switch (movingPosition){
                    case "right":
                        snakePointsList.get(0).setPositionX(headPositionX + (pointSize * 2));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;
                    case "left":
                        snakePointsList.get(0).setPositionX(headPositionX - (pointSize * 2));
                        snakePointsList.get(0).setPositionY(headPositionY);
                        break;
                    case "top":
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY - (pointSize * 2));
                        break;
                    case "bottom":
                        snakePointsList.get(0).setPositionX(headPositionX);
                        snakePointsList.get(0).setPositionY(headPositionY + (pointSize * 2));
                        break;

                }


                if (checkGameOver(headPositionX, headPositionY)){
                    timer.purge();
                    timer.cancel();

                    AlertDialog.Builder builder = new AlertDialog.Builder(SnakeGame.this);
                    builder.setMessage("Your Score" + score);
                    builder.setTitle("GAME OVER");
                    builder.setCancelable(false);
                    builder.setPositiveButton("Start Again", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            init();
                        }
                    });
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            builder.show();
                        }
                    });
                }
                else {
                    canvas = surfaceHolder.lockCanvas();
                    canvas.drawColor(Color.WHITE, PorterDuff.Mode.CLEAR);
                    canvas.drawCircle(snakePointsList.get(0).getPositionX(), snakePointsList.get(0).getPositionY(), pointSize, createPointColor());
                    canvas.drawCircle(positionX, positionY, pointSize, createPointColor());

                        for (int i = 1; i < snakePointsList.size(); i++) {

                        int getTempPositionX = snakePointsList.get(i).getPositionX();
                        int getTempPositionY = snakePointsList.get(i).getPositionY();

                        snakePointsList.get(i).setPositionX(headPositionX);
                        snakePointsList.get(i).setPositionY(headPositionY);
                        canvas.drawCircle(snakePointsList.get(i).getPositionX(), snakePointsList.get(i).getPositionY(),pointSize, createPointColor());

                        headPositionX = getTempPositionX;
                        headPositionY = getTempPositionY;

                    }
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }
        },1000-snakeMovingSpeed,1000-snakeMovingSpeed);

    }

    private boolean checkGameOver(int headPositionX, int headPositionY) {
        boolean gameOver=false;

        if (snakePointsList.get(0).getPositionX() < 0 ||
                snakePointsList.get(0).getPositionY() < 0 ||
                snakePointsList.get(0).getPositionX() >= surfaceView.getWidth() ||
                snakePointsList.get(0).getPositionY() >= surfaceView.getHeight())
        {
            gameOver = true;
        }
        else {
            for (int i = 1; i < snakePointsList.size(); i++) {

                if (headPositionX == snakePointsList.get(i).getPositionX() &&
                        headPositionY == snakePointsList.get(i).getPositionY()){

                    gameOver = true;
                    break;
                }
            }
        }

        return gameOver;
    }

    private void growSnake() {
        SnakePoints snakePoints = new SnakePoints(0,0);
        snakePointsList.add(snakePoints);
        score++;
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                scoreTv.setText(String.valueOf(score));
            }
        });

    }
    private Paint createPointColor(){
       if (pointColor == null){
           pointColor = new Paint();
           pointColor.setColor(snakeColor);
           pointColor.setStyle(Paint.Style.FILL);
           pointColor.setAntiAlias(true);
       }

        return pointColor;
    }

}
