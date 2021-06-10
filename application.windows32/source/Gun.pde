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
  String getRandomAmmo(){//returns a random type of ammo from the given list
    return ammoList[(int)random(ammoList.length)];
  }
  String getName()//returns name
  {
    return name;
  }
  void setAmmo(String ammoType)// given the ammo type, the correct file is read, to create a list of differnet ammo types available for that gun
  {
    this.ammoType = ammoType;
    ammoList = loadStrings("A"+ammoType+".txt");
  }
}
