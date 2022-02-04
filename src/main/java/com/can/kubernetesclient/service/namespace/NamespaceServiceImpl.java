package com.can.kubernetesclient.service.namespace;

import io.fabric8.kubernetes.api.model.Namespace;
import io.fabric8.kubernetes.api.model.ObjectMeta;
import io.fabric8.kubernetes.client.DefaultKubernetesClient;
import io.fabric8.kubernetes.client.KubernetesClient;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class NamespaceServiceImpl implements NamespaceService{

    @Override
    public List<String> getNameSpaces() {
        KubernetesClient client = new DefaultKubernetesClient();
        var namespaceList = client.namespaces().list();
        List<String> namespaces = new ArrayList<>();
        for (Namespace ns : namespaceList.getItems()) {
            namespaces.add(ns.getMetadata().getName());
        }
        return namespaces;
    }

    @Override
    public boolean isNotExistNamespace(String name) {
        KubernetesClient client = new DefaultKubernetesClient();
        return client.namespaces().list().getItems()
                .stream().filter(namespace -> namespace.getMetadata().getName().equals(name))
                .collect(Collectors.toList()).isEmpty();
    }

    @Override
    public String createNamespace(String namespace) {
        KubernetesClient client = new DefaultKubernetesClient();
        
        if(!isNotExistNamespace(namespace)) return namespace;

        Namespace ns = new Namespace();
        ns.setApiVersion("v1");
        ns.setKind("Namespace");

        ObjectMeta objectMeta = new ObjectMeta();
        objectMeta.setName(namespace);

        ns.setMetadata(objectMeta);

        var savedNamespace = client.namespaces().create(ns);
        return savedNamespace.getMetadata().getName();
    }
}
