package com.svistoyanov.eks.resolver;

import com.svistoyanov.eks.resolver.KeyIdResolver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;

/**
 * @author svilen on 23/10/2025
 */
class KeyIdResolverTests
{
    private final KeyIdResolver resolver = KeyIdResolver.instance;

    @Test
    void testResolveSuccess()
    {
        assertResolved("mts_12-asd as asd_Asd -123", "mts", "12-asd as asd", "asd -123");
        assertResolved("cs_12345_6M - 90C", "cs", "12345", "6m - 90c");
        assertResolved("mts_fus_dev", "mts", "fus", "dev");
        assertResolved("mts_Ae- 1495-_--  ", "mts", "ae- 1495-", "--  ");
        assertResolved("mts_ _ ", "mts", " ", " ");
        assertResolved("mts_-_-", "mts", "-", "-");
        assertResolved("mts__", "mts");
    }

    @Test
    void testResolveNullKey()
    {
        assertResolved(null);
    }

    void assertResolved(String input, String... output)
    {
        List<String> actual = resolver.resolve(input);
        List<String> expected = List.of(output);
        Assertions.assertEquals(expected, actual);
    }

}
