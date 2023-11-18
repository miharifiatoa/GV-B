package org.sales_management.session;

import org.sales_management.entity.UserEntity;

public class UserSession {
    private UserEntity currentUser;
    public UserEntity getCurrentUser() {
        return currentUser;
    }
    public void setCurrentUser(UserEntity user) {
        currentUser = user;
    }
}
