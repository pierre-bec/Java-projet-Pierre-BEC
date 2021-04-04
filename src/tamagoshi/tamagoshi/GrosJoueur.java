package tamagoshi.tamagoshi;

public class GrosJoueur extends Tamagoshi {
    public GrosJoueur(String name) {
        super(name);
    }

    @Override
    public boolean consommeEnergie() {
        EnergyMoins1();
        FunMoins1();
        FunMoins1();
        AgePlus1();
        if (getEnergy()<=0) {
            System.out.println(getName()+" : Je suis KO");
            return false;
        } else {
            return true;
        }
    }
}
