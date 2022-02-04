package com.can.kubernetesclient.controller;

import com.can.kubernetesclient.service.namespace.NamespaceService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/namespaces")
@RequiredArgsConstructor
public class NameSpaceController {

    private final NamespaceService namespaceService;

    @GetMapping(value = "")
    public List<String> getNameSpaces() {
        return namespaceService.getNameSpaces();
    }

    @PostMapping(value = "")
    public String createNamespace(@RequestParam String namespace) {
        return namespaceService.createNamespace(namespace);
    }
}
