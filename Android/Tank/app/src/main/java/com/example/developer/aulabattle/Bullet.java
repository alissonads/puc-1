package com.example.developer.aulabattle;

import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

/**
 * Created by developer on 27/03/17.
 */

public class Bullet extends AnimatedImageGameObject {
    public boolean isRendered = false;
    Matrix matrix = new Matrix();
    public float angle = 0;
    public Bullet(String file, AssetManager manager) {
        loadImages(file, manager, 1,1);
    }
    public float velocity = 150;
    @Override
    public void draw(Canvas canvas, Paint paint) {
        matrix.reset();
        matrix.preTranslate(x-w/2, y-h/2);
        matrix.preRotate((angle*180.0f/(float) Math.PI) + 90, w/2,h/2);
        canvas.drawBitmap(anim[currentFrame], matrix, paint);
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        x += Math.cos(angle) * deltaTime * velocity / 1000;
        y += Math.sin(angle) * deltaTime * velocity / 1000;


    }
}
