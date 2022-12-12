package com.bank.testsuite;

import com.bank.customlisteners.CustomListeners;
import com.bank.pages.AccountPage;
import com.bank.pages.AddCustomerPage;
import com.bank.pages.CustomerPage;
import com.bank.pages.OpenAccountPage;
import com.bank.testbase.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;


@Listeners(CustomListeners.class)

public class BankTest extends BaseTest {

    AddCustomerPage addCustomerPage;
    OpenAccountPage openAccountPage;
    CustomerPage customerPage;
    AccountPage accountPage;

    @BeforeMethod(alwaysRun = true)
    public void inIt(){
        addCustomerPage = new AddCustomerPage();
        openAccountPage = new OpenAccountPage();
        customerPage = new CustomerPage();
        accountPage = new AccountPage();
    }

    @Test(groups = {"sanity", "regression"})
    public void BankManagerShouldAddCustomerSuccessfully() throws InterruptedException {
        accountPage.clickOnBankManagerLoginTab();
        addCustomerPage.clickOnAddCustomerTab();
        addCustomerPage.sendTextToFirstName("Manan");
        addCustomerPage.sendTextToLastName("Shah");
        addCustomerPage.sendTextToPostCode("HA8 9LA");
        addCustomerPage.clickOnAddCustomer();
    }

    @Test(groups = {"smoke", "regression"})
    public void bankManagerShouldOpenAccountSuccessfully() throws InterruptedException {
        accountPage.clickOnBankManagerLoginTab();
        openAccountPage.clickOnOpenAccountLink();
        openAccountPage.customerNameList();
        openAccountPage.currencyList();
        Thread.sleep(5000);
        openAccountPage.clickOnProcessButton();
    }

    @Test(groups = "regression")
    public void customerShouldLoginAndLogoutSuccessfully() throws InterruptedException {

        customerPage.clickCustomerLoginLink();
        customerPage.clickOnYourNameList();
        Thread.sleep(2000);
        customerPage.clickOnLogin();
        customerPage.verifyLogoutButtonDisplayed();
        customerPage.verifyWelcomeMessage();
        Assert.assertEquals(customerPage.verifyWelcomeMessage(),"Welcome Harry Potter !!");
        customerPage.clickOnLogout();
    }
    @Test(groups = "regression")
    public void customerShouldDepositMoneySuccessfully() throws InterruptedException {
        customerPage.clickCustomerLoginLink();
        customerPage.clickOnYourNameList();
        Thread.sleep(10000);
        customerPage.clickOnLogin();
        accountPage.clickOnDeposit();
        accountPage.enterAmount100("1000");
        accountPage.clickOnDepositButton();
        accountPage.verifyMessageDepositSuccessfully();
        Assert.assertEquals(accountPage.verifyMessageDepositSuccessfully(),"Deposit Successful");
    }
    @Test(groups = "regression")
    public void customerShouldWithdrawMoneySuccessful()  {
        customerPage.clickCustomerLoginLink();
        customerPage.clickOnYourNameList();
        customerPage.clickOnLogin();
        accountPage.clickOnDeposit();
        accountPage.enterAmount100("1000");
        accountPage.clickOnDepositButton();
        accountPage.clickOnWithdrawButton();
        accountPage.enterAmount("500");
        accountPage.clickOnWithdraw();

//        accountPage.verifyMessageTransactionSuccessful();
//        Assert.assertEquals(accountPage.verifyMessageTransactionSuccessful(),"Transaction successful");

    }
}
