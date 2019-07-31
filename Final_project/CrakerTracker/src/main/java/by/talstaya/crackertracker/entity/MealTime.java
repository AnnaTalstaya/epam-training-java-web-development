package by.talstaya.crackertracker.entity;

import java.util.Objects;

public class MealTime implements Entity {

    private int mealTimeId;
    private String mealTimeValue;

    public MealTime() {
    }

    public int getMealTimeId() {
        return mealTimeId;
    }

    public String getMealTime() {
        return mealTimeValue;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MealTime)) return false;
        MealTime mealTime = (MealTime) o;
        return mealTimeId == mealTime.mealTimeId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(mealTimeId);
    }

    public static class Builder {
        private MealTime mealTime;

        public Builder() {
            mealTime = new MealTime();
        }

        public Builder setMealTimeId(int mealTimeId) {
            mealTime.mealTimeId = mealTimeId;
            return this;
        }

        public Builder setMealTimeValue(String mealTimeValue) {
            mealTime.mealTimeValue = mealTimeValue;
            return this;
        }

        public MealTime build() {
            return mealTime;
        }

    }
}
