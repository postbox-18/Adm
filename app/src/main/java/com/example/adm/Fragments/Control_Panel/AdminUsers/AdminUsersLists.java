package com.example.adm.Fragments.Control_Panel.AdminUsers;

public class AdminUsersLists {
    private String  user_name,email,phone_number,primary;
    public AdminUsersLists()
    {

    }

    public void setPrimary(String primary) {
        this.primary = primary;
    }

    public String getPrimary() {
        return primary;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public String getUser_name() {
        return user_name;
    }
}
