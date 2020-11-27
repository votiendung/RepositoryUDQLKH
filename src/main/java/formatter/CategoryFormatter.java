package formatter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import service.category.CategoryServiceImpl;

import java.text.ParseException;
import java.util.Locale;

public class CategoryFormatter implements Formatter {
    private CategoryServiceImpl categoryService;
    @Autowired
    public CategoryFormatter(CategoryServiceImpl categoryService) {
        this.categoryService = categoryService;
    }

    @Override
    public Object parse(String text, Locale locale) throws ParseException {
        return categoryService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Object object, Locale locale) {
        return null;
    }
}
