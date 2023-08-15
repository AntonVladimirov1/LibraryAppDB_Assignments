package com.library.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserPage extends BasePage{

@FindBy (id="user_status")
    public WebElement statusDropdown;

@FindBy(className = "dataTables_info")
    public WebElement userCount;

public String getUserCount(){
    String allText = userCount.getText();
    System.out.println(allText);
    int start = allText.indexOf("of")+3;
    int end = allText.indexOf("entries")-1;
    return allText.substring(start,end).replace(",","");
}

}
