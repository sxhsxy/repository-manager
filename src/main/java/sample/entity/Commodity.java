package sample.entity;

import javafx.beans.InvalidationListener;
import javafx.beans.property.*;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;

/**
 * Created by xiaohu on 14-6-7.
 */
@Entity
@SecondaryTable(name = "commodity_inventory")
public class Commodity {
    private Long id;
    private final SimpleStringProperty barcode = new SimpleStringProperty();
    private final SimpleStringProperty name = new SimpleStringProperty();
    private final SimpleStringProperty unit = new SimpleStringProperty();
    /*  SimpleObjectProperty wrapper for decimals */
    private final SimpleObjectProperty<BigDecimal> cost = new SimpleObjectProperty<BigDecimal>();
    private final SimpleObjectProperty<BigDecimal> price = new SimpleObjectProperty<BigDecimal>();

    private Collection<PurchaseRecordDetail> purchaseRecordDetails;
    private Collection<SaleRecordDetail> saleRecordDetails;
    private final SimpleLongProperty initialQuantity = new SimpleLongProperty();
    private final  SimpleLongProperty inputQuantity = new  SimpleLongProperty();
    private final SimpleLongProperty outputQuantity = new  SimpleLongProperty();

    @Id
    @Column(name = "id", nullable = false, insertable = true, updatable = true, length = 19, precision = 0)
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "barcode", nullable = true, insertable = true, updatable = true, length = 16, precision = 0)
    public String getBarcode() {
        return barcode.get();
    }

    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }

    @Transient
    public SimpleStringProperty barcodeProperty() {
        return barcode;
    }

    @Basic
    @Column(name = "name", nullable = false, insertable = true, updatable = true, length = 64, precision = 0)
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
    @Column(name = "unit", nullable = true, insertable = true, updatable = true, length = 16, precision = 0)
    public String getUnit() {
        return unit.get();
    }

    public void setUnit(String unit) {
        this.unit.set(unit);
    }

    @Transient
    public SimpleStringProperty unitProperty() {
        return unit;
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

    @Column(table = "commodity_inventory", name = "initial_quantity")
    public Long getInitialQuantity() {
        return initialQuantity.get();
    }

    public void setInitialQuantity(Long initialQuantity) {
        this.initialQuantity.set(initialQuantity);
    }

    @Transient
    public SimpleLongProperty initialQuantityProperty() {
        return initialQuantity;
    }

    @Column(table = "commodity_inventory", name = "input_quantity")
    public Long getInputQuantity() {
        return inputQuantity.get();
    }

    public void setInputQuantity(Long inputQuantity) {
        this.initialQuantity.set(inputQuantity);
    }

    @Transient
    public SimpleLongProperty inputQuantityProperty() {
        return inputQuantity;
    }

    @Column(table = "commodity_inventory", name = "output_quantity")
    public Long getOutputQuantity() {
        return outputQuantity.get();
    }

    public void setOutputQuantity(Long outputQuantity) {
        this.outputQuantity.set(outputQuantity);
    }

    @Transient
    public SimpleLongProperty outputQuantityProperty() {
        return outputQuantity;
    }

    @OneToMany(mappedBy = "commodity")
    public Collection<PurchaseRecordDetail> getPurchaseRecordDetails() {
        return purchaseRecordDetails;
    }

    public void setPurchaseRecordDetails(Collection<PurchaseRecordDetail> purchaseRecordDetails) {
        this.purchaseRecordDetails = purchaseRecordDetails;
    }

    @OneToMany(mappedBy = "commodity")
    public Collection<SaleRecordDetail> getSaleRecordDetails() {
        return saleRecordDetails;
    }

    public void setSaleRecordDetails(Collection<SaleRecordDetail> saleRecordDetails) {
        this.saleRecordDetails = saleRecordDetails;
    }
}
