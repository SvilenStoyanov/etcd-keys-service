package com.svistoyanov.eks;

import java.util.List;

/**
 *
 * @author svilen on 24/10/2025
 */
public interface EtcdKeyTransformer {

    String transform(List<String> identifiers);
}
