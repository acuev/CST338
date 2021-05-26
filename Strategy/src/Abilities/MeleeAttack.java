/*
Title: MeleeAttack.java
 */
package Abilities;

import Monsters.Monster;

public class MeleeAttack implements Attack {
    Monster attacker;

    public MeleeAttack(Monster attacker){
        this.attacker = attacker;
    }

    @Override
    public Integer attack(Monster target){
        String message = attacker + " uses a melee attack on " + target;
        System.out.println(message);
        return attacker.getStr() - target.getDef();
    }
}
