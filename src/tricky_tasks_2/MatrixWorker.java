package tricky_tasks_2;

import java.util.InputMismatchException;

public class MatrixWorker implements IMatrixWorker{
    @Override
    public void print(double[][] m) {
        System.out.println("Result matrix:");
        for (double[] doubles : m) {
            for (int j = 0; j < m[0].length; j++)
                System.out.print(doubles[j] + " ");
            System.out.println();
        }
    }

    @Override
    public boolean haveSameDimension(double[][] m1, double[][] m2) {
        return m1.length == m2.length && m1[0].length == m2[0].length;
    }

    @Override
    public double[][] add(double[][] m1, double[][] m2) {
        if (!haveSameDimension(m1,m2))throw new InputMismatchException();
        double[][] m3 = new double[m1.length][m2[0].length];
        for (int row = 0; row < m3.length; row++)
        {
            for (int col = 0; col < m3[row].length; col++)
            {
                m3[row][col] = m1[row][col] + m2[row][col];
            }
        }
        return m3;
    }

    @Override
    public double[][] subtract(double[][] m1, double[][] m2) {
        if (!haveSameDimension(m1,m2))throw new InputMismatchException();
        double[][] m3 = new double[m1.length][m2[0].length];
        for (int row = 0; row < m3.length; row++)
        {
            for (int col = 0; col < m3[row].length; col++)
            {
                m3[row][col] = m1[row][col] - m1[row][col];
            }
        }
        return m3;
    }

    @Override
    public double[][] multiply(double[][] m1, double[][] m2) {
        if (!haveSameDimension(m1,m2))throw new InputMismatchException();
        double[][] m3 = new double[m1.length][m2[0].length];

        for (int row = 0; row < m3.length; row++)
        {
            for (int col = 0; col < m3[row].length; col++)
            {
                double cell = 0;
                for (int i = 0; i < m2.length; i++)
                {
                    cell += m1[row][i] * m2[i][col];
                }
                m3[row][col] = cell;
            }
        }
        return m3;
    }
}
