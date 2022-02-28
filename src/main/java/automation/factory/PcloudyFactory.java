package automation.factory;

import com.ssts.pcloudy.Connector;
import com.ssts.pcloudy.dto.device.MobileDevice;
import com.ssts.pcloudy.dto.file.PDriveFileDTO;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import java.io.File;
import java.net.URL;
import static automation.constants.AppProperties.*;

public class PcloudyFactory {

    protected static Logger log = Logger.getLogger(PcloudyFactory.class);

    /** This method creates an pCloudy android driver instance based on the android OS version **/
    public void initialisePcloudyAndroidDriver(String platformVersion){
        try {
            MobileDevice device = getAvailableDevice("Android",platformVersion);
            String deviceFullName = device.full_name;
            if(!StringUtils.isEmpty(deviceFullName)) {
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("pCloudy_Username", PCLOUDY_USER_NAME);
                capabilities.setCapability("pCloudy_ApiKey", PCLOUDY_API_KEY);
                capabilities.setCapability("pCloudy_ApplicationName",PCLOUDY_ANDROID_APP);
                capabilities.setCapability("pCloudy_DurationInMinutes", PCLOUDY_DURATION_IN_MINS);
                capabilities.setCapability("pCloudy_DeviceFullName", deviceFullName);
                capabilities.setCapability("automationName", "uiautomator2");
                capabilities.setCapability("idleTimeout",120);
                capabilities.setCapability("newCommandTimeout", 120000);
                capabilities.setCapability("launchTimeout", 90000);
                capabilities.setCapability("appPackage", ANDROID_PACKAGE);
                capabilities.setCapability("appActivity", ANDROID_MAIN_APP_ACTIVITY);
                capabilities.setCapability("pCloudy_EnableVideo",true);
                capabilities.setCapability("pCloudy_EnableDeviceLogs", true);
                capabilities.setCapability("pCloudy_EnablePerformanceData", true);
                capabilities.setCapability("noSign", true);
                capabilities.setCapability("uiautomator2ServerLaunchTimeout", 200000);
                capabilities.setCapability("uiautomator2ServerInstallTimeout", 90000);
                MobileFactory.driver.set(new AndroidDriver<>(new URL(PCLOUDY_URL), capabilities));
            }else {
                log.error("Sorry. All pCloudy devices with platformVersion "+platformVersion+" is busy right now. Pls try again in sometime");
            }
        } catch (Exception e) {
            log.error("Exception occurred while initialising the pcloudy android driver instance ",e);
        }
    }

    /** This method creates an pCloudy iOS driver instance based on the specified  iOS version **/
    public void initialisePcloudyIosDriver(String platformVersion){
        try {
            MobileDevice device = getAvailableDevice("Ios",platformVersion);
            String deviceFullName = device.full_name;
            if(!StringUtils.isEmpty(deviceFullName)){
                DesiredCapabilities capabilities = new DesiredCapabilities();
                capabilities.setCapability("pCloudy_Username", PCLOUDY_USER_NAME);
                capabilities.setCapability("pCloudy_ApiKey", PCLOUDY_API_KEY);
                capabilities.setCapability("pCloudy_DurationInMinutes", PCLOUDY_DURATION_IN_MINS);
                capabilities.setCapability("newCommandTimeout", 120000);
                capabilities.setCapability("launchTimeout", 90000);
                capabilities.setCapability("pCloudy_DeviceFullName", deviceFullName);
                capabilities.setCapability("platformVersion", platformVersion);
                capabilities.setCapability("platformName", "ios");
                capabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, true);
                capabilities.setCapability("automationName", "XCUITest");
                capabilities.setCapability("pCloudy_ApplicationName",PCLOUDY_IOS_APP);
                capabilities.setCapability(MobileCapabilityType.NO_RESET, false);
                capabilities.setCapability("bundleId", IOS_APP_BUNDLE_ID);
                capabilities.setCapability("pCloudy_WildNet", "true");
                capabilities.setCapability("pCloudy_EnableVideo", "true");
                capabilities.setCapability("pCloudy_EnablePerformanceData", "true");
                capabilities.setCapability("pCloudy_EnableDeviceLogs", "true");

                IOSDriver<WebElement> iosdriver = new IOSDriver<>(new URL("https://device.pcloudy.com/appiumcloud/wd/hub"), capabilities);
                MobileFactory.driver.set(iosdriver);
            }else{
                log.error("No IOS Device is currently available/open with pcloudy. " +
                        "Please try again later after sometime");
            }
        } catch (Exception e) {
            log.error("Exception occurred in launching the pcloudy ios driver",e);
        }
    }

    /*
    * Given the android OS Version, we can fetch the list of available devices
    * using pcloudy api and execute the automation suite in that device.
    * @param
    * deviceType - specifies whether the deviceType should be android/ios. Ex: Android and Ios
    * platformVersion - OS Version of Android/iOS
    * */
    public static MobileDevice getAvailableDevice(String deviceType, String platformVersion) {
        try {
            String deviceName = deviceType+"_"+platformVersion;
            Connector connector = getPcloudyConnector();
            String authToken = getPcloudyAuthToken(connector);
            MobileDevice[] availableDevices = connector.getDevices(authToken, PCLOUDY_DURATION_IN_MINS, deviceType, true);
            for(MobileDevice device: availableDevices){
                if(device.full_name.contains(deviceName)) return device;
            }
        } catch (Exception e) {
            log.info("Unable to get the available device list from pcloudy");
            log.error("Exception occurred ",e);
        }
        return null;
    }

    /*
     * This method creates an instance of pCloudy connector for speaking with pCloudy api
     * */
    public static Connector getPcloudyConnector() {
        Connector con = null;
        try {
            con = new Connector(PCLOUDY_DEVICE_URL);
        } catch (Exception e) {
            log.error("Exception occurred ",e);
        }
        return con;
    }

    /*
     * This method fetches the pCloudy authentication token based on the pCloudy userName and api key
     * */
    public static String getPcloudyAuthToken(Connector connector) {
        String authToken = null;
        try {
            authToken = connector.authenticateUser(PCLOUDY_USER_NAME, PCLOUDY_API_KEY);
        } catch (Exception e) {
            log.error("Exception occurred ",e);
        }
        return authToken;
    }

    /*
    * This method can be used to upload android/ios app to the pCloudy site.
    * After uploading the app, the pCloudy site generates a unique name for the app
    * That name will be returned
    */
    public String uploadAppAndGetFileName(String deviceType) {
        log.info("Starting App upload...");
        String uploadedFilename = null;
        PDriveFileDTO pDriveFile = null;
        File sourceDir = new File("src");
        Connector connector = getPcloudyConnector();
        String authToken = getPcloudyAuthToken(connector);
        try {
            if (deviceType.equalsIgnoreCase("android")) {
                File app = new File(sourceDir, ANDROID_APP_PATH);
                pDriveFile = connector.uploadApp(authToken, new File(app.getAbsolutePath()),true);
            } else {
                File app = new File(sourceDir, IOS_APP_PATH);
                pDriveFile = connector.uploadAndResignIpaFile(authToken, new File(app.getAbsolutePath()), true);
            }
        } catch (Exception e) {
            log.error("Exception occurred while uploading the app to pCloudy", e);
        }
        uploadedFilename = pDriveFile.file;
        log.info("App upload success. File name :: " + uploadedFilename);
        return uploadedFilename;
    }
}
