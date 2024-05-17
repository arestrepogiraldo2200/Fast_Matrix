package com.codigofacilito.fastmatrix;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.json.*;
import static com.codigofacilito.fastmatrix.MatrixUtils.zeros;
import static com.codigofacilito.fastmatrix.MatrixUtils.zerosV;

/**
 * ReadData class provides methods for reading matrices and vectors from the console,
 * converting them to JSON format, and storing them in a JSON file. It also includes methods
 * for retrieving and converting data from the JSON file.
 * <p>
 * The JSON file is used as a database to store and retrieve the matrices and vectors defined by the user.
 * The class includes methods to handle the creation of the JSON file, appending data, clearing
 * data, and searching for specific matrices or vectors by name.
 * </p>
 * <p>
 * The methods provided by this class by purpose are:
 * <ul>
 *   <li>enterMatrix, readRow, castRow, enterVector, readEntryReading: matrices and vectors from the console</li>
 *   <li>jsonMatrix, jsonVector, appendMatrixData, appendVectorData: Converting matrices and vectors to and from JSON format</li>
 *   <li>storeMatrixData, storeVectorData, castJSONMatrixtoArrayList, castJSONVectortoArrayList, getMatrix, getVector: Storing and retrieving matrices and vectors from a JSON file</li>
 *   <li>checkExistence, clearData: Handling the creation and maintenance of the JSON file</li>
 * </ul>
 * </p>
 * <p>
 * The JSON structure for storing matrices and vectors is as follows:
 * <ul>
 *   <li>name: The name of the matrix or vector</li>
 *   <li>type: The type of the data ("m" for matrices, "v" for vectors)</li>
 *   <li>object: The matrix or vector data in JSON format</li>
 * </ul>
 * </p>
 */
public class ReadData {

    // Database file name
    static String jsonfile = "./data.json";

    // ================================================================================
    /**
     * Reads the entries of a square matrix from the console.
     * @return A 2D ArrayList representing the matrix.
     */
    public static ArrayList<ArrayList<Double>> enterMatrix() {

        System.out.println("Enter the dimension of a square matrix: ");
        Scanner scanner = new Scanner(System.in);
        int dim = scanner.nextInt();

        if (dim < 1){
            System.out.println("Invalid dimension");
            System.exit(0);
        }

        ArrayList<ArrayList<Double>> P = new ArrayList<>(dim);
        for (int i = 0; i < dim; i++) {
            P.add(new ArrayList<>(Collections.nCopies(dim, 0.0)));
        }

        for (int i = 0; i < dim; i++) {

            boolean sentinel = true;
            while (sentinel) {
                try {
                    System.out.println("Enter row " + (i + 1) + " with numbers separated by spaces: ");
                    P.set(i, readRow(dim));
                    sentinel = false;

                } catch (Exception e) {
                    System.out.println("Invalid format. Enter row again.");
                }
            }
        }
        return P;
    }

