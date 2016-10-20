package OnlineStore;

import com.google.common.base.Predicate;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import java.util.concurrent.TimeUnit;
import static junit.framework.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 *
 * @author Jingyi Lin (jil173)
 */
public class StepDefinitions {
    private WebDriver driver;
    private WebDriverWait wait;
    
    private final String HOME_PAGE = "http://store.demoqa.com/";
    
    @Before
    public void setup(){
        System.setProperty("webdriver.gecko.driver", "libs\\geckodriver.exe");
        driver = new FirefoxDriver();
        wait = new WebDriverWait(driver, 30);
    }
    
    // ------------------------------------------------------
    //  Feature 1: View Page (ViewPages.feature)
    //      As a user
    //      I would like to view all the pages smoothly
    //      So that I can use the website properly
    // ------------------------------------------------------
    
    //  Scenario 1: View Product Category Page
    @Given("I am on Home page")
    public void onHome() throws Throwable {
        driver.get(HOME_PAGE);
    }
    
    @When("I navigate to Product Category page")
    public void navigateProdCat() throws Throwable {
        WebElement label = driver.findElement(By.xpath("//*[@id=\"menu-item-33\"]/a"));
        label.click();
    }
    
    @Then("the page title should be (.*)")
    public void checkProdCatTitle(String title) throws Throwable { 
          wait.until(ExpectedConditions.titleContains(title));
    }   
    
    //  Scenario 2: Back to Home Page
    @Given("I am on account register page")
    public void onRegister() {
        driver.get("http://store.demoqa.com/tools-qa/?action=register");
    }
    
    @When("I click \"Back to ONLINE STORE\"")
    public void backHome() {
        WebElement label = driver.findElement(By.xpath("//*[@id=\"backtoblog\"]/a"));
        label.click();
    }
    
    @Then("the title of page should be (.*)")
    public void checkHome(String title) throws InterruptedException {
          wait.until(ExpectedConditions.titleContains(title));
    }
    
    //  Scenario 3: See more product information
    @Given("I am on Product Categoty page")
    public void onProdCat() {
        driver.get("http://store.demoqa.com/products-page/product-category");
    } 
    
    @When("I want to see more info about iPhone 5 and click \"iPhone 5\"")
    public void checkIphone5() {
        WebElement label = driver.findElement(By.xpath("//*[@id=\"default_products_page_container\"]/div[3]/div[2]/h2/a"));
        label.click();
    }          
    
    @Then("the page should has a title as (.*)")
    public void checkProduct(String title) throws InterruptedException {
          wait.until(ExpectedConditions.titleContains(title));
    }
    
    //  Scenario 4: Search a product
    @When("I enter (.*) in the search box")
    public void searchMouse(String searchTerm) {
        WebElement label = driver.findElement(By.name("s"));
        label.clear();
        label.sendKeys(searchTerm);
        label.sendKeys(Keys.ENTER);
    }
    
    @Then("a product named (.*) should show")
    public void showSearch(String productName) throws InterruptedException{
        wait.until((Predicate<WebDriver>)d -> d.findElement(By.xpath("//*[@id=\"grid_view_products_page_container\"]/div/div/div[2]/h2/a")).isDisplayed());
        WebElement prod = driver.findElement(By.xpath("//*[@id=\"grid_view_products_page_container\"]/div/div/div[2]/h2/a"));
        assertEquals(productName, prod.getText());
    }

    // ---------------------------------------------------------------
    //  Feature 2: Manage Account (ManageAccount.feature)
    //       As a user
    //       I would like to manage my account
    //       So that I can access my account while using the website
    // ---------------------------------------------------------------
    
    //  Scenario 1: Successful Login with Valid Credentials    
    private void logIn(String name, String psw) {
        WebElement username = driver.findElement(By.xpath("//*[@id=\"log\"]"));
        username.sendKeys(name);
        WebElement password = driver.findElement(By.xpath("//*[@id=\"pwd\"]"));
        password.sendKeys(psw);
        WebElement submit = driver.findElement(By.xpath("//*[@id=\"login\"]"));
        submit.sendKeys(Keys.ENTER);
    }
    
    @Given("I am on My Account page")
    public void onAccount() {
        driver.get("http://store.demoqa.com/products-page/your-account/");
    }
    
    @When("I enter correct Username and Password")
    public void enterLogin() {
        String correctName = "jingyitest";
        String correctPsw = "jingyitest";
        
        logIn(correctName, correctPsw);
    }
    
