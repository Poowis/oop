/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import Engine.Data;
import Engine.Main;
import Engine.WorkSpace;
import Exception.FileException;
import InputAndOutput.MainIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Poowis
 */
public class GUI extends JFrame {

    Main main;

    JMenuBar mb;
    JMenu file, loadWS, data;
    JMenuItem newWS, importWS, exportWS, save, saveExit;
    JMenuItem importData, updateData;

    JPanel controlPn;
    JScrollPane scrollPane;
    JTable table;
    JTextField alert;
    JLabel control;

    public void init() {

//Load Main
        try {
            main = MainIO.loadMain();
        } catch (FileException | IOException | ClassNotFoundException ex) {
            main = new Main();
        }

//Menu Bar
        mb = new JMenuBar();

        file = new JMenu("File");
        newWS = new JMenuItem("New WorkSpace");
        loadWS = new JMenu("Load Recent WorkSpace");
        importWS = new JMenuItem("Import WorkSpace");
        exportWS = new JMenuItem("Export WorkSpace");
        save = new JMenuItem("Save");
        saveExit = new JMenuItem("Save and Exit");

        mb.add(file);
        file.add(newWS);
        file.add(loadWS);
        file.add(importWS);
        file.add(exportWS);
        file.add(new JSeparator());
        file.add(save);
        file.add(saveExit);

        data = new JMenu("Data");
        importData = new JMenuItem("Import Data");
        updateData = new JMenuItem("Refresh Data");
        mb.add(data);
        data.add(importData);
        data.add(updateData);

        setJMenuBar(mb);

//Table
        table = new JTable();
        scrollPane = new JScrollPane();
        scrollPane.setViewportView(table);
        table.setEnabled(false);
        add(scrollPane);

//Control Panel
        controlPn = new JPanel();
        controlPn.setLayout(new GridBagLayout());
        GridBagConstraints c = new GridBagConstraints();
//        add(controlPn, BorderLayout.EAST);
        add(new ControlPanel(), BorderLayout.EAST);

        alert = new JTextField(20);
        alert.setEditable(false);
        controlPn.add(alert);

//Set JFrame
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setTitle("Data");
        setSize(1000, 500);
        setLocationRelativeTo(null);
        setVisible(true);

//Add Action
        newWS.addActionListener((ActionEvent evt) -> {
            try {
                main.newWS();
            } catch (Exception ex) {
                alert.setText(ex.toString());
            }
        });
        importWS.addActionListener((ActionEvent evt) -> {
            try {
                String path = getPath("Import WorkSpace", "Import");
                if (path != null) {
                    main.importWS(path);
                }
            } catch (Exception ex) {
                alert.setText(ex.toString());
            }
        });
        exportWS.addActionListener((ActionEvent evt) -> {
            try {
                String path = getPath("Export WorkSpace", "Export");
                if (path != null) {
                    main.exportWS(path);
                }
            } catch (Exception ex) {
                alert.setText(ex.toString());
            }
        });
        save.addActionListener((ActionEvent evt) -> {
            try {
                MainIO.saveMain(main);
            } catch (Exception ex) {
                alert.setText(ex.toString());
            }
        });
        saveExit.addActionListener((ActionEvent evt) -> {
            try {
                MainIO.saveMain(main);
                System.exit(0);
            } catch (Exception ex) {
                alert.setText(ex.toString());
            }
        });
        importData.addActionListener((ActionEvent evt) -> {
            try {
                String path = getPath("Import Data", "Import");
                if (path != null) {
                    main.getCurrentWS().importData(path);
                    updateTable();
                }
            } catch (Exception ex) {
                alert.setText(ex.toString());
            }
        });
        updateData.addActionListener((ActionEvent evt) -> {
            try {
                main.getCurrentWS().update();
                updateTable();
            } catch (Exception ex) {
                alert.setText(ex.toString());
            }
        });
        
        
        updateRecentWS();

    }

    private void updateTable() {
        Data dt = main.getCurrentWS().getData();
        if (main.getCurrentWS() == null | dt == null) {
            return;
        }
        DefaultTableModel model = new DefaultTableModel();
        for (Object column : dt.getMetaInfo()) {
            model.addColumn(column);
        }
        for (int i = 0; i < dt.getDataSize().get(0); i++) {
            model.addRow(new Object[0]);
            for (int j = 0; j < dt.getDataSize().get(1); j++) {
                model.setValueAt(dt.get(i, j), i, j);
            }
        }
        table.setModel(model);
    }

    private void updateRecentWS() {
        loadWS.removeAll();
        for (Integer i =0 ; i<5; i++) {
            loadWS.add(new JMenuItem(i.toString()));
        }
//        for (WorkSpace ws : main.getRecentWS()) {
//            loadWS.add(new JMenuItem(ws.toString()));
//        }
    }

    private String getPath(String dialog, String button) {
        JFileChooser jf = new JFileChooser();
        jf.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        jf.setFileFilter(new FileNameExtensionFilter("WorkSpace", "ws"));
        jf.setDialogTitle(dialog);
        jf.setApproveButtonText(button);
        if (jf.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
            return jf.getSelectedFile().getAbsolutePath();
        }
        return null;
    }

    public static void main(String[] args) {
        GUI obj = new GUI();
        obj.init();
    }

}
