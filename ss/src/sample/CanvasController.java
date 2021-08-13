package sample;

import javafx.application.Platform;
import javafx.embed.swing.SwingFXUtils;
import javafx.fxml.FXML;
import javafx.scene.ImageCursor;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.image.*;
import mathpck.Matrix;
import mathpck.doall;
import javax.imageio.ImageIO;
import java.io.*;
import java.nio.ByteBuffer;


public class CanvasController {
    @FXML
    private Canvas canvas;
    @FXML
    private ColorPicker colorPicker;
    @FXML
    private CheckBox eraser;
    @FXML
    private TextArea list;
    @FXML
    private Slider brushSize;
    @FXML
    private Button clear;
    @FXML
    private Button predictor;
    @FXML
    private CheckBox lang;


    private GraphicsContext g;

    File newFile = new File("cursor.png");
    private final Image penCursor = new Image(newFile.toURI().toString());

    @FXML
    public void initialize() {
        brushSize.setMax(100);
        brushSize.setValue(65);
        g = canvas.getGraphicsContext2D();
        canvas.setCursor(new ImageCursor(penCursor, 0, penCursor.getHeight()));
        list.setWrapText(true);

        canvas.setOnMouseDragged(e -> {
            double size = Double.parseDouble(String.valueOf(brushSize.getValue()));
            double x = e.getX() - size / 2;
            double y = e.getY() - size / 2;
            if (eraser.isSelected()) {
                g.clearRect(x, y, size, size);
            } else {
                g.setFill(colorPicker.getValue());
                g.fillOval(x, y, size, size);
            }
        });
        clear.setOnAction(e ->{
            ClearCanvas();
            list.clear();
        });
    }
    public void predictMethod() throws IOException, InterruptedException {
        onSave();
        doall main=new doall();
        Matrix probilites=main.getprob("/home/surya/temp.png",lang.isSelected());
        int predic=main.getmaxval(probilites);

    for(int i=0;i<10;i++){
            double val = probilites.getval(i,0);
            list.appendText(i +" -> "+ val+"%\n");
        }
        list.appendText("\n"+"predicted value : " + predic+"\n");


    }
    public void ClearCanvas(){
        g.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());

    }

    public void onSave() {
        try {
            String path = "/home/surya/temp.png";
            Image snapshot = canvas.snapshot(null, null);
            ImageIO.write(SwingFXUtils.fromFXImage(snapshot, null), "png", new File(path));
        } catch (Exception e) {
            System.out.println("Failed to save image: " + e);
        }
    }
    public void onExit() { System.exit(0); }
}
