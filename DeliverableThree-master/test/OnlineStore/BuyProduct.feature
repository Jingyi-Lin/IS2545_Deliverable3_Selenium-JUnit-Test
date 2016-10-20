Feature: Buy Product
        As a user
        I would like to add products to cart
        So that I can make purchase on the website
 
        Scenario: Add iPhone 5 to cart
                Given I am on iPhone 5 page
                When I click "Add To Cart"
                Then a notify message should show as "You just added "iPhone 5" to your cart."
 
        Scenario: Change product quantity
                Given I have added an iPhone 5 to cart
                When I am on Checkout page and I update quantity to 2
                Then the total should be $24.00

        Scenario: Remove product
                Given I have added an iPhone 5 to cart
                When I am on Checkout page and I remove the product
                Then a message as "Oops, there is nothing in your cart." should show
