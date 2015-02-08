package sample.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by xiaohu on 14-6-7.
 */
@Entity
@Table(name = "sale_record")
public class SaleRecord {
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleObjectProperty<Timestamp> datetime = new SimpleObjectProperty<Timestamp>();
    private final SimpleObjectProperty<Customer> customer = new SimpleObjectProperty<Customer>();
    private Collection<SaleRecordDetail> saleRecordDetails;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id.get();
    }

    public void setId(Long id) {
        this.id.set(id);
    }

    @Transient
    public SimpleLongProperty idProperty() {
        return id;
    }

    @Basic
    @Column(name = "datetime", nullable = true, insertable = true, updatable = true, length = 35, precision = 6)
    public Timestamp getDatetime() {
        return datetime.get();
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime.set(datetime);
    }

    @Transient
    public SimpleObjectProperty<Timestamp> datetimeProperty() {
        return datetime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SaleRecord that = (SaleRecord) o;

        if (datetime != null ? !datetime.equals(that.datetime) : that.datetime != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (datetime != null ? datetime.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "customer_id", referencedColumnName = "id")
    public Customer getCustomer() {
        return customer.get();
    }

    public void setCustomer(Customer customer) {
        this.customer.set(customer);
    }

    @Transient
    public SimpleObjectProperty<Customer> customerProperty() {
        return customer;
    }

    @OneToMany(mappedBy = "saleRecord")
    public Collection<SaleRecordDetail> getSaleRecordDetails() {
        return saleRecordDetails;
    }

    public void setSaleRecordDetails(Collection<SaleRecordDetail> saleRecordDetails) {
        this.saleRecordDetails = saleRecordDetails;
    }
}
