package sample;

import mathpck.Matrix;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.regex.*;
import java.util.List;

class csvException extends Exception{
    csvException(){
        System.out.println("Error in reading CSV file"+
                "Check file for type of CSV file");
    }
}
public class excelconvert
{
    public Matrix data=null;
    public excelconvert(int rowc, int colc, String path) {
        data = new Matrix(rowc,colc);
        String line = "";
        String splitBy = ",";
        int rowindex=0;
        data.intilizeZeros();
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((line = br.readLine()) != null) {
                Pattern P = Pattern.compile("(?<=\s|^)(\d{1,7}(,\d{7})*(\.\d\d)?|\.\d\d)(?=\s|$)");
                Matcher m = P.matcher(line);
                if (m.matches() == false) throw new csvException();
                String[] values = line.split(splitBy);
                for (int i = 0; i < colc; i++) {
                    data.setval(Double.parseDouble(values[i]), rowindex, i);
                }
                rowindex++;
            }
        }
        catch (IOException | csvException e)
        {
            e.printStackTrace();
        }
    }
}
