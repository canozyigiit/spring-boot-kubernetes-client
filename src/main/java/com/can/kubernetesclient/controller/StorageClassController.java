package com.can.kubernetesclient.controller;

import com.can.kubernetesclient.model.CreateStorageClassModel;
import com.can.kubernetesclient.service.storageclass.StorageClassService;
import io.fabric8.kubernetes.api.model.storage.StorageClassList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/sc")
@RequiredArgsConstructor
public class StorageClassController {


    private final StorageClassService storageClassService;


    @GetMapping(value = "")
    public StorageClassList getStorageClass( ){
        return storageClassService.getStorageClass();
    }

    @PostMapping(value = "")
    public String createStorageClass(@RequestBody CreateStorageClassModel createStorageClassModel){
        return storageClassService.createStorageClass(createStorageClassModel);
    }
}
