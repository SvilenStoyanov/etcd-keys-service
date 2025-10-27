package com.svistoyanov.eks;

import com.svistoyanov.eks.utils.ValidationResult;

/**
 *
 * @author svilen on 24/10/2025
 */
public interface Validator {

    ValidationResult validate(String value);
}
