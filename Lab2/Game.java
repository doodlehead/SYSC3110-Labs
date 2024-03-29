import java.util.LinkedList;
import java.util.Map;
import java.util.Scanner;

/**
 *  This class is the main class of the "World of Zuul" application. 
 *  "World of Zuul" is a very simple, text based adventure game.  Users 
 *  can walk around some scenery. That's all. It should really be extended 
 *  to make it more interesting!
 * 
 *  To play this game, create an instance of this class and call the "play"
 *  method.
 * 
 *  This main class creates and initialises all the others: it creates all
 *  rooms, creates the parser and starts the game.  It also evaluates and
 *  executes the commands that the parser returns.
 * 
 * @author  Michael Kölling and David J. Barnes
 * @version 2016.02.29
 */

public class Game 
{
    private Parser parser;
    private Room currentRoom;
    private LinkedList<Room> history = new LinkedList<>();
    private Player player;
        
    /**
     * Create the game and initialise its internal map.
     */
    public Game() 
    {
    	//Create a player with a max weight of 50
    	player = new Player(50);
        createRooms();
        parser = new Parser();
    }

    /**
     * Create all the rooms and link their exits together.
     */
    private void createRooms()
    {
        Room outside, theater, pub, lab, office;
        
        Map<String, Item> itemsOutside = Map.of(
        	"rock", new Item("It's a rock", 1.0),
        	"shotgun", new Item("It's a shotgun", 5.0)
        );
      
        // create the rooms
        outside = new Room("outside the main entrance of the university", itemsOutside);
        theater = new Room("in a lecture theater");
        pub = new Room("in the campus pub");
        lab = new Room("in a computing lab");
        office = new Room("in the computing admin office");
        
        // initialise room exits
        outside.setExits(null, theater, lab, pub);
        theater.setExits(null, null, null, outside);
        pub.setExits(null, outside, null, null);
        lab.setExits(outside, office, null, null);
        office.setExits(null, null, null, lab);

        currentRoom = outside;  // start game outside
        this.history.push(outside);
    }

    /**
     *  Main play routine.  Loops until end of play.
     */
    public void play() 
    {            
        printWelcome();

        // Enter the main command loop.  Here we repeatedly read commands and
        // execute them until the game is over.
                
        boolean finished = false;
        while (! finished) {
            Command command = parser.getCommand();
            finished = processCommand(command);
        }
        System.out.println("Thank you for playing.  Good bye.");
    }

    /**
     * Print out the opening message for the player.
     */
    private void printWelcome()
    {
        System.out.println();
        System.out.println("Welcome to the World of Zuul!");
        System.out.println("World of Zuul is a new, incredibly boring adventure game.");
        System.out.println("Type 'help' if you need help.");
        System.out.println();
        System.out.println("You are " + currentRoom.getDescription());
        currentRoom.printExits();
    }

    /**
     * Given a command, process (that is: execute) the command.
     * @param command The command to be processed.
     * @return true If the command ends the game, false otherwise.
     */
    private boolean processCommand(Command command) 
    {
    	Scanner input = new Scanner(System.in);
        boolean wantToQuit = false;

        if(command.isUnknown()) {
            System.out.println("I don't know what you mean...");
            return false;
        }

        String commandWord = command.getCommandWord();
        if (commandWord.equals("help")) {
            printHelp();
        }
        else if (commandWord.equals("go")) {
            goRoom(command);
        }
        else if (commandWord.equals("quit")) {
            wantToQuit = quit(command);
        } else if(commandWord.equals("back")) {
        	this.goBack();
        } else if(commandWord.equals("drop")) {
        	System.out.println("Which item to drop?");
        	this.player.printInventory();
        	
        	String userInput = input.nextLine();
        	this.player.drop(userInput);
        	
        } else if(commandWord.equals("pick")) {
        	System.out.println("Which item to pick?");
        	this.currentRoom.printItems();
        	
        	String userInput = input.nextLine();
        	boolean success = this.player.pick(userInput, currentRoom.getItem(userInput));
        	if(success) {
        		//this.currentRoom.r
        	}
        }

        return wantToQuit;
    }

    // implementations of user commands:

    /**
     * Print out some help information.
     * Here we print some stupid, cryptic message and a list of the 
     * command words.
     */
    private void printHelp() 
    {
    	this.parser.printHelp();
    }

    /** 
     * Try to go in one direction. If there is an exit, enter
     * the new room, otherwise print an error message.
     */
    private void goRoom(Command command) 
    {
        if(!command.hasSecondWord()) {
            // if there is no second word, we don't know where to go...
            System.out.println("Go where?");
            return;
        }

        String direction = command.getSecondWord();

        // Try to leave current room.
        Room nextRoom = null;
        if(direction.equals("north")) {
            nextRoom = currentRoom.getExit("north");
        }
        if(direction.equals("east")) {
        	nextRoom = currentRoom.getExit("east");
        }
        if(direction.equals("south")) {
        	nextRoom = currentRoom.getExit("south");
        }
        if(direction.equals("west")) {
        	nextRoom = currentRoom.getExit("west");
        }

        if (nextRoom == null) {
            System.out.println("There is no door!");
        }
        else {
            currentRoom = nextRoom;
            this.history.push(nextRoom);
            System.out.println("You are " + currentRoom.getDescription());
            //NEW CODE YEEET
            currentRoom.printExits();
        }
    }
    
    private void goBack() {
    	if(this.history.size() > 1) {
        	this.history.pop();
        	this.currentRoom = this.history.peek();
        	
        	System.out.println("You are " + currentRoom.getDescription());
        	currentRoom.printExits();
    	} else {
    		System.out.println("You can't go back any more!");
    	}
    }

    /** 
     * "Quit" was entered. Check the rest of the command to see
     * whether we really quit the game.
     * @return true, if this command quits the game, false otherwise.
     */
    private boolean quit(Command command) 
    {
        if(command.hasSecondWord()) {
            System.out.println("Quit what?");
            return false;
        }
        else {
            return true;  // signal that we want to quit
        }
    }
    
    public static void main(String[] args) {
    	Game game = new Game();
    	game.play();
    }
}
