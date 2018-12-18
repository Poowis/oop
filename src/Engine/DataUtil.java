/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Exception.FileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poowis
 */
public class DataUtil {

    public static Data select(Data data, String sel) {
        ArrayList<ArrayList<Object>> ans = new ArrayList<>();
        ArrayList<ArrayList<Object>> cmd = new ArrayList<>();
        //output = row do with
        //do_0 ==
        //do_10 = regex
        //do_1 = >
        //do_2 = <
        //do_3 = >=
        //do_4 = <=
        for (String selects : sel.split("\n|,")) {
            String[] tmp = null;
            ArrayList<Object> temp = new ArrayList<>();
            if (selects.matches(".*(>=|=>).*")) {
                tmp = selects.split(">=|=>");
                temp.add(3);
            } else if (selects.matches(".*(<=|=<).*")) {
                tmp = selects.split("<=|=<");
                temp.add(4);
            } else if (selects.matches(".*==.*")) {
                tmp = selects.split("==");
                temp.add(0);
            } else if (selects.matches(".*=.*")) {
                tmp = selects.split("=");
                temp.add(10);
            } else if (selects.matches(".*>.*")) {
                tmp = selects.split(">");
                temp.add(1);
            } else if (selects.matches(".*<.*")) {
                tmp = selects.split("<");
                temp.add(2);
            } else {
                continue;
            }
            temp.add(tmp[1].strip());
            for (int i = 0; i < data.getDataSize().get(1); i++) {
                if (data.getMetaInfo(i).equalsIgnoreCase(tmp[0].strip())) {
                    temp.add(0, i);
                    cmd.add(temp);
                    break;
                }
            }
        }
        for (ArrayList<Object> row : data) {
            boolean check = true;
            for (ArrayList<Object> cm : cmd) {
                int d = ((int) cm.get(1));
                if (row.get((int) cm.get(0)) instanceof String) {
                    String val = (String) row.get((int) cm.get(0));
                    String with = (String) cm.get(2);
                    switch (d) {
                        case 10:
                            if (!(val.toLowerCase()).matches(with.toLowerCase())) {
                                check = false;
                            }
                            break;
                        case 0:
                            if (!(val.equalsIgnoreCase(with))) {
                                check = false;
                            }
                            break;
                        case 1:
                            if ((val).compareToIgnoreCase(with) <= 0) {
                                check = false;
                            }
                            break;
                        case 2:
                            if (val.compareToIgnoreCase(with) >= 0) {
                                check = false;
                            }
                            break;
                        case 3:
                            if (val.compareToIgnoreCase(with) < 0) {
                                check = false;
                            }
                            break;
                        case 4:
                            if (val.compareToIgnoreCase(with) > 0) {
                                check = false;
                            }
                            break;
                    }

                } else if (row.get((int) cm.get(0)) instanceof Double) {
                    Double val = (Double) row.get((int) cm.get(0));
                    try {
                        Double with = Double.parseDouble((String) cm.get(2));
                        switch (d) {
                            case 10:
                            case 0:
                                if (!(val.equals(with))) {
                                    check = false;
                                }
                                break;
                            case 1:
                                if (val <= with) {
                                    check = false;
                                }
                                break;
                            case 2:
                                if (val >= with) {
                                    check = false;
                                }
                                break;
                            case 3:
                                if (val < with) {
                                    check = false;
                                }
                                break;
                            case 4:
                                if (val > with) {
                                    check = false;
                                }
                                break;
                        }
                    } catch (Exception ex) {
                        check = false;
                        continue;
                    }

                }
            }
            if (check) {
                ans.add(row);
            }
        }
        return new Data(data.getMetaInfo(), ans);
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        try {
            Data data = new Data("C:\\Users\\Poowis\\Desktop\\sales.csv");
            System.out.println(data.getMetaInfo());
            for (ArrayList<Object> row : data) {
                System.out.println(row);
            }
            System.out.println(123);
            for (ArrayList<Object> row : select(data, "price = 3600,Payment_Type = visa")) {
                System.out.println(row);
            }

        } catch (IOException | FileException ex) {
            Logger.getLogger(DataUtil.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
