package sample.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by xiaohu on 14-6-7.
 */
@Entity
@Table(name = "purchase_record_detail", schema = "public", catalog = "javafxdb")
public class PurchaseRecordDetail {
    private Long id;
    private SimpleObjectProperty<BigDecimal> cost = new SimpleObjectProperty<BigDecimal>();
    private SimpleLongProperty quantity = new SimpleLongProperty();
    private SimpleObjectProperty<Commodity> commodity = new SimpleObjectProperty<Commodity>();
    private SimpleObjectProperty<PurchaseRecord> purchaseRecord = new SimpleObjectProperty<PurchaseRecord>();

    @Id
    @GeneratedValue(strategy= GenerationType.TABLE, generator="hibernate_table_generator")
    @TableGenerator(name = "hibernate_table_generator",
            table = "hibernate_sequence_table",
            pkColumnName = "sequence_name",
            valueColumnName = "next_val",
            pkColumnValue = "purchase_record_detail"
    )
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
        return cost.get();
    }

    public void setCost(BigDecimal cost) {
        this.cost.set(cost);
    }

    @Transient
    public SimpleObjectProperty<BigDecimal> costProperty() {
        return cost;
    }

    @Basic
    @Column(name = "quantity", nullable = true, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getQuantity() {
        return quantity.get();
    }

    public void setQuantity(Long quantity) {
        this.quantity.set(quantity);
    }

    @Transient
    public SimpleLongProperty quantityProperty() {
        return quantity;
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
        return commodity.get();
    }

    public void setCommodity(Commodity commodity) {
        this.commodity.set(commodity);
    }

    @Transient
    public SimpleObjectProperty<Commodity> commodityProperty() {
        return commodity;
    }

    @ManyToOne
    @JoinColumn(name = "purchase_record_id", referencedColumnName = "id")
    public PurchaseRecord getPurchaseRecord() {
        return purchaseRecord.get();
    }

    public void setPurchaseRecord(PurchaseRecord purchaseRecord) {
        this.purchaseRecord.set(purchaseRecord);
    }

    @Transient
    public SimpleObjectProperty<PurchaseRecord> purchaseRecordProperty() {
        return purchaseRecord;
    }

    public PurchaseRecordDetail() {
    }

    public PurchaseRecordDetail(PurchaseRecord purchaseRecord, BigDecimal cost, Long quantity, Commodity commodity) {
        this.setPurchaseRecord(purchaseRecord);
        this.setCost(cost);
        this.setQuantity(quantity);
        this.setCommodity(commodity);
    }
}
