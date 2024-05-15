package com.codigofacilito.fastmatrix;
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
import org.json.*;
import static com.codigofacilito.fastmatrix.MatrixUtils.zeros;
import static com.codigofacilito.fastmatrix.MatrixUtils.zerosV;

public class ReadData {

    static String jsonfile = "./data.json";

    // ================================================================================
    public static ArrayList<ArrayList<Double>> enterMatrix(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of a square matrix: ");
        int dim = scanner.nextInt();                      // BUG HERE ===========================================0

        ArrayList<ArrayList<Double>> P = new ArrayList<>(dim);
        for (int i = 0; i < dim; i++) {
            P.add(new ArrayList<>(Collections.nCopies(dim, 0.0)));
        }

        for (int i = 0; i<dim; i++){

            boolean sentinel = true;
            while(sentinel){
                try {
                    System.out.println("Enter row " + (i + 1) + " with numbers separated by spaces: ");
                    P.set(i, readRow(dim));
                    sentinel = false;

                } catch(Exception e){
                    System.out.println("Invalid format. Enter row again.");
                }
            }
        }
        return P;
    }

    // ================================================================================
    public static ArrayList<Double> readRow(int dim) throws Exception{
        Scanner scanner = new Scanner(System.in);
        String row = scanner.nextLine();
        return castRow(row, dim);
    }

    // ================================================================================
    public static ArrayList<Double> castRow(String row, int dim) throws Exception{

        String[] rowSplit = row.split(" ");
        ArrayList<Double> castedRow = new ArrayList<>(dim);

        if (rowSplit.length != dim){
            throw new Exception();
        }

        for (int i = 0; i<dim; i++){
            castedRow.add(i, Double.parseDouble(rowSplit[i]));
        }
        return castedRow;
    }

    // ================================================================================
    public static ArrayList<Double> enterVector(){

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter the dimension of the vector matrix: ");
        int dim = scanner.nextInt();                      // BUG HERE ===========================================0

        ArrayList<Double> V = new ArrayList<>(dim);
        for (int i = 0; i < dim; i++) {
            V.add(0.0);
        }

        for (int i = 0; i<dim; i++){

            boolean sentinel = true;
            while(sentinel){
                try {
                    System.out.println("Set entry " + (i + 1) + " : ");
                    V.set(i, readEntry());
                    sentinel = false;

                } catch(Exception e){
                    System.out.println("Invalid. Enter again.");
                }
            }
        }
        return V;
    }

    // ================================================================================
    public static double readEntry() throws Exception{
        Scanner scanner = new Scanner(System.in);
        String row = scanner.nextLine();
        return Double.parseDouble(row);
    }

    // ================================================================================
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
    public static JSONArray jsonVector(ArrayList<Double> v) {

        JSONArray jsonv = new JSONArray();

        for (int i = 0; i < v.size(); i++) {
            jsonv.put(v.get(i));
        }
        return jsonv;
    }

    // ================================================================================
    public static JSONObject appendMatrixData(String type, String name, ArrayList<ArrayList<Double>> M) throws JSONException {

        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("type", "m");
        json.put("object", jsonMatrix(M));

        return json;
    }

