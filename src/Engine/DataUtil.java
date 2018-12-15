/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import java.util.ArrayList;

/**
 *
 * @author Poowis
 */
public class DataUtil {

    public static Data select(Data data, int column, String select) {
        ArrayList<ArrayList<Object>> ans = new ArrayList<>();

        for (ArrayList<Object> row : data) {
            if (row.get(column) instanceof String) {
                String tmp = (String) row.get(column);
                if (tmp.matches(select)) {
                    ans.add(row);
                }
            }
        }
        return new Data(data.getMetaInfo(), ans);
    }

    public static Data select(Data data, String column, String select) {
        int index = -1;
        for (int i = 0; i < data.getMetaInfo().size(); i++) {
            if (data.getMetaInfo(i).equals(column)) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            return null;
        }
        return select(data, index, select);
    }

    public static Data select(Data data, int column, Double select) {
        ArrayList<ArrayList<Object>> ans = new ArrayList<>();

        for (ArrayList<Object> row : data) {
            if (row.get(column) instanceof Double) {
                Double tmp = (Double) row.get(column);
                if (tmp.equals(select)) {
                    ans.add(row);
                }
            }
        }
        return new Data(data.getMetaInfo(), ans);
    }

    public static Data select(Data data, String column, Double select) {
        int index = -1;
        for (int i = 0; i < data.getMetaInfo().size(); i++) {
            if (data.getMetaInfo(i).equals(column)) {
                index = i;
                break;
            }
        }
        if (index < 0) {
            return null;
        }
        return select(data, index, select);
    }

    public static Data select(Data data, int column, String select, boolean convert) {
        ArrayList<ArrayList<Object>> ans = new ArrayList<>();

        if (select.matches(" *<=.")) {
            Double tmp = Double.parseDouble(select.replace("<=", ""));
            for (ArrayList<Object> row : data) {
                if (row.get(column) instanceof String) {
                    if (((Double) row.get(column)) <= tmp) {
                        ans.add(row);
                    }
                }
            }
        } else if (select.matches(" *>=.")) {

        } else if (select.matches(" *<.")) {

        } else if (select.matches(" *>.")) {

        } else if (select.matches(" *(=|==).")) {

        } else if (select.matches(".-.")) {

        } else {
        
        }

        for (ArrayList<Object> row : data) {
            if (row.get(column) instanceof String) {
                if (((String) row.get(column)).matches(select)) {
                    ans.add(row);
                }
            } else if (row.get(column) instanceof Double) {

            }
        }
        return new Data(data.getMetaInfo(), ans);
    }

}
