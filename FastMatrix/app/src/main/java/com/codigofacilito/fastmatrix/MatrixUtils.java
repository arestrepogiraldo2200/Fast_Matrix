package com.codigofacilito.fastmatrix;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class MatrixUtils {

    // ================================================================================
    public static void printMatrix(ArrayList<ArrayList<Double>> M) {
        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.get(i).size(); j++) {
                if (M.get(i).get(j) >= 0){
                    System.out.printf(" %5.3f   ", M.get(i).get(j));
                }else {
                    System.out.printf("%5.3f   ", M.get(i).get(j));
                }
            }
            System.out.print("\n");
        }
    }

    // ================================================================================
    public static void printVector(ArrayList<Double> M) {
        for (int i = 0; i < M.size(); i++) {
            System.out.printf("%5.3f   ", M.get(i));
        }
        System.out.print("\n");
    }

    // ================================================================================
    public static ArrayList<Double> getColumn(ArrayList<ArrayList<Double>> M, int i) {

        ArrayList<Double> Col = new ArrayList<>();

        for (int k = 0; k < M.size(); k++) {
            for (int l = 0; l < M.get(k).size(); l++) {
                if (l == i) {
                    Col.add(M.get(k).get(l));
                }
            }
        }
        return Col;
    }

    // ================================================================================
    public static ArrayList<Double> getRow(ArrayList<ArrayList<Double>> M, int i) {

        ArrayList<Double> Row = M.get(i);
        return Row;
    }

    // ================================================================================
    public static ArrayList<Double> getDiagonal(ArrayList<ArrayList<Double>> M) {

        ArrayList<Double> diag = new ArrayList<>();

        for (int k = 0; k < M.size(); k++) {
            for (int l = 0; l < M.get(k).size(); l++) {
                if (l == k) {
                    diag.add(M.get(k).get(l));
                }
            }
        }
        return diag;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> identity(int a) {

        ArrayList<ArrayList<Double>> I = new ArrayList<>(a);

        for (int i = 0; i < a; i++) {
            I.add(new ArrayList<>(Collections.nCopies(a, 0.0)));
        }

        for (int k = 0; k < a; k++) {
            I.get(k).set(k,1.0);
        }
        return I;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> ones(int a) {

        ArrayList<ArrayList<Double>> O = new ArrayList<>(a);

        for (int i = 0; i < a; i++) {
            O.add(new ArrayList<>(Collections.nCopies(a, 1.0)));
        }

        return O;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> zeros(int a) {

        ArrayList<ArrayList<Double>> Z = new ArrayList<>(a);

        for (int i = 0; i < a; i++) {
            Z.add(new ArrayList<>(Collections.nCopies(a, 0.0)));
        }

        return Z;
    }


    // ================================================================================
    public static ArrayList<Double> zerosV(int a) {

        ArrayList<Double> vZ = new ArrayList<>(a);

        for (int i = 0; i < a; i++) {
            vZ.add(0.0);
        }

        return vZ;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> transposeMatrix(ArrayList<ArrayList<Double>> M) {

        ArrayList<ArrayList<Double>> Mt = new ArrayList<>(M.size());

        for (int i = 0; i < M.size(); i++) {
            Mt.add(new ArrayList<>(Collections.nCopies(M.size(), 0.0)));
        }

        for (int k = 0; k < M.size(); k++) {
            for (int l = 0; l < M.size(); l++) {
                Mt.get(k).set(l, M.get(l).get(k));
            }
        }

        return Mt;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> editRow(int row, ArrayList<ArrayList<Double>> M) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < M.size(); i++) {
            System.out.println("Enter the entry number "+(i+1)+": ");
            M.get(row).set(i, scanner.nextDouble());
        }
        return M;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> editColumn(int row, ArrayList<ArrayList<Double>> M) {

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < M.size(); i++) {
            System.out.println("Enter the entry number "+(i+1)+": ");
            M.get(row).set(i, scanner.nextDouble());
        }
        return M;
    }
}

