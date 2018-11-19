/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package InputAndOutput;

import Exception.FileException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 *
 * @author Poowis
 */
public class DataIO {

    public DataIO() {

    }

    public static ArrayList<ArrayList<Object>> getData(String path) throws FileNotFoundException, IOException, FileException {
        File file = new File(path);
        ArrayList<ArrayList<Object>> data = new ArrayList<>();
        if (file.exists()) {
            try (BufferedReader input = new BufferedReader(new FileReader(file));) {
                String line;
                line = input.readLine();
                if (line == null) {
                    throw new FileException("File is Empty");
                }
                ArrayList<Object> meta = new ArrayList<>();
                for (String value : line.split(",")) {
                        value = ((String) value).strip();
                        value = ((String) value).replaceAll("^\"|\"$", "");
                        meta.add(value);
                }
                data.add(meta);
                while ((line = input.readLine()) != null) {
                    ArrayList<Object> row = new ArrayList<>();
                    for (Object value : line.split(",")) {
                        try {
                            value = Double.parseDouble((String) value);
                            row.add(value);
                        } catch (NumberFormatException ex) {
                            value = ((String) value).strip();
                            value = ((String) value).replaceAll("^\"|\"$", "");
                            row.add(value);
                        }
                    }
                    data.add(row);
                }
            }
        } else {
            throw new FileException("File does not exist");
        }
        return data;
    }

}
