
# Crazy Putting - Group 04

We have created a program which is able to simulate a 3D golf game with 2D physics. This program uses its own custom made physics engine for computing the trajectory of the ball. 
This physics engine can use six different ODE solvers for computing the physics. This program also contains five different bots which are able to complete the game.


![](https://media.discordapp.net/attachments/945742228108742668/976104158572728381/Screenshot_2022-05-17_at_14.49.20.png?width=1185&height=670)


## Run Locally

Clone the project

```bash
  git clone https://github.com/UM-KEN1300/Group-04
```

Go to the project directory and open the entire group-04 folder in your preferred IDE.

Next, go to the following folder.
```bash
   group-04\3dcrazyputting\desktop\src\com\crazyputting3d
```
Finally, run the main of the class Main.java to run the program.
## Features

- Settings menu to change X and Y  positions of the hole and ball
- Drop down menu to change which bot will be used
- Drop down menu to change which solver will be utilized
- Three launch modes (Game, Simulator, Bot)


## Modes
- Mode "Game": when the program is launched as "Game" the user will be able to play the game as a normal golf game.

- Mode "Simulator": when the program is launched as "Simulator" the program will take the data from the file "input2.txt" and visualize the inputs. This file can be found and editted in the following folder.
```bash
   group-04\3dcrazyputting\assets
```
- Mode "Bot": the bot which is currently selected will solve the hole in one problem once the spacebar is pressed. You can change the currently selected bot by going to the main menu and selecting a different one from the drop down menu.

## Solvers and bots
In this program you can choose from the following different solvers.
- Eulers method
- Runge Kutta 2
- Runge Kutta 4
- Verlets method
- Dormand Prince
- Predictor corrector (Adams Moulton & Adams Bashforth)

In this program you can also choose from the following bots.
- Hill climbing bot
- Newton raphson bot
- Rule based bot
- Random based bot
- Brute force bot
## Authors

- [@Casper Brocheler](https://github.com/casperbroch)
- [@Gabrijel Radovcic](https://github.com/gabrijelradovcic)
- [@Guilherme Sequeira](https://github.com/sequeiragui)
- [@Arjen van Gelder](https://github.com/ArjenvanGelder)
- [@Trinh Le](https://github.com/khanhtrinh820)
- [@Elza Strazda](https://github.com/elzastrazda)
- [@Alina Gavrish](https://github.com/AlinaGavrish)

