package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

public class Practice8DrawArcView extends View {

    public Practice8DrawArcView(Context context) {
        super(context);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice8DrawArcView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        练习内容：使用 canvas.drawArc() 方法画弧形和扇形
        int xCenter = canvas.getWidth() / 2;
        int yCenter = canvas.getHeight() / 2;
        Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStyle(Paint.Style.STROKE);
        canvas.drawArc(xCenter - 100, yCenter - 100, xCenter + 100, yCenter + 100, 90, 90, true, paint);
        canvas.drawArc(xCenter - 100, yCenter - 100, xCenter + 100, yCenter + 100, 180, 90, false, paint);
        paint.setStyle(Paint.Style.FILL_AND_STROKE);
        canvas.drawArc(xCenter - 100, yCenter - 100, xCenter + 100, yCenter + 100, 0, 90, true, paint);
        canvas.drawArc(xCenter - 100, yCenter - 100, xCenter + 100, yCenter + 100, -90, 90, false, paint);
    }
}
