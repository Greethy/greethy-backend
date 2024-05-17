package com.greethy.annotation.hexagonal;

import java.lang.annotation.*;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Component;

/**
 * Indicates that the annotated class is a <em>Driven Adapter</em>.
 *
 * <p> Driven adapters (also called infrastructure adapters/secondary adapters) enable a software system to interact with external systems
 * by receiving, storing and providing data when requested (like databases, message brokers, sending emails or messages, requesting 3rd party API, etc.).
 *
 * <p> Adapters also can be used to interact with different domains inside single process to avoid coupling between those domains.
 *
 * <p> Adapters are essentially an implementation of ports. They are not supposed to be called directly in any point in code, only through ports(interfaces).
 *
 * @author Kien N.Thanh
 * */
@Component
@Documented
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface DrivenAdapter {

    /**
     * The value may indicate a suggestion for a logical component name,
     * to be turned into a Spring bean in case of an autodetected component.
     * @return the suggested component name, if any (or empty String otherwise)
     */
    @AliasFor(annotation = Component.class)
    String value() default "";
}