    // ================================================================================
    /**
     * Reads a single row of a matrix from the console.
     * @param dim The dimension of the matrix.
     * @return An ArrayList representing the row.
     * @throws Exception If there is an error reading the row.
     */
    private static ArrayList<Double> readRow(int dim) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String row = scanner.nextLine();
        return castRow(row, dim);
    }

    // ================================================================================
    /**
     * Casts a row of strings to a row of doubles.
     * @param row The row of the matrix as a space-separated string.
     * @param dim The expected dimension of the row.
     * @return An ArrayList of Doubles.
     * @throws Exception If the row string cannot be cast to the expected ArrayList dimension.
     */
    public static ArrayList<Double> castRow(String row, int dim) throws Exception {

        String[] rowSplit = row.split(" ");
        ArrayList<Double> castedRow = new ArrayList<>(dim);

        if (rowSplit.length != dim) {
            throw new Exception();
        }
        for (int i = 0; i < dim; i++) {
            castedRow.add(i, Double.parseDouble(rowSplit[i]));
        }
        return castedRow;
    }

    // ================================================================================
    /**
     * Reads the entries of a vector from the console.
     * @return An ArrayList representing the vector.
     */
    public static ArrayList<Double> enterVector() {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of the vector: ");
        int dim = scanner.nextInt();

        if (dim < 1){
            System.out.println("Invalid dimension");
            System.exit(0);
        }

        ArrayList<Double> V = new ArrayList<>(dim);
        for (int i = 0; i < dim; i++) {
            V.add(0.0);
        }

        for (int i = 0; i < dim; i++) {

            boolean sentinel = true;
            while (sentinel) {
                try {
                    System.out.println("Set entry " + (i + 1) + " : ");
                    V.set(i, readEntry());
                    sentinel = false;

                } catch (Exception e) {
                    System.out.println("Invalid. Enter again.");
                }
            }
        }
        return V;
    }

    // ================================================================================
    /**
     * Reads a single entry of a vector from the console.
     * @return The entry as a double.
     * @throws Exception If there is an error reading the entry.
     */
    public static double readEntry() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String row = scanner.nextLine();
        return Double.parseDouble(row);
    }

    // ================================================================================
    /**
     * Converts a matrix represented by an ArrayList to a JSONArray.
     * @param M The matrix as an ArrayList of ArrayLists of Doubles.
     * @return The matrix as a JSONArray.
     */
    public static JSONArray jsonMatrix(ArrayList<ArrayList<Double>> M) {

        JSONArray jsonM = new JSONArray();

        for (int i = 0; i < M.size(); i++) {
            JSONArray jsonRow = new JSONArray();
            for (int j = 0; j < M.size(); j++) {
                jsonRow.put(M.get(i).get(j));
            }
            jsonM.put(jsonRow);
        }
        return jsonM;
    }

    // ================================================================================
    /**
     * Converts a vector represented by an ArrayList to a JSONArray.
     * @param v The vector as an ArrayList of Doubles.
     * @return The vector as a JSONArray.
     */
    public static JSONArray jsonVector(ArrayList<Double> v) {

        JSONArray jsonv = new JSONArray();

        for (int i = 0; i < v.size(); i++) {
            jsonv.put(v.get(i));
        }
        return jsonv;
    }

    // ================================================================================
    /**
     * Appends a matrix to the JSON database.
     * @param type The type of the matrix.
     * @param name The name of the matrix.
     * @param M The matrix as an ArrayList of ArrayLists of Doubles.
     * @return A JSONObject representing the matrix.
     * @throws JSONException If there is an error creating the JSON object.
     */
    public static JSONObject appendMatrixData(String type, String name, ArrayList<ArrayList<Double>> M) throws JSONException {

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", "m");
        json.put("object", jsonMatrix(M));

        return json;
    }

    // ================================================================================
    /**
     * Appends a vector to the JSON database.
     * @param type The type of the vector.
     * @param name The name of the vector.
     * @param v The vector as an ArrayList of Doubles.
     * @return A JSONObject representing the vector.
     * @throws JSONException If there is an error creating the JSON object.
     */
    public static JSONObject appendVectorData(String type, String name, ArrayList<Double> v) throws JSONException {

        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("type", "v");
        json.put("object", jsonVector(v));

        return json;
    }

    // ================================================================================
    /**
     * Checks if the JSON file exists, and creates it if it does not.
     */
    public static void checkExistence() {

        File f = new File(jsonfile);
        if (!f.exists()) {
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // ================================================================================
    /**
     * Stores a matrix in the JSON database.
     * @param type The type of the matrix.
     * @param name The name of the matrix.
     * @param M The matrix as an ArrayList of ArrayLists of Doubles.
     */
    public static void storeMatrixData(String type, String name, ArrayList<ArrayList<Double>> M) {

        checkExistence();

        try (FileReader readFile = new FileReader(jsonfile)) {
            // Loads the data already in the json file
            JSONTokener tokener = new JSONTokener(readFile);
            JSONArray jsonArray;

            // If the file has data, load it, else initialize the jsonarray empty
            if (tokener.more()) {
                jsonArray = new JSONArray(tokener);
            } else {
                jsonArray = new JSONArray();
            }

            // Delete repeated definition
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getString("type").equals(type) && jsonArray.getJSONObject(i).getString("name").equals(name)) {
                    jsonArray.remove(i);
                }
            }

            // Cast to json the new data and appends to the old data
            JSONObject json = appendMatrixData(type, name, M);
            jsonArray.put(json);

            // Saves the data in the file
            try (FileWriter file = new FileWriter(jsonfile);) {
                file.write(jsonArray.toString());
                file.flush();
            } catch (IOException e) {
                System.out.println("There was an error writing the data.");
                throw new RuntimeException(e);
            }

        } catch (JSONException | IOException e) {
            System.out.println("There was an error reading the data.");
            throw new RuntimeException(e);
        }
    }

    // ================================================================================
    /**
     * Stores a vector in the JSON database.
     * @param type The type of the vector.
     * @param name The name of the vector.
     * @param v The vector as an ArrayList of Doubles.
     */
    public static void storeVectorData(String type, String name, ArrayList<Double> v) {

        checkExistence();

        try (FileReader readFile = new FileReader(jsonfile)) {
            // Loads the data already in the json file
            JSONTokener tokener = new JSONTokener(readFile);
            JSONArray jsonArray;

            // If the file has data, load it, else initialize the jsonarray empty
            if (tokener.more()) {
                jsonArray = new JSONArray(tokener);
            } else {
                jsonArray = new JSONArray();
            }

            // Delete repeated definition
            for (int i = 0; i < jsonArray.length(); i++) {
                if (jsonArray.getJSONObject(i).getString("type").equals(type) && jsonArray.getJSONObject(i).getString("name").equals(name)) {
                    jsonArray.remove(i);
                }
            }

            // Cast to json the new data and appends to the old data
            JSONObject json = appendVectorData(type, name, v);
            jsonArray.put(json);

            // Saves the data in the file
            try (FileWriter file = new FileWriter(jsonfile);) {
                file.write(jsonArray.toString());
                file.flush();
            } catch (IOException e) {
                System.out.println("There was an error writing the data.");
                throw new RuntimeException(e);
            }

        } catch (JSONException | IOException e) {
            System.out.println("There was an error reading the data.");
            throw new RuntimeException(e);
        }
    }

    // ================================================================================
    /**
     * Deletes all data in the JSON database.
     */
    public static void clearData() {

        checkExistence();

        // Saves the data in the file
        try (FileWriter file = new FileWriter(jsonfile);) {
            file.flush();
        } catch (IOException e) {
            System.out.println("There was an error erasing the data.");
            throw new RuntimeException(e);
        }
    }

    // ================================================================================
    /**
     * Converts a matrix represented by a JSONArray to an ArrayList.
     * @param M The matrix as a JSONArray.
     * @return The matrix as an ArrayList of ArrayLists of Doubles.
     */
    public static ArrayList<ArrayList<Double>> castJSONMatrixtoArrayList(JSONArray M) {

        ArrayList<ArrayList<Double>> castedM = new ArrayList<>();

        for (int i = 0; i < M.length(); i++) {
            JSONArray row = M.getJSONArray(i);
            ArrayList<Double> castedRow = new ArrayList<>();
            for (int j = 0; j < M.length(); j++) {
                castedRow.add(row.getDouble(j));
            }
            castedM.add(castedRow);
        }

        return castedM;
    }

    // ================================================================================
    /**
     * Converts a vector represented by a JSONArray to an ArrayList.
     * @param v The vector as a JSONArray.
     * @return The vector as an ArrayList of Doubles.
     */
    public static ArrayList<Double> castJSONVectortoArrayList(JSONArray v) {

        ArrayList<Double> castedv = new ArrayList<>();

        for (int j = 0; j < v.length(); j++) {
            castedv.add(v.getDouble(j));
        }

        return castedv;
    }

    // ================================================================================
    /**
     * Searches for a matrix with a given name in the JSON database.
     * @param type The type of the data. It can be only "m" or "v".
     * @param name The name of the matrix.
     * @return The matrix as an ArrayList of ArrayLists of Doubles, or a zero matrix if not found.
     */
    public static ArrayList<ArrayList<Double>> getMatrix(String type, String name) {

        checkExistence();

        try (FileReader readFile = new FileReader(jsonfile)) {

            // Loads the data already in the json file
            JSONTokener tokener = new JSONTokener(readFile);
            JSONArray jsonArray;

            // If the file has data, load it
            if (tokener.more()) {

                jsonArray = new JSONArray(tokener);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getString("type").equals(type) && jsonObject.getString("name").equals(name)) {
                        return castJSONMatrixtoArrayList(jsonObject.getJSONArray("object"));
                    }
                }
                System.out.println("No matrix with name " + name + " has been defined");
                return zeros(1);

            } else {
                System.out.println("No matrix with name " + name + " has been defined");
            }

        } catch (JSONException | IOException e) {
            System.out.println("There was an error reading the data.");
            throw new RuntimeException(e);
        }
        return zeros(1);
    }

    // ================================================================================
    /**
     * Searches for a vector with a given name in the JSON database.
     * @param type The type of the data. It can be only "m" or "v".
     * @param name The name of the vector.
     * @return The vector as an ArrayList of Doubles, or a zero vector if not found.
     */
    public static ArrayList<Double> getVector(String type, String name) {

        checkExistence();

        try (FileReader readFile = new FileReader(jsonfile)) {

            // Loads the data already in the json file
            JSONTokener tokener = new JSONTokener(readFile);
            JSONArray jsonArray;

            // If the file has data, load it
            if (tokener.more()) {

                jsonArray = new JSONArray(tokener);

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getString("type").equals(type) && jsonObject.getString("name").equals(name)) {
                        return castJSONVectortoArrayList(jsonObject.getJSONArray("object"));
                    }
                }
                System.out.println("No vector with name " + name + " has been defined");
                return zerosV(1);

            } else {
                System.out.println("No vector with name " + name + " has been defined");
            }

        } catch (JSONException | IOException e) {
            System.out.println("There was an error reading the data.");
            throw new RuntimeException(e);
        }
        return zerosV(1);
    }
}