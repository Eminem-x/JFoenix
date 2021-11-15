package fxDrawing.stage;

import javafx.scene.paint.Color;

/**
 * 当前图形类，纪录当前图形需要的属性
 *
 * @author Yuanhao
 */
public class Shape {
    static String toolName = "PEN";
    static String lineSize = "7";
    static int rubberSize = 7;
    static int fontSize = 12;
    static String fontFamily = "AIGDT";
    static Color color = Color.BLACK;

    static void resetToolName(String name) {
        Shape.toolName = name;
    }

    static void resetLineSize(String size) {
        Shape.lineSize = size;
    }

    static void resetRubberSize(int size) {
        Shape.rubberSize = size;
    }

    static void resetFontSize(int size) {
        Shape.fontSize = size;
    }

    static void resetFontFamily(String font) {
        Shape.fontFamily = font;
    }

    static void resetColor(Color c) {
        Shape.color = c;
    }
}
