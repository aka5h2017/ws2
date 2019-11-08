package com.akash.kvpoc.ws2.blob;

import java.util.ArrayList;
import java.util.List;

import com.microsoft.azure.storage.blob.CloudBlob;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.ListBlobItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BlobListService {

    @Autowired
    BlobClientProvider blobClientProvider;
    public List<String> listBlobsInContainer() throws Exception {

        List<String> blobList = new ArrayList<>();
        for (CloudBlobContainer container : blobClientProvider.getBlobClientReference().listContainers("poc")) {
            for(ListBlobItem lbi: container.listBlobs()) {
                if (lbi instanceof CloudBlob) {
                    blobList.add(((CloudBlob) lbi).getName());
                }
            } 
        }
        return blobList;
    }

}