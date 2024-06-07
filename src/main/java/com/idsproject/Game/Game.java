package com.idsproject.Game;

import javax.swing.*;
import java.awt.*;
import com.idsproject.Events.Events;

import com.idsproject.Player.Player;
import com.idsproject.SaveFile.saveFile;

public class Game extends JFrame {

    //componenti grafici del frame
    protected JButton submit;
    public JTextField command;
    public JLabel log;
    public JLabel display;
    public JPanel bottomPanel;

    //variabile che tramite un intero sceglie che tipo di partita iniziare (nuova/ salvata precedentemente)
    public int mode;

    protected String name; //nome del giocatore
    protected Player player;//oggetto che definisce il giocatore

    public Game(String title, int mode) throws InterruptedException {

        super(title);
        this.mode = mode;

        //definizione dei componenti grafici
        submit = new JButton("Submit");
        command = new JTextField(32);
        command.setFont(new Font("Arial", Font.BOLD, 20));
        log = new JLabel("",SwingConstants.CENTER);
        display = new JLabel("", SwingConstants.CENTER);
        
        log.setOpaque(true);
        log.setFont(new Font("Serif", Font.BOLD, 30));
        log.setBackground(Color.BLACK);
        log.setForeground(Color.WHITE);

        display.setOpaque(true);
        display.setBackground(Color.BLACK);
        display.setForeground(Color.GREEN);

        //impostazione di posizionamento dei componenti sul frame
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;

        //label display per le immagini ascii
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 1;
        gbc.weighty = 0;
        add(display, gbc);

        //label log di gioco
        gbc.gridy = 1;
        gbc.weightx = 1;
        gbc.weighty = 1;
        add(log, gbc);


        bottomPanel = new JPanel();
        bottomPanel.setLayout(new FlowLayout());
        bottomPanel.add(command);
        bottomPanel.add(submit);
        bottomPanel.add(submit);
        bottomPanel.setBackground(Color.BLACK);

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weighty = 0;
        add(bottomPanel, gbc);

        //controllo sul tipo di partita da caricare
        if(this.mode == 0)
            newGame();
        if(this.mode == 1){
            log.setText("<html><center>"+"Resume"+"<br><br>submit to continue"+"<center><html>");
            load_game();
        }
    }

    //metodo per iniziare una nuova partita definendo il player con gli attributi di default
    public void newGame(){
        Events.GameIntro(log);
        this.submit.addActionListener(e -> {
            try {
                name = command.getText();
                player = new Player(name, 100, 0, 5);
                Events.initDungeon();
                Events.setDungeon(Events.dungeon);
                Events.gameLoop(player, log, display, command, bottomPanel);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }

    //metodo per caricare l'ultima partita salvata tramite la classe saveFile
    public void load_game() throws InterruptedException{
        this.submit.addActionListener(e -> {
            try {
                player = new Player("", 100, 0, 0);
                Events.initDungeon();
                saveFile.load(player, Events.dungeon);
                Events.gameLoop(player, log, display, command, bottomPanel);
            } catch (InterruptedException ex) {
                throw new RuntimeException(ex);
            }
        });
    }
}

