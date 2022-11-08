package base;

import com.applitools.eyes.*;
import com.applitools.eyes.selenium.ClassicRunner;
import com.applitools.eyes.selenium.Configuration;
import com.applitools.eyes.selenium.Eyes;
import com.applitools.eyes.selenium.StitchMode;
import com.applitools.eyes.selenium.fluent.Target;
import org.apache.commons.io.IOUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;


public class BaseClass {
    public static WebDriver driver;
    public static ClassicRunner runner;
  //public static VisualGridRunner runner;
    public static Eyes eyes;

    public static void setupDriver(Boolean useHeadless) throws MalformedURLException
    {
    ChromeOptions chromeOptions = new ChromeOptions();
    //String userAgent = "Mozilla/5.0 (Macintosh; Intel Mac OS X 10_15_7) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/94.0.4606.71 Safari/537.36";
    chromeOptions.addArguments("--headless", "--ignore-certificate-errors", "--window-size=1400,900", "--disable-web-security");
    String downloadFilepath = "./src/test/java/pdf_downloads";
    Map<String, Object> prefs = new HashMap<String, Object>();
    prefs.put("download.prompt_for_download", false);
    prefs.put("download.default_directory", downloadFilepath);
    chromeOptions.setExperimentalOption("prefs", prefs);
        if(useHeadless) {
            driver=new ChromeDriver(chromeOptions);
        }
        else {
            driver=new ChromeDriver();
        }
        //driver=new FirefoxDriver();
    }


    public static void closeDriver()
    {
        driver.quit();
    }

