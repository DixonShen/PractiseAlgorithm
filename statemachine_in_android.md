# StateMachine in Android

## 1. Short Introduction

StateMachine is a hierarchical state machine, in which one state can have different sub-state and different 
sub-state have different functions. The StateMachine processed messages and can have states arranged hierarchically. 
For a message, different actions will be taken when system is in different states.
And the states in Android StateMachine is inherited, just like tree in data structure. If the current node(state) 
handle the message, the parent node will try to handle it. StateMachine is a typical example of state pattern.


## 2. Related terms explanation

### State Pattern

![水的三种状态](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/three%20states%20of%20water.jpg)

In software system, some objects have several states like water and these states can convert to each other in some conditions.
The object have different functions in different states. To design these multi-states object better, we can use a design pattern called 
state pattern.

State Pattern allows that one object can change the functions when the state changes, which looks like the class of the object changed.

State Pattern is used to solve the problem of state conversions and different functions of complex objects in system.
State Pattern separate the states of one object from the object and package it into specific state class to achieve the flexible
change of states. The client does not need to care for the state conversion and the current state of object when handling objects.

![state pattern](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/state%20pattern%20structure.jpg)

## 3. Design&Mechanism

![StateMachine类图](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/StateMachine_UML%E5%9B%BE.jpg)

  Context - StateMachine  
  State   - State