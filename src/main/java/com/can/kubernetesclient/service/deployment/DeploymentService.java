package com.can.kubernetesclient.service.deployment;

import com.can.kubernetesclient.model.CreateDeploymentModel;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;

import java.util.List;

public interface DeploymentService {
    List<String> getDeployments();

    String createDeployment(CreateDeploymentModel deploymentModel);

    String deleteDeploymentByName(String deploymentName);
    DeploymentList getDeploymentByNameSpace(String namespace);
}
