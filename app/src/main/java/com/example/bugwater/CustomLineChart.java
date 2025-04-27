package com.example.bugwater;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.View;

public class CustomLineChart extends View {
    private float[] dataPoints;
    private Paint linePaint;
    private Paint pointPaint;
    private Paint textPaint;
    private String[] labels = {"Січ", "Лют", "Бер", "Квіт", "Трав", "Черв", "Лип", "Серп", "Вер", "Жовт", "Лист", "Груд"};

    public CustomLineChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        linePaint = new Paint();
        linePaint.setColor(Color.BLUE);
        linePaint.setStrokeWidth(6);
        linePaint.setStyle(Paint.Style.STROKE);

        pointPaint = new Paint();
        pointPaint.setColor(Color.BLUE);
        pointPaint.setStyle(Paint.Style.FILL);

        textPaint = new Paint();
        textPaint.setColor(Color.BLACK);
        textPaint.setTextSize(30);
    }

    public void setData(float[] data) {
        this.dataPoints = data;
        invalidate(); // Перемалювати графік
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (dataPoints == null || dataPoints.length == 0) return;

        float width = getWidth();
        float height = getHeight();
        float padding = 100; // Відступи для осей і міток

        // Знаходимо максимальне значення для масштабування
        float maxValue = 0;
        for (float value : dataPoints) {
            if (value > maxValue) maxValue = value;
        }
        maxValue = (float) Math.ceil(maxValue / 100) * 100; // Округлення вгору до найближчого 100

        // Крок між точками по осі X
        float stepX = (width - 2 * padding) / (dataPoints.length - 1);
        float stepY = (height - 2 * padding) / maxValue;

        // Малюємо осі
        Paint axisPaint = new Paint();
        axisPaint.setColor(Color.BLACK);
        axisPaint.setStrokeWidth(4);

        canvas.drawLine(padding, height - padding, width - padding, height - padding, axisPaint); // X-вісь
        canvas.drawLine(padding, padding, padding, height - padding, axisPaint); // Y-вісь

        // Малюємо мітки на осі X
        for (int i = 0; i < labels.length; i++) {
            canvas.drawText(labels[i], padding + i * stepX - 20, height - padding + 40, textPaint);
        }

        // Малюємо мітки на осі Y
        for (int i = 0; i <= maxValue; i += 100) {
            canvas.drawText(String.valueOf(i), padding - 60, height - padding - i * stepY, textPaint);
        }

        // Малюємо лінію і точки
        Path path = new Path();
        path.moveTo(padding, height - padding - dataPoints[0] * stepY);
        for (int i = 1; i < dataPoints.length; i++) {
            path.lineTo(padding + i * stepX, height - padding - dataPoints[i] * stepY);
        }
        canvas.drawPath(path, linePaint);

        for (int i = 0; i < dataPoints.length; i++) {
            canvas.drawCircle(padding + i * stepX, height - padding - dataPoints[i] * stepY, 10, pointPaint);
        }
    }
}