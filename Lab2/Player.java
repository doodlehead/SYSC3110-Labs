import java.util.HashMap;
import java.util.Map;

public class Player {
	private HashMap<String, Item> inventory = new HashMap<>();
	private double maxWeight;
	private double currentWeight;
	
	public Player(double maxWeight) {
		this.maxWeight = maxWeight;
		this.currentWeight = 0;
	}
	
    public void printInventory() {
        for(Map.Entry<String, Item> e: this.inventory.entrySet()) {
        	System.out.print(e.getKey() + " ");
        }
    }
    
	public boolean drop(String item) {
		if(this.inventory.get(item) != null) {
			this.currentWeight -= this.inventory.get(item).getWeight();
			this.inventory.remove(item);
			System.out.println("You dropped the " + item);
			return true;
		} else {
			System.out.println("Cannot drop item that doesn't exit");
			return false;
		}

	}
	
	public boolean pick(String name, Item item) {
		//Check if the player can carry the weight
		if(this.currentWeight + item.getWeight() <= this.maxWeight) {
			this.currentWeight += item.getWeight();
			this.inventory.put(name, item);
			System.out.println("You picked up a " + name);
			return true;
		} else {
			System.out.println("Cannot pick item, weight limit is over " + this.maxWeight);
			return false;
		}
	}
}
