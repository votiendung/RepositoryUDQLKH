package formatter;

import exception.NotFoundException;
import model.Province;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.Formatter;
import org.springframework.stereotype.Component;
import service.province.IProvinceService;
import service.province.ProviceServiceImpl;

import java.text.ParseException;
import java.util.Locale;

@Component
public class ProvinceFormatter implements Formatter<Province> {
    private ProviceServiceImpl proviceService;
    @Autowired
    public ProvinceFormatter(ProviceServiceImpl proviceService) {
        this.proviceService = proviceService;
    }
    @Override
    public Province parse(String text, Locale locale) throws  ParseException{
        return proviceService.findById(Long.parseLong(text));
    }

    @Override
    public String print(Province object, Locale locale) {
        return null;
    }
}
