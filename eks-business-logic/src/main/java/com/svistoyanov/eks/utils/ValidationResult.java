package com.svistoyanov.eks.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;
import java.util.function.UnaryOperator;

/**
 *
 * @author svilen on 27/10/2025
 */
public class ValidationResult {

    private final UnaryOperator<Printable> errorDecorator;
    private final List<Printable>          errors;

    public ValidationResult() {
        this(UnaryOperator.identity(), new ArrayList<>());
    }

    private ValidationResult(UnaryOperator<Printable> errorDecorator, List<Printable> errorDestination) {
        this.errorDecorator = errorDecorator;
        this.errors = errorDestination;
    }

    public boolean hasFailed() {
        return !errors.isEmpty();
    }

    public ValidationResult addError(Printable error) {
        errors.add(errorDecorator.apply(error));
        return this;
    }

    public ValidationResult addErrors(List<Printable> errors) {
        errors.forEach(this::addError);
        return this;
    }

    public List<Printable> getErrors() {
        return Collections.unmodifiableList(errors);
    }

    public ValidationResult createResultForComponent(String componentName) {
        final var subResultDecorator = new ComponentErrorDecorator(errorDecorator, componentName);
        return new ValidationResult(subResultDecorator, errors);
    }

    public ValidationResult createResultForComponent(Printable componentName) {
        final var subResultDecorator = new ComponentErrorDecorator(errorDecorator, componentName);
        return new ValidationResult(subResultDecorator, errors);
    }

    public List<String> toMessages() {
        return errors.stream().map(Printable::print).toList();
    }

    private static class ComponentErrorDecorator implements UnaryOperator<Printable> {
        private static final String COMPONENT_MESSAGE_TEMPLATE = "{0} > {1}";

        private final Function<Printable, Printable> parentDecorator;
        private final Object                         componentName;

        public ComponentErrorDecorator(UnaryOperator<Printable> parentDecorator, String componentName) {
            this.parentDecorator = parentDecorator;
            this.componentName = componentName;
        }

        public ComponentErrorDecorator(UnaryOperator<Printable> parentDecorator, Printable componentName) {
            this.parentDecorator = parentDecorator;
            this.componentName = componentName;
        }

        @Override
        public Printable apply(Printable printable) {
            final var decoratedBySelf = new ConstantPrintable(COMPONENT_MESSAGE_TEMPLATE, componentName, printable);
            return parentDecorator.apply(decoratedBySelf);
        }
    }
}
