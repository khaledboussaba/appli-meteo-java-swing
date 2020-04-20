package fr.dev.weather;

import javax.swing.JFrame;
import java.awt.EventQueue;

public class Application {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                MainFrame mainFrame = new MainFrame("Weather of the day");
                mainFrame.setResizable(false);
                mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                mainFrame.pack();
//                mainFrame.setLocationRelativeTo(null);
                mainFrame.setVisible(true);
            }
        });

    }

}
