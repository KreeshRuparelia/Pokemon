// Importing required modules
import java.io.*;
import java.util.*;

class Move {
    // Instance variables
    private String moveName;
    private String moveType;
    private int movePower;

    /**
     * This is the constructor for the move object
     */
    public Move() {
        moveName = "";
        moveType = "";
        movePower = 0;
    }

    /**
     * This helper method generates a random move from the moves.csv file
     */
    public void generateMove() {
        // Getting random number
        Random random = new Random();
        int randomNum = random.nextInt(1, 166);
        // initializing accumulator
        int accumulator = 0;

        try {
            // Scanning through moves.csv
            Scanner scanner = new Scanner(new File("moves.csv"));

            // Checking if next line available
            while (scanner.hasNextLine()) {
                // Getting all values and storing them in an array
                String[] values = scanner.nextLine().split(",");
                // Checking if current line is random
                if (randomNum == accumulator) {
                    // Assigning values to instance variables        
                    moveName = values[0];
                    moveType = values[1];
                    movePower = Integer.valueOf(values[2]);
                    break;
                }
                // Updating accumulator
                accumulator++;
            }
        }
        // Catching potential error
        catch (FileNotFoundException e) {}
    }

    /**
     * Getter for the move name
     * @return The name of the move
     */
    public String getMoveName() {
        return moveName;
    }

    /**
     * This function sets the name of the move
     * 
     * @param moveName The name of the move
     */
    public void setMoveName(String moveName) {
        this.moveName = moveName;
    }

    /**
     * This function returns the type of the move
     * 
     * @return The type of the move
     */
    public String getMoveType() {
        return moveType;
    }

    /**
     * This function sets the move type of the player
     * 
     * @param moveType The new type ofmove
     */
    public void setMoveType(String moveType) {
        this.moveType = moveType;
    }
    
    /**
     * This function returns the power of the move
     * 
     * @return The power of the move
     */
    public int getMovePower() {
        return movePower;
    }

    /**
     * This function sets the movePower variable to the value passed in the parameter
     * 
     * @param movePower The new move power
     */
    public void setMovePower(int movePower) {
        this.movePower = movePower;
    }
}
