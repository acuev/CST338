/*
Title: Imp.java
 */
package Monsters;
import Abilities.MeleeAttack;

import java.util.HashMap;

public class Imp extends Monster {
    public Imp(Integer maxHp, Integer xp, HashMap<String,Integer> items) {
        super(maxHp, xp, items);

        Integer maxStr = 15;
        Integer maxDef = 6;
        Integer madAgi = 3;

        attack = new MeleeAttack(this);
        str = super.getAttribute(str,maxStr);
        def = super.getAttribute(def,maxDef);
        agi = super.getAttribute(agi, madAgi);

    }

    @Override
    public String toString(){
        return "Monsters.Imp has : " + super.toString();
    }
}
