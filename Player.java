// Importing required module
import java.util.Random;

class Player {
    // Instance Variables
    private Pokemon[] allPokemon;
    private Pokemon currentPokemon;
    private boolean playerLost;

    /**
     * This is the constructor for the Player object, which initializes the necessary
     * instance variables with either a temporary or default value
     */
    public Player() {
        allPokemon = new Pokemon[4];
        playerLost = false;
    }

    /**
     * This method generates 4 unique Pokemons for the user and stores them in an array
     */
    public void generatePokemons() {
        // Generating a new Pokemon for each index
        for (int i = 0; i < 4; i++) {
            // While loop will iterate until unique pokemon generates
            while (true) {
                // uniquePokemon variable initialized as true until a duplicate Pokemon is found
                boolean uniquePokemon = true;
                // Assigning current index as a new Pokemon
                allPokemon[i] = new Pokemon();
                allPokemon[i].generatePokemonData();
                allPokemon[i].generatePokemonMoves();

                // Checking current index is unique
                for (int x = 0; x < 4; x++) {
                    if (x != i && allPokemon[x] != null) {
                        // Checking for duplicates
                        if (allPokemon[i].getPokemonName().equals(allPokemon[x].getPokemonName())) {
                            // Updating uniquePokemon as duplicate found
                            uniquePokemon = false;
                            break;
                        }
                    }
                }
                // Breaking infinite while loop if the Pokemon is unique
                if (uniquePokemon) {
                    break;
                }
            }
        }
    }

    /**
     * This method checks if the player has lost the game by checking if all of the player's Pokemon
     * have 0 health and returns true accordingly
     * 
     * @return The boolean value of whether or not the player has lost.
     */
    public boolean hasLost() {
        for (int i = 0; i < 4; i++) {
            // Checking if pokemon is alive
            if (allPokemon[i].getCurrentHealth() > 0) {
                // Returning false as player has not lost
                return false;
            }
        }
        // Returning true as no alive pokemon found
        return true;
    }

    /**
     * This method returns the number of alive pokemons the player has
     * 
     * @return The number of alive pokemons.
     */
    public int pokemonsAlive() {
        // Initializing return variable
        int alivePokemons = 0;
        // Iterating through array of all pokemons the player has
        for (int i = 0; i < 4; i++) {
            // Updating alive pokemon count if current pokemon is alive
            if (allPokemon[i].getCurrentHealth() > 0) {
                alivePokemons++;
            }
        }
        // Returning number
        return alivePokemons;
    }

    /**
     * This method picks a random pokemon out of all of the player's pokemons
     * to act as the starting pokemon for the player
     */
    public void generateCurrentPokemon() {
        Random random = new Random();
        int randomNum = random.nextInt(0, 4);

        for (int i = 0; i < 4; i++) {
            if (i == randomNum) currentPokemon = allPokemon[i];
        }
    }

    /**
     * This method returns an Array of all the Pokemons the player has
     * 
     * @return An array of all Pokemon the player has.
     */
    public Pokemon[] getAllPokemon() {
        return allPokemon;
    }

    /**
     * This method sets the array of all Pokemon a player has to the 
     * array of Pokemon passed in the parameter
     * 
     * @param allPokemon An array of Pokemon.
     */
    public void setAllPokemon(Pokemon[] allPokemon) {
        this.allPokemon = allPokemon;
    }

    /**
     * This method returns the current Pokemon that the player is using
     * 
     * @return The current Pokemon
     */
    public Pokemon getCurrentPokemon() {
        return currentPokemon;
    }

    /**
     * This function sets the currentPokemon to the Pokemon that is passed in as a parameter
     * 
     * @param currentPokemon The Pokemon that will be used in battle.
     */
    public void setCurrentPokemon(Pokemon currentPokemon) {
        this.currentPokemon = currentPokemon;
    }

    /**
     * This method returns whether the player has decided to quit the game
     * 
     * @return If the player has quit the game.
     */
    public boolean getPlayerLost() {
        return playerLost;
    }

    /**
     * It sets the playerLost variable to the value of the parameter.
     * 
     * @param playerLost The new value of whether the player has decided to quit.
     */
    public void setPlayerLost(boolean playerLost) {
        this.playerLost = playerLost;
    }
}