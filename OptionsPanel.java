import javax.swing.*;
import java.awt.*;
import java.io.*;

class OptionsPanel extends JPanel {
    private Player player;
    private Computer computer;

    public OptionsPanel(Player player, Computer computer) {
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
        pokemonImage = pokemonImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        pokemonIcon = new ImageIcon(pokemonImage);

        JLabel playerPokemonLabel = new JLabel(pokemonIcon);
        playerPokemonLabel.setBounds(945, 30, 300, 300);
        this.add(playerPokemonLabel);

        JLabel textLabel = new JLabel(player.getCurrentPokemon().getPokemonName(), SwingConstants.RIGHT);
        textLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        textLabel.setBounds(550, 75, 250, 50);
        this.add(textLabel);

        JLabel statusLabel = new JLabel("Condition: " + player.getCurrentPokemon().getStatusCondition());
        statusLabel.setFont(new Font("Serif", Font.BOLD, 18));
        statusLabel.setBounds(945, 350, 350, 75);
        this.add(statusLabel);

        JLabel identifierLabel = new JLabel("YOU", SwingConstants.RIGHT);
        identifierLabel.setBounds(550, 190, 250, 40);
        identifierLabel.setFont(new Font("Courier", Font.BOLD, 24));
        identifierLabel.setForeground(Color.BLUE);
        this.add(identifierLabel);
        
        JProgressBar healthBar = new JProgressBar(0, (int) Math.round(player.getCurrentPokemon().getInitialHealth()));
        healthBar.setValue((int) Math.round(player.getCurrentPokemon().getCurrentHealth()));
        healthBar.setSize(250, 30);
        healthBar.setForeground(Color.BLUE);
        healthBar.setBounds(550, 145, 250, 30);
        this.add(healthBar);
    }

    private void displayComputer() {
        ImageIcon pokemonIcon = new ImageIcon("back/" + String.valueOf(computer.getCurrentPokemon().getNumber()) + ".png");
        Image pokemonImage = pokemonIcon.getImage();
        pokemonImage = pokemonImage.getScaledInstance(300, 300, Image.SCALE_SMOOTH);
        pokemonIcon = new ImageIcon(pokemonImage);

        JLabel computerPokemonLabel = new JLabel(pokemonIcon);
        computerPokemonLabel.setBounds(25, 250, 300, 300);
        this.add(computerPokemonLabel);

        JLabel textLabel = new JLabel(computer.getCurrentPokemon().getPokemonName());
        textLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        textLabel.setBounds(400, 375, 250, 50);
        this.add(textLabel);

        JLabel identifierLabel = new JLabel("COMPUTER");
        identifierLabel.setBounds(400, 490, 175, 40);
        identifierLabel.setFont(new Font("Courier", Font.BOLD, 24));
        identifierLabel.setForeground(Color.RED);
        this.add(identifierLabel);

        JProgressBar healthBar = new JProgressBar(0, (int) Math.round(computer.getCurrentPokemon().getInitialHealth()));
        healthBar.setValue((int) Math.round(computer.getCurrentPokemon().getCurrentHealth()));
        healthBar.setSize(250, 30);
        healthBar.setForeground(Color.RED);
        healthBar.setBounds(400, 445, 250, 30);
        this.add(healthBar);
    }

