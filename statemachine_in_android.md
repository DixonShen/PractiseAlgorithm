# StateMachine in Android

## 1. Short Introduction

StateMachine is a hierarchical state machine, in which one state can have different sub-state and different 
sub-state have different functions. The StateMachine processed messages and can have states arranged hierarchically. 
For a message, different actions will be taken when system is in different states. The StateMachine can solve the problem 
of poor reading and inconvenient extension raised by enormous switch statements, make the whole structure more clear 
and make the system more flexible.

And the states in Android StateMachine is inherited, just like tree in data structure. If the current node(state) 
handle the message, the parent node will try to handle it. StateMachine is a typical application of state pattern.


## 2. Related terms explanation

### State Pattern

![Three states of water](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/three%20states%20of%20water.jpg)

In software system, some objects have several states like water and these states can convert to each other in some conditions.
The object have different functions in different states. To design these multi-states object better, we can use a design pattern called 
state pattern.

State Pattern allows that one object can change the functions when the state changes, which looks like the class of the object changed.

State Pattern is used to solve the problem of state conversions and different functions of complex objects in system.
State Pattern separate the states of one object from the object and package it into specific state class to achieve the flexible
change of states. The client does not need to care for the state conversion and the current state of object when handling objects.

![state pattern](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/state%20pattern%20structure.jpg)

## 3. Design&Mechanism

![StateMachine_UML](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/StateMachine_UML%E5%9B%BE.jpg)

  Context - StateMachine  
  State - State
  
  The constructors of StateMachine are all protected and the instantiation is in the subclasses
  <pre>
  protected StateMachine(String name) 
  {
  　　mSmThread = new HandlerThread(name);
  　　mSmThread.start();
  　　Looper looper = mSmThread.getLooper();
  　　initStateMachine(name, looper);
  }
  </pre>
  
  The following is the role of every module.
  
  State:  
  <pre>
  public class State implements IState
  {
  　　protected State() {}
  　　public void enter() {}
  　　public void exit() {}
  　　public boolean processMessage(Message msg) {}
  　　public String getName() {}
  }
  </pre>
  
  The base class of state. All the states in StateMachine is inherited from State.
  
  Three inner class of StateMachine  
  ProcessedMessageInfo: The information of processed messages.
  <pre>
  public static class ProcessedMessageInfo 
  {
  　　private int what;                
  　　private State state;               
  　　private State orgState;          
  }
  </pre>
  ProcessedMessages: A list of log records including messages recently processed by the state machine.
  <pre>
  private static class ProcessedMessages {
  　　private static final int DEFAULT_SIZE = 20;
  　　private Vector<ProcessedMessageInfo> mMessages = 
  　　　　new Vector<ProcessedMessageInfo>();
  　　private int mMaxSize = DEFAULT_SIZE;
  　　private int mOldestIndex = 0;
  　　private int mCount = 0;
  }
  </pre>
  The two classes above have updated to LogRec and LogRecords but the roles are the same.
  
  SmHandler:  
  
  There are three inner classes.  
  StateInfo: Current State, its parentState and whether the state is active. The StateInfo is used to construct
  the hierarchical tree model.
  <pre>
  private class StateInfo 
  {
  　　/** the  state */
  　　State state;
  
  　　/** The parent of this state, null if there is no parent */
  　　StateInfo parentStateInfo;
  
  　　/** True when the state has been entered and on the stack */
  　　boolean active;
  }
  </pre>
  
  HaltingState and QuittingState  
  Both are subclass of State and are used to do some things when the state halt and quit.
  
  
  