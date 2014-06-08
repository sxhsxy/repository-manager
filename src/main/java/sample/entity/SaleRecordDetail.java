package sample.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by xiaohu on 14-6-7.
 */
@Entity
@Table(name = "sale_record_detail", schema = "public", catalog = "javafxdb")
public class SaleRecordDetail {
    private Long id;
    private final SimpleObjectProperty<BigDecimal> price = new SimpleObjectProperty<BigDecimal>();
    private final SimpleLongProperty quantity = new SimpleLongProperty();
    private final SimpleObjectProperty<Commodity> commodity = new SimpleObjectProperty<Commodity>();
    private SaleRecord saleRecord;

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "price", nullable = true, insertable = true, updatable = true, length = 16, precision = 2)
    public BigDecimal getPrice() {
        return price.get();
    }

    public void setPrice(BigDecimal price) {
        this.price.set(price);
    }

    @Transient
    public SimpleObjectProperty<BigDecimal> priceProperty() {
        return price;
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

        SaleRecordDetail that = (SaleRecordDetail) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (price != null ? !price.equals(that.price) : that.price != null) return false;
        if (quantity != null ? !quantity.equals(that.quantity) : that.quantity != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
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
    @JoinColumn(name = "sale_record_id", referencedColumnName = "id")
    public SaleRecord getSaleRecord() {
        return saleRecord;
    }

    public void setSaleRecord(SaleRecord saleRecord) {
        this.saleRecord = saleRecord;
    }
}
