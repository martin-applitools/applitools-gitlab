import base.BaseClass;
import com.applitools.eyes.*;
import com.applitools.eyes.selenium.*;
import com.applitools.eyes.selenium.fluent.Target;
import com.applitools.eyes.visualgrid.model.*;
import com.applitools.eyes.visualgrid.services.RunnerOptions;
import com.applitools.eyes.visualgrid.services.VisualGridRunner;
import org.junit.*;
import org.junit.BeforeClass;
import org.junit.FixMethodOrder;
import org.junit.rules.TestName;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;

import java.net.MalformedURLException;
import java.util.UUID;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class Test extends BaseClass {
    static Eyes eyes;
    static EyesRunner runner = null;
    String appName = "McDonalds.com";
    static String batchName = "Web-Demo";
    static String batchSequence = "McDonalds";
    static String prospectName = "McDonalds";
    static Boolean useVisualGrid = false;
    static Boolean apiKeyDev = true;
    ;
    public Boolean useHeadless = true;

    static Boolean useCI = false;
    public Boolean useLocalization = false;
    public Boolean simulateDiffs = true;

    @Rule
    public final TestName testName = new TestName();

    @BeforeClass
    public static void beforeTestSuite() throws MalformedURLException {
        //
        Configuration sconf = new Configuration();
        //
        if (useVisualGrid) {
            runner = new VisualGridRunner(new RunnerOptions().testConcurrency(20));
            sconf.addBrowser(1400, 900, BrowserType.CHROME);
            sconf.addBrowser(1400, 900, BrowserType.CHROME_ONE_VERSION_BACK);
            sconf.addBrowser(1400, 900, BrowserType.CHROME_TWO_VERSIONS_BACK);
            sconf.addBrowser(1400, 900, BrowserType.FIREFOX);
            sconf.addBrowser(1400, 900, BrowserType.FIREFOX_ONE_VERSION_BACK);
            sconf.addBrowser(1400, 900, BrowserType.FIREFOX_TWO_VERSIONS_BACK);
            sconf.addBrowser(1400, 900, BrowserType.SAFARI);
            sconf.addBrowser(1400, 900, BrowserType.EDGE_CHROMIUM);
            sconf.addBrowser(1920, 1080, BrowserType.CHROME);
            sconf.addBrowser(1920, 1080, BrowserType.CHROME_ONE_VERSION_BACK);
            sconf.addBrowser(1920, 1080, BrowserType.CHROME_TWO_VERSIONS_BACK);
            sconf.addBrowser(1920, 1080, BrowserType.FIREFOX);
            sconf.addBrowser(1920, 1080, BrowserType.FIREFOX_ONE_VERSION_BACK);
            sconf.addBrowser(1920, 1080, BrowserType.FIREFOX_TWO_VERSIONS_BACK);
            sconf.addBrowser(1920, 1080, BrowserType.SAFARI);
            sconf.addDeviceEmulation(DeviceName.Pixel_4, ScreenOrientation.PORTRAIT);
            sconf.addBrowser(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro));
            sconf.addBrowser(new IosDeviceInfo(IosDeviceName.iPhone_12_Pro_Max));
        } else {
            runner = new ClassicRunner();
            sconf.setStitchMode(StitchMode.CSS);
        }
        //
        eyes = new Eyes(runner);
        //Update
        BatchInfo batchname = new BatchInfo(batchName);
        //
        batchname.setSequenceName(batchSequence);
        //
        batchname.setNotifyOnCompletion(true);
        //
        batchname.addProperty("Demo", prospectName);
        //Update
        if (useCI) {
            batchname.setId(System.getenv("APPLITOOLS_BATCH_ID"));
        }
        if (apiKeyDev) {
            sconf.setApiKey(System.getenv("APPLITOOLS_API_KEY_DEV"));
        }
        else {
            sconf.setApiKey(System.getenv("APPLITOOLS_API_KEY_SALES"));
        }
        ;
        //
        sconf.setBatch(batchname);
        //
        sconf.setMatchTimeout(0);
        //
        sconf.setAccessibilityValidation(new AccessibilitySettings(AccessibilityLevel.AA, AccessibilityGuidelinesVersion.WCAG_2_1));
        //
        eyes.setConfiguration(sconf);
    }

    @Before
    public void beforeEachTest() throws MalformedURLException {
        setupDriver(useHeadless);

    }
    @After
    public void afterEachTest() throws MalformedURLException {
        closeDriver();
    }

    @AfterClass
    public static void afterSuite() throws MalformedURLException {

        TestResultsSummary allTestResults = runner.getAllTestResults(false);
        System.out.println(allTestResults);
        //for (TestResultContainer result : allTestResults) handleTestResults(result);
        eyes.abortIfNotClosed();
    }

    @org.junit.Test
    public void NewFeature() throws Exception {
        eyes.open(driver, appName, testName.getMethodName());
        driver.get("https://www.mcdonalds.com/us/en-us.html");
        sleeptime(2000);
        AcceptCookies("#onetrust-close-btn-container > button");
        if(simulateDiffs) {
            addFeature("\'cmp-footer__nav-section'", 3, "\'#292929\'");
        }
        eyes.check("Home", Target.window().fully().ignoreDisplacements(true));
        eyes.close();
    }
    @org.junit.Test
    public void SimulatedDiffs() throws Exception {
        eyes.open(driver, appName, testName.getMethodName());
        driver.get("https://www.mcdonalds.com/us/en-us/full-menu/drinks.html");
        sleeptime(2000);
        AcceptCookies("#onetrust-close-btn-container > button");
        if(simulateDiffs) {
            simColorDiff("#container-d651edba8f > div > div > div > div > div.cmp-global-header__desktop-nav > div > div.cmp-global-header__primary-nav > div > nav > ul > li:nth-child(2) > a", "white");
        }
        eyes.check("Beverages", Target.window().fully());
        driver.get("https://www.mcdonalds.com/us/en-us/full-menu/breakfast.html");
        sleeptime(2000);
        if(simulateDiffs) {
            simoverFontStyleDiff("#product-category-a20918d941-daa8222b2f_name", "serif");
        }
        eyes.check("Breakfast", Target.window().fully().ignoreDisplacements(true));
        driver.get("https://www.mcdonalds.com/us/en-us/full-menu/burgers.html");
        sleeptime(2000);
        if(simulateDiffs) {
            simOverLapDiff("#maincatcontent > div.product-category.aem-GridColumn.aem-GridColumn--default--12 > ul > li:nth-child(1)", -95);
        }
        eyes.check("Burgers", Target.window().fully());
        eyes.close();
    }
    @org.junit.Test
    public void PassingTest() throws Exception {
        eyes.addProperty("Use-Case", "Passing Test");
        eyes.open(driver, appName, testName.getMethodName());
        driver.get("https://www.mcdonalds.com/us/en-us/mymcdonalds.html");
        sleeptime(2000);
        AcceptCookies("#onetrust-close-btn-container > button");
        eyes.check("My McDonalds", Target.window().fully());
        eyes.close();
    }
    @org.junit.Test
    public void Localization() throws Exception {
        eyes.addProperty("Use-Case", "Passing Test");
        eyes.open(driver, appName, testName.getMethodName());
        if (useLocalization) {
            driver.get("https://www.mcdonalds.com/us/es-us/deals.html");
        }
        else {
            driver.get("https://www.mcdonalds.com/us/en-us/deals.html");
        }
        sleeptime(2000);
        AcceptCookies("#onetrust-close-btn-container > button");
        eyes.check("Exclusive Deals", Target.window().fully().layout());
        eyes.close();
    }

    @org.junit.Test
    public void FunctionalTest() throws Exception {
        eyes.open(driver, appName, testName.getMethodName());
        driver.get("https://www.mcdonalds.com/us/en-us/restaurant-locator.html");
        sleeptime(2000);
        AcceptCookies("#onetrust-close-btn-container > button");
        eyes.check("Search Home", Target.window().fully().exact());
        driver.findElement(By.name("search-val")).sendKeys("73301");
        driver.findElement(By.xpath("//*[@id='button-93a5672f18']")).click();
        eyes.check("Search Results", Target.window().fully().waitBeforeCapture(2000));
        eyes.closeAsync();
    }
    @Ignore
    @org.junit.Test
    public void PixelbyPixel() throws Exception {
        eyes.open(driver, appName, testName.getMethodName());
        driver.get("https://www.mcdonalds.com/us/en-us/restaurant-locator.html");
        sleeptime(2000);
        AcceptCookies("#onetrust-close-btn-container > button");
        driver.findElement(By.name("search-val")).sendKeys("73301");
        driver.findElement(By.xpath("//*[@id='button-93a5672f18']")).click();
        sleeptime(2000);
        if(simulateDiffs) {
            simOverLapDiff("#container-4d7f535f5a > div > div > div > div.cmp-restaurant-locator__search-results.cmp-restaurant-locator__search-results--results-available > div.cmp-restaurant-locator__restaurants", -1);
        }
        eyes.check("PixelByPixel", Target.window().fully().exact());
        eyes.check("Strict", Target.window().fully().strict().ignoreDisplacements(true));
        eyes.close();
    }
    @Ignore
    @org.junit.Test
    public void PdfCompare() throws Exception {
        driver.get("https://www.analog.com/en/applications/markets/automotive-pavilion-home.html");
        sleeptime(2000);
        driver.findElement(By.xpath("//*[@id=\'cookie-consent-container\']/div/div/div[2]/div[1]/a")).click();
        sleeptime(1000);
        driver.findElement(By.cssSelector("#all-featured-resource > div > div > div > div > div.product-row > div > div > div > div:nth-child(3) > ul > li:nth-child(1) > a")).click();
        sleeptime(3000);
        validatePDF("./src/test/java/pdf_downloads", "Application_Notes", "PDF_Compare");
    }
}

