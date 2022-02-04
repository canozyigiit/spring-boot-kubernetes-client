package com.can.kubernetesclient.service.pvc;

import com.can.kubernetesclient.model.CreatePvcModel;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Component;


@Component
public class PvcServiceImpl implements PvcService {

    @Override
    public PersistentVolumeClaimList getPersistentVolumeClaimList(){
    KubernetesClient client = new DefaultKubernetesClient();
    PersistentVolumeClaimList persistentVolumeClaimList = client.persistentVolumeClaims().list();
    return persistentVolumeClaimList;
}

    @Override
    public String createPersistentVolumeClaim(CreatePvcModel createPvcModel) {

        KubernetesClient client = new DefaultKubernetesClient();
        PersistentVolumeClaim persistentVolumeClaim = new PersistentVolumeClaimBuilder()

                .withNewMetadata()
                .withNamespace(createPvcModel.getNamespace())
                .withName(createPvcModel.getName())
                .withLabels(createPvcModel.getLabels())
                .withAnnotations(createPvcModel.getAnnotations())
                .endMetadata()

                .withNewSpec()                                                                    
                .withNewStorageClassName(createPvcModel.getStorageClassName())
                .addNewAccessMode(createPvcModel.getAccessModes())
                .withNewResources()
                .addToRequests("storage", new Quantity(createPvcModel.getAmount()))
                .endResources()
                .withNewSelector()
                .addToMatchLabels(createPvcModel.getMatchLabels())
                .endSelector()
                .endSpec()
                .build();
        client.persistentVolumeClaims().create(persistentVolumeClaim);

        return persistentVolumeClaim.getMetadata().getName() + " pvc created";
    }
}
