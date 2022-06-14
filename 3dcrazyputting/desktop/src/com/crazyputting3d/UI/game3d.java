package com.crazyputting3d.UI;
import com.badlogic.gdx.*;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.PerspectiveCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.VertexAttributes;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g3d.*;
import com.badlogic.gdx.graphics.g3d.attributes.ColorAttribute;
import com.badlogic.gdx.graphics.g3d.attributes.TextureAttribute;
import com.badlogic.gdx.graphics.g3d.environment.PointLight;
import com.badlogic.gdx.graphics.g3d.utils.FirstPersonCameraController;
import com.badlogic.gdx.graphics.g3d.utils.ModelBuilder;
import com.crazyputting3d.Engine.physicsEngine;
import com.crazyputting3d.Bots.BasicBot;
import com.crazyputting3d.Bots.Bot;
import com.crazyputting3d.Bots.BruteForceBot;
import com.crazyputting3d.Bots.HillClimbingBot;
import com.crazyputting3d.Bots.NewtonRaphsonBot;
import com.crazyputting3d.Bots.RandomBot;
import com.crazyputting3d.InputReader.Search;
import com.crazyputting3d.Network.Client;
import com.crazyputting3d.Network.ClientThread;
import com.crazyputting3d.InputReader.Function;
import com.crazyputting3d.Sounds.Sounds;
import org.lwjgl.opengl.GL20;
import java.io.IOException;
import java.util.ArrayList;
import static com.badlogic.gdx.Gdx.input;


    /**
     * This is the class which is responsible for the creation and visualization of the UI. Below is
     * visible were all the variables are created. They are created as instance variables, so they can
     * be used in the entire class.
     * Authors
     * Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le,
     * Gabrijel Radovcic, Elza Strazda
     * version 2.0
     * since   2022-05-11
     */

public class game3d extends ApplicationAdapter implements InputProcessor {

    /**
     * The instance field which contains all of the instance variables
     * which are used when visualizing the program in 3D.
     */

    private Search search;
    private float terrainX1;
    private float terrainX2;
    private float terrainZ1;
    private float terrainZ2;
    private double ballX;
    private double ballY;
    private float ballZ;
    private double holeX;
    private double holeY;
    private double holeRad;
    private double [] xvaluesSP;
    private double [] zvaluesSP;
    private double [] xvaluesT;
    private double [] zvaluesT;

    private double []xvaluesW;
    private double []zvaluesW;

    private ModelBuilder stickBuilder;
    private Model stick;
    private ModelInstance stickInstance;
    private ModelBuilder stickBuilderBot;
    private Model stickBot;
    private ModelInstance stickInstanceBot;
    private float timeSeconds2 = 0f;
    private int angle = 30;
    private ArrayList<Double> coords = new ArrayList<>();
    private PerspectiveCamera camera;
    private FirstPersonCameraController controller;
    private Environment environment;
    private PointLight pointLight;
    private ModelBuilder terrainBuilder;
    private ModelBatch modelBatch;
    private ModelBuilder modelBuilder;
    private ModelBuilder holeBuilder;
    private Model hole;
    private ModelInstance holeInstance;
    private ModelBuilder ballBuilder;
    private Model ball;
    private ModelInstance ballInstance;
    private ModelInstance bArrowInstance;
    private ArrayList<ModelInstance> terrain3d = new ArrayList<>();
    private ArrayList<ModelInstance> trees3d = new ArrayList<>();
    private ArrayList<ModelInstance> wall3d = new ArrayList<>();
    private int screenWidth;
    private int screenHeight;
    private double ballArrowX;
    private double ballArrowY;
    private float[] ballcoordsX = {0};
    private float[] ballcoordsY = {0};
    private float timeSeconds = 0f;
    private int index=-1;
    private int fallFrames=-1;
    private boolean playFlag=false;
    private boolean arrowFlag=true;
    private int numShotsTaken;
    private BitmapFont font;
    private SpriteBatch batch;
    private Sounds sound;
    private double velocity = 0.1;
    private double speedY;
    private double speedX;
    private boolean treeHitted;
    private boolean isInWater;
    private boolean hitWall;
    private Thread thread;
    private Thread thread2;
    private boolean soundOn;
    private physicsEngine engine;
    private boolean game;
    private boolean bot;
    private double renderSpeed=0.0167;
    private boolean stopgame = true;
    private double[] radius;
    private Bot gameBot;
    private int botInt;
    private int level;
    private int playerid;
    private ClientThread clientthread;
    private boolean waitingroom;
    private boolean ballCam;


