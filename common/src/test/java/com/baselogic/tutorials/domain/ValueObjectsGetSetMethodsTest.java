package com.baselogic.tutorials.domain;

import com.baselogic.tutorials.util.StringUtilities;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.reflect.ConstructorUtils;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.lang.reflect.*;

import org.apache.commons.lang3.reflect.MethodUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.math.BigDecimal;
import java.net.URL;
import java.net.URLDecoder;
import java.util.*;

import static java.lang.System.out;

public class ValueObjectsGetSetMethodsTest {

    private static final Logger logger = LoggerFactory.getLogger(ValueObjectsGetSetMethodsTest.class);

    private static Map<String, Object> reflectionObjTypeMap = new HashMap<String, Object>();

    static {

        try {
            reflectionObjTypeMap.put("java.util.List", ArrayList.class.newInstance());
            reflectionObjTypeMap.put("java.util.Map", HashMap.class.newInstance());
            reflectionObjTypeMap.put("java.util.Set", HashSet.class.newInstance());
            reflectionObjTypeMap.put("boolean", Boolean.FALSE);
            reflectionObjTypeMap.put("java.lang.Boolean", Boolean.FALSE);
            reflectionObjTypeMap.put("short", new Short("0"));
            reflectionObjTypeMap.put("java.lang.Short", new Short("0"));
            reflectionObjTypeMap.put("int", new Integer(0));
            reflectionObjTypeMap.put("java.lang.Integer", new Integer(0));
            reflectionObjTypeMap.put("long", new Long(0));
            reflectionObjTypeMap.put("java.lang.Long", new Long(0));
            reflectionObjTypeMap.put("float", new Float(0));
            reflectionObjTypeMap.put("java.lang.Float", new Float(0));
            reflectionObjTypeMap.put("double", new Double(0));
            reflectionObjTypeMap.put("java.lang.Double", new Double(0));
            reflectionObjTypeMap.put("java.math.BigDecimal", new BigDecimal(0));
        } catch (Exception excp) {
            out.format("Exception while initializing the reflectionObjTypeMap.");
            excp.printStackTrace();
        }
    }

    private static Object getObjectByReflection(Class clazz) {
        Object result = null;

        try {
            Constructor[] allConstructors = clazz.getDeclaredConstructors();
            Class[] paramTypes = null;

            int ctorParamCount = -1;
            Constructor selectedCtor = null;
            for (Constructor constructor : allConstructors) {
                paramTypes = constructor.getParameterTypes();
                if (ctorParamCount == -1 || paramTypes.length < ctorParamCount) {
                    selectedCtor = constructor;
                    ctorParamCount = paramTypes.length;
                    if (paramTypes.length == 0) break; //Found default ctor
                }
            }

            if (ctorParamCount == 0) {
                //Default ctor exists so newInstance can be used
                result = clazz.newInstance();
            } else {
                ArrayList<Object> paramObjects = new ArrayList<Object>();
                //Loop thru the constructor argument list and construct each one of them.
                for (Class paramTypeClass : paramTypes) {
                    Object obj = getParamForMethodInvocation(paramTypeClass);
                    if (obj != null) {
                        //This argument exists in the basic java list types
                        paramObjects.add(obj);
                    } else {
                        //recursive call here to construct the argument object
                        paramObjects.add(getObjectByReflection(paramTypeClass));
                    }
                }

                result = ConstructorUtils.invokeConstructor(clazz, paramObjects.toArray());
            }

        } catch (Exception excp) {
            //Do nothing here
        }

        return result;
    }

    private static Object getParamForMethodInvocation(Class paramType) {
        Object result = null;
        try {
            if (paramType.isEnum()) {
                result = paramType.getEnumConstants()[0];
            } else if (reflectionObjTypeMap.containsKey(paramType.getCanonicalName())) {
                result = reflectionObjTypeMap.get(paramType.getCanonicalName());
            } else {
                result = paramType.newInstance();
            }
        } catch (Exception excp) {
            out.format("Exception while constructing param object for param type: %s%n", paramType.getCanonicalName());
        }
        return result;
    }

    private static boolean isValidGetOrSetMethod(Class clazz, Method method) {
        boolean result = false;

        String fieldName = method.getName();
        if (fieldName.startsWith("get")) {
            fieldName = StringUtils.uncapitalize(fieldName.replaceFirst("get", ""));
        } else if (fieldName.startsWith("is")) {
            fieldName = StringUtils.uncapitalize(fieldName.replaceFirst("is", ""));
        } else if (fieldName.startsWith("set")) {
            //Skip testing setter methods for an enum as this will update the enum by reference which will cause the
            //tests that come after this JUnit test invoking the getter methods of the enums to FAIL !!!!!!!
            //If necessary write JUnit tests for setters in the corresponding JUnit class with appropriate resets.
            //IMPORTANT: DO NOT REMOVE THIS CHECK !!!!!!!
            if (!clazz.isEnum()) {
                fieldName = StringUtils.uncapitalize(fieldName.replace("set", ""));
            }
        }

        if (StringUtils.isNotBlank(fieldName)) {
            try {
                result = (clazz.getDeclaredField(fieldName) != null);
            } catch (NoSuchFieldException e) {
                //Do nothing and let it go
            }
        }

        return result;
    }

