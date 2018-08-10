package com.saquib.sod.entity;
/**
 * Represents a monster which can have one super power
 * 
 * @author Saquib Sayyad
 *
 */
public final class Monster extends Being {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5421344339834925850L;
	
	private SuperPower superPower;
	private Integer superPowerProbability;

	public Monster(String name, Integer health, Integer damage, SuperPower superPower, Integer superPowerProbability) {
		super(name, health, damage);
		this.superPower = superPower;
		this.superPowerProbability = superPowerProbability;
	}

	/**
	 * @return the superPower
	 */
	public SuperPower getSuperPower() {
		return superPower;
	}

	/**
	 * @param superPower the superPower to set
	 */
	public void setSuperPower(SuperPower superPower) {
		this.superPower = superPower;
	}

	/**
	 * Returns probability of using super power on scale of 10. So 5 means 50% and 10 means 100%.
	 * @return the superPowerProbability
	 */
	public Integer getSuperPowerProbability() {
		return superPowerProbability;
	}

	/**
	 * Sets probability of using super power on scale of 10.
	 * @param superPowerProbability the superPowerProbability to set
	 */
	public void setSuperPowerProbability(Integer superPowerProbability) {
		this.superPowerProbability = superPowerProbability;
	}
}