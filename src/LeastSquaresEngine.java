public class LeastSquaresEngine {
    private FunctionBasis fb;
    private Point[] dataSet;
    double[] coeff;
    private boolean dirty = true;

    public void setFunctionBasis(FunctionBasis fb) {
        dirty = true;
        this.fb = fb;
    }

    public FunctionBasis getFunctionBasis() {
        return fb;
    }

    public void setDataSet(Point[] ds) {
        dirty = true;
        dataSet = ds;
    }

    public double[] generateLSCoeff(FunctionBasis fb){
        if (!dirty) {
            return coeff;
        }


        int size = fb.getBasisSize();
        coeff = new double[size];
        double[][] CoeffA = new double[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                CoeffA[i][j] = CoeffEvalX(i, j, fb, dataSet);//Calculates the Summation Matrix for the A column in Ax=B.
            }

        }


        double[] CoeffB = new double[size];
        for (int i = 0; i < size; i++) {
            CoeffB[i] = CoeffEvalY(i, fb, dataSet);    //Calculates the Summation Matrix for the B column in Ax=B.
        }


        naiveForwardElimination(fb.getBasisSize(), CoeffA, CoeffB);//Calculates the Coefficients by using gaussian elimination used in Assignment 2.
        naiveBackwardSubstitution(fb.getBasisSize(), CoeffA, CoeffB, coeff);//Calculates the Coefficients by using gaussian elimination used in Assignment 2.

        dirty = false;
        return coeff;
    }

    public double CoeffEvalX(int mainIndex, int latentIndex, FunctionBasis fb, Point[] ds) {  //Returns the summation result to the Matrix.
        double summation = 0;
        for (int i = 0; i < dataSet.length; i++) {
            double tempA = fb.evaluateFunction(mainIndex, dataSet[i].x);
            double tempB = fb.evaluateFunction(latentIndex, dataSet[i].x);
            summation = summation + (tempA * tempB);
        }
        return summation;
    }

    public double CoeffEvalY(int mainIndex, FunctionBasis fb, Point[] ds) {  //Returns the summation result to the Matrix.
        double summation = 0;
        for (int i = 0; i < dataSet.length; i++) {
            double tempA = fb.evaluateFunction(mainIndex, dataSet[i].x);
            double tempB = dataSet[i].y;
            summation = summation + (tempA * tempB);
        }
        return summation;
    }

    public double naiveBackwardSubstitution(int n, double[][] coeff, double[] consti, double[] sol) {//Calculates the Coefficients by using gaussian elimination used in Assignment 2.
        sol[n - 1] = consti[n - 1] / coeff[n - 1][n - 1];
        for (int i = n - 2; i >= 0; i--) {
            double sum = consti[i];
            for (int j = i + 1; j <= n - 1; j++) {
                sum = sum - coeff[i][j] * sol[j];
            }
            sol[i] = sum / coeff[i][i];
        }
        return 1;
    }

    public double naiveForwardElimination(int n, double[][] coeff, double[] consti) {//Calculates the Coefficients by using gaussian elimination used in Assignment 2.
        for (int k = 0; k < n - 1; k++) {
            for (int i = k + 1; i < n; i++) {
                double mult = coeff[i][k] / coeff[k][k];
                for (int j = k + 1; j < n; j++) {
                    coeff[i][j] -= mult * coeff[k][j];
                }
                consti[i] = consti[i] - mult * consti[k];

            }
        }
        return 1;
    }

    public double evaluateApproxFunction(double x) {
        if (dirty) generateLSCoeff(fb);

        double result = 0.0;

        for (int i = 0; i < fb.getBasisSize(); ++i) {
            result += coeff[i] * fb.evaluateFunction(i, x);
        }

        return result;
    }

}
