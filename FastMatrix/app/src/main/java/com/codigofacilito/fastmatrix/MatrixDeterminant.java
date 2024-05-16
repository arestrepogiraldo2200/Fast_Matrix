package com.codigofacilito.fastmatrix;

import java.util.ArrayList;

import static java.lang.Math.pow;

public class MatrixDeterminant {

    // ================================================================================
    // Computes the cofactor matrix
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
    // Recursive function to compute the determinant from cofactors
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
