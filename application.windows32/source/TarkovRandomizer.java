import processing.core.*; 
import processing.data.*; 
import processing.event.*; 
import processing.opengl.*; 

import java.util.HashMap; 
import java.util.ArrayList; 
import java.io.File; 
import java.io.BufferedReader; 
import java.io.PrintWriter; 
import java.io.InputStream; 
import java.io.OutputStream; 
import java.io.IOException; 

public class TarkovRandomizer extends PApplet {

Button randomize;
String helmet = "";
String armor = "";
String rig = "";
String backpack = "";
String gun = "";
String map = "";
String ammo = "";
String tLevel = "";
String[] helmets;
String[] armors;
String[] rigs;
String[] backpacks;
String[] guns;
String[] maps;
int count = 0;//gun count
int rcount = 0;//rig count
int gSize = 0;//size of gun array
int rSize = 0;//size of rig array
ArrayList<Gun> gtypes = new ArrayList<Gun>();//list of gun types
ArrayList<Rigs> rtypes = new ArrayList<Rigs>();//list of rig types
BufferedReader reader;//reads lists
String line = "hello";//for the sake of making line not null
public void setup()
{
  reader = createReader("gunsAndAmmo.txt");//creates buffered reader
  background(140);
  
  randomize = new Button("Randomize", 480, 760, 150, 50);//creates
  helmets = loadStrings("helmets.txt");                //
  armors = loadStrings("armor.txt");                   //
  rigs = loadStrings("rigs.txt");                      //  Loads items that do not have special considerations
  backpacks = loadStrings("backpacks.txt");            //
  maps = loadStrings("maps.txt");                      //
  try{//Create buffered reader
    line = reader.readLine();
  } catch (IOException e){
    e.printStackTrace();
    line = null;
  }
  while(!(line == null))//Start reading the file
  {
    gtypes.add(new Gun(line));//add new gun to the array
    try{
      line = reader.readLine();
    } catch (IOException e){
      e.printStackTrace();
      line = null;
    }
    gtypes.get(count).setAmmo(line);//add ammo type to gun
    try{
      line = reader.readLine();
    } catch (IOException e){
      e.printStackTrace();
      line = null;
    }
    count++;//increase gun array count
  }
    reader = createReader("rigs.txt");//create reader using rigs file
    line = "hello";
    try{
      line = reader.readLine();
    } catch (IOException e){
      e.printStackTrace();
      line = null;
    }
    while(!(line == null))
    {
      rtypes.add(new Rigs(line));//add new rig
      try{
        line = reader.readLine();
      } catch (IOException e){
        e.printStackTrace();
        line = null;
      }
      rtypes.get(rcount).setArmor(line);//set rig armored value
      try{
        line = reader.readLine();
      } catch (IOException e){
        e.printStackTrace();
        line = null;
      }
      rcount++;//increase rig size
  }
  gSize = gtypes.size();
  rSize = rtypes.size();
}
public void draw()
{
  clear();
  randomize.Draw();
  fill(255);
  textSize(24);
  text("Helmet: ",100,100);
  text(helmet, 300, 100);
  text("Armor: ", 100, 200);
  text(armor, 300, 200);
  text("Rig: ", 100, 300);
  text(rig, 300, 300);
  text("Backpack: ", 100, 400);          //Basically just draws the text and button
  text(backpack, 300, 400);
  text("Gun: ", 100, 500);
  text(gun, 300, 500);
  text("Ammo: ", 100, 600);
  text(ammo, 300, 600);
  text("Trader Lvl: ", 100, 700);
  text(tLevel, 300, 700);
  text("Map: ", 100, 800);
  text(map, 300, 800);
  
    
}
public void mouseReleased()
{
  if(randomize.MouseIsOver())//checks to see if the mouse button was released, and if the button was being hovered over
  {
    helmet = helmets[(int)random(helmets.length)];//
    armor = armors[(int)random(armors.length)];   // Randomly selects these types of gear from the list
    int rNum = (int)random(rSize);                //  
    Rigs r = rtypes.get(rNum);                    //
    if(r.armored())//Checks to see if the rig is armored, and sets armor to none
    {
      rig = r.getName();
      armor = "None";
    }
    else//otherwise just grab rig name and keep armor the name
    rig = r.getName();
    backpack = backpacks[(int)random(backpacks.length)];//
    tLevel = ""+ ((int)random(4)+1);                    //
    int gNum = (int)random(gSize);                      //
    gun = gtypes.get(gNum).getName();                   // Grabs rest of the random data from implemented lists
    ammo = gtypes.get(gNum).getRandomAmmo();            //
    map = maps[(int)random(maps.length)];               //
  }
    
}
class Button {
  String label;
  float x;    // top left corner x position
  float y;    // top left corner y position
  float w;    // width of button
  float h;    // height of button
  
  Button(String labelB, float xpos, float ypos, float widthB, float heightB) {//creates a button with the given attributes
    label = labelB;
    x = xpos;
    y = ypos;
    w = widthB;
    h = heightB;
  }
  
  public void Draw() {//draw's a rectangle with the given size and label
    fill(218);
    stroke(141);
    rect(x, y, w, h, 10);
    textAlign(CENTER, CENTER);
    fill(0);
    text(label, x + (w / 2), y + (h / 2));
  }
  
  public boolean MouseIsOver() {//checks to see if the mouse was clicked while over the button
    if (mouseX > x && mouseX < (x + w) && mouseY > y && mouseY < (y + h)) {
      return true;
    }
    return false;
  }
}
class Gun{
  String ammoType;
  String[] ammoList;
  String name;
  Gun(String name)//creates a gun object with the given anme
  {
    this.name = name;
  }
  Gun(String name, String ammoType)//overloaded constructor, not utilized at the moment
  {
    this.name = name;
    this.ammoType = ammoType;
    ammoList = loadStrings("A"+ammoType+".txt");
  }
  public String getRandomAmmo(){//returns a random type of ammo from the given list
    return ammoList[(int)random(ammoList.length)];
  }
  public String getName()//returns name
  {
    return name;
  }
  public void setAmmo(String ammoType)// given the ammo type, the correct file is read, to create a list of differnet ammo types available for that gun
  {
    this.ammoType = ammoType;
    ammoList = loadStrings("A"+ammoType+".txt");
  }
}
class Rigs{
  String isArmored;
  String name;
  Rigs(String name)//creates new rig with the rig's name
  {
    this.name = name;
  }
  public String getName()//returns name
  {
    return name;
  }
  public void setArmor(String isArmored)//sets the value of isArmored, which means if the rig provides the player armor or not
  {
    this.isArmored = isArmored;
  }
  public boolean armored()//returns isArmored value
  {
    return isArmored.equals("True");
  }
}
  public void settings() {  size(650,860); }
  static public void main(String[] passedArgs) {
    String[] appletArgs = new String[] { "TarkovRandomizer" };
    if (passedArgs != null) {
      PApplet.main(concat(appletArgs, passedArgs));
    } else {
      PApplet.main(appletArgs);
    }
  }
}
