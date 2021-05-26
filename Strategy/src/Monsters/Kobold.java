/*
Title: Kobold.java
 */
package Monsters;

import Abilities.MeleeAttack;
import Abilities.RangedAttack;

import java.util.HashMap;

public class Kobold extends Monster {
    public Kobold(Integer maxHp, Integer xp, HashMap<String,Integer>itmes){
        super(maxHp,xp,itmes);

        Integer maxStr = 15;
        Integer maxDef = 6;
        Integer madAgi = 3;

        attack = new RangedAttack(this);

        str = super.getAttribute(str,maxStr);
        def = super.getAttribute(def,maxDef);
        agi = super.getAttribute(agi, madAgi);
    }

    @Override
    public String toString(){
        return "Monsters.Kobold has : " + super.toString();
    }
}
