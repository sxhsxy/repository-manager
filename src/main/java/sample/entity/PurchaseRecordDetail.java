package sample.entity;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by xiaohu on 14-6-7.
 */
@Entity
@Table(name = "purchase_record_detail", schema = "public", catalog = "javafxdb")
public class PurchaseRecordDetail {
    private Long id;
    private BigDecimal cost;
    private Long quantity;
    private Commodity commodity;
    private PurchaseRecord purchaseRecord;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "cost", nullable = true, insertable = true, updatable = true, length = 16, precision = 2)
    public BigDecimal getCost() {
        return cost;
    }

    public void setCost(BigDecimal cost) {
        this.cost = cost;
    }

    @Basic
    @Column(name = "quantity", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getQuantity() {
        return quantity;
    }

    public void setQuantity(Long quantity) {
        this.quantity = quantity;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PurchaseRecordDetail that = (PurchaseRecordDetail) o;

        if (cost != null ? !cost.equals(that.cost) : that.cost != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (cost != null ? cost.hashCode() : 0);
        result = 31 * result + (quantity != null ? quantity.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "commodity_id", referencedColumnName = "id")
    public Commodity getCommodity() {
        return commodity;
    }

    public void setCommodity(Commodity commodity) {
        this.commodity = commodity;
    }

    @ManyToOne
    @JoinColumn(name = "purchase_record_id", referencedColumnName = "id")
    public PurchaseRecord getPurchaseRecord() {
        return purchaseRecord;
    }

    public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
        this.purchaseRecord = purchaseRecord;
    }
}
