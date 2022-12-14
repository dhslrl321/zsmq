package com.github.dhslrl321.zsmq.annotation;

import com.github.dhslrl321.zsmq.listener.DeletionPolicy;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ZolaMessageListener {
    String queueName();
    DeletionPolicy deletionPolicy();
}
