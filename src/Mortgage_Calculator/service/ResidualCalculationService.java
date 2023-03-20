package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.MortgageResidual;
import Mortgage_Calculator.model.Rate;
import Mortgage_Calculator.model.RateAmounts;

public interface ResidualCalculationService {
    MortgageResidual calculate(RateAmounts rateAmounts, InputData inputData);
    MortgageResidual calculate(RateAmounts rateAmounts, Rate rate);
}