    @Then("the welcome message \"(.*)\" should show in top-right corner")
    public void showID(String message) throws InterruptedException {
        wait.until((Predicate<WebDriver>)d -> d.findElement(By.xpath("//*[@id=\"wp-admin-bar-my-account\"]/a")).isDisplayed());
        WebElement welcome = driver.findElement(By.xpath("//*[@id=\"wp-admin-bar-my-account\"]/a"));
        assertEquals(message, welcome.getText());
    }
    
    //  Scenario 2: Successful Logout
    @When("I login the website with correct username and password, and click logout")
    public void logout() throws InterruptedException {
        String correctName = "jingyitest";
        String correctPsw = "jingyitest";
        
        logIn(correctName, correctPsw);
              
        Thread.sleep(6000);
        WebElement label = driver.findElement(By.xpath("//*[@id=\"meta\"]/ul/li[2]/a"));
        label.click();
    }
    
    @Then("there should be a message show as \"(.*)\"")
    public void logoutMsg(String message) throws InterruptedException {
        wait.until((Predicate<WebDriver>)d -> d.findElement(By.xpath("//*[@id=\"login\"]/p[1]")).isDisplayed());
        WebElement logout = driver.findElement(By.xpath("//*[@id=\"login\"]/p[1]"));
        assertEquals(message, logout.getText());
    }
    
    //  Scenario 3: Unsuccessful Login with Invalid Credentials
    @When("I enter correct Username but incorrect Password")            
    public void enterWrongLogin() {
        String correctName = "jingyitest";
        String incorrectPsw = "test";
        
        logIn(correctName, incorrectPsw);
    }
    
    @Then("a message should show as \"(.*)\"")
    public void loginMsg(String message) throws InterruptedException {
        wait.until((Predicate<WebDriver>)d -> d.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]")).isDisplayed());
        WebElement login = driver.findElement(By.xpath("//*[@id=\"ajax_loginform\"]/p[1]"));
        assertEquals(message, login.getText());
    }
    
    // --------------------------------------------------
    //  Feature 3: Buy Product (BuyProduct.feature)
    //      As a user
    //      I would like to add products to cart
    //      So that I can make purchase on the website
    // --------------------------------------------------
    
    //  Scenario 1: Add iPhone 5 to cart
    private void buyPhone() {
       WebElement label = driver.findElement(By.name("Buy"));
       label.click();
    }
    
    @Given("I am on iPhone 5 page")
    public void onPhone() {
        driver.get("http://store.demoqa.com/products-page/product-category/n/");
    } 
    
    @When("I click \"Add To Cart\"")
    public void addCart() {
        buyPhone();
    }
    
    @Then("a notify message should show as \"(.*)\"")
    public void cartMsg(String message) throws InterruptedException {
        wait.until((Predicate<WebDriver>)d -> d.findElement(By.xpath("//*[@id=\"fancy_notification_content\"]/span")).isDisplayed());
        WebElement cartMsg = driver.findElement(By.xpath("//*[@id=\"fancy_notification_content\"]/span"));
        assertEquals(message, cartMsg.getText());
    }
    
    //  Scenario 2: Change product quantity
    @Given("I have added an iPhone 5 to cart")
    public void onCheckOut() {
        driver.get("http://store.demoqa.com/products-page/product-category/n/");
        buyPhone();
    }
    
    @When("I am on Checkout page and I update quantity to 2")
    public void updateQuan() {
        driver.get("http://store.demoqa.com/products-page/checkout/");
        
        WebElement quantity = driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[3]/form/input[1]"));
        quantity.clear();
        quantity.sendKeys("2");
        WebElement update = driver.findElement(By.name("submit"));
        update.sendKeys(Keys.ENTER);
    }
    
    @Then("the total should be (.*)")
    public void total(String value) throws InterruptedException {
        Thread.sleep(6000);
        WebElement total = driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[5]/span/span"));
        assertEquals(value, total.getText());
    }
    
    //  Scenario 3: Remove product
    @When("I am on Checkout page and I remove the product")
    public void remove() {
        driver.get("http://store.demoqa.com/products-page/checkout/");
        
        WebElement remove = driver.findElement(By.xpath("//*[@id=\"checkout_page_container\"]/div[1]/table/tbody/tr[2]/td[6]/form/input[4]"));
        remove.click();
    }           
    
    @Then("a message as \"(.*)\" should show")
    public void nothingInCart(String message) throws InterruptedException {
        Thread.sleep(6000);
        WebElement removeMsg = driver.findElement(By.xpath("//*[@id=\"post-29\"]/div"));
        assertEquals(message, removeMsg.getText());
    }
    
    @After
    public void cleanUp() {
        driver.quit();
    }
}
