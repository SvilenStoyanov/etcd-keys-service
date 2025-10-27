package com.svistoyanov.eks.transformer;

import com.svistoyanov.eks.Validator;
import com.svistoyanov.eks.ValidatorImpl;
import com.svistoyanov.eks.utils.Printable;
import com.svistoyanov.eks.utils.ValidationResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author svilen on 5.01.22 г.
 */
class KeyIdValidatorImplTest {

    private static final String EXPECTED_PATTERN = "^(svi|mts)(_[-A-Za-z0-9\\s]*){2}$";

    private final Validator validator = new ValidatorImpl();

    @Test
    void nullKeyTestCase() {
        assertValidationFailed(null, "Key ID must not be null.");
    }

    @Test
    void wrongPrefixTestCase() {
        assertValidationFailed("aaaa_12-qwe qwe_Asd -123", "Key ID [aaaa_12-qwe qwe_Asd -123] does not match the pattern: " + EXPECTED_PATTERN);
        assertValidationFailed("abc", "Key ID [abc] does not match the pattern: " + EXPECTED_PATTERN);
    }

    @Test
    void onlyOneIdentifierTestCase() {
        assertValidationFailed("svi_12345", "Key ID [svi_12345] does not match the pattern: " + EXPECTED_PATTERN);
    }

    @Test
    void invalidCharsTestCase() {
        assertValidationFailed("svi_12345_6M - 90C @", "Key ID [svi_12345_6M - 90C @] does not match the pattern: " + EXPECTED_PATTERN);
        assertValidationFailed("mts_svi_dev~", "Key ID [mts_svi_dev~] does not match the pattern: " + EXPECTED_PATTERN);
        assertValidationFailed("mts_svi_dev %%", "Key ID [mts_svi_dev %%] does not match the pattern: " + EXPECTED_PATTERN);
    }

    @Test
    void extraUnderscoreTestCase() {
        assertValidationFailed("svi_12345_6M - 90C_", "Key ID [svi_12345_6M - 90C_] does not match the pattern: " + EXPECTED_PATTERN);
        assertValidationFailed("_svi_12345_6M - 90C", "Key ID [_svi_12345_6M - 90C] does not match the pattern: " + EXPECTED_PATTERN);
        assertValidationFailed("_svi_12345_6M - 90C_", "Key ID [_svi_12345_6M - 90C_] does not match the pattern: " + EXPECTED_PATTERN);
        assertValidationFailed("svi_12345__6M - 90C", "Key ID [svi_12345__6M - 90C] does not match the pattern: " + EXPECTED_PATTERN);
    }

    @Test
    void validKeyTestCase() {
        assertValidationSuccess("mts_12-qwe qwe_Asd -123");
        assertValidationSuccess("svi_12345_6M - 90C");
        assertValidationSuccess("mts_svi_dev");
        assertValidationSuccess("mts_Ae- 1495-_--  ");
        assertValidationSuccess("mts_ _ ");
        assertValidationSuccess("mts_-_-");
        assertValidationSuccess("mts__");
    }

    void assertValidationFailed(String input, String... expectedErrors) {
        final ValidationResult vr = validator.validate(input);

        Assertions.assertTrue(vr.hasFailed());
        Assertions.assertEquals(expectedErrors.length, vr.getErrors().size());

        final List<String> actualErrors = vr.getErrors().stream().map(Printable::print).toList();

        for (String expectedError : expectedErrors) {
            Assertions.assertTrue(actualErrors.contains(expectedError));
        }
    }

    void assertValidationSuccess(String input) {
        final ValidationResult vr = validator.validate(input);
        Assertions.assertFalse(vr.hasFailed());
    }
}
