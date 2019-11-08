package com.akash.kvpoc.ws2.blob;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.blob.CloudBlobClient;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;

/**
 * Manages the storage blob client
 */
@Component
public class BlobClientProvider {
    
    @Value("${storage.connection.string}")
    private String storageConnectionString;

    public String getStorageConnectionString() {

        return storageConnectionString;

    }

    public void setStorageConnectionString(String storageConnectionString) {

        this.storageConnectionString = storageConnectionString;

    }

    /**
     * Validates the connection string and returns the storage blob client.
     * The connection string must be in the Azure connection string format.
     *
     * @return The newly created CloudBlobClient object
     *
     * @throws RuntimeException
     * @throws IOException
     * @throws URISyntaxException
     * @throws IllegalArgumentException
     * @throws InvalidKeyException
     */
    public CloudBlobClient getBlobClientReference() throws RuntimeException, IOException, IllegalArgumentException, URISyntaxException, InvalidKeyException {

        // Retrieve the connection string
        /* Properties prop = new Properties();
        try {
            InputStream propertyStream = BlobBasics.class.getClassLoader().getResourceAsStream("config.properties");
            if (propertyStream != null) {
                prop.load(propertyStream);
            }
            else {
                throw new RuntimeException();
            }
        } catch (RuntimeException|IOException e) {
            System.out.println("\nFailed to load config.properties file.");
            throw e;
        } */

        CloudStorageAccount storageAccount;
        try {
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
        }
        catch (IllegalArgumentException|URISyntaxException e) {
            System.out.println("\nConnection string specifies an invalid URI.");
            System.out.println("Please confirm the connection string is in the Azure connection string format.");
            throw e;
        }
        catch (InvalidKeyException e) {
            System.out.println("\nConnection string specifies an invalid key.");
            System.out.println("Please confirm the AccountName and AccountKey in the connection string are valid.");
            throw e;
        }

        return storageAccount.createCloudBlobClient();
    }

}