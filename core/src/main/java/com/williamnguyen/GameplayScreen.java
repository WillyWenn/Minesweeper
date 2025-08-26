package com.williamnguyen;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Camera;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.viewport.FitViewport;
import com.badlogic.gdx.utils.viewport.Viewport;

public class GameplayScreen implements Screen {

    //Object that draws all our sprite graphics: jpgs, pngs, etc.
    private SpriteBatch spriteBatch;

    //Object that draws shapes: rectangles, ovals, etc.
    private ShapeRenderer shapeRenderer;

    //Camera to view the virtual world
    private Camera camera;

    //controls how the camera views the world
    //zoom in/out? Keep everything scaled?
    private Viewport viewport;

    private GameBoard gameBoard;

        /*
         * runs one time at the very beginning
         * all setup should happen here:
         *      loading graphics
         *      start values for variables
         */    
    @Override
    public void show() {
        //OrthographicCamera is a 2d camera
        camera = new OrthographicCamera();
        //set the camera position to the middle of the world
        camera.position.set(1280/2, 720/2, 0);

        //required to save and update the camera changes above
        camera.update();

        //freeze my view to 1280x720 no matter the resolution of the window, the camera will
        //only show 1280x720
        viewport = new FitViewport(1280, 720, camera);

        //empty initialization of objects that will draw graphics for us
        spriteBatch = new SpriteBatch();
        shapeRenderer = new ShapeRenderer();

        //I just know this was the solution to an annoying problem.
        shapeRenderer.setAutoShapeType(true);

        gameBoard = new GameBoard(this);
    }

    /*
     * This method runs as fast as it can(or a set FPS)
     * repeatedly, constantly looped
     * Things to include in this method:
     *      (1) Process user input
     *      (2) A.I.
     *      (3) Draw all graphics
     */
    @Override
    public void render(float delta) {

        //get player input

        //process player input, A.I.

        //all drawings of shapes MUST go between begin/end
        shapeRenderer.begin();
        shapeRenderer.end();

        //all drawing of graphics must be between begin and end
        spriteBatch.begin();
        gameBoard.draw(spriteBatch);
        spriteBatch.end();
    }

    @Override
    public void resize(int width, int height) {
        viewport.update(width, height);
    }

    @Override
    public void pause() {
        
    }

    @Override
    public void resume() {
        
    }

    @Override
    public void hide() {
        
    }

    @Override
    public void dispose() {
        spriteBatch.dispose();
        shapeRenderer.dispose();
    }
    
}
