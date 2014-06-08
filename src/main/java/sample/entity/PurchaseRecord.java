package sample.entity;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

/**
 * Created by xiaohu on 14-6-7.
 */
@Entity
@Table(name = "purchase_record", schema = "public", catalog = "javafxdb")
public class PurchaseRecord {
    private Long id;
    private Timestamp datetime;
    private Supplier supplier;
    private Collection<PurchaseRecordDetail> purchaseRecordDetails;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "datetime", nullable = true, insertable = true, updatable = true, length = 35, precision = 6)
    public Timestamp getDatetime() {
        return datetime;
    }

    public void setDatetime(Timestamp datetime) {
        this.datetime = datetime;
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
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier = supplier;
    }

    @OneToMany(mappedBy = "purchaseRecord")
    public Collection<PurchaseRecordDetail> getPurchaseRecordDetails() {
        return purchaseRecordDetails;
    }

    public void setPurchaseRecordDetails(Collection<PurchaseRecordDetail> purchaseRecordDetails) {
        this.purchaseRecordDetails = purchaseRecordDetails;
    }
}
