package sample.entity;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;

/**
 * Created by xiaohu on 14-6-1.
 */
@Entity
@Table(name = "supplier")
public class Supplier {
    private Long id;
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty phone = new SimpleStringProperty("");
    private final SimpleStringProperty address = new SimpleStringProperty("");

    public Supplier() {
    }

    public Supplier(String name, String phone, String address) {
        setName(name);
        setPhone(phone);
        setAddress(address);
    }


    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="hibernate_table_generator")
    @TableGenerator(name = "hibernate_table_generator",
            table = "hibernate_sequence_table",
            pkColumnName = "sequence_name",
            valueColumnName = "next_val",
            pkColumnValue = "supplier"
    )
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name.get();
    }

    @Transient
    public SimpleStringProperty nameProperty() {
        return name;
    }

    public void setName(String name) {
        this.name.set(name);
    }

    public String getPhone() {
        return phone.get();
    }

    @Transient
    public SimpleStringProperty phoneProperty() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone.set(phone);
    }

    public String getAddress() {
        return address.get();
    }

    @Transient
    public SimpleStringProperty addressProperty() {
        return address;
    }

    public void setAddress(String address) {
        this.address.set(address);
    }
}