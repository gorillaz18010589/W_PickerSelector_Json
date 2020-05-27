package com.example.pickerselector.Model;

import java.util.List;

public class JsonBean {
    public String a;
    public List<B> b;
    public C c;
    public static class B {
        public String b1;
        public String b2;
    }
    public static class C {
        public String c1;
        public String c2;
    }

    public String getA() {
        return a;
    }

    public void setA(String a) {
        this.a = a;
    }

    public List<B> getB() {
        return b;
    }

    public void setB(List<B> b) {
        this.b = b;
    }

    public C getC() {
        return c;
    }

    public void setC(C c) {
        this.c = c;
    }
}
