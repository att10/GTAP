package org.gtap;

import java.awt.BorderLayout;

import javax.swing.JFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JPanel;

public class App {

    /**
     *
     */
    private static final long serialVersionUID = 1L;

    JFrame appFrame;

    public App() {

        appFrame = new JFrame();

        JPanel menuPanel = new JPanel(new BorderLayout());
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("File");
        JMenu menu1 = new JMenu("Edit");
        GCanvas canvas = new GCanvas();

        menuBar.add(menu);
        menuBar.add(menu1);
        menuPanel.add(menuBar, BorderLayout.NORTH);
        menuPanel.add(canvas, BorderLayout.CENTER);
        appFrame.add(menuPanel);

        appFrame.setSize(1080, 720);
        appFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        appFrame.setTitle("GTAP");
        appFrame.setVisible(true);

    }

    public static void main(String arg[]) {

        new App();
    }

}