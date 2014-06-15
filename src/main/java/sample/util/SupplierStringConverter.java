package sample.util;

import javafx.util.StringConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import sample.entity.Supplier;
import sample.repository.SupplierRepository;

/**
 * Created by xiaohu on 14-6-14.
 */

@Component
public class SupplierStringConverter extends StringConverter<Supplier> {
    @Autowired
    private SupplierRepository supplierRepository;
    @Override
    public String toString(Supplier object) {
        return object.getName();
    }

    @Override
    public Supplier fromString(String string) {
        return supplierRepository.findByName(string);
    }
}
