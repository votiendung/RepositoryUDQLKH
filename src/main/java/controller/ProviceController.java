package controller;

import exception.NotFoundException;
import model.Customer;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import service.customer.ICustomerService;
import service.province.IProvinceService;

@Controller
@RequestMapping("/province")
public class ProviceController {
    @Autowired
    private IProvinceService iProvinceService;

    @Autowired
    private ICustomerService iCustomerService;

    @GetMapping("/create")
            public ModelAndView showCreateForm() {
    ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("provinces",new Province());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProvince(@ModelAttribute Province province){
        iProvinceService.save(province);
        ModelAndView modelAndView = new ModelAndView("province/create");
        modelAndView.addObject("province", new Province());
        modelAndView.addObject("message", "New province created successfully");
        return modelAndView;
    }

    @GetMapping
    public ModelAndView showProvince(){
        ModelAndView modelAndView = new ModelAndView("province/list");
        modelAndView.addObject("province",iProvinceService.findAll());
        return modelAndView;
    }

    @GetMapping("/edit/{id}")
    public ModelAndView showEditForm(@PathVariable Long id) throws NotFoundException {
            ModelAndView modelAndView = new ModelAndView("/province/edit");
            modelAndView.addObject("province", iProvinceService.findById(id));
            return modelAndView;
    }

    @PostMapping("/edit/{id}")
    public String updateProvince(@ModelAttribute Province province){
        iProvinceService.save(province);
        return "redirect:/province";
    }

    @GetMapping("/delete/{id}")
    public String deleteProvince(@PathVariable Long id){
        iProvinceService.remove(id);
        return "redirect:/province";
    }

    @GetMapping("/view/{id}")
    public ModelAndView viewProvince(@PathVariable("id") Long id) throws NotFoundException{
        Province province = iProvinceService.findById(id);
//        if(province == null){
//            return new ModelAndView("/error.404");
//        }

        Iterable<Customer> customers = iCustomerService.findAllByProvince(province);

        ModelAndView modelAndView = new ModelAndView("/province/view");
        modelAndView.addObject("province", province);
        modelAndView.addObject("customers", customers);
        return modelAndView;
    }


}
