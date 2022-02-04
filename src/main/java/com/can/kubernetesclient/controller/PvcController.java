package com.can.kubernetesclient.controller;


import com.can.kubernetesclient.model.CreatePvcModel;
import com.can.kubernetesclient.service.pvc.PvcService;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pvc")
@RequiredArgsConstructor
public class PvcController {


    private final PvcService pvcService;


    @GetMapping(value = "")
    public PersistentVolumeClaimList getPersistentVolumeClaimList( ){
        return pvcService.getPersistentVolumeClaimList();
    }

    @PostMapping(value = "")
    public String createPersistentVolumeClaim(@RequestBody CreatePvcModel createPvcModel){
        return pvcService.createPersistentVolumeClaim(createPvcModel);
    }
}
