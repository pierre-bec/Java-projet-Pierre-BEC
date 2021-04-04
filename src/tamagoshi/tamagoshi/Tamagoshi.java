package tamagoshi.tamagoshi;

import layout.TamaPanel;

import java.util.Random;

public class Tamagoshi {
    private int age;
    private final int maxEnergy;
    private int energy;
    private final int maxFun;
    private int fun;
    private final String name;
    public static int lifeTime = 10;
    private final Random random;
    private TamaPanel tamaPanel;

    public Tamagoshi(String name) {
        this.name = name;
        this.age = 0;
        this.random = new Random();
        this.maxEnergy = random.nextInt(4)+5;
        this.energy = random.nextInt(4)+3;
        this.maxFun = random.nextInt(4)+5;
        this.fun = random.nextInt(4)+3;
    }

    public boolean parle() {
        String response = "";
        boolean result;
        if(this.energy>4 & this.fun>4) {
            response = (this.name + " : Tout est OK la mif !");
            result = true;
        }
        else if(this.energy<=4 && this.fun>4){
            response = (this.name + " : Je suis affamé");
            result =  false;
        } else if(this.energy>4) {
            response = (this.name + " : J'veux jouer à fortnite !");
            result = false;
        } else {
            response = (this.name + " : Est-ce que la vie vaut vraiement la peine d'être vécu ?");
            result = false;
        }
        System.out.println(response);
        tamaPanel.setEtatlabel(response);
        return result;
    }

    public boolean mange() {
        String response = "";
        boolean result;
        if (this.energy<this.maxEnergy) {
            this.energy += random.nextInt(2)+1;
            response = ("Je suis content j'ai bien mangé");
            result =  true;
        } else {
            response = ("J'ai pas faim batard");
            result =  false;
        }
        System.out.println(response);
        tamaPanel.setResponseLabel(response);
        return result;
    }

    public boolean fun() {
        String response = "";
        boolean result;
        if (this.fun<this.maxFun) {
            this.fun += random.nextInt(2)+1;
            response = ("Qu'est-ce qu'on rigole");
            result =  true;
        } else {
            response = ("Alors la j'en ai vu des mecs chiants mais toi");
            result =  false;
        }
        System.out.println(response);
        tamaPanel.setResponseLabel(response);
        return result;
    }


    public boolean consommeEnergie() {
        this.energy--;
        this.fun--;
        this.age++;
        if (this.energy<=0) {
            System.out.println(this.name+" : Je suis KO");
            return false;
        } else {
            return true;
        }
    }

    @Override
    public String toString() {
        return "\nTamagoshi{" +
                "age=" + age +
                ", maxEnergy=" + maxEnergy +
                ", energy=" + energy +
                ", maxFun=" + maxFun +
                ", fun=" + fun +
                ", name='" + name + '\'' +
                ", random=" + random +
                '}';
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public static boolean atteintAgeLimite(Tamagoshi t) {
        if (t.age >= Tamagoshi.lifeTime) {
            System.out.println("Je suis vieux, adieu.\n");
            return true;
        } else {
            return false;
        }


    }

    public static void main(String[] args) {
        Tamagoshi t1 = new Tamagoshi("Clément");
        System.out.println(t1);
    }

    public void AgePlus1() {
        this.age++;
    }

    public void EnergyMoins1() {
        this.energy--;
    }

    public void FunMoins1() {
        this.fun--;
    }

    public int getEnergy() {
        return energy;
    }

    public void setTamaPanel(TamaPanel tamaPanel) {
        this.tamaPanel = tamaPanel;
    }

    public TamaPanel getTamaPanel() {
        return tamaPanel;
    }
}
