package com.codigofacilito.fastmatrix;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

/**
 * The MatrixUtils class provides a set of utility methods for manipulating matrices and vectors.
 * These methods include printing matrices and vectors, extracting specific rows, columns, and diagonals,
 * as well as creating matrices of zeros, ones, and identity matrices. Additionally, it includes
 * methods for transposing matrices and editing specific rows or columns of matrices.
 */
public class MatrixUtils {

    // ================================================================================
    /**
     * Prints the given matrix to the console.
     * @param M the matrix to print, represented as a list of lists of Doubles.
     */
    public static void printMatrix(ArrayList<ArrayList<Double>> M) {
        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.get(i).size(); j++) {
                if (M.get(i).get(j) >= 0) {
                    System.out.printf(" %5.3f   ", M.get(i).get(j));
                } else {
                    System.out.printf("%5.3f   ", M.get(i).get(j));
                }
            }
            System.out.print("\n");
        }
    }

    // ================================================================================
    /**
     * Prints the given vector to the console.
     * @param M the vector to print, represented as a list of Doubles.
     */
    public static void printVector(ArrayList<Double> M) {
        for (int i = 0; i < M.size(); i++) {
            System.out.printf("%5.3f   ", M.get(i));
        }
        System.out.print("\n");
    }

    // ================================================================================
    /**
     * Extracts a specific column from the given matrix.
     * @param M the matrix from which to extract the column.
     * @param i the index of the column to extract.
     * @return the extracted column as a list of Doubles.
     */
    public static ArrayList<Double> getColumn(ArrayList<ArrayList<Double>> M, int i) {

        ArrayList<Double> Col = new ArrayList<>();
        for (int k = 0; k < M.size(); k++) {
            Col.add(M.get(k).get(i));
        }
        return Col;
    }

    // ================================================================================
    /**
     * Extracts a specific row from the given matrix.
     * @param M the matrix from which to extract the row.
     * @param i the index of the row to extract.
     * @return the extracted row as a list of Doubles.
     */
    public static ArrayList<Double> getRow(ArrayList<ArrayList<Double>> M, int i) {

        ArrayList<Double> Row = M.get(i);
        return Row;
    }

    // ================================================================================
    /**
     * Extracts the diagonal elements from the given matrix.
     * @param M the matrix from which to extract the diagonal.
     * @return the extracted diagonal as a list of Doubles.
     */
    public static ArrayList<Double> getDiagonal(ArrayList<ArrayList<Double>> M) {

        ArrayList<Double> diag = new ArrayList<>();

        for (int k = 0; k < M.size(); k++) {
            diag.add(M.get(k).get(k));
        }
        return diag;
    }

    // ================================================================================
    /**
     * Creates an identity matrix of the given dimension.
     * @param a the dimension of the identity matrix.
     * @return the identity matrix as a list of lists of Doubles.
     */
    public static ArrayList<ArrayList<Double>> identity(int a) {

        ArrayList<ArrayList<Double>> I = new ArrayList<>(a);

        for (int i = 0; i < a; i++) {
            I.add(new ArrayList<>(Collections.nCopies(a, 0.0)));
        }

        for (int k = 0; k < a; k++) {
            I.get(k).set(k, 1.0);
        }
        return I;
    }

    // ================================================================================
    /**
     * Creates a matrix of ones with the given dimension.
     * @param a the dimension of the matrix.
     * @return the matrix of ones as a list of lists of Doubles.
     */
    public static ArrayList<ArrayList<Double>> ones(int a) {

        ArrayList<ArrayList<Double>> O = new ArrayList<>(a);

        for (int i = 0; i < a; i++) {
            O.add(new ArrayList<>(Collections.nCopies(a, 1.0)));
        }

        return O;
    }

    // ================================================================================
    /**
     * Creates a matrix of zeros with the given dimension.
     * @param a the dimension of the matrix.
     * @return the matrix of zeros as a list of lists of Doubles.
     */
    public static ArrayList<ArrayList<Double>> zeros(int a) {

        ArrayList<ArrayList<Double>> Z = new ArrayList<>(a);
        for (int i = 0; i < a; i++) {
            Z.add(new ArrayList<>(Collections.nCopies(a, 0.0)));
        }

        return Z;
    }

    // ================================================================================
    /**
     * Creates a vector of zeros with the given dimension.
     * @param a the dimension of the vector.
     * @return the vector of zeros as a list of Doubles.
     */
    public static ArrayList<Double> zerosV(int a) {

        ArrayList<Double> vZ = new ArrayList<>(a);

        for (int i = 0; i < a; i++) {
            vZ.add(0.0);
        }

        return vZ;
    }

    // ================================================================================
    /**
     * Transposes the given matrix.
     * @param M the matrix to transpose.
     * @return the transposed matrix as a list of lists of Doubles.
     */
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
    /**
     * Edits a specific row of the given matrix.
     * @param row the index of the row to edit.
     * @param M the matrix to edit.
     * @return the edited matrix as a list of lists of Doubles.
     */
    public static ArrayList<ArrayList<Double>> editRow(int row, ArrayList<ArrayList<Double>> M) {

        if (row<1 || row >M.size()){
            System.out.println("Row out of bounds.");
            System.exit(0);
        }

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < M.size(); i++) {
            System.out.println("Enter the entry number " + (i + 1) + ": ");
            M.get(row).set(i, scanner.nextDouble());
        }
        return M;
    }

    // ================================================================================
    /**
     * Edits a specific column of the given matrix.
     * @param col the index of the column to edit.
     * @param M the matrix to edit.
     * @return the edited matrix as a list of lists of Doubles.
     */
    public static ArrayList<ArrayList<Double>> editColumn(int col, ArrayList<ArrayList<Double>> M) {

        if (col<1 || col >M.size()){
            System.out.println("Row out of bounds.");
            System.exit(0);
        }

        Scanner scanner = new Scanner(System.in);

        for (int i = 0; i < M.size(); i++) {
            System.out.println("Enter the entry number " + (i + 1) + ": ");
            M.get(i).set(col, scanner.nextDouble());
        }
        return M;
    }
}