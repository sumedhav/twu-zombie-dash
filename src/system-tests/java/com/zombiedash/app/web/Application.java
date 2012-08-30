package com.zombiedash.app.web;

import com.zombiedash.app.jetty.WebServer;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;

import java.net.ServerSocket;

public class Application {

    private static Application instance;

    private WebServer server;
    private Browser statelessBrowser;
    private Browser firefoxBrowser;
    private String serverName;
    private int port;

    private Application(String serverName, int port) {
        this.serverName = serverName;
        this.port = port;
    }

    public static Browser open(String url) {
        return statelessBrowser().open(url);
    }

    public static Browser statelessBrowser() {
        return instance().getStatelessBrowser();
    }

    public static Browser javascriptEnabledBrowser() {
        return instance().getFirefoxBrowser();
    }

    private static Application instance() {
        if (instance != null) {
            return instance;
        }
        try {
            int port = findFreePort();
            String hostAddress = getHostAddress();
            instance = new Application(hostAddress, port);
            registerShutdownHook();
            instance.start();
            return instance;

        } catch (Exception e) {
            throw new RuntimeException("Application startup failed", e);
        }
    }

    private static int findFreePort() throws Exception {
        ServerSocket socket = new ServerSocket(0);
        int port = socket.getLocalPort();
        socket.close();
        return port;
    }

    private static String getHostAddress() throws Exception {
        String hostAddress = System.getProperty("test.with.host.address");

        setSystemProperty("controlm.queue.provider", "none");
        setSystemProperty("icc.service.provider", "local");

        if (StringUtils.isBlank(hostAddress)) {
            hostAddress = "http://localhost:";
        }
        return hostAddress;
    }

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
    }

    private void start() throws Exception {
        server = new WebServer(port).start();
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
        if (firefoxBrowser!= null) {
            firefoxBrowser.stop();
        }
        if (server != null) {
            server.stop();
        }
    }

    public Browser getStatelessBrowser() {
        if (statelessBrowser != null) return statelessBrowser;
        statelessBrowser = new Browser(serverName + port, false);
        return statelessBrowser;
    }

    public Browser getFirefoxBrowser() {
        if (firefoxBrowser != null) return firefoxBrowser;
        firefoxBrowser = new Browser(serverName + port, true);
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
