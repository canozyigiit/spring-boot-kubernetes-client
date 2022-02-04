package com.can.kubernetesclient.service.pvc;

import com.can.kubernetesclient.model.CreatePvcModel;
import io.fabric8.kubernetes.api.model.PersistentVolumeClaimList;

public interface PvcService {

    PersistentVolumeClaimList getPersistentVolumeClaimList();
    String createPersistentVolumeClaim(CreatePvcModel createPvcModel);
}
