package com.helen.framework.listener;

import org.apache.log4j.PropertyConfigurator;
import org.openqa.selenium.support.events.WebDriverListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyWebDriverListener implements WebDriverListener {
    private static final Logger logger = LoggerFactory.getLogger(MyWebDriverListener.class);

    public MyWebDriverListener() {
        String path = System.getProperty("user.dir");
        path = path + "/src/main/resources/log4j.properties";
       // PropertyConfigurator.configure(path);
    }
}
