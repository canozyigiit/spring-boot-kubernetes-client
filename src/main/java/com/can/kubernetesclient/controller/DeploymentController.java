package com.can.kubernetesclient.controller;

import com.can.kubernetesclient.model.CreateDeploymentModel;
import com.can.kubernetesclient.service.deployment.DeploymentService;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/deployments")
@RequiredArgsConstructor
public class DeploymentController {

    private final DeploymentService deploymentService;

    @GetMapping(value = "")
    public List<String> getDeployments(){
        return deploymentService.getDeployments();
    }

    @GetMapping(value = "{namespace}")
    public DeploymentList getDeploymentsByNameSpace(@PathVariable("namespace") String namespace){
        return deploymentService.getDeploymentByNameSpace(namespace);
    }

    @PostMapping(value = "")
    public String createDeployment(@RequestBody CreateDeploymentModel deploymentModel){
        return deploymentService.createDeployment(deploymentModel);
    }

    @DeleteMapping(value = "")
    public String deleteDeployment(@RequestParam String deploymentName){
        return deploymentService.deleteDeploymentByName(deploymentName);
    }
}
