package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;

import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.List;

import static com.company.oop.cosmetics.utils.ParsingHelpers.*;

public class CreateCreamCommand implements Command {


    private static final String CREAM_CREATED = "Cream with name %s was created!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 15;
    private static final int BRAND_NAME_MIN_LENGTH = 3;
    private static final int BRAND_NAME_MAX_LENGTH = 15;

    private final CosmeticsRepository cosmeticsRepository;

    public CreateCreamCommand(CosmeticsRepository cosmeticsRepository) {
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
        ScentType scent = tryParseScent(parameters.get(4));

        return createCream(name, brand, price, genderType, scent);


    }

    private String createCream(String name, String brand, Double price, GenderType genderType, ScentType scent) {

        this.cosmeticsRepository.createCream(name, brand, price, genderType, scent);

        return String.format(CREAM_CREATED, name);

    }

}