    /**
     * Scans all classes accessible from the context class loader which belong to the given package and subpackages.
     *
     * @param packageName The base package
     * @return The classes
     * @throws ClassNotFoundException
     * @throws IOException
     */
    @SuppressWarnings("unchecked")
    private static List<Class> getClasses(String packageName)
            throws ClassNotFoundException, IOException {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
//        ClassLoader classLoader = ValueObjectsGetSetMethodsTest.class.getClassLoader();

        assert classLoader != null;

        String path = packageName.replace('.', '/');

//        Enumeration<URL> resources = classLoader.getSystemResources(path);
        Enumeration<URL> resources = classLoader.getResources(path);
        logger.debug("***** path: {}", path);
        List<File> dirs = new ArrayList<File>();

        while (resources.hasMoreElements()) {
            URL resource = resources.nextElement();
            String fileName = resource.getFile();
            String fileNameDecoded = URLDecoder.decode(fileName, "UTF-8");
            dirs.add(new File(fileNameDecoded));
            logger.debug("***** fileNameDecoded: {}", fileNameDecoded);
        }

        ArrayList<Class> classes = new ArrayList<Class>();

        for (File directory : dirs) {
            logger.debug("***** DIR: {}", directory.getAbsolutePath());
            classes.addAll(findClasses(directory, packageName));
        }
        return classes;
    }

    /**
     * Recursive method used to find all classes in a given directory and subdirs.
     *
     * @param directory   The base directory
     * @param packageName The package name for classes found inside the base directory
     * @return The classes
     * @throws ClassNotFoundException
     */
    @SuppressWarnings("unchecked")
    private static List<Class> findClasses(File directory, String packageName) throws ClassNotFoundException {
        List<Class> classes = new ArrayList<Class>();
        if (!directory.exists()) {
            return classes;
        }
        File[] files = directory.listFiles();
        for (File file : files) {
            String fileName = file.getName();
            if (file.isDirectory()) {
                assert !fileName.contains(".");
                classes.addAll(findClasses(file, packageName + "." + fileName));
            } else if (fileName.endsWith(".class")
                    && !fileName.contains("$")
                    && !fileName.contains("AsyncResultsContainer")
                    && !fileName.endsWith("Test.class")) {
                Class _class;
                try {
                    _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6));
                } catch (ExceptionInInitializerError e) {
                    // happen, for example, in classes, which depend on
                    // Spring to inject some beans, and which fail,
                    // if dependency is not fulfilled
                    _class = Class.forName(packageName + '.' + fileName.substring(0, fileName.length() - 6),
                            false, Thread.currentThread().getContextClassLoader());
                }
                classes.add(_class);
            }
        }
        return classes;
    }

    //@Test
    public void testValueObjectMethods_invokeGetSetMethods() {
        try {
            // Get all classes in the following packages
            Set<Class> classesSet = new HashSet<Class>(getClasses("com.baselogic.tutorials.domain"));

            logger.debug("No. of classes found: " + classesSet.size());
            for (Class clazz : classesSet) {

                out.format("Class:  %s%n", clazz.getCanonicalName());

                Method[] allMethods = clazz.getDeclaredMethods();
                for (Method method : allMethods) {
                    boolean invokeMethod = false;
                    Object paramForMethodInvocation = null;
                    String methodName = method.getName();
                    //out.format("%s%n", methodName);

                    if (!isValidGetOrSetMethod(clazz, method)) {
                        continue;
                    }

                    if ((methodName.startsWith("get") && method.getGenericParameterTypes().length == 0)
                            || (methodName.startsWith("is") && (method.getGenericReturnType() == boolean.class || method.getGenericReturnType() == Boolean.class))) {
                        //out.format("found getter method %s()%n", methodName);
                        invokeMethod = true;
                    } else if (methodName.startsWith("set")
                            && method.getGenericParameterTypes().length == 1
                            && method.getGenericReturnType() == void.class) {
                        //out.format("found setter method %s()%n", methodName);
                        invokeMethod = true;

                        Class[] paramsTypeList = method.getParameterTypes();
                        //out.format("param type is: %s%n", paramsTypeList[0].getCanonicalName());

                        paramForMethodInvocation = getParamForMethodInvocation(paramsTypeList[0]);
                    }

                    try {
                        if (invokeMethod) {
                            method.setAccessible(true);
                            Object objOfMethodToBeInvoked = null;
                            if (clazz.isEnum()) {
                                Object[] enumConstants = clazz.getEnumConstants();
                                objOfMethodToBeInvoked = enumConstants[0];
                            } else {
                                objOfMethodToBeInvoked = getObjectByReflection(clazz);
                            }

                            Object o = (paramForMethodInvocation == null) ? method.invoke(objOfMethodToBeInvoked)
                                    : method.invoke(objOfMethodToBeInvoked, paramForMethodInvocation);
                            out.format("successfully invoked %s()%n", methodName);
                        }
                    } catch (Exception excp) {
                        out.format(">>>>>>>>Exception while processing:%n");
                        out.format("Class : %s%n", clazz.getCanonicalName());
                        out.format("Method: %s%n", methodName);
                        //out.format("Exception: %s%n", StringUtilities.getExceptionStackTraceString(excp));
                    }

                    //Some useful debug statements
                    out.format("%s%n", method.toGenericString());
                    out.format("  Modifiers:  %s%n", Modifier.toString(method.getModifiers()));
                    //out.format("  [ synthetic=%-5b var_args=%-5b bridge=%-5b ]%n", m.isSynthetic(), m.isVarArgs(), m.isBridge());
                } // for loop

            }
        } catch (Exception excp) {
            out.format("Exception: %s", StringUtilities.getExceptionStackTraceString(excp));
        }
    }

}
