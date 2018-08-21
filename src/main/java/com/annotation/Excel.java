package com.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import java.lang.annotation.RetentionPolicy;

@Target(ElementType.FIELD)//标注用来注解field类型
@Retention(RetentionPolicy.RUNTIME)//标注运行时注解
public @interface Excel {

	//不设置default表示必填
	String title();//title表示导出时的标题
	int index();//inde表示导出时改字段的列号
	Class format() default String.class;//默认使用String.class只是标注,自定义的不可能是String.class
}
