import java.util.Scanner; //imports scanner library
import java.util.Random; //imports random library
/* ***************************************
AUTHOR: Tobi Adebari
*** This is a program that simulates a dungeon.
The user will be presented with many different situations while being given options to deal
with them
****************************************/
class MiniProj
{
public static void main (String[] param)
{
dungeon1();
System.exit(0);
} // END main
public static void dungeon1()
{
int playerScore = 0;
int scoreAwarded = 0;
final String END = "Quit";
dungeon[][] dungeonArray = new dungeon[3][3];
player player1 = new player();
player1
player1
= setX(player1, 1);
= setY(player1, 1);
dungeonArray = createRooms(dungeonArray);
String direction = doorNavigation("\nNorth, East, South or West?");
String aInput = direction;
mainloop:
while(!(direction.equalsIgnoreCase(END) || aInput.equalsIgnoreCase(END)))
{
nav(player1, direction);
roomInfo(dungeonArray[getX(player1)][getY(player1)]);
aInput = inputA();
subloop:
while(! (aInput.equalsIgnoreCase(END)))
{
if(aInput.equalsIgnoreCase("Search"))
{
scoreAwarded = scoreAwarded + sMethod(aInput, scoreAwarded, dungeonArray[getX(player1)][getY(player1)]);
}
else if(aInput.equalsIgnoreCase("Fight"))
{
int death = rollDice(2);
if(death == 1 && getMonster(dungeonArray[getX(player1)][getY(player1)]))
{
gameOver();
break mainloop;
}
else
{
scoreAwarded = scoreAwarded + fMethod(aInput, scoreAwarded, dungeonArray[getX(player1)][getY(player1)]);
}
}
else if(aInput.equalsIgnoreCase("Walk"))
{
direction = doorNavigation("\nNorth, East, South or West?");
aInput = direction;
break subloop;
}
else
{
unsure();
}
aInput = inputA();
}
}playerScore = playerScore + scoreAwarded;
printScore(playerScore, scoreAwarded);
return;
}//END dungeon1
/* ***************************************
*
*
*sets the players location relatve to the position in the 2d array
*/
public static void nav(player n, String d)
{
if(d.equalsIgnoreCase("North"))
{
n = setX(n, 0);
n = setY(n, 1);
}
else if (d.equalsIgnoreCase("East"))
{
n = setX(n, 1);
n = setY(n, 0);
}
else if (d.equalsIgnoreCase("South"))
{
n = setX(n, 0);
n = setY(n, -1);
}
else if (d.equalsIgnoreCase("West"))
{
n = setX(n, -1);
n = setY(n, 0);
}
else
{
unsure();
}
} //END nav
/* ***************************************
*
*
*takes the players input for the direction and returns it as a string
*/
public static String doorNavigation(String fourDoors)
{
String inputDirection;
Scanner scanner = new Scanner(System.in);
//
System.out.println(fourDoors);
inputDirection = scanner.nextLine();
return inputDirection;
} // END doorNavigation
/* ***************************************
*
*
* Prints out the chosen rooms information to the terminal by using the getter methods
*/
public static void roomInfo(dungeon r)
{
System.out.println("\nYou are now in " +getDescription(r)+ ", where you see "+getItems(r)+"." );
if(r.monster)
{
System.out.println("There is a monster in this room");
}
else
{System.out.println("There is no monster in this room");
}
System.out.println("\nThe exit of this room "+ getExit(r)+ ".");
}
/* ***************************************
*
*
Series of action methods based on what action the player chooses to take
*/
///////////////////////
public static int sMethod(String c, int d, dungeon f)
{
if(!(f.items.equalsIgnoreCase("nothing")) )
{
aquired(f);
d = d + rollDice(6);
f = setItems(f, "nothing");
}
else
{
System.out.println("\nthere is nothing here");
}
return d;
}
public static int fMethod(String c, int d, dungeon f)
{
int died = rollDice(2);
if(getMonster(f))
{
System.out.println("\nYou killed the monster");
d = d + rollDice(6);
f = setMonster(f, false);
}
else
{
System.out.println("\nthere is no monster here");
}
return d;
}
/* ***************************************
*
*
* General method to return a random value from a specific dice roll
*/
public static int rollDice(int itemValue)
{
Random dice = new Random();
int dicethrow = dice.nextInt(itemValue) + 1;
return dicethrow;
}// END rollDice
/* ***************************************
*
*
* Prints what score the player is awarded and what they have overall
*/
public static void printScore(int pScore, int sAwarded)
{
System.out.println("\nYour score increased by " + sAwarded + ". Your new score is " + pScore);
return;
}// END printScore/* ***************************************
*
*
* Dedicated method to create different rooms in the dungeon
*/
//////////////////
public static dungeon[][] createRooms(dungeon[][] n)
{
n[1][1] = new dungeon();
n[1][1]
n[1][1]
n[1][1]
n[1][1]
=
=
=
=
setExit(n[1][1], "There is a north, south, east and west exit");
setDescription(n[1][1], "central room");
setItems(n[1][1], "nothing");
setMonster(n[1][1], false);
n[0][1] = new dungeon();
n[0][1]
n[0][1]
n[0][1]
n[0][1]
=
=
=
=
n[1][2]
n[1][2]
n[1][2]
n[1][2]
n[1][2]
setExit(n[0][1], "There is a north, south and east exit");
setDescription(n[0][1], "a bright green room, very calming");
setItems(n[0][1], "a leaf");
setMonster(n[0][1], false);
= new dungeon();
=
=
=
=
setExit(n[1][2], "There is a west, east and south exit");
setDescription(n[1][2], "a dark red room");
setItems(n[1][2], "a red spatula");
setMonster(n[1][2], true);
n[2][1] = new dungeon();
n[2][1]
n[2][1]
n[2][1]
n[2][1]
=
=
=
=
setExit(n[2][1], "Ther is a north, west and south exit");
setDescription(n[2][1], " a room full of vines and dead corpses");
setItems(n[2][1], "a pebble");
setMonster(n[2][1], true);
n[1][0] = new dungeon();
n[1][0]
n[1][0]
n[1][0]
n[1][0]
=
=
=
=
setExit(n[1][0], "There is a north, west and east exit");
setDescription(n[1][0], "an emplty dull room");
setItems(n[1][0], "spider silk");
setMonster(n[1][0], true);
n[0][2] = new dungeon();
n[0][2]
n[0][2]
n[0][2]
n[0][2]
=
=
=
=
setExit(n[0][2], "There is an east and south exit");
setDescription(n[0][2], "a large brown room");
setItems(n[0][2], "a potato");
setMonster(n[0][2], true);
n[2][2] = new dungeon();
n[2][2]
n[2][2]
n[2][2]
n[2][2]
=
=
=
=
setExit(n[2][2], "There is an west and south exit");
setDescription(n[2][2], "a tiny purple room");
setItems(n[2][2], "cheese");
setMonster(n[2][2], true);
n[0][0] = new dungeon();
n[0][0]
n[0][0]
n[0][0]
n[0][0]
=
=
=
=
setExit(n[0][0], "There is an east and north exit");
setDescription(n[0][0], "a small modern looking room");
setItems(n[0][0], "a sophisticated vase");
setMonster(n[0][0], false);
n[2][0] = new dungeon();
n[2][0]
n[2][0]
n[2][0]
n[2][0]
}
=
=
=
=
setExit(n[2][0], "There is an west and north exit");
setDescription(n[2][0], "a plain looking room");
setItems(n[2][0], "a plain looking sword");
setMonster(n[2][0], true);
return n;
//END createRooms
/* ***************************************
*
*
* Series of Getter Setter methods
*/
////////////////////////
public static String getExit(dungeon n)
{
return n.exit;
}public static String getDescription(dungeon n)
{
return n.description;
}
public static String getItems(dungeon n)
{
return n.items;
}
public static boolean getMonster(dungeon n)
{
return n.monster;
}
public static int getX(player n)
{
return n.player1x;
}
public static int getY(player n)
{
return n.player1y;
}
/////////////////////////
public static dungeon setExit(dungeon n, String ex)
{
n.exit = ex;
return n;
}
public static dungeon setDescription(dungeon n, String descr)
{
n.description = descr;
return n;
}
public static dungeon setItems(dungeon n, String ite)
{
n.items = ite;
return n;
}
public static dungeon setMonster(dungeon n, boolean mons)
{
n.monster = mons;
return n;
}
public static player setX(player n, int z)
{
n.player1x = n.player1x + z;
return n;
}
public static player setY(player n, int z)
{
n.player1y = n.player1y + z;
return n;
}
////////////////////////////////
/* ***************************************
*
*
* Takes the input for the action the player wants to take and returns as string
*/
public static String inputA()
{
String input;Scanner scanner = new Scanner(System.in);
System.out.println("\nWhat do you want to do?\nSearch\nFight\nWalk");
input = scanner.nextLine();
return input;
} //END inputA
/* ***************************************
*
*
* Prints what score the player is awarded and what they have overall
*/
public static void aquired(dungeon nAquired)
{
System.out.println("\nYou aquired "+ getItems(nAquired) +".");
} //END aquired
/* ***************************************
*
*
* Method for when input is unrecognized or not viable
*/
public static void unsure()
{
System.out.println("\nI am unsure what you mean");
} //END unsure
/* ***************************************
*
*
* Game over screen
*/
public static void gameOver()
{
System.out.println("\nYou died");
}
//END gameOver
} // END MiniProj
class player
{
int player1x;
int player1y;
}// END player
class dungeon
{
String exit;
String description;
String items;
boolean monster;
}// END dungeon
