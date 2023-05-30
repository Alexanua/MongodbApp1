public class Main {
    public static void main(String[] args) {
        String connectionString = System.getenv("MONGODB_CONNECTION_STRING");
        if (connectionString == null) {
            connectionString = "mongodb://localhost:27017/myDatabase";
        }

        MongoDBFacade mongoDBFacade = new MongoDBFacade(connectionString, "myDatabase");

        // Create Kund and Anställda objects
        Kund kund1 = new Kund("Alex", 30, "Baungatan", "K1001");
        Anställd anställd1 = new Anställd("Marcus", 39, "Karlstad", "A2001");

        // Create objects in database
        mongoDBFacade.createKund(kund1);
        mongoDBFacade.createAnställd(anställd1);

        // läs objects from database
        Kund fetchedKund = mongoDBFacade.readKund("K1001");
        Anställd fetchedAnställd = mongoDBFacade.readAnställd("A2001");

        // Updatera objects in database
        kund1.setAddress("Karlstad");
        mongoDBFacade.updateKund(kund1);

        anställd1.setAge(36);
        mongoDBFacade.updateAnställd(anställd1);

    }
}