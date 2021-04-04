package tamagoshi.tamagoshi;

public class GrosMangeur extends Tamagoshi{
    public GrosMangeur(String name) {
        super(name);
    }

    @Override
    public boolean consommeEnergie() {
        EnergyMoins1();
        EnergyMoins1();
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
