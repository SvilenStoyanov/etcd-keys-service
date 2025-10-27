package com.svistoyanov.eks.utils;

/**
 *
 * @author svilen on 27/10/2025
 */
public class TemplatePrintable implements Printable{

    private final String template;

    public TemplatePrintable(String template) {
        this.template = template;

    }

    public Printable make(Object... parameters) {
        return new ConstantPrintable(template, parameters);
    }

    @Override
    public String print() {
        return template;
    }
}
