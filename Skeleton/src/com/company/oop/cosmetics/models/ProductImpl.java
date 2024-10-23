package com.company.oop.cosmetics.models;

import com.company.oop.cosmetics.models.contracts.Product;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

public abstract class ProductImpl implements Product {

    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int BRAND_NAME_MIN_LENGTH = 2;
    private static final int BRAND_NAME_MAX_LENGTH = 10;
    private final String name;
    private final String brand;
    private final Double price;
    private final GenderType genderType;

    public ProductImpl(String name, String brand, double price, GenderType genderType) {

        ValidationHelpers.validateIntRange(NAME_MIN_LENGTH, NAME_MAX_LENGTH, name.length(), "Name");
        ValidationHelpers.validateIntRange(BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, brand.length(), "Brand");
        ValidationHelpers.validateNotNegative(price, "Price");

        this.name = name;
        this.brand = brand;
        this.price = price;
        this.genderType = genderType;
    }

    protected ProductImpl(String name, String brand, double price, GenderType genderType,
                          int validate_name_min, int validate_name_max, int validate_brand_min, int validate_brand_max) {

        ValidationHelpers.validateIntRange(validate_name_min, validate_name_max, name.length(), "Name");
        ValidationHelpers.validateIntRange(validate_brand_min, validate_brand_max, brand.length(), "Brand");
        ValidationHelpers.validateNotNegative(price, "Price");

        this.name = name;
        this.brand = brand;
        this.price = price;
        this.genderType = genderType;
    }


    @Override
    public String getName() {
        return this.name;
    }

    @Override
    public String getBrandName() {
        return this.brand;
    }

    @Override
    public double getPrice() {
        return this.price;
    }

    @Override
    public GenderType getGenderType() {
        return this.genderType;
    }

    @Override
    public abstract String print();

}
