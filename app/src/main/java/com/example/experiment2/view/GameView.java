package com.example.experiment2.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.experiment2.R;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GameView extends SurfaceView implements SurfaceHolder.Callback {
    private GameLoopThread gameLoopThread;
    private List<Book> books;
    private int booksCaught;
    private long startTime;
    private boolean isRunning;
    private Paint textPaint; // 用于绘制文本
    private int textSize = 80; // 文本大小
    private List<Book> booksToRemove;
    private boolean gameEnded;

    public GameView(Context context, AttributeSet attrs) {
        super(context, attrs);
        getHolder().addCallback(this);
        books = new ArrayList<>();
        booksCaught = 0;
        isRunning = false;

        // 初始化用于绘制文本的 Paint
        textPaint = new Paint();
        textPaint.setColor(Color.BLACK); // 设置文本颜色
        textPaint.setTextSize(textSize); // 设置文本大小
        // ... 现有的初始化代码 ...
        booksToRemove = new ArrayList<>();
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        gameLoopThread = new GameLoopThread();
        gameLoopThread.start();
        startTime = System.currentTimeMillis();
        isRunning = true;
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        // SurfaceView尺寸发生变化时调用，可以在此处进行相应的调整
    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        isRunning = false;
        boolean retry = true;
        while (retry) {
            try {
                gameLoopThread.join();
                retry = false;
            } catch (InterruptedException e) {
                // 处理异常
            }
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        if (gameEnded && event.getAction() == MotionEvent.ACTION_DOWN) {
            restartGame();
            return true;
        }
        if (event.getAction() == MotionEvent.ACTION_DOWN) {
            float touchX = event.getX();
            float touchY = event.getY();

            synchronized (books) {
                for (Book book : books) {
                    if (book.isTouching(touchX, touchY)) {
                        booksToRemove.add(book);
                        booksCaught++;
                        break;
                    }
                }
            }
        }
        return true;
    }

    private void createBooks() {
        if (books.size() < 5) {
            books.add(new Book((int) (getWidth() * Math.random()), (int) (getHeight() * Math.random())));
        }
    }

    private void updateGame() {
        if (System.currentTimeMillis() - startTime > 30000) {
            isRunning = false; // 停止游戏循环
            gameEnded = true;  // 设置游戏结束标志
        }
    }

    private class GameLoopThread extends Thread {
        @Override
        public void run() {
            while (isRunning) {
                Canvas canvas = null;
                try {
                    canvas = GameView.this.getHolder().lockCanvas();
                    synchronized (GameView.this.getHolder()) {
                        if (!gameEnded) {
                            createBooks();
                            updateGame();
                            drawBooks(canvas);
                            synchronized (books) {
                                books.removeAll(booksToRemove);
                                booksToRemove.clear();
                            }
                        } else {
                            drawGameOver(canvas);
                        }
                    }
                } finally {
                    if (canvas != null) {
                        GameView.this.getHolder().unlockCanvasAndPost(canvas);
                    }
                }

                // 检查游戏是否应该结束
                if (System.currentTimeMillis() - startTime > 30000) {
                    gameEnded = true;
                    isRunning = false; // Stop the game after 30 seconds
                }

                try {
                    Thread.sleep(50); // Adjust this value as needed for smoother animation
                } catch (InterruptedException e) {
                    // Handle exception
                }
            }
        }
    }

    private void drawBooks(Canvas canvas) {
        if (canvas != null) {
            canvas.drawColor(Color.WHITE); // Clear the canvas
            Paint paint = new Paint();

            for (Book book : books) {
                Bitmap originalBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.my_image); // Replace with your book image
                // 设置想要的大小
                int newWidth = 200; // 例如，将宽度设置为50像素
                int newHeight = 200; // 例如，将高度设置为50像素
                // 重新计算图书的宽高比例
                Bitmap scaledBitmap = Bitmap.createScaledBitmap(originalBitmap, newWidth, newHeight, true);
                // 绘制缩放后的图书图片
                canvas.drawBitmap(scaledBitmap, book.x, book.y, paint);
            }
            // 绘制捕获的书本数量
            canvas.drawText("Books caught: " + booksCaught, 10, textSize + 10, textPaint);
            // 绘制剩余时间
            String remainingTime = getRemainingTime();
            canvas.drawText(remainingTime, 10, canvas.getHeight() - 10, textPaint); // 在左下角绘制

        }
    }
    private String getRemainingTime() {
        long elapsed = System.currentTimeMillis() - startTime;
        long remaining = Math.max(30000 - elapsed, 0); // 30秒游戏时间
        return "Time: " + (remaining / 1000) + "s"; // 将剩余时间转换为秒
    }
    private class Book {
        int x, y;

        public Book(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public boolean isTouching(float touchX, float touchY) {
            // Implement collision detection logic
            // For simplicity, let's assume each book is a square of 50x50 pixels
            return touchX >= x && touchX <= x + 200 && touchY >= y && touchY <= y + 200;
        }
    }

    private void drawGameOver(Canvas canvas) {
        if (canvas != null && gameEnded) {
            // 清除画布
            canvas.drawColor(Color.WHITE);

            // 设置并绘制结束信息
            String gameOverText = "Game Over!";
            String scoreText = "Score: " + booksCaught;
            textPaint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(gameOverText, canvas.getWidth() / 2, canvas.getHeight() / 2, textPaint);
            canvas.drawText(scoreText, canvas.getWidth() / 2, (canvas.getHeight() / 2) + textSize, textPaint);
            // 假设在显示结束信息5秒后重新开始游戏
            if (System.currentTimeMillis() - startTime > 35000) { // 30秒游戏时间 + 5秒等待
                restartGame();
            }
        }
    }
    public void restartGame() {
        isRunning = true;
        gameEnded = false;
        booksCaught = 0;
        books.clear();
        booksToRemove.clear();
        startTime = System.currentTimeMillis();

        // 重新启动游戏循环线程
        if (gameLoopThread != null && !gameLoopThread.isAlive()) {
            gameLoopThread = new GameLoopThread();
            gameLoopThread.start();
        }
    }
}
