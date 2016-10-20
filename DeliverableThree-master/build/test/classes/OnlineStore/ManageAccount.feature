Feature: Manage Account
        As a user
        I would like to manage my account
        So that I can access my account while using the website
 
        Scenario: Successful Login with Valid Credentials
                Given I am on My Account page
                When I enter correct Username and Password
                Then the welcome message "Howdy, jingyitest" should show in top-right corner
 
        Scenario: Successful Logout
                Given I am on My Account page
                When I login the website with correct username and password, and click logout
                Then there should be a message show as "You are now logged out."

        Scenario: Unsuccessful Login with Invalid Credentials
                Given I am on My Account page
                When I enter correct Username but incorrect Password
                Then a message should show as "ERROR: Invalid login credentials."

