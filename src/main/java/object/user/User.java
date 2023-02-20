package object.user;

import com.github.javafaker.Faker;


import java.util.Objects;

public class User {
    private String firstName;
    private String lastName;
    private String email;
    private String password;

    public User(String passwordUser) {
        this.firstName = new Faker().name().firstName();
        this.lastName = new Faker().name().lastName();
        this.email = new Faker().internet().emailAddress();
        if (passwordUser.equals("valid")) {
            this.password = new Faker().internet().password(8, 10, true, true);
        } else if (passwordUser.equals("invalid")) {
            this.password = new Faker().internet().password(1, 2, false);
        }
    }

    public User() {
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (!Objects.equals(firstName, user.firstName)) return false;
        if (!Objects.equals(lastName, user.lastName)) return false;
        return Objects.equals(email, user.email);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        return result;
    }
}
