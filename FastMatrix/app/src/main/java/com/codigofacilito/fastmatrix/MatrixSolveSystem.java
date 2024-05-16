package com.codigofacilito.fastmatrix;
import java.util.ArrayList;

public class MatrixSolveSystem {

    // ================================================================================
    // Creates the diagonal pivots by dividing each row by its diagonal elements
    private static void pivot(ArrayList<ArrayList<Double>> M, ArrayList<Double> b) {

        for (int i = 0; i < M.size(); i++) {
            Double d = M.get(i).get(i);
            if (M.get(i).get(i) != 0) {
                b.set(i, b.get(i) / d);
                for (int j = 0; j < M.size(); j++) {
                    M.get(i).set(j, M.get(i).get(j) / d);
                }
            }
        }
    }

    // ================================================================================
    // Computes the upper triangular form by row reduction and the diagonal pivots
    private static void pivotUpperTriangular(ArrayList<ArrayList<Double>> M, ArrayList<Double> b) {

        for (int i = 1; i < M.size(); i++) {
            for (int j = 0; j < i; j++) {
                Double l = M.get(i).get(j);
                b.set(i, b.get(i) - b.get(j) * l);
                for (int k = 0; k < M.get(i).size(); k++) {
                    M.get(i).set(k, M.get(i).get(k) - M.get(j).get(k) * l);
                }
            }
            pivot(M, b);
        }
    }

    // ================================================================================
    // Diagonalizes the matrix by row reduction
    private static void diagonal(ArrayList<ArrayList<Double>> M, ArrayList<Double> b) {

        for (int i = M.size() - 2; i >= 0; i--) {
            for (int j = M.size() - 1; j >= i + 1; j--) {
                Double l = M.get(i).get(j);
                b.set(i, b.get(i) - b.get(j) * l);
                for (int k = 0; k < M.get(i).size(); k++) {
                    M.get(i).set(k, M.get(i).get(k) - M.get(j).get(k) * l);
                }
            }
        }
    }

    // ================================================================================
    // Implements the above functions and delivers the solution
    public static ArrayList<Double> solveByGauss(ArrayList<ArrayList<Double>> M, ArrayList<Double> b) {
        pivot(M, b);
        pivotUpperTriangular(M, b);
        diagonal(M, b);
        return b;
    }
}