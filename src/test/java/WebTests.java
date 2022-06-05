import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

public class WebTests {

    private static final String BASE_URL = "http://www.99-bottles-of-beer.net/";
    private static final String CHROME_DRIVER = "webdriver.chrome.driver";
    private static final String DRIVER_PATH = "C:\\ChromeDriver\\chromedriver.exe";

    private static final String TRS_XPATH = "//table[@id='category']/tbody/tr";
    private static final String LANG_INFO = ". Shakespeare Jonas Sj�bergh 05/18/05 6";
    private static final String WORD_SHAKESPEARE = "Shakespeare";

    static List<String> fillList(List<WebElement> trs, List<String> table) {
        for (WebElement tr : trs) {
            table.add(tr.getText());
        }

        return table;
    }

    static int findActualResult(List<String> table, int actRes, String td) {
        for (int i = 0; i < table.size(); i++) {
            if (table.get(i).contains(WORD_SHAKESPEARE)) {
                actRes = table.indexOf(td);
            }
        }

        return actRes;
    }

    static String generateName(int lengthName) {

        String letters = "abcdefghijklmnopqrstuvwxyz -'ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder name = new StringBuilder();

        for (int i = 0; i < lengthName; i++) {
            int index = (int) (Math.random() * letters.length());
            name.append(letters.charAt(index));
        }

        return name.toString();
    }

    static String generateLocation(int lengthLocation) {

        String letters = "abcdefghijklmnopqrstuvwxyz -'ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        StringBuilder location = new StringBuilder();

        for (int i = 0; i < lengthLocation; i++) {
            int index = (int) (Math.random() * letters.length());
            location.append(letters.charAt(index));

        }

        return location.toString();
    }

    static StringBuilder siteName(int lengthSiteName) {

        StringBuilder siteName = new StringBuilder();
        String letters = "abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        for (int i = 0; i < lengthSiteName; i++) {
            int index = (int) (Math.random() * letters.length());
            siteName.append(letters.charAt(index));
        }

        return siteName;
    }

    static StringBuilder domain(int lengthDomain) {

        StringBuilder domain = new StringBuilder();
        String letters = "abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        letters.replace("-", "");

        for (int i = 0; i < lengthDomain; i++) {
            int index = (int) (Math.random() * letters.length());
            domain.append(letters.charAt(index));
        }

        return domain;

    }

    static String generateEmail(int lengthLocalPart, int lengthSiteName, int lengthDomain) {

        String letters = "abcdefghijklmnopqrstuvwxyz-ABCDEFGHIJKLMNOPQRSTUVWXYZ";
        String dot = ".";

        StringBuilder email = new StringBuilder();

        for (int i = 0; i < lengthLocalPart; i++) {
            int index = (int) (Math.random() * letters.length());
            email.append(letters.charAt(index));
        }

        email
                .append("@")
                .append(siteName(lengthSiteName))
                .append(dot)
                .append(domain(lengthDomain));

        return email.toString().toLowerCase();
    }

    static String generateHomepageAddress(int lengthSiteName, int lengthDomain) {

        final String W3 = "www";
        String dot = ".";

        String homepage
                = W3
                .concat(dot)
                .concat(siteName(lengthSiteName).toString())
                .concat(dot)
                .concat(domain(lengthDomain).toString())
                .toLowerCase();

        return homepage;
    }

    static String generateMessage(int lengthMessage) {

        String letters
                = "abcdefghijklmnopqrstuvwxyz .,-'?:;ABCDEFGHIJKLMNOPQRSTUVWXYZ";

        StringBuilder message = new StringBuilder();

        for (int i = 0; i < lengthMessage; i++) {
            int index = (int) (Math.random() * letters.length());
            message.append(letters.charAt(index));
        }

        return message.toString();
    }

    static String generateThreeDigitCode(int start, int end) {

        int code3digit = 0;

        if (start >= 0 && end >= 0) {
            code3digit = (int) (Math.random() * end);
        }

        return String.valueOf(code3digit);
    }

    @Test
    public void testBrowseLanguagesSubmenuJ() {

        String expectedResult
                = "All languages starting with the letter J are shown, sorted by Language.";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        driver.findElement(By.linkText("J")).click();

        String actualResult
                = driver.findElement(
                        By.xpath("//table[@id='category']/preceding-sibling::p")
                )
                .getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testMenuBrowseLanguagesTableHeaders() {

        String expectedResult1 = "Language";
        String expectedResult2 = "Author";
        String expectedResult3 = "Date";
        String expectedResult4 = "Comments";
        String expectedResult5 = "Rate";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();

        List<WebElement> ths
                = driver.findElements(By.xpath("//table[@id='category']/tbody/tr/th"));

        List<String> actualResult = new ArrayList<>();
        for (WebElement th : ths) {
            actualResult.add(th.getText());
        }

        Assert.assertEquals(actualResult.size(), 5);
        Assert.assertEquals(actualResult.get(0), expectedResult1);
        Assert.assertEquals(actualResult.get(1), expectedResult2);
        Assert.assertEquals(actualResult.get(2), expectedResult3);
        Assert.assertEquals(actualResult.get(3), expectedResult4);
        Assert.assertEquals(actualResult.get(4), expectedResult5);

        driver.quit();
    }

    @Test
    public void testBrowseLanguagesSubmenuMLastLanguageIsLangMySQL() {

        String expectedResult = "MySQL";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        driver.findElement(By.linkText("M")).click();

        List<WebElement> trs
                = driver.findElements(By.xpath("//table[@id='category']/tbody/tr"));
        List<String> languages = new ArrayList<>();

        String trContainsMySQL = "";

        for (WebElement tr : trs) {
            languages.add(tr.getText());
            if (languages.get(languages.size() - 1).contains(expectedResult)) {
                trContainsMySQL = languages.get(languages.size() - 1).toString();
            }
        }

        String[] containsMySQL = new String[]{};
        containsMySQL = trContainsMySQL.split(" ");
        String actualResult = containsMySQL[0].toString();

        Assert.assertTrue(!containsMySQL.toString().isEmpty());
        Assert.assertEquals(containsMySQL.length, 5);
        Assert.assertEquals(expectedResult, actualResult);

        driver.quit();
    }

    @Test
    public void testHeadOnBaseUrl() {

        String expectedResult = "99 Bottles of Beer";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);

        String actualResult
                = driver.findElement(
                        By.xpath(
                                "//div[@id='header']/h1")
                )
                .getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testSubmitNewLanguageInMenuOnBaseUrl() {

        String expectedResult = "Submit new Language".toUpperCase();

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);

        String actualResult
                = driver.findElement(
                        By.xpath(
                                "//li/a[@href='/submitnewlanguage.html']")
                )
                .getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testSubtitleSubmitNewLanguage() {

        String expectedResult = "Submit New Language";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//li/a[@href='/submitnewlanguage.html']")).click();

        String actualResult
                = driver.findElement(
                        By.xpath(
                                "//li/a[@href='./submitnewlanguage.html']")
                )
                .getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testMathematicaLanguageInformation() {

        String languageExpected = "Mathematica";
        String authorExpected = "Brenton Bostick";
        String dataExpected = "03/16/06";
        String commentsExpected = "1";

        StringBuilder expectedResult = new StringBuilder();
        expectedResult
                .append(languageExpected)
                .append(" ")
                .append(authorExpected)
                .append(" ")
                .append(dataExpected)
                .append(" ")
                .append(commentsExpected);

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        driver.findElement(By.linkText("M")).click();

        List<WebElement> trs
                = driver.findElements(
                By.xpath(
                        "//table[@id='category']/tbody/tr")
        );

        List<String> actualResult = new ArrayList<>();
        for (WebElement tr : trs) {
            if (tr.getText().contains(languageExpected)) {
                actualResult.add(tr.getText());
            }
        }

        Assert.assertTrue(!actualResult.get(0).isEmpty());
        Assert.assertEquals(actualResult.size(), 1);
        Assert.assertEquals(actualResult.get(0), expectedResult.toString());

        driver.quit();
    }

    @Test
    public void testNamesNumbersOfLanguages() {

        int expectedResult = 10;

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        driver.findElement(By.linkText("0-9")).click();

        List<WebElement> trs
                = driver.findElements(
                By.xpath(
                        "//table[@id='category']/tbody/tr")
        );
        List languagesNamesNumbers = new ArrayList<>();

        for (WebElement tr : trs) {
            languagesNamesNumbers.add(tr.getText());
        }
        languagesNamesNumbers.remove(0);

        int actualResult = languagesNamesNumbers.size();

        Assert.assertFalse(languagesNamesNumbers.isEmpty());
        Assert.assertEquals(expectedResult, actualResult);

        driver.quit();
    }

    @Test
    public void testLanguageShakespeareSolutions() {

        int expectedAllTop20 = 17;
        int expectedEsotericTop10 = 8;
        int expectedHitsTop6 = 6;
        int expectedRealTop0 = 0;

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(
                        By.xpath(
                                "//li/a[@href='/toplist.html']")
                )
                .click();

        List<WebElement> trsAll
                = driver.findElements(
                By.xpath(TRS_XPATH)
        );

        List<String> topRated = new ArrayList<>();
        fillList(trsAll, topRated);

        String placeInAllTop20
                = String.valueOf(expectedAllTop20)
                .concat(LANG_INFO);

        driver.findElement(
                        By.linkText(
                                "Top Rated Esoteric")
                )
                .click();

        List<WebElement> trsEso
                = driver.findElements(
                By.xpath(TRS_XPATH)
        );

        List<String> topEso = new ArrayList<>();
        fillList(trsEso, topEso);

        String placeInEsotericTop10
                = String.valueOf(expectedEsotericTop10)
                .concat(LANG_INFO);

        driver.findElement(
                        By.linkText(
                                "Top Hits")
                )
                .click();

        List<WebElement> trsHits
                = driver.findElements(
                By.xpath(TRS_XPATH)
        );

        List<String> topHits = new ArrayList<>();
        fillList(trsHits, topHits);

        String HitsTop6
                = String.valueOf(expectedHitsTop6)
                .concat(LANG_INFO);

        driver.findElement(
                        By.linkText(
                                "Top Rated Real")
                )
                .click();

        List<WebElement> trsReal
                = driver.findElements(
                By.xpath(TRS_XPATH)
        );

        List<String> topReal = new ArrayList<>();
        fillList(trsReal, topReal);

        String RealTop0
                = String.valueOf(expectedRealTop0)
                .concat(LANG_INFO);

        int actualAllTop20 = 0;
        int actualEsotericTop10 = 0;
        int actualHitsTop6 = 0;
        int actualRealTop0 = 0;

        Assert.assertTrue(!topRated.get(0).isEmpty());
        Assert.assertEquals(
                findActualResult(topRated, actualAllTop20, placeInAllTop20),
                expectedAllTop20
        );
        Assert.assertTrue(!topEso.get(0).isEmpty());
        Assert.assertEquals(
                findActualResult(topEso, actualEsotericTop10, placeInEsotericTop10),
                expectedEsotericTop10
        );
        Assert.assertTrue(!topHits.get(0).isEmpty());
        Assert.assertEquals(
                findActualResult(topHits, actualHitsTop6, HitsTop6),
                expectedHitsTop6
        );
        Assert.assertFalse(topReal.get(0).isEmpty());
        Assert.assertEquals(
                findActualResult(topReal, actualRealTop0, RealTop0),
                expectedRealTop0
        );

        driver.quit();
    }

    @Test
    public void testErrorSignGuestbookRandomGeneratedThreeDigitNumber() {

        String expectedResult = "Error: Error: Invalid security code.";

        System.setProperty(CHROME_DRIVER, DRIVER_PATH);
        WebDriver driver = new ChromeDriver();

        driver.get(BASE_URL);
        driver.findElement(
                        By.xpath(
                                "//li/a[@href='/guestbookv2.html']")
                )
                .click();

        driver.findElement(
                        By.linkText(
                                "Sign Guestbook")
                )
                .click();

        WebElement nameField
                = driver
                .findElement(
                        By.xpath(
                                "//p/input[@name='name']")
                );

        nameField.sendKeys(
                generateName(50).toString()
        );

        WebElement locationField
                = driver
                .findElement(
                        By.xpath(
                                "//p/input[@name='location']")
                );

        locationField.sendKeys(
                generateLocation(85)
        );

        WebElement emailField
                = driver
                .findElement(
                        By.xpath("//p/input[@name='email']")
                );

        emailField.sendKeys(
                generateEmail(13, 20, 3)
        );

        WebElement homepageField
                = driver
                .findElement(
                        By.xpath("//p/input[@name='homepage']")
                );

        homepageField.sendKeys(
                generateHomepageAddress(20, 3)
        );

        WebElement message
                = driver
                .findElement(
                        By.xpath("//p/textarea[@name='comment']")
                );

        message.sendKeys(
                generateMessage(256)
        );

        WebElement securityCode
                = driver.findElement(
                By.xpath("//p/input[@name='captcha']")
        );

        securityCode.sendKeys(generateThreeDigitCode(0, 999));

        driver.findElement(
                        By.xpath(
                                "//p/input[@name='submit']")
                )
                .click();

        WebElement actualResult
                = driver.findElement(
                By.xpath(
                        "//div[@id='main']/p")
        );

        Assert.assertEquals(actualResult.getText(), expectedResult);

        driver.quit();
    }
}
