package com.svistoyanov.eks.transformer;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * @author svilen on 23/10/2025
 */
class KeyTransformerTest {

    private final KeyTransformer transformer = KeyTransformer.instance;

    @Test
    void testTransformKeySuccess() {
        assertTransformed("mts_12-asd as asd_Asd -123", "mts", "12-asd as asd", "asd -123");
        assertTransformed("cs_12345_6M - 90C", "cs", "12345", "6m - 90c");
        assertTransformed("mts_fus_dev", "mts", "fus", "dev");
        assertTransformed("mts_Ae- 1495-_--  ", "mts", "ae- 1495-", "--  ");
        assertTransformed("mts_ _ ", "mts", " ", " ");
        assertTransformed("mts_-_-", "mts", "-", "-");
        assertTransformed("mts__", "mts");
    }

    @Test
    void testTransformKeyNullKey() {
        assertTransformed(null);
    }

    @Test
    void testTransformKeyWrongPrefix() {
        assertIllegalArgument("aaaa_12-asd as asd_Asd -123");
    }

    @Test
    void testTransformKeyOnlyOneId() {
        assertIllegalArgument("cs_12345");
    }

    @Test
    void testTransformKeyInvalidChars() {
        assertIllegalArgument("cs_12345_6M - 90C @");
        assertIllegalArgument("mts_fus_dev~");
        assertIllegalArgument("mts_fus_dev %%");
    }

    @Test
    void testTransformKeyExtraUnderscore() {
        assertIllegalArgument("cs_12345_6M - 90C_");
        assertIllegalArgument("_cs_12345_6M - 90C");
        assertIllegalArgument("_cs_12345_6M - 90C_");
        assertIllegalArgument("cs_12345__6M - 90C");
    }

    void assertTransformed(String input, String... output) {
        List<String> actual = transformer.transformKey(input);
        List<String> expected = List.of(output);
        Assertions.assertEquals(expected, actual);
    }

    void assertIllegalArgument(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> transformer.transformKey(input));
    }

}
