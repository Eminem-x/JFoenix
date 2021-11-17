package me.yuanhao.draw.stage;

import java.util.ArrayList;
import java.util.List;

import me.yuanhao.draw.common.Size;
import me.yuanhao.draw.shape.Shapes2D;
import javafx.scene.Group;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

/**
 * 画板容器，由多张画布（图层）合成
 *
 * @author Yuanhao
 */
@SuppressWarnings("all")
public class Board {
    public static Group content;
    private VBox vbox;
    private static Canvas drawingCanvas;
    private static GraphicsContext gc;
    private static Image background;
    public static int drawingCanvasWidth;
    public static int drawingCanvasHeight;
    private final Shapes2D shapeDrawer = new Shapes2D();
    private static List<Canvas> listCanvas;

    /**
     * 鼠标按下位置
     */
    private double x1;
    private double y1;

    /**
     * 鼠标松开位置
     */
    private double x2;
    private double y2;

    public Board(Image image) {
        // image initialed as background img
        background = image;
        init();
    }

    public void init() {
        content = new Group();
        vbox = new VBox();
        vbox.getChildren().add(content);
        listCanvas = new ArrayList<>();

        drawingCanvas = new Canvas(Size.CANVAS_WIDTH, Size.CANVAS_HEIGHT);
        gc = drawingCanvas.getGraphicsContext2D();
        gc.setFill(Color.WHITE);
        gc.drawImage(background, 60, 40, 1800, 900);
        gc.restore();

        content.getChildren().add(drawingCanvas);

        shapeDrawer.setCanvas(drawingCanvas, Shape.color, false);
        drawingCanvasWidth = (int) drawingCanvas.getWidth();
        drawingCanvasHeight = (int) drawingCanvas.getHeight();
        handleDrawingCanvas();
    }

    /**
     * 响应鼠标事件
     */
    private void handleDrawingCanvas() {
        drawingCanvas.setOnMousePressed(event -> {
            Canvas canvas = new Canvas(drawingCanvasWidth, drawingCanvasHeight);
            gc = canvas.getGraphicsContext2D();

            canvas.setOnMousePressed(drawingCanvas.getOnMousePressed());
            canvas.setOnMouseDragged(drawingCanvas.getOnMouseDragged());
            canvas.setOnMouseReleased(drawingCanvas.getOnMouseReleased());
            canvas.setOnMouseMoved(drawingCanvas.getOnMouseMoved());
            canvas.setOnMouseExited(drawingCanvas.getOnMouseExited());

            if ("OVAL".equals(Shape.toolName) ||
                "RECTANGLE".equals(Shape.toolName) ||
                "RECTANGLEY".equals(Shape.toolName)) {
                if (!"填充".equals(Shape.lineSize)) {
                    gc.setLineWidth(Integer.parseInt(Shape.lineSize));
                    shapeDrawer.setCanvas(canvas, Shape.color, false);
                } else if ("填充".equals(Shape.lineSize)) {
                    shapeDrawer.setCanvas(canvas, Shape.color, true);
                }
            } else {
                gc.setLineWidth(Shape.rubberSize);
                shapeDrawer.setCanvas(canvas, Shape.color, false);
            }

            if ("ERASER".equals(Shape.toolName)) {
                gc.setStroke(Color.WHITE);
            }

            x1 = event.getX();
            y1 = event.getY();

            listCanvas.add(canvas);
            content.getChildren().add(canvas);
        });

        drawingCanvas.setOnMouseDragged(event -> {
            if ("PEN".equals(Shape.toolName) || "ERASER".equals(Shape.toolName)) {
                gc.lineTo(event.getX(), event.getY());
                gc.stroke();
            }
        });

        drawingCanvas.setOnMouseReleased(event -> {
            x2 = event.getX();
            y2 = event.getY();
            double width = x2 - x1;
            double height = y2 - y1;
            if ("LINE".equals(Shape.toolName)) {
                shapeDrawer.drawLine(x1, y1, x2, y2);
            } else if ("OVAL".equals(Shape.toolName)) {
                shapeDrawer.drawOval(x1, y1, width, height);
            } else if ("RECTANGLE".equals(Shape.toolName)) {
                shapeDrawer.drawRectangle(x1, y1, width, height);
            } else if ("RECTANGLEY".equals(Shape.toolName)) {
                shapeDrawer.drawRoundRectangle(x1, y1, width, height);
            }
            gc.stroke();
        });
    }

    /**
     * 撤销操作
     */
    public static void undo() {
        if (!listCanvas.isEmpty()) {
            content.getChildren().remove(listCanvas.get(listCanvas.size() - 1));
            listCanvas.remove(listCanvas.size() - 1);
        }
    }

    public VBox getCanvas() {
        return vbox;
    }

    /**
     * 更新画布，清空list，清空容器，但是保留背景图
     */
    public static void clear() {
        content.getChildren().clear();
        listCanvas.clear();
        gc.setFill(Color.WHITE);
        gc.drawImage(background, 60, 40, 1800, 900);
        gc.restore();
        content.getChildren().add(drawingCanvas);
    }

    public List<Canvas> getList() {
        return listCanvas;
    }

    public int getW() {
        return drawingCanvasWidth;
    }

    public int getH() {
        return drawingCanvasHeight;
    }
}
