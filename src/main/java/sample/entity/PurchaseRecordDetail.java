package sample.entity;

import javafx.beans.property.SimpleLongProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;

import javax.persistence.*;
import java.math.BigDecimal;

/**
 * Created by xiaohu on 14-6-7.
 */
@Entity
@Table(name = "purchase_record_detail")
public class PurchaseRecordDetail {
    private Long id;
    private final SimpleObjectProperty<BigDecimal> cost = new SimpleObjectProperty<BigDecimal>();
    private final SimpleLongProperty quantity = new SimpleLongProperty();
    private final SimpleObjectProperty<Commodity> commodity = new SimpleObjectProperty<Commodity>();
    private final SimpleObjectProperty<PurchaseRecord> purchaseRecord = new SimpleObjectProperty<PurchaseRecord>();

    //Transient Properties
    private final SimpleStringProperty unit = new SimpleStringProperty();
    private final SimpleStringProperty barcode = new SimpleStringProperty();
    private final SimpleObjectProperty<BigDecimal> value = new SimpleObjectProperty<BigDecimal>();


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

    @Transient
    public String getUnit() {
        return unit.get();
    }
    @Transient
    public void setUnit(String unit) {
        this.unit.set(unit);
    }
    @Transient
    public SimpleStringProperty unitProperty() {
        return unit;
    }

    @Transient
    public String getBarcode() {
        return barcode.get();
    }
    @Transient
    public void setBarcode(String barcode) {
        this.barcode.set(barcode);
    }
    @Transient
    public SimpleStringProperty barcodeProperty() {
        return barcode;
    }

    @Transient
    public BigDecimal getValue() {
        return value.get();
    }
@Transient
    public void setValue(BigDecimal value) {
        this.value.set(value);
    }

    @Transient
    public SimpleObjectProperty<BigDecimal> valueProperty() {
        return value;
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
        //bindValues();
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
        bindValues();

    }


    public PurchaseRecordDetail(PurchaseRecord purchaseRecord, BigDecimal cost, Long quantity, Commodity commodity) {
        this.setPurchaseRecord(purchaseRecord);
        this.setCost(cost);
        this.setQuantity(quantity);
        this.setCommodity(commodity);
        bindValues();
    }

    private void bindValues() {
        commodityProperty().addListener(new ChangeListener<Commodity>() {
            @Override
            public void changed(ObservableValue<? extends Commodity> observable, Commodity oldValue, Commodity newValue) {
                barcodeProperty().bind(getCommodity().barcodeProperty());
                unitProperty().bind(getCommodity().unitProperty());
                costProperty().addListener(new ChangeListener<BigDecimal>() {
                    @Override
                    public void changed(ObservableValue<? extends BigDecimal> observable, BigDecimal oldValue, BigDecimal newValue) {
                        setValue(newValue.multiply(BigDecimal.valueOf(getQuantity())));
                    }
                });
                quantityProperty().addListener(new ChangeListener<Number>() {
                    @Override
                    public void changed(ObservableValue<? extends Number> observable, Number oldValue, Number newValue) {
                        setValue(getCost().multiply(BigDecimal.valueOf(getQuantity())));
                    }
                });
                // Set initial value for cost...
                setCost(newValue.getCost());
            }
        });

    }
}
