package com.codigofacilito.fastmatrix;

import picocli.CommandLine.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.Callable;

import static com.codigofacilito.fastmatrix.MatrixUtils.*;
import static com.codigofacilito.fastmatrix.MatrixSolveSystem.*;
import static com.codigofacilito.fastmatrix.MatrixOperations.*;
import static com.codigofacilito.fastmatrix.ReadData.*;


@Command(
        name="fm",
        mixinStandardHelpOptions = true,
        version = "FAST MATRIX 0.0.1",
        description = "Operate with square matrices and vectors quickly"
)
public class FastMatrixApp implements Callable<Integer>{


    @Option(names = {"-defm", "--definem"}, description = "Define a square matrix")
    private boolean definem;

    @Parameters(paramLabel = "<Matrix name>", index="0", description = "Name of matrix to be stored")
    private String nameM;

    // call method to inform the status of execution
    @Override
    public Integer call() throws Exception {
//        if (definem) {
//            ReadData.enterMatrix();
//            return 0;
//        }
        return 0;
    }

    // Communication with terminal
    public static void main(String[] args) {
//        int exitCode = new CommandLine(new FastMatrixApp()).execute(args);
//        System.exit(exitCode);

        //ArrayList<ArrayList<Double>> M = new ArrayList<>();
        //M.add(new ArrayList<>(Arrays.asList(3.0,1.0,-3.0)));
        //M.add(new ArrayList<>(Arrays.asList(1.0,-3.0,-2.0)));
        //M.add(new ArrayList<>(Arrays.asList(3.0,-2.0,-1.0)));

        ArrayList<Double> b = new ArrayList<>(Arrays.asList(-1.0,-12.0,-5.0));


        ArrayList<ArrayList<Double>> M = new ArrayList<>();

        M.add(new ArrayList<>(Arrays.asList(3.0,2.0,4.3)));
        M.add(new ArrayList<>(Arrays.asList(2.0,0.0,2.0)));
        M.add(new ArrayList<>(Arrays.asList(4.0,2.0,3.0)));


        //ArrayList<ArrayList<Double>> M = new ArrayList<>();

        //M.add(new ArrayList<>(Arrays.asList(2.0,3.0)));
       // M.add(new ArrayList<>(Arrays.asList(1.0,-1.0)));

        //ArrayList<ArrayList<Double>> N = new ArrayList<>();

        //N.add(new ArrayList<>(Arrays.asList(2.0,3.0)));
        //N.add(new ArrayList<>(Arrays.asList(1.0,-1.0)));


        //MatrixUtils.printMatrix(M);
        //MatrixUtils.printVector(MatrixUtils.getDiagonal(M));
        //MatrixUtils.printVector(MatrixUtils.getColumn(M,1));
        //MatrixUtils.printVector(MatrixUtils.getRow(M,2));

        //ArrayList<Double> b = new ArrayList<>(Arrays.asList(10.0,3.0));

       //System.out.println(MatrixDeterminant.determinant(M));
//
//       MatrixUtils.printMatrix(MatrixUtils.transposeMatrix(M));
//
//        ArrayList<ArrayList<Double>> N = ReadData.enterMatrix();
//        ArrayList<Double> v = ReadData.enterVector();
//
//       MatrixUtils.printVector(MatrixSolveSystem.solveByGauss(M,v));

        //clearData();
        storeMatrixData("m", "K", M);

        storeVectorData("v", "x", b);

        //printMatrix(getMatrix("m", "A"));
        //printVector(getVector("v", "z"));

//        storeMatrixData("m", "YY", enterMatrix());
        //storeVectorData("v", "lll", enterVector());

    }
}
