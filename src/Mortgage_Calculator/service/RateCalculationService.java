package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.Rate;

import java.util.List;

public interface RateCalculationService {
    List<Rate> calculate(final InputData inputData);
}
