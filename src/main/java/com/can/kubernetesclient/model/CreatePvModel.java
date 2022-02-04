package com.can.kubernetesclient.model;

import io.fabric8.kubernetes.api.model.Quantity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Map;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreatePvModel {
    private String namespace;
    private String name;
    private String accessModes;
    private String persistentVolumeReclaimPolicy;
    private String nfsPath;
    private String nfsServer;
    private Map<String, String> labels;
    private String amount;
    private Map<String, String> annotations;

}
