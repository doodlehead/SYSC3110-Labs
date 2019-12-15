import java.util.HashMap;
import java.util.Map;

/**
 * Class Room - a room in an adventure game.
 *
 * This class is part of the "World of Zuul" application. 
 * "World of Zuul" is a very simple, text based adventure game.  
 *
 * A "Room" represents one location in the scenery of the game.  It is 
 * connected to other rooms via exits.  The exits are labelled north, 
 * east, south, west.  For each direction, the room stores a reference
 * to the neighboring room, or null if there is no exit in that direction.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */
public class Room 
{
    public String description;
    private Map<String, Item> items;
//	private Room northExit;
//    private Room southExit;
//    private Room eastExit;
//    private Room westExit;
    
    private HashMap<String, Room> exits = new HashMap<>();
    
    public Room getExit(String exit) {
    	return this.exits.get(exit);
    }

    /**
     * Create a room described "description". Initially, it has
     * no exits. "description" is something like "a kitchen" or
     * "an open court yard".
     * @param description The room's description.
     */
    public Room(String description) 
    {
        this.description = description;
    }
    
    public Room(String description, Map<String, Item> items) 
    {
        this.description = description;
        this.items = items;
    }

    /**
     * Define the exits of this room.  Every direction either leads
     * to another room or is null (no exit there).
     * @param north The north exit.
     * @param east The east east.
     * @param south The south exit.
     * @param west The west exit.
     */
    public void setExits(Room north, Room east, Room south, Room west) 
    {
        if(north != null) {
        	this.exits.put("north", north);
        }
        if(east != null) {
        	this.exits.put("east", east);
        }
        if(south != null) {
        	this.exits.put("south", south);
        }
        if(west != null) {
        	this.exits.put("west", west);
        }
    }

    /**
     * @return The description of the room.
     */
    public String getDescription()
    {
        return description;
    }
    
    public void printExits() {
        System.out.print("Exits: ");
        for(Map.Entry<String, Room> e: this.exits.entrySet()) {
        	System.out.print(e.getKey() + " ");
        }
//        if(this.northExit != null) {
//            System.out.print("north ");
//        }
//        if(this.eastExit != null) {
//            System.out.print("east ");
//        }
//        if(this.southExit != null) {
//            System.out.print("south ");
//        }
//        if(this.westExit != null) {
//            System.out.print("west ");
//        }
        System.out.println();
    }
    
    public void printItems() {
        for(Map.Entry<String, Item> e: this.items.entrySet()) {
        	System.out.print(e.getKey() + " ");
        }
    }
    
    public Item getItem(String item) {
    	return this.items.get(item);
    }

}
