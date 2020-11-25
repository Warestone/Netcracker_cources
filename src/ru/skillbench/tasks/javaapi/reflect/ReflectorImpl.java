package ru.skillbench.tasks.javaapi.reflect;

import java.lang.reflect.*;
import java.util.*;
import java.util.stream.Stream;

public class ReflectorImpl implements Reflector {
    Class<?> inputClass = null;

    @Override
    public void setClass(Class<?> clazz) {
        this.inputClass = clazz;
    }

    @Override
    public Stream<String> getMethodNames(Class<?>... paramTypes) {
        if (inputClass == null) throw new NullPointerException();
        HashMap<String, String> methodNames = new HashMap<>();
        Method[] methods = inputClass.getMethods();
        for (Method founded : methods) {
            if (Arrays.equals(founded.getParameterTypes(), paramTypes)) {
                String methodName = founded.getName();
                String returnType = founded.getReturnType().toString();
                if (methodNames.containsKey(methodName)) {
                    if (!methodNames.get(methodName).equals(returnType)) methodNames.put(methodName, returnType);
                } else methodNames.put(methodName, returnType);
            }
        }
        return methodNames.keySet().stream();
    }

    @Override
    public Stream<Field> getAllDeclaredFields() {
        if (inputClass == null) throw new NullPointerException();
        Class<?>superclass = inputClass;
        List<Field> nonStaticFields = new ArrayList<>();
        while (superclass!=null)
        {
            Field[] fields = superclass.getDeclaredFields();
            for (Field founded : fields)
            {
                if (!Modifier.isStatic(founded.getModifiers()))
                {
                    founded.setAccessible(true);
                    nonStaticFields.add(founded);
                }
            }
            superclass = superclass.getSuperclass();
        }
        return nonStaticFields.stream();
    }

    @Override
    public Object getFieldValue(Object target, String fieldName) throws NoSuchFieldException, IllegalAccessException {

        if (inputClass != null) {
            Field field1 = inputClass.getDeclaredField(fieldName);
            field1.setAccessible(true);
            return field1.get(inputClass);
        } else {
            Field field2 = target.getClass().getDeclaredField(fieldName);
            field2.setAccessible(true);
            return field2.get(target);
        }
    }

    @Override
    public Object getMethodResult(Object constructorParam, String methodName, Object... methodParams) throws IllegalAccessException, InstantiationException, NoSuchMethodException, InvocationTargetException
    {
        Class mClassObject = inputClass;
        Constructor constructor = null;
        Object myObject = null;

        if (constructorParam!=null)constructor = mClassObject.getConstructor(constructorParam.getClass());
        else constructor = mClassObject.getConstructor();
        if (constructorParam==null)myObject = constructor.newInstance();
        else myObject = constructor.newInstance(constructorParam.toString());

        Method method = null;
        if (methodParams.length==0)method = mClassObject.getDeclaredMethod(methodName);
        else method = mClassObject.getDeclaredMethod(methodName, methodParams.getClass());
        method.setAccessible(true);
        if (methodParams.length==0) return method.invoke(myObject);
        else return method.invoke(myObject, methodParams.toString());
    }
}




