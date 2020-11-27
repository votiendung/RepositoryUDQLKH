package service.product;

import exception.NotFoundException;
import model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ProductRepository;

public class ProductServiceImpl implements IProductService{
    @Autowired
    ProductRepository productRepository;
    @Override
    public Iterable<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public Product findById(Long id) throws NotFoundException {
        return productRepository.findOne(id);
    }

    @Override
    public void update(Product model) {
        productRepository.save(model);
    }

    @Override
    public void save(Product model) {
        productRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        productRepository.delete(id);
    }
}
