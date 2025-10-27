package com.svistoyanov.eks.utils;

import java.text.MessageFormat;
import java.util.Arrays;

/**
 *
 * @author svilen on 27/10/2025
 */
public class ConstantPrintable implements Printable {

    private final String   template;
    private final Object[] parameters;
    private       String   renderedValue;

    public ConstantPrintable(String template, Object... parameters) {
        this.template = template;
        this.parameters = parameters;

    }

    private static String render(String template, Object... params) {
        if (params.length == 0) {
            return template;
        }

        String[] renderedParams = new String[params.length];
        Arrays.setAll(renderedParams, idx -> renderParam(params[idx]));

        return MessageFormat.format(template, renderedParams);
    }

    private static String renderParam(Object param) {
        if (param instanceof Printable printable) {
            return printable.print();
        }

        return param.toString();
    }

    @Override
    public String print() {
        if (renderedValue == null) {
            renderedValue = render(template, parameters);
        }

        return renderedValue;
    }

}
