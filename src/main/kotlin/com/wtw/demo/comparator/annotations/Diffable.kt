package com.wtw.demo.comparator.annotations

import com.wtw.demo.comparator.Differ
import com.wtw.demo.comparator.ObjectDiffer
import java.lang.annotation.Inherited
import java.lang.annotation.Retention
import java.lang.annotation.RetentionPolicy
import kotlin.reflect.KClass

@Target(AnnotationTarget.FIELD, AnnotationTarget.ANNOTATION_CLASS, AnnotationTarget.CLASS)
@Inherited
@Retention(RetentionPolicy.RUNTIME)
annotation class Diffable(val value: KClass<*> = ObjectDiffer::class,
                          val type: DifferCreationType = DifferCreationType.BY_CLASS, val name: String = ""


)
