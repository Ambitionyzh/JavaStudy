package org.example.common;

import java.io.Serializable;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/26 9:46
 */
public class URL implements Serializable {
    private String hostName;
    private Integer port;

    public String getHostName() {
        return hostName;
    }

    public void setHostName(String hostName) {
        this.hostName = hostName;
    }

    public Integer getPort() {
        return port;
    }

    public void setPort(Integer port) {
        this.port = port;
    }

    public URL(String hostName, Integer port) {
        this.hostName = hostName;
        this.port = port;
    }
}
