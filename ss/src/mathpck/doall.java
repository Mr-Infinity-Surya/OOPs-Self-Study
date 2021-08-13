package mathpck;

import sample.excelconvert;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class doall {
    public Matrix getimg7841(String path) throws IOException {
        ImageConvert t2 = new ImageConvert();
        String temp2 = t2.getrescale(path);
        Matrix temp = t2.convertTo2D(temp2);
        return temp;
    }
    public class ParallelDataLoading implements Runnable {
        Thread thread;
        String path = "/home/surya/jupyter saves/MNSIT digit recog/";
        boolean iskannada;
        excelconvert Weights;
        int row,col;
        ParallelDataLoading(boolean iskannada,String filename,int row,int col) {
            this.iskannada = iskannada;
            if(iskannada == true) {
                this.path=this.path + "kannada/";
            }
            this.path += filename;
            this.col=col;
            this.row=row;
            thread = new Thread(this);
        }
        @Override
        public void run() {
            try {
                Weights = new excelconvert(row,col,path); Weights = new excelconvert(row,col,path);
                thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
    public List<Matrix> getweights(boolean iskannada) throws InterruptedException {

        List<Matrix> weigths=new ArrayList<>();
        ParallelDataLoading W1=new ParallelDataLoading(iskannada,"W1.csv",16,784);
        ParallelDataLoading W2=new ParallelDataLoading(iskannada,"W2.csv",16,16);
        ParallelDataLoading W3=new ParallelDataLoading(iskannada,"W3.csv",10,16);
        W1.thread.start();
        W2.thread.start();
        W3.thread.start();
        W1.thread.join();
        W2.thread.join();
        W3.thread.join();
        weigths.add(W1.Weights.data);
        weigths.add(W2.Weights.data);
        weigths.add(W3.Weights.data);
        //String p1 = "/home/surya/jupyter saves/MNSIT digit recog/";
        //excelconvert W1=new excelconvert(16,784,p1+"W1.csv");
//
//        excelconvert W2=new excelconvert(16,16,p1+"W2.csv");
//        weigths.add(W2.data);
//        excelconvert W3=new excelconvert(10,16,p1+"W3.csv");
//        weigths.add(W3.data);
        return  weigths;
    }
    public List<Matrix> getbias(boolean iskannada) throws InterruptedException {

//        List<Matrix> bias=new ArrayList<>();
//        String p1="/home/surya/jupyter saves/MNSIT digit recog/";
//        if(iskannada==true) p1+="kannada/";
//        excelconvert B1=new excelconvert(16,1,p1+"b1.csv");
//        bias.add(B1.data);
//        excelconvert B2=new excelconvert(16,1,p1+"b2.csv");
//        bias.add(B2.data);
//        excelconvert B3=new excelconvert(10,1,p1+"b3.csv");
//        bias.add(B3 .data);
        List<Matrix> bias=new ArrayList<>();
        ParallelDataLoading b1=new ParallelDataLoading(iskannada,"b1.csv",16,1);
        ParallelDataLoading b2=new ParallelDataLoading(iskannada,"b2.csv",16,1);
        ParallelDataLoading b3=new ParallelDataLoading(iskannada,"b3.csv",10,1);
        b1.thread.start();
        b2.thread.start();
        b3.thread.start();
        b1.thread.join();
        b2.thread.join();
        b3.thread.join();
        bias.add(b1.Weights.data);
        bias.add(b2.Weights.data);
        bias.add(b3.Weights.data);
        return bias;
    }
    public Matrix getprob(String path,boolean flag) throws IOException, InterruptedException {
        Propagate fwd = new Propagate();
        List<Matrix> up1 = fwd.Forward_propagate(getweights(flag), getbias(flag), getimg7841(path));
        Matrix probabilities = up1.get(2);
        double sum=0;
        for(int i=0;i<10;i++){
            sum+=probabilities.getval(i,0);
        }
        for(int i=0;i<10;i++){
            double val = probabilities.getval(i,0);
            val/=sum;
            val*=1000;
            val=Math.floor(val);
            val/=10;
            probabilities.setval(val,i,0);
        }
        return probabilities;
    }
    public int getmaxval(Matrix m){
        int ans=0;
        double val=-1;
        for(int i=0;i<10;i++){
            if(val<m.getval(i,0)) {
                val=m.getval(i,0);
                ans=i;
            }
        }
        return ans;
    }

}

