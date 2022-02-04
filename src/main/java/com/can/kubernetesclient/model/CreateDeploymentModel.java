package com.can.kubernetesclient.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateDeploymentModel {

    private String namespace;
    private String name;
    private Map<String, String> metadataLabels;
    private int replicas;
    private Map<String, String> selectorLabels;
    private String image;
    private String containerName;
    private Integer containerPort;
    private String[] command;
    private String[] args;

}
