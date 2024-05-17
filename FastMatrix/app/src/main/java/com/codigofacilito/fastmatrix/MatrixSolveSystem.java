package com.codigofacilito.fastmatrix;
import java.util.ArrayList;

/**
 * The MatrixSolveSystem class provides methods to solve a system of linear equations
 * using Gaussian elimination. It includes private methods for performing pivoting,
 * converting a matrix to upper triangular form, and diagonalizing the matrix, as well
 * as a public method to solve the system using these techniques.
 */
public class MatrixSolveSystem {

    // ================================================================================
    /**
     * Creates the diagonal pivots by dividing each row by its diagonal elements.
     * @param M the matrix to pivot, represented as a list of lists of Doubles.
     * @param b the vector of constants, represented as a list of Doubles.
     */
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
    /**
     * Computes the upper triangular form of the matrix by row reduction and applying diagonal pivots.
     * @param M the matrix to transform, represented as a list of lists of Doubles.
     * @param b the vector of constants, represented as a list of Doubles.
     */
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
    /**
     * Diagonalizes the matrix by row reduction.
     * @param M the matrix to diagonalize, represented as a list of lists of Doubles.
     * @param b the vector of constants, represented as a list of Doubles.
     */
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
    /**
     * Solves a system of linear equations using Gaussian elimination.
     * @param M the matrix of coefficients, represented as a list of lists of Doubles.
     * @param b the vector of constants, represented as a list of Doubles.
     * @return the solution vector, represented as a list of Doubles.
     */
    public static ArrayList<Double> solveByGauss(ArrayList<ArrayList<Double>> M, ArrayList<Double> b) {
        pivot(M, b);
        pivotUpperTriangular(M, b);
        diagonal(M, b);
        return b;
    }
}