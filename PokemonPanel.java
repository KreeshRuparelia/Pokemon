import javax.swing.*;
import java.awt.*;
import java.io.*;

class PokemonPanel extends JPanel {
    private Player player;
    private Computer computer;

    public PokemonPanel(Player player, Computer computer) {
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

        JLabel textLabel = new JLabel("CURRENT POKEMON: " + player.getCurrentPokemon().getPokemonName(), SwingConstants.CENTER);
        textLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        textLabel.setBounds(660, 35, 600, 50);
        this.add(textLabel);
    }

    private void displayMessage() {
        JLabel textLabel = new JLabel("SELECT A POKEMON", SwingConstants.CENTER);
        textLabel.setBounds(100, 117, 440, 100);

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
    
    private void choiceEventHandler(int pokemonNum) {
        if (player.getAllPokemon()[pokemonNum - 1].getCurrentHealth() > 0 && !player.getAllPokemon()[pokemonNum - 1].equals(player.getCurrentPokemon())) {
            player.setCurrentPokemon(player.getAllPokemon()[pokemonNum - 1]);
    
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
    }

    private void addButtons() {
        PokemonButton pokemonButtonOne = new PokemonButton(player, 1, 100, 232, 440, 60);
        pokemonButtonOne.initializeProperties();
        this.add(pokemonButtonOne);
        pokemonButtonOne.addActionListener(e -> choiceEventHandler(1));

        PokemonButton pokemonButtonTwo = new PokemonButton(player, 2, 100, 307, 440, 60);
        pokemonButtonTwo.initializeProperties();
        this.add(pokemonButtonTwo);
        pokemonButtonTwo.addActionListener(e -> choiceEventHandler(2));

        PokemonButton pokemonButtonThree = new PokemonButton(player, 3, 100, 382, 440, 60);
        pokemonButtonThree.initializeProperties();
        this.add(pokemonButtonThree);
        pokemonButtonThree.addActionListener(e -> choiceEventHandler(3));

        PokemonButton pokemonButtonFour = new PokemonButton(player, 4, 100, 457, 440, 60);
        pokemonButtonFour.initializeProperties();
        this.add(pokemonButtonFour);
        pokemonButtonFour.addActionListener(e -> choiceEventHandler(4));
    }

    public void populatePanel() {
        initializeProperties();
        displayPlayer();
        displayMessage();
        addButtons();
    }
}
