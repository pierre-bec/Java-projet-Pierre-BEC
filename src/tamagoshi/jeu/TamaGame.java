package tamagoshi.jeu;


import layout.TamaPanel;
import tamagoshi.tamagoshi.GrosJoueur;
import tamagoshi.tamagoshi.GrosMangeur;
import tamagoshi.tamagoshi.Tamagoshi;

import javax.swing.*;
import java.awt.*;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Properties;
import java.util.Random;

public class TamaGame extends JFrame {
    private ArrayList<Tamagoshi> tamagoshiDepart;
    private ArrayList<Tamagoshi> tamagoshiEnCourse;
    private final Random random = new Random();
    protected ArrayList<TamaPanel> panels = new ArrayList<TamaPanel>();
    protected JTextArea console;
    protected int tour = 0;
    private int nbActions;

    public TamaGame() {
        this.initialisation();
    }

    /**
     * Initialise l'ensemble des variables nécessaire au début de
     * la partie, les tamagoshis ainsi que leue type et les différentes fenêtres.
     */
    private void initialisation() {
        this.tamagoshiDepart = new ArrayList<Tamagoshi>();
        this.tamagoshiEnCourse = new ArrayList<Tamagoshi>();
        int nbTamagoshi = 0;
        while (nbTamagoshi <= 0 || nbTamagoshi > 8) {
            try {
                nbTamagoshi = Integer.parseInt(JOptionPane.showInputDialog("Entrez le nombre de tamagoshis désiré (Max : 8) :", "3"));
            } catch (NumberFormatException var11) {
                JOptionPane.showMessageDialog((Component) null, "Entrez un nombre svp (Max : 8)", "Erreur de saisie", 0);
            }
        }
        for (int i = 1; i < nbTamagoshi + 1; i++) {
            System.out.println("Nom du Tamagohi n°" + i + " : Tamagoshi" + i);
            String name = "Tamagoshi" + i;
            Tamagoshi t;
            if (random.nextBoolean()) {
                t = new GrosJoueur(name);
            } else {
                t = new GrosMangeur(name);
            }
            this.tamagoshiDepart.add(t);
            this.tamagoshiEnCourse.add(t);

        }
        this.console = new JTextArea();
        this.setSize(250, 400);
        setLocationRelativeTo(null);
        this.add(new JScrollPane(console));

    }

    /**
     * @param informations données à afficher dans la console
     * Affiche les informations transimsent en paramètre dans la console
     */
    private void afficherInfos(String informations) {
        this.console.append(informations + "\n");
        this.console.setCaretPosition(this.console.getDocument().getLength());
    }

    /**
     * Lance une nouvelle partie
     */
    public void play() {
        int nbTour = 0;

        this.setVisible(true);
        afficherInfos("------------------Nouvelle Game------------------");
        int x = 0;
        int y = 0;
        for (int i = 0; i < tamagoshiDepart.size(); i++) {
            TamaPanel tp = new TamaPanel(tamagoshiDepart.get(i), x, y);
            tp.setTamaGame(this);
            tamagoshiDepart.get(i).setTamaPanel(tp);
            this.panels.add(tp);
            System.out.println(Toolkit.getDefaultToolkit().getScreenSize().width);
            System.out.println(i * tp.getSize().width + tp.getSize().width);
            if (x > Toolkit.getDefaultToolkit().getScreenSize().width-2*(tp.getWidth()+30)) {
                y += tp.getSize().height + 30;
                x = 0;
            } else {
                x += tp.getSize().width + 30;
        }
            tamagoshiDepart.get(i).parle();
            afficherInfos(tp.getEtat());
        }

    }

    /**
     * Fonction exécuté à chaque fois que l'on clique sur le bouton nourrir ou jouer
     */
    public void nouveauTour() {
        nbActions++;
        if (nbActions == 2) {
            tour++;
            afficherInfos("------------------Tour n°" + tour + "------------------");
            nbActions = 0;
            ArrayList<TamaPanel> dead = new ArrayList<TamaPanel>();
            for (TamaPanel tp : this.panels
            ) {
                if (tp.getMonTamagoshi().consommeEnergie() && !Tamagoshi.atteintAgeLimite(tp.getMonTamagoshi())) {
                    afficherInfos(tp.getEtat());
                    tp.disableNourrir(true);
                    tp.disableJouer(true);
                } else {
                    System.out.println(tp.getMonTamagoshi().toString());
                    tp.setEtatlabel("Je suis KO !");
                    afficherInfos(tp.getMonTamagoshi().getName() + " : Je suis KO");
                    tp.setResponseLabel("");
                    tp.disableJouer(false);
                    tp.disableNourrir(false);
                    tamagoshiEnCourse.remove(tp.getMonTamagoshi());
                    dead.add(tp);
                }

            }
            panels.removeAll(dead);
        }
        if (tamagoshiEnCourse.size()==0) {
            resultat();
        }
    }

    /**
     * @param btn type de bouton à activer ou désactiver (nourrir ou jouer)
     * @param enable Etat dans lequel mettre le type de bouton (activé/désactivé)
     */
    public void setButton(JButton btn, boolean enable) {
        for (TamaPanel tp : this.panels
        ) {
            if (btn.getText().equals("Nourrir")) {
                tp.disableNourrir(false);
            } else {
                tp.disableJouer(false);
            }
        }

    }

    private void listeChoixTamagochi() {
        for (int i = 0; i < this.tamagoshiEnCourse.size(); i++) {
            System.out.print(this.tamagoshiEnCourse.get(i).getName() + "(" + i + ")     ");
        }
        System.out.println("\n");
    }

    /**
     * Calcul le score de fin de partie
     * @return un double
     */
    private double score() {
        double somme = 0;
        for (Tamagoshi t : this.tamagoshiDepart
        ) {
            somme += t.getAge();
        }
        return somme / (Tamagoshi.lifeTime * this.tamagoshiDepart.size());
    }

    /**
     * Affiche toutes les infos de fin de partie dans la console
     */
    private void resultat() {
        this.setSize(750, 400);
        System.out.println("\n------Bilan------");
        for (Tamagoshi t : this.tamagoshiDepart
        ) {
            if (t.getAge() == Tamagoshi.lifeTime) {
                System.out.println(t.getName() + " qui était un " + Tamagoshi.class.getName() + " à bien survécu et vous remercie.");
                afficherInfos(t.getName() + " qui était un " + Tamagoshi.class.getName() + " à bien survécu et vous remercie.");
            } else {
                System.out.println(t.getName() + " qui était un " + t.getClass().getSimpleName() + " est mort dans d'horribles souffrances");
                afficherInfos(t.getName() + " qui était un " + t.getClass().getSimpleName() + " est mort dans d'horribles souffrances");
            }

        }
        sauvegardeScore(this.score() * 100);
        System.out.println("\nVous avez un score de " + this.score() * 100 + "%");
        afficherInfos("\nVous avez un score de " + this.score() * 100 + "%");
    }

    public static void Start() {
        TamaGame jeu = new TamaGame();
        jeu.play();
    }

    /**
     *
     * @param score score de fin de partie
     * Confront le nouveau score a ceux enregistrés pour voir si il rentre dans le top 3 des meilleurs scores
     */
    public void sauvegardeScore(double score) {
        String propertiesFileLocation = "tamagochi.properties";
        Properties myProps = new Properties();

        try {
            InputStream in = new FileInputStream(propertiesFileLocation);
            myProps.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        System.out.println(myProps.stringPropertyNames());

        Double[] bestScores = new Double[4];

        for (int i=0; i<3; i++) {
            bestScores[i] = Double.parseDouble(myProps.getProperty("score"+(i+1)));
        }

        bestScores[3] = score;


      Arrays.sort(bestScores);

        System.out.println(Arrays.toString(bestScores));

        for (int i=3; i!=0; i--) {
           myProps.setProperty("score"+i, bestScores[i]+"");
        }

        try {
            OutputStream out = new FileOutputStream(propertiesFileLocation);
            myProps.store(out, "Un fichier properties");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
