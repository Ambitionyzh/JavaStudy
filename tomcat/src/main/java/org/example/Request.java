package org.example;

import javax.servlet.http.HttpServletRequest;
import java.net.Socket;

/**
 * @author yongzh
 * @version 1.0
 * @program: JavaTest
 * @description:
 * @date 2023/3/28 21:59
 */
public class Request extends AbstractHttpServletRequest {
    private String method;
    private String url;
    private String protocol;
    private Socket socket;

    public Request(String method, String url, String protocol,Socket socket) {
        this.method = method;
        this.url = url;
        this.protocol = protocol;
        this.socket = socket;
    }

    public String getMethod() {
        return method;
    }



    public StringBuffer getRequestURL() {
        return new StringBuffer(url);
    }


    public String getProtocol() {
        return protocol;
    }

    public Socket getSocket() {
        return socket;
    }
}
