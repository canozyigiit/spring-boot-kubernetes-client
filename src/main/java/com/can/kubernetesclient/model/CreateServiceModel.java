package com.can.kubernetesclient.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateServiceModel {

    private String namespace;
    private String name;
    private Map<String, String> labels;
    private int port;
    private int targetPort;
    private int nodePort;
    private String protocol;
    private String type;
}
