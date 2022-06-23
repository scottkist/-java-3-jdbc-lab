package edu.cscc.views;

import edu.cscc.mvc.framework.ApplicationView;
import edu.cscc.mvc.framework.MVCContext;

public class ErrorView extends ApplicationView {
    private String message;

    public ErrorView(MVCContext context, String message) {
        super(context);
        this.message = message;
    }

    @Override
    public void show() {
        System.out.println("Something went wrong:");
        System.out.println(message);
    }
}
