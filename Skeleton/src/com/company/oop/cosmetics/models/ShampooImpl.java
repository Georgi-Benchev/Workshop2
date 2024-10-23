package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Shampoo;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ValidationHelpers;


public class ShampooImpl extends ProductImpl implements Shampoo {

    private final int milliliters;
    private final UsageType usageType;


    public ShampooImpl(String name, String brand, double price, GenderType genderType, int milliliters, UsageType usageType) {
        super(name, brand, price, genderType);

        ValidationHelpers.validateNotNegative(milliliters, "Milliliters");
        this.milliliters = milliliters;
        this.usageType = usageType;
    }



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ShampooImpl shampoo = (ShampooImpl) o;
        return getName().equals(shampoo.getName()) &&
                getBrandName().equals(shampoo.getBrandName()) &&
                getPrice() == shampoo.getPrice() &&
                getGenderType().equals(shampoo.getGenderType()) &&
                getMillilitres() == shampoo.getMillilitres() &&
                getUsageType().equals(shampoo.getUsageType());
    }

    @Override
    public int getMillilitres() {
        return this.milliliters;
    }

    @Override
    public UsageType getUsageType() {
        return this.usageType;
    }


    @Override
    public String print() {
        String output = String.format("#%s %s\n #Price: $%.2f\n #Gender: %s\n #Milliliters: %d\n #Usage: %s\n"
                , getName(), getBrandName(), getPrice(), getGenderType(), this.milliliters, this.usageType);

        return output;
    }
}
