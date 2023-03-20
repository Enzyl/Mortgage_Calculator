package Mortgage_Calculator.service;

import Mortgage_Calculator.model.Rate;
import Mortgage_Calculator.model.Summary;

import java.util.List;

public interface SummaryService {
    Summary calculate(List<Rate> rates);
}
