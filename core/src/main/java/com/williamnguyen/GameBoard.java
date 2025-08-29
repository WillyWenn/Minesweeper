package com.williamnguyen;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class GameBoard {
    private int[][] board; //our data structure
    private int numBombs; //number of bombs in the grid
    private int numFlags; //the number of flags that STILL have to be placed by the player
    public static final int BOMB = -1; //helps with readability
    private GameplayScreen gameScreen;

    //texture = 2d graphic
    private Texture emptyTile;
    private Texture emptyFloorTile;
    private Texture oneTile;
    private Texture twoTile;
    private Texture threeTile;
    private Texture fourTile;
    private Texture fiveTile;
    private Texture sixTile;
    private Texture sevenTile;
    private Texture eightTile;
    private Texture bombTile;
    private Texture flagTile;

    public GameBoard(GameplayScreen gameScreen) {
        this.gameScreen = gameScreen;
        board = new int[16][30];
        numBombs = 50;
        numFlags = numBombs;
        loadGraphics();
        addBombs();
    }

    public GameBoard(GameplayScreen gameScreen, int numRows, int numCols, int numBombs) {
        this.gameScreen = gameScreen;
        board = new int[numRows][numCols];
        this.numBombs = numBombs;
        this.numFlags = this.numBombs;
        loadGraphics();
        addBombs();
    }

    public void loadGraphics() {
        emptyTile = new Texture("emptyTile.jpg");
        emptyFloorTile = new Texture("empty floor.jpg");
        oneTile = new Texture("oneTile.jpg");
        twoTile = new Texture("twoTile.jpg");
        threeTile = new Texture("threeTile.jpg");
        fourTile = new Texture("fourTile.jpg");
        fiveTile = new Texture("fiveTile.jpg");
        sixTile = new Texture("sixTile.jpg");
        sevenTile = new Texture("sevenTile.jpg");
        eightTile = new Texture("eightTile.jpg");
        bombTile = new Texture("bomb.jpg");
        flagTile = new Texture("flagTile.jpg");
    }

    //add numBombs bombs to the gameboard
    //note: you need to make sure that the correct number of bombs are placed.
    public void addBombs() {
        while(numBombs > 0) {
            int rowRandom = (int)(Math.random() * board.length);
            int colRandom = (int)(Math.random() * board[0].length);

            if(board[rowRandom][colRandom] != -1) {
                board[rowRandom][colRandom] = -1;
            }
            numBombs--;
        }
        
        
        /* 
        board[0][0] = 11;
        board[0][1] = 12;
        board[0][2] = 13;
        board[0][3] = 14;
        board[0][4] = 15;
        board[0][5] = 16;
        board[0][6] = 17;
        board[0][7] = 18;
        board[0][8] = 19;
        board[0][9] = 9;
        board[0][10] = 10;
        */
        
    }

    //loop through the entire board and count and place the correct number
    //in each non-bomb space
    public void initBoardNumbers() {
        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[0].length; col++) {
                if (board[row][col] != BOMB) {
                    int bombCount = 0;
                    for (int neighborRowOffset = -1; neighborRowOffset <= 1; neighborRowOffset++) {
                        for (int neighborColOffset = -1; neighborColOffset <= 1; neighborColOffset++) {
                            if (!(neighborRowOffset == 0 && neighborColOffset == 0)) {
                                int neighborRow = row + neighborRowOffset;
                                int neighborCol = col + neighborColOffset;
                                if (neighborRow >= 0 && neighborRow < board.length && neighborCol >= 0 && neighborCol < board[0].length) {
                                    if (board[neighborRow][neighborCol] == BOMB) bombCount++;
                                }
                            }
                        }
                    }
                    if (bombCount == 0) {
                        board[row][col] = 10; // empty floor
                    } else {
                        board[row][col] = 10 + bombCount; // 11 for 1 bomb, 12 for 2 bombs, etc.
                    }
                }
            }
        }
    }

    public void draw(SpriteBatch spriteBatch) {
        int yOffset = 500;
        int xOffset = 100;

        //each tile is 25x25 pixels
        for(int numRows = 0; numRows < board.length; numRows++) {
            for(int numCols = 0; numCols < board[0].length; numCols++) {
                //+10 to uncover tile
                if(board[numRows][numCols] == 9) {
                    spriteBatch.draw(bombTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                //temporarilty draw bombs on the screen to know that its placed correctly
                else if(board[numRows][numCols] == -1) {
                    spriteBatch.draw(bombTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 10) {
                    spriteBatch.draw(emptyFloorTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 11) {
                    spriteBatch.draw(oneTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 12) {
                    spriteBatch.draw(twoTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 13) {
                    spriteBatch.draw(threeTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 14) {
                    spriteBatch.draw(fourTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 15) {
                    spriteBatch.draw(fiveTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 16) {
                    spriteBatch.draw(sixTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 17) {
                    spriteBatch.draw(sevenTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                else if(board[numRows][numCols] == 18) {
                    spriteBatch.draw(eightTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                //+20 to indicate flag tile
                else if(board[numRows][numCols] == 19 && board[numRows][numCols] <= 28) {
                    spriteBatch.draw(flagTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                //if it is less than 9, than it is an covered tile
                else {
                    spriteBatch.draw(emptyTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                //spriteBatch.draw(emptyTile, numCols*25 + 75, numRows*25 + 75); the right answer
            }
        }
    }



}
