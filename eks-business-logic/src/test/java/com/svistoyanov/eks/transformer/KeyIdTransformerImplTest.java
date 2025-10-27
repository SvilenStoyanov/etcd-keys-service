package com.svistoyanov.eks.transformer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author svilen on 23/10/2025
 */
class KeyIdTransformerImplTest {

    private final KeyIdTransformerImpl transformer = KeyIdTransformerImpl.instance;

    @Test
    void testTransformSuccess() {
        assertSuccess("mts_12-qwe qwe_Asd -123", "mts", "12-qwe qwe", "asd -123");
        assertSuccess("svi_12345_6M - 90C", "svi", "12345", "6m - 90c");
        assertSuccess("mts_svi_dev", "mts", "svi", "dev");
        assertSuccess("mts_Ae- 1495-_--  ", "mts", "ae- 1495-", "--  ");
        assertSuccess("mts_ _ ", "mts", " ", " ");
        assertSuccess("mts_-_-", "mts", "-", "-");
        assertSuccess("mts__", "mts");
    }

    void assertSuccess(String value, String... listOfValues) {
        final List<String> actual = KeyIdTransformerImpl.instance.transform(value);
        final List<String> expected = List.of(listOfValues);
        Assertions.assertEquals(expected, actual);
    }
}
