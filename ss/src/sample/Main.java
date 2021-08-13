package sample;
import mathpck.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.security.Signature;
import java.util.ArrayList;
import java.util.List;
import java.awt.image.*;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("canvas.fxml"));
        primaryStage.setTitle("Digit recognizer With Artificial Neural Networks" );
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public static void main(String[] args) throws IOException {
        launch(args);
////        BufferedImage image = ImageIO.read(new File("/home/surya/temp.png"));
////        Image scaleImage = image.getScaledInstance(28, 28,Image.SCALE_DEFAULT);
////        //File outputfile = new File("saved.png");
////         ImageIO.write(scaleImage, "png", "temp.png");
//
//

//        System.out.println("imprtdone");
//        List<Matrix> weigths=new ArrayList<>();
//
//        List<Matrix> bias=new ArrayList<>();
//String p1="/home/surya/jupyter saves/MNSIT digit recog/";
//     excelconvert W1=new excelconvert(16,784,p1+"W1.csv");
//        weigths.add(W1.data);
//        excelconvert W2=new excelconvert(16,16,p1+"W2.csv");
//        weigths.add(W2.data);
//        excelconvert W3=new excelconvert(10,16,p1+"W3.csv");
//        weigths.add(W3 .data);
////
//        excelconvert B1=new excelconvert(16,1,p1+"b1.csv");
//        bias.add(B1.data);
//        excelconvert B2=new excelconvert(16,1,p1+"b2.csv");
//        bias.add(B2.data);
//        excelconvert B3=new excelconvert(10,1,p1+"b3.csv");
//        bias.add(B3 .data);
//    System.out.println("Data import Done");
//    //label.display();
//    int layers[]= new int[]{784, 16, 16, 10};
//
//
//
//
////    for(int i=0;i<3;i++){
////        weigths.add(new Matrix(layers[i+1],layers[i]));
////        bias.add(new Matrix(layers[i+1],1));
////
////    }
////    for(Matrix mat:weigths){
////        mat.intilizeRandom();
////    }
////    for(Matrix mat:bias){
////        mat.intilizeZeros();
////    }
// Forward_propagate fwd=new Forward_propagate();
//   List<Matrix> up1=fwd.doevery(weigths,bias,temp);
//   Matrix pred=up1.get(2);
//        pred.display();



    }
}
