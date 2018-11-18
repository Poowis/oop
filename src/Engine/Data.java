/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Exception.FileException;
import InputAndOutput.DataInput;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 *
 * @author Poowis
 */
public class Data implements Iterable<ArrayList> {

    private ArrayList<String> metaInfo;
    private ArrayList<Integer> dataSize;
    private ArrayList<ArrayList<Object>> data;

    public Data() {
    }

    public Data(String path) throws IOException, FileNotFoundException, FileException {
        setData(path);
    }

    public final void setData(String path) throws IOException, FileNotFoundException, FileException {
        ArrayList<ArrayList<Object>> tmp = DataInput.getData(path);
        metaInfo = new ArrayList<>();
        for (Object value : tmp.get(0)) {
            String s = (String) value;
            metaInfo.add(s);
        }
        tmp.remove(0);
        data = tmp;
        dataSize = new ArrayList<>(2);
        dataSize.add(tmp.size());
        dataSize.add(metaInfo.size());

    }

    public ArrayList<String> getMetaInfo() {
        return metaInfo;
    }

    public String getMetaInfo(int index) {
        return metaInfo.get(index);
    }

    public ArrayList<Integer> getDataSize() {
        return dataSize;
    }

    public ArrayList<ArrayList<Object>> getAll() {
        return data;
    }

    public ArrayList<Object> get(int index) {
        return data.get(index);
    }

    public Object get(int indexO, int indexI) {
        return data.get(indexO).get(indexI);
    }

    @Override
    public Iterator iterator() {
        return data.iterator();
    }

}