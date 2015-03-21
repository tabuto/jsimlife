README

JSimLife v.0.2.0.b1

Homepage: http://tabuto.github.io/jsimlife

Freshmeat: http://freshmeat.net/projects/jsimlife

Download Latest Release: https://github.com/tabuto/jsimlife/releases/download/v.2.0.2/JSimLife.jar


#1 Install
To run this application you need the Java Runtime Enviroment (JRE) v.6. 
You can download it at http://www.java.com/it/download/index.jsp.
Java is a cross platform language, so you can run this software in every system with JRE.

If  Java Runtime Enviroment (JRE) is already installed on your machine simply extract JSimLife.jar file in a directory and double clicking on the jar file, altough you can execute:

java -jar JSimLife.jar


#2 USER GUIDE
JSimLife it's a simple life simulation, that implemented a jenetics reproduction of a simple life form called Zlife, Zretador and Zetatron.
You can add they by clicing the "Add A Cell" button on the toolbar. 
So you can edit the genetic parameter values and observe the evolution.

The Zlife and Zretador's Dna contains infact 17 genes controlling several life parameter:
   1. dnaParam: the Dna random parameter simulate Genetics mutation: how change genes when Zlifes are reproducing?
   2. energy: actual Zlife's energy 
   3. maxEnergy: max Zlife's energy
   4. hornyEnergy: the minimum energy needs to riproduce
   5. hungryEnergy: the energy below which Zlifes are hungry
   6. lifeCycle: the number of lifeCycle before the Zlife Die. When Zlife's change his state it spends a lifeCycle! 
   7. metabolism: the energy needs for each Zlife's move. this value is subtract to energy
   8. BoredSpeed: the speed when the cell is in BORED state
   9. riproductionEnergy: the energy zlife's spend to riproduce itself
  10. hungrySpeed: the speed when cell is hungry
  11. hornySpeed: the speed when cell is horny
  12. scarySpeed: the speed when cell is scary (NOT YET USED!)
  13. radius: Zlife's radius size
  14. R: the red color's component of this cell
  15. G: the green color's component of this cell
  16. B: the blue color's component of this cell
  17. ageFactor: energy percent subtract to max energy for each life cycle cell spends 

Each Zlife's (but also Zretador) live its life using 4 life state:
    * BORED: when bored the cell moving random until his energy is below the HungryEnergy?. If cell meet some seed, store its position for future meal!! 
    * HUNGRY: when hungry the cell move to seed position (if exist) or moving randomly in search of it! After meal the cell's energy are max and it is ready to riproduce itself; 
    * HORNY: when cell is horny, it moves fast in search of onether Horny-Cell. If they collide another life is born! 

Every change state mean a lifeCycle spends! When cells finished his lifeCycle or if his energy is below zero, cell die. 

When a ZLifes die, it changes in Seed.

The Zredator represent the predator of ZLifes. When they hungry, they hunting Zilfes for eating.

You can also select a Zlife's and controlling its actual state and its Dna.
You can save and load simulation, but remember that the maximum number of ZLife's presents on game at same time is limited to 500!
You can watch in real time how evolves Game's parameter using the "CHART" button.
You can edit some Game parameters as the background color of playfield, or the playfield dimension by clicking on the preference item in JSimLife menu.
The new Zlife's type Zetatron is a Zretador's predator. It uses a neural network with 8 neurons to select its own current state.
Zetatron's dna is greater than zlife's one because I've adding the neuron's weights as a genes in order to allows the Zetatron brain's evolution!

#3 Developments
An help should be great!!! 
If you want to join the project contact me at (tabuto83[at]gmail[dot]com]
N.B. This game use a JDOM library: youu need to import it in your project if you want recompile it!

#4 About
JSimLife is currently at version 0.2.0, is developed by tabuto83 under MIT license. 
Visit JSimLife's Home Page at: http://tabuto.github.io/jsimlife
