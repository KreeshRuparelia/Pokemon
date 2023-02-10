// Importing required modules
import javax.swing.*;
import java.util.*;

/**
 * This is the stack object used to switch panels.
 * The stack object is inheriting Main.java
 */
class Stack extends Main {
    // Instance variables
    private static int topOfStack;
    private static ArrayList<JPanel> panelStack;
    
    /**
     * This is the constructor for the stack which and it initalizes all instance variables
     * as well as the appropriate variables in main
     */
    public Stack() {
        topOfStack = 0;
        panelStack = new ArrayList<JPanel>();
        stackUpdated = false;
        computerTurn = false;
    }

    /**
     * This methods a panel at the top of the stack
     * 
     * @param addPanel The panel to add to the stack.
     */
    public static void push(JPanel addPanel) {
        // Updating panelStack ArrayList
        panelStack.add(topOfStack, addPanel);
        // Updating top of stack
        topOfStack++;
        // Setting stack updated to true as there is a new panel at the top
        stackUpdated = true;
    }

    /**
     * This method pops the current panel (could be fight/pokemon/bag) as well as the outdated
     * panel below and adds a new top panel which will be shown and once that is popped it will show an updated panel
     * 
     * @param topPanel The panel that will be on top
     * @param updatedPanel The panel below the top most panel that will show and updated once the top
     * panel is popped
     */
    public static void pop(JPanel topPanel, JPanel updatedPanel) {
        // Removing top panel (fight/pokemon/bag)
        panelStack.remove(topOfStack - 1);
        topOfStack--;

        // Removing outdated options panel
        panelStack.remove(topOfStack - 1);
        topOfStack--;
        
        // Adding updated options panel
        panelStack.add(topOfStack, updatedPanel);
        topOfStack++;

        // Adding computer message panel
        panelStack.add(topOfStack, topPanel);
        topOfStack++;

        // Setting stcckUpdated and computerTurn to true so the message
        // is conveyed to the user
        computerTurn = true;
        stackUpdated = true;
    }
    
    /**
     * Pops the top panel from the panel stack
     */
    public static void popOne() {
        // Removes the top panel
        panelStack.remove(topOfStack - 1);
        topOfStack--;

        // Resetting variables to show options panel
        computerTurn = false;
        stackUpdated = true;
    }

    /**
     * It returns the top panel of the stack.
     * 
     * @return The top panel of the stack.
     */
    public static JPanel topPanel() {
        stackUpdated = false;
        return panelStack.get(topOfStack - 1);
    }

    /**
     * It returns the ArrayList of JPanels
     * 
     * @return The panelStack ArrayList.
     */
    public static ArrayList<JPanel> getPanelStack() {
        return panelStack;
    }

    /**
     * This function takes an ArrayList of JPanels and sets the panelStack variable to that ArrayList.
     * 
     * @param newStack The new stack of panels to be set.
     */
    public static void setPanelStack(ArrayList<JPanel> newStack) {
        panelStack = newStack;
    }
}