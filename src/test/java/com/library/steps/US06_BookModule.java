package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import io.cucumber.java.it.Ma;
import org.junit.Assert;

import java.util.Map;

public class US06_BookModule {

    BookPage bookPage = new BookPage();

    @When("the librarian click to add book")
    public void the_librarian_click_to_add_book() {
        bookPage.addBook.click();
    }

    @When("the librarian enter book name {string}")
    public void the_librarian_enter_book_name(String name) {
        bookPage.bookName.sendKeys(name);
    }

    @When("the librarian enter ISBN {string}")
    public void the_librarian_enter_isbn(String isbn) {
        bookPage.isbn.sendKeys(isbn);
    }

    @When("the librarian enter year {string}")
    public void the_librarian_enter_year(String year) {
        bookPage.year.sendKeys(year);
    }

    @When("the librarian enter author {string}")
    public void the_librarian_enter_author(String author) {
        bookPage.author.sendKeys(author);
    }

    @When("the librarian choose the book category {string}")
    public void the_librarian_choose_the_book_category(String category) {
        BrowserUtil.selectOptionDropdown(bookPage.categoryDropdown, category);
    }

    @When("the librarian click to save changes")
    public void the_librarian_click_to_save_changes() {
        bookPage.saveChanges.click();
        BrowserUtil.waitFor(2);
    }

    @Then("verify {string} message is displayed")
    public void verify_message_is_displayed(String expectedResult) {
        Assert.assertEquals(expectedResult, bookPage.toastMessage.getText());
    }

    @Then("verify {string} information must match with DB")
    public void verify_information_must_match_with_db(String expectedBook) {
        String query = "select name, author, isbn from books where name='" + expectedBook + "'";
        DB_Util.runQuery(query);
        Map<String, String> rowMap = DB_Util.getRowMap(1);
        String actualBookName = rowMap.get("name");
        Assert.assertEquals(expectedBook, actualBookName);
    }


}
