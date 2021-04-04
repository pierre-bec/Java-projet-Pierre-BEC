package layout;


import tamagoshi.jeu.TamaGame;
import tamagoshi.tamagoshi.Tamagoshi;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TamaPanel extends JFrame implements ActionListener {
    private JPanel infos;
    private JPanel actions;
    private JLabel etatlabel;
    private JLabel responseLabel;
    private JButton nourrir;
    private JButton jouer;
    private Tamagoshi monTamagoshi;
    private TamaGame tamaGame;

    public TamaPanel(Tamagoshi t, int x , int y) {
        this.monTamagoshi = t;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle(t.getName());
        this.setSize(260, 300);
        setLocation(x,y);
        setLayout(new GridBagLayout());

        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.VERTICAL;


        this.infos = new JPanel(new GridBagLayout());
        this.actions = new JPanel(new GridBagLayout());

        this.etatlabel = new JLabel("");
        this.responseLabel = new JLabel("");

        this.nourrir = new JButton("Nourrir");
        this.nourrir.addActionListener(this);
        this.jouer = new JButton("Jouer");
        this.jouer.addActionListener(this);
        infos.add(etatlabel, gbc);
        infos.add(responseLabel, gbc);
        actions.add(nourrir);
        actions.add(jouer);

        gbc.weighty = 1;
        add(infos, gbc);
        add(actions, gbc);
        this.setVisible(true);


    }

    public void setEtatlabel(String etatlabel) {
        this.etatlabel.setText(etatlabel);
    }

    public void setResponseLabel(String responseLabel) {
        this.responseLabel.setText(responseLabel);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource()==this.nourrir) {
            this.monTamagoshi.mange();
        } else {
            this.monTamagoshi.fun();
        }
        this.tamaGame.setButton((JButton) e.getSource(), false);
        this.tamaGame.nouveauTour();
    }

    public void disableNourrir(boolean b) {
        this.nourrir.setEnabled(b);
    }

    public void disableJouer(boolean b) {
        this.jouer.setEnabled(b);
    }

    public void setTamaGame(TamaGame tamaGame) {
        this.tamaGame = tamaGame;
    }

    public Tamagoshi getMonTamagoshi() {
        return monTamagoshi;
    }

    public String getEtat() {
        return etatlabel.getText();
    }
}

