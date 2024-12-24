package com.helen.framework.listener;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class DevtoolsListener {
    private static final Logger logger = LoggerFactory.getLogger(DevtoolsListener.class);

    public static void setUpDevTools(WebDriver driver) {
        setUpDevToolsWeb(driver,true);
    }
    public static void setUpDevToolsWeb(WebDriver driver,boolean isWeb) {

        /*DevTools devTools = ((ChromeDriver) driver).getDevTools();
        devTools.createSession();

        if(!isWeb)
        {// Set device metrics
            devTools.send(Emulation.setDeviceMetricsOverride(
                    375, // width
                    812, // height
                    100, // device scale factor
                    true, // mobile
                    Optional.empty(), // screen width
                    Optional.empty(), // screen height
                    Optional.empty(), // position x
                    Optional.empty(), // position y
                    Optional.empty(), // dont set
                    Optional.empty(), // dont set
                    Optional.empty(), // dont set
                    Optional.empty(), // dont set
                    Optional.empty() // dont set
                    // dont set
            ));
        }
        // Enable Network domain
        devTools.send(Network.enable(java.util.Optional.empty(), java.util.Optional.empty(), java.util.Optional.empty()));

        CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
            devTools.addListener(Network.responseReceived(), responseReceived -> {
                RequestId requestId = responseReceived.getRequestId();
                Response response = responseReceived.getResponse();

                // Check if the response URL matches what you're interested in
                if (response.getUrl().contains("api") && response.getUrl().contains(BaseTestCase.getCPURL())) {
                    try {
                        Network.GetResponseBodyResponse responseBody = devTools.send(Network.getResponseBody(requestId));
                        String resBody = responseBody.getBody().replace("<", "&lt").replace(">", "&gt");
                        if (responseBody.getBody() != null) {
                            logger.info("Response Status: " + response.getStatus() + "<br> URL: " + responseReceived.getResponse().getUrl() + " <br>Crm-Event-Id:" + response.getHeaders().get("crm-event-id") + "<br>     Response Body: " + resBody);
                        } else {
                            logger.info("No response body found for request ID: " + requestId);
                        }
                        //catch error like : {"code":500,"errmsg":"Server failed","data":null,"totalRecords":null,"msg":null,"extendString":null,"extendInteger":null}
                        if (responseBody.getBody().matches(".*\"code\":5[0-9]{2}.*")) {
                            String alert = "The following api contains error code: " + "<br> Response Status: " + response.getStatus() + " <br>Crm-Event-Id:" + response.getHeaders().get("crm-event-id") + " <br> URL: " + responseReceived.getResponse().getUrl()
                                    + "<br> RequestId: " + requestId + "<br> Response Body: " + responseBody.getBody();
                            logger.error(alert);
                            GlobalMethods.printDebugInfo(alert);
                            String screenshotBase64 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.BASE64);
                            logger.error(" <br><img src=\"data:image/png;base64," + screenshotBase64 + "\" width=\"" + 1000 + "\" height=\"" + 600 + "\"/>");
                        }

                    } catch (org.openqa.selenium.devtools.DevToolsException e) {
                        logger.info("Error getting response body: " + e.getMessage() + response.getStatus() + "<br> URL: " + responseReceived.getResponse().getUrl() + "<br>    RequestId: " + requestId);
                    }
                }
            });
        });
        // Wait for the asynchronous handling to complete
        future.join();*/
        // Add a delay to ensure the request is complete
      /* try {
            Thread.sleep(500);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }*/
    }

}
