package com.zombiedash.app.web;

import com.zombiedash.app.jetty.WebServer;
import org.apache.commons.dbcp.BasicDataSource;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.Validate;

import java.net.ServerSocket;

public class Application {

    private static Application instance;

    private WebServer server;
    private Browser statelessBrowser;
    private Browser firefoxBrowser;

    public static Browser open(String url) {
        return statelessBrowser().open(url);
    }

    public static Browser statelessBrowser() {
        return browser(true);
    }

    public static Browser javascriptEnabledBrowser() {
        return browser(false);
    }

    private static Browser browser(boolean stateless) {
        Browser browser = stateless ? instance().statelessBrowser : instance().firefoxBrowser;
        Validate.notNull(browser, "Application has not started succesfully. Please check earlier failed tests.");
        return browser;
    }

    private static Application instance() {
        if (instance != null) {
            return instance;
        }
        try {
            int port = findFreePort();
            instance = new Application();
            registerShutdownHook();
            instance.start(port);
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

    private static void registerShutdownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread(new ShutdownHook()));
    }

    private void start(int port) throws Exception {
        String hostAddress = System.getProperty("test.with.host.address");

        setSystemProperty("controlm.queue.provider", "none");
        setSystemProperty("icc.service.provider", "local");

        if (StringUtils.isBlank(hostAddress)) {
            server = new WebServer(port).start();
            hostAddress = "http://localhost:" + port;
        }
        statelessBrowser = new Browser(hostAddress, false);
        firefoxBrowser = new Browser(hostAddress, true);
    }

    private void setSystemProperty(String key, String value) {
        if (System.getProperty(key) == null) {
            System.setProperty(key, value);
        }
    }

    private void stop() {
        if (statelessBrowser != null) {
            statelessBrowser.stop();
        }
        if (server != null) {
            server.stop();
        }
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
