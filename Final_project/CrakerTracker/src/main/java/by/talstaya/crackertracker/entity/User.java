package by.talstaya.crackertracker.entity;

public class User implements Entity {

    private int userId;
    private UserType userType;
    private String firstName;
    private String surname;
    private String email;
    private String username;
    private String password;
    private String dateOfBirth;
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

    public String getDateOfBirth() {
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

        public Builder setDateOfBirth(String dateOfBirth) {
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
