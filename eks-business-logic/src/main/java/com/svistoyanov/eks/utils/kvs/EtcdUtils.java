package com.svistoyanov.eks.utils.kvs;

import com.svistoyanov.eks.utils.ConstantPrintable;
import com.svistoyanov.eks.utils.Printable;
import com.svistoyanov.eks.utils.Utils;
import com.svistoyanov.eks.utils.ValidationResult;
import io.etcd.jetcd.ByteSequence;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class EtcdUtils {
    private static final Charset   CHARSET;
    private static final Printable MISSING_KEY;
    private static final Printable BLANK_KEY;
    private static final Printable MISSING_VALUE;
    private static final Printable BLANK_VALUE;

    private EtcdUtils() {
    }

    public static ByteSequence getByteSequence(String value) {
        return ByteSequence.from(value, CHARSET);
    }

    public static String toString(ByteSequence byteSequence) {
        return byteSequence.toString(CHARSET);
    }

    public static ValidationResult validateKey(String key) {
        ValidationResult vr = new ValidationResult();
        Utils.verifyNotNull(key, vr, MISSING_KEY);
        if (key != null) {
            Utils.verifyNotBlank(key, vr, BLANK_KEY);
        }

        return vr;
    }

    public static ValidationResult validateKeyAndValue(String key, String value) {
        ValidationResult vr = validateKey(key);
        Utils.verifyNotNull(value, vr, MISSING_VALUE);
        if (value != null) {
            Utils.verifyNotBlank(value, vr, BLANK_VALUE);
        }

        return vr;
    }

    static {
        CHARSET = StandardCharsets.UTF_8;
        MISSING_KEY = new ConstantPrintable("Key must be not null.", new Object[0]);
        BLANK_KEY = new ConstantPrintable("Key must be not blank.", new Object[0]);
        MISSING_VALUE = new ConstantPrintable("Value must be not null.", new Object[0]);
        BLANK_VALUE = new ConstantPrintable("Value must be not blank.", new Object[0]);
    }
}
