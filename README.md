# SoftwareEngineeringProject-Text Based Game :european_castle:

Gruppo 9 (Simionato Matteo, Pizzolato Enrico, Piccolo Stefano, Dall'Ò Leonardo)
                                                                                                    
                                                           
                              #--------===#                                  
                           =-::::-+++**++==-=+#                              
                         *----++--===========+++*#                           
                       %===**=-==**##%@%#*****#*##%                          
                      *+**=-*@#*+#@@@@@@@@@@%***##%                          
                    *=----=#@@%**@@@@@@@@@@@%***#%                           
                 *++*****++%@@@%#@@@@@@@@@@#*###                             
               +----====--*@@@@@#@@@@@@@@@@*#                                
              #=------=+*#%###%@+@@@@@@@@@@##    %%%#*+=-==+++***            
             **#*****====+%*++++++++++#@@@@@#***##+++=*#*+========*          
          #*=-============+++++*=+*======+%@@%##%****##============+*        
      %+=-=*=----=======+===+=======-=========+*#*#*##***+*******##*++=+*#%  
     *----+**+==++***++=====================+*****%  #++*##===============*% 
    %*=-----================++++++===+++++++***##%        #=--=========-==*@ 
    **#*++++**###**++===+**+++++++*++++=+#%%%##**%         %++#  ###%+=---+  
    *=+**++++***++++++****+**##*********#**********##                 *==*  
    #===+*#**##******##***#**********************#%#*=%                         
    ##**=---=++*#***********######*****#####*###**##%                          
     *=--=======#**********#***+========++*****#                              
      #+*#%%++++   @%%%           %#++====+*%                                 
                                                                                                   
                                                                                
                                                                      

## Documentazione e istruzioni per l'utilizzo
Tutta la documentazione è consultabile sul branch [docs](https://github.com/TeoSimii/SoftwareEngineeringProject/tree/docs)

## Introduzione al gioco
Our project is a fantasy text-based game set in a dungeon where players navigate through rooms to collect items and tools for use in battles. These objects assist in advancing by either defeating enemies or trading with NPCs for key items. The game is won by defeating the final boss located in the dungeon's last room. Conversely, the game ends if the player's Health Points (HP) drop to zero during a fight. To prevent this, players can find healing items in various rooms to partially restore their HP.

The player starts in the upper left room of the dungeon grid, with the objective of reaching the boss room. Along the way, they must avoid losing HP and find key items to surpass locked doors. Designed as a text-based game, it allows players to request help at any moment by using the command "help," which provides a list of commands such as "inventory," "list" (to view objects and NPCs in the room), and "save." The "save" command is particularly important as it allows players to save their current game progress to AWS buckets, enabling them to quit and later resume their adventure from the last save point.

Throughout the journey, players can encounter various items (both pickable and non-pickable) that can be used as combat or healing items. Additionally, key items are used to unlock gates or trade with NPCs found in certain rooms. The layout of the grid, including blocked gates, items, and NPCs, is detailed in the file "Griglia progetto EdIdS.jpg."

![Dungeon Overview](img/DungeonOverview.png| width=100)

## Fonti esterne
Per la generazione delle Ascii art è stato utilizzato il sito: [AsciiArt](https://www.asciiart.eu/image-to-ascii)
