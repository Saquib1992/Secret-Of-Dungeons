package com.saquib.sod.entity;

import java.io.Serializable;

/**
 * Represents a room, each room has a monster
 * 
 * @author Saquib Sayyad
 *
 */
public class Room implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -5137331552026470017L;
	
	private final String description;
    private final Monster monster;
    private final Boolean isBossRoom;
    
    public Room(String description, Monster monster, Boolean isBossRoom) {
        this.description = description;
        this.monster = monster;
        this.isBossRoom = isBossRoom;
    }

    public boolean isBossRoom() {
        return isBossRoom;
    }

    public boolean isComplete() {
        return !monster.isAlive();
    }

    @Override
    public String toString() {
        return description;
    }

    /**
	 * @return the description
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * @return the monster
	 */
	public Monster getMonster() {
		return monster;
	}

	/**
	 * @return the isBossRoom
	 */
	public Boolean getIsBossRoom() {
		return isBossRoom;
	}
}