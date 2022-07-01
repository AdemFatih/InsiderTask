package com.useinsider.Pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class OpenPositionsPage extends BasePage {

    @FindBy(id = "filter-by-location")
    public WebElement filterLocation;

    @FindBy(id = "filter-by-department")
    public WebElement filterDepartment;

    // return 4 job of list
    @FindBy(css = "div[class='position-list-item-wrapper bg-light']")
    public List<WebElement> listOfCheckJob;

    @FindBy(xpath = "//p[@class='position-title font-weight-bold']")
    public List<WebElement> listOfPositionJob;

    @FindBy(xpath = "//span[@class='position-department text-large font-weight-600 text-primary']")
    public List<WebElement> listOfDepartmentJob;

    @FindBy(css = "div[class='position-location text-large']")
    public List<WebElement> listOfLocationJob;

    @FindBy(css = "a[class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5']")
    public List<WebElement> listOfApplyButton;

    @FindBy(css = "a[class='btn btn-navy rounded pt-2 pr-5 pb-2 pl-5']")
    public WebElement applyNowButton;
}

