
import java.util.ArrayList;
import java.util.List;

public class FunctionBasis {

    private List<Function> basis = new ArrayList<Function>();

    public void insertFunction(Function f) {
        basis.add(f);
    }

    public void removeFunction(int index) {
        basis.remove(index);
    }

    public int getBasisSize() {
        return basis.size();
    }

    public String getFunctionDescription(int index) {
        return basis.get(index).getDescription();
    }

    public double evaluateFunction(int index, double x) {
        return basis.get(index).eval(x);
    }

}
