package com.cl.crawler.service.strategy.abs;

import com.cl.crawler.service.strategy.IpProxyService;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * by cl at 2020/5/19 0019
 */
public abstract  class AbsIpProxyService implements IpProxyService {

    protected boolean isHostConnectable(String host, int port) {
        Socket socket = new Socket();
        try {
            socket.connect(new InetSocketAddress(host, port));
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        } finally {
            try {
                socket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }
}
