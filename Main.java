/*
 * NAME: KREESH RUPARELIA
 * PROJECT: POKEMON BATTLE GAME
 * DESCRIPTION: THIS GAME IS MADE USING THE JAVAX SWING FRAMEWORK AND ALLOWS YOU TO BATTLE AGAINST A COMPUTER
 * IN A BATTLE. GOOD LUCK!
 */

// Importing required modules
import javax.swing.*;
import java.awt.*;

class Main {
    // Instance variables
    private static Player player;
    private static Computer computer;
    private static JFrame gameFrame;
    public static boolean stackUpdated;
    public static boolean computerTurn;
    public static boolean tryGame;

    /** 
     * This is the constructor for the class Main. It initializes the instance variables.
     */
    public Main() {
        player = new Player();
        computer = new Computer();
        gameFrame = new JFrame("POKEMON BY KREESH");
        tryGame = false;
    }

    /**
     * This is the start protocol helper method which initializes all required instance
     * variables and sets specific parameters
     */
    private static void startProtocol() {
        // generating pokemons and current pokemon for the player
        player.generatePokemons();
        player.generateCurrentPokemon();

        // generating pokemons and current pokemon for the computer
        computer.generatePokemons();
        computer.generateCurrentPokemon();
     
        // Setting certain properties of the gameFrame
        gameFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        gameFrame.setSize(1280, 720);
        gameFrame.setResizable(false);
        gameFrame.getContentPane().setBackground(Color.WHITE);
        gameFrame.setLayout(null);
    }
   
    /**
     * The function creates a new game, and then runs the game until the player either wins or loses
     */
    public static void main(String[] args) {
        // While loop that keeps the game program operational
        while (true) {
            // Setting tryGame as false to reset it
            tryGame = false;

            // Creating a main object
            new Main();
            
            // configuring instance variables
            startProtocol();

            // Creating a start panel object that is the welcome screen
            StartPanel startPanel = new StartPanel(player, computer);
            // populating the start panel
            startPanel.populatePanel();
    
            // Creating a new stack object
            new Stack();
    
            // Pushing start panel to stack
            Stack.push(startPanel);

            // Making game visible
            gameFrame.setVisible(true);

            // This while loop will conduct turns
            while (gameFrame.isShowing()) {
                // Checking if the stack was updated
                if (stackUpdated) {
                    // If stack is updated, the gameFrame (game window) is updated to
                    // show the updated panel
                    gameFrame.getContentPane().removeAll();
                    gameFrame.getContentPane().repaint();
                    gameFrame.setSize(1280, 720);
                    gameFrame.setResizable(false);
                    gameFrame.add(Stack.topPanel());

                    // This condition checks if it the computer did turn and displays message
                    if (computerTurn) {
                        // Sleeping for 2.5 seconds
                        try {
                            Thread.sleep(2500);
                        } catch (InterruptedException e) {}

                        // Checking if the player lost as a consequence of the computer's move
                        if (player.getPlayerLost()) {
                            // Pushing end panel
                            EndPanel endPanel = new EndPanel(false);
                            endPanel.populatePanel();
                            Stack.push(endPanel);
                        } 
                        // Popping stack to do user's turn now
                        else Stack.popOne();
                    }
                }

                // Checking if the game ended and user wishes to run the game again
                if (tryGame) break;
            }

            // Checking if the user wishes to quit, if so breaking out of loop and ending problem
            if (!tryGame) break;
            
            // turning off the current game frame as while loop will reset with new frame as game will restart
            else gameFrame.setVisible(false);
        }

        // Ending program
        System.exit(0);        
    }
}