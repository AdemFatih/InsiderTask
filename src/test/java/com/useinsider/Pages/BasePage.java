package com.useinsider.Pages;

import com.useinsider.Utilities.Driver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public abstract class BasePage {
    public BasePage() {
        PageFactory.initElements(Driver.get(), this);
    }

    @FindBy(css = "body > nav:nth-child(4) > div:nth-child(2) > div:nth-child(3) > ul:nth-child(1) > li:nth-child(6) > a:nth-child(1)")
    public WebElement moreButton;

    //appears after clicking the more button
    @FindBy(xpath = "//h5[normalize-space()='Careers']")
    public WebElement careersButton;

}
