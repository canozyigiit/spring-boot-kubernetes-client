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
public class CreatePodModel {
    private String namespace;
    private String name;
    private String restartPolicy;

    private Map<String, String> labels;
    private Map<String, String> annotations;

    private String containerImage;
    private String containerName;
    private Integer containerPort;
    private String[] command;
    private String[] args;


}
