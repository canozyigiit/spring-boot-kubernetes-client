package com.can.kubernetesclient.service.storageclass;

import com.can.kubernetesclient.model.CreateStorageClassModel;
import io.fabric8.kubernetes.api.model.storage.StorageClassList;

public interface StorageClassService {


    StorageClassList getStorageClass();
    String createStorageClass(CreateStorageClassModel createStorageClassModel);
}
