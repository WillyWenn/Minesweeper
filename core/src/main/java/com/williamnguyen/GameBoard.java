package com.williamnguyen;

import com.badlogic.gdx.Gdx;
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
        initBoardNumbers();
    }

    public GameBoard(GameplayScreen gameScreen, int numRows, int numCols, int numBombs) {
        this.gameScreen = gameScreen;
        board = new int[numRows][numCols];
        this.numBombs = numBombs;
        this.numFlags = this.numBombs;
        loadGraphics();
        addBombs();
        initBoardNumbers();
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
                Location loc = new Location(row, col);
                if (isValid(loc) && getValueOnBoard(loc) != BOMB) {
                    int bombCount = getBombCount(loc);
                    if (bombCount == 0) {
                        board[row][col] = 0; // empty floor
                    } 
                    else {
                        board[row][col] = 10 + bombCount; // 11 for 1 bomb, 12 for 2 bombs, etc.
                    }
                }
            }
        }
    }

    public boolean isValid(Location loc) {
        return loc.getRow() >= 0 && loc.getRow() < board.length &&
                loc.getCol() >= 0 && loc.getCol() < board[0].length;
    }

    // Returns the value at the given location on the board
    public int getValueOnBoard(Location loc) {
        if (isValid(loc)) {
            return board[loc.getRow()][loc.getCol()];
        }
        return Integer.MIN_VALUE; // or any value to indicate invalid location
    }

    //loc doesn't contain a bomb
    public int getBombCount(Location loc) {
        int count = 0;
        for(int row = loc.getRow()-1; row <= loc.getRow()+1; row++) {
            for(int col= loc.getCol()-1; col <= loc.getCol()+1; col++) {
                Location current = new Location(row,col);
                if(isValid(current)) {
                    int value = getValueOnBoard(current);
                    if(value == BOMB) {
                        count++;
                    }
                }
            }
        }
        return count;
    }
    

    //return the location on the gameboard 
    //corresponding to to a give (mouseX,mouseY position).
    //return null or if position isn't on gameboard
    public Location getTileAt(int mouseX, int mouseY) {
        int col = (mouseX-100) / 25;
        int row = (mouseY-200) / 25;
        Location loc = new Location(row, col);
        if(isValid(loc)) {
            return loc;
        }
        return null;
        
        
     }
     
    public void handleLeftClick(int mouseX, int mouseY) {
        Location loc = getTileAt(mouseX, mouseY);
        if(loc != null) {
            System.out.println("Handling left click");
            if(board[loc.getRow()][loc.getCol()] < 9) {
                board[loc.getRow()][loc.getCol()] += 10;
            }
        }
    }

    public void handleRightClick(int mouseX, int mouseY) {
        Location loc = getTileAt(mouseX, mouseY);
        if(loc != null) {
            System.out.println("Handling right click");
            if(board[loc.getRow()][loc.getCol()] < 9) {
                board[loc.getRow()][loc.getCol()] += 20;
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
                /*
                temporarilty draw bombs on the screen to know that its placed correctly
                else if(board[numRows][numCols] == -1) {
                    spriteBatch.draw(bombTile, xOffset + numCols*25, yOffset - numRows*25);
                }
                */
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
                else if(board[numRows][numCols] >= 19 && board[numRows][numCols] <= 28) {
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
