// Importing required module
import java.util.Random;

class Computer {
    // Instance variable
    private Pokemon[] allPokemon;
    private Pokemon currentPokemon;
    private boolean computerLost;

    /**
     * This is the constructor for the Computer classs
     */
    public Computer() {
        allPokemon = new Pokemon[4];
        computerLost = false;
    }

    /**
     * This method generates 4 unique Pokemons for the computer and stores them in an array
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
                    if (x < i) {
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
     * This method picks a random pokemon out of all of the computer's pokemons
     * to act as the starting pokemon
     */
    public void generateCurrentPokemon() {
        // Generating random number
        Random random = new Random();
        int randomNum = random.nextInt(0, 4);

        // Getting the pokemon at that random index
        for (int i = 0; i < 4; i++) {
            if (i == randomNum) currentPokemon = allPokemon[i];
        }
    }

    /**
     * This function checks if the computer has lost the game by checking if all of the computer's Pokemon
     * have 0 health
     * 
     * @return The boolean value of whether or not the computer has lost.
     */
    public boolean hasLost() {
        // Iterating through all pokemon array
        for (int i = 0; i < 4; i++) {
            // Checking if current pokemon is alive
            if (allPokemon[i].getCurrentHealth() > 0) {
                // Returning false if current pokemon alive
                return false;
            }
        }
        // Returning true as computer has no alive pokemon
        return true;
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
     * This function returns if the computer has lost
     * 
     * @return The computerLost variable is being returned.
     */
    public boolean getComputerLost() {
        return computerLost;
    }

    /**
     * This function sets the computerLost variable to the value of the parameter
     * 
     * @param computerLost updated computer lost condition
     */
    public void setComputerLost(boolean computerLost) {
        this.computerLost = computerLost;
    }
}