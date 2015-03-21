# JSimLife #

### [HOME PAGE](http://www.jsimlife.netsons.org/) ###

|![![](http://s14.postimage.org/ae317kba9/Start_splash_small.png)](http://s14.postimage.org/yga7rkw81/Start_splash.png) | ![![](http://s15.postimage.org/kh8eugo4r/Screenshot_v_2_0_b2_small.png)](http://s17.postimage.org/gcqg54dhb/Screenshot_v_2_0_b2.png)| ![![](http://s16.postimage.org/kumbdz8dx/watchzlifes_small.png)](http://s18.postimage.org/t4jbjlpah/watchzlifes.png)|
|:----------------------------------------------------------------------------------------------------------------------|:------------------------------------------------------------------------------------------------------------------------------------|:--------------------------------------------------------------------------------------------------------------------|

**JSimLife** is an advanced Biological Life simulation, written in Java. In JSimLife every life form is a cell. Every cell is a Sprite and it has a Brain Object (not yet implemented) and a Dna object.
Every Cell's aspect are stored in a Dna (Dna is a Vector of parameters). Cells are able to riproduce itself using the methods provides by Dna class.
I want to create several type of cells in order to riproduce a simple food chain.
The simulation use the [J2DGF package ](http://freshmeat.net/projects/j2dgf) to show sprites on a JPanel in a Window Form.
The user can change the Simulation Parameter, create new DNA, create new type of cells and save or load simulation.
See ToDo wiki for future feature.

## Getting Started ##
Download the latest version of JSimLife (you need the JRE\_6) and run it.
Create new Cell: ZLifes or Zredator, add seed and watch they evolution by clicking them and exploring Dna.
You can now save and load your simulation!

## How does it work ##
JSimLife is quite simple. Each Zlife (this is generic name of JSimLife life's form) pass through four state:

Bored: in this State, Zlife's energy isn't enough to reproduce itself but it is enough not to be hungry!

Horny: when zlife is horny, it is able to reproduce itself if it hits another horny zlife!

Hungry: when zlife's energy is too low, it is hungry and it moves searching for food;

Scared: when scared, Zlife simply changes its direction to avoid dangers;

Those four states are selected by zlifes in function of its own life's parameter which are stored in a data structure simulating DNA mechanism. When reproduction occour, parameters are infact merged with second Zlife's DNA's parameters. Altough, parameter values are edited by a random value to a quantity defined in the DNAParam.

The ZlifeDna contains 17 genes (v.0.1.7), controlling Cell life parameter:

  1. dnaParam: the Dna random parameter simulate Genetics mutation
  1. energy: actual energy of the cell
  1. maxEnergy: max energy for the cell
  1. hornyEnergy: the minimum energy needs to riproduce
  1. hungryEnergy: the energy below one the Cell is hungry
  1. lifeCycle: the number of lifeCycle before the cell Die.
  1. metabolism: the energy needs for every moving of the cell. this value is subtract to energy
  1. BoredSpeed: the speed when the cell is in BORED state
  1. riproductionEnergy: the energy cell spend to riproduce itself
  1. hungrySpeed: the speed when cell is hungry
  1. hornySpeed: the speed when cell is horny
  1. scarySpeed: the speed when cell is scary (NOT YET USED!)
  1. radius: the radius of the cell
  1. R: the red color's component of this cell
  1. G: the green color's component of this cell
  1. B: the blue color's component of this cell
  1. ageFactor: energy percent subtract to max energy for each life cycle cell spends

### EATING ###

In the current JSimLife's version we have two Zlifes types: Zlife and Zretador. Let we see how they eat.
Zlife
Zlife eates those little green dots which we can add simply clicking the "add Seed" button on JSimLife's toolbar or which appears when a Zlife dies.
When Zlife meets some food we have two possibilities:
If Zlife is hungry, it eats some seed, if it isn't, it stores seed position waiting for the moment when it is hungry, then it moves to that point.

### Zretador ###
Zretadors instead are carnivores. They hunt Zlifes when they are hungry. Hunting mechanism are quite simple, when an hungry Zretador is a certain distance from Zlife, it follows trying to hit the Zlife in order to eats it. Not ever the Zretador kills Zlife couse when it "bites" subtract some Zlife's energy, so if Zlife had enough energy, it doesn't die.

### ZETATRON: The Intelligent one ###

Zetatron are the new creatures present in the next JSimLife version. Unlike other Zlifes, Zetatron decides their state using a Neural Network provides by neuroph library. The Neural Network is a Multi Layer Perceptron Net with 10 neurons (2 input, 5 in the hide layer and 3 on output).
The 2 input parameters are the following Zlife's life parameters:
energy/hungryEnergy, energyHornyEnergy.
The net had trained to activate on output a single neuron in order to activate one from the following Zlife's state: Hungry, Horny, Bored.
Into Neural Net all of this is avaible using a certain neurons connection weight, this weight are stored into Zlife's Dna like any other parameter. So, when Zetatron reproducts itself, they are passed to its sons. In this way, while simulation going on, we can observe how weight are edited by the riproduction process, consequences is we'll observe a chenge in ZLife's behavior. For this reasons I advise you keep DnaParam value fairly.

## Development ##
<ul>
<li>Develops of 2D Sprite Engine; <b>Done</b> </li>
<li>Develops of a Collision Manager: <b>Done</b> </li>
<li>Develops of GUI (AWT based): <b>Done</b> </li>
<li>Develops of Dna model: <b>Done</b> </li>
<li>Develops of Brain Model neural network based: <b>Done</b> </li>
<li>Develops of Cell Model: <b>Done</b>  </li>
</ul>

---


## Join Project ##
If you want to Join project, contact me: tabuto83(AT)gmail(DOT)com

## About ##
Project's Home page [JLife](http://freshmeat.net/projects/jsimlife)