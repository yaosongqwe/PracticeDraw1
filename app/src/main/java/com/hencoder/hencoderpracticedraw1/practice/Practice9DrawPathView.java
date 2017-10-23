package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice9DrawPathView extends View {

    public Practice9DrawPathView(Context context) {
        super(context);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice9DrawPathView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawPath() 方法画心形
        int xCenter = canvas.getWidth() / 2;
        int yCenter = canvas.getHeight() / 2;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        Path path = new Path();
        path.arcTo(xCenter - 60, yCenter - 30, xCenter, yCenter + 30, 135, 225, true);
        path.arcTo(xCenter, yCenter - 30, xCenter + 60, yCenter + 30, 180, 225, true);
        path.lineTo(xCenter, (float)(yCenter + 30 + Math.sqrt(2d) * 30));
        path.lineTo((float) (xCenter - (Math.sqrt(2d) * 15 + 30)), (float)(yCenter + Math.sqrt(2d) * 15));
        canvas.drawPath(path, paint);
        paint.reset();
        paint.setAntiAlias(true);
        paint.setTextSize(15);
        paint.setColor(Color.WHITE);
    }
}
