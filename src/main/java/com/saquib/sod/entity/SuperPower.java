/**
 * 
 */
package com.saquib.sod.entity;

import java.io.Serializable;

/**
 * Defines a Super Power which can be used by player or monster
 * @author Saquib Sayyad
 *
 */
public class SuperPower implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -2202502411391251968L;
	
	private String name;
	private String description;
	private Integer attackDamage;
	private Integer manaCost;
	private Integer healPoints;

	public SuperPower(String name, String description, Integer attackDamage, Integer healPoints, Integer manaCost) {
		this.name = name;
		this.description = description;
		this.attackDamage = attackDamage;
		this.healPoints = healPoints;
		this.manaCost = manaCost;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the attackDamage
	 */
	public Integer getAttackDamage() {
		return attackDamage;
	}

	/**
	 * @param attackDamage the attackDamage to set
	 */
	public void setAttackDamage(Integer attackDamage) {
		this.attackDamage = attackDamage;
	}

	/**
	 * @return the manaCost
	 */
	public Integer getManaCost() {
		return manaCost;
	}

	/**
	 * @param manaCost the manaCost to set
	 */
	public void setManaCost(Integer manaCost) {
		this.manaCost = manaCost;
	}

	/**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the healPoints
	 */
	public Integer getHealPoints() {
		return healPoints;
	}

	/**
	 * @param healPoints the healPoints to set
	 */
	public void setHealPoints(Integer healPoints) {
		this.healPoints = healPoints;
	}
}
