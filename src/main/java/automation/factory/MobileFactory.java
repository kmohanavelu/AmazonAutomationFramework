package automation.factory;

import automation.utitilites.Utilities;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.AutomationName;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;
import io.appium.java_client.service.local.flags.GeneralServerFlag;
import org.apache.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;

import static automation.utitilites.Constants.*;

public class MobileFactory {

    /**
     * We use thread local variable for driver. Because if we are running tests in parallel, then each thread
     * will have it's own instance of driver
     * We maintain it as a static variable, because it will be easier to access anywhere without creating an instance of MobileFactory
     */
    public AppiumDriverLocalService service;
    public static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();
    private Logger log = Logger.getLogger(MobileFactory.class);


    /**
     * In order to run the appium automation suite, Appium server needs to be running at the background.
     * This method serves that purpose. The default appium server port is 4723.
     * First this method checks if the server is already running in the background at that port.
     * If it's already running, the code will use that server. Otherwise a new server instance will be launched
     */
    public void startAppiumServer() {
        boolean isServerUp = checkAppiumServerIsAlreadyRunning(4723);
        if (!isServerUp) {
            AppiumServiceBuilder builder = new AppiumServiceBuilder();
            if (!(System.getProperty("os.name").contains("Windows"))) {
                builder.withAppiumJS(new File("/usr/local/lib/node_modules/appium/build/lib/main.js"))
                        .usingDriverExecutable(new File("/usr/local/bin/node")).usingPort(4723)
                        .withArgument(GeneralServerFlag.LOG_LEVEL, "debug")
                        .withArgument(GeneralServerFlag.LOCAL_TIMEZONE)
                        .withLogFile(new File("appiumlog.txt"));
            }
            service = AppiumDriverLocalService.buildService(builder);
            service.start();
        }
    }

    /**
     * This method returns true if an appium server is already running at the default port.
     **/
    public boolean checkAppiumServerIsAlreadyRunning(int port) {
        boolean isServerRunning = false;
        ServerSocket serverSocket;
        try {
            serverSocket = new ServerSocket(port);
            serverSocket.close();
        } catch (IOException e) {
            isServerRunning = true;
        }
        return isServerRunning;
    }

    /**
     * @param deviceName - Device Name. User defined variable. It can be anything. Ex: If it's SamsungS20 device, then we can give s20
     *                   platformVersion - Android OS Version of the device
     *                   UDID - Unique device identifier of the device. Can be fetched using 'adb devices' on terminal
     *                   The method parameters are fetched from the maven arguments during run time
     *                   Generic things like APP_PATH, APP_ACTIVITY, APP_PACKAGE, ANDROID_APP_WAIT_DURATION are constants for a framework.
     *                   So it's better to load them from an external properties file or json file
     **/

    public void initialiseAndroidDriver(String deviceName, String platformVersion, String udid) {
        try {
            File sourceDir = new File("src");
            File app = new File(sourceDir, ANDROID_APP_PATH);
            DesiredCapabilities desiredCapabilties = new DesiredCapabilities();
            desiredCapabilties.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilties.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
            desiredCapabilties.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120000);
            desiredCapabilties.setCapability(MobileCapabilityType.UDID, udid);
            desiredCapabilties.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            desiredCapabilties.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
            desiredCapabilties.setCapability(MobileCapabilityType.PLATFORM_VERSION, platformVersion);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.APP_ACTIVITY, ANDROID_MAIN_APP_ACTIVITY);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.APP_PACKAGE, ANDROID_PACKAGE);
            desiredCapabilties.setCapability(MobileCapabilityType.FULL_RESET, false);
            desiredCapabilties.setCapability(MobileCapabilityType.NO_RESET, true);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.APP_WAIT_DURATION, ANDROID_APP_WAIT_DURATION);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, true);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.DISABLE_ANDROID_WATCHERS, true);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.DISABLE_WINDOW_ANIMATION, true);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.SKIP_UNLOCK, true);
            desiredCapabilties.setCapability(AndroidMobileCapabilityType.IGNORE_UNIMPORTANT_VIEWS, true);
            driver.set(new AndroidDriver<>(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilties));
        } catch (Exception e) {
            log.error("Exception occurred while initialising an android driver instance" +
                    "for the device " + deviceName + " and udid " + udid, e);
        }
    }

    public void initialiseIOSDriver(String deviceName, String osVersion, String udid) {
        try {
            File sourceDir = new File("src");
            File app = new File(sourceDir, IOS_APP_PATH);
            DesiredCapabilities desiredCapabilties = new DesiredCapabilities();
            desiredCapabilties.setCapability(MobileCapabilityType.DEVICE_NAME, deviceName);
            desiredCapabilties.setCapability(MobileCapabilityType.PLATFORM_NAME, "iOS");
            desiredCapabilties.setCapability(MobileCapabilityType.AUTOMATION_NAME, AutomationName.IOS_XCUI_TEST);
            desiredCapabilties.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 120000);
            desiredCapabilties.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
            desiredCapabilties.setCapability(MobileCapabilityType.PLATFORM_VERSION, osVersion);
            desiredCapabilties.setCapability(MobileCapabilityType.UDID, udid);
            desiredCapabilties.setCapability(IOSMobileCapabilityType.BUNDLE_ID, IOS_APP_BUNDLE_ID);
            desiredCapabilties.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
            desiredCapabilties.setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, XCODE_ORG_ID);
            desiredCapabilties.setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, XCODE_SIGNING_ID);
            desiredCapabilties.setCapability(IOSMobileCapabilityType.SIMPLE_ISVISIBLE_CHECK, true);
            desiredCapabilties.setCapability(MobileCapabilityType.NO_RESET, false);
            desiredCapabilties.setCapability(IOSMobileCapabilityType.IOS_INSTALL_PAUSE, 3000);
            driver.set(new IOSDriver(new URL("http://127.0.0.1:4723/wd/hub"), desiredCapabilties));
        } catch (Exception e) {
            log.error("Exception occurred while initialising an ios driver instance" +
                    "for the device "+deviceName+" and udid "+udid, e);
        }
    }

    /*
     * This method will be called as a part of @BeforeSuite. It kills the already running appium server instance
     */
    public void killAppiumServer() {
        Utilities.executeScript(APPIUM_SERVER_KILL_SCRIPT);
    }

    /*
    * Static method used to fetch the driver instance of that particular thread wherever needed
    * Hence there avoids the need to pass the driver instance as a method argument
    * */
    public static AppiumDriver getDriver() {
        return driver.get();
    }
}
