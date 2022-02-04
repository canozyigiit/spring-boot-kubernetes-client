package com.can.kubernetesclient.service.pv;

import com.can.kubernetesclient.model.CreatePvModel;
import io.fabric8.kubernetes.api.model.PersistentVolume;
import io.fabric8.kubernetes.api.model.PersistentVolumeBuilder;
import io.fabric8.kubernetes.api.model.PersistentVolumeList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Component;
import io.fabric8.kubernetes.api.model.Quantity;
import java.util.Collections;


@Component
public class PvServiceImpl implements PvService {


    @Override
    public PersistentVolumeList getPersistentVolumes() {
        KubernetesClient client = new DefaultKubernetesClient();
        PersistentVolumeList persistentVolumeList = client.persistentVolumes().list();
        return persistentVolumeList;
    }

    @Override
    public String createPersistentVolumesNfs(CreatePvModel createPVModel) {

        KubernetesClient client = new DefaultKubernetesClient();
        PersistentVolume persistentVolume = new PersistentVolumeBuilder()
                .withNewMetadata()
                .withNamespace(createPVModel.getNamespace())
                .withName(createPVModel.getName())
                .withLabels(createPVModel.getLabels())
                .withAnnotations(createPVModel.getAnnotations())
                .endMetadata()
                .withNewSpec()
                .addToCapacity(Collections.singletonMap("storage", new Quantity(createPVModel.getAmount())))
                .addNewAccessMode(createPVModel.getAccessModes())
                .withPersistentVolumeReclaimPolicy(createPVModel.getPersistentVolumeReclaimPolicy())
                .withNewNfs()
                .withNewPath(createPVModel.getNfsPath())
                .withNewServer(createPVModel.getNfsServer())
                .endNfs()
                .endSpec()
                .build();
        client.persistentVolumes().create(persistentVolume);

        return persistentVolume.getMetadata().getName() + " pv created";
    }
}
