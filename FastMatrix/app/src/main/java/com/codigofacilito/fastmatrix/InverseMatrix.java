package com.codigofacilito.fastmatrix;

import java.util.ArrayList;
import java.util.concurrent.SynchronousQueue;

import static com.codigofacilito.fastmatrix.MatrixDeterminant.determinant;
import static com.codigofacilito.fastmatrix.MatrixOperations.scalarProduct;
import static com.codigofacilito.fastmatrix.MatrixUtils.*;
import static java.lang.Math.pow;

public class InverseMatrix {

    // ================================================================================
    // Computes the adjugate matrix
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
    // Computes the determinant of a cofactor matrix
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
    // Computes the inverse of a matrix
    public static ArrayList<ArrayList<Double>> inverseMatrix(ArrayList<ArrayList<Double>> M) {

        if (determinant(M) == 0.0) {
            System.out.println("Non invertible matrix.");
            return zeros(1);
        } else {
            return scalarProduct(adjugateMatrix(M), 1.0 / determinant(M));
        }
    }
}
