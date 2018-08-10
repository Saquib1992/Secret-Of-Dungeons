package com.saquib.sod.entity;

import java.io.Serializable;

/**
 * Represents a being which has basic attributes such as name, health, damage, position etc.
 * @author Saquib Sayyad
 *
 */
public abstract class Being implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5791910512740429146L;
	
	private String name;
	private Integer healthPoint;
	private Integer damage;
	private Integer locationX;
	private Integer locationY;
	
	public Being(String name, Integer healthPoint, Integer damage) {
		this.name = name;
		this.healthPoint = healthPoint;
		this.damage = damage;
	}

	/**
	 * @return the healthPoint
	 */
	public Integer getHealthPoint() {
		return healthPoint;
	}

	/**
	 * @param healthPoint the healthPoint to set
	 */
	public void setHealthPoint(Integer healthPoint) {
		this.healthPoint = healthPoint;
	}

	/**
	 * @return the damage
	 */
	public Integer getDamage() {
		return damage;
	}

	/**
	 * @param damage the damage to set
	 */
	public void setDamage(Integer damage) {
		this.damage = damage;
	}

	/**
	 * @return the locationX
	 */
	public Integer getLocationX() {
		return locationX;
	}

	/**
	 * @param locationX the locationX to set
	 */
	public void setLocationX(Integer locationX) {
		this.locationX = locationX;
	}

	/**
	 * @return the locationY
	 */
	public Integer getLocationY() {
		return locationY;
	}

	/**
	 * @param locationY the locationY to set
	 */
	public void setLocationY(Integer locationY) {
		this.locationY = locationY;
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
	 * Checks if being is alive or dead
	 * @return true if alive else false.
	 */
	public Boolean isAlive() {
		return this.getHealthPoint() > 0;
	}

}