    public void addFeature(String className, int indexNum, String applitoolsFontColor) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //New Feature
        js.executeScript("document.getElementsByClassName(" + className +")" + "[" + indexNum +"].appendChild(document.createElement('a')).innerHTML = 'Applitools'.fontcolor(" + applitoolsFontColor+ ")");
    }
    public void removeElement(String xpathSelector) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Remove Element
        WebElement headerimage = driver.findElement(By.xpath(xpathSelector));
        js.executeScript("arguments[0].setAttribute('style', 'visibility:hidden')", headerimage);
    }
    public void simColorDiff(String cssSelector, String newcolor) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Color Change
        WebElement changecolor = driver.findElement(By.cssSelector(cssSelector));
        js.executeScript("arguments[0].setAttribute('style', 'color:" + newcolor + "')", changecolor);
    }
    public void simOverLapDiff(String cssSelector, int pixelsMarginRight) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Content Overlap
        WebElement overlapContent = driver.findElement(By.cssSelector(cssSelector));
        js.executeScript("arguments[0].setAttribute('style', 'margin-right:" + pixelsMarginRight + "px')", overlapContent);
    }
    public void simoverFontStyleDiff(String cssSelector, String newFont) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Font Styling Change
        WebElement changefont = driver.findElement(By.cssSelector(cssSelector));
        js.executeScript("arguments[0].setAttribute('style', 'font-family:" + newFont + "')", changefont);

    }
    public void disableTransition(String cssSelector) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        //Remove Element
        WebElement headerimage = driver.findElement(By.cssSelector(cssSelector));
        js.executeScript("arguments[0].setAttribute('style', 'transition:none')", headerimage);
    }
    //Disable Animation for given CSS
    public void disableAnimation(String csselector) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement animation = driver.findElement(By.cssSelector(csselector));
        js.executeScript("arguments[0].setAttribute('style', 'animation:none')", animation);
    }
    //Disable All Animation for a Page
    public void disableAllAnimation() throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("var disableAnimationStyles = '-webkit-transition: none !important;' +\n" +
                "                             '-moz-transition: none !important;' +\n" +
                "                             '-ms-transition: none !important;' +\n" +
                "                             '-o-transition: none !important;' +\n" +
                "                             'transition: none !important;' +\n" +
                "                             'transition-property: none !important;' +\n" +
                "                             '-o-transition-property: none !important;' +\n" +
                "                             '-moz-transition-property: none !important;' +\n" +
                "                             '-ms-transition-property: none !important;' +\n" +
                "                             '-webkit-transition-property: none !important;' +\n" +
                "                             'transform: none !important;' +\n" +
                "                             '-o-transform: none !important;' +\n" +
                "                             '-moz-transform: none !important;' +\n" +
                "                             '-ms-transform: none !important;' +\n" +
                "                             '-webkit-transform: none !important;' +\n" +
                "                             'animation: none !important;' +\n" +
                "                             '-o-animation: none !important;' +\n" +
                "                             '-moz-animation: none !important;' +\n" +
                "                             '-ms-animation: none !important;' +\n" +
                "                             '-webkit-animation: none !important;'\n" +
                "\n" +
                "\n" +
                "  var animationStyles = document.createElement('style');\n" +
                "  animationStyles.type = 'text/css';\n" +
                "  animationStyles.innerHTML = '* {' + disableAnimationStyles + '}';\n" +
                "  document.head.appendChild(animationStyles);\n" +
                "  $.fx.off = true;\n" +
                ";");
    }
    public void setBodyHeightAuto(String cssSelector) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement bodySelector = driver.findElement(By.cssSelector(cssSelector));
        js.executeScript("arguments[0].setAttribute('style', 'height:auto')", bodySelector);

    }
    public void setOverflowVisible(String cssSelector) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement bodySelector = driver.findElement(By.cssSelector(cssSelector));
        js.executeScript("arguments[0].setAttribute('style', 'overflow-y:auto')", bodySelector);
    }
    public void setPositionAbsolute(String xpathselector) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        WebElement bodySelector = driver.findElement(By.xpath(xpathselector));
        js.executeScript("arguments[0].setAttribute('style', 'position:absolute')", bodySelector);
    }
    public void AcceptCookies (String cssSelector) throws Exception {
        driver.findElement(By.cssSelector(cssSelector)).click();;
    }
    public void changeOrder() {
        String script = "var items = document.getElementsByClassName('item story');\n" +
                "items[2].parentNode.insertBefore(items[2], items[0]);\n";
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript(script);
    }


    public void lazyloading() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        Long height = (Long) (js.executeScript("return document.body.scrollHeight;"));
        for (int i = 0; i < height / 150; i++) {
            js.executeScript("window.scrollBy(0,100)");
            sleeptime(750);
        }
        js.executeScript("return document.body.scrollHeight", 0);
        js.executeScript("window.scrollTo(0,0)");
    }

    public void sleeptime(int timeToSleep) {
        try {
            Thread.sleep(timeToSleep);
        } catch (Exception e) {
            System.out.println(e);
        }

    }
    public void disableVideo() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript ("videos = document.querySelectorAll(\"video\"); \n" +
                "for(video of videos) {\n" +
                "  video.pause(); \n" +
                "}");
    }
    public void changeElementValue() {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript ("document.querySelector(\"#app > div > main > div > div > div > div > div > div > form > div.invalid-login\").innerText=\"Not the correct message\"");
    }
    protected static void handleTestResults(TestResultContainer summary) {
        TestResults result = summary.getTestResults();
        Throwable ex = summary.getException();
        if (ex != null) {
            System.out.printf("System error occured while checking target.\n");
        }

        if (result == null) {
            System.out.printf("No test results information available\n");
        } else {
            System.out.printf("AppName = %s\nTestname = %s\nBrowser = %s\nOS = %s\nViewport = %dx%d\nMatched = %d\nMismatched = %d\nMissing = %d\nAborted = %s\nAccessibility Status = %s\nDashboard URL = %s\n\n",
                    result.getAppName(),
                    result.getName(),
                    result.getHostApp(),
                    result.getHostOS(),
                    result.getHostDisplaySize().getWidth(),
                    result.getHostDisplaySize().getHeight(),
                    result.getMatches(),
                    result.getMismatches(),
                    result.getMissing(),
                    (result.isAborted() ? "aborted" : "No"),
                    result.getAccessibilityStatus().getStatus(),
                    result.getUrl());

        }
    }

    public void crawlUrlsCheck(String baseurl, String appName, String batchName) throws Exception {
        setupEyes(batchName, appName);
        driver.get(baseurl);
        //Create a list for all links on page
        List<WebElement> links = driver.findElements(By.xpath("//a"));
        System.out.println("Number of links by xpath: " + links.size());
        //Create hashset to include only the unique urls
        HashSet<String> hrefs = new HashSet<String>();
        links.forEach(
                (link) -> {
                    System.out.println(link.getAttribute("href"));
                    if (link.getAttribute("href") == null || link.getAttribute("href").length() == 0 ) {
                        //System.out.println("Link is Null or Blank");
                    }
                    else {
                        hrefs.add(link.getAttribute("href"));
                    }
                }
        );
        System.out.println("Number of Unique URLs: " + hrefs.size());
        //navigate through each url, take a screenshot, and go back to the base url
        for (String url : hrefs) {
            System.out.println("Navigating to " + url);
            driver.navigate().to(url);
            sleeptime(2000);
            eyes.open(driver, appName, url);
            eyes.check(url, Target.window());
            eyes.closeAsync();
            if (!url.equals(baseurl)) {
                driver.navigate().back();
                sleeptime(2000);
            }
        }
        closeEyes(false);
        closeDriver();

    }
    public static void setupEyes(String batchName, String appName) throws Exception {
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--headless", "--ignore-certificate-errors", "--window-size=1400,900", "--disable-web-security");
        driver=new ChromeDriver(chromeOptions);
        runner = new ClassicRunner();
        eyes = new Eyes(runner);
        BatchInfo batchname = new BatchInfo(batchName);
        Configuration sconf = new Configuration();
        sconf.setApiKey(System.getenv("APPLITOOLS_API_KEY_DEV"));
        sconf.setBatch(batchname);
        sconf.setStitchMode(StitchMode.CSS);
        sconf.setAccessibilityValidation(new AccessibilitySettings(AccessibilityLevel.AA, AccessibilityGuidelinesVersion.WCAG_2_0));
        eyes.setConfiguration(sconf);
    }
    public static void closeEyes(Boolean failure) throws Exception {
        TestResultsSummary allTestResults = runner.getAllTestResults(failure);
        System.out.println(allTestResults);
        eyes.abortIfNotClosed();
    }
    public static boolean validatePDF(String filepath, String AppName, String BatchName) throws IOException, InterruptedException {
        String command = String.format(
                "java -jar /Users/mkowalewski/JavaProjects/applitools-demo/src/test/resources/ImageTester_3.1.1.jar -k %s -a %s -fb %s -f %s",
                System.getenv("APPLITOOLS_API_KEY_SALES"),
                AppName,
                BatchName,
                filepath);

        Process process = Runtime.getRuntime().exec(command);
        process.waitFor();
        String stream = IOUtils.toString(process.getInputStream(), StandardCharsets.UTF_8);
        System.out.println(stream);

        return stream == null || !stream.contains("Mismatch");
    }

}