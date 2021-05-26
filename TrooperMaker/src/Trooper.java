/*
Title: Trooper.java
Date: 04/15/2021
 */
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;

public abstract class Trooper {
    private String unit;
    private int number;
    String trooperKind;
    double marchSpeed;
    double marchModifier;

    Trooper(){
        this("AA",0);
    }

    public Trooper(String unit, int number){
        this.unit = unit;
        this.number = number;
        this.marchSpeed = 5;
    }

    public static void addToUnit(HashMap<String, List<Trooper>> units, Trooper t){
        if(t == null){
            return;
        }
        boolean result = units.containsKey(t.getUnit());
        if(!result){
            String unit = t.getUnit();
            List<Trooper>temp = new ArrayList<>();
            temp.add(t);
            units.put(unit,temp);
        }else{
            List<Trooper>x = new ArrayList<>();
            x.add(t);
            units.replace(t.getUnit(),x );
        }

    }

    abstract double march(double march);

    public  boolean attack(Trooper t, int roll){

        System.out.println(this.toString() + " is attacking " + t.toString());
        System.out.println(this.toString() + " rolled a " + roll);

        if(this == t || roll == 1){
            System.out.println(this.toString() + " is targeting itself");
            System.out.println(this.toString() + " rolled a " + roll + " and hurt itself in the confusion.");
            return true;
        }
        if(this.getTrooperKind().equals("StormTrooper")){//stormtrooper
            if(t.getTrooperKind().equals("StormTrooper")){
                System.out.println("No treason in the ranks!");
                return false;
            }//false == miss
            else if(t.getTrooperKind().equals("pilot")){//rebeltrooper
                System.out.println("Rolled a " + roll + " against the rebel scum.");
                return roll > 10 && roll%2 == 0;//even
            }
            else{
                System.out.println("Acceptable Collateral Damage!");
                return roll > 10 || roll%2==0;//even
            }
        }
        else{//rebeltrooper
            if(t.getTrooperKind().equals("pilot")){//rebeltrooper
                System.out.println("Imperial Spy!");
                return false;
            }
            else if(t.getTrooperKind().equals("StormTrooper")){
                System.out.println("Rolled a " + roll + " against the imperial scum");
                return roll > 5 || roll%2 != 0;//odd
            }
            else{
                System.out.println("Rebels targets an innocent bystander");
                return roll >= 18 && roll%2 != 0;//odd
            }
        }
    }


    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getTrooperKind() {
        return trooperKind;
    }

    public void setTrooperKind(String trooperKind) {
        this.trooperKind = trooperKind;
    }

    public double getMarchSpeed() {
        return marchSpeed;
    }

    public void setMarchSpeed(double marchSpeed) {
        this.marchSpeed = marchSpeed;
    }

    public double getMarchModifier() {
        return marchModifier;
    }

    public void setMarchModifier(double marchModifier) {
        this.marchModifier = marchModifier;
    }

    @Override
    public String toString() {
        return unit + number + " : ";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Trooper trooper = (Trooper) o;
        return getNumber() == trooper.getNumber() && Double.compare(trooper.getMarchSpeed(), getMarchSpeed()) == 0 && Double.compare(trooper.getMarchModifier(), getMarchModifier()) == 0 && Objects.equals(getUnit(), trooper.getUnit()) && Objects.equals(getTrooperKind(), trooper.getTrooperKind());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUnit(), getNumber(), getTrooperKind(), getMarchSpeed(), getMarchModifier());
    }
    

}
