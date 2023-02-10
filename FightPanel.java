import javax.swing.*;
import java.awt.*;
import java.io.*;

class FightPanel extends JPanel {
    private Player player;
    private Computer computer;

    public FightPanel(Player player, Computer computer) {
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

        JLabel textLabel = new JLabel(player.getCurrentPokemon().getPokemonName());
        textLabel.setFont(new Font("Sans Serif", Font.BOLD, 30));
        textLabel.setBounds(550, 75, 250, 50);
        this.add(textLabel);

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

        JProgressBar healthBar = new JProgressBar(0, (int) Math.round(computer.getCurrentPokemon().getInitialHealth()));
        healthBar.setValue((int) Math.round(computer.getCurrentPokemon().getCurrentHealth()));
        healthBar.setSize(250, 30);
        healthBar.setForeground(Color.RED);
        healthBar.setBounds(400, 445, 250, 30);
        this.add(healthBar);
    }

    private void displayMessage() {
        JLabel textLabel = new JLabel("SELECT A MOVE TO ATTACK WITH", SwingConstants.CENTER);
        textLabel.setBounds(0, 554, 640, 150);

        try {
            InputStream is = new BufferedInputStream(new FileInputStream("arcadeFont.ttf"));
            Font font = Font.createFont(Font.TRUETYPE_FONT, is);
            textLabel.setFont(font.deriveFont(48f));
        }
        catch (FontFormatException | IOException e) {
            textLabel.setFont(new Font("Serif", Font.PLAIN, 48));
        }

        textLabel.setBackground(Color.CYAN);
        textLabel.setOpaque(true);

        textLabel.setHorizontalTextPosition(JLabel.CENTER);
        textLabel.setVerticalTextPosition(JLabel.CENTER);

        this.add(textLabel);
    }

    private void moveEventHandler(int move) {
        Battle battle = new Battle(player, computer);
        battle.attack(player.getCurrentPokemon().getAvailableMoves()[move - 1]);
        
        if (computer.getComputerLost()) {
            EndPanel endPanel = new EndPanel(true);
            endPanel.populatePanel();

            Stack.push(endPanel);
        }  else {
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
        MoveButton moveButtonOne = new MoveButton(player.getCurrentPokemon().getAvailableMoves()[0].getMoveName(), 640, 555, 310, 60);
        moveButtonOne.initializeProperties();
        this.add(moveButtonOne);
        moveButtonOne.addActionListener(e -> moveEventHandler(1));

        MoveButton moveButtonTwo = new MoveButton(player.getCurrentPokemon().getAvailableMoves()[1].getMoveName(), 640, 625, 310, 60);
        moveButtonTwo.initializeProperties();
        this.add(moveButtonTwo);
        moveButtonTwo.addActionListener(e -> moveEventHandler(2));

        MoveButton moveButtonThree = new MoveButton(player.getCurrentPokemon().getAvailableMoves()[2].getMoveName(), 970, 555, 310, 60);
        moveButtonThree.initializeProperties();
        this.add(moveButtonThree);
        moveButtonThree.addActionListener(e -> moveEventHandler(3));

        MoveButton moveButtonFour = new MoveButton(player.getCurrentPokemon().getAvailableMoves()[3].getMoveName(), 970, 625, 310, 60);
        moveButtonFour.initializeProperties();
        this.add(moveButtonFour);
        moveButtonFour.addActionListener(e -> moveEventHandler(4));
    }

    public void populatePanel() {
        initializeProperties();
        displayPlayer();
        displayComputer();
        displayMessage();
        addButtons();
    }
}
