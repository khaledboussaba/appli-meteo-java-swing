package fr.dev.weather.utilities;

import javax.swing.*;
import java.awt.*;

public class Alert {

    public static void error(Component parentComponent, String title, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, title, JOptionPane.ERROR_MESSAGE);
    }

    public static void error(Component parentComponent, String message) {
        JOptionPane.showMessageDialog(parentComponent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }

}
