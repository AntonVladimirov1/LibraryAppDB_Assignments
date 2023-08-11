package com.library.steps;

import com.library.pages.BookPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;
import java.util.Map;

public class BookInfoStepDef {

    BookPage bookPage=new BookPage();
    String actBookName;
    Map<String, String> expBookInfo;
    String expBookCategory;


    @When("the user searches for {string} book")
    public void the_user_searches_for_book(String bookName) {
        actBookName=bookName;
        bookPage.bookSearch(bookName);
    }
    @When("the user clicks edit book button")
    public void the_user_clicks_edit_book_button() {

        bookPage.editBook(actBookName).click();
        BrowserUtil.waitFor(3);
        System.out.println("book name: "+actBookName);
    }
    @Then("book information must match the Database")
    public void book_information_must_match_the_database() {
           // DB queries
        String querySpecificBook= "select * from books where name='Antonio Banderas'";
        DB_Util.runQuery(querySpecificBook);
        expBookInfo = DB_Util.getRowMap(1);
        System.out.println(expBookInfo);

        String specificBookCategory= "select bc.name from books b join book_categories bc on b.book_category_id=bc.id where b.name='Antonio Banderas'";
        DB_Util.runQuery(specificBookCategory);
        expBookCategory = DB_Util.getFirstRowFirstColumn();
        System.out.println(expBookCategory);

             // DB results - one by one (since it is Map)
        String expBookName = expBookInfo.get("name");
        String expAuthor = expBookInfo.get("author");
        String expISBN = expBookInfo.get("isbn");
        String expYear = expBookInfo.get("year");
        //String expDescription = expBookInfo.get("description");

        System.out.println("-------------------------------------------");

        String actBookName = bookPage.getBookInfo("Book Name");
        String actAuthor = bookPage.getBookInfo("Author");
        String actISBN = bookPage.getBookInfo("ISBN");
        String actYear = bookPage.getBookInfo("Year");
        //String actBookCategory = bookPage.getBookInfo("Book Category");
        //String actDescription = bookPage.getBookInfo("Description");
        BrowserUtil.waitFor(3);

        Assert.assertEquals(expBookName,actBookName);
        Assert.assertEquals(expAuthor,actAuthor);
        Assert.assertEquals(expISBN,actISBN);
        Assert.assertEquals(expYear,actYear);
        //Assert.assertEquals(expBookCategory,actBookCategory);

    }

}
