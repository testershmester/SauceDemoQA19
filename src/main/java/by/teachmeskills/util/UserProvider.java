package by.teachmeskills.util;

import by.teachmeskills.dto.User;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Arrays;

public class UserProvider {

    public User getStandardUser() {
        Logger log = LogManager.getLogger(UserProvider.class);
        String standardUserCreds = System.getenv("STANDARD_USER_CREDS");
        log.info(standardUserCreds);
        log.info(String.valueOf(standardUserCreds.contains("/")));
        String[] s = standardUserCreds.split("/");
        log.info(Arrays.toString(s));
        return new User(s[0], s[1]);
    }
}
