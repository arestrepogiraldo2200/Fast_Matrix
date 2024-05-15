package com.codigofacilito.fastmatrix;
import picocli.CommandLine.*;
import picocli.CommandLine;
import picocli.CommandLine.Command;
import picocli.CommandLine.Option;
import java.util.ArrayList;
import java.util.Objects;
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

    // Options of the cli
    @ArgGroup(exclusive = true)
    private JEnvOptions jEnvOptions;

    static class JEnvOptions {

        @Option(names = {"-defm"}, required = true,description = "Define a square matrix")
        private boolean nameM;

        @Option(names = {"-defv"}, required = true,description = "Define a vector")
        private boolean nameV;

        @Option(names = {"-findm"}, required = true, description = "Find and display a stored matrix")
        private String fnameM = "";

        @Option(names = {"-findv"}, required = true, description = "Find and display a stored vector")
        private String fnameV = "";

        @Option(names = {"-clean"}, required = true, description = "Cleans all the variables defined")
        private boolean clean;

        @Option(names = {"-det"}, required = true, description = "Compute the determinant of a stored matrix")
        private String detM = "";

        @Option(names = {"-prod"}, required = true, description = "Multiply a matrix times a vector of the same dimension. Deliver in notation A*v, where A is the matrix and v the vector. The * is mandatory")
        private String prod = "";

        @Option(names = {"-solve"}, required = true, description = "Solves a system of equations M*x = b. Deliver in notation M*x = b, where M is the matrix, x represents the vector of unknown variables and b the vector of constants. The * and x are mandatory")
        private String sol = "";

        @Option(names = {"-scalarm"}, required = true, description = "Multiplies a matrix by a scalar s in the notation s*M and saves it in a variable B. The notation is s*M->B")
        private String scM = "";

        @Option(names = {"-scalarv"}, required = true, description = "Multiplies a vector by a scalar s in the notation s*v and saves it in a variable w. The notation is s*v->w")
        private String scV = "";

        @Option(names = {"-dotm"}, required = true, description = "Multiplies a matrix by another matrix element by element. The notation is A.B->C")
        private String dotM = "";

        @Option(names = {"-dotv"}, required = true, description = "Multiplies a vector by another vector and returns a scalar. The notation is v.w")
        private String dotV = "";

        @Option(names = {"-prodm"}, required = true, description = "Multiplies a matrix by another matrix of the same dimensions ans returns another matrix. The notation is A*B->C")
        private String prodm = "";

        @Option(names = {"-getcol"}, required = true, description = "Gets column c of a given matrix and stores it in a vector. The notation is c@M->v")
        private String getcol = "";

        @Option(names = {"-getrow"}, required = true, description = "Gets row l of a given matrix and stores it in a vector. The notation is l@M->v")
        private String getRow = "";

        @Option(names = {"-getrodiag"}, required = true, description = "Gets diagonal of a given matrix and stores it in a vector. The notation is M->d")
        private String getDiag = "";

        @Option(names = {"-tr"}, required = true, description = "Transposes matrix M and stores it in N. Notation M->N")
        private String tr = "";

    }

    // call method to inform the status of execution
    @Override
    public Integer call() throws Exception {
        // ..........................................................
        if (jEnvOptions.nameM) {
            printVector(getVector("v", "x"));
        }

        // ..........................................................
        if (jEnvOptions.nameV) {
            //ReadData.enterMatrix();
            System.out.println("VVVVVVV");
            //return 0;
        }

        if (jEnvOptions.clean){
            clearData();
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.fnameM, "")){
            MatrixUtils.printMatrix(getMatrix("m", jEnvOptions.fnameM));
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.fnameV, "")){
            MatrixUtils.printVector(getVector("v", jEnvOptions.fnameV));
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.detM, "")){
            ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", jEnvOptions.detM);
            if (retrievedM.equals(zeros(1))){
                System.out.println("Cannot calculate the determinant");
                System.exit(0);
            } else {
                System.out.println("The determinant is: "+ MatrixDeterminant.determinant(retrievedM));
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.prod, "")){

            if (jEnvOptions.prod.matches("^.*\\*.*$")){

                String[] MandV = jEnvOptions.prod.split("\\*");

                ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", MandV[0]);
                ArrayList<Double> retrievedV = getVector("v", MandV[1]);

                if (retrievedM.equals(zeros(1)) || retrievedV.equals(zerosV(1))){
                    System.out.println("Cannot calculate the product because one of the factors is not defined.");
                    System.exit(0);
                } else {

                    if (retrievedM.size() != retrievedV.size()){
                        System.out.println("Cannot calculate the product because the factors have inconsistent dimensions.");
                        System.exit(0);
                    } else {
                        System.out.println(MandV[0] + "*" + MandV[1] + "=");
                        printVector(matrixTimesVector(retrievedM,retrievedV));
                    }
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.sol, "")){
            String[] MandV = jEnvOptions.sol.split("\\*x=");

            if (MandV.length == 2){

                ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", MandV[0]);
                ArrayList<Double> retrievedV = getVector("v", MandV[1]);

                if (retrievedM.equals(zeros(1)) || retrievedV.equals(zerosV(1))){
                    System.out.println("Cannot calculate the product because one of the factors is not defined.");
                    System.exit(0);
                } else {

                    if (retrievedM.size() != retrievedV.size()){
                        System.out.println("Cannot calculate the product because the factors have inconsistent dimensions.");
                        System.exit(0);
                    } else {
                        System.out.println("The solution of "+MandV[0] + "*x=" + MandV[1] + " is");
                        printVector(solveByGauss(retrievedM,retrievedV));
                    }
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.sol, "")){
            String[] MandV = jEnvOptions.sol.split("\\*x=");

            if (MandV.length == 2){

                ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", MandV[0]);
                ArrayList<Double> retrievedV = getVector("v", MandV[1]);

                if (retrievedM.equals(zeros(1)) || retrievedV.equals(zerosV(1))){
                    System.out.println("Cannot calculate the product because one of the factors is not defined.");
                    System.exit(0);
                } else {

                    if (retrievedM.size() != retrievedV.size()){
                        System.out.println("Cannot calculate the product because the factors have inconsistent dimensions.");
                        System.exit(0);
                    } else {
                        System.out.println("The solution of "+MandV[0] + "*x=" + MandV[1] + " is");
                        printVector(solveByGauss(retrievedM,retrievedV));
                    }
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.scM, "")){

            String[] scalarD = jEnvOptions.scM.split("\\*");
            if (scalarD.length == 2) {
                try {
                    double s = Double.parseDouble(scalarD[0]);
                    String[] scalarD2 =scalarD[1].split("->");

                    if (scalarD2.length == 2) {

                        ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", scalarD2[0]);

                        if (retrievedM.equals(zeros(1))) {
                            System.out.println("Cannot calculate the product because the matrix is not defined.");
                            System.exit(0);
                        } else {
                            ArrayList<ArrayList<Double>> pM = scalarProduct(retrievedM, s);
                            System.out.println("Result:\n"+scalarD2[1]+"=");
                            printMatrix(pM);
                            storeMatrixData("m", scalarD2[1], pM);
                        }
                    } else {
                        System.out.println(("Wrong input"));
                        System.exit(0);
                    }
                } catch (Exception e){
                    System.out.println("Wrong input");
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.scV, "")){

            String[] scalarD = jEnvOptions.scV.split("\\*");
            if (scalarD.length == 2) {
                try {
                    double s = Double.parseDouble(scalarD[0]);
                    String[] scalarD2 =scalarD[1].split("->");

                    if (scalarD2.length == 2) {

                        ArrayList<Double> retrievedV = getVector("v", scalarD2[0]);

                        if (retrievedV.equals(zerosV(1))) {
                            System.out.println("Cannot calculate the product because the vector is not defined.");
                            System.exit(0);
                        } else {
                            ArrayList<Double> pV = scalarProductV(retrievedV, s);
                            System.out.println("Result:\n"+scalarD2[1]+"=");
                            printVector(pV);
                            storeVectorData("v", scalarD2[1], pV);
                        }
                    } else {
                        System.out.println(("Wrong input"));
                        System.exit(0);
                    }
                } catch (Exception e){
                    System.out.println("Wrong input");
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.dotM, "")){

            String[] scalarD = jEnvOptions.dotM.split("\\.");
            if (scalarD.length == 2) {
                    String[] scalarD2 =scalarD[1].split("->");

                if (scalarD2.length == 2) {

                    ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", scalarD[0]);
                    ArrayList<ArrayList<Double>> retrievedN = getMatrix("m", scalarD2[0]);

                    if (retrievedM.equals(zeros(1)) || retrievedN.equals(zeros(1))) {
                        System.out.println("Cannot calculate the product because one of the factors is not defined.");
                        System.exit(0);
                    } else {
                        ArrayList<ArrayList<Double>> MP = dotProductM(retrievedM, retrievedN);
                        System.out.println("Result:\n"+scalarD2[1]+"=");
                        printMatrix(MP);
                        storeMatrixData("m", scalarD2[1], MP);
                    }
                } else {
                    System.out.println(("Wrong input"));
                    System.exit(0);
                }

            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.dotV, "")){

            String[] scalarD = jEnvOptions.dotV.split("\\.");

            if (scalarD.length == 2) {

                ArrayList<Double> retrievedv = getVector("v", scalarD[0]);
                ArrayList<Double> retrievedw = getVector("v", scalarD[1]);

                if (retrievedv.equals(zerosV(1)) || retrievedw.equals(zerosV(1))) {
                    System.out.println("Cannot calculate the product because one of the factors is not defined.");
                    System.exit(0);
                } else {
                    double p = dotProductV(retrievedv, retrievedw);
                    System.out.println("The dot product is "+p);
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.prodm, "")){

            String[] scalarD = jEnvOptions.prodm.split("\\*");
            if (scalarD.length == 2) {
                String[] scalarD2 =scalarD[1].split("->");

                if (scalarD2.length == 2) {

                    ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", scalarD[0]);
                    ArrayList<ArrayList<Double>> retrievedN = getMatrix("m", scalarD2[0]);

                    if (retrievedM.equals(zeros(1)) || retrievedN.equals(zeros(1))) {
                        System.out.println("Cannot calculate the product because one of the factors is not defined.");
                        System.exit(0);
                    } else {
                        ArrayList<ArrayList<Double>> MP = matrixProduct(retrievedM, retrievedN);
                        System.out.println("Result:\n"+scalarD2[1]+"=");
                        printMatrix(MP);
                        storeMatrixData("m", scalarD2[1], MP);
                    }
                } else {
                    System.out.println(("Wrong input"));
                    System.exit(0);
                }

            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.getcol, "")){

            String[] scalarD = jEnvOptions.getcol.split("@");

            if (scalarD.length == 2) {
                try {
                    int c = Integer.parseInt(scalarD[0]);
                    String[] scalarD2 =scalarD[1].split("->");

                    if (scalarD2.length == 2) {

                        ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", scalarD2[0]);

                        if (retrievedM.equals(zeros(1))) {
                            System.out.println("Cannot get the column because the matrix is not defined.");
                            System.exit(0);
                        } else {
                            ArrayList<Double> col = getColumn(retrievedM, c-1);
                            System.out.println("Column "+scalarD[0]+" of "+scalarD2[0]+" is");
                            printVector(col);
                            storeVectorData("v", scalarD2[1], col);
                        }
                    } else {
                        System.out.println(("Wrong input"));
                        System.exit(0);
                    }
                } catch (Exception e){
                    System.out.println("Wrong input");
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.getRow, "")){

            String[] scalarD = jEnvOptions.getRow.split("@");

            if (scalarD.length == 2) {
                try {
                    int c = Integer.parseInt(scalarD[0]);
                    String[] scalarD2 =scalarD[1].split("->");

                    if (scalarD2.length == 2) {

                        ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", scalarD2[0]);

                        if (retrievedM.equals(zeros(1))) {
                            System.out.println("Cannot get the row because the matrix is not defined.");
                            System.exit(0);
                        } else {
                            ArrayList<Double> row = getRow(retrievedM, c-1);
                            System.out.println("Row "+scalarD[0]+" of "+scalarD2[0]+" is");
                            printVector(row);
                            storeVectorData("v", scalarD2[1], row);
                        }
                    } else {
                        System.out.println(("Wrong input"));
                        System.exit(0);
                    }
                } catch (Exception e){
                    System.out.println("Wrong input");
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.getDiag, "")){

            String[] scalarD2 = jEnvOptions.getDiag.split("->");

            if (scalarD2.length == 2) {

                ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", scalarD2[0]);

                if (retrievedM.equals(zeros(1))) {
                    System.out.println("Cannot get the row because the matrix is not defined.");
                    System.exit(0);
                } else {
                    ArrayList<Double> diag = getDiagonal(retrievedM);
                    System.out.println("Diagonal of "+scalarD2[0]+" is");
                    printVector(diag);
                    storeVectorData("v", scalarD2[1], diag);
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }

        // ..........................................................
        if (!Objects.equals(jEnvOptions.tr, "")){

            String[] scalarD = jEnvOptions.tr.split("->");
            if (scalarD.length == 2) {

                ArrayList<ArrayList<Double>> retrievedM = getMatrix("m", scalarD[0]);

                if (retrievedM.equals(zeros(1))) {
                    System.out.println("Cannot get the diagonal because the matrix is not defined.");
                    System.exit(0);
                } else {
                    ArrayList<ArrayList<Double>> Mtr = transposeMatrix(retrievedM);
                    System.out.println("Matrix transposed:\n");
                    printMatrix(Mtr);
                    storeMatrixData("m", scalarD[1], Mtr);
                }
            } else {
                System.out.println(("Wrong input"));
                System.exit(0);
            }
        }























        return 0;
    }

    public static void main(String[] args) {

        // Communication with terminal
        int exitCode = new CommandLine(new FastMatrixApp()).execute(args);
        System.exit(exitCode);

        //ArrayList<ArrayList<Double>> M = new ArrayList<>();
        //M.add(new ArrayList<>(Arrays.asList(3.0,1.0,-3.0)));
        //M.add(new ArrayList<>(Arrays.asList(1.0,-3.0,-2.0)));
        //M.add(new ArrayList<>(Arrays.asList(3.0,-2.0,-1.0)));

//        ArrayList<Double> b = new ArrayList<>(Arrays.asList(-1.0,-12.0,-5.0));
//        ArrayList<ArrayList<Double>> M = new ArrayList<>();
//
//        M.add(new ArrayList<>(Arrays.asList(3.0,2.0,4.3)));
//        M.add(new ArrayList<>(Arrays.asList(2.0,0.0,2.0)));
//        M.add(new ArrayList<>(Arrays.asList(4.0,2.0,3.0)));
//
//


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
        //storeMatrixData("m", "K", M);

        //storeVectorData("v", "x", b);

        //printMatrix(getMatrix("m", "A"));
        //printVector(getVector("v", "z"));

//        printVector(matrixTimesVector(M,b));

//        storeMatrixData("m", "YY", enterMatrix());
        //storeVectorData("v", "lll", enterVector());

    }
}
