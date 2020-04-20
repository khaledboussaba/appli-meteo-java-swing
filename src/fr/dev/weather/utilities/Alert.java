package fr.dev.weather.utilities;

import javax.swing.*;
import java.awt.*;

public class Alert {

    public static void error(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void error(Component parentComponent, String message) {
        error(parentComponent, "Error", message);
    }

    public static void info(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.INFORMATION_MESSAGE);
    }

    public static void info(Component parentComponent, String message) {
        info(parentComponent, "Information", message);
    }

}
