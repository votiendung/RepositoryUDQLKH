package service.province;

import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import repository.ProvinceRepository;

public class ProviceServiceImpl implements IProvinceService{
    @Autowired
    private ProvinceRepository provinceRepository;
    @Override
    public Iterable<Province> findAll() {
        return provinceRepository.findAll();
    }

    @Override
    public Province findById(Long id) {
        return provinceRepository.findOne(id);
    }

    @Override
    public void update(Province model) {
    }

    @Override
    public void save(Province model) {
        provinceRepository.save(model);
    }

    @Override
    public void remove(Long id) {
        provinceRepository.delete(id);
    }
}
