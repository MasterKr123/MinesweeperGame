# MinesweeperGame
Challenge PSL intership  
Jorge Eliecer Castaño Valencia  
Universidad Icesi - Ingenieria Telematica  
jorgeeliecercastao@gmail.com

This challenge consists of the classic game of search for mines based on command line where the board is constituted by:  
‘.’ : Unselected cell (can be uncovered or marked)  
‘-’ : Disable cell (a cell that can not be modified by the user)
‘*’ : Represents a mine  
‘P’ : Represents a flag, used when the user marks a cell (use your imagination, it’s a flag)  
‘1’ .. ‘9’ : Represents the number of adjacent mines to an specific cell  
‘ ’ (space) : Separates each column  


## Modo de juego  
* When you start the game you must enter the width, height and number of mines of the board. This with the format:  
```
width height numMines  
```  
**NOTE:** Note that each value is separated by a space, can not enter different values ​​or otherwise the game will stop and the number of mines must be less than the number of cells in the board.
  
  
* When the game is started you will be asked to enter the row, column and action to take. The action can be "U" to select a cell or "M" to mark it as a possible mine. This with the format:  
``` 
row column action  
```  
**NOTE:** Note that each value is separated by a space and can not enter different values ​​or otherwise the game will stop.  
  
**At the end of the game, the game notifies you if you win or lose.**



## Execution instructions   

**With IDE Eclipse:**  
1. Download the project.  
2. Open project with Eclipse  
3. Choose File -> Export and select Java -> Runnable JAR File.  
4. In command line run:  
```  
java -jar MinesWeeper.java  
```  

**With the command line:**

1. Download the project.
2. Situate yourself in the project directory.
3. To run the project in Windows or Linux you must:

* Have the java environment variable defined. If so, in CMD you should be able to execute the javac command. If not, go to note 1 for additional comments.

* Compile the main class MinesWeeper.java located in MinesweeperGame\src\control\MinesWeeper.java, with the command:  
```
$ javac MinesweeperGame\src\control\MinesWeeper.java
```

This will generate a file MinesWeeper.class

* Run said file with the command  
```
$ java MinesWeeper.class
```  

**Also, according to https://stackoverflow.com/questions/5441565/java-build-and-run-eclipse-project-from-command-line, you can:**
1. Run the project with simplified syntax looks like this:
java -cp <classpath> <main class> <args>  
  
where:  
<classpath>  the current directory /bin of the project  
<main class> MinesWeeper.class  
<args> ""  
     
       
       
 
## Additional comments
* The project was developed on a Windows 10 machine.
* The development IDE was Eclipse.
* The project runs in java.
* The JDK version is: jdk1.8.0_171.

**NOTE 1**  
You must download the JDK (in the clarifications this is the version used by my machine) and verify that the path to the jdk is in the environment variables of the system. For it:
- Go to the equipment directory
- Right click and properties
- Click on advanced system settings
- Click on environment variables
- Click on the PATH variable and edit
- In case the path to the JDK does not exist, you must add it. In my case, the road is C:\Program Files\Java\jdk1.8.0_171\bin.
