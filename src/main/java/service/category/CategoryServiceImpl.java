package service.category;

import exception.NotFoundException;
import model.Category;
import org.springframework.beans.factory.annotation.Autowired;
import repository.CategoryRepository;

public class CategoryServiceImpl implements ICategoryService{
    @Autowired
    CategoryRepository categoryRepository;
    @Override
    public Iterable<Category> findAll() {
        return categoryRepository.findAll();
    }

    @Override
    public Category findById(Long id){
        return categoryRepository.findOne(id);
    }

    @Override
    public void update(Category model) {

    }

    @Override
    public void save(Category model) {
        categoryRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        categoryRepository.delete(id);
    }
}
