package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.UsageType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.cosmetics.utils.ParsingHelpers.*;

public class CreateShampooCommand implements Command {

    private static final String SHAMPOO_CREATED = "Shampoo with name %s was created!";
    private static final String PRODUCT_NAME_ALREADY_EXISTS = "Shampoo with name %s already exists!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;


    private final CosmeticsRepository cosmeticsRepository;

    public CreateShampooCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);

        String name = parameters.get(0);
        String brand = parameters.get(1);
        Double price = tryParseDouble(parameters.get(2), INVALID_PRICE);
        GenderType genderType = tryParseGender(parameters.get(3));
        int milliliters = tryParseInt(parameters.get(4),INVALID_MILLILITRES);
        UsageType usageType = tryParseUsageType(parameters.get(5));

        return createShampoo(name, brand, price, genderType, milliliters, usageType);

    }

    private String createShampoo(String name, String brand, Double price,
                                 GenderType genderType, int milliliters, UsageType usageType) {

        if (cosmeticsRepository.productExist(name)){
            throw new IllegalArgumentException(String.format(PRODUCT_NAME_ALREADY_EXISTS, name));
        }
        cosmeticsRepository.createShampoo(name, brand, price, genderType, milliliters, usageType);

        return String.format(SHAMPOO_CREATED, name);
    }


}
