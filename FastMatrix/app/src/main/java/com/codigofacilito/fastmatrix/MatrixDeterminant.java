package com.codigofacilito.fastmatrix;

import java.util.ArrayList;
import java.util.Collections;

public class MatrixDeterminant {

    // ================================================================================
    public static ArrayList<ArrayList<Double>> upperTriangular(ArrayList<ArrayList<Double>> M){

        for (int i = 1; i < M.size(); i++) {
            for (int j = 0; j < i; j++) {
                Double l = M.get(i).get(j);
                if (M.get(j).get(j) == 0){
                    continue;
                } else {
                    for (int k = 0; k < M.get(i).size(); k++) {
                        M.get(i).set(k, M.get(i).get(k) - M.get(j).get(k) * l / M.get(j).get(j));
                    }
                }
            }
        }
        return M;
    }

    // ================================================================================
    public static double determinant(ArrayList<ArrayList<Double>> M){

        ArrayList<ArrayList<Double>> P = upperTriangular(M);
        double det = 1;

        for (int i = 0; i < P.size(); i++) {
            det *= P.get(i).get(i);
        }

        return det;
    }





}
