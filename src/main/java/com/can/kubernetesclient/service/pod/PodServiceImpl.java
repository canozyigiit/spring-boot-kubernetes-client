package com.can.kubernetesclient.service.pod;

import com.can.kubernetesclient.model.CreatePodModel;
import io.fabric8.kubernetes.api.model.Pod;
import io.fabric8.kubernetes.api.model.PodBuilder;
import io.fabric8.kubernetes.api.model.PodList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Component;

@Component
public class PodServiceImpl implements PodService {

    @Override
    public PodList getPods() {
        KubernetesClient client = new DefaultKubernetesClient();
        PodList podList = client.pods().list();
        return podList;
    }
    @Override
    public PodList getPodsByNamespace(String namespace) {
        KubernetesClient client = new DefaultKubernetesClient();
        PodList podList = client.pods().inNamespace(namespace).list();
        return podList;
    }

    @Override
    public String createPod(CreatePodModel createPodModel) {
        KubernetesClient client = new DefaultKubernetesClient();


        Pod pod = new PodBuilder()
                .withApiVersion("v1")
                .withNewKind("Pod")

                .withNewMetadata()
                .withName(createPodModel.getName())
                .addToLabels(createPodModel.getLabels())
                .addToAnnotations(createPodModel.getAnnotations())
                .withNamespace(createPodModel.getNamespace())
                .endMetadata()

                .withNewSpec()
                .withRestartPolicy(createPodModel.getRestartPolicy())

                .addNewContainer()
                .withName(createPodModel.getContainerName())
                .withImage(createPodModel.getContainerImage())
                .addNewPort()
                .withContainerPort(createPodModel.getContainerPort())
                .endPort()
                .withArgs(createPodModel.getArgs())
                .withCommand(createPodModel.getCommand())
                .endContainer()



                .endSpec()
                .build();

         client.pods().inNamespace(pod.getMetadata().getNamespace()).create(pod);

        return pod.getMetadata().getName() + " pod created";
    }


}
