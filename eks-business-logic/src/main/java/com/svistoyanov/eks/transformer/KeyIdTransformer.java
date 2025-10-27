package com.svistoyanov.eks.transformer;

import java.util.List;

/**
 *
 * @author svilen on 24/10/2025
 */
public interface KeyIdTransformer {

    List<String> transform(String keyToTransform);
}
