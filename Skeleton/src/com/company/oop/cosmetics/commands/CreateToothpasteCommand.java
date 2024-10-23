package com.company.oop.cosmetics.commands;

import com.company.oop.cosmetics.core.contracts.Command;
import com.company.oop.cosmetics.core.contracts.CosmeticsRepository;
import com.company.oop.cosmetics.models.enums.GenderType;
import com.company.oop.cosmetics.models.enums.ScentType;
import com.company.oop.cosmetics.utils.ValidationHelpers;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static com.company.oop.cosmetics.utils.ParsingHelpers.*;

public class CreateToothpasteCommand implements Command {

    private static final String TOOTHPASTE_CREATED = "Toothpaste with name %s was created!";
    public static final int EXPECTED_NUMBER_OF_ARGUMENTS = 5;
    private static final int NAME_MIN_LENGTH = 3;
    private static final int NAME_MAX_LENGTH = 10;
    private static final int BRAND_NAME_MIN_LENGTH = 2;
    private static final int BRAND_NAME_MAX_LENGTH = 10;

    private final CosmeticsRepository cosmeticsRepository;


    public CreateToothpasteCommand(CosmeticsRepository cosmeticsRepository) {
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
        List<String> ingredients = Arrays.stream(parameters.get(4).split(",")).toList();

        return createToothpaste(name, brand, price, genderType,ingredients );



    }

    private String createToothpaste(String name, String brand, Double price,GenderType genderType, List<String> ingredients) {

        cosmeticsRepository.createToothpaste(name, brand, price, genderType, ingredients);

        return String.format(TOOTHPASTE_CREATED, name);

    }
}