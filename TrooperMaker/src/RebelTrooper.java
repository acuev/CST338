/*
Title: RebelTrooper.java
Date: 04/15/2021
 */
public class RebelTrooper extends Trooper{
    private String name = "Rebel";
    private static int soldierCounter = 0;

    RebelTrooper(String unit, int number, String trooperName){
        super(unit,number);
        this.name = trooperName;
        soldierCounter++;
        this.trooperKind= "pilot";
        this.marchModifier = 0.75;

    }

    public static int getSoldierCount() {
        return soldierCounter;
    }

    public static void setSoldierCount(int soldierCounter) {
        RebelTrooper.soldierCounter = soldierCounter;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double march(double duration){
        return (this.marchSpeed*duration*this.marchModifier);
    }

    @Override
    public String toString() {
        return this.getName() + "(" + this.getUnit() + this.getNumber() + ": )" + " a " + this.getTrooperKind();
    }
}
