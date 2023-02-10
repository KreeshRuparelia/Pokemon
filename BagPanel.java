import javax.swing.*;
import java.awt.*;
import java.io.*;

class BagPanel extends JPanel {
    private Player player;
    private Computer computer;

    public BagPanel(Player player, Computer computer) {
        this.player = player;
        this.computer = computer;
    }

    private void initializeProperties() {
        this.setSize(1280, 720);
        this.setBackground(Color.WHITE);
        this.setAlignmentX(JPanel.CENTER_ALIGNMENT);
        this.setAlignmentY(JPanel.CENTER_ALIGNMENT);
        this.setLayout(null);
    }

    private void displayPlayer() {
        ImageIcon pokemonIcon = new ImageIcon("front/" + String.valueOf(player.getCurrentPokemon().getNumber()) + ".png");
        Image pokemonImage = pokemonIcon.getImage();
        pokemonImage = pokemonImage.getScaledInstance(575, 575, Image.SCALE_SMOOTH);
        pokemonIcon = new ImageIcon(pokemonImage);

        JLabel playerPokemonLabel = new JLabel(pokemonIcon);
        playerPokemonLabel.setBounds(660, 85, 600, 600);
        this.add(playerPokemonLabel);

        JLabel textLabel = new JLabel("CURRENT CONDITION: " + player.getCurrentPokemon().getStatusCondition(), SwingConstants.CENTER);
        textLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        textLabel.setBounds(660, 35, 600, 50);
        this.add(textLabel);
    }

    private void displayMessage() {
        JLabel textLabel = new JLabel("SELECT A HEAL", SwingConstants.CENTER);
        textLabel.setBounds(100, 47, 440, 100);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            textLabel.setFont(font.deriveFont(48f));
        }
        catch (FontFormatException | IOException e) {
            textLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        }

        textLabel.setBackground(Color.YELLOW);
        textLabel.setOpaque(true);

        textLabel.setHorizontalTextPosition(JLabel.CENTER);
        textLabel.setVerticalTextPosition(JLabel.CENTER);

        this.add(textLabel);
    }

    private void choiceEventHandler(String cures) {
        if (cures.equalsIgnoreCase("all") || player.getCurrentPokemon().getStatusCondition().equalsIgnoreCase(cures)) {
            player.getCurrentPokemon().setStatusCondition("Normal");
        }
        
        Battle battle = new Battle(player, computer);
        ComputerPanel computerPanel = new ComputerPanel(battle.doComputerTurn());
        computerPanel.populatePanel();

        if (player.getPlayerLost()) {
            EndPanel endPanel = new EndPanel(false);
            endPanel.populatePanel();

            Stack.pop(computerPanel, endPanel);
        } else {
            OptionsPanel optionsPanel = new OptionsPanel(player, computer);
            optionsPanel.populatePanel();

            Stack.pop(computerPanel, optionsPanel);
        }
    }

    private void addButtons() {
        BagButton healButtonOne = new BagButton("Full Heal", 100, 162, 440, 60);
        healButtonOne.initializeProperties();
        this.add(healButtonOne);
        healButtonOne.addActionListener(e -> choiceEventHandler("all"));

        BagButton healButtonTwo = new BagButton("Antidote", 100, 237, 440, 60);
        healButtonTwo.initializeProperties();
        this.add(healButtonTwo);
        healButtonTwo.addActionListener(e -> choiceEventHandler("Poisoned"));

        BagButton healButtonThree = new BagButton("Awakening", 100, 312, 440, 60);
        healButtonThree.initializeProperties();
        this.add(healButtonThree);
        healButtonThree.addActionListener(e -> choiceEventHandler("Sleeping"));

        BagButton healButtonFour = new BagButton("Burn Heal", 100, 387, 440, 60);
        healButtonFour.initializeProperties();
        this.add(healButtonFour);
        healButtonFour.addActionListener(e -> choiceEventHandler("Burning"));

        BagButton healButtonFive = new BagButton("Ice Heal", 100, 462, 440, 60);
        healButtonFive.initializeProperties();
        this.add(healButtonFive);
        healButtonFive.addActionListener(e -> choiceEventHandler("freezing"));

        BagButton healButtonSix = new BagButton("Paralyze Heal", 100, 537, 440, 60);
        healButtonSix.initializeProperties();
        this.add(healButtonSix);
        healButtonSix.addActionListener(e -> choiceEventHandler("paralysed"));

        BagButton healButtonSeven = new BagButton("Persim Berry", 100, 612, 440, 60);
        healButtonSeven.initializeProperties();
        this.add(healButtonSeven);
        healButtonSeven.addActionListener(e -> choiceEventHandler("confused"));
    }

    public void populatePanel() {
        initializeProperties();
        displayPlayer();
        displayMessage();
        addButtons();
    }
}