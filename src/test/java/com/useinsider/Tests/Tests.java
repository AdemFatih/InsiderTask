package com.useinsider.Tests;

import com.useinsider.Pages.CareerPage;
import com.useinsider.Pages.OpenPositionsPage;
import com.useinsider.Pages.QualityAssurancePage;
import com.useinsider.Utilities.BrowserUtils;
import com.useinsider.Utilities.Driver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.util.ArrayList;

public class Tests extends TestBase {

    CareerPage careerPage = new CareerPage();
    QualityAssurancePage qualityAssurancePage = new QualityAssurancePage();
    OpenPositionsPage openPositionsPage = new OpenPositionsPage();

    @Test(priority = 0)
    public void step_1() {
        Driver.get().get(url);
        Assert.assertEquals(Driver.get().getTitle(), "Insider personalization engine for seamless customer experiences");
    }

    @Test(priority = 1)
    public void step_2() {
        Driver.get().get(url);
        BrowserUtils.waitFor(4);
        careerPage.moreButton.click();
        BrowserUtils.waitForClickablility(careerPage.careersButton, 10);
        careerPage.careersButton.click();
        Assert.assertEquals(careerPage.ourLocationsIsAppears.getText(), "Our Locations");
        Assert.assertEquals(careerPage.lifeAtInsiderIsAppears.getText(), "Life at Insider");
        Assert.assertEquals(careerPage.findYourCallingIsAppears.getText(), "Find your calling");

    }

    @Test(dependsOnMethods = "step_2")
    public void step_3() {
        BrowserUtils.scrollToElement(careerPage.seeAllTeamsButton);
        BrowserUtils.clickWithJS(careerPage.seeAllTeamsButton);
        BrowserUtils.scrollToElement(careerPage.selectQualityAssurance);
        BrowserUtils.clickWithJS(careerPage.selectQualityAssurance);
        BrowserUtils.clickWithJS(qualityAssurancePage.SeeallQAjobsButton);
        Select stateDropdown = new Select(openPositionsPage.filterLocation);
        stateDropdown.selectByVisibleText("Istanbul, Turkey");
        Select stateDropdown2 = new Select(openPositionsPage.filterDepartment);
        stateDropdown2.selectByVisibleText("Quality Assurance");
        Assert.assertTrue(openPositionsPage.listOfCheckJob.size() > 0);

    }

    @Test(dependsOnMethods = {"step_2", "step_3"})
    public void step_4_1() {
        for (int i = 0; i < openPositionsPage.listOfPositionJob.size(); i++) {
            //Position of all work here should continue with "Quality Assurance". But some jobs contain keywords like "QA Tester".
            // I wanted to get the different ones as an error, as the Task specifically stated "Quality Assurance".
            Assert.assertTrue(openPositionsPage.listOfPositionJob.get(i).getAttribute("textContent").contains("Quality Assurance"));
        }
    }

    @Test(dependsOnMethods = {"step_2", "step_3"})
    public void step_4_2() {
        for (int i = 0; i < openPositionsPage.listOfDepartmentJob.size(); i++) {
            Assert.assertTrue(openPositionsPage.listOfDepartmentJob.get(i).getAttribute("textContent").contains("Quality Assurance"));
        }
    }

    @Test(dependsOnMethods = {"step_2", "step_3"})
    public void step_4_3() {
        for (int i = 0; i < openPositionsPage.listOfLocationJob.size(); i++) {

            Assert.assertTrue(openPositionsPage.listOfLocationJob.get(i).getAttribute("textContent").contains("Istanbul, Turkey"));

        }
    }

    @Test(dependsOnMethods = {"step_2", "step_3"})
    public void step_4_4() {
        for (int i = 0; i < openPositionsPage.listOfApplyButton.size(); i++) {

            Assert.assertTrue(openPositionsPage.listOfApplyButton.get(i).getAttribute("textContent").contains("Apply Now"));

        }

    }

    @Test(dependsOnMethods = {"step_2", "step_3"})
    public void step_5() {

        BrowserUtils.scrollToElement(openPositionsPage.applyNowButton);
        BrowserUtils.clickWithJS(openPositionsPage.applyNowButton);
        BrowserUtils.waitForPageToLoad(5);
        Assert.assertEquals(Driver.get().getWindowHandles().size(),2);
        Driver.get().switchTo().window(new ArrayList<>(Driver.get().getWindowHandles()).get(1));
        Assert.assertTrue(Driver.get().getCurrentUrl().startsWith("https://jobs.lever.co/useinsider"));

    }
}
