package fxDrawing.shape;

import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

/**
 * 画图类，设置画笔样式，封装画图函数
 *
 * @author Yuanhao
 */

public class Shapes2D {
    private GraphicsContext gc;
    private boolean fill;

    /**
     * 设置画笔样式
     *
     * @param c     当前画布
     * @param color 画笔颜色
     * @param f     是否填充满
     */
    public void setCanvas(Canvas c, Color color, boolean f) {
        gc = c.getGraphicsContext2D();
        fill = f;
        if (fill) {
            gc.setFill(color);
        } else {
            gc.setStroke(color);
        }
    }

    /**
     * 画直线
     *
     * @param x1 起点x
     * @param y1 起点y
     * @param x2 终点x
     * @param y2 终点y
     */
    public void drawLine(double x1, double y1, double x2, double y2) {
        gc.moveTo(x1, y1);
        gc.lineTo(x2, y2);
        gc.stroke();
    }

    /**
     * 画椭圆
     */
    public void drawOval(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if (fill) {
            gc.fillOval(x1, y1, width, height);
        } else {
            gc.strokeOval(x1, y1, width, height);
        }
    }

    /**
     * 画直角矩形
     */
    public void drawRectangle(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if (fill) {
            gc.fillRect(x1, y1, width, height);
        } else {
            gc.strokeRect(x1, y1, width, height);
        }
    }

    /**
     * 画圆角矩形
     */
    public void drawRoundRectangle(double x1, double y1, double width, double height) {
        if (width < 0) {
            width = -width;
            x1 = x1 - width;
        }
        if (height < 0) {
            height = -height;
            y1 = y1 - height;
        }
        if (fill) {
            gc.fillRoundRect(x1, y1, width, height, 30, 30);
        } else {
            gc.strokeRoundRect(x1, y1, width, height, 30, 30);
        }
    }

    /**
     * 填充整个画布
     */
    public void drawFillCanvas(double w, double h) {
        gc.fillRect(0, 0, w, h);
    }
}
