/*
Title: Monsters.java
 */
package Monsters;
import Abilities.Attack;

import java.util.HashMap;
import java.util.Objects;
import java.util.Random;

public abstract class Monster {
    private Integer hp;
    private Integer xp = 10;
    private Integer maxHp;
    Integer agi = 10;
    Integer def = 10;
    Integer str = 10;
    Attack attack;
    private HashMap<String, Integer> items;

    public Monster(Integer maxHp, Integer xp, HashMap<String, Integer> items){
        this.maxHp = maxHp;
        hp = this.maxHp;
        this.xp = xp;
        this.items = items;
    }
    public Integer attackTarget(Monster monster){
        Integer temp = attack.attack(monster);
        monster.takeDamage(temp);
        System.out.println(monster.toString());
        return temp;
    }

    public Integer getHp() {
        return hp;
    }

    public void setHp(Integer hp) {
        this.hp = hp;
    }

    public Integer getXp() {
        return xp;
    }

    public Integer getMaxHp() {
        return maxHp;
    }
    public HashMap<String, Integer> getItems() {
        return items;
    }

    public void setItems(HashMap<String, Integer> items) {
        this.items = items;
    }

    public Integer getAgi(){return agi;}
    public Integer getDef(){return def;}
    public Integer getStr(){return  str;}
    Integer getAttribute(Integer min,Integer max){
        Random rand = new Random();
        if(min > max){
            Integer temp = min;
            min = max;
            max = temp;
        }
        //returns a random number between min and max inclusive
        return rand.nextInt(max-min) + min;
    }

    boolean takeDamage(Integer damage){
        if(damage > 0){
            hp-=damage;
            System.out.println("The creature was hit for " + damage + " damage");
            if(hp <= 0 ){
                System.out.println("Oh no! the creature has perished.");
                hp = 0;
            }
        }
        return hp > 0;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Monster monster = (Monster) o;
        return Objects.equals(getHp(), monster.getHp()) && Objects.equals(getXp(), monster.getXp()) && Objects.equals(getMaxHp(), monster.getMaxHp()) && Objects.equals(getAgi(), monster.getAgi()) && Objects.equals(getDef(), monster.getDef()) && Objects.equals(getStr(), monster.getStr()) && Objects.equals(attack, monster.attack) && Objects.equals(getItems(), monster.getItems());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getHp(), getXp(), getMaxHp(), getAgi(), getDef(), getStr(), attack, getItems());
    }

    @Override
    public String toString() {
        return "hp=" + this.hp + "/" + this.maxHp;
    }
}
