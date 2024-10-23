package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Cream;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;

public class CreamImpl extends ProductImpl implements Cream {

    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int BRAND_NAME_MIN_LENGTH = 3;
    private static final int BRAND_NAME_MAX_LENGTH = 15;

    private final ScentType scent;

    public CreamImpl(String name, String brand, double price, GenderType genderType, ScentType scent) {
        super(name, brand, price, genderType,
                NAME_MIN_LENGTH, NAME_MAX_LENGTH, BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH);

        this.scent = scent;
    }

    @Override
    public String print() {
        String output = String.format("#%s %s\n #Price: $%.2f\n #Gender: %s\n #Scent: %s\n"
                , getName(), getBrandName(), getPrice(), getGenderType(), this.scent);

        return output;
    }

    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreamImpl cream = (CreamImpl) o;
        return getName().equals(cream.getName()) &&
                getBrandName().equals(cream.getBrandName()) &&
                getPrice() == cream.getPrice() &&
                this.getGenderType().equals(cream.getGenderType()) &&
                getScent().equals(cream.getScent());
    }

    @Override
    public ScentType getScent() {
        return this.scent;
    }

}
