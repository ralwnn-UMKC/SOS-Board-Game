package sprint_5.product;

import java.awt.Font;

import javax.swing.*;

public class GameStatusPanel { // TOP PANEL ON GAME BOARD
    private final GameBoard board;  
    private final GameLogic game;
    
    private final JPanel panel;
    private JLabel statusLabel;
    
    
    public GameStatusPanel(GameBoard board, GameLogic game) {
        this.board = board;
        this.game = game;
        this.panel = statusPanel();
    }
    
    private JPanel statusPanel() {
        JPanel panel = new JPanel();
        
        // GAME STATUS
        statusLabel = new JLabel();
        statusLabel.setFont(new Font("Tahoma", Font.BOLD, 14));
        
        panel.add(statusLabel);
        updateStatusLabel();
        
        return panel;
    }
    
    // METHOD TO UPDATE PLAYER TURN LABEL TEXT
    public void updateStatusLabel() {
        if (game.isGameOver() == true) {
            int[] score = game.getScore();
            if (score[0] > score[1]) {
                statusLabel.setText("Blue Wins!");
            } else if (score[1] > score[0]) {
                statusLabel.setText("Red Wins!");
            } else if (score[0] == score[1]) {
                statusLabel.setText("Draw!");
            }
      
        } else if (game.getPlayerTurn() == 1) {
            statusLabel.setText("Blue's Turn");
        } else if (game.getPlayerTurn() == 2) {
            statusLabel.setText("Red's Turn");
        }
    }
    
    // GETTER FOR PANEL
    public JPanel getPanel() {
        return panel;
    }
}

