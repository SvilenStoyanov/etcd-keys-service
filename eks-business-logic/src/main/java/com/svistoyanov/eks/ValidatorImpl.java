package com.svistoyanov.eks;

import com.svistoyanov.eks.utils.ConstantPrintable;
import com.svistoyanov.eks.utils.Printable;
import com.svistoyanov.eks.utils.TemplatePrintable;
import com.svistoyanov.eks.utils.Utils;
import com.svistoyanov.eks.utils.ValidationResult;

/**
 *
 * @author svilen on 27/10/2025
 */
public class ValidatorImpl implements Validator{

    private static final Printable         NULL_MESSAGE      = new ConstantPrintable("Key ID must not be null.");
    private static final TemplatePrintable PATTERN_NOT_MATCH = new TemplatePrintable("Key ID [{0}] does not match the pattern: {1}");

    private static final String KEY_ID_PATTERN = "^(svi|mts)(_[-A-Za-z0-9\\s]*){2}$";

    @Override
    public ValidationResult validate(String keyId) {
        final var vr = new ValidationResult();

        Utils.verifyNotNull(keyId, vr, NULL_MESSAGE);
        if (keyId != null) {
            Utils.verifyTrue(keyId.matches(KEY_ID_PATTERN), vr, PATTERN_NOT_MATCH.make(keyId, KEY_ID_PATTERN));
        }

        return vr;
    }
}
