package com.can.kubernetesclient.service.svc;

import com.can.kubernetesclient.model.CreateServiceModel;
import com.can.kubernetesclient.service.namespace.NamespaceService;
import io.fabric8.kubernetes.api.model.*;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class SvcServiceImpl implements SvcService {

    @Value("${k8s.svc.kind}")
    private String svcKind;
    @Value("${k8s.svc.api-version}")
    private String svcApiVersion;
    private final NamespaceService namespaceService;

    @Override
    public ServiceList getServices(){
        KubernetesClient client = new DefaultKubernetesClient();
       /* var serviceList = client.services().list();
        List<String> services = new ArrayList<>();
        for (Service service : serviceList.getItems()) {
            services.add(service.getMetadata().getName());
        }*/
        ServiceList myServices = client.services().list();
        return myServices;
    }

    @Override
    public String createService(CreateServiceModel serviceModel){
        KubernetesClient client = new DefaultKubernetesClient();

        String namespace = namespaceService.createNamespace(serviceModel.getNamespace());

        Service service = new ServiceBuilder()
                .withNewMetadata()
                .withName(serviceModel.getName())
                .withLabels(serviceModel.getLabels())
                .endMetadata()
                .withNewSpec()
                .addNewPort()
                .withProtocol(serviceModel.getProtocol())
                .withPort(serviceModel.getPort())
                .withTargetPort(new IntOrString(serviceModel.getTargetPort()))
                .withNodePort(serviceModel.getNodePort())
                .endPort()
                .withType(serviceModel.getType())
                .endSpec()
                .build();

        service = client.services().inNamespace(namespace).create(service);

        return service.getMetadata().getName()+" service created";
    }

    @Override
    public ServiceList getServiceByNameSpace(String namespace) {
        KubernetesClient client = new DefaultKubernetesClient();
        ServiceList myNsServices = client.services().inNamespace(namespace).list();
        return myNsServices;
    }

}
