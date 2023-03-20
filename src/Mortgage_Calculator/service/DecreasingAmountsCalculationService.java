package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.Overpayment;
import Mortgage_Calculator.model.Rate;
import Mortgage_Calculator.model.RateAmounts;

public interface DecreasingAmountsCalculationService {
    RateAmounts calculate(InputData inputData, Overpayment overpayment);

    RateAmounts calculate(InputData inputData, Overpayment overpayment, Rate previousRate);
}
