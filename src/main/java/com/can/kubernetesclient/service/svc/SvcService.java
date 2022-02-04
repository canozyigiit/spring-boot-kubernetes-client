package com.can.kubernetesclient.service.svc;

import com.can.kubernetesclient.model.CreateServiceModel;
import io.fabric8.kubernetes.api.model.ServiceList;

public interface SvcService {
    ServiceList getServices();

    String createService(CreateServiceModel serviceModel);
    ServiceList getServiceByNameSpace(String namespace);
}
