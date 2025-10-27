package com.svistoyanov.eks.transformer;

import com.svistoyanov.eks.EtcdKeyTransformerImpl;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static com.svistoyanov.eks.Constants.ETCD_KEY_PREFIX;

/**
 * @author svilen on 5.01.22 г.
 */
class EtcdKeyTransformerImplTest {

    @Test
    void buildSuccessTestCase() {
        assertSuccess(ETCD_KEY_PREFIX + "mts/12-qwe qwe/rty -123", "mts", "12-qwe qwe", "rty -123");
        assertSuccess(ETCD_KEY_PREFIX + "svi/12345/6m - 90c", "svi", "12345", "6m - 90c");
        assertSuccess(ETCD_KEY_PREFIX + "mts/svi/dev", "mts", "svi", "dev");
    }

    void assertSuccess(String value, String... listOfValues) {
        final String actual = EtcdKeyTransformerImpl.instance.transform(List.of(listOfValues));
        Assertions.assertEquals(value, actual);
    }

}
