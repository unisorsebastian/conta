package ro.jmind.controller;


import org.ocpsoft.rewrite.annotation.Join;
import org.ocpsoft.rewrite.el.ELBeanName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ro.jmind.model.Timesheet;

@Scope(value = "session")
@Component(value = "productController")
@ELBeanName(value = "productController")
@Join(path = "/product", to = "/product-form.jsf")
public class ProductController {


    @Autowired
    private TimesheetController timesheetController;

    private Timesheet timesheet = new Timesheet();

    public String save() {
        Timesheet timesheet = timesheetController.getTimesheet("2018-08-01");
        return "/product-list.xhtml?faces-redirect=true";
    }

    public Timesheet getProduct() {
        return timesheet;
    }
}