import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

public class WebTests {

    @Test
    public void testBrowseLanguagesSubmenuJ() {

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\ChromeDriver\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";

        String expectedResult
                = "All languages starting with the letter J are shown, sorted by Language.";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//li/a[@href='j.html']")).click();

        String actualResult
                = driver.findElement(
                        By.xpath("//div[@id='main']/p")
                )
                .getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testMenuBrowseLanguagesTableHeaders() {

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\ChromeDriver\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";

        String expectedResult1 = "Language";
        String expectedResult2 = "Author";
        String expectedResult3 = "Date";
        String expectedResult4 = "Comments";
        String expectedResult5 = "Rate";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();

        String[] tableHeaders = new String[5];

        for (int i = 0; i < tableHeaders.length; i++) {
            int ind = i + 1;
            tableHeaders[i] = driver
                    .findElement(
                            By.xpath("//table[@id='category']/tbody/tr/th[" + ind + "]")
                    )
                    .getText();
        }

        String actualResult1 = tableHeaders[0];
        String actualResult2 = tableHeaders[1];
        String actualResult3 = tableHeaders[2];
        String actualResult4 = tableHeaders[3];
        String actualResult5 = tableHeaders[4];

        Assert.assertEquals(actualResult1, expectedResult1);
        Assert.assertEquals(actualResult2, expectedResult2);
        Assert.assertEquals(actualResult3, expectedResult3);
        Assert.assertEquals(actualResult4, expectedResult4);
        Assert.assertEquals(actualResult5, expectedResult5);

        driver.quit();
    }

    @Test
    public void testBrowseLanguagesSubmenuMLastProgramIsLangMySQL() {

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\ChromeDriver\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";

        String expectedResult = "MySQL";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);
        driver.findElement(By.xpath("//li/a[@href='/abc.html']")).click();
        driver.findElement(By.xpath("//li/a[@href='m.html']")).click();

        String actualResult
                = driver.findElement(
                        By.xpath(
                                "//td/a[@href='language-mysql-1252.html']")
                )
                .getText();

        Assert.assertEquals(actualResult, expectedResult);

        driver.quit();
    }

    @Test
    public void testHeadOnBaseUrl() {

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\ChromeDriver\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";

        String expectedResult = "99 Bottles of Beer";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

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

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\ChromeDriver\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";

        String expectedResult = "Submit new Language".toUpperCase();

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);

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

        String chromeDriver = "webdriver.chrome.driver";
        String driverPath = "C:\\ChromeDriver\\chromedriver.exe";
        String url = "http://www.99-bottles-of-beer.net/";

        String expectedResult = "Submit New Language";

        System.setProperty(chromeDriver, driverPath);
        WebDriver driver = new ChromeDriver();

        driver.get(url);
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
}
