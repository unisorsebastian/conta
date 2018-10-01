package ro.jmind.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ro.jmind.service.BillableDayService;

@Controller
public class MainController {
    @RequestMapping(value = { "/testJsp" }, method = RequestMethod.GET)
    public String testJspView() {

        return "testJsp";
    }

    @RequestMapping(value = { "/testThymeleaf" }, method = RequestMethod.GET)
    public String testThymeleafView() {

        return "th_page1";
    }

    @RequestMapping(value = { "/testFreeMarker" }, method = RequestMethod.GET)
    public String testFreeMarkerView() {

        return "testFreeMarker";
    }

}
