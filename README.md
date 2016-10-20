# IS2545 - Deliverable3
In this assignment, I used a Windows laptop and applied Cucumber, Selenium tests and JUnit assertions to generate the automated test.  

At first, when I was viewing the project Professor created on GitHub, I felt very confused about what are the feature file and the stepdefinitions file, and how to use them. At that time, I have very low knowledge about cucumber testing and selenium webdriver. Later after I asked Professor about basic concepts of cucumber testing, and searched online to read some documentations, I get more familiar about this automated testing process.

When I was writing stepdefinitions for my very first senario - view product category page, there was a click() method called by a WebElement. Each time I run the test file, it failed because it cannot catch the new web page's title as I set. I first thought maybe the problem is the incorrect use of click(), and I searched and tried several solutions, while they all failed. Then I realized that the reason could be there were not enough wait time for the web page to respond.

I tried many ways to adjust various set of explicit wait before the assertion - WebDriverWait wait = new WebDriverWait(driver, 50); but no one can work. I also used this kind of wait - driver.manage().timeouts().implicitlyWait(50, TimeUnit.SECONDS); but it cannot work either. Then I tried to use Thred.sleep(), which is not a good method due to waste too much resources, but it can works. However, I did not think this was a good solution and there should be more solutions to solve the wait problem.

Finally I asked Professor to get some suggestions. Good news, a new way to write wait until can work - wait.until((Predicate<WebDriver>)d -> d.findElement(By.xpath("xxx")).isDisplayed());. Later I searched online and get another way to express wait until which can work perfectly on catching a page's title - wait.until(ExpectedConditions.titleContains(title));.

It is good for me that I could get rid of Thread.sleep() to make the code wait. However, in some scenarios, like change product quantity in checkout page, maybe because the javascript cannot load as fast as the findElement in wait until, so I have to use Thread.sleep() at these situations. 

In summary, I have three user stories (features) in total - View Page, Manage Account, and Buy Product. 
In View Page feature, I write four senarios, including go and back through pages, view product information and search for a product. 
In Manage Account feature, there are three senarios, including successfully and unsuccessfully log in, and log out.
In Buy Product feature, three aspects of operation are tested, including buy a product, modify purchase quantity and remove a product from shopping cart.
