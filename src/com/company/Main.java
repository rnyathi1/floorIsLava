package com.company;

import javax.swing.*;
import javax.swing.JFrame;
public class Main {
    public Main(){

    }
    public static void main(String[] args) {
        JFrame window = new JFrame("Floor is Lava");
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Ryan's 2D Game");

        GamePanel gamePanel = new GamePanel();
        window.add(gamePanel);
        window.pack();
        window.setLocationRelativeTo(null);
        window.setVisible(true);

        gamePanel.startGameThread();
        }
}
