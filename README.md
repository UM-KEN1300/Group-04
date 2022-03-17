# Group-04

Crazy Putting - Group 04 - Golf Simulator

Aim of this project is to complete phase 1 of Project 1-2.

This program aims to simulate real-life motion of a ball in a golf course.
The following is done by implementing a physics engine that handles any
Mathematical function as the terrain and a 4-dimensional state vector to
represent the state of the ball at any given position.

It is possible to have both a game-play and a simulation.
Game-play includes keyboard listeners for different cameras and game moves.
Simulation is done by reading different velocity inputs from a file and the moves
will then be animated and shown by the UI.

Challenges faced include the creation of the UI, physics engine, game engine
and a Input file reader.

Launch - GAME :
Step 1: Copy the path from your personal computer which leads to the ´assets´ folder inside
        the project folder. You find this folder by going to:
        3dcrazyputting > assets

Step 2: When this is copied, go to Search.java, Sounds.java, Menu.java and VictoryScreen.java. These
        files are found by going to the following folders:
        3dcrazyputting > desktop> src > com > crazyputting3d >

Step 4: For Search.java: scroll down until you reach the run() method. The first line
        should look like this,
        FileReader read=new FileReader("your\\path\\here\\"+filename);
        Then paste the path at the correct location (do not remove the "" and +filename).

Step 5: For Sounds.java: scroll down until you are in the class, the first line is,
        private String filePath = "your\\path\\here\\";
        Then paste the same path at this location.

Step 6: For Menu.java scroll down until you are at line 41, it should look like this,
        img = new ImageIcon("your\\path\\here\\title6.jpeg");
        Then paste the path at this location.

Step 7: For VictoryScreen.java scroll down until you are at line 56, it should look like this,
        img = new ImageIcon("your\\path\\here\\victory.png");
        Then paste the path at this location.

Step 8: Next, copy the path from the crazyputting3d folder which contains all the .java files.
        When this path is copied, go to the BigCheat.java file. When this file is opened,
        go to the 11th line, which should look like this,
        try (PrintWriter writer = new PrintWriter(new File("your\\path\\here\\"))) {
        Then, once again (and for the last time) paste the path at this location between the "".

Step 9: Once this is done, open the file Menu.java which is located in the same folder as
        the other files. Then, simply run this file by going to the bottom and find its main.
        When the Menu.java file is opened, you will be greeted by a main menu screen.
        Three options will be presented, to play the game manually, press the button 'Game'.


Launch - SIMULATOR :
Step 1: Go to 3dcrazyputting > assets
        Then, find the input2.txt file and insert the desired velocities. They should look like this:
        v0x1 = 2
        v0y1 = 1
        v0x2 = 3
        v0y2 = 1
Step 2: Go to this README file and go to Launch-Game. Then execute Step 1-8. When this is done,
        open the file Menu.java. Then run this file by going to the bottom and executing the
        main method. After this, you will be greeted with the main menu screen. To play the game
        as the simulator press the button called 'Simulator'.\

Launch - Experiments
Step 1: For experiments.java scroll down until you are at line 10, it should look like this,
        try (PrintWriter writer = new PrintWriter(new File("your\\path\\here\\experiment.txt"))) {
        Then paste the path of the asset folder (step 1 of launch GAME) at this location.

Step 2: Run the main method of the experiments.java class.

Step 3: Finally, run the main method of physicsEngineForExperiments.java

Created by: Casper Bröcheler, Guilherme Pereira Sequeira, Alina Gavrish, Arjen van Gelder, Trinh Le, Gabrijel Radovčić, Elza Strazda.
