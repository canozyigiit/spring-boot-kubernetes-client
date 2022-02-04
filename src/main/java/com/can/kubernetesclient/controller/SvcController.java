package com.can.kubernetesclient.controller;

import com.can.kubernetesclient.model.CreateServiceModel;
import com.can.kubernetesclient.service.svc.SvcService;
import io.fabric8.kubernetes.api.model.ServiceList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/services/")
@RequiredArgsConstructor
public class SvcController {

    private final SvcService svcService;

    @GetMapping(value = "")
    public ServiceList getServices(){
        return svcService.getServices();
    }
    @GetMapping(value = "{namespace}")
    public ServiceList getServiceByNameSpace(@PathVariable("namespace") String namespace){
        return svcService.getServiceByNameSpace(namespace);
    }

    @PostMapping(value = "")
    public String getServices(@RequestBody CreateServiceModel serviceModel){
        return svcService.createService(serviceModel);
    }
}
