package com.idsproject.SaveFile;
// Librerie AmazonAWS
import com.amazonaws.AmazonClientException;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.PropertiesFileCredentialsProvider;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
// Librerie Interne
import com.idsproject.Armor.Armor;
import com.idsproject.Items.HealItem;
import com.idsproject.Items.Item;
import com.idsproject.Items.KeyItem;
import com.idsproject.Weapons.Weapon;
import com.idsproject.Player.Player;
import com.idsproject.Rooms.Room;
// Libreria Commons-IO
import org.apache.commons.io.IOUtils;
//Librerie Gson
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
// Librerie org.json
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
// Librerie java
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class saveFile
{
    // Creazione del Client tramite credenziali
    private final PropertiesFileCredentialsProvider properties = new PropertiesFileCredentialsProvider("..\\IDS_Project\\src\\main\\resources\\aws-credentials.properties" );
    protected AWSStaticCredentialsProvider credentialsProvider = new AWSStaticCredentialsProvider(properties.getCredentials());
    protected AmazonS3 s3Client = AmazonS3ClientBuilder.standard()
                .withCredentials(credentialsProvider)
                .withRegion(Regions.EU_NORTH_1)
                .build();

    protected  String bucketName="software-engineering-elements-project-save-files";
    protected  String fileName="saveFile-1.json";
    protected  String filePath="..\\IDS_Project\\"+ fileName;


    public static void save(Player player, Room[][] dungeon){
        saveFile sf =new saveFile();
        // Mi permette di stampare nel file json le arraylist, il comando setPrettyPrinting() e' puramente estetico per riuscire a comprendere meglio il json
        Gson gson = new GsonBuilder().setPrettyPrinting().create();

        //Intestazione manuale di un "oggetto" json
        String jsonPlayer = "{\"player\":{\n   \"score\":"+player.getScore()
                +",\n   \"name\": \""       +player.getName()+"\""
                +",\n   \"weight\":"        +player.getWeight()
                +",\n   \"HP\":"            +player.getHp()
                +",\n   \"maxHP\":"         +player.getMaxHp()
                +",\n   \"attackDamage\":"  +player.getAttackDamage()
                +",\n   \"x\":"             +player.getX()
                +",\n   \"y\":"             +player.getY()
                +",\n   \"prex\":"          +player.getPreX()
                +",\n   \"prey\":"          +player.getPreY()
                +"\n }, \n";

        //Divisione in sotto array all'interno del json
        String jsonInventory ="\"inventory\":"+ gson.toJson(player.getInventory())+", \n";
        String headerItem="{\"Item\":\n";
        String headerNpc="{\"Npc\":\n";

        //Stampa all'interno del file tutti i dati utili
        try (PrintWriter out = new PrintWriter(sf.getFilePath())) {
            out.println(jsonPlayer);
            out.println(jsonInventory);
            for (int i = 0; i < 4; i++) {
                for (int j = 0; j < 4; j++) {
                    // Dungeon e' un arraylist di Room
                    // Ogni Room ha al suo interno due arraylist npc e item e le salvo come due array all'interno dell'array Room[i][j] 
                    String headerRoom="\"Room["+i+"]["+j+"]\":[\n";
                    String jsonItem = gson.toJson(dungeon[i][j].getItems())+"},\n";
                    String jsonNpc;
                    // Fino all'ultima stanza ha bisogno di mettere una virgola per permettere di aggiungere una stanza dopo
                    if(i == 3 && j == 3) {
                        jsonNpc = gson.toJson(dungeon[i][j].getNpc()) + "}]";
                    }
                    else{
                        jsonNpc = gson.toJson(dungeon[i][j].getNpc()) + "}],";
                    }
                    // Stampa le stringe all'interno del file
                    out.println(headerRoom+headerItem+jsonItem);
                    out.println(headerNpc+jsonNpc);
                }
            }
            out.println("}");
        }
        // Catch nel caso in cui il file non esista
        catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
        // Salvataggio in AWS su bucket s3
        try {
            sf.getS3Client().putObject(sf.getBucketName(),sf.getFileName(), new File(sf.getFilePath()));
        }
        // Gestione dell'eccezione nel caso in cui il client non potesse accedere al bucket
        catch (AmazonClientException e) {
                    System.err.println(e.getMessage());
                    System.exit(1);
        }
    }

    public static void load(Player player, Room[][] dungeon){
        saveFile sf =new saveFile();
        try {
            // Richiedo al bucket che mi salvi il file richiesto all'interno dell'oggetto object
            S3Object object = sf.getS3Client().getObject(new GetObjectRequest(sf.getBucketName(), sf.getFileName()));
            // InputStream per stampare l'oggetto nel file e salvare il testo all'interno dell'InputStream
            InputStream is = getInputStream(object, sf);
            // Conversione in stringa del file json
            String jsonTxt = IOUtils.toString(is, StandardCharsets.UTF_8);
            // Trasformo la stringa in un JSONObject
            JSONObject jsonObject = new JSONObject(jsonTxt);
            // Cambio i valori di player con quelli salvati nel file json
                player.setScore(jsonObject.getJSONObject("player").getInt("score"));
                player.setName(jsonObject.getJSONObject("player").getString("name"));
                player.setHp(jsonObject.getJSONObject("player").getInt("HP"));
                player.setWeight(jsonObject.getJSONObject("player").getInt("weight"));
                player.setAttackDamage(jsonObject.getJSONObject("player").getInt("attackDamage"));
                player.setX(jsonObject.getJSONObject("player").getInt("x"));
                player.setY(jsonObject.getJSONObject("player").getInt("y"));
                player.setPreX(jsonObject.getJSONObject("player").getInt("prex"));
                player.setPreY(jsonObject.getJSONObject("player").getInt("prey"));

            // Salvo l'array inventory che è dentro al json nell'oggeto JSONArray
            JSONArray arr = jsonObject.getJSONArray("inventory");
            // Creo un ArrayList temporanea per poi utilizzare la funzione setInventory
            ArrayList<Item> items = new ArrayList<>();
            try{
                // Svuoto l'inventario del player
                player.clearInventory();
                for (int i = 0; i < arr.length(); i++) {
                     JSONObject item = arr.getJSONObject(i);
                     // Funzione che controlla che tipo di oggetto deve essere e lo mette all'interno dell'ArrayList items
                     itemSorter(items, item);
                }
                player.setInventory(items);
                
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        // Rimmuovo gli item da ogni stanza
                        // removeAllItem rimuove gli oggetti nella stanza del dungeon
                        dungeon[i][j].removeAllItem();
                    }
                }
                for (int i = 0; i < 4; i++) {
                    for (int j = 0; j < 4; j++) {
                        // Arraylist temporanea per utilizzare setItems
                        ArrayList<Item> temp = new ArrayList<>();
                        // Devo specificare che stanza prendere che coincide con dungeon[i][j]
                        JSONArray dungeonRoom = jsonObject.getJSONArray("Room["+ i + "][" + j + "]");
                        // Controllo che ogni stanza venga caricata
                        System.out.println("Room["+ i + "][" + j + "]");
                        // L'oggetto che mi serve e' nella posizione 0 dell'array Room 
                        JSONObject room = dungeonRoom.getJSONObject(0);
                        // Nella posizione 0 e' presente l'array degli item nella stanza
                        JSONArray dungeonItem = room.getJSONArray("Item");
                        for (int k = 0; k < dungeonItem.length(); k++) {
                            JSONObject item = dungeonItem.getJSONObject(k);
                            itemSorter(temp, item);
                        }
                        dungeon[i][j].setItems(temp);
                    }
                }
                loadNpc(jsonObject,dungeon);

            }   catch (JSONException e) {
                System.err.println(e.getMessage());
            }
        } catch (AmazonClientException | FileNotFoundException e) {
            System.err.println(e.getMessage());
            System.exit(1);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private static void loadNpc(JSONObject jsonObject, Room[][] dungeon) {
        // Avendo un dungeon pre-generato vado direttamente nelle posizioni in cui ci sono gli NPC
        JSONArray dungeonRoomPatches = jsonObject.getJSONArray("Room["+ 1 + "][" + 3 + "]");
        JSONArray dungeonRoomKnight = jsonObject.getJSONArray("Room["+ 2 + "][" + 0 + "]");
        // getJSONObject(1) perche' gli NPC sono nella posizione 1 dell'array Room
        JSONObject roomPatches = dungeonRoomPatches.getJSONObject(1);
        JSONObject roomKnight = dungeonRoomKnight.getJSONObject(1);
        // Salvo l'array NPC nell'oggetto JSONArray
        JSONArray npcPatches = roomPatches.getJSONArray("Npc");
        JSONArray npcKnight = roomKnight.getJSONArray("Npc");
        // Avendo un solo NPC per stanza va direttamente a prendere la prima posizione
        JSONObject npcQuestPatches = npcPatches.getJSONObject(0);
        JSONObject npcQuestKnight = npcKnight.getJSONObject(0);
        // Confronta QuestCompleted del file e quello della partita in corso e lo cambia solo se sono discordanti
            if (npcQuestPatches.getBoolean("QuestCompleted") == !dungeon[1][3].isNpcInRoom("patches").isQuestCompleted() && npcQuestPatches.getString("name").contains("patches")) {
                dungeon[1][3].isNpcInRoom("patches").setQuestCompleted();
            } else if (npcQuestKnight.getBoolean("QuestCompleted") == !dungeon[2][0].isNpcInRoom("injured knight").isQuestCompleted() && npcQuestKnight.getString("name").contains("injured")) {
                dungeon[2][0].isNpcInRoom("injured knight").setQuestCompleted();
            }
    }

    private static void itemSorter(ArrayList<Item> temp, JSONObject item) {
        // Sfrutto la gestione degli oggetti tramite switch controllando se il nome dell'oggetto da come risultato il default case
        // Se non finisce nel default case significa che sto inizializzando l'oggetto nella classe giusta
        // Avendo gestito negli switch tutti i nomi degli oggetti
        if(     new KeyItem(item.getString("name")).getName().equals("notItem") &&
                new HealItem(item.getString("name")).getName().equals("notHealItem") &&
                new Armor(item.getString("name")).getName().equals("Ordinary Clothes")){
            // Controllo se l'oggetto era equipaggiato
            if (item.getBoolean("equipped")) {
                Weapon tempWeapon =new Weapon(item.getString("name"));
                // Se la condizione e 'vera equipaggio l'oggetto
                tempWeapon.setEquipped();
                temp.add(tempWeapon);
            }
            else {
                // Se la condizione non e' vera creo semplicemente l'oggetto che di defaul non e' equipaggiato
                temp.add(new Weapon(item.getString("name")));
            }
        }

        else if (new Weapon(item.getString("name")).getName().equals("notWeapon") &&
                new HealItem(item.getString("name")).getName().equals("notHealItem") &&
                new Armor(item.getString("name")).getName().equals("Ordinary Clothes")) {
            temp.add(new KeyItem(item.getString("name")));
        }

        else if (new KeyItem(item.getString("name")).getName().equals("notItem") &&
                new Weapon(item.getString("name")).getName().equals("notWeapon") &&
                new Armor(item.getString("name")).getName().equals("Ordinary Clothes")) {
            temp.add(new HealItem(item.getString("name")));
        }

        else if (new KeyItem(item.getString("name")).getName().equals("notItem") &&
                new Weapon(item.getString("name")).getName().equals("notWeapon") &&
                new HealItem(item.getString("name")).getName().equals("notHealItem")) {
            // Controllo se l'oggetto era equipaggiato
            if (item.getBoolean("equipped")) {
                Armor tempArmor =new Armor(item.getString("name"));
                // Se la condizione e' vera equipaggio l'oggetto
                tempArmor.setEquipped();
                temp.add(tempArmor);
            }
            else {
                // Se la condizione non e' vera creo semplicemente l'oggetto che di defaul non e' equipaggiato
                temp.add(new Armor(item.getString("name")));
            }
        }
    }


    //Funzioni di get
    private static InputStream getInputStream(S3Object object, saveFile sf) throws IOException {
        // Prende l'oggetto che e' stato scaricato dal bucket per poi scriverlo nel file json
        InputStream reader = new BufferedInputStream(
                object.getObjectContent());
        File file = new File(sf.getFilePath());
        OutputStream writer = new BufferedOutputStream(new FileOutputStream(file));

        int read;
        while ( ( read = reader.read() ) != -1 ) {
            writer.write(read);
        }
        // Svuoto e chiudo gli stream
        writer.flush();
        writer.close();
        reader.close();
        // Mette il testo del file nell'InputStream
        return new FileInputStream(file);
    }
    public String getFileName(){
        return fileName;
    }
    public String getFilePath(){
        return filePath;
    }
    public String getBucketName(){
        return bucketName;
    }
    public AmazonS3 getS3Client(){
        return s3Client;
    }
}
