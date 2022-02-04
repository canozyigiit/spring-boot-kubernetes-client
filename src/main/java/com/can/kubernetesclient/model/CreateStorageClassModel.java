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
public class CreateStorageClassModel {


    private String namespace;
    private String name;
    private String provisioner;
    private String reclaimPolicy;
    private String volumeBindingMode;
    private Map<String, String> labels;
    private Map<String, String> parameters;
    private Map<String, String> annotations;
}
