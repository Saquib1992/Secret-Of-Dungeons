/**
 * 
 */
package com.saquib.sod.entity;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Represents a player, Player can have multiple super powers and they can use Mana as well.
 * @author Saquib Sayyad
 *
 */
public class Player extends Being {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3154240557129188117L;
	
	private Integer mana;
	private Integer bonusPoints = 0;
	private Integer level = 1;
	private Map<Integer, SuperPower> superPower;
	private Room currentRoom;

	public Player(String name, Integer healthPoint, Integer damage) {
		super(name, healthPoint, damage);
		superPower = new HashMap<Integer, SuperPower>();
	}

	/**
	 * @return the mana
	 */
	public Integer getMana() {
		return mana;
	}

	/**
	 * @param mana the mana to set
	 */
	public void setMana(Integer mana) {
		this.mana = mana;
	}

	/**
	 * @return the superPower
	 */
	public Map<Integer, SuperPower> getSuperPower() {
		return superPower;
	}

	/**
	 * @param superPower the superPower to set
	 */
	public void setSuperPower(Map<Integer, SuperPower> superPower) {
		this.superPower = superPower;
	}

	/**
	 * @return the bonusPoints
	 */
	public Integer getBonusPoints() {
		return bonusPoints;
	}

	/**
	 * @param bonusPoints the bonusPoints to set
	 */
	public void setBonusPoints(Integer bonusPoints) {
		this.bonusPoints = bonusPoints;
	}
	
	/**
	 * Adds the bonus point to current bonus points.
	 * @param bonusPoints
	 */
	public void addBonusPoints(Integer bonusPoints) {
		this.bonusPoints += bonusPoints;
	}
	

	/**
	 * @return the level
	 */
	public Integer getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(Integer level) {
		this.level = level;
	}

	/**
	 * @return the currentRoom
	 */
	public Room getCurrentRoom() {
		return currentRoom;
	}

	/**
	 * @param currentRoom the currentRoom to set
	 */
	public void setCurrentRoom(Room currentRoom) {
		this.currentRoom = currentRoom;
	}

	public void addBonusHealth(Integer point) {
		this.setBonusPoints(this.getBonusPoints() - point);
		this.setHealthPoint(this.getHealthPoint() + point);
	}

	public void addBonusDamage(Integer point) {
		this.setBonusPoints(this.getBonusPoints() - point);
		this.setDamage(this.getDamage() + point);
	}

	public void addBonusMana(Integer point) {
		this.setBonusPoints(this.getBonusPoints() - point);
		this.setMana(this.getMana() + point);
	}

	public void addLocationX(Integer x) {
		this.setLocationX(this.getLocationX() + x);
	}

	public void addLocationY(Integer y) {
		this.setLocationY(this.getLocationY() + y);
	}

	public Boolean isManaEnoughToSuperPower() {
		Boolean canSuperPower = false;
		final Integer mana = this.getMana();
		Set<Integer> keys = this.getSuperPower().keySet();

		for (Integer key : keys) {
			SuperPower superPower = this.getSuperPower().get(key);
			if (mana >= superPower.getManaCost()) {
				canSuperPower = true;
			}
		}

		return canSuperPower;
	}
}
