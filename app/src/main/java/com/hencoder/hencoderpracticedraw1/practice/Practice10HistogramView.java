package com.hencoder.hencoderpracticedraw1.practice;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.hencoder.hencoderpracticedraw1.practice.model.ChartData;
import com.hencoder.hencoderpracticedraw1.practice.model.ChartDataItem;
import com.hencoder.hencoderpracticedraw1.practice.util.ChartUtil;

class Practice10HistogramView extends View {

    public Practice10HistogramView(Context context) {
        super(context);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public Practice10HistogramView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        综合练习
//        练习内容：使用各种 Canvas.drawXXX() 方法画直方图
        int xCenter = canvas.getWidth() / 2;
        int yCenter = canvas.getHeight() / 2;

        ChartData demoData = ChartUtil.getDemoData();

        int widthChart = demoData.getDataList().size() * (demoData.getWidthUnit() + demoData.getWidthSpan()) + demoData.getWidthSpan();
        int heightChart = (int)(((float)(demoData.getMaxValue() - demoData.getStartY() )/demoData.getValuePerSpanHeight()) * demoData.getHeightSpan());

        if (heightChart + demoData.getTextSize() * 2 > canvas.getHeight() || widthChart + 10 > canvas.getWidth()) {
            Log.e("draw", "too Large Size, try adjust param of Chart");
        }

        int xDot = xCenter - widthChart / 2;
        int yDot = yCenter + heightChart / 2;

        Paint paint = new Paint();
        paint.setStrokeWidth(1);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(Color.WHITE);
        paint.setAntiAlias(true);
        //画坐标系
        canvas.drawLine(xDot, yDot, xDot, yDot - heightChart, paint);
        canvas.drawLine(xDot, yDot, xDot + widthChart, yDot, paint);
        for (int yHeight = 0; yHeight < heightChart; yHeight += demoData.getHeightSpan()) {
            int i = yHeight/demoData.getHeightSpan();
            String yStr = ((i * demoData.getValuePerSpanHeight()) + demoData.getStartY()) + "";
            if (0 != yHeight) {
                //画y轴标线
                paint.setStrokeWidth(5);
                paint.setColor(Color.WHITE);
                paint.setStrokeCap(Paint.Cap.ROUND);
                canvas.drawPoint(xDot, yDot - yHeight, paint);
            }
            //y轴注释
            paint.setColor(Color.WHITE);
            paint.setStrokeWidth(1);
            paint.setTextSize(10);
            paint.setTextAlign(Paint.Align.RIGHT);
            canvas.drawText(yStr, xDot - 5, yDot - yHeight, paint);
        }
        //画柱子
        paint.reset();
        paint.setStyle(Paint.Style.FILL);
        paint.setAntiAlias(true);
        for (int i = 0; i < demoData.getDataList().size(); i++) {
            ChartDataItem item = demoData.getDataList().get(i);
            paint.setColor(item.getColor());
            canvas.drawRect(xDot + demoData.getWidthSpan() * (i + 1) + demoData.getWidthUnit() * i,
                    yDot - demoData.getHeightSpan() * ((float)(item.getNum() - demoData.getStartY())/demoData.getValuePerSpanHeight()),
                    xDot + demoData.getWidthSpan() * (i + 1) + demoData.getWidthUnit() * (i + 1),
                    yDot, paint);
            //显示名称
            paint.setTextSize(demoData.getTextSize());
            paint.setTextAlign(Paint.Align.CENTER);
            canvas.drawText(item.getName(), xDot + demoData.getWidthSpan() * (i + 1) + demoData.getWidthUnit() * (i + 0.5f),
                    yDot + demoData.getTextSize(), paint);
            //显示数值
            paint.setColor(Color.WHITE);
            canvas.drawText(item.getNum() + "", xDot + demoData.getWidthSpan() * (i + 1) + demoData.getWidthUnit() * (i + 0.5f),
                    yDot - (demoData.getHeightSpan() / 2) * ((float)(item.getNum() - demoData.getStartY())/demoData.getValuePerSpanHeight()), paint);
        }
        //画标题
        paint.setAntiAlias(true);
        paint.setTextSize(demoData.getTitleTextSize());
        paint.setColor(Color.WHITE);
        paint.setTextAlign(Paint.Align.CENTER);
        canvas.drawText(demoData.getName(), xCenter, yDot + 50, paint);
    }
}
