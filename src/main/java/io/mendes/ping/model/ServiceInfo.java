package io.mendes.ping.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ServiceInfo {
    private String name;
    private String version;
    private String source;
    private String podId;
    private String podNamepsace;
    private String podNodeName;
}
