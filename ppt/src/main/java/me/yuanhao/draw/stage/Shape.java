package me.yuanhao.draw.stage;

import javafx.scene.paint.Color;

/**
 * 当前图形类，纪录当前图形需要的属性
 *
 * @author Yuanhao
 */
public class Shape {
    public static String toolName = "PEN";
    public static String lineSize = "7";
    public static int rubberSize = 7;
    public static Color color = Color.BLACK;

    public static void resetToolName(String name) {
        Shape.toolName = name;
    }

    public static void resetLineSize(String size) {
        Shape.lineSize = size;
    }

    public static void resetRubberSize(int size) {
        Shape.rubberSize = size;
    }

    public static void resetColor(Color c) {
        Shape.color = c;
    }
}
