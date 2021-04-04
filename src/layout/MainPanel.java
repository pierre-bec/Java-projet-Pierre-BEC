package layout;

import tamagoshi.jeu.TamaGame;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainPanel extends JFrame {

    public static void main(String[] args) {
        new MainPanel().Startmenu();
    }

    public void Startmenu() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("Test");
        add(displayMenu());
        pack();
        setLocationRelativeTo(null);
        setVisible(true);
    }

    public JPanel displayMenu() {

        JPanel MenuPan = new JPanel();
        MenuPan.setBorder(new EmptyBorder(10, 10, 10, 10));
        MenuPan.setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        MenuPan.add(new JLabel("<html><h1><strong><i>Jeux Tamagoshi</i></strong></h1><hr></html>"), gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        JButton nouvellePartie = new JButton("Nouvelle Partie");
        buttons.add(nouvellePartie, gbc);
        JButton scoresBtn = new JButton("Voir les scores");
        buttons.add(scoresBtn, gbc);
        JButton optionsBtn = new JButton("Options");
        buttons.add(optionsBtn, gbc);
        JButton helpBtn = new JButton("Help");
        buttons.add(helpBtn, gbc);
        JButton exitBtn = new JButton("Exit");
        buttons.add(exitBtn, gbc);

        nouvellePartie.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.out.println("testBtn");
                setVisible(false);
                TamaGame.Start();

            }
        });

        helpBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new HelpPanel("Ceci est le texte d'explication du jeux");

            }
        });

        exitBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        scoresBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new BestScorePanel();
            }
        });

        gbc.weighty = 1;
        MenuPan.add(buttons, gbc);
        return MenuPan;
    }


}