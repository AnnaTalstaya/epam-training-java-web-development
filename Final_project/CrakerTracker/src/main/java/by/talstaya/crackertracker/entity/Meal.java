package by.talstaya.crackertracker.entity;

import java.util.Objects;

public class Meal implements Entity {
    private int mealId;
    private User user;
    private Product product;
    private String date;
    private MealTime mealTime;
    private int quantity;

    public Meal() {
    }

    public int getMealId() {
        return mealId;
    }

    public User getUser() {
        return user;
    }

    public Product getProduct() {
        return product;
    }

    public String getDate() {
        return date;
    }

    public MealTime getMealTime() {
        return mealTime;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Meal)) return false;
        Meal meal = (Meal) o;
        return mealId == meal.mealId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealId);
    }

    public static class Builder {
        private Meal meal;

        public Builder() {
            meal = new Meal();
        }

        public Builder setMealId(int mealId) {
            meal.mealId = mealId;
            return this;
        }

        public Builder setUser(User user) {
            meal.user = user;
            return this;
        }

        public Builder setProduct(Product product) {
            meal.product = product;
            return this;
        }

        public Builder setDate(String date) {
            meal.date = date;
            return this;
        }

        public Builder setMealTime(MealTime mealTime) {
            meal.mealTime = mealTime;
            return this;
        }

        public Builder setQuantity(int quantity) {
            meal.quantity = quantity;
            return this;
        }

        public Meal build() {
            return meal;
        }

    }
}
