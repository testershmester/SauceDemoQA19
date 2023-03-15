package by.teachmeskills.util;

import by.teachmeskills.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Arrays;

public class UserProvider {

    public User getStandardUser() {
        Logger logger = LoggerFactory.getLogger(UserProvider.class);
        String standardUserCreds = System.getenv("STANDARD_USER_CREDS");
        logger.info(standardUserCreds);
        String[] s = standardUserCreds.split("/");
        logger.info(Arrays.toString(s));
        return new User(s[0], s[1]);
    }
}
