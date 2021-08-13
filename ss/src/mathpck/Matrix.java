package mathpck;
 public class Matrix {
     private double[][] mat;
     int row,col;

    String type;
    public Matrix(){
        mat=new double[0][0];
        row=0;
        col=0;
        type="Not Initialized";
    }
    public Matrix(int row, int col){
        this.col=col;
        this.row=row;
        mat=new double[row][col];
        type="2d";
    }
    public Matrix(int row){
        this.row=row;
        col=1;
        mat=new double[row][col];
        type="1d";
    }
    public void display(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){

                System.out.print(mat[i][j]+" ");
            }
            System.out.print('\n');
        }
        System.out.println("done");
    }
    public void intilizeRandom(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                mat[i][j]=Math.random();
            }
        }
    }
    public void intilizeZeros(){
        for(int i=0;i<row;i++){
            for(int j=0;j<col;j++){
                mat[i][j]=0.0;
            }
        }
    }
    public void setval(double val,int i,int j){
        mat[i][j]=val;
    }
    public void setval(double val,int i){
        mat[i][0]=val;
    }
    public int getRows(){
        return row;
    }
    public int getCols(){
        return col;
    }
    public double getval(int i,int j){
        return mat[i][j];
    }
    public double getval(int i){
        return mat[i][0];
    }
}