    /**
     *  The constructor of game3d brings a boolean variable which is responsible for checking if the program
     *  is going to run as a game or as the simulation. It contains another boolean which is responsible
     *  for checking if the program is run in 'bot' mode. The third parameter is an integer which is used
     *  to determine which bot is used when the game is launched in bot mode. 
     */

    public game3d(boolean game, boolean bot, int botInt, int level){
        this.game = game;
        this.bot = bot;
        this.botInt = botInt;
        this.level = level;

        if(!bot&&!game) {
            waitingroom=true;
        } else waitingroom=false;
    }

    public void create() {
        /*
        *   The create() method is runned once and only once when the game3d class is run.
        *   If the 'game' variable is false, the input for velocities is given from the input file.
        */

        if(!game && !bot){
            clientthread = new ClientThread();
            clientthread.start();
            clientthread.getClient().createPlayer(playerid);
        }
        
        /**
         * Create sound object and threads. Run getValues and setTerrainCoords method and create
         * an physicsEngine object.
         */

        sound = new Sounds();
        thread = sound.background();
        thread2 = sound.background2();
        getValues();
        ballZ = h(ballX,ballY);
        setTerrainCoords();

        /**
         * According to the parameter botInt, channge which bot will be used
         */

        try {
            engine = new physicsEngine();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(botInt==4){
            this.gameBot = new BruteForceBot(engine);
        }
        else if(botInt==2){
            this.gameBot = new RandomBot(engine);
        }
        else if(botInt==3){
            this.gameBot = new BasicBot(engine);
        }
        else if(botInt==0){
            this.gameBot = new HillClimbingBot(engine);
        }
        else if(botInt==1){
            this.gameBot = new NewtonRaphsonBot(engine);
        }

        //Create the camera and set variables to the length and the width of the camera
        this.screenWidth = Gdx.graphics.getWidth();
        this.screenHeight = Gdx.graphics.getHeight();
        this.camera = new PerspectiveCamera(75,screenWidth,screenHeight);
        camera.position.set(-2,1f,0f);
        camera.lookAt(0,1,0);
        camera.near = 0.1f;
        camera.far = 300f;
        controller = new FirstPersonCameraController(camera);
        Gdx.input.setInputProcessor(controller);

        // Create variables which are used to create and render 3D models.
        modelBatch = new ModelBatch();
        modelBuilder = new ModelBuilder();
        terrainBuilder = new ModelBuilder();
        environment = new Environment();
        environment.set(new ColorAttribute(ColorAttribute.AmbientLight, 0.8f, 0.8f,0.8f, 1f));
        pointLight = new PointLight();
        pointLight.set(0.8f,0.8f,0.8f,camera.position,1);
        environment.add(pointLight);
        font = new BitmapFont();
        batch = new SpriteBatch();

        //Run the render Terrain and render Trees method
        renderTerrain();
        renderTrees(radius);
        renderWallMaze();
    }

    //The render() method is fired 60 times per second and is used to update locations and logic variables
    public void render() {
        if(ballCam) {
            camera.position.set((float)ballX,h(ballX,ballY)+1,(float)ballY);
        }

        if(waitingroom) {
            waitingroom = !clientthread.getClient().isPlayable();
        } else {
            update();
        }

        //Get the time (in seconds) which will be stored in a variable
        timeSeconds +=Gdx.graphics.getDeltaTime();

        if(timeSeconds > renderSpeed && index<ballcoordsX.length-1 && playFlag == true){
            //If the ball is hit and rolling, this if statement will trigger and will animate the ball
            timeSeconds = 0;
            index++;
            animateBall(index);
        } else if(index==ballcoordsX.length-1 && fallFrames<10 && engine.isInHole()) {
            //If the animation is at the end, trigger the falling animation of the ball
            fallFrames++;
            animateBallFalling();
            if(fallFrames==9) {

                sound.victory();
                /**
                 * If the falling animation is done, trigger the victory screen
                 * to be showed and exit the current game screen
                 */
                //VictoryScreen VScreen = new VictoryScreen();
                System.out.println(bot);
                if (bot) {
                    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                    config.setTitle("Crazy Putting 3D!");
                    config.setWindowedMode(600, 360);
                    new Lwjgl3Application(new VictoryScreenGame(this), config);
                } if(!bot&&!game) {
                    clientthread.getClient().deletePlayer(playerid);
                    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                    config.setTitle("Crazy Putting 3D!");
                    config.setWindowedMode(600, 360);
                    new Lwjgl3Application(new VictoryScreenGame(this), config);
                } else {
                    Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
                    config.setTitle("Crazy Putting 3D!");
                    config.setWindowedMode(600, 360);
                    new Lwjgl3Application(new VictoryScreenGame(this), config);
                }
                //sound.victory();

            }

        } else if(index==ballcoordsX.length-1 && fallFrames<10 && playFlag ==true) {
            //If the ball is at rest on the field, update the arrow of the ball X and Y location
            ballArrowX = ballcoordsX[index]+0.3f;
            ballArrowY = ballcoordsY[index];
            playFlag=false;
        }else if(index==ballcoordsX.length-1 && fallFrames<10 && ((game)||(!game&&!bot))){
            //Trigger the rendering of the arrow which points at the direction of were to shoot the ball
            arrowFlag=true;
        }
        if(engine.isInTree(ballX+0.01*speedX, ballY+0.01*speedY)&&treeHitted){
            //If the ball hits the tree, trigger the appropriate sound
            sound.tree_hit();
            treeHitted=false;
        }

        if(index!=-1&&engine.isInWater(ballcoordsX[index], ballcoordsY[index])&&isInWater==false){
            //If the ball falls in the water, trigger the appropriate sound
            sound.ball_water();
            isInWater=true;
        }
        if((Math.abs(ballX-terrainX1)<0.05||Math.abs(ballX-terrainX2)<0.05||Math.abs(ballY-terrainZ1)<0.05||Math.abs(ballY-terrainZ2)<0.05)&&hitWall&&index>=2){
            //If the ball hits a wall, trigger the appropriate sound
            sound.wall_hit();
            hitWall=false;
        }

        //Clear the screen for updating and update the camera
        Gdx.gl.glClearColor(0.5f,0.7f,0.9f,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT | GL20.GL_DEPTH_BUFFER_BIT);
        camera.update();
        controller.update(Gdx.graphics.getDeltaTime());
        modelBatch.begin(camera);
        
        //Render the terrain
        for(int i=0; i<terrain3d.size(); i++) {
            modelBatch.render(terrain3d.get(i), environment);

        }

        //Render all the trees
        for(int i=0; i<trees3d.size(); i++) {
            modelBatch.render(trees3d.get(i), environment);
        }

        //Render all the walls
        for (int i = 0; i < wall3d.size(); i++) {
            modelBatch.render(wall3d.get(i),environment);
        }

        //Render the hole and the flag
        renderHole((float) holeX, (float) holeY, (float) holeRad);
        renderFlag((float) holeX, (float) holeY);

        //Render the wall
        if(h(terrainX1,terrainZ1) == 0) {
            renderWall((terrainX1 + terrainX2) / 2, terrainZ1);
        }
        else
            renderWall2(terrainX1+terrainX2 + 7, terrainZ1);
        modelBatch.end();

        //Render the ball and the stick (if the game is played as the user)
        renderBall((float) ballX, (float) ballY, ballZ, 0.05f);
        if((game)||(!game&&!bot)) {
            renderStick1((float) ballX, (float) ballY, ballZ);
        }

        //Render the text on the screen
        batch.begin();
        font.getData().setScale(1,1);
        font.draw(batch, "FPS = " + Gdx.graphics.getFramesPerSecond(), 5,  camera.viewportHeight-5);
        font.draw(batch, "Shots taken = " + numShotsTaken, 5,  camera.viewportHeight-25);
        font.draw(batch, "Press 'SPACE' to shoot the ball", 5,  camera.viewportHeight-45);
        font.draw(batch, "Press 'ESCAPE' to close the game", 5,  camera.viewportHeight-65);
        font.draw(batch, "Press 'R'  to go back to the main menu", 5,  camera.viewportHeight-85);
        font.draw(batch, "Press 'B'  to toggle ball cam on/off", 5,  camera.viewportHeight-105);


        if(waitingroom) {
            font.getData().setScale(2,2);
            font.draw(batch, "Waiting for other players...",450,400);
            font.getData().setScale(1,1);
        }

        if(!game&&!bot) {
            for(int k=0; k<Client.playerdata.size(); k++) {
                font.draw(batch, "Player "+Client.playerdata.get(k).get(0)+" has score: "+Client.playerdata.get(k).get(1), 5, camera.viewportHeight-205-(k*20));
            }
        }

        if(game||(!game&&!bot)){
        font.draw(batch, "Press ' Left-Arrow ' or ' Right-Arrow ' to change direction of the shot", 5,  camera.viewportHeight-125);
        font.draw(batch, "Press ' WASD ' to move the camera", 5,  camera.viewportHeight-145);
        font.draw(batch, "Press 'UP-DOWN' to change selected speed", 5,  camera.viewportHeight-165);
        
        font.getData().setScale(2, 2);
        font.draw(batch, "V = " + (float)velocity, camera.viewportWidth-100,  camera.viewportHeight-690);
        }
        font.getData().setScale(2, 2);
        font.draw(batch, "The X coordinate of the ball is: " + String.format("%.3f",ballX), 5, camera.viewportHeight-660);
        font.draw(batch, "The Y coordinate of the ball is: " + String.format("%.3f",ballY), 5, camera.viewportHeight-690);
        font.getData().setScale(1, 1);
        batch.end();
    }

    //Animation of the ball when it is rolling
    public void animateBall(int index) {
        ballX = ballcoordsX[index];
        ballY = ballcoordsY[index];
        ballZ = h(ballX,ballY);
    }

    //Animation of the ball when it is falling and trigger the sound when it goes in the hole
    public void animateBallFalling() {
        ballX = ballcoordsX[ballcoordsX.length-1];
        ballY = ballcoordsY[ballcoordsX.length-1];
        ballZ = ballZ - 0.01f;
        if(stopgame) {
            sound.ball_inhole();
            stopgame=false;
        }
    }

    //Get the function of the terrain from cheat.java
    public float h(double x, double y){
        Function cheat = new Function();
        return (float) cheat.getHeightFunction(x, y);
    }

    //Get all the values from search.java and put them into the appropriate variables
    public void getValues() {
        try {
            search = new Search("input.txt");
            search.run();

            xvaluesSP = search.get_sandPitX();
            zvaluesSP = search.get_sandPitY();

            xvaluesT = search.get_treeX();
            zvaluesT = search.get_treeY();

            xvaluesW = search.get_wallX();
            zvaluesW = search.get_wallY();

            ballArrowX = search.get_x0()+0.3f;
            ballArrowY = search.get_y0();
            ballX = search.get_x0();
            ballY = search.get_y0();
            holeX = search.get_xt();
            holeY = search.get_yt();
            holeRad = search.get_r();
            radius = search.get_treeR();

        } catch (IOException e) {
            e.printStackTrace();
        }

        //Add all the different coordinates in one ArrayList
        coords.add(ballX);
        coords.add(ballY);
        coords.add(holeX);
        coords.add(holeY);

        for(int i=0; i<xvaluesSP.length; i++) {
            coords.add(xvaluesSP[i]);
            coords.add(zvaluesSP[i]);
        }

        for(int i=0; i<xvaluesT.length; i++) {
            coords.add(xvaluesT[i]);
            coords.add(zvaluesT[i]);
        }
    }

    //Use the array which stores the coordinates to calculate the min and max of X and Y
    public void setTerrainCoords() {
        double biggestX=-1000000;
        double smallestX=1000000;
        double biggestY=-1000000;
        double smallestY=1000000;
        int offset = 3;
        for(int i=0; i<coords.size(); i++) {
            if(i%2==0) {
                if(coords.get(i)>biggestX) {
                    biggestX = coords.get(i);
                }
                if(coords.get(i)<smallestX) {
                    smallestX = coords.get(i);
                }
            } else {
                if(coords.get(i)>biggestY) {
                    biggestY = coords.get(i);
                }
                if(coords.get(i)<smallestY) {
                    smallestY = coords.get(i);
                }
            }
        }
        terrainX1 = (float) (smallestX-offset);
        terrainX2 = (float) (biggestX+offset);
        terrainZ1 = (float) (smallestY-offset);
        terrainZ2 = (float) (biggestY+offset);
    }

    //The method which renders the ball and the arrow of the ball
    public void renderBall(float x, float y, float z, float rad) {
        float intersection = rad*2;
        ballBuilder = new ModelBuilder();
        ball = ballBuilder.createSphere(intersection,intersection,intersection,30,30,new Material(ColorAttribute.createDiffuse(Color.WHITE)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        ballInstance = new ModelInstance(ball, x, z+rad+(float)Math.sqrt(Math.pow(0.1f*(float)engine.hxderivated(x, y),2)+Math.pow(0.1f*(float)engine.hyderivated(x, y),2))+0.03f, y);

        if(arrowFlag&&((game)||(!game&&!bot))) {
            Model ballArrow = modelBuilder.createArrow(x, h(x, y) + rad + (float)Math.sqrt(Math.pow(0.1f*(float)engine.hxderivated(x, y),2)+Math.pow(0.1f*(float)engine.hyderivated(x, y),2))+0.03f, y, (float) (ballArrowX), h(ballArrowX, ballArrowY) + rad + (float)Math.sqrt(Math.pow(0.1f*(float)engine.hxderivated(x, y),2)+Math.pow(0.1f*(float)engine.hyderivated(x, y),2))+0.03f, (float) ballArrowY, 0.1f, 0.6f, 5,
                    GL20.GL_TRIANGLES, new Material(ColorAttribute.createDiffuse(Color.DARK_GRAY)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            bArrowInstance = new ModelInstance(ballArrow);
            modelBatch.render(bArrowInstance, environment);
        }
        modelBatch.render(ballInstance,environment);
    }

    //The method which renders the hole at the correct location
    public void renderHole(float x, float y, float rad) {
        float intersect = rad*2;
        holeBuilder = new ModelBuilder();
        hole = holeBuilder.createSphere(intersect,0.01f,intersect,30,30,new Material(ColorAttribute.createDiffuse(Color.BLACK)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        holeInstance = new ModelInstance(hole, x,h(x,y)+0.10f,y);
        modelBatch.render(holeInstance,environment);
    }

    /*
        The function which calculates all the locations of the lines which form the mesh of the terrain
        according to the given function h(). It is also responsible for colouring the terrain green,yellow
        or blue according to of the field is grass, sand or water.
    */
    public void renderTerrain() {
        Color color = Color.GREEN;
        for(float x=terrainX2; x>=terrainX1;x=x-0.2f) {
            for(float z=terrainZ2; z>=terrainZ1; z=z-0.2f) {



                if (h(x, z) < 0) {
                    color = Color.NAVY;
                } else {
                    color = collorheight(h(x,z));
                }
                for(int i=0; i<xvaluesSP.length-1; i++) {
                    if (x >= xvaluesSP[i] && x <= xvaluesSP[i+1] && z >= zvaluesSP[i] && z <= zvaluesSP[i+1]) {
                        color = Color.YELLOW;
                        break;
                    } 
                }

                if(x!=terrainX1) {
                    Model arrow1 = terrainBuilder.createBox(0.3f,0.1f,0.3f,
                            GL20.GL_TRIANGLES, new Material(ColorAttribute.createDiffuse(color)), VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                    ModelInstance model = new ModelInstance(arrow1);
                    model.transform.setToTranslation(x,h(x,z),z);
                    terrain3d.add(model);


                }




            }
        }
    }

    /**
     * The method which is used to create a gradient for the colors. When the terrain is higher
     * the grass becomes a lighter green and vice versa. 
     */

    public Color collorheight(float h){
        float r=(150*h+30f)/255;
        float g=(150*h+125f)/255;
        return new Color(r,g,0,1f);
    }
    
    //The method which is responsible for rendering and creating the trees given an array for all the
    public void renderTrees(double[] radius) {
        for(int i=0; i<xvaluesT.length; i++) {
            Model treeBot = modelBuilder.createCylinder(2*(float) radius[i], 1, 2*(float) radius[i], 100,
                    new Material(ColorAttribute.createDiffuse(Color.BROWN)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            Model treeTop = modelBuilder.createCone(2*(float) radius[i]*4, 2*(float)radius[i]*2, 2*(float)radius[i]*4, 5, new Material(ColorAttribute.createDiffuse(Color.FOREST)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            trees3d.add(new ModelInstance(treeBot, (float) xvaluesT[i], (float)h(xvaluesT[i], (float)zvaluesT[i]) + 0.5f, (float)zvaluesT[i]));
            trees3d.add(new ModelInstance(treeTop, (float)xvaluesT[i],(float)h(xvaluesT[i], (float)zvaluesT[i]) + 1f+2*(float)radius[i],(float) zvaluesT[i]));
        }
    }

    /*
        Render the wall which is placed in front of the terrain. It can visualise a given picture.
        Which renderWall method is rendered and used is dependent on the function of the terrain.
     */
    public void renderWall(float x, float y){
        float height = (terrainX2-terrainX1)/3;
        float depth = (terrainZ1 +terrainZ2)/2;

        Texture wallTexture = new Texture("logoGroup.png");
        wallTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        wallTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Material material = new Material(TextureAttribute.createDiffuse(wallTexture));

        Texture wallTexture2 = new Texture("logoDKE.png");
        wallTexture2.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        wallTexture2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Material material2 = new Material(TextureAttribute.createDiffuse(wallTexture2));

        ModelBuilder wallBuilder;
        wallBuilder = new ModelBuilder();
        Model wall1 = wallBuilder.createBox(terrainX2-terrainX1,0.5f,0.01f,new Material(material),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        ModelInstance wallInstance1 = new ModelInstance(wall1,x,h(x,y)+height/6,terrainZ1);

        Model wall2 = wallBuilder.createBox(0.0001f,0.5f,terrainZ2-terrainZ1,new Material(material2),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        ModelInstance wallInstance2 = new ModelInstance(wall2,terrainX2,h(x,y)+height/6,depth);

        modelBatch.render(wallInstance1);
        modelBatch.render(wallInstance2);
    }
    public void renderWall2(float x, float y){
        float height = (terrainX2-terrainX1)/3;
        float depth = (terrainZ1 +terrainZ2)/2;

        Texture wallTexture = new Texture("dkelogosmaller.png");
        wallTexture.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        wallTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);

        Texture wallTexture2 = new Texture("umlogo.png");
        wallTexture2.setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat);
        wallTexture2.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
        Material material2 = new Material(TextureAttribute.createDiffuse(wallTexture2));


        ModelBuilder wallBuilder;
        wallBuilder = new ModelBuilder();
        Model wall1 = wallBuilder.createBox(0.0001f,height,terrainZ2-terrainZ1,new Material(material2),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        ModelInstance wallInstance1 = new ModelInstance(wall1,terrainX2,h(x,y)+height/2.5f,depth);

        Model wall2 = wallBuilder.createBox(0.0001f,height,terrainZ2-terrainZ1,new Material(material2),VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        ModelInstance wallInstance2 = new ModelInstance(wall2,terrainX2,h(x,y)+height/2.5f,depth);

        modelBatch.render(wallInstance1);
        modelBatch.render(wallInstance2);
    }

    public void renderWallMaze(){
        ModelBuilder wallBuilder;
        wallBuilder = new ModelBuilder();
        for(int i=0; i<xvaluesW.length-1; i++) {
            double lengthX = xvaluesW[i+1] - xvaluesW[i];
            double lengthZ = zvaluesW[i+1] - zvaluesW[i];
            System.out.println(xvaluesW[i]);
            System.out.println(zvaluesW[i]);
                Model wall = wallBuilder.createBox((float)lengthX, 1.5F, (float) lengthZ,new Material(ColorAttribute.createDiffuse(Color.BLACK)),
                        VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
                ModelInstance model = new ModelInstance(wall);

                model.transform.setToTranslation((float) (lengthX/2 + xvaluesW[i]),h(xvaluesW[i],zvaluesW[i]), (float) (lengthZ/2 +zvaluesW[i]));
                wall3d.add(new ModelInstance(model));
            }
        }




    //Render the flag which is at the exact location of the hole
    public void renderFlag(float x, float y){
        Model flagBot = modelBuilder.createCylinder(0.05f,1,0.05f,5, new Material(ColorAttribute.createDiffuse(Color.YELLOW)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        Model flagTop = modelBuilder.createCone(0.1f,0.3f,0.1f,5, new Material(ColorAttribute.createDiffuse(Color.RED)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        ModelInstance flagInstanseTop = new ModelInstance(flagTop,x,h(x,y)+1f,y);
        ModelInstance flagInstanseBot = new ModelInstance(flagBot,x,h(x,y)+0.5f,y);
        flagInstanseTop.transform.setToTranslation(x-0.01f,h(x,y)+1f,y+0.1f).rotate(x,h(x,y)+0.5f,y+0.05f,90);
        modelBatch.render(flagInstanseTop,environment);
        modelBatch.render(flagInstanseBot,environment);
    }

    //Render the idle stick which is placed behind the ball
    public void renderStick(float x, float y, float z){
        stickBuilder = new ModelBuilder();
        stick = stickBuilder.createCylinder(0.05f,0.5f,0.05f,5, new Material(ColorAttribute.createDiffuse(Color.DARK_GRAY)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
        stickInstance = new ModelInstance(stick,x-0.2f,z+0.25f,y+0.1f);

        stickBuilderBot = new ModelBuilder();
        stickBot = stickBuilderBot.createBox(0.05f,0.05f,0.2f,new Material(ColorAttribute.createDiffuse(Color.DARK_GRAY)),
                VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
        stickInstanceBot = new ModelInstance(stickBot,x-0.2f, z+0.045f,y);

        timeSeconds2 +=Gdx.graphics.getDeltaTime();
        if(timeSeconds2 > 0.0016) {
            timeSeconds2 = 0;
            stickInstance.transform.rotate(0, 0, 1, angle);
            stickInstanceBot.transform.setToTranslation((float) (x - 0.05), z + 0.045f, y).rotate(0, 0, 1, angle);
        }

        modelBatch.render(stickInstanceBot,environment);
        modelBatch.render(stickInstance,environment);
    }

    //Render the second position of the stick when the ball is shot (it acts as an animation)
    public void renderStick1(float x, float y, float z){
        if(arrowFlag) {
            stickBuilder = new ModelBuilder();
            stick = stickBuilder.createCylinder(0.05f, 0.5f, 0.05f, 5, new Material(ColorAttribute.createDiffuse(Color.DARK_GRAY)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal);
            stickInstance = new ModelInstance(stick, x - 0.2f, z + 0.25f, y + 0.1f);

            stickBuilderBot = new ModelBuilder();
            stickBot = stickBuilderBot.createBox(0.05f, 0.05f, 0.2f, new Material(ColorAttribute.createDiffuse(Color.DARK_GRAY)),
                    VertexAttributes.Usage.Position | VertexAttributes.Usage.Normal | VertexAttributes.Usage.TextureCoordinates);
            stickInstanceBot = new ModelInstance(stickBot, x - 0.2f, z + 0.045f, y);
            modelBatch.render(stickInstanceBot, environment);
            modelBatch.render(stickInstance);
        }
    }

    /*
        The update() function is responsible for keeping all the input statements together and is run in
        the render() method 60 times per second to be able to check the inputs reliably.
     */
    public void update() {
        if(input.isKeyPressed(Input.Keys.ESCAPE)) {
            //Exit the program when the ESCAPE key is pressed
            if (!game && !bot) {
                clientthread.getClient().deletePlayer(playerid);
            }
            Gdx.app.exit();
            System.exit(-1);
        }
        if(input.isKeyPressed(Input.Keys.RIGHT)&&((game)||(!game&&!bot))) {
            //Move the arrow for the direction to the right when the RIGHT arrow is pressed
            double x1 = ballArrowX - ballX;
            double y1 = ballArrowY - ballY;

            double x2 = x1 * Math.cos(0.05) - y1 * Math.sin(0.05);
            double y2 = x1 * Math.sin(0.05) + y1 * Math.cos(0.05);

            float x22 = (float)x2;
            float y22 = (float)y2;

            ballArrowX = (float) (x22 + ballX);
            ballArrowY = (float) (y22 + ballY);
        }

        if(input.isKeyPressed(Input.Keys.LEFT)&&((game)||(!game&&!bot))) {
            //Move the arrow for the direction to the left when the LEFT arrow is pressed
            double x1 = ballArrowX - ballX;
            double y1 = ballArrowY - ballY;

            double x2 = x1 * Math.cos(0.05) + y1 * Math.sin(0.05);
            double y2 = - x1 * Math.sin(0.05) + y1 * Math.cos(0.05);

            float x22 = (float)x2;
            float y22 = (float)y2;

            ballArrowX = (float) (x22 + ballX);
            ballArrowY = (float) (y22 + ballY);
        }
        if(input.isKeyPressed(Input.Keys.UP)&&((game)||(!game&&!bot))) {
            //Increase the velocity of the next shot when pressing the UP arrow
            if(velocity<=4.9) {
                velocity += 0.1;
            }
        }
        if(input.isKeyJustPressed(Input.Keys.P)) {
            //Activate the music loop when P is pressed
            if(soundOn==false) {
                thread.start();
                soundOn = true;
            }
        }
        if(input.isKeyJustPressed(Input.Keys.G)) {
            //Activate the easter egg when the button G is pressed
            if(soundOn==false) {
                thread2.start();
                soundOn = true;
            }
        }
        if(input.isKeyPressed(Input.Keys.DOWN)&&((game)||(!game&&!bot))) {
            //Decrease the velocity of the next shot when pressing the DOWN arrow
            if(velocity>0.2) {
                velocity -= 0.1;
            }
        }

        if(input.isKeyJustPressed(Input.Keys.SPACE) && playFlag == false && game) {
            /*
                When SPACE is pressed and the ball is not moving, the velocities are put into the engine
                and the engine will calculate the new movement of the ball. The game logic flags are also
                reset and changed when pressing SPACE to make the game function accordingly.
             */
            sound.ball_hit();
            double velocityX = ballArrowX-ballX;
            double velocityY = ballArrowY-ballY;
            double length = Math.sqrt(velocityX*velocityX+velocityY*velocityY);
            speedX = velocity*velocityX/length;
            speedY = velocity*velocityY/length;
            engine.setVelocities(speedX,speedY);
            numShotsTaken++;
            playFlag = true;
            arrowFlag = false;
            ballcoordsX = engine.get_ball_coordinatesX();
            ballcoordsY = engine.get_ball_coordinatesY();
            index=-1;
            treeHitted = true;
            isInWater = false;
            hitWall = true;
        }
        if(input.isKeyJustPressed(Input.Keys.SPACE)&&((game)||(!game&&!bot))){
            /*
                When SPACE is pressed, the stick is rendered for the animation
             */
            System.out.println(12);
            renderStick((float) ballX, (float) ballY, ballZ);
        }
        if(input.isKeyJustPressed(Input.Keys.SPACE)&&!game&&!bot&&playFlag==false){
            /*
                When SPACE is pressed and the game is in simulator mode, visualise the next
                move of the input file.
             */
            //BELOW IS OLD SIMULATOR CODE
            // if(count<speedsX.length){
            //     engine.setVelocities(speedsX[count],speedsY[count]);
            //     count++;
            // }
            // numShotsTaken++;
            // playFlag = true;
            // arrowFlag = false;
            // ballcoordsX = engine.get_ball_coordinatesX();
            // ballcoordsY = engine.get_ball_coordinatesY();
            // index=-1;
            // treeHitted = true;
            // isInWater = false;

            //BELOW IS NEW NETWORK CODE
            sound.ball_hit();
            double velocityX = ballArrowX-ballX;
            double velocityY = ballArrowY-ballY;
            double length = Math.sqrt(velocityX*velocityX+velocityY*velocityY);
            speedX = velocity*velocityX/length;
            speedY = velocity*velocityY/length;
            engine.setVelocities(speedX,speedY);
            numShotsTaken++;
            playFlag = true;
            arrowFlag = false;
            ballcoordsX = engine.get_ball_coordinatesX();
            ballcoordsY = engine.get_ball_coordinatesY();
            index=-1;
            treeHitted = true;
            isInWater = false;
            hitWall = true;
            clientthread.getClient().updatePlayer(playerid);
        }

        if(input.isKeyJustPressed(Input.Keys.SPACE)&&!game&&bot){
            /*
                When SPACE is pressed and the game is in Bot mode, visualise the next
                move of the bot.
             */
            gameBot.makeMove();
            numShotsTaken++;
            playFlag = true;
            arrowFlag = false;
            ballcoordsX = engine.get_ball_coordinatesX();
            ballcoordsY = engine.get_ball_coordinatesY();
            index=-1;
            treeHitted = true;
            isInWater = false;
        }

        if(input.isKeyPressed(Input.Keys.R)) {
            //When the button R is pressed, close the game and bring the player back to the main menu
            if(!bot&&!game) {
                clientthread.getClient().deletePlayer(playerid);
            }
            Gdx.app.exit();
        }

        if(input.isKeyJustPressed(Input.Keys.B)) {
            if(ballCam) {
                ballCam=false;
            } else ballCam=true;
        }

    }

    @Override
    public boolean keyDown(int keycode) {
        return true;
    }


    @Override
    public boolean keyUp(int keycode) {
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }
    public Bot getBot(){
        return gameBot;
    }
    public int getNumOfShootsTaken(){
        return numShotsTaken;
    }

}