    // ================================================================================
    public static JSONObject appendVectorData(String type, String name, ArrayList<Double> v) throws JSONException {

        JSONObject json = new JSONObject();

        json.put("name", name);
        json.put("type", "v");
        json.put("object", jsonVector(v));

        return json;
    }
    // ================================================================================
    public static void checkExistence(){

        File f = new File(jsonfile);
        if (!f.exists()){
            try {
                f.createNewFile();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }

    }

    // ================================================================================
    public static void storeMatrixData(String type, String name, ArrayList<ArrayList<Double>> M) {

        checkExistence();

        try  (FileReader readFile = new FileReader(jsonfile)) {
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
            for (int i=0; i< jsonArray.length(); i++){
                if (jsonArray.getJSONObject(i).getString("type").equals(type) && jsonArray.getJSONObject(i).getString("name").equals(name)){
                    jsonArray.remove(i);
                }
            }

            // Cast to json the new data and appends to the old data
            JSONObject json = appendMatrixData(type, name, M);
            jsonArray.put(json);

            // Saves the data in the file
            try  (FileWriter file = new FileWriter(jsonfile);) {
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
    public static void storeVectorData(String type, String name, ArrayList<Double> v) {

        checkExistence();

        try  (FileReader readFile = new FileReader(jsonfile)) {
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
            for (int i=0; i< jsonArray.length(); i++){
                if (jsonArray.getJSONObject(i).getString("type").equals(type) && jsonArray.getJSONObject(i).getString("name").equals(name)){
                    jsonArray.remove(i);
                }
            }

            // Cast to json the new data and appends to the old data
            JSONObject json = appendVectorData(type, name, v);
            jsonArray.put(json);

            // Saves the data in the file
            try  (FileWriter file = new FileWriter(jsonfile);) {
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
    public static void clearData() {

        checkExistence();

        // Saves the data in the file
        try  (FileWriter file = new FileWriter(jsonfile);) {
            file.flush();
        } catch (IOException e) {
            System.out.println("There was an error erasing the data.");
            throw new RuntimeException(e);
        }
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> castJSONMatrixtoArrayList(JSONArray M){

        ArrayList<ArrayList<Double>> castedM = new ArrayList<>();

        for (int i = 0; i < M.length(); i++){
            JSONArray row = M.getJSONArray(i);
            ArrayList<Double> castedRow = new ArrayList<>();
            for (int j = 0; j < M.length(); j++){
                castedRow.add(row.getDouble(j));
            }
            castedM.add(castedRow);
        }

        return castedM;
    }

    // ================================================================================
    public static ArrayList<Double> castJSONVectortoArrayList(JSONArray v){

        ArrayList<Double> castedv = new ArrayList<>();

        for (int j = 0; j < v.length(); j++){
            castedv.add(v.getDouble(j));
        }

        return castedv;
    }

    // ================================================================================
    public static ArrayList<ArrayList<Double>> getMatrix(String type, String name) {

        checkExistence();

        try  (FileReader readFile = new FileReader(jsonfile)) {

            // Loads the data already in the json file
            JSONTokener tokener = new JSONTokener(readFile);
            JSONArray jsonArray;

            // If the file has data, load it
            if (tokener.more()) {

                jsonArray = new JSONArray(tokener);

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getString("type").equals(type) && jsonObject.getString("name").equals(name)){
                        return castJSONMatrixtoArrayList(jsonObject.getJSONArray("object"));
                    }
                }
                System.out.println("No matrix with name "+name+" has been defined");
                return zeros(1);

            } else {
                System.out.println("No matrix with name "+name+" has been defined");
            }

        } catch (JSONException | IOException e) {
            System.out.println("There was an error reading the data.");
            throw new RuntimeException(e);
        }
        return zeros(1);
    }

    // ================================================================================
    public static ArrayList<Double> getVector(String type, String name) {

        checkExistence();

        try  (FileReader readFile = new FileReader(jsonfile)) {

            // Loads the data already in the json file
            JSONTokener tokener = new JSONTokener(readFile);
            JSONArray jsonArray;

            // If the file has data, load it
            if (tokener.more()) {

                jsonArray = new JSONArray(tokener);

                for (int i = 0; i < jsonArray.length(); i++){
                    JSONObject jsonObject = jsonArray.getJSONObject(i);
                    if (jsonObject.getString("type").equals(type) && jsonObject.getString("name").equals(name)){
                        return castJSONVectortoArrayList(jsonObject.getJSONArray("object"));
                    }
                }
                System.out.println("No vector with name "+name+" has been defined");
                return zerosV(1);

            } else {
                System.out.println("No vector with name "+name+" has been defined");
            }

        } catch (JSONException | IOException e) {
            System.out.println("There was an error reading the data.");
            throw new RuntimeException(e);
        }
        return zerosV(1);
    }
}
