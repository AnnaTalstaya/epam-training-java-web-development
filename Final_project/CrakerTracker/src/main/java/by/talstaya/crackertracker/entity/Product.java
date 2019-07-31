package by.talstaya.crackertracker.entity;

import java.util.Objects;

public class Product implements Entity {

    private int productId;
    private String name;
    private String imageURL;
    private String description;
    private int calories;
    private int proteins;
    private int lipids;
    private int carbohydrates;

    private Product() {

    }

    public int getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public String getImageURL() {
        return imageURL;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public int getProteins() {
        return proteins;
    }

    public int getLipids() {
        return lipids;
    }

    public int getCarbohydrates() {
        return carbohydrates;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Product)) return false;
        Product product = (Product) o;
        return productId == product.productId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(productId);
    }

    public static class Builder {
        private Product product;

        public Builder() {
            product = new Product();
        }

        public Builder setProductId(int productId) {
            product.productId = productId;
            return this;
        }

        public Builder setName(String name) {
            product.name = name;
            return this;
        }

        public Builder setImageURL(String imageURL) {
            product.imageURL = imageURL;
            return this;
        }

        public Builder setDescription(String description) {
            product.description = description;
            return this;
        }

        public Builder setCalories(int calories) {
            product.calories = calories;
            return this;
        }

        public Builder setProteins(int proteins) {
            product.proteins = proteins;
            return this;
        }

        public Builder setLipids(int lipids) {
            product.lipids = lipids;
            return this;
        }

        public Builder setCarbohydrates(int carbohydrates) {
            product.carbohydrates = carbohydrates;
            return this;
        }

        public Product build() {
            return product;
        }
    }
}
