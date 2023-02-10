// Importing modules required
import java.util.*;

class Battle {
    // Instance variables
    private Player player;
    private Computer computer;
    
    /**
     *  This is a constructor. It is used to create an instance of the battle class.
     */
    public Battle(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    /**
     * The function determines the damage the user's move deals and the status condition of the computer's
     * pokemon.
     * 
     * @param userMove The move the user chose to use
     */
    public void attack(Move userMove) {
        // Determining damage and status condition
        computer.getCurrentPokemon().determineDamage(player.getCurrentPokemon(), userMove);
        computer.getCurrentPokemon().determineStatusCondition(userMove);

        if (computer.hasLost()) {
            computer.setComputerLost(true);
        }
    }

    /**
     * The computer randomly chooses a move from its available moves and uses it on the player's
     * current Pokemon
     * 
     * @return A summary of what the computer did.
     */
    private String computerAttack() {
        // Getting a random number
        Random random = new Random();
        int randomNum = random.nextInt(0, 4);

        // Doing the random number index move
        player.getCurrentPokemon().determineDamage(computer.getCurrentPokemon(), computer.getCurrentPokemon().getAvailableMoves()[randomNum]);
        player.getCurrentPokemon().determineStatusCondition(computer.getCurrentPokemon().getAvailableMoves()[randomNum]);

        // Checking if player has lost
        if (player.hasLost()) {
            player.setPlayerLost(true);
        }

        // Returning update on what move the user did
        return ("COMPUTER USES " + computer.getCurrentPokemon().getAvailableMoves()[randomNum].getMoveName());
    }

    /**
     * This method heals the computer and removes its status condition
     * 
     * @return A summary of what the computer did.
     */
    private String computerHeal() {
        computer.getCurrentPokemon().setStatusCondition("Normal");
        return "COMPUTER USES HEAL TO REMOVE STATUS EFFECT";
    }

    /**
     * The function will keep generating a random number until it finds a pokemon that is not the
     * current pokemon and is not fainted.
     * 
     * @return A summary of what the computer did.
     */
    private String computerSwitch() {
        while(true) {
            // Getting a random number
            Random random = new Random();
            int randomNum = random.nextInt(0, 4);

            // Checking if the current pokemon can be switched to pokemon at random index
            if (!computer.getAllPokemon()[randomNum].equals(computer.getCurrentPokemon()) && computer.getAllPokemon()[randomNum].getCurrentHealth() > 0) {
                // Switching pokemon
                computer.setCurrentPokemon(computer.getAllPokemon()[randomNum]);
                // breaking
                break;
            }
        }
        // Returning a summary of what the computer did
        return "COMPUTER DECIDES TO CHANGE POKEMONS";
    }

    /**
     * The computer will use a series of conditions to determine what it should do for its current move
     * 
     * @return A summary of the computer's turn
     */
    public String doComputerTurn() {

        String returnString = "";
        
        // Checking attack condition
        if (computer.getCurrentPokemon().getCurrentHealth() > 0 && computer.getCurrentPokemon().getStatusCondition().equalsIgnoreCase("Normal")) {
            // Updating return string
            returnString = computerAttack();
        }

        // Checking bag condition
        else if (computer.getCurrentPokemon().getCurrentHealth() > 0 && !computer.getCurrentPokemon().getStatusCondition().equalsIgnoreCase("Normal")) {
            // Updating return string
            returnString = computerHeal();
        }

        // Checking switch condition
        else if (computer.getCurrentPokemon().getCurrentHealth() <= 0) {
            // Updating return string
            returnString = computerSwitch();
        }

        return returnString;
    }
}