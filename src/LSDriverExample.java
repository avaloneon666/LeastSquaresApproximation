import java.util.Arrays;

public class LSDriverExample {
    public static void main(String[] args) {
        double[] testPoints = {0.42, 1.25, 2.11};
        double[] points = {0.5, 1.618, 2.35, 3.5, 4.2};
        double[] first = linearRegressionTest(testPoints);
        double[] second = squaredRegressionTest(points);
        double[] third = logRegressionTest(points);
        double[] fourth = trigRegressionTest(points);
        double[] fifth = polyRegressionTest(points);
        System.out.println(Arrays.toString(first));
        System.out.println(Arrays.toString(second));
        System.out.println(Arrays.toString(third));
        System.out.println(Arrays.toString(fourth));
        System.out.println(Arrays.toString(fifth));
    }

    private static double[] linearRegressionTest(double evalPoints[]) {
        double[] results = new double[evalPoints.length];

        FunctionBasis fb = new FunctionBasis();

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return 1;
                    }

                    @Override
                    public String getDescription() {
                        return "Constant Function: 1";
                    }
                });

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return x;
                    }

                    @Override
                    public String getDescription() {
                        return "Linear Function: x";
                    }
                });

        Point[] dataSet = {new Point(0.0, 3.0), new Point(1.0, 3.14), new Point(2.0, 1.618)};
        LeastSquaresEngine lse = new LeastSquaresEngine();
        lse.setDataSet(dataSet);
        lse.setFunctionBasis(fb);

        lse.generateLSCoeff(fb);

        for (int i = 0; i < evalPoints.length; ++i) {
            results[i] = lse.evaluateApproxFunction(evalPoints[i]);
        }

        return results;
    }

    private static double[] squaredRegressionTest(double evalPoints[]) {
        double[] results = new double[evalPoints.length];

        Point[] dataSet = {new Point(0, 2.1), new Point(1, 0.13), new Point(2, 3.14),
                new Point(3, 5), new Point(4, 7), new Point(5, 9.5), new Point(6, 4.15)};

        FunctionBasis fb = new FunctionBasis();

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return 1;
                    }

                    @Override
                    public String getDescription() {
                        return "Constant Function: 1";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.pow(x, 2);
                    }

                    @Override
                    public String getDescription() {
                        return "Squared Function: x^2";
                    }
                }
        );

        LeastSquaresEngine lse = new LeastSquaresEngine();
        lse.setDataSet(dataSet);
        lse.setFunctionBasis(fb);

        lse.generateLSCoeff(fb);

        for (int i = 0; i < evalPoints.length; ++i) {
            results[i] = lse.evaluateApproxFunction(evalPoints[i]);
        }

        return results;
    }

    private static double[] logRegressionTest(double evalPoints[]) {
        double[] results = new double[evalPoints.length];

        Point[] dataSet = {new Point(0, 2.1), new Point(1, 0.13), new Point(2, 3.14),
                new Point(3, 5), new Point(4, 7), new Point(5, 9.5), new Point(6, 4.15)};

        FunctionBasis fb = new FunctionBasis();

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return 1;
                    }

                    @Override
                    public String getDescription() {
                        return "Constant Function: 1";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.log(x);
                    }

                    @Override
                    public String getDescription() {
                        return "Log Function: log x";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return x;
                    }

                    @Override
                    public String getDescription() {
                        return "Linear Function: x";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return x * Math.log(x);
                    }

                    @Override
                    public String getDescription() {
                        return "Log Function: x log x";
                    }
                }
        );

        LeastSquaresEngine lse = new LeastSquaresEngine();
        lse.setDataSet(dataSet);
        lse.setFunctionBasis(fb);

        lse.generateLSCoeff(fb);

        for (int i = 0; i < evalPoints.length; ++i) {
            results[i] = lse.evaluateApproxFunction(evalPoints[i]);
        }

        return results;
    }

    private static double[] trigRegressionTest(double evalPoints[]) {
        double[] results = new double[evalPoints.length];

        Point[] dataSet = {new Point(0, 2.1), new Point(1, 0.13), new Point(2, 3.14),
                new Point(3, 5), new Point(4, 7), new Point(5, 9.5), new Point(6, 4.15)};

        FunctionBasis fb = new FunctionBasis();

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return 1;
                    }

                    @Override
                    public String getDescription() {
                        return "Constant Function: 1";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.sin(x);
                    }

                    @Override
                    public String getDescription() {
                        return "Trig Function: sin x";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.cos(x);
                    }

                    @Override
                    public String getDescription() {
                        return "Trig Function: cos x";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.sin(2 * x);
                    }

                    @Override
                    public String getDescription() {
                        return "Trig Function: sin 2x";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.cos(2 * x);
                    }

                    @Override
                    public String getDescription() {
                        return "Trig Function: cos 2x";
                    }
                }
        );

        LeastSquaresEngine lse = new LeastSquaresEngine();
        lse.setDataSet(dataSet);
        lse.setFunctionBasis(fb);

        lse.generateLSCoeff(fb);

        for (int i = 0; i < evalPoints.length; ++i) {
            results[i] = lse.evaluateApproxFunction(evalPoints[i]);
        }

        return results;
    }

    private static double[] polyRegressionTest(double evalPoints[]) {
        double[] results = new double[evalPoints.length];

        Point[] dataSet = {new Point(0, 2.1), new Point(1, 0.13), new Point(2, 3.14),
                new Point(3, 5), new Point(4, 7), new Point(5, 9.5), new Point(6, 4.15)};

        FunctionBasis fb = new FunctionBasis();

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return 1;
                    }

                    @Override
                    public String getDescription() {
                        return "Constant Function: 1";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return x;
                    }

                    @Override
                    public String getDescription() {
                        return "Linear Function: x";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.pow(x, 2);
                    }

                    @Override
                    public String getDescription() {
                        return "Squared Function: x^2";
                    }
                }
        );

        fb.insertFunction(
                new Function() {
                    @Override
                    public double eval(double x) {
                        return Math.pow(x, 3);
                    }

                    @Override
                    public String getDescription() {
                        return "Cubed Function: x^3";
                    }
                }
        );

        LeastSquaresEngine lse = new LeastSquaresEngine();
        lse.setDataSet(dataSet);
        lse.setFunctionBasis(fb);

        lse.generateLSCoeff(fb);

        for (int i = 0; i < evalPoints.length; ++i) {
            results[i] = lse.evaluateApproxFunction(evalPoints[i]);
        }

        return results;
    }
}