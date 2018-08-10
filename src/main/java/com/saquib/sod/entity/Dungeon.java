package com.saquib.sod.entity;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Represents the map of the game, which includes room.
 * 
 * @author Saquib Sayyad
 *
 */
public final class Dungeon implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 3399098548454298794L;
	
	private final Map<Integer, Map<Integer, Room>> map;

    public Dungeon() {
    	map = new HashMap<Integer, Map<Integer, Room>>();
    }

    /**
     * Add room to the coordinates in dungeon
     * @param x
     * @param y
     * @param room
     */
    public void putRoom(int x, int y, Room room) {
        if (!map.containsKey(x)) {
            map.put(x, new HashMap<Integer, Room>());
        }
        map.get(x).put(y, room);
    }

    /**
     * Get room using the coordinates in dungeon
     * @param x
     * @param y
     * @return
     */
    public Room getRoom(int x, int y) {
        return map.get(x).get(y);
    }

    /**
     * Check if room exist in dungeon or not
     * @param x
     * @param y
     * @return
     */
    public boolean roomExists(int x, int y) {
        if (!map.containsKey(x)) {
            return false;
        }
        return map.get(x).containsKey(y);
    }
    
    /**
     * Returns dungeon layout
     * @return
     */
    public Map<Integer, Map<Integer, Room>> getMap() {
		return map;
	}

    /**
     * Check if game is completed by killing boss monster.
     * @param room
     * @return
     */
    public boolean isComplete(Room room) {
        return room.isBossRoom() && room.isComplete();
    }
}
