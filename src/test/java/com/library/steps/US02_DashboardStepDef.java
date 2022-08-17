package com.library.steps;

import com.library.pages.DashBoardPage;
import com.library.pages.LoginPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class US02_DashboardStepDef {


    @Given("the {string} on the home page")
    public void the_on_the_home_page(String user) {
    new LoginPage().login(user);
        BrowserUtil.waitFor(3);

    }
    String actualResultUI;
    @When("the librarian gets borrowed books number")
    public void the_librarian_gets_borrowed_books_number() {
    actualResultUI = new DashBoardPage().borrowedBooksNumber.getText();


    }
    @Then("borrowed books number information must match with DB")
    public void borrowed_books_number_information_must_match_with_db() {
    String db = "select count(*) from book_borrow where is_returned = 0";
        DB_Util.runQuery(db);
        String expectedResultDB = DB_Util.getFirstRowFirstColumn();
        Assert.assertEquals(expectedResultDB, actualResultUI);
    }
}
