package com.idsproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.idsproject.Game.Game;

public class Main {

    public static void main(String[] args) {

        //Frame principale del menu
        JFrame main = new JFrame("Dungeon");
        main.setSize(875, 500);
        main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        main.setLocationRelativeTo(null);
        main.setResizable(false);
        main.setLayout(new BorderLayout());

        //Label per il background
        JLabel background = new JLabel();
        background.setIcon(new ImageIcon("..\\SoftwareEngineeringProject-main\\src\\main\\java\\com\\idsproject\\background.jpg"));
        background.setLayout(new GridBagLayout());

        //Label del titolo
        JPanel panel = new JPanel();
        panel.setOpaque(false);  // Make panel transparent
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS)); 

        JLabel title = new JLabel("Dungeon");
        title.setFont(new Font("Serif", Font.BOLD, 70));
        title.setForeground(Color.ORANGE);
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        //Jbutton per interagire con il menu, new game
        JButton startGame = new JButton("New Game");

        startGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Game game = null;
                try {
                    game = new Game("Dungeon", 0);
                } catch (InterruptedException ex) {
                    throw new RuntimeException(ex);
                }
                game.setBackground(Color.BLACK);
                game.setSize(1280, 800);
                game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                game.setLocationRelativeTo(null);
                main.setVisible(false);
                game.setVisible(true);
            }
        });

        //Jbutton per interagire con il menu, load game
        JButton loadGame = new JButton("Load Game");

        loadGame.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Game game = null;
                    try {
                        game = new Game("Dungeon", 1);
                    } catch (InterruptedException ex) {
                        throw new RuntimeException(ex);
                    }
                    game.setBackground(Color.BLACK);
                    game.setSize(1280, 800);
                    game.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                    game.setLocationRelativeTo(null);
                    main.setVisible(false);
                    game.setVisible(true);

                }
        });

        //Allineamento dei componenti grafici
        startGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        loadGame.setAlignmentX(Component.CENTER_ALIGNMENT);
        panel.add(Box.createRigidArea(new Dimension(0, 20 )));  // Spacer
        panel.add(title);
        panel.add(Box.createRigidArea(new Dimension(0, 20)));  // Spacer
        panel.add(startGame);
        panel.add(Box.createRigidArea(new Dimension(0, 10)));  // Spacer
        panel.add(loadGame);

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        background.add(panel, gbc);  // Center the panel
        main.add(background, BorderLayout.CENTER);
        main.setVisible(true);

    }
}
