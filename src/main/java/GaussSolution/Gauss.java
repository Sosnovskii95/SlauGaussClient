package GaussSolution;

public class Gauss {
    private int countN;
    private int countM;

    private double[][] matrixA;
    private double[] masB;
    private double[] masX;

    private boolean sol;

    public Gauss(double[][] matrixA, double[] masB) {
        this.matrixA = matrixA;
        this.masB = masB;
    }

    public double[][] getMatrixA(){return  this.matrixA;}

    public double[] getMasB(){return  this.masB;}

    public double[] getMasX()
    {
        return this.masX;
    }

    public void Solution() {
        sol = true;
        int N = matrixA.length;

        for (int i = 0; i < N; i++) {

            int max = i;
            for (int j = i + 1; j < N; j++) {
                if (Math.abs(matrixA[j][j]) > Math.abs(matrixA[max][j])) {
                    max = j;
                }
            }
            double[] temp = matrixA[i];
            matrixA[i] = matrixA[max];
            matrixA[max] = temp;
            double t = masB[i];
            masB[i] = masB[max];
            masB[max] = t;

            for (int j = i + 1; j < N; j++) {
                double alpha = matrixA[j][i] / matrixA[i][i];
                masB[j] -= alpha * masB[i];
                for (int k = i; k < N; k++) {
                    matrixA[j][k] -= alpha * matrixA[i][k];
                }
            }

        }
    }

    public void findMasX()
    {
        int N = matrixA.length;

        masX = new double[N];
        for (int i = N - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = i + 1; j < N; j++) {
                sum += matrixA[i][j] * masX[j];
            }
            masX[i] = (masB[i] - sum) / matrixA[i][i];
        }
    }
}
