package com.can.kubernetesclient.service.deployment;

import com.can.kubernetesclient.model.CreateDeploymentModel;
import com.can.kubernetesclient.service.namespace.NamespaceService;
import io.fabric8.kubernetes.api.model.apps.Deployment;
import io.fabric8.kubernetes.api.model.apps.DeploymentBuilder;
import io.fabric8.kubernetes.api.model.apps.DeploymentList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DeploymentServiceImpl implements DeploymentService{

    private final NamespaceService namespaceService;

    @Override
    public List<String> getDeployments(){
        KubernetesClient client = new DefaultKubernetesClient();
        var deploymentList = client.apps().deployments().list().getItems();
        List<String> deployments = new ArrayList<>();
        for (Deployment deployment: deploymentList){
            deployments.add(deployment.getMetadata().getName());
        }
        return deployments;
    }

    @Override
    public String createDeployment(CreateDeploymentModel deploymentModel){

        KubernetesClient client = new DefaultKubernetesClient();

        String namespace = namespaceService.createNamespace(deploymentModel.getNamespace());

        Deployment deployment = new DeploymentBuilder()
                .withNewMetadata()
                .withName(deploymentModel.getName())
                .endMetadata()
                .withNewSpec()
                .withReplicas(deploymentModel.getReplicas())
                .withNewTemplate()
                .withNewMetadata()
                .addToLabels(deploymentModel.getMetadataLabels())
                .endMetadata()
                .withNewSpec()
                .addNewContainer()
                .withCommand(deploymentModel.getCommand())
                .withArgs(deploymentModel.getArgs())
                .withName(deploymentModel.getContainerName())
                .withImage(deploymentModel.getImage())
                .addNewPort()
                .withContainerPort(deploymentModel.getContainerPort())
                .endPort()
                .endContainer()
                .endSpec()
                .endTemplate()
                .withNewSelector()
                .addToMatchLabels(deploymentModel.getSelectorLabels())
                .endSelector()
                .endSpec()
                .build();

        deployment = client.apps().deployments().inNamespace(namespace).create(deployment);

        return deployment.getMetadata().getName()+" deployment created";
    }

    @Override
    public String deleteDeploymentByName(String deploymentName){
        KubernetesClient client = new DefaultKubernetesClient();
        var deploymentList = client.apps().deployments().list().getItems();
        Deployment deployment = deploymentList.stream()
                .filter(dp -> dp.getMetadata().getName().equals(deploymentName)).findFirst().orElse(null);

        if(deployment != null){
            if(client.apps().deployments().delete(deployment)){
                return deploymentName+" successfully deleted";
            } else {
                return "Error.";
            }
        } else {
            return "Deployment not found.";
        }

    }

    @Override
    public DeploymentList getDeploymentByNameSpace(String namespace) {
        KubernetesClient client = new DefaultKubernetesClient();
        DeploymentList deploymentList = client.apps().deployments().inNamespace(namespace).list();
        return deploymentList;
    }
}
