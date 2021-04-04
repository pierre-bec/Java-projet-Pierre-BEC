package layout;

import javax.swing.*;
import java.awt.*;
import java.util.jar.JarFile;

public class HelpPanel extends JFrame {
    HelpPanel(String HelpTxt) {
        setTitle("Aide");
        setBounds(500, 500, 450, 250);
        JPanel panHaut = new JPanel();
        JPanel panBas = new JPanel();
        panHaut.setLayout(new BorderLayout());
        panBas.setLayout(new FlowLayout());

        JTextArea jtaSortie = new JTextArea(HelpTxt,10,5);


        Container contentPane = getContentPane();
        contentPane.setLayout(new BorderLayout());

        panHaut.add(jtaSortie);

        contentPane.add(panHaut,"North");
        contentPane.add(panBas, "South");


        this.setVisible(true);
    }
}
