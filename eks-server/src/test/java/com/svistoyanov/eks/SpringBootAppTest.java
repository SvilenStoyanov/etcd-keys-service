package com.svistoyanov.eks;

import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Import;

/**
 *
 * @author svilen on 24/10/2025
 */
@Import({
        SpringBootApp.class,
})
public class SpringBootAppTest {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootAppTest.class, args);
    }
}
