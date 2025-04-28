package com.seidor.comerzzia.connector.util.reflections;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import org.reflections.Reflections;
import org.springframework.core.annotation.Order;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ReflectionsUtils {

	public static <T> Object executeVoid(T interfaceObj, Class<?>[] constructorsClasstTypes, Object[] constructorsClassValues, String methodName) {

		return executeVoid(interfaceObj, constructorsClasstTypes, constructorsClassValues, methodName, null, null);
		
	}
	
	@SuppressWarnings({ "unchecked" })
	public static <T> Object executeVoid(T interfaceObj, Class<?>[] constructorsClasstTypes, Object[] constructorsClassValues, String methodName, Class<?>[] typesParamsMethod, Object[] valuesParamsMethod) {
		
		Reflections reflections = new Reflections(interfaceObj);    
		Set<Class<? extends T>> clazzes = reflections.getSubTypesOf((Class<T>) interfaceObj);
		
		List<Class<? extends T>> clazzesSorted = clazzes.stream()
			.filter(m -> m.getAnnotation(Order.class)!= null)
			.sorted(Comparator.comparingInt(m -> m.getAnnotation(Order.class).value()))
			.collect(Collectors.toList());
		
		Method method;
		
	    for (Class<? extends T> clazz : clazzesSorted) {
	    	Class<?> c = null;
			try {
				c = Class.forName(clazz.getName());
				if (c != null) {
					if (!Modifier.isAbstract(clazz.getModifiers())) {
						Constructor<?> cons = c.getDeclaredConstructor(constructorsClasstTypes);
						T obj = (T) cons.newInstance(constructorsClassValues);
						method = obj.getClass().getDeclaredMethod(methodName, typesParamsMethod);
						method.setAccessible(true);
						method.invoke(obj, valuesParamsMethod);
					}	
				}
			} catch (InstantiationException | IllegalAccessException | IllegalArgumentException
						| InvocationTargetException | NoSuchMethodException | ClassNotFoundException | ConstraintViolationException | SecurityException e) {
				log.error("[ReflectionsUtils] - ERRO: {}", e.getLocalizedMessage());
			}
        }
		
		return null;

	}
	
}

