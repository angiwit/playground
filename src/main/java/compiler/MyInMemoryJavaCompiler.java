package compiler;

import org.mdkt.compiler.InMemoryJavaCompiler;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Method;

public class MyInMemoryJavaCompiler {

    public static void main(String[] args) throws Exception {
        StringBuilder sourceCode = new StringBuilder();
        sourceCode.append("package org.mdkt;\n");
        sourceCode.append("public class HelloClass {\n");
        sourceCode.append("   public String hello() { return \"hello\"; }");
        sourceCode.append("}");

        Class<?> helloClass = InMemoryJavaCompiler.newInstance().compile("org.mdkt.HelloClass", sourceCode.toString());
        Object instance = helloClass.getConstructor().newInstance();
        Method method = helloClass.getMethod("hello");
        System.out.println(ReflectionUtils.invokeMethod(method, instance));
    }
}
