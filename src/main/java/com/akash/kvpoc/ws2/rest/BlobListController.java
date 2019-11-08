package com.akash.kvpoc.ws2.rest;

import java.util.List;

import com.akash.kvpoc.ws2.blob.BlobListService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/blob", produces = "application/json")
public class BlobListController {

    @Autowired
    private BlobListService blobListService;

    @RequestMapping(path = "/list")
    public List<String> listBlobs() throws Exception {

        return blobListService.listBlobsInContainer();
        
    }

}