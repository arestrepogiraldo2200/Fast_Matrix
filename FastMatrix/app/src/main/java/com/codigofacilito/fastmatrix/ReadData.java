package com.codigofacilito.fastmatrix;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.json.*;
import static com.codigofacilito.fastmatrix.MatrixUtils.zeros;
import static com.codigofacilito.fastmatrix.MatrixUtils.zerosV;

public class ReadData {

    // Database file name
    static String jsonfile = "./data.json";

    // ================================================================================
    // Reads from console the entries of a matrix
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
    // Reads a single row from console
    private static ArrayList<Double> readRow(int dim) throws Exception {
        Scanner scanner = new Scanner(System.in);
        String row = scanner.nextLine();
        return castRow(row, dim);
    }

    // ================================================================================
    // Casts the row to double type
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
    // Reads from console the entries of a vector
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
    // Reads from console a single entry of a vector
    public static double readEntry() throws Exception {
        Scanner scanner = new Scanner(System.in);
        String row = scanner.nextLine();
        return Double.parseDouble(row);
    }

    // ================================================================================
    // Casts matrix arraylist to json array type
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
    // Casts vector arraylist to json array type
    public static JSONArray jsonVector(ArrayList<Double> v) {

        JSONArray jsonv = new JSONArray();

        for (int i = 0; i < v.size(); i++) {
            jsonv.put(v.get(i));
        }
        return jsonv;
    }

    // ================================================================================
    // Appends a matrix to the json database
    public static JSONObject appendMatrixData(String type, String name, ArrayList<ArrayList<Double>> M) throws JSONException {

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", "m");
        json.put("object", jsonMatrix(M));

        return json;
    }

    // ================================================================================
    // Appends a vector to the json database
    public static JSONObject appendVectorData(String type, String name, ArrayList<Double> v) throws JSONException {

        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("type", "v");
        json.put("object", jsonVector(v));

        return json;
    }

    // ================================================================================
    // Checks if json file exists, if not creates it
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
    // Stores a matrix to the json database
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
    // Stores a vector to the json database
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
    // Deletes all data in the json database
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
    // Casts from json database to the arraylist type
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
    // Casts from json database to the arraylist type
    public static ArrayList<Double> castJSONVectortoArrayList(JSONArray v) {

        ArrayList<Double> castedv = new ArrayList<>();

        for (int j = 0; j < v.length(); j++) {
            castedv.add(v.getDouble(j));
        }

        return castedv;
    }

    // ================================================================================
    // Searches a given name matrix
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
    // Searches a given name vector
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