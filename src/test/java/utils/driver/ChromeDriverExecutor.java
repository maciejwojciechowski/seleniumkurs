package utils.driver;

import org.apache.commons.lang3.SystemUtils;

class ChromeDriverExecutor extends DriverExecutor {

    private static final String CHROME_WINDOWS_PATH = "src/test/resources/drivers/chrome/chromedriver.exe";
    private static final String CHROME_MACOS_PATH = "src/test/resources/drivers/chrome/chromedriverMac";
    private static final String CHROME_LINUX_64_PATH = "src/test/resources/drivers/chrome/chromedriver_Linux64";

    static String getProperDriverExecutable() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (SystemUtils.IS_OS_MAC) {
            System.out.println("Mac OS X detected, assigning proper executable.");
            return CHROME_MACOS_PATH;
        } else if (SystemUtils.IS_OS_WINDOWS) {
            System.out.println("Windows detected, assigning proper executable.");
            return CHROME_WINDOWS_PATH;
        } else if (osName.contains("linux")) {
            setFullPermissionsForDriver(CHROME_LINUX_64_PATH);
            return CHROME_LINUX_64_PATH;
        }
        return null;
    }

}