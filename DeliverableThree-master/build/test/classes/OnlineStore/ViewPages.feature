Feature: View Pages
        As a user
        I would like to view all the pages smoothly
        So that I can use the website properly
 
        Scenario: View Product Category Page
                Given I am on Home page
                When I navigate to Product Category page
                Then the page title should be Product Category | ONLINE STORE

        Scenario: Back to Home Page
                Given I am on account register page
                When I click "Back to ONLINE STORE"
                Then the title of page should be ONLINE STORE | Toolsqa Dummy Test site

        Scenario: See more product information
                Given I am on Product Categoty page
                When I want to see more info about iPhone 5 and click "iPhone 5"
                Then the page should has a title as iPhone 5 | ONLINE STORE

        Scenario: Search a product
                Given I am on Home page
                When I enter Magic Mouse in the search box
                Then a product named Magic Mouse should show