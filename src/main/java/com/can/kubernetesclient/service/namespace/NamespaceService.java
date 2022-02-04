package com.can.kubernetesclient.service.namespace;

import java.util.List;

public interface NamespaceService {

    List<String> getNameSpaces();

    boolean isNotExistNamespace(String name);

    String createNamespace(String namespace);
}
