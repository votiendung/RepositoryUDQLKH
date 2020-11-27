package controller;

import exception.NotFoundException;
import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.customer.ICustomerService;
import service.province.IProvinceService;

import java.util.Optional;

@Controller
@RequestMapping("/customers")
public class CustomerController {
    @Autowired
    ICustomerService iCustomerService;

    @Autowired
    IProvinceService iProvinceService;

    @ModelAttribute("provinces")
    public Iterable<Province> provinces() {
        return iProvinceService.findAll();
    }

    @ExceptionHandler(NotFoundException.class)
    public ModelAndView showInputNotAcceptable() {
        return new ModelAndView("notfound");
    }

    @GetMapping("/create")
    public ModelAndView showCreateForm() {
        ModelAndView modelAndView = new ModelAndView("customer/create");
        modelAndView.addObject("customer", new Customer());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView createCustomer(@Validated @ModelAttribute Customer customer, BindingResult bindingResult) {
        if (bindingResult.hasFieldErrors()){
            return new ModelAndView("customer/create","customer",customer);
        }else {
            iCustomerService.save(customer);
            ModelAndView modelAndView = new ModelAndView("customer/create");
            modelAndView.addObject("customer",new Customer());
            return modelAndView;
        }
    }

    @GetMapping
    public ModelAndView showList(@PageableDefault(size = 10) Pageable pageable, @RequestParam("name")Optional<String> name){

        Page<Customer> customers;
        if (name.isPresent()){
            customers = iCustomerService.findAllByNameContaining(name.get(),pageable);
        }else {
            customers = iCustomerService.findAll(pageable);

        }
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) throws NotFoundException {
        ModelAndView modelAndView = new ModelAndView("customer/edit");
        modelAndView.addObject("customer",iCustomerService.findById(id));
        return modelAndView;
    }
    @PostMapping("/edit/{id}")
    public String editCustomer(@ModelAttribute Customer customer){
        iCustomerService.save(customer);
        return "redirect:/customers";
    }
    @GetMapping("/delete/{id}")
    public String deleteCustomer(@PathVariable Long id){
        iCustomerService.remove(id);
        return "redirect:/customers";
    }

    @GetMapping("/listTop")
    public ModelAndView listCustomers(@PageableDefault(size = 10) Pageable pageable){
        Page<Customer> customers = iCustomerService.findAll(pageable);
        ModelAndView modelAndView = new ModelAndView("customer/list");
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }

}
