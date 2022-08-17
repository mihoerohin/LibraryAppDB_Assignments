package com.library.steps;

import com.library.utility.DB_Util;
import io.cucumber.java.en.*;
import org.junit.Assert;

public class US05_BookGenre {
    String actualGenreDB;
    @When("I execute query to find most popular book genre")
    public void i_execute_query_to_find_most_popular_book_genre() {
    String query = "select bc.name, count(*) from book_borrow bb inner join books b on bb.book_id = b.id inner join book_categories bc on b.book_category_id = bc.id group by bc.name order by count(*) desc";
        DB_Util.runQuery(query);
        actualGenreDB = DB_Util.getFirstRowFirstColumn();
        DB_Util.getCellValue(1,2);
    }

    @Then("verify {string} is the most popular book genre.")
    public void verify_is_the_most_popular_book_genre(String expectedGenre) {
        Assert.assertEquals(expectedGenre, actualGenreDB);

    }
}
