package layout;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class BestScorePanel extends JFrame{

    BestScorePanel() {
            setTitle("Aide");
            setBounds(500, 500, 450, 250);
            JPanel panHaut = new JPanel();
            JPanel panBas = new JPanel();
            panHaut.setLayout(new BorderLayout());
            panBas.setLayout(new FlowLayout());


        String propertiesFileLocation = "tamagochi.properties";
        Properties myProps = new Properties();

        try {
            InputStream in = new FileInputStream(propertiesFileLocation);
            myProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        StringBuilder bestScores = new StringBuilder();

        for (int i=3; i!=0; i--) {
            bestScores.append("Score n°").append(-1*(i-4)).append(" : ").append(myProps.getProperty("score" + i)).append("% \n");
        }

            JTextArea jtaSortie = new JTextArea(bestScores.toString(),10,5);


            Container contentPane = getContentPane();
            contentPane.setLayout(new BorderLayout());

            panHaut.add(jtaSortie);

            contentPane.add(panHaut,"North");
            contentPane.add(panBas, "South");


            this.setVisible(true);
        }
    }


