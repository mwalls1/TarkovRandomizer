class Rigs{
  String isArmored;
  String name;
  Rigs(String name)//creates new rig with the rig's name
  {
    this.name = name;
  }
  String getName()//returns name
  {
    return name;
  }
  void setArmor(String isArmored)//sets the value of isArmored, which means if the rig provides the player armor or not
  {
    this.isArmored = isArmored;
  }
  boolean armored()//returns isArmored value
  {
    return isArmored.equals("True");
  }
}
