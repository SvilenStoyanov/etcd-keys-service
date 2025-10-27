package com.svistoyanov.eks.transformer;

import com.svistoyanov.eks.PublicKeyService;
import com.svistoyanov.eks.transformer.mock.MockEtcdKeyTransformer;
import com.svistoyanov.eks.transformer.mock.MockKeyIdTransformer;
import com.svistoyanov.eks.transformer.mock.MockKvsServiceProvider;
import com.svistoyanov.eks.transformer.mock.MockValidator;
import com.svistoyanov.eks.utils.exception.ConflictException;
import com.svistoyanov.eks.utils.exception.NotFoundException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class PublicKeyServiceTest {

    private final PublicKeyService publicKeysService = new PublicKeyService(
            new MockKvsServiceProvider(),
            new MockValidator(),
            MockEtcdKeyTransformer.instance,
            new MockKeyIdTransformer()
    );

    @Test
    void keyNotFoundTestCase() {
        Assertions.assertThrows(NotFoundException.class, () -> publicKeysService.getKey("zero"));
    }

    @Test
    void moreThanOneResultFoundTestCase() {
        Assertions.assertThrows(ConflictException.class, () -> publicKeysService.getKey("moreThanOne"));
    }

    @Test
    void getKeySuccessTestCase() {
        String actual = publicKeysService.getKey("valid");
        Assertions.assertEquals("ETCDValue", actual);
    }

}
