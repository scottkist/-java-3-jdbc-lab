package edu.cscc;

import edu.cscc.mvc.framework.MVCContext;
import edu.cscc.mvc.framework.Request;

import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        MVCContext mvcContext = new MVCContext("edu.cscc.controllers");
        try {
            mvcContext.processRequest(new Request("Home", "index"));
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }
}
