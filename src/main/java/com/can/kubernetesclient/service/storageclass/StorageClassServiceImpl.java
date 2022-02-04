package com.can.kubernetesclient.service.storageclass;


import com.can.kubernetesclient.model.CreateStorageClassModel;
import io.fabric8.kubernetes.api.model.storage.StorageClass;
import io.fabric8.kubernetes.api.model.storage.StorageClassBuilder;
import io.fabric8.kubernetes.api.model.storage.StorageClassList;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Component;

@Component
public class StorageClassServiceImpl implements StorageClassService {


    @Override
    public StorageClassList getStorageClass() {
        KubernetesClient client = new DefaultKubernetesClient();
        StorageClassList storageClassList = client.storage().storageClasses().list();
        return storageClassList;
    }

    @Override
    public String createStorageClass(CreateStorageClassModel createStorageClassModel) {
        KubernetesClient client = new DefaultKubernetesClient();
        StorageClass storageClass = new StorageClassBuilder()
                .withNewMetadata()
                .withName(createStorageClassModel.getName())
                .withLabels(createStorageClassModel.getLabels())
                .withAnnotations(createStorageClassModel.getAnnotations())
                .endMetadata()
                .withParameters(createStorageClassModel.getParameters())
                .withProvisioner(createStorageClassModel.getProvisioner())
                .withReclaimPolicy(createStorageClassModel.getReclaimPolicy())
                .withVolumeBindingMode(createStorageClassModel.getVolumeBindingMode())
                .build();
        client.storage().storageClasses().create(storageClass);

        return storageClass.getMetadata().getName() + " sc created";

    }
}
