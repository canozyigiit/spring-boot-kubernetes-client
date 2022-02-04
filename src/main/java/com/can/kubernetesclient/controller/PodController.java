package com.can.kubernetesclient.controller;

import com.can.kubernetesclient.service.pod.PodService;
import com.can.kubernetesclient.model.CreatePodModel;
import io.fabric8.kubernetes.api.model.PodList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/pods")
@RequiredArgsConstructor
public class PodController {

    private final PodService podService;

    @GetMapping(value = "")
    public PodList getPods() {
        return podService.getPods();
    }

    @PostMapping(value = "")
    public String createPod(@RequestBody CreatePodModel createPodModel){
        return podService.createPod(createPodModel);
    }
    @GetMapping(value = "{namespace}")
    public PodList getPodsByNamespace(String namespace) {
        return podService.getPodsByNamespace(namespace);
    }
}
