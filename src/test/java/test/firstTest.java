package test;

import org.testng.annotations.Test;
import page.loginpage;
import utility.BaseClass;
import utility.excelReader;

public class firstTest extends BaseClass {
    loginpage lp;
    @Test(dataProviderClass = loginpage.class,dataProvider="mu")
    public void firsttest(String userid,String userpass){
        lp=new loginpage(driver);
        lp.loginPM(userid,userpass);

    }
}
