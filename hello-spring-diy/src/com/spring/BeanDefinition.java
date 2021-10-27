package com.spring;

public class BeanDefinition {
    private Class className;
    private String scope;
    public void setClassName(Class className) {
        this.className = className;
    }
    public Class getClassName() {
        return className;
    }
    public String getScope() {
        return scope;
    }
    public void setScope(String scope) {
        this.scope = scope;
    }
}
