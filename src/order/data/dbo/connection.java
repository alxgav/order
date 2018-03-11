package order.data.dbo;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;

public class connection {

    MongoDatabase database;

    public connection() {
    }

    public MongoDatabase dbConnection(){
        MongoClientURI uri = new MongoClientURI("mongodb+srv://alxgav:Mepas34mepas#@cluster0-spqsk.mongodb.net/test");
        MongoClient mongoClient = new MongoClient(uri);

        return mongoClient.getDatabase("jobdb");
    }

    public MongoCollection<Document> getCollection(String collection){
        return database.getCollection(collection);
    }
}
