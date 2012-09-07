package com.zombiedash.app.web;

import com.zombiedash.app.jetty.WebServer;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

import java.io.IOException;
import java.net.ServerSocket;

public class Application {

    private static Application instance;

    private WebServer server;
    private Browser statelessBrowser;
    private Browser firefoxBrowser;
    private String serverName;
    private int port;
    private int sslPort;

    private Application(String serverName) {
        this.serverName = serverName;
        try {
            this.port = findFreePort();
            this.sslPort = findFreePort();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static Browser statelessBrowser(boolean useHttps) {
        return instance().getStatelessBrowser(useHttps);
    }

    public static Browser javascriptEnabledBrowser(boolean useHttps) {
        return instance().getFirefoxBrowser(useHttps);
    }

    private static Application instance() {
        if (instance != null) {
            return instance;
        }
        try {
            String hostAddress = getHostname();
            instance = new Application(hostAddress);
            registerShutdownHook();
            instance.start();
            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Application startup failed", e);
        }
    }

    private int findFreePort() throws IOException {
        ServerSocket socket = new ServerSocket(0);
        int port = socket.getLocalPort();
        socket.close();
        return port;
    }

    private static String getHostname() throws Exception {
        String hostname = System.getProperty("test.with.host.address");

        setSystemProperty("controlm.queue.provider", "none");
        setSystemProperty("icc.service.provider", "local");

        if (StringUtils.isBlank(hostname) || hostname!=null) {
            hostname = "localhost";
        }
        return hostname;
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
    }

    private void start() throws Exception {
        server = new WebServer(port);
        server.addSSLConnector(sslPort);
        server.start();
    }

    private static void setSystemProperty(String key, String value) {
        if (System.getProperty(key) == null) {
            System.setProperty(key, value);
        }
    }

    private void stop() {
        if (statelessBrowser != null) {
            statelessBrowser.stop();
        }
        if(firefoxBrowser != null) {
            firefoxBrowser.stop();
        }
        if (server != null) {
            server.stop();
        }
    }

    public Browser getStatelessBrowser(boolean useHttps) {
        if (statelessBrowser != null) return statelessBrowser;
        statelessBrowser = new Browser(getAddress(useHttps), false);
        return statelessBrowser;
    }

    private String getAddress(boolean useHttps) {
        String protocol = useHttps ? "https://" : "http://";
        int port = useHttps ? this.sslPort : this.port;
        return protocol + serverName + ":" + port;
    }

    public Browser getFirefoxBrowser(boolean useHttps) {
        if (firefoxBrowser != null) return firefoxBrowser;
        firefoxBrowser = new Browser(getAddress(useHttps), true);
        return firefoxBrowser;
    }

    private static class ShutdownHook implements Runnable {
        public void run() {
            instance.stop();
        }
    }

    public static BasicDataSource setupDataSource(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("org.h2.Driver");
        dataSource.setUrl("jdbc:h2:mem:db1");
        dataSource.setUsername("sa");
        return dataSource;
    }
}
