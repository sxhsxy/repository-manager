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
@Table(name = "purchase_record")
public class PurchaseRecord {
    private final SimpleLongProperty id = new SimpleLongProperty();
    private final SimpleObjectProperty<Timestamp> datetime = new SimpleObjectProperty<Timestamp>();
    private final SimpleObjectProperty<Supplier> supplier = new SimpleObjectProperty<Supplier>();
    private Collection<PurchaseRecordDetail> purchaseRecordDetails;

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="hibernate_table_generator")
    @TableGenerator(name = "hibernate_table_generator",
            table = "hibernate_sequence_table",
            pkColumnName = "sequence_name",
            valueColumnName = "next_val",
            pkColumnValue = "purchase_record"
    )
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

        PurchaseRecord that = (PurchaseRecord) o;

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
    @JoinColumn(name = "supplier_id", referencedColumnName = "id")
    public Supplier getSupplier() {
        return supplier.get();
    }

    public void setSupplier(Supplier supplier) {
        this.supplier.set(supplier);
    }

    public SimpleObjectProperty<Supplier> supplierProperty() {
        return supplier;
    }

    @OneToMany(mappedBy = "purchaseRecord")
    public Collection<PurchaseRecordDetail> getPurchaseRecordDetails() {
        return purchaseRecordDetails;
    }

    public void setPurchaseRecordDetails(Collection<PurchaseRecordDetail> purchaseRecordDetails) {
        this.purchaseRecordDetails = purchaseRecordDetails;
    }

    public PurchaseRecord() {
    }

    public PurchaseRecord(Timestamp datetime, Supplier supplier) {
        this.setDatetime(datetime);
        this.setSupplier(supplier);
    }
}
