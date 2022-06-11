package sprint_5.product;


import java.awt.Font;

import javax.swing.*;

public class GameOptionsPanel { // BOTTOM PANEL ON GAME BOARD
    private final GameBoard board;
    private final GameLogic game;
   
    private final JPanel panel;
    
    private SpinnerModel spinnerModel;

    public GameOptionsPanel(GameBoard board, GameLogic game) {
        this.board = board;
        this.game = game;
        this.panel = optionsPanel();
    }
    
    private JPanel optionsPanel() {
        JPanel panel = new JPanel();
        panel.setBorder(BorderFactory.createEmptyBorder(0, 5, 5, 5));

        // GAME MODE OPTIONS
        JLabel gameModeLabel = new JLabel("Game Mode:");
        gameModeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(gameModeLabel);
        JRadioButton simpleGame = new JRadioButton("Simple");
        simpleGame.setFont(new Font("Tahoma", Font.PLAIN, 13));
        simpleGame.setSelected(true);
        JRadioButton generalGame = new JRadioButton("General");
        generalGame.setFont(new Font("Tahoma", Font.PLAIN, 13));
        ButtonGroup modeButtons = new ButtonGroup();
        modeButtons.add(simpleGame);
        modeButtons.add(generalGame);
        panel.add(simpleGame);
        panel.add(generalGame);
         
        // ADDING SPACER BETWEEN PANEL ELEMENTS 
        JLabel spacerLabel = new JLabel("  ||   ");
        spacerLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(spacerLabel);        
        
        // GRID SIZE OPTIONS
        JLabel sizeLabel = new JLabel("Grid Size:");
        sizeLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        panel.add(sizeLabel);
        int minimum = game.getMinimumSize();
        int maximum = game.getMaximumSize();
        int width = game.getGameboardSize();
        spinnerModel = new SpinnerNumberModel(width, minimum, maximum, 1);
        JSpinner gridwidthSpinner = new JSpinner(spinnerModel);
        panel.add(gridwidthSpinner);
        
        // NEW GAME BUTTON
        JButton button = new JButton("New Game");
        button.setFont(new Font("Tahoma", Font.BOLD, 12));
        panel.add(button);
        
        // ACTION LISTENER TO CHANGE GRID SIZE
        button.addActionListener(event -> {
            int gameboardSize = (int) spinnerModel.getValue();
            game.setGameboardSize(gameboardSize);
            board.calculateCells();
            if (simpleGame.isSelected())
                game.setGameMode(0);
            if (generalGame.isSelected())
                game.setGameMode(1);
            board.updateScore();
            board.repaint();
        });
        
        return panel;
    }
      
    // GETTER FOR PANEL
    public JPanel getPanel() {
        return panel;
    }
    
}
