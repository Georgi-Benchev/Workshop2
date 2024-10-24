package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Toothpaste;
import com.company.oop.cosmetics.models.enums.GenderType;

import java.util.ArrayList;
import java.util.List;

public class ToothpasteImpl extends ProductImpl implements Toothpaste {


    private final List<String> ingredients;


    public ToothpasteImpl(String name, String brandName, double price, GenderType genderType, List<String> ingredients) {
        super(name, brandName, price, genderType);

        this.ingredients = ingredients;
    }


    @Override
    public String print() {
        StringBuilder ingredients = new StringBuilder("[");
        for (String ingredient : this.ingredients) {
            ingredients.append(ingredient).append(", ");
        }
        ingredients.deleteCharAt(ingredients.length() - 1);
        ingredients.deleteCharAt(ingredients.length() - 1);
        ingredients.append("]");

        String output = String.format("#%s %s\n #Price: $%.2f\n #Gender: %s\n #Ingredients: %s\n"
                , getName(), getBrandName(), getPrice(), getGenderType(), ingredients);

        return output;
    }

    @Override
    public List<String> getIngredients() {
        return new ArrayList<>(ingredients);
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ToothpasteImpl toothpaste = (ToothpasteImpl) o;
        return getName().equals(toothpaste.getName()) &&
                getBrandName().equals(toothpaste.getBrandName()) &&
                getPrice() == toothpaste.getPrice() &&
                this.getGenderType().equals(toothpaste.getGenderType()) &&
                getIngredients().equals(toothpaste.getIngredients());
    }
}
