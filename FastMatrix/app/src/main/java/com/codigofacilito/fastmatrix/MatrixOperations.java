package com.codigofacilito.fastmatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


public class MatrixOperations {

    // ================================================================================
    public static ArrayList<ArrayList<Double>> scalarProduct(ArrayList<ArrayList<Double>> M, double a){
        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.size(); j++){
                M.get(i).set(j, M.get(i).get(j) * a);
            }
        }
        return M;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> dotProductM(ArrayList<ArrayList<Double>> M, ArrayList<ArrayList<Double>> N){

        ArrayList<ArrayList<Double>> P = new ArrayList<>(M.size());

        for (int i = 0; i < M.size(); i++) {
            P.add(new ArrayList<>(Collections.nCopies(M.size(), 0.0)));
        }

        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.size(); j++){
                P.get(i).set(j, M.get(i).get(j) * N.get(i).get(j));
            }
        }
        return P;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> matrixProduct(ArrayList<ArrayList<Double>> M, ArrayList<ArrayList<Double>> N){

        ArrayList<ArrayList<Double>> P = new ArrayList<>(M.size());

        for (int i = 0; i < M.size(); i++) {
            P.add(new ArrayList<>(Collections.nCopies(M.size(), 0.0)));
        }

        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.size(); j++){
                double s = 0.0;
                for (int k = 0; k < M.size(); k++) {
                    s += M.get(i).get(k) * N.get(k).get(j);
                }
                P.get(i).set(j, s);
            }
        }
        return P;
    }

    // ================================================================================
    public static ArrayList<Double> matrixTimesVector(ArrayList<ArrayList<Double>> M, ArrayList<Double> v){

        ArrayList<Double> w = new ArrayList<>();

        for (int i = 0; i < M.size(); i++) {
            double s = 0.0;
            for (int k = 0; k < M.size(); k++) {
                s += M.get(i).get(k) * v.get(k);
            }
            w.add(s);
        }
        return w;
    }

    // ================================================================================
    public static double dotProductV(ArrayList<Double> v, ArrayList<Double> w){

        double p = 0.0;

        for (int i = 0; i < v.size(); i++) {
            p += v.get(i)*w.get(i);
        }
        return p;
    }

    // ================================================================================
    public static ArrayList<Double> scalarProductV(ArrayList<Double> v, double a){
        for (int j = 0; j < v.size(); j++){
            v.set(j, v.get(j) * a);
        }
        return v;
    }

}

