package by.talstaya.crackertracker.entity;

import java.time.LocalDate;

/**
 * User is a entity of user
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class User implements Entity {

    private int userId;
    private UserType userType;
    private String firstName;
    private String surname;
    private String email;
    private String username;
    private String password;
    private LocalDate dateOfBirth;
    private double weight;
    private double height;
    private double rating;
    private int supervisorId;

    private User() {
    }

    public int getUserId() {
        return userId;
    }

    public UserType getUserType() {
        return userType;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmail() {
        return email;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public double getWeight() {
        return weight;
    }

    public double getHeight() {
        return height;
    }

    public double getRating() {
        return rating;
    }

    public int getSupervisorId() {
        return supervisorId;
    }

    public void setSupervisorId(int supervisorId) {
        this.supervisorId = supervisorId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public static class Builder {
        private User user;

        public Builder() {
            user = new User();
        }

        public Builder setUserId(int userId) {
            user.userId = userId;
            return this;
        }

        public Builder setUserType(UserType userType) {
            user.userType = userType;
            return this;
        }

        public Builder setFirstName(String firstName) {
            user.firstName = firstName;
            return this;
        }

        public Builder setSurname(String surname) {
            user.surname = surname;
            return this;
        }

        public Builder setEmail(String email) {
            user.email = email;
            return this;
        }

        public Builder setUsername(String username) {
            user.username = username;
            return this;
        }

        public Builder setPassword(String password) {
            user.password = password;
            return this;
        }

        public Builder setDateOfBirth(LocalDate dateOfBirth) {
            user.dateOfBirth = dateOfBirth;
            return this;
        }

        public Builder setWeight(double weight) {
            user.weight = weight;
            return this;
        }

        public Builder setHeight(double height) {
            user.height = height;
            return this;
        }

        public Builder setRating(double rating) {
            user.rating = rating;
            return this;
        }

        public Builder setSupervisorId(int supervisorId) {
            user.supervisorId = supervisorId;
            return this;
        }

        public User build() {
            return user;
        }

    }
}
