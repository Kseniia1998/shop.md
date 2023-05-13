package com.pages;

import com.models.Product;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Apparel extends Page {
    public Apparel() {
        super("/en/apparel");
    }

    @FindBy(xpath = "//div[@id='facet_6323']")
    private List<WebElement> sizeFilters;

    @FindBy(xpath = "//div[@id='_desktop_category_ordered']")
    private WebElement productOrderButton;

    @FindBy(xpath = "//section[@id='products']//div[@id='_desktop_category_ordered']//a")
    private List<WebElement> productOrderList;

    @FindBy(xpath = "//div[@data-target='#facet_6323']")
    private List<WebElement> sizeFiltersCollapse;

    @FindBy(xpath = "//div[@class='faceted-overlay']")
    private WebElement pageLoader;

    @FindBy(xpath = "//div[@data-id-product]")
    private List<WebElement> productCards;

    @FindBy(xpath = "//nav[@class='pagination']//a[@rel='nofollow']")
    private List<WebElement> pagination;
    @FindBy(xpath = "//nav[@class='pagination']//a[@rel='next']")
    private WebElement paginationNext;

    public void waitForPageLoading() {
        waitForElementNotVisible(pageLoader);
    }

    public int getProductCardCount() {
        return productCards.size();
    }

    public void setProductOrder(String order) {
        productOrderButton.click();
        waitForElementsVisible(productOrderList);
        productOrderList.stream()
                .filter((element) -> {
                    return element.getAttribute("href").contains(order);
                })
                .findFirst().ifPresent(WebElement::click);
        waitForPageLoading();
    }

    public List<Product> grabProductCardInfo() {
        List<Product> products = new ArrayList<>();
        for (WebElement pageNumber : pagination) {

            if (!pageNumber.getAttribute("class").contains("current")) {
                pageNumber.click();
                waitForPageLoading();
            }

            for(WebElement productCard : productCards){
                products.add(new Product(
                        productCard.findElement(By.xpath(".//span[@class='product-title']")).getText(),
                        Double.parseDouble(productCard.findElement(By.xpath(".//span[@class='price']")).getText().replaceAll("[^\\d.]", ""))
                ));
            }

        }
        return products;
    }


}
