package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;
import java.util.List;

public class US03_BookStepDef {

    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String books) {
      new BookPage().navigateModule(books);
    }
    List<String> bookCategories;
    @When("the user clicks book categories")
    public void the_user_clicks_book_categories() {
        bookCategories = BrowserUtil.getAllSelectOptions(new BookPage().mainCategoryElement);
        bookCategories.remove(0);
        System.out.println(bookCategories);
    }
    @Then("verify book categories must match book_categories table from db")
    public void verify_book_categories_must_match_book_categories_table_from_db() {
       String query = "select * from book_categories";
       DB_Util.runQuery(query);

       List<String> expectedResultDB = DB_Util.getColumnDataAsList("name");
        System.out.println(expectedResultDB);
        Assert.assertEquals(expectedResultDB, bookCategories);
    }
}
