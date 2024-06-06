# Design Class Model

![Design Class Model](img/DesignClassModel.png)

```plantuml
@startuml
class Weapon {
  #int attackDamage
  --
  .. Constructor ..
  + Weapon(String)
  .. Getter ..
  + int getAttackDamage()
  ..
  + void setEquipped()
}

class Armor {
  #int armorPoints
  --
  .. Constructor ..
  + Armor(String)
  .. Getter ..
  + int getArmorPoints()
  ..
  + void setEquipped()
}

class Item {
  #String name
  #String description
  #int weight
  #int quantity
  #boolean canUse
  #boolean canPickUp
  #boolean heals
  #boolean equipped
  #int stack
  #boolean canDiscard
  #boolean isArmor
  #boolean isWeapon
  .. Getter ..
  + String getName()
  + int getStack()
  + boolean getHeals()
  + String getDescription ()
  + int getWeight()
  .. 
  + boolean canDiscard()
  + boolean isArmor()
  + boolean isWeapon()
  + boolean isPickable()
  + boolean isEquipped()
  + void ItemCountSet(String)
  + void PickItem()
}

class HealItem {
  #int healingPower
  ..Constructor..
  + HealItem (String)
  ..Getter..
  + int getHealingPower()
}

class Game {
  # Jbutton submit
  # JTextField command
  # Jlabel log
  # Jlabel display
  # Jpanel bottomPanel
  # int mode
  # String Name
  # Player player
  ..Constructor..
  + Game(String, Int)
  ..
  + void newGame()
  + void load_game()
}

class Enemy {
  #int hp
  #int given_score
  #int mobTier
  #int maxHp
  #String Name
  #Random RANDOM 

  ..Constructor..
  + Enemy ()
  + Enemy (int)
  ..Getter..
  + String getName()
  + int getHealth()
  + int getMaxHp()
  + int getScore()
  + int getTier()
  ..
  + int attack(int)
  + void takeDamage()
}

class KeyItem {
  ..Constructor..
  + KeyItem (String)
}

class Room {
  #int tier
  #int id
  #ArrayList<Item> items
  #ArrayList<String> connectedRooms
  #ArrayList<Npc> npcs
  #boolean northLocked
  #boolean southLocked
  #boolean eastLocked
  #boolean westLocked
  ..Constructor..
  + Room ()
  + Room (int)
  ..Getter..
  + int getId()
  + ArrayList<Item> getItems()
  + ArrayList<Npc> getNpc()
  + int getTier()
  ..Setter..
  + void setItems (ArrayList<Item>)
  + void SetLocked (String)
  ..
  + boolean HasDirection (String)
  + void showItems(Jlabel)
  + Item isInRoom (String)
  + Npc isNpcInRoom (String)
  + void removeItem (Item)
  + void removeAllItem()
  + boolean isLocked (String)
  + void updateNpc (Npc)
}

class Player {
  #String name
  #int hp
  #int maxHp
  #int score
  #int weight
  #static int maxWeight
  #int attackDamage
  #int x
  #int y
  #boolean equippedWeapon
  #boolean equippedArmor
  #int prex
  #int prey
  #ArrayList<Item> inventory
  ..Constructor..
  Player (String, int, int, int)
  ..Getter..
  + String getName()
  + int getMaxHp()
  + int getHp()
  + int getScore()
  + int getWeight()
  + ArrayList<Item> getInventory()
  + int getX()
  + int getY()
  + int getPreX()
  + int getPreY()
  ..Setter..
  + void setName (String)
  + void setHp (int)
  + void setScore (int)
  + void setWeight (int)
  + void setAttackDamage (int)
  + void setInventory (ArrayList<Item>)
  + void setX (int)
  + void setY (int)
  + void setPreX (int)
  + void setPreY (int)
  ..
  + int attack()
  + void healHp (int)
  + void addScore (int)
  + void takeDamage (int)
  + void clearInventory()
  + void equipWeaponInSlot (Weapon)
  + void unequipWeaponInList (Weapon)
  + void equipArmorInSlot (Armor)
  + void unequipArmorInSlot (Armor)
  + void setCoordinates (String)
  + boolean hasMoved()
  + void teleport (String)
  + void moveBack()
  + void pickUp (Item)
  + void ShowInventory()
  + boolean isAlive()
  + boolean checkInventory (Item)
  + void discardItem (Item)
}

class Npc {
  -String name
  -boolean questCompleted
  ..Constructor..
  Npc (String)
  ..
  + String getName()
  + boolean isQuestCompleted()
  + void setQuestCompleted()
}

class Events {
  # static Room[][] dungeon
  # static String c
  # static final JButton submit
  # static boolean buttonPressed
  # JTextField test
  ..
  + static void initDungeon () 
  + static void setDungeon(Room[][])
  + static Room[][] getDungeon()
  + static boolean randomEncounter (int, Player, JLabel, JLabel, String, Boolean)
  + static void battle (Enemy, Player, JLabel, JLabel, String, Boolean)
  + static boolean gameEnd (Player, JLabel)
  + static boolean move (String, Player, Room, JLabel)
  + static void usePotion (HealItem, Player, JLabel)
  + static synchronized void gameLoop (Player, JLabel, JLabel, JTextField, JPanel)
  + static Npc Interaction (Npc, JLabel, JLabel)
  + static void GameIntro (JLabel)
}

class SaveFile {
  # PropertiesFileCredentialsProvider properties
  # AWSStaticCredentialsProvider credentialsProvider
  # AmazonS3 s3Client
  # String bucketName
  # String fileName
  # String filePath
  ..
  + static void save (Player, Room[][])
  + static void load (Player, Room[][])
  + static void loadNpc (JSONObject, Room[][])
  + static void itemSorter (ArrayList<Item>, JSONObject)
  ..Getter..
  + String getFileName()
  + String getFilePath()
  + String getBucketName()
  + AmazonS3 getS3Client()
  + static InputStream getInputStream (S3Object, saveFile)
}

Item *-- HealItem: contains
Item *-- KeyItem: contains
Item *-- Weapon: contains
Item *-- Armor: contains

Events -up-> Game: uses
Events -up-> Enemy: instantiates
Player -left-> Item : interacts with
Events --> Player : instantiates
Events *-right- Room : contains and instantiates
Events -up-> Npc : uses
Events --> Item : uses
Room -up-> Npc: instantiates
Room --> Item: instantiates
Events -left-> SaveFile: uses
@enduml
```
