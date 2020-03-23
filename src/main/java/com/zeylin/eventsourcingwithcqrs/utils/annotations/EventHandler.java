package com.zeylin.eventsourcingwithcqrs.utils.annotations;

import com.zeylin.eventsourcingwithcqrs.utils.EventType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface EventHandler {
    public EventType type() default EventType.UNDEFINED;
}
