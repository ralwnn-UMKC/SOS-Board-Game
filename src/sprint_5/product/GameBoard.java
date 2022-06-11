package sprint_5.product;

import java.awt.*;
import javax.swing.*;

public class GameBoard {
    @SuppressWarnings("unused")
    private final GameLogic game;
    private final GameStatusPanel statusPanel;
    private final RedPlayerPanel redPlayerPanel;
    private final BluePlayerPanel bluePlayerPanel;
    private final GameOptionsPanel optionsPanel;
    private final GridPanel gridPanel;
    private final JFrame frame;

    public GameBoard(GameLogic game) {
        this.game = game;
        this.statusPanel = new GameStatusPanel(this, game);
        this.redPlayerPanel = new RedPlayerPanel(this, game);
        this.bluePlayerPanel = new BluePlayerPanel(this, game);
        this.optionsPanel = new GameOptionsPanel(this, game);
        this.gridPanel = new GridPanel(this, game);
        this.frame = GUI();
    }
    
    private JFrame GUI() {
        JFrame frame = new JFrame("SOS");
        frame.add(optionsPanel.getPanel(), BorderLayout.SOUTH);
        frame.add(redPlayerPanel.getPanel(), BorderLayout.EAST);
        frame.add(bluePlayerPanel.getPanel(), BorderLayout.WEST);
        frame.add(statusPanel.getPanel(), BorderLayout.NORTH);
        frame.add(gridPanel, BorderLayout.CENTER);
        
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        return frame;
    }
    
    public void shutdown() {
        frame.dispose();
        System.exit(0);
    }

    public JFrame getFrame() {
        return frame; }
        
    public void updateScore() {
        bluePlayerPanel.updateScore();
        redPlayerPanel.updateScore();}
    
    public void updateStatusLabel() {
        statusPanel.updateStatusLabel(); }
    
    public int getGridWidth() {
        return gridPanel.getGridWidth(); }

    public int getMargin() {
        return gridPanel.getMargin(); }
    
    public void calculateCells() {
        gridPanel.calculateCells(); }

    public void repaint() {
        gridPanel.repaint(); }
    
    public void getCells() {
        gridPanel.getCells();
    }
     
}