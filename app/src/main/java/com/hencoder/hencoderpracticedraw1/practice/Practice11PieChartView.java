package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.practice.model.ChartData;
import com.hencoder.hencoderpracticedraw1.practice.model.ChartDataItem;
import com.hencoder.hencoderpracticedraw1.practice.util.ChartUtil;

import java.util.List;

public class Practice11PieChartView extends View {

    private float radius;
    private List<ChartDataItem> datas;
    private Paint paint;
    private RectF rectF;
    private float total = 1.0f;

    float startAngle = 0f; // 开始的角度
    float sweepAngle;      // 扫过的角度
    float lineAngle;       // 当前扇形一半的角度

    float lineStartX = 0f; // 直线开始的X坐标
    float lineStartY = 0f; // 直线开始的Y坐标
    float lineEndX;        // 直线结束的X坐标
    float lineEndY;        // 直线结束的Y坐标

    public Practice11PieChartView(Context context) {
        super(context);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice11PieChartView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setStrokeWidth(1);
        paint.setTextSize(10);
        rectF = new RectF(-150, -150, 150, 150);
        ChartData demoData = ChartUtil.getDemoData();
        datas = demoData.getDataList();
        canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);  // 将画布(0，0)坐标点移到画布的中心
        startAngle = 0f; //这句代码很重要，不然有bug
        for (ChartDataItem data : datas) {
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(data.getColor());
            sweepAngle = data.getPercent() / total * 360f;
            lineAngle = startAngle + sweepAngle / 2;
            lineStartX = radius * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineStartY = radius * (float) Math.sin(lineAngle / 180 * Math.PI);
            lineEndX = (radius + 50) * (float) Math.cos(lineAngle / 180 * Math.PI);
            lineEndY = (radius + 50) * (float) Math.sin(lineAngle / 180 * Math.PI);
            if (data.getNum() == demoData.getMaxValue()) {
                canvas.save();
                canvas.translate(lineStartX * 0.1f, lineStartY * 0.1f);
                canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
            } else {
                canvas.drawArc(rectF, startAngle, sweepAngle - 1.0f, true, paint);
            }
            paint.setColor(Color.WHITE);
            paint.setStyle(Paint.Style.STROKE);
            canvas.drawLine(lineStartX, lineStartY, lineEndX, lineEndY, paint);
            if (lineAngle > 90 && lineAngle <= 270) {
                canvas.drawLine(lineEndX, lineEndY, lineEndX - 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX - 50 - 10 - paint.measureText(data.getName()), lineEndY, paint);
            } else {
                canvas.drawLine(lineEndX, lineEndY, lineEndX + 50, lineEndY, paint);
                paint.setStyle(Paint.Style.FILL);
                canvas.drawText(data.getName(), lineEndX + 50 + 10, lineEndY, paint);
            }
            if (data.getNum() == demoData.getMaxValue()) {
                canvas.restore();
            }
            startAngle += sweepAngle;
        }
    }
}
