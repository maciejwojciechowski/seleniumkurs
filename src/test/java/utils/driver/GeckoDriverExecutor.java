package utils.driver;

import org.apache.commons.lang3.SystemUtils;

class GeckoDriverExecutor extends DriverExecutor {

    private static final String GECKO_WINDOWS_PATH = "src/test/resources/drivers/firefox/geckodriver.exe";
    private static final String GECKO_MACOS_PATH = "src/test/resources/drivers/firefox/geckodriverMac";
    private static final String GECKO_LINUX_64_PATH = "src/test/resources/drivers/firefox/geckodriver_Linux64";

    static String getProperDriverExecutable() {
        String osName = System.getProperty("os.name").toLowerCase();
        if (SystemUtils.IS_OS_MAC) {
            System.out.println("Mac OS X detected, assigning proper executable.");
            return GECKO_MACOS_PATH;
        } else if (SystemUtils.IS_OS_WINDOWS) {
            System.out.println("Windows detected, assigning proper executable.");
            return GECKO_WINDOWS_PATH;
        } else if (osName.contains("linux")) {
            setFullPermissionsForDriver(GECKO_LINUX_64_PATH);
            return GECKO_LINUX_64_PATH;
        }
        return null;
    }

}