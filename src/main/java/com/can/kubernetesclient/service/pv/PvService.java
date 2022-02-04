package com.can.kubernetesclient.service.pv;

import com.can.kubernetesclient.model.CreatePvModel;
import io.fabric8.kubernetes.api.model.PersistentVolumeList;

public interface PvService {

    PersistentVolumeList getPersistentVolumes();
    String createPersistentVolumesNfs(CreatePvModel createPVModel);

}
