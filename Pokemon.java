// Importing required modules
import java.io.*;
import java.util.*;

class Pokemon {
    // Instance Variables
    private int number;
    private Move[] availableMoves;
    private String pokemonName;
    private String pokemonType;
    private String statusCondition;
    private double initialHealth;
    private double currentHealth;
    private int attackPower;
    private int defenseLevel;

    /**
     * This is the constructor for the Pokemon object. This constructor initializes
     * all of the instance variables with a temporary value.
     */
    public Pokemon() {
        number = 0;
        availableMoves = new Move[4];
        pokemonName = "";
        pokemonType = "";
        statusCondition = "Normal";
        initialHealth = 0;
        currentHealth = 0;
        attackPower = 0;
        defenseLevel = 0;
    }

    /**
     * This method randomly selects a pokemon from 'pokemon.csv', then, the pokemon is given 
     * four random, unique attack moves.
     */
    public void generatePokemonData() {
        // Creating a random object to obtain a random number
        Random random = new Random();
        number = random.nextInt(1, 152);

        try {
            // Creating a scanner object to scan through 'pokemon.csv'
            Scanner scanner = new Scanner(new File("pokemon.csv"));

            // Going through 'pokemon.csv'
            while (scanner.hasNextLine()) {
                // Getting the line from 'pokemon.csv' and splitting it into an array
                String[] values = scanner.nextLine().split(",");
                
                // Getting a random line
                if (values[0].equals(Integer.toString(number))) {
                    // Assigning proper characteristics to the Pokemon and updating
                    // the instance variables
                    pokemonName = values[1];
                    pokemonType = values[2];
                    initialHealth = Double.valueOf(values[3]);
                    currentHealth = initialHealth;
                    attackPower = Integer.valueOf(values[4]);
                    defenseLevel = Integer.valueOf(values[5]);
                    // breaking loop
                    break;
                }
            }
        }
        // Catching potential errors
        catch (FileNotFoundException e) {} 
    }

    /**
     * This function generates the 4 unique moves for the pokemon
     */
    public void generatePokemonMoves() {
        // Generating 4 unique moves for the pokemon
        for (int i = 0; i < 4; i++) {
            // While loop to ensure the move is unique
            while (true) {
                // Initializing uniqueMove as true
                boolean uniqueMove = true;
                // Creating move object
                availableMoves[i] = new Move();
                availableMoves[i].generateMove();

                // Checking current index is unique
                for (int x = 0; x < 4; x++) {
                    if (x != i && availableMoves[x] != null) {
                        // Checking if a past index is the same
                        if (availableMoves[i].getMoveName().equals(availableMoves[x].getMoveName())) {
                            // Updating uniqueMove
                            uniqueMove = false;
                            break;
                        }
                    }
                }
                // Breaking infinite while loop if move is unique
                if (uniqueMove) {
                    break;
                }
            }
        }
    }

    /**
     * This method determines the damage that will be dealt to this pokemon
     * 
     * @param attacker The attacker pokemon
     * @param moveUsed The move used by the ataccker pokemon
     */
    public void determineDamage(Pokemon attacker, Move moveUsed) {
        // Updating current health
        currentHealth -= ((((6 * moveUsed.getMovePower() * (attacker.getAttackPower()/defenseLevel))/50)+2) * 5 * determineMultiplier(attacker));
    }

    /**
     * This helper method determines what the multiplier should be in the damge equation
     * 
     * @param attacker The Pokemon that is attacking
     * @return The multiplier is being returned.
     */
    private double determineMultiplier(Pokemon attacker) {
        try {
            // Creating scanner object to scan through the multiplier.csv file
            Scanner scanner = new Scanner(new File("multiplier.csv"));

            // Getting the values from the current line and storing array
            String[] values = scanner.nextLine().split(",");

            // Initializing variable
            int defenderIndex = 0;

            // Getting the index of the pokemon by scanning through the multiplier.csv file and
            // comparing pokemon types
            for (int i = 0; i < values.length; i++) {
                if (pokemonType.equalsIgnoreCase(values[i])) defenderIndex = i;
            }

            // Scanning through multiplier.csv
            while (scanner.hasNextLine()) {
                // Using values array to store current line
                values = scanner.nextLine().split(",");

                // Checking where the attacker pokemon type and defender pokemon type intersect and returning the value
                if (values[0].equalsIgnoreCase(attacker.getPokemonType())) return Double.valueOf(values[defenderIndex]);
            }

        } 
        // Catching error
        catch (FileNotFoundException e) {}
        // Default return value
        return 1;
    }

    /**
     * Determining a certain move will cause the pokemon to obtain a special status effect
     * 
     * @param attackingMove The move that is being used to attack the Pokemon
     */
    public void determineStatusCondition(Move attackingMove) {
        try {
            // Creating scanner object to scan through special_status_pokemon.csv
            Scanner scanner = new Scanner(new File("special_status_pokemon.csv"));
            
            // Scanning through the special_status_pokemon.csv file
            while(scanner.hasNextLine()) {
                // Splitting the comma seperated values into a values array
                String values[] = scanner.nextLine().split(",");

                // Checking if the current move can cause a special status effect and ensuring
                // it is not overriding another effect
                if (values[0].equalsIgnoreCase(attackingMove.getMoveName()) && statusCondition.equalsIgnoreCase("Normal")) {
                    // Updating status condition
                    statusCondition = values[1];
                }
            }
        } 
        // Catching potential exception
        catch (FileNotFoundException e) {}
    }

    /**
     * This method attempts to remove the user's status condition according to their selection 
     * 
     * @param userChoice The user's choice of which status condition to remove.
     */
    public void removeStatusCondition(int userChoice) {

        // Checking which condition the user has selected to cure

        if (userChoice == 1 && statusCondition.equalsIgnoreCase("Poisoned")) statusCondition = "Normal";

        else if (userChoice == 2 && statusCondition.equalsIgnoreCase("Sleeping")) statusCondition = "Normal";

        else if (userChoice == 3 && statusCondition.equalsIgnoreCase("Burning")) statusCondition = "Normal";

        else if (userChoice == 4 && statusCondition.equalsIgnoreCase("Freezing")) statusCondition = "Normal";

        else if (userChoice == 5 && statusCondition.equalsIgnoreCase("Paralysed")) statusCondition = "Normal";

        else if (userChoice == 6 && statusCondition.equalsIgnoreCase("Confused")) statusCondition = "Normal";

        else if (userChoice == 7) statusCondition = "Normal";
    }

    /**
     * This method returns the Pokemon's number
     * 
     * @return The Pokemon's mumber
     */
    public int getNumber() {
        return number;
    }

    /**
     * This method sets the Pokemon number to the number passed in the parameter
     * 
     * @param number The Pokemon number
     */
    public void setNumber(int number) {
        this.number = number;
    }

    /**
     * This method returns the available moves for the current player
     * 
     * @return The array of available moves.
     */
    public Move[] getAvailableMoves() {
        return availableMoves;
    }

    /**
     * This method sets the available moves for the player
     * 
     * @param availableMoves An array of all the possible moves that can be done.
     */
    public void setAvailableMoves(Move[] availableMoves) {
        this.availableMoves = availableMoves;
    }

    /**
     * This method returns the name of the pokemon
     * 
     * @return The name of the Pokemon is being returned.
     */
    public String getPokemonName() {
        return pokemonName;
    }

    /**
     * This method sets the name of the pokemon
     * 
     * @param pokemonName The name of the Pokemon
     */
    public void setPokemonName(String pokemonName) {
        this.pokemonName = pokemonName;
    }

    /**
     * This method returns the type of Pokemon this is
     * 
     * @return The type of the Pokemon
     */
    public String getPokemonType() {
        return pokemonType;
    }

    /**
     * This method sets the type of the pokemon to the parameter passed in
     * 
     * @param pokemonType The type of the pokemon.
     */
    public void setPokemonType(String pokemonType) {
        this.pokemonType = pokemonType;
    }

    /**
     * This function returns the status condition of the Pokemon
     * 
     * @return The status condition of the Pokemon.
     */
    public String getStatusCondition() {
        return statusCondition;
    }

    /**
     * This method sets the status condition of the Pokemon to the
     * parameter passed in
     * 
     * @param statusCondition The status condition of the Pokemon.
     */
    public void setStatusCondition(String statusCondition) {
        this.statusCondition = statusCondition;
    }

    /**
     * This method returns the initial health of the Pokemon
     * 
     * @return The health of the pokemon.
     */
    public double getInitialHealth() {
        return initialHealth;
    }

    /**
     * This method sets the initial health of the pokemon to the value passed in the
     * parameter
     * 
     * @param initialHealth The initial amount of health the pokemon has.
     */
    public void setInitialHealth(double initialHealth) {
        this.initialHealth = initialHealth;
    }

    /**
     * This method returns the current health of the pokemon.
     * 
     * @return The current health of the pokemon
     */
    public double getCurrentHealth() {
        return currentHealth;
    }

    /**
     * This method sets the current health of the pokemon to that of the
     * value passsed in the parameter
     * 
     * @param currentHealth The current health of the pokemon.
     */
    public void setCurrentHealth(double currentHealth) {
        this.currentHealth = currentHealth;
    }

    /**
     * This method returns the attack power of the Pokemon
     * 
     * @return The attack power of the Pokemon.
     */
    public int getAttackPower() {
        return attackPower;
    }

    /**
     * This method sets the attack power of the Pokemon
     * 
     * @param attackPower The amount of damage the Pokemon does.
     */
    public void setAttackPower(int attackPower) {
        this.attackPower = attackPower;
    }

    /**
     * This method returns the defense level of the Pokemon
     * 
     * @return The defense level of the pokemon.
     */
    public int getDefenseLevel() {
        return defenseLevel;
    }

    /**
     * This method sets the defense level of the Pokemon to the value passed 
     * in the parameter
     * 
     * @param defenseLevel The defense level of the pokemon.
     */
    public void setDefenseLevel(int defenseLevel) {
        this.defenseLevel = defenseLevel;
    }
}