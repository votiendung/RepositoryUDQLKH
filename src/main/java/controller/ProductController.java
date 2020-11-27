package controller;

import model.Category;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import service.category.ICategoryService;
import service.product.IProductService;

import java.io.File;
import java.io.IOException;

@Controller
@RequestMapping("/products")
public class ProductController {

    @Autowired
    Environment environment;
    @Autowired
    private IProductService iProductService;
    @Autowired
    private ICategoryService iCategoryService;

    @ModelAttribute("category")
    public Iterable<Category> categories(){
        return iCategoryService.findAll();
    }

    @GetMapping
    public ModelAndView showProduct() {
        ModelAndView modelAndView = new ModelAndView("product/list");
        modelAndView.addObject("product", iProductService.findAll());
        return modelAndView;
    }

    @GetMapping("/create")
    public ModelAndView showProductForm() {
        ModelAndView modelAndView = new ModelAndView("product/create");
        modelAndView.addObject("product", new Product());
        return modelAndView;
    }

    @PostMapping("/create")
    public ModelAndView saveProduct(@ModelAttribute Product product) {
        MultipartFile multipartFile = product.getAvatar();
        String image = multipartFile.getOriginalFilename();
        String fileUpload = environment.getProperty("file_upload");
        try {
            FileCopyUtils.copy(multipartFile.getBytes(), new File(fileUpload + image));
        }catch (IOException e) {
            e.printStackTrace();
        }
        Product productDB = new Product(product.getProName(), product.getProPrice(), product.getProQuantity(),image, product.getCategory());
        iProductService.save(productDB);
        return new ModelAndView("product/create","product",new Product());
    }

    @GetMapping("/delete/{id}")
    public String DeleteProduct(@PathVariable Long id){
        iProductService.remove(id);
        return "redirect:/products";
    }


}
