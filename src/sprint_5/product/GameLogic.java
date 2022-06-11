package sprint_5.product;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GameLogic {
    private final int minimumSize, defaultSize, maximumSize;
    private int gameboardSize, playerTurn;
    private int[] score;
    private char[][] gameboard;
    public int gameMode;
    
    // FOR COMPUTER AUTO PLAY
    public int bluePlayer;
    public int redPlayer;

    public List<CompletedSOSLine> lineSegments;
    
    public GameLogic() {
        this.minimumSize = 3;
        this.defaultSize = 3;
        this.maximumSize = 10;
        this.gameboardSize = defaultSize;
        this.gameboard = initializeGameboard(gameboardSize);
        this.lineSegments = new ArrayList<>();
        this.playerTurn = 1;
        this.score = new int[2];
    }
    
    public int setGameMode(int gameMode) {
        this.gameMode = gameMode;
        return gameMode; }

    public int getGameboardSize() {
        return gameboardSize; }
    
    public void setGameboardSize(int gameboardSize) {
        this.gameboardSize = gameboardSize;
        this.gameboard = initializeGameboard(gameboardSize);
        this.lineSegments.clear();
        this.playerTurn = 1;
        this.score = new int[2];
    }
    
    private char[][] initializeGameboard(int gameboardWidth) {
        char[][] gameboard = new char[gameboardWidth][gameboardWidth];
        
        for (int row = 0; row < gameboardWidth; row++) {
            for (int column = 0; column < gameboardWidth; column++) {
                gameboard[row][column] = ' ';
            }
        }
        return gameboard;
    }
    
    
    public void setGameboard(int row, int column, String letter) {
        this.gameboard[row][column] = letter.charAt(0); }
    
    public char getGameboard(int row, int column) {
        return gameboard[row][column]; }

    public char[][] getGameboard() {
        return gameboard; }
    
    
    // SIMPLE GAME OVER - AFTER FIRST SOS MADE (OR BOARD IS FULL)
    public boolean isSimpleGameOver() {
        if (checkSOS() == true) {
            return true;
        } else {
            for (int row = 0; row < gameboardSize; row++) {
                for (int column = 0; column < gameboardSize; column++) {
                    if (gameboard[row][column] == ' ') {
                        return false;
                    }
                }
            }
            return true;
        }
    }
    
    
    // GENERAL GAME OVER - CHECKING FOR A FULL BOARD
    public boolean isGeneralGameOver() {
        for (int row = 0; row < gameboardSize; row++) {
            for (int column = 0; column < gameboardSize; column++) {
                if (gameboard[row][column] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
    
    // GAME OVER
    public boolean isGameOver() {
        while (gameMode == 0 && isSimpleGameOver() == true) {
            return true; 
            }
        while (gameMode == 1 && isGeneralGameOver() == true) {
            return true;
            }
        return false;
    }
    
    public int getMinimumSize() {
        return minimumSize; }

    public int getDefaultSize() {
        return defaultSize; }

    public int getMaximumSize() {
        return maximumSize; }
    
    public void nextPlayersTurn() {
        this.playerTurn = (playerTurn == 1) ? 2 : 1; }

    public int getPlayerTurn() {
        return playerTurn; }
    
    
    // CHECKING GRID FOR SOS
    public boolean checkSOS() {
        boolean sosFound = false;
        for (int row = 0; row < gameboardSize; row++) {
            for (int column = 0; column < gameboardSize; column++) {
                if (checkHorizontal(row, column)) sosFound = true;
                if (checkDiagonalLR(row, column)) sosFound = true;
                if (checkVertical(row, column)) sosFound = true;
                if (checkDiagonalRL(row, column)) sosFound = true;
            }
        }
        return sosFound;
    }
    
    // CHECKING FOR SOS HORIZONTALLY
    private boolean checkHorizontal(int row, int column) {
        Color[] lineColor = { Color.BLUE, Color.RED };
        if (isCharacter('S', row, column)
                && isCharacter('O', row, column + 1)
                && isCharacter('S', row, column + 2)) {
            Coordinates start = new Coordinates(row, column);
            Coordinates end = new Coordinates(row, column + 2);
            Color color = lineColor[playerTurn - 1];
            if (addLineSegment(new CompletedSOSLine(color, start, end))) {
                incrementScore(playerTurn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    // CHECKING FOR SOS VERTICALLY
    private boolean checkVertical(int row, int column) {
        Color[] lineColor = { Color.BLUE, Color.RED };
        if (isCharacter('S', row, column)
                && isCharacter('O', row + 1, column)
                && isCharacter('S', row + 2, column)) {
            Coordinates start = new Coordinates(row, column);
            Coordinates end = new Coordinates(row + 2, column);
            Color color = lineColor[playerTurn - 1];
            if (addLineSegment(new CompletedSOSLine(color, start, end))) {
                incrementScore(playerTurn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    // CHECKING FOR SOS DIAGONALLY (LEFT TO RIGHT)
    private boolean checkDiagonalLR(int row, int column) {
        Color[] lineColor = { Color.BLUE, Color.RED };
        if (isCharacter('S', row, column)
                && isCharacter('O', row + 1, column + 1)
                && isCharacter('S', row + 2, column + 2)) {
            Coordinates start = new Coordinates(row, column);
            Coordinates end = new Coordinates(row + 2, column + 2);
            Color color = lineColor[playerTurn - 1];
            if (addLineSegment(new CompletedSOSLine(color, start, end))) {
                incrementScore(playerTurn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    // CHECKING FOR SOS DIAGONALLY (RIGHT TO LEFT)
    private boolean checkDiagonalRL(int row, int column) {
        Color[] lineColor = { Color.BLUE, Color.RED };
        if (isCharacter('S', row, column)
                && isCharacter('O', row + 1, column - 1)
                && isCharacter('S', row + 2, column - 2)) {
            Coordinates start = new Coordinates(row, column);
            Coordinates end = new Coordinates(row + 2, column - 2);
            Color color = lineColor[playerTurn - 1];
            if (addLineSegment(new CompletedSOSLine(color, start, end))) {
                incrementScore(playerTurn);
            }
            return true;
        } else {
            return false;
        }
    }
    
    // METHOD CHECKING IF CELL ALREADY HAS A LETTER PLAYED IN IT
    private boolean isCharacter(char c, int row, int column) {
        if (row < 0 || row > (gameboardSize - 1)) {
            return false; }
        
        if (column < 0 || column > (gameboardSize - 1)) {
            return false; }
        
        if (gameboard[row][column] == c) {
            return true;
        } else {
            return false; }
    }
        
    private boolean addLineSegment(CompletedSOSLine lineSegment) {
        CompletedSOSLine newSegment = new CompletedSOSLine(lineSegment.getLineColor(),
                lineSegment.getEndCoordinate(), lineSegment.getStartCoordinate());
        if (checkLineCoordinates(lineSegment) || checkLineCoordinates(newSegment)) {
            return false;
        } else {
            lineSegments.add(lineSegment);
            return true; 
            }
    }
    
    private boolean checkLineCoordinates(CompletedSOSLine lineSegment) {
        for (CompletedSOSLine segment : lineSegments) {
            if ((segment.getStartCoordinate()
                    .equals(lineSegment.getStartCoordinate()))
                    && (segment.getEndCoordinate()
                            .equals(lineSegment.getEndCoordinate()))) {
                return true;
            }
        }
        return false;
    }

    public List<CompletedSOSLine> getLineSegments() {
        return lineSegments; }
    
    private void incrementScore(int player) {
        this.score[player - 1]++; }

    public int[] getScore() {
        return score; }
   
}