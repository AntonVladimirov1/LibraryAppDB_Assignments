package com.library.steps;

import com.library.pages.LoginPage;
import com.library.pages.UserPage;
import com.library.utility.BrowserUtil;
import com.library.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

import java.util.List;

public class UserStepDefs {


    String actualUsersCount;
    String expectedUserCount;
    List<String> actualColumnNames;

    @Given("Establish the database connection")
    public void establish_the_database_connection() {
        System.out.println("Connection to DB - in Hooks");
    }

    @When("Execute query to get all IDs from users")
    public void execute_query_to_get_all_i_ds_from_users() {

        String query = "select count(id) from users";
        DB_Util.runQuery(query);

        actualUsersCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(actualUsersCount);
    }

    @Then("verify all users has unique ID")
    public void verify_all_users_has_unique_id() {

        String query = "select count(distinct id) from users";
        DB_Util.runQuery(query);

        expectedUserCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expectedUserCount);

        Assert.assertEquals(expectedUserCount,actualUsersCount);
    }
    @When("Execute query to get all columns")
    public void executeQueryToGetAllColumns() {

        String query = "select * from users";
        DB_Util.runQuery(query);
        actualColumnNames = DB_Util.getAllColumnNamesAsList();
    }

    @Then("verify the below columns are listed in result")
    public void verifyTheBelowColumnsAreListedInResult(List<String> expectedColumnNames) {

        Assert.assertEquals(expectedColumnNames,actualColumnNames);
        System.out.println(expectedColumnNames);
        System.out.println(actualColumnNames);
    }
    UserPage userPage=new UserPage();
    @When("the user selected status {string}")
    public void the_user_selected_status(String status) {
        BrowserUtil.selectOptionDropdown(userPage.statusDropdown,status);

    }
    String actStatusCount;
    @When("the gets number of users")
    public void the_gets_number_of_users() {
        BrowserUtil.waitFor(2);
        actStatusCount = userPage.getUserCount();
        System.out.println(userPage.getUserCount());

    }
    @Then("verify {string} status users count matches with DB")
    public void verify_status_users_count_matches_with_db(String status) {
    String query = "select count(*) from users where status='"+status+"' and user_group_id<>1";
    DB_Util.runQuery(query);
        String expStatusCount = DB_Util.getFirstRowFirstColumn();
        System.out.println(expStatusCount);

        Assert.assertEquals(expStatusCount,actStatusCount);

    }

}
