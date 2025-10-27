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
        assertTransformed("mts_12-asd as asd_Asd -123", "mts", "12-asd as asd", "asd -123");
        assertTransformed("cs_12345_6M - 90C", "cs", "12345", "6m - 90c");
        assertTransformed("mts_fus_dev", "mts", "fus", "dev");
        assertTransformed("mts_Ae- 1495-_--  ", "mts", "ae- 1495-", "--  ");
        assertTransformed("mts_ _ ", "mts", " ", " ");
        assertTransformed("mts_-_-", "mts", "-", "-");
        assertTransformed("mts__", "mts");
    }

    @Test
    void testTransformKeyNull() {
        assertTransformed(null);
    }

    @Test
    void testTransformWrongPrefix() {
        assertIllegalArgument("aaaa_12-asd as asd_Asd -123");
    }

    @Test
    void testTransformOnlyOneId() {
        assertIllegalArgument("cs_12345");
    }

    @Test
    void testTransformInvalidChars() {
        assertIllegalArgument("cs_12345_6M - 90C @");
        assertIllegalArgument("mts_fus_dev~");
        assertIllegalArgument("mts_fus_dev %%");
    }

    @Test
    void testTransformExtraUnderscore() {
        assertIllegalArgument("cs_12345_6M - 90C_");
        assertIllegalArgument("_cs_12345_6M - 90C");
        assertIllegalArgument("_cs_12345_6M - 90C_");
        assertIllegalArgument("cs_12345__6M - 90C");
    }

    void assertTransformed(String input, String... output) {
        List<String> actual = transformer.transform(input);
        List<String> expected = List.of(output);
        Assertions.assertEquals(expected, actual);
    }

    void assertIllegalArgument(String input) {
        Assertions.assertThrows(IllegalArgumentException.class, () -> transformer.transform(input));
    }

}
