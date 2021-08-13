package mathpck;
import mathpck.Propagate;

import java.util.ArrayList;
import java.util.List;

public class Back_Propagate extends Propagate{
    @Override
    public List<Matrix> Forward_propagate(List<Matrix> W, List<Matrix> B, Matrix data) {

        List<Matrix> ans = new ArrayList<>();
        relu Relu=new relu();
        sigmoid Sigmoid=new sigmoid();

        Matrix Z1 = dot(W.get(0),data);
        addbias(Z1,B.get(0));
        Matrix A1 = copyMatrix(Z1);
        Relu.activate(A1);

        Matrix Z2 = dot(W.get(1), Z1);
        addbias(Z2,B.get(1));
        Matrix A2 = copyMatrix(Z2);
        Relu.activate(A2);

        Matrix Z3 = dot(W.get(2), Z2);
        addbias(Z3,B.get(2));
        Matrix A3 = copyMatrix(Z3);
        Sigmoid.activate(A3);

        ans.add(Z1);
        ans.add(A1);
        ans.add(Z2);
        ans.add(A2);
        ans.add(Z3);
        ans.add(A3);
        return ans;
    }
    public Matrix copyMatrix(Matrix m){
        Matrix A = new Matrix(m.getRows(),m.getCols());
        for(int i=0;i<m.getRows();i++){
            for(int j=0;j< m.getCols();j++){
                A.setval(m.getval(i,j),i,j);
            }
        }
        return A;
    }
    public List<Matrix> Backward_propagate(List<Matrix> W, Matrix data,Matrix Y){
        int m = Y.getCols();
        dA3 = -divide(Y,W)-divide(one)
    }

}
