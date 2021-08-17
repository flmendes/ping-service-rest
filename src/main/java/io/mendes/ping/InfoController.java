package io.mendes.ping;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class InfoController {

    @Value("${version:0.0.1}")
    private String version;

    @Value("${POD_ID:}")
    private String podId;

    @Value("${POD_NODE_NAME:}")
    private String podNodeName;

    @Value("${POD_NAMESPACE:}")
    private String podNamespace;

    @GetMapping("/info")
    public ServiceInfo getInfo() {
        return new ServiceInfo(
                "C4P Service (REST)",
                "v"+version,
                "https://github.com/flmendes/ping-service-rest/releases/tag/v" + version,
                podId,
                podNamespace,
                podNodeName);
    }
}
