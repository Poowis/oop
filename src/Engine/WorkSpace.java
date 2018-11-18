/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Engine;

import Exception.FileException;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 *
 * @author Poowis
 */
public class WorkSpace {

    private Data data;

    public void importData(String path) throws IOException, FileNotFoundException, FileException {
        if (data != null) {
            data = new Data(path);
        } else {
            Data tmp = new Data(path);
            if
        
        }
    }

}