    private void displayMessage() {
        JLabel textLabel = new JLabel("SELECT AN OPTION", SwingConstants.CENTER);
        textLabel.setBounds(0, 554, 640, 150);

        try {
            File fontFile = new File("arcadeFont.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            customFont = customFont.deriveFont(48f);
            textLabel.setFont(customFont);
        } 
        catch (FontFormatException | IOException e) {}

        textLabel.setBackground(Color.ORANGE);
        textLabel.setOpaque(true);

        textLabel.setHorizontalTextPosition(JLabel.CENTER);
        textLabel.setVerticalTextPosition(JLabel.CENTER);

        this.add(textLabel);
    }

    // ===============================================================================
    // MOVE 1

    private void displayFightButton() {
        JButton fightButton = new JButton("FIGHT");
        fightButton.setBounds(640, 555, 310, 60);

        try {
            File fontFile = new File("arcadeFont.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            customFont = customFont.deriveFont(48f);
            fightButton.setFont(customFont);
        } 
        catch (FontFormatException | IOException e) {}

        if (player.getCurrentPokemon().getCurrentHealth() <= 0 || !player.getCurrentPokemon().getStatusCondition().equalsIgnoreCase("Normal")) {
            fightButton.setBackground(Color.GRAY);
        }

        else {
            fightButton.setBackground(Color.GREEN);
        }

        fightButton.setOpaque(true);
        
        this.add(fightButton);

        fightButton.addActionListener(e -> fightEventHandler());
    }

    private void fightEventHandler() {
        if (player.getCurrentPokemon().getCurrentHealth() > 0 && player.getCurrentPokemon().getStatusCondition().equalsIgnoreCase("Normal")) {
            FightPanel fightPanel = new FightPanel(player, computer);
            fightPanel.populatePanel();
            Stack.push(fightPanel);
        }
    }

    // ===============================================================================
    // MOVE 2

    private void displayPokemonButton() {
        JButton pokemonButton = new JButton("POKEMON");
        pokemonButton.setBounds(640, 625, 310, 60);

        try {
            File fontFile = new File("arcadeFont.ttf");
            Font customFont = Font.createFont(Font.TRUETYPE_FONT, fontFile);

            GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
            ge.registerFont(customFont);

            customFont = customFont.deriveFont(48f);
            pokemonButton.setFont(customFont);
        } 
        catch (FontFormatException | IOException e) {}

        if (player.pokemonsAlive() > 1 || (player.getCurrentPokemon().getCurrentHealth() <= 0 && player.pokemonsAlive() > 0)) {
            pokemonButton.setBackground(Color.GREEN);
        }

        else {
            pokemonButton.setBackground(Color.GRAY);
        }
        
        pokemonButton.setOpaque(true);
        
        this.add(pokemonButton);

        pokemonButton.addActionListener(e -> pokemonEventHandler());
    }

    private void pokemonEventHandler() {
        if (player.pokemonsAlive() > 1 || (player.getCurrentPokemon().getCurrentHealth() <= 0 && player.pokemonsAlive() > 0)) {
            PokemonPanel pokemonPanel = new PokemonPanel(player, computer);
            pokemonPanel.populatePanel();
            Stack.push(pokemonPanel);
        }
    }

    // ===============================================================================
    // MOVE 3

    private void displayBagButton() {
        JButton bagButton = new JButton("BAG");
        bagButton.setBounds(970, 555, 310, 60);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(48f);
            bagButton.setFont(font);
        } 
        catch (FontFormatException | IOException e) {
            bagButton.setFont(new Font("Serif", Font.PLAIN, 48));
        }

        if (player.getCurrentPokemon().getCurrentHealth() > 0 && !player.getCurrentPokemon().getStatusCondition().equalsIgnoreCase("Normal")) {
            bagButton.setBackground(Color.GREEN);
        }

        else {
            bagButton.setBackground(Color.GRAY);
        }

        
        bagButton.setOpaque(true);
        
        this.add(bagButton);

        bagButton.addActionListener(e -> bagEventHandler());
    }

    private void bagEventHandler() {
        if (player.getCurrentPokemon().getCurrentHealth() > 0 && !player.getCurrentPokemon().getStatusCondition().equalsIgnoreCase("Normal")) {
            BagPanel bagPanel = new BagPanel(player, computer);
            bagPanel.populatePanel();
            Stack.push(bagPanel);
        }
    }

    // ===============================================================================
    // MOVE 4

    private void displayRunButton() {
        JButton runButton = new JButton("RUN");
        runButton.setBounds(970, 625, 310, 60);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            font = font.deriveFont(48f);
            runButton.setFont(font);
        } 
        catch (FontFormatException | IOException e) {
            runButton.setFont(new Font("Serif", Font.PLAIN, 48));
        }

        runButton.setBackground(Color.GREEN);
        runButton.setOpaque(true);
        
        this.add(runButton);

        runButton.addActionListener(e -> runEventHandler());
    }

    private void runEventHandler() {
        EndPanel endPanel = new EndPanel(false);
        endPanel.populatePanel();
        Stack.push(endPanel);
    }

    public void populatePanel() {
        initializeProperties();
        displayPlayer();
        displayComputer();
        displayMessage();
        displayFightButton();
        displayPokemonButton();
        displayBagButton();
        displayRunButton();
    }
}
