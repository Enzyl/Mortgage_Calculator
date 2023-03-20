package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.MortgageReference;
import Mortgage_Calculator.model.Rate;
import Mortgage_Calculator.model.RateAmounts;

public interface ReferenceCalculationService {
    MortgageReference calculate(InputData inputData);

    MortgageReference calculate(InputData inputData, RateAmounts rateAmounts, Rate previousRate);
}
