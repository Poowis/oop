/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Exception.FileException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author Poowis
 */
public class Main implements Serializable {

    private WorkSpace currentWS;
    private ArrayList<WorkSpace> recentWS;
    private final int MAX_WS = 5;

    public Main() {
        recentWS = new ArrayList<>(MAX_WS);
    }
    
    public void importWS(String path) throws IOException, FileNotFoundException, FileException {
        changeRecentWS(currentWS);
        currentWS = new WorkSpace(path);
    }

    private void changeRecentWS(WorkSpace ws) {
        if (recentWS.size() == MAX_WS) {
            recentWS.remove(0);
        }
        recentWS.add(ws);
    }

}
