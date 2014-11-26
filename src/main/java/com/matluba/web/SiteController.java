package com.matluba.web;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * Controller that demonstrates tiles mapping, request parameters and path variables.
 */
@Controller
public class SiteController {
    private Log log = LogFactory.getLog(this.getClass());

    @RequestMapping(value = {"/home", "/"}, method = RequestMethod.GET)
    public String home() {
        return "site.homepage";
    }

    @RequestMapping(value = "/packages", method = RequestMethod.GET)
    public ModelAndView greet() {
        return new ModelAndView("site.packages");
    }

    @RequestMapping(value = "/contact", method = RequestMethod.GET)
    public ModelAndView greetTwoWays() {
        return new ModelAndView("site.contactus");
    }

    @RequestMapping(value = "/product-view", method = RequestMethod.GET)
    public ModelAndView productview() {
        return new ModelAndView("site.productview");

    }

    @RequestMapping(value = "/products", method = RequestMethod.GET)
    public ModelAndView products() {
        return new ModelAndView("site.products");
    }

    @RequestMapping(value = "/about", method = RequestMethod.GET)
    public ModelAndView about() {
        return new ModelAndView("site.about");
    }

}
