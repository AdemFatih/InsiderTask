package com.useinsider.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CareerPage extends BasePage{

    //for Locations block open or not
    @FindBy(xpath = "//h3[@class='category-title-media ml-0']")
    public WebElement ourLocationsIsAppears;

    //for Life at Insider block open or not
    @FindBy(xpath = "//h2[normalize-space()='Life at Insider']")
    public WebElement lifeAtInsiderIsAppears;

    //for Teams block open or not
    @FindBy(xpath = "//h3[normalize-space()='Find your calling']")
    public WebElement findYourCallingIsAppears;


    @FindBy(css = ".btn.btn-outline-secondary.rounded.text-medium.mt-5.mx-auto.py-3.loadmore")
    public WebElement seeAllTeamsButton;


    @FindBy(xpath = "//h3[normalize-space()='Quality Assurance']")
    public WebElement selectQualityAssurance;
}
