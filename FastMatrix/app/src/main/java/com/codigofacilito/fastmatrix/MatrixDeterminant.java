package com.codigofacilito.fastmatrix;

import java.util.ArrayList;

import static java.lang.Math.pow;

/**
 * The MatrixDeterminant class provides methods to compute the determinant of a matrix.
 * It includes methods for calculating the cofactor matrix and recursively computing the determinant.
 */
public class MatrixDeterminant {

    // ================================================================================
    /**
     * Computes the cofactor matrix by removing the specified row and column.
     * @param M the original matrix, represented as a list of lists of Doubles.
     * @param k the row to be removed.
     * @param l the column to be removed.
     * @return the cofactor matrix after removing the specified row and column.
     */
    private static ArrayList<ArrayList<Double>> cofactor(ArrayList<ArrayList<Double>> M, int k, int l) {

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
        return Mcof;
    }

    // ================================================================================
    /**
     * Recursively computes the determinant of a matrix using cofactors.
     * @param M the matrix, represented as a list of lists of Doubles.
     * @return the determinant of the matrix.
     */
    public static double determinant(ArrayList<ArrayList<Double>> M) {

        double det = 0;

        for (int i = 0; i < M.size(); i++) {
            det += pow(-1, i) * M.get(0).get(i) * determinant(cofactor(M, 0, i));
        }

        if (M.size() == 2) {
            return M.get(0).get(0) * M.get(1).get(1) - M.get(0).get(1) * M.get(1).get(0);
        }

        return det;
    }
}
