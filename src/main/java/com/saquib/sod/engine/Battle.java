package com.saquib.sod.engine;

import java.util.Random;

import com.saquib.sod.config.GameConfiguration;
import com.saquib.sod.entity.Being;
import com.saquib.sod.entity.Monster;
import com.saquib.sod.entity.Player;
import com.saquib.sod.entity.SuperPower;
import com.saquib.sod.entity.actions.PlayerAction;
import com.saquib.sod.io.ConsoleIO;
import com.saquib.sod.io.IO;
/**
 * Represents a battle stage.
 *  * @author Saquib Sayyad
 *
 */
public final class Battle {

	private IO io;
	private final Random random;

	public Battle() {
		this.io = new ConsoleIO();
		this.random = new Random();
	}

	/**
	 * Starts a battle between Player and Monster
	 * 
	 * @param player
	 * @param monster
	 */
	public void startBattle(Player player, Monster monster) {
		io.battleStart(player, monster);

		while (player.isAlive() && monster.isAlive()) {

			io.battleStatus(player, monster);

			PlayerAction action = io.acceptBattleAction(player);

			switch (action) {
			case ATTACK:
				this.attack(player, monster);
				break;
			case SUPER_POWER:
				SuperPower power = io.acceptSuperPowerForAttack(player);
				this.superAttack(player, monster, power);
				break;
			}

			if (monster.isAlive()) {
				this.attack(monster, player);
			}
		}

		if (!monster.isAlive()) {
			io.monsterDefeated(monster);
			player.setLevel(player.getLevel() + 1);
			io.playerLevel(player);

			// Add bonus points to player
			player.addBonusPoints(GameConfiguration.BONUS_POINT_BATTLE);

			io.granteBonusPoint(player, GameConfiguration.BONUS_POINT_BATTLE);
			io.showPlayerSummary(player, false);
		}
	}

	/**
	 * Performs an attack by attacker to defender.
	 * 
	 * @param attacker
	 * @param defender
	 */

	public void attack(Being attacker, Being defender) {
		Integer minDamage = (int) (attacker.getDamage() * GameConfiguration.MIN_ATTACK_EFFICIENY);
		Integer damage = random.nextInt(attacker.getDamage() - minDamage) + minDamage;

		// If attacker is monster, check for super power attack based on his probability
		if (attacker instanceof Monster) {
			Monster monster = (Monster) attacker;
			Integer superPowerProbility = monster.getSuperPowerProbability();

			Integer randomNumber = random.nextInt(10) + 1;

			if (randomNumber <= superPowerProbility) {
				damage = monster.getSuperPower().getAttackDamage();
				io.monsterUsesSuperPower(monster, damage);
			} else {
				io.monsterAttack(monster, damage);
			}
		} else if (attacker instanceof Player) {
			io.playerAttack(defender, damage);
		}
		defender.setHealthPoint(defender.getHealthPoint() - damage);
	}

	/**
	 * Performs a super power attack
	 * 
	 * @param attacker
	 * @param defender
	 * @param superPower
	 */
	public void superAttack(Player attacker, Being defender, SuperPower superPower) {

		Integer damage = superPower.getAttackDamage();
		Integer heal = superPower.getHealPoints();
		Integer manaCost = superPower.getManaCost();

		// Deduct Mana if attacker is a player
		Player player = (Player) attacker;
		player.setMana(player.getMana() - manaCost);
		io.playerSuperPowerAttack(player, defender, superPower);

		attacker.setHealthPoint(attacker.getHealthPoint() + heal);
		defender.setHealthPoint(defender.getHealthPoint() - damage);
	}

}