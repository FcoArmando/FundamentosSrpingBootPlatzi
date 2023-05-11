package com.fundamentosplatzi.springboot.fundamentos.bean;

public class MyBeanWithDependencyImplement implements MyBeanWithDependecy{
    MyOperation myOperation;
    @Override
    public void printWithDependency() {
        System.out.println("Hola desde la implmentación de un bean con dependencia");
    }
}
