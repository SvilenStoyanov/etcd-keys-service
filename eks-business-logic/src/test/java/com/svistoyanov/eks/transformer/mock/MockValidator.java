package com.svistoyanov.eks.transformer.mock;

import com.svistoyanov.eks.Validator;
import com.svistoyanov.eks.utils.ValidationResult;

public class MockValidator implements Validator {

    @Override
    public ValidationResult validate(String value) {
        return new ValidationResult();
    }
}
