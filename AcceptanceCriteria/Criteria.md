# User Stories
1. Save the game
2. Inspect Objects
3. Take Objects
4. Move between roms
5. Fight Enemies
6. Interact with Npcs
7. Access the inventory
8. Use Objects
9. Equip Objects
10. Show Score
11. Load Game


### Acceptance Criteria 1
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm playing the game</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the command "Save"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The system saves my progress</td>
  </tr>
</table>

#### Extended Criteria
* While the player is in the room and the system asks him the action to perform
* When the player digits the command "Save"
* The system saves the current status of the player and every room in the game
* Display the message "Saving complete"


### Acceptance Criteria 2
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I want to inspect an object</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the command "Inspect object_name"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The system shows me the description of the item</td>
  </tr>
</table>

#### Extended Criteria
* While the player is in the room and the system asks him the action to perform
* When the player digit "inspect object_name"
* Display the description of that item
* Display an error message if the item is not in the room
* Display an error message if the object name is missing in the command

### Acceptance Criteria 3
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I want to take an item</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the command "Take Object_name"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The the item gets added to my inventory</td>
  </tr>
</table>

#### Extended Criteria
* While the player is in the room and the system asks him the action to perform
* When the player digit "Take object_name"
* Add the item to the inventory of the player
* Increase the player weight
* Display error message if the item is not in the room
* Display error message if the player has reached max weight
* Display error message if the item cannot be picked up

### Acceptance Criteria 4
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I want to move to another room</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the a cardinal direction between "north/south/east/west"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The character moves to the room in that direction</td>
  </tr>
</table>

#### Extended Criteria
* While the player is in the room and the system asks him the action to perform
* When the player digits one of the four cardinal direction:  "north/south/east/west"
* The player moves to the room in that direction
* Display error message if there isn't a door in that direction
* Display error message if the door is locked

### Acceptance Criteria 5
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm moving to a room</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>There is an enemy</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>I'm fighting him</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I defeat him</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>My score increases</td>
  </tr>
</table>

#### Extended Criteria
* When the player encounters an enemy and decides to fight him
* When the player defeats the enemy by bringing his hp to 0
* A certain score based on the difficulty of the enemy gets added to the player score
* The score doesn't get added if the player runs away from the enemy
* The game ends if the player health reaches 0

### Acceptance Criteria 6
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>There is a Npc</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the command "Talk Npc_name"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The character interacts with the npc</td>
  </tr>
</table>

### Acceptance Criteria 7
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm playing the game</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the command "Save"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The system saves my progress</td>
  </tr>
</table>

### Acceptance Criteria 8
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm playing the game</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the command "Save"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The system saves my progress</td>
  </tr>
</table>

### Acceptance Criteria 9
<table>
  <tr>
    <td><b>Given</b>
    <td>I'm playing the game</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I'm in a room</td>
  </tr>
  <tr>
    <td><b>When</b></td>
    <td>The games asks me what to do</td>
  </tr>
  <tr>
    <td><b>And</b></td>
    <td>I digit the command "Save"</td>
  </tr>
  <tr>
    <td><b>Then</b></td>
    <td>The system saves my progress</td>
  </tr>
</table>
