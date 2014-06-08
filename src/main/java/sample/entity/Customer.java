package sample.entity;

import javafx.beans.property.SimpleStringProperty;

import javax.persistence.*;
import java.util.Collection;


/**
 * Created by xiaohu on 14-6-1.
 */
@Entity
@Table(name = "customer")
public class Customer {
    private final SimpleStringProperty name = new SimpleStringProperty("");
    private final SimpleStringProperty linkman =  new SimpleStringProperty();
    private final SimpleStringProperty tel = new SimpleStringProperty("");
    private final SimpleStringProperty fax = new SimpleStringProperty();
    private final SimpleStringProperty email = new SimpleStringProperty();
    private final SimpleStringProperty address = new SimpleStringProperty("");
    private Long id;
    private Collection<SaleRecord> saleRecords;

    public Customer() {
    }

    public Customer(String name, String tel, String address) {
        setName(name);
        setTel(tel);
        setAddress(address);
    }

    public Customer(String name, String linkman, String tel, String fax, String email, String address) {
        setName(name);
        setLinkman(linkman);
        setTel(tel);
        setFax(fax);
        setEmail(email);
        setAddress(address);
    }

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="hibernate_table_generator")
    @TableGenerator(name = "hibernate_table_generator",
            table = "hibernate_sequence_table",
            pkColumnName = "sequence_name",
            valueColumnName = "next_val",
            pkColumnValue = "customer"
    )
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 128, precision = 0)
    public String getName() {
        return name.get();
    }

    public void setName(String name) {
        this.name.set(name);
    }

    @Transient
    public SimpleStringProperty nameProperty() {
        return name;
    }

    @Basic
    @Column(name = "address", nullable = true, insertable = true, updatable = true, length = 256, precision = 0)
    public String getAddress() {
        return address.get();
    }

    public void setAddress(String address) {
        this.address.set(address);
    }

    @Transient
    public SimpleStringProperty addressProperty() {
        return address;
    }

    @Basic
    @Column(name = "linkman", nullable = true, insertable = true, updatable = true, length = 64, precision = 0)
    public String getLinkman() {
        return linkman.get();
    }

    public void setLinkman(String linkman) {
        this.linkman.set(linkman);
    }

    @Transient
    public SimpleStringProperty linkmanProperty() {
        return linkman;
    }

    @Basic
    @Column(name = "tel", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getTel() {
        return this.tel.get();
    }

    public void setTel(String tel) {
        this.tel.set(tel);
    }

    @Transient
    public SimpleStringProperty telProperty() {
        return tel;
    }

    @Basic
    @Column(name = "fax", nullable = true, insertable = true, updatable = true, length = 32, precision = 0)
    public String getFax() {
        return this.fax.getName();
    }

    public void setFax(String fax) {
        this.fax.set(fax);;
    }

    @Transient
    public SimpleStringProperty faxProperty() {
        return fax;
    }

    @Basic
    @Column(name = "email", nullable = true, insertable = true, updatable = true, length = 256, precision = 0)
    public String getEmail() {
        return this.email.get();
    }

    public void setEmail(String email) {
        this.email.set(email);
    }

    @Transient
    public SimpleStringProperty emailProperty() {
        return email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Customer customer = (Customer) o;

        if (id != null ? !id.equals(customer.id) : customer.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }

    @OneToMany(mappedBy = "customer")
    public Collection<SaleRecord> getSaleRecords() {
        return saleRecords;
    }

    public void setSaleRecords(Collection<SaleRecord> saleRecords) {
        this.saleRecords = saleRecords;
    }
}