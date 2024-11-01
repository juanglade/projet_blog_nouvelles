/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package tools;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 *
 * @author Julien Anglade
 */
public class Utils {

    private static final String CONFIG_FILE = "db.properties";

    /**
     * Charge et retourne les propriétés contenues dans le fichier de
     * configuration.
     *
     * @return La configuration du projet
     * @throws RuntimeException
     */
    public static Properties loadConfig() throws RuntimeException {
        Properties config = new Properties();
        try (InputStream input = Thread.currentThread().getContextClassLoader().getResourceAsStream(CONFIG_FILE)) {
            config.load(input);
        } catch (IOException ex) {
            throw new RuntimeException("Impossible de lire le fichier de configuration.");
        }
        return config;
    }

}
