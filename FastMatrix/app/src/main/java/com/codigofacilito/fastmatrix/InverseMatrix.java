package com.codigofacilito.fastmatrix;

import java.util.ArrayList;

import static com.codigofacilito.fastmatrix.MatrixDeterminant.determinant;
import static com.codigofacilito.fastmatrix.MatrixOperations.scalarProduct;
import static com.codigofacilito.fastmatrix.MatrixUtils.*;
import static java.lang.Math.pow;

/**
 * The InverseMatrix class provides methods to compute the inverse of a matrix.
 * It includes methods for calculating the adjugate matrix, cofactor entries, and the inverse matrix.
 */
public class InverseMatrix {

    // ================================================================================
    /**
     * Computes the adjugate matrix of the given matrix.
     * @param M the original matrix, represented as a list of lists of Doubles.
     * @return the adjugate matrix of the given matrix.
     */
    private static ArrayList<ArrayList<Double>> adjugateMatrix(ArrayList<ArrayList<Double>> M) {

        ArrayList<ArrayList<Double>> Madj = new ArrayList<>();

        for (int i = 0; i < M.size(); i++) {

            ArrayList<Double> Row = new ArrayList<>();

            for (int j = 0; j < M.size(); j++) {
                double entry = pow(-1, i + j) * cofactorEntry(M, i, j);
                Row.add(entry);
            }
            Madj.add(Row);
        }
        return transposeMatrix(Madj);
    }

    // ================================================================================
    /**
     * Computes the determinant of the cofactor matrix by removing the specified row and column.
     * @param M the original matrix, represented as a list of lists of Doubles.
     * @param k the row to be removed.
     * @param l the column to be removed.
     * @return the determinant of the cofactor matrix.
     */
    private static double cofactorEntry(ArrayList<ArrayList<Double>> M, int k, int l) {

        ArrayList<ArrayList<Double>> Mcof = new ArrayList<>();

        for (int i = 0; i < M.size(); i++) {

            ArrayList<Double> Row = new ArrayList<>();

            if (i == k) {
                continue;
            } else {
                for (int j = 0; j < M.size(); j++) {
                    if (j == l) {
                        continue;
                    } else {
                        Row.add(M.get(i).get(j));
                    }
                }
                Mcof.add(Row);
            }
        }
        return determinant(Mcof);
    }

    // ================================================================================
    /**
     * Computes the inverse of the given matrix.
     * @param M the original matrix, represented as a list of lists of Doubles.
     * @return the inverse matrix if the given matrix is invertible; otherwise, a matrix of zeros.
     */
    public static ArrayList<ArrayList<Double>> inverseMatrix(ArrayList<ArrayList<Double>> M) {

        if (determinant(M) == 0.0) {
            System.out.println("Non invertible matrix.");
            return zeros(1);
        } else {
            return scalarProduct(adjugateMatrix(M), 1.0 / determinant(M));
        }
    }
}
