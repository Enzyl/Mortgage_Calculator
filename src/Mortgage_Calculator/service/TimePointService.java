package Mortgage_Calculator.service;

import Mortgage_Calculator.model.InputData;
import Mortgage_Calculator.model.TimePont;

import java.math.BigDecimal;

public interface TimePointService {
    TimePont calculate(BigDecimal rateNumber,  InputData inputData);
}
