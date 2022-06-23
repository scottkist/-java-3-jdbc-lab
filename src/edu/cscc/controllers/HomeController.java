package edu.cscc.controllers;

import edu.cscc.mvc.framework.ApplicationController;
import edu.cscc.mvc.framework.MVCContext;
import edu.cscc.views.home.HomeGoodbyeView;
import edu.cscc.views.home.HomeIndexView;

public class HomeController extends ApplicationController {

    public HomeController(MVCContext context) {
        super(context);
    }

    public void index() {
        render(new HomeIndexView(context));
    }

    public void goodbye() {
        render(new HomeGoodbyeView(context));
    }
}
