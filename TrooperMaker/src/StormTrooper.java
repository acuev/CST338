/*
Title: StormTrooper.java
Date: 04/15/2021
 */
public class StormTrooper extends Trooper{
    private String name = "";
    private static int soldierCount=0;

    StormTrooper(String unit, int number){
        super(unit,number);
        soldierCount++;
        this.trooperKind = "StormTrooper";
        this.marchModifier = 1.10;

    }
    public double march(double duration) {
        return (this.marchSpeed*duration*this.marchModifier);

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public static int getSoldierCount() {
        return soldierCount;
    }

    public static void setSoldierCount(int soldierCount) {
        StormTrooper.soldierCount = soldierCount;
    }

    @Override
    public String toString() {
        return this.getName() +
                "("  + this.getUnit() + this.getNumber()+ ": )" + " a " + getTrooperKind();
    }
}
