package fr.dev.weather;

import javax.swing.*;
import java.awt.*;

public class Application {

    public static void main(String[] args) {

        EventQueue.invokeLater(() -> {
                MainFrame mainFrame = new MainFrame("city weather");
                mainFrame.setResizable(false);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.pack();
                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
        });

    }

}
