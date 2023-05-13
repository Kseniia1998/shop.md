package steps;

import com.core.Utils;
import com.models.Product;
import com.pages.Apparel;
import io.cucumber.java.en.*;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.api.Assertions;
import com.google.common.collect.Ordering;
import org.testng.Assert;

import java.util.List;
import java.util.function.Function;

@Slf4j
@AllArgsConstructor
public class ApparelStepdefs {
    private Apparel apparelPage;

    @Given("I am on the {string} webpage")
    public void iAmOnTheWebpage(String pageName) {
        apparelPage.navigate();
    }

    @When("I choose to sort products by {string}")
    public void iChooseToSortProductsBy(String order) {
        apparelPage.setProductOrder(order);
    }

    @Then("the products should be displayed in {string} order of {string}")
    public void theProductsShouldBeDisplayedInOrderOf(String order, String productAttribute) {
        List<Product> products = apparelPage.grabProductCardInfo();
        boolean isSorted = Utils.isListSorted(products, productAttribute, order);
        Assert.assertTrue(isSorted,
                "Invalid Product List sorting by " + productAttribute +
                        ", ordered by " + order +
                        ". Product List: " + StringUtils.join(products, ", ")
        );
    }

    @When("I select the size {string}")
    public void iSelectTheSize(String arg0) {

    }

    @Then("I should see products available in size {string}")
    public void iShouldSeeProductsAvailableInSize(String arg0) {
    }

    @Then("the products should be displayed in ascending order of price")
    public void theProductsShouldBeDisplayedInAscendingOrderOfPrice() {
    }

    @When("I select the color {string}")
    public void iSelectTheColor(String arg0) {
    }

    @Then("I should see products available in the color {string}")
    public void iShouldSeeProductsAvailableInTheColor(String arg0) {
    }

    @When("I choose to sort products by rating from high to low")
    public void iChooseToSortProductsByRatingFromHighToLow() {
    }

    @Then("the products should be displayed in descending order of rating")
    public void theProductsShouldBeDisplayedInDescendingOrderOfRating() {
    }

    @When("I set the price range from {int} to {int}")
    public void iSetThePriceRangeFromTo(int arg0, int arg1) {
    }

    @Then("I should see products within the specified price range")
    public void iShouldSeeProductsWithinTheSpecifiedPriceRange() {
    }

}
