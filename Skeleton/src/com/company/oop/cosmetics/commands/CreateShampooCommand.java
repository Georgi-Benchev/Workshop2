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

    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 6;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int BRAND_NAME_MIN_LENGTH = 2;
    private static final int BRAND_NAME_MAX_LENGTH = 10;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateShampooCommand(CosmeticsRepository cosmeticsRepository) {
        this.cosmeticsRepository = cosmeticsRepository;
    }

    @Override
    public String execute(List<String> parameters) {

        ValidationHelpers.validateArgumentsCount(parameters, EXPECTED_NUMBER_OF_ARGUMENTS);
        ValidationHelpers.validateIntRange(NAME_MIN_LENGTH, NAME_MAX_LENGTH, parameters.get(0).length(), "Name");
        ValidationHelpers.validateIntRange(BRAND_NAME_MIN_LENGTH, BRAND_NAME_MAX_LENGTH, parameters.get(1).length(), "Brand");

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

        cosmeticsRepository.createShampoo(name, brand, price, genderType, milliliters, usageType);

        return String.format(SHAMPOO_CREATED, name);
    }


}
