/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package jtabbedpane;

import com.google.common.hash.Hashing;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonIOException;
import com.google.gson.JsonSyntaxException;
import java.awt.Component;
import java.io.File;
import java.io.IOException;
import java.io.Reader;
import java.io.Writer;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Supplier;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author mxtur
 */
public class FileService {

    public static String originalConfigHash;
    public File currentFile;
    private JFileChooser fileChooser;
    private Component parent;
    private static final Logger logger = Logger.getLogger(FileService.class.getName());

    public FileService(Component parent) {

        this.parent = parent;

        fileChooser = new JFileChooser(".");
        
        fileChooser.setFileFilter(new FileFilter() {
            @Override
            public boolean accept(File f) {
                return f.getName().endsWith("json") || f.isDirectory();
            }

            @Override
            public String getDescription() {
                return "Only JSON";
            }
        });

    }

    public void saveFile(List<Configurations> conflist) {
        if (this.currentFile == null) {
            saveFileAs(conflist);
        } else {
            File file = new File(this.currentFile.toString());
            Gson gson = new GsonBuilder().registerTypeAdapter(Configurations.class, new ConfigurationsTypeAdapter()).create();

            Writer writer = null;
            try {
                writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath(), new String[0]),
                        new java.nio.file.OpenOption[0]);
            } catch (IOException ex) {

                logger.log(Level.SEVERE, (Supplier<String>) ex);

                return;
            }
            gson.toJson(conflist, writer);

            try {
                writer.close();
            } catch (IOException ex) {

                logger.log(Level.SEVERE, (Supplier<String>) ex);
            }

            originalConfigHash = calculateHash(conflist);
        }
    }

    public void saveFileAs(List<Configurations> conflist) {

        int option = fileChooser.showSaveDialog(parent);
        if (option == 0) {
            File file;
            if (fileChooser.getSelectedFile().getAbsolutePath().endsWith("json")) {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath());
            } else {
                file = new File(fileChooser.getSelectedFile().getAbsolutePath() + ".json");
            }

            try {
                file.createNewFile();
            } catch (IOException ex) {

                logger.log(Level.SEVERE, (Supplier<String>) ex);
            }

            Gson gson = new GsonBuilder().registerTypeAdapter(Configurations.class, new ConfigurationsTypeAdapter()).create();

            Writer writer = null;
            try {
                writer = Files.newBufferedWriter(Paths.get(file.getAbsolutePath(), new String[0]),
                        new java.nio.file.OpenOption[0]);
            } catch (IOException ex) {

                logger.log(Level.SEVERE, (Supplier<String>) ex);
            }

            gson.toJson(conflist, writer);

            try {
                writer.close();
            } catch (IOException ex) {

                logger.log(Level.SEVERE, (Supplier<String>) ex);
            }

            originalConfigHash = calculateHash(conflist);

            currentFile = fileChooser.getSelectedFile();
        }
    }

    public List<Configurations> openFile(List<Configurations> conflist) {
        int option = fileChooser.showOpenDialog(parent);
        if (option == 0) {
            File file = fileChooser.getSelectedFile();

            try {
                Gson gson = new GsonBuilder().registerTypeAdapter(Configurations.class, new ConfigurationsTypeAdapter()).create();
                try (Reader reader = Files.newBufferedReader(file.toPath())) {
                    conflist = new ArrayList<>(
                            Arrays.asList((Configurations[]) gson.fromJson(reader, Configurations[].class)));

                    originalConfigHash = calculateHash(conflist);
                }

            } catch (JsonIOException | JsonSyntaxException | IOException ex) {
                Logger.getLogger(SystemPanel.class.getName()).log(Level.SEVERE, (String) null, ex);
            }
        }

        currentFile = fileChooser.getSelectedFile();

        return conflist;
    }

    public String calculateHash(List<Configurations> configs) {
        return Hashing.sha256()
                .hashString(configs.toString(), StandardCharsets.UTF_8)
                .toString();
    }
}
