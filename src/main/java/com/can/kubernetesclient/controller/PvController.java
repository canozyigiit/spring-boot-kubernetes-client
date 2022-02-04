package com.can.kubernetesclient.controller;


import com.can.kubernetesclient.model.CreatePvModel;
import com.can.kubernetesclient.service.pv.PvService;
import io.fabric8.kubernetes.api.model.PersistentVolumeList;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pv")
@RequiredArgsConstructor
public class PvController {

    private final PvService pvService;


    @GetMapping(value = "")
    public PersistentVolumeList getPersistentVolumes( ){
        return pvService.getPersistentVolumes();
    }

    @PostMapping(value = "")
    public String createPersistentVolumesNfs(@RequestBody CreatePvModel createPVModel){
        return pvService.createPersistentVolumesNfs(createPVModel);
    }
}
