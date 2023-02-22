package by.teachmeskills.util.testng;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryAnalyzer implements IRetryAnalyzer {

    private static int COUNT = 1;
    private static final int MAX_TRY = 2;

    @Override
    public boolean retry(ITestResult result) {
        if (!result.isSuccess()) {                      //Check if test not succeed
            if (COUNT < MAX_TRY) {                            //Check if maxtry count is reached
                COUNT++;                                     //Increase the maxTry count by 1
                result.setStatus(ITestResult.FAILURE);  //Mark test as failed
                return true;                                 //Tells TestNG to re-run the test
            } else {
                result.setStatus(ITestResult.FAILURE);  //If maxCount reached,test marked as failed
            }
        } else {
            result.setStatus(ITestResult.SUCCESS);      //If test passes, TestNG marks it as passed
        }
        return false;
    }
}
