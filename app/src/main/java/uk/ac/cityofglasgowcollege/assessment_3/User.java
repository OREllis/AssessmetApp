package uk.ac.cityofglasgowcollege.assessment_3;

/**
 * Created by 30055525 on 19/02/2018.
 */

public class User {
    //private properties
    private String username,
            password,
            email;
    private int age;


    //public getter functions
    public String getUsername() {
        return username;
    }

    //public setter methods
    public void setUsername(String usernameIn) {
        username = usernameIn;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String passwordIn) {
        password = passwordIn;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String emailIn) {
        email = emailIn;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int ageIn) {
        age = ageIn;
    }
}