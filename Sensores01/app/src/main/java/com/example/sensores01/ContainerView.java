package com.example.sensores01;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class ContainerView extends View {

    Context context;
    Paint paint;

    public int containerHeight=0;
    public int containerWidth=0;
    public int color = 0;

    Circle circle;
    Canvas canvas;

    public ContainerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
        paint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        this.canvas = canvas;
        circle = new Circle(paint,containerWidth, containerHeight );
    }
    class Circle{
        public Circle(Paint paint, int width, int height){
            int radius;
            radius = 100;
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.WHITE);
            canvas.drawPaint(paint);
            paint.setColor(Color.parseColor("#CD5C5C"));
            canvas.drawColor(color);
            canvas.drawCircle(containerWidth, containerHeight, radius, paint);
        }

    }


}
