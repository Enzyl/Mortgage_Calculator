package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.Overpayment;

import java.math.BigDecimal;

public interface OverpaymentCalculationService {
    Overpayment calculate(BigDecimal rateNumber, InputData inputData);
}
