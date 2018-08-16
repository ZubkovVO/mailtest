package com.epam.add;

import com.epam.pf.LoginPage;
import com.epam.pf.MainMailPage;
import com.epam.pf.ProfilePopup;
import com.epam.pf.RequestPhone;

public class User {

    private String login;
    private String password;

    public User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public void signIn() {
        new LoginPage().openMailPage().goToLoginPage().enterLogin(login).enterPassword(password).pressNextButton();
    }

    public void signInToDisk() {
        new LoginPage().openDiskPage().diskLogin().enterLogin(login).enterPassword(password).pressNextButton();
    }

    public void noPhone(){
        new RequestPhone().noPhone();
    }

    public void signOut() {
        new ProfilePopup().signOutOptions().signOut();
    }

    public void signOutDisk() throws InterruptedException {
        new ProfilePopup().signOutDiskOptions().signOutDisk();
    }

}
