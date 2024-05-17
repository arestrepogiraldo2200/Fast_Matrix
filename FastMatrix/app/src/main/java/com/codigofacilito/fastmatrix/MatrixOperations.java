package com.codigofacilito.fastmatrix;
import java.util.ArrayList;
import java.util.Collections;


/**
 * The MatrixOperations class provides various methods for performing operations on matrices and vectors.
 * It includes methods for scalar multiplication, element-wise multiplication, matrix multiplication,
 * matrix-vector multiplication, dot product, and computing the trace of a matrix.
 */
public class MatrixOperations {

    // ================================================================================
    /**
     * Computes the scalar product of a scalar and a matrix.
     * @param M the matrix to be multiplied, represented as a list of lists of Doubles.
     * @param a the scalar value to multiply each element of the matrix.
     * @return the resulting matrix after scalar multiplication.
     */
    public static ArrayList<ArrayList<Double>> scalarProduct(ArrayList<ArrayList<Double>> M, double a) {
        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.size(); j++) {
                M.get(i).set(j, M.get(i).get(j) * a);
            }
        }
        return M;
    }

    // ================================================================================
    /**
     * Computes the element-wise product of two matrices of the same dimensions.
     * @param M the first matrix, represented as a list of lists of Doubles.
     * @param N the second matrix, represented as a list of lists of Doubles.
     * @return the resulting matrix after element-wise multiplication.
     */
    public static ArrayList<ArrayList<Double>> dotProductM(ArrayList<ArrayList<Double>> M, ArrayList<ArrayList<Double>> N) {

        ArrayList<ArrayList<Double>> P = new ArrayList<>(M.size());

        for (int i = 0; i < M.size(); i++) {
            P.add(new ArrayList<>(Collections.nCopies(M.size(), 0.0)));
        }

        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.size(); j++) {
                P.get(i).set(j, M.get(i).get(j) * N.get(i).get(j));
            }
        }
        return P;
    }

    // ================================================================================
    /**
     * Computes the product of two matrices of the same dimensions.
     * @param M the first matrix, represented as a list of lists of Doubles.
     * @param N the second matrix, represented as a list of lists of Doubles.
     * @return the resulting matrix after matrix multiplication.
     */
    public static ArrayList<ArrayList<Double>> matrixProduct(ArrayList<ArrayList<Double>> M, ArrayList<ArrayList<Double>> N) {

        ArrayList<ArrayList<Double>> P = new ArrayList<>(M.size());

        for (int i = 0; i < M.size(); i++) {
            P.add(new ArrayList<>(Collections.nCopies(M.size(), 0.0)));
        }

        for (int i = 0; i < M.size(); i++) {
            for (int j = 0; j < M.size(); j++) {
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
    /**
     * Computes the product of a matrix and a vector of the same dimension.
     * @param M the matrix, represented as a list of lists of Doubles.
     * @param v the vector, represented as a list of Doubles.
     * @return the resulting vector after matrix-vector multiplication.
     */
    public static ArrayList<Double> matrixTimesVector(ArrayList<ArrayList<Double>> M, ArrayList<Double> v) {

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
    /**
     * Computes the dot product of two vectors of the same dimension.
     * @param v the first vector, represented as a list of Doubles.
     * @param w the second vector, represented as a list of Doubles.
     * @return the resulting scalar after computing the dot product.
     */
    public static double dotProductV(ArrayList<Double> v, ArrayList<Double> w) {

        double p = 0.0;

        for (int i = 0; i < v.size(); i++) {
            p += v.get(i) * w.get(i);
        }
        return p;
    }

    // ================================================================================
    /**
     * Computes the scalar product of a scalar and a vector.
     * @param v the vector to be multiplied, represented as a list of Doubles.
     * @param a the scalar value to multiply each element of the vector.
     * @return the resulting vector after scalar multiplication.
     */
    public static ArrayList<Double> scalarProductV(ArrayList<Double> v, double a) {
        for (int j = 0; j < v.size(); j++) {
            v.set(j, v.get(j) * a);
        }
        return v;
    }

    // ================================================================================
    /**
     * Computes the trace of a matrix.
     * @param M the matrix, represented as a list of lists of Doubles.
     * @return the trace of the matrix.
     */
    public static double trace(ArrayList<ArrayList<Double>> M) {

        double s = 0.0;

        for (int i = 0; i < M.size(); i++) {
            s += M.get(i).get(i);
        }

        return s;
    }
}