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
void setup()
{
  reader = createReader("gunsAndAmmo.txt");//creates buffered reader
  background(140);
  size(650,860);
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
void draw()
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
void mouseReleased()
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
