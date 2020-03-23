package com.example.red;

public class Profile {
    private String name;
    private String profilePic;


    public Profile() {
    }

    public Profile(String name, String email, String profilePic, boolean permission) {
        this.name = name;

        this.profilePic = profilePic;

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getProfilePic() {
        return profilePic;
    }

    public void setProfilePic(String profilePic) {
        this.profilePic = profilePic;
    }

}
