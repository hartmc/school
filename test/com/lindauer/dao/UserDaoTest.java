package com.lindauer.dao;

import com.lindauer.model.User;
import org.junit.Test;

import javax.security.sasl.AuthenticationException;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class UserDaoTest {

    @Test
    public void validateUserAndPasswordForTeacher() throws Exception {
        User user = UserDao.validate("ndube", "password");
        assertEquals("ndube", user.getUserName());
        assertEquals("Niti", user.getFirstName());
        assertEquals("Dube", user.getLastName());
    }

    @Test
    public void validateUserAndPasswordForStudent() throws Exception {
        User user = UserDao.validate("dkim", "password");
        assertEquals("dkim", user.getUserName());
        assertEquals("Dylan", user.getFirstName());
        assertEquals("Kim", user.getLastName());
    }

    /**
     * we saw a bug in which we failed to check the password again
     * after the first success (ie, after we started caching the result)
     * test that here.
     */
    @Test(expected=AuthenticationException.class)
    public void validateFailsAfterSuccess() throws Exception {
        UserDao.validate("dkim", "password");
        UserDao.validate("dkim", "not-the-right-password");
    }

    @Test (expected= AuthenticationException.class)
    public void validateUserAndPwdThrowsExceptionWithWrongPassword() throws Exception {
        UserDao.validate("ndube", "wrongpassword");
        fail("Exception should have been thrown");
    }

    @Test (expected=AuthenticationException.class)
    public void validateUserAndPwdThrowsExceptionWithWrongUsername() throws Exception {
        User user = UserDao.validate("nosuchuser", "password");
        fail("Exception should have been thrown");
    }
}
