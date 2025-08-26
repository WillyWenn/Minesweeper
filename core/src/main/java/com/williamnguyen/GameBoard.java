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
    }

    public GameBoard(GameplayScreen gameScreen, int numRows, int numCols, int numBombs) {
        this.gameScreen = gameScreen;
        board = new int[numRows][numCols];
        this.numBombs = numBombs;
        this.numFlags = this.numBombs;
        loadGraphics();
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

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(emptyTile, 50, 50);
    }

}
