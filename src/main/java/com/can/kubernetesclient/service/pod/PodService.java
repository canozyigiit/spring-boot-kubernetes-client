package com.can.kubernetesclient.service.pod;

import com.can.kubernetesclient.model.CreatePodModel;
import io.fabric8.kubernetes.api.model.PodList;

public interface PodService {
    PodList getPods();
    PodList getPodsByNamespace(String namespace);
    String createPod(CreatePodModel pod);
}
