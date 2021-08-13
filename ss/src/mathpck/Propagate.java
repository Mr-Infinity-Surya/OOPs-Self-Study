package mathpck;

import java.util.ArrayList;
import java.util.List;


public class Propagate {
    public List<Matrix> Forward_propagate(List<Matrix> W, List<Matrix> B, Matrix data) {

        List<Matrix> ans = new ArrayList<>();
        relu Relu=new relu();
        sigmoid Sigmoid=new sigmoid();

        Matrix Z1 = dot(W.get(0),data);
        addbias(Z1,B.get(0));
        Relu.activate(Z1);

        Matrix Z2 = dot(W.get(1), Z1);
        addbias(Z2,B.get(1));
        Relu.activate(Z2);

        Matrix Z3 = dot(W.get(2), Z2);
        addbias(Z3,B.get(2));
        Sigmoid.activate(Z3);

        ans.add(Z1);
        ans.add(Z2);
        ans.add(Z3);
        return ans;

    }

    public Matrix dot(Matrix m1, Matrix m2) {
        int r1 = m1.getRows(), c1 = m1.getCols();
        int r2 = m2.getRows(), c2 = m2.getCols();

        Matrix c = new Matrix(r1, c2);
        c.intilizeZeros();

        for (int i = 0; i < r1; i++) {
            for (int j = 0; j < c2; j++) {
                for (int k = 0; k < r2; k++) {
                    c.setval(c.getval(i, j) + m1.getval(i, k) * m2.getval(k, j), i, j);
                }
            }
        }
        return c;
    }

    interface activationFunction {
        void activate(Matrix m);
    }

    public class relu implements activationFunction {
        public void activate(Matrix m) {
            for(int i=0;i<m.getRows();i++){
                for(int j=0;j< m.getCols();j++){
                    if(m.getval(i,j)<0) m.setval(0.0,i,j);
                }
            }
        }
    }

    public class sigmoid implements activationFunction {
        public void activate(Matrix m) {
            for(int i=0;i<m.getRows();i++){
                for(int j=0;j< m.getCols();j++){
                    m.setval(1/(1+Math.exp((-1)*m.getval(i,j))),i,j);
                }
            }
        }
    }

    public void addbias(Matrix m1,Matrix m2){
        for(int i=0;i<m1.getRows();i++){
            for(int j=0;j<m1.getCols();j++){
                m1.setval(m1.getval(i,j)+m2.getval(i,0),i,j);
            }
        }
    }
}
