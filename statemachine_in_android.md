# StateMachine in Android

## 1. Short Introduction

StateMachine is a hierarchical state machine, in which one state can have different sub-state and different 
sub-state have different functions. The StateMachine processed messages and can have states arranged hierarchically. 
For a message, different actions will be taken when system is in different states. The StateMachine can solve the problem 
of poor reading and inconvenient extension raised by enormous switch statements, make the whole structure more clear 
and make the system more flexible.

And the states in Android StateMachine is inherited, just like tree in data structure. If the current node(state) 
handle the message, the parent node will try to handle it. StateMachine is a typical application of state pattern.


## 2. Related Term Explanation

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
  
  ![](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/state_machine_Looper.png)
  
  The following is the role of every module.
  
  <b>State</b>:  
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
  <b>ProcessedMessageInfo</b>: The information of processed messages.
  <pre>
  public static class ProcessedMessageInfo 
  {
  　　private int what;                
  　　private State state;               
  　　private State orgState;          
  }
  </pre>
  <b>ProcessedMessages</b>: A list of log records including messages recently processed by the state machine.
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
  The two classes above have updated to <b>LogRec</b> and <b>LogRecords</b> but the roles are the same.
  
  <b>SmHandler</b>:  
  
  There are three inner classes.  
  <b>StateInfo</b>: Current State, its parentState and whether the state is active. The StateInfo is used to construct
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
  
  <b>HaltingState</b> and <b>QuittingState</b>  
  Both are subclass of State and are used to do some things when the state halt and quit. They are predefined for extension.
  
  <b>SmHandler</b> is the core of <b>StateMachine</b> for message handling and state changing, which runs in an unique thread.
  <b>SmHandler</b> has three functions:  
  - Construct hierarchical tree structure to preserve state
  - Construct StateStack of StateMachine and change state
  - Handle and dispatch messages  

  Next, we introduce the implement of the functions above.
  
  #### Construct Hierarchical Tree Structure
  
  Before constructing a statemachine, we should confirm all the states we have and the states should be assemble for 
  management. StateMachine provides a protected method `addState` for users to add state to statemachine,
  <pre>
  protected final void addState(State state, State parent) {
        mSmHandler.addState(state, parent);
  }
  </pre>
  SmHandler do the job actually:
  <pre>
  /****************************************************
  * state:　　　 State to be added
  * parent:  　　the parent of state
  ****************************************************/
  private final StateInfo addState(State state, State parent) 
  {
  　　StateInfo parentStateInfo = null;
  　　if (parent != null) {
  　　　　parentStateInfo = mStateInfo.get(parent);
  　　　　if (parentStateInfo == null) {
  　　　　　　parentStateInfo = addState(parent, null);
  　　　　}
  　　}
  
  　　StateInfo stateInfo = mStateInfo.get(state);
  　　if (stateInfo == null) {
  　　　　stateInfo = new StateInfo();
  　　　　mStateInfo.put(state, stateInfo);
  　　}
  
  　　if ((stateInfo.parentStateInfo != null) &&
  　　　　(stateInfo.parentStateInfo != parentStateInfo)) {
  　　　　　　throw new RuntimeException("state already added");
  　　}
  
  　　stateInfo.state = state;
  　　stateInfo.parentStateInfo = parentStateInfo;
  　　stateInfo.active = false;
  　　return stateInfo;
  }
  </pre>
  So, in SmHandler class, StateInfo packages state into a node to construct the tree structure:  
  `private HashMap<State, StateInfo> mStateInfo = new HashMap<State, StateInfo>();`
  
  For example, 
  <pre>
  SmHandle sm;
  　　sm.addState(S0,null);
  　　sm.addState(S1,S0);
  　　sm.addState(S2,S0);
  　　sm.addState(S3,S1);
  　　sm.addState(S4,S1);
  　　sm.addState(S5,S2);
  　　sm.addState(S6,S2);
  　　sm.addState(S7,S2);
  　　setInitialState(S4);      // set the initial state
  </pre>
  We can get the following structure:  
  ![](https://raw.githubusercontent.com/DixonShen/Repo4Pics/master/StateMachinePics/20170725142404.png)
  
  #### Construct StateStack and Change State
  After all the states are added into statemachine, we can start statemachine by invoke `start()`,
  <pre>
  public void start() 
  {
  　　　　/** Send the complete construction message */
  　　　　mSmHandler.completeConstruction();
  }
  
  //Complete the construction of the state machine.
  private final void completeConstruction()
  {
      // Calculate the max depth
      int maxDepth = 0;
      for (StateInfo si : mStateInfo.values()) {
             int depth = 0;
             for (StateInfo i = si; i != null; depth++) {
                    i = i.parentStateInfo;
             }
             if (maxDepth < depth) {
                    maxDepth = depth;
             }
      }
  
      //create State Stack
      mStateStack = new StateInfo[maxDepth];
      mTempStateStack = new StateInfo[maxDepth];
  
      // Store the StateInfo on this path into State Stack
      setupInitialStateStack();
  
      // Invoke The enter method of every State in the mStateStack to activate the State
      mIsConstructionCompleted = true;
      mMsg = obtainMessage(SM_INIT_CMD);
      invokeEnterMethods(0);
  
      //Perform any transitions requested by the enter methods
      performTransitions();      // change state
  }
  </pre>
  
  The StateStack is constructed based on the current state `mDestState`, if `mDestState` is S4, the state in StateStack is 
  S0-S1-S4. It stores the parent child relationships.
  
  StateMachine provide `transitionTo(IState destState)` to change state:
  <pre>
  protected final void transitionTo(IState destState) 
  {
  　　mSmHandler.transitionTo(destState);
  }
  
  private final void transitionTo(IState destState) 
  {
  　　// mDestState stores the current states
  　　mDestState = (State) destState;
  }
  </pre>
  The method above just change the mDestState and the StateStack needs to be changed.
  So the state change is not complete. After dispatching and handle the message, the statemachine update the 
  StateStack and transfer to next state by invoking `performTransitions()`:  
  <pre>
  // Do any transitions
  private synchronized void performTransitions() 
  {
  　　while (mDestState != null)
  　　{
  　　　　//find the closest active parent in the tree and store the unactive node
  　　　　StateInfo commonStateInfo = setupTempStateStackWithStatesToEnter(destState);
  
          // remove the node not on the current path(invoke exit method)
  　　　　invokeExitMethods(commonStateInfo);
  
          // add the current relation chain into StateStack
  　　　　int stateStackEnteringIndex = moveTempStateStackToStateStack();
  
          // activate the new added node
  　　　　invokeEnterMethods(stateStackEnteringIndex);
  
          // move the deffered message to the front toi handle first
  　　　　moveDeferredMessageAtFrontOfQueue();
  　　}
  }
  </pre>
  
  #### Dispatch and handle message
  
  SmHandler is the core of StateMachine, which is a Handler and runs in a unique thread.  
  After the statemachine starts, it can execute some command, such as receive message, dispatch message and process message.
  Let's take a look at how the `handleMessage` process messages.
  
  StateMachine provides `sendMessage` method for users to add message into message queue and the SmHandler handle the message. That is 
  related to the mechanism of Handler, not repeat here.
  
  `handleMessage` in SmHandler handle the messages:
  <pre>
  public final void handleMessage(Message msg) 
  {
  　　//process the message in current state
  　　processMsg(msg);
  
  　　//change state
  　　performTransitions();
  }
  
  private final void processMsg(Message msg)
  {
  　　//dispatch the message to the state
  　　StateInfo curStateInfo = mStateStack[mStateStackTopIndex];
  　　while (!curStateInfo.state.processMessage(msg))
  　　{
  　　　　//if the current state did not handle the message, the parent state take over
  　　　　curStateInfo = curStateInfo.parentStateInfo;
  　　　　if (curStateInfo == null){
  　　　　　　// the message can not be handled by the current chain
  　　　　}
  　　}
  }
  </pre>
  
  ## Usage&Example
  
  <pre>
  class Hsm1 extends StateMachine {  
      private static final String TAG = "hsm1";  
    
      public static final int CMD_1 = 1;  
      public static final int CMD_2 = 2;  
      public static final int CMD_3 = 3;  
      public static final int CMD_4 = 4;  
      public static final int CMD_5 = 5;  
    
      public static Hsm1 makeHsm1() {  
          Log.d(TAG, "makeHsm1 E");  
          Hsm1 sm = new Hsm1("hsm1");  
          sm.start();  
          Log.d(TAG, "makeHsm1 X");  
          return sm;  
      }  
    
      Hsm1(String name) {  
          super(name);  
          Log.d(TAG, "ctor E");  
    
          // Add states, use indentation to show hierarchy  
          addState(mP1);  
              addState(mS1, mP1);  
              addState(mS2, mP1);  
          addState(mP2);  
    
          // Set the initial state  
          setInitialState(mS1);  
          Log.d(TAG, "ctor X");  
      }  
    
      class P1 extends State {  
          @Override public void enter() {  
              Log.d(TAG, "mP1.enter");  
          }  
          @Override public boolean processMessage(Message message) {  
              boolean retVal;  
              Log.d(TAG, "mP1.processMessage what=" + message.what);  
              switch(message.what) {  
              case CMD_2:  
                  // CMD_2 will arrive in mS2 before CMD_3  
                  sendMessage(obtainMessage(CMD_3));  
                  deferMessage(message);  
                  transitionTo(mS2);  
                  retVal = HANDLED;  
                  break;  
              default:  
                  // Any message we don't understand in this state invokes unhandledMessage  
                  retVal = NOT_HANDLED;  
                  break;  
              }  
              return retVal;  
          }  
          @Override public void exit() {  
              Log.d(TAG, "mP1.exit");  
          }  
      }  
    
      class S1 extends State {  
          @Override public void enter() {  
              Log.d(TAG, "mS1.enter");  
          }  
          @Override public boolean processMessage(Message message) {  
              Log.d(TAG, "S1.processMessage what=" + message.what);  
              if (message.what == CMD_1) {  
                  // Transition to ourself to show that enter/exit is called  
                  transitionTo(mS1);  
                  return HANDLED;  
              } else {  
                  // Let parent process all other messages  
                  return NOT_HANDLED;  
              }  
          }  
          @Override public void exit() {  
              Log.d(TAG, "mS1.exit");  
          }  
      }  
    
      class S2 extends State {  
          @Override public void enter() {  
              Log.d(TAG, "mS2.enter");  
          }  
          @Override public boolean processMessage(Message message) {  
              boolean retVal;  
              Log.d(TAG, "mS2.processMessage what=" + message.what);  
              switch(message.what) {  
              case(CMD_2):  
                  sendMessage(obtainMessage(CMD_4));  
                  retVal = HANDLED;  
                  break;  
              case(CMD_3):  
                  deferMessage(message);  
                  transitionTo(mP2);  
                  retVal = HANDLED;  
                  break;  
              default:  
                  retVal = NOT_HANDLED;  
                  break;  
              }  
              return retVal;  
          }  
          @Override public void exit() {  
              Log.d(TAG, "mS2.exit");  
          }  
      }  
    
      class P2 extends State {  
          @Override public void enter() {  
              Log.d(TAG, "mP2.enter");  
              sendMessage(obtainMessage(CMD_5));  
          }  
          @Override public boolean processMessage(Message message) {  
              Log.d(TAG, "P2.processMessage what=" + message.what);  
              switch(message.what) {  
              case(CMD_3):  
                  break;  
              case(CMD_4):  
                  break;  
              case(CMD_5):  
                  transitionToHaltingState();  
                  break;  
              }  
              return HANDLED;  
          }  
          @Override public void exit() {  
              Log.d(TAG, "mP2.exit");  
          }  
      }  
    
      @Override  
      void onHalting() {  
          Log.d(TAG, "halting");  
          synchronized (this) {  
              this.notifyAll();  
          }  
      }  
    
      P1 mP1 = new P1();  
      S1 mS1 = new S1();  
      S2 mS2 = new S2();  
      P2 mP2 = new P2();  
  }  
   
  // If this is executed by sending two messages CMD_1 and CMD_2  
  // (Note the synchronize is only needed because we use hsm.wait()) 
  Hsm1 hsm = makeHsm1();  
  synchronize(hsm) {  
       hsm.sendMessage(obtainMessage(hsm.CMD_1));  
       hsm.sendMessage(obtainMessage(hsm.CMD_2));  
       try {  
            // wait for the messages to be handled  
            hsm.wait();  
       } catch (InterruptedException e) {  
            Log.e(TAG, "exception while waiting " + e.getMessage());  
       }  
  }  
    
    
  输出如下:  
  D/hsm1    ( 1999): makeHsm1 E  
  D/hsm1    ( 1999): ctor E  
  D/hsm1    ( 1999): ctor X  
  D/hsm1    ( 1999): mP1.enter  
  D/hsm1    ( 1999): mS1.enter  
  D/hsm1    ( 1999): makeHsm1 X  
    
  D/hsm1    ( 1999): mS1.processMessage what=1  
    
  D/hsm1    ( 1999): mS1.exit  
  D/hsm1    ( 1999): mS1.enter  
    
  D/hsm1    ( 1999): mS1.processMessage what=2  
  D/hsm1    ( 1999): mP1.processMessage what=2  
    
  D/hsm1    ( 1999): mS1.exit  
  D/hsm1    ( 1999): mS2.enter  
    
  D/hsm1    ( 1999): mS2.processMessage what=2  
  D/hsm1    ( 1999): mS2.processMessage what=3  
    
  D/hsm1    ( 1999): mS2.exit  
  D/hsm1    ( 1999): mP1.exit  
  D/hsm1    ( 1999): mP2.enter  
    
  D/hsm1    ( 1999): mP2.processMessage what=3  
  D/hsm1    ( 1999): mP2.processMessage what=4  
  D/hsm1    ( 1999): mP2.processMessage what=5  
  D/hsm1    ( 1999): mP2.exit  
  D/hsm1    ( 1999): halting  
  </pre>
  
  
  
  