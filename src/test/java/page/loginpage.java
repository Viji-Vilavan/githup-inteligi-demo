package page;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.DataProvider;
import utility.BaseClass;
import utility.excelReader;

import java.io.IOException;

public class loginpage extends BaseClass {
    @FindBy(xpath = "//input[@placeholder='Employee ID']")
    public WebElement loginUsername;

    @FindBy(xpath = "//input[@placeholder='Password']")
    public WebElement loginPassword;

    @FindBy(xpath = "//button[@type='submit']")
    public WebElement submit;


    public loginpage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }@DataProvider(name = "mu")
    public static Object[][] callExcel() throws IOException {
        return excelReader.getdata("pm");
    }
    public void loginPM(String userid,String userpass){
        loginUsername.sendKeys(userid);
        loginPassword.sendKeys(userpass);
        submit.click();
    }
}
