// Import necessary packages for MongoDB operations
import com.mongodb.client.*;
import org.bson.Document;
import com.mongodb.ConnectionString;
import com.mongodb.MongoClientSettings;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import com.mongodb.client.model.Updates;

// Define the MongoDBFacade class
public class MongoDBFacade {

    // Declare the customer and employee collections as instance variables
    private final MongoCollection<Document> kundCollection;
    private final MongoCollection<Document> anställdCollection;

    // Constructor for the MongoDBFacade class
    public MongoDBFacade(String connectionString, String databaseName) {
        MongoClientSettings settings = MongoClientSettings.builder()
                .applyConnectionString(new ConnectionString(connectionString))
                .build();
        MongoClient mongoClient = MongoClients.create(settings);
        MongoDatabase database = mongoClient.getDatabase(databaseName);
        this.kundCollection = database.getCollection("kunder");
        this.anställdCollection = database.getCollection("anställda");
    }


    // CRUD methods for Kund
    // Skapa en kund
    public void createKund(Kund kund) {
        Document doc = new Document("kundnummer", kund.getKundnummer())
                .append("namn", kund.getName())
                .append("ålder", kund.getAge())
                .append("adress", kund.getAddress());
        kundCollection.insertOne(doc);
    }

    // Läs en kund med hjälp av kundnummer
    public Kund readKund(String kundnummer) {
        Document doc = kundCollection.find(Filters.eq("kundnummer", kundnummer)).first();
        if (doc == null) {
            return null;
        }
        return new Kund(doc.getString("namn"), doc.getInteger("ålder"), doc.getString("adress"), doc.getString("kundnummer"));
    }
    // Uppdatera en kund
    public void updateKund(Kund kund) {
        kundCollection.updateOne(
                Filters.eq("kundnummer", kund.getKundnummer()),
                Updates.combine(
                        Updates.set("namn", kund.getName()),
                        Updates.set("ålder", kund.getAge()),
                        Updates.set("adress", kund.getAddress())
                )
        );
    }
    // Ta bort en kund med hjälp av kundnummer
    public void deleteKund(String kundnummer) {
        kundCollection.deleteOne(Filters.eq("kundnummer", kundnummer));
    }


    // Skapa en anställd
    public void createAnställd(Anställd anställd) {
        Document doc = new Document("anställningsnummer", anställd.getAnställningsnummer())
                .append("namn", anställd.getName())
                .append("ålder", anställd.getAge())
                .append("adress", anställd.getAddress());
        anställdCollection.insertOne(doc);
    }

    // CRUD-metoder för Anställd
    // Läs en anställd med hjälp av anställning nummer
    public Anställd readAnställd(String anställningsnummer) {
        Document doc = anställdCollection.find(Filters.eq("anställningsnummer", anställningsnummer)).first();
        if (doc == null) {
            return null;
        }
        return new Anställd (doc.getString("namn"), doc.getInteger("ålder"), doc.getString("adress"), doc.getString("anställningsnummer"));
    }

    public void updateAnställd(Anställd anställd) {
        anställdCollection.updateOne(
                Filters.eq("anställningsnummer", anställd.getAnställningsnummer()),
                Updates.combine(
                        Updates.set("namn", anställd.getName()),
                        Updates.set("ålder", anställd.getAge()),
                        Updates.set("adress", anställd.getAddress())
                )
        );

    }

    public void deleteAnställd(String anställningsnummer) {
        anställdCollection.deleteOne(Filters.eq("anställningsnummer", anställningsnummer));
    }
}