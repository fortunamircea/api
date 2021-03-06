/*
 * This file is generated by jOOQ.
 */
package persistence.db.tables;


import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import org.jooq.Field;
import org.jooq.ForeignKey;
import org.jooq.Name;
import org.jooq.Record;
import org.jooq.Row4;
import org.jooq.Schema;
import org.jooq.Table;
import org.jooq.TableField;
import org.jooq.TableOptions;
import org.jooq.impl.DSL;
import org.jooq.impl.SQLDataType;
import org.jooq.impl.TableImpl;

import persistence.db.Keys;
import persistence.db.Public;
import persistence.db.tables.records.PurchaseHistoryRecord;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class PurchaseHistory extends TableImpl<PurchaseHistoryRecord> {

    private static final long serialVersionUID = 1L;

    /**
     * The reference instance of <code>public.purchase_history</code>
     */
    public static final PurchaseHistory PURCHASE_HISTORY = new PurchaseHistory();

    /**
     * The class holding records for this type
     */
    @Override
    public Class<PurchaseHistoryRecord> getRecordType() {
        return PurchaseHistoryRecord.class;
    }

    /**
     * The column <code>public.purchase_history.id</code>.
     */
    public final TableField<PurchaseHistoryRecord, UUID> ID = createField(DSL.name("id"), SQLDataType.UUID.defaultValue(DSL.field("uuid_generate_v4()", SQLDataType.UUID)), this, "");

    /**
     * The column <code>public.purchase_history.customers_id</code>.
     */
    public final TableField<PurchaseHistoryRecord, UUID> CUSTOMERS_ID = createField(DSL.name("customers_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.purchase_history.card_id</code>.
     */
    public final TableField<PurchaseHistoryRecord, UUID> CARD_ID = createField(DSL.name("card_id"), SQLDataType.UUID.nullable(false), this, "");

    /**
     * The column <code>public.purchase_history.total_value</code>.
     */
    public final TableField<PurchaseHistoryRecord, BigDecimal> TOTAL_VALUE = createField(DSL.name("total_value"), SQLDataType.NUMERIC, this, "");

    private PurchaseHistory(Name alias, Table<PurchaseHistoryRecord> aliased) {
        this(alias, aliased, null);
    }

    private PurchaseHistory(Name alias, Table<PurchaseHistoryRecord> aliased, Field<?>[] parameters) {
        super(alias, null, aliased, parameters, DSL.comment(""), TableOptions.table());
    }

    /**
     * Create an aliased <code>public.purchase_history</code> table reference
     */
    public PurchaseHistory(String alias) {
        this(DSL.name(alias), PURCHASE_HISTORY);
    }

    /**
     * Create an aliased <code>public.purchase_history</code> table reference
     */
    public PurchaseHistory(Name alias) {
        this(alias, PURCHASE_HISTORY);
    }

    /**
     * Create a <code>public.purchase_history</code> table reference
     */
    public PurchaseHistory() {
        this(DSL.name("purchase_history"), null);
    }

    public <O extends Record> PurchaseHistory(Table<O> child, ForeignKey<O, PurchaseHistoryRecord> key) {
        super(child, key, PURCHASE_HISTORY);
    }

    @Override
    public Schema getSchema() {
        return Public.PUBLIC;
    }

    @Override
    public List<ForeignKey<PurchaseHistoryRecord, ?>> getReferences() {
        return Arrays.<ForeignKey<PurchaseHistoryRecord, ?>>asList(Keys.PURCHASE_HISTORY__FK_CUSTOMERS, Keys.PURCHASE_HISTORY__FK_CART);
    }

    private transient Customers _customers;
    private transient Cart _cart;

    public Customers customers() {
        if (_customers == null)
            _customers = new Customers(this, Keys.PURCHASE_HISTORY__FK_CUSTOMERS);

        return _customers;
    }

    public Cart cart() {
        if (_cart == null)
            _cart = new Cart(this, Keys.PURCHASE_HISTORY__FK_CART);

        return _cart;
    }

    @Override
    public PurchaseHistory as(String alias) {
        return new PurchaseHistory(DSL.name(alias), this);
    }

    @Override
    public PurchaseHistory as(Name alias) {
        return new PurchaseHistory(alias, this);
    }

    /**
     * Rename this table
     */
    @Override
    public PurchaseHistory rename(String name) {
        return new PurchaseHistory(DSL.name(name), null);
    }

    /**
     * Rename this table
     */
    @Override
    public PurchaseHistory rename(Name name) {
        return new PurchaseHistory(name, null);
    }

    // -------------------------------------------------------------------------
    // Row4 type methods
    // -------------------------------------------------------------------------

    @Override
    public Row4<UUID, UUID, UUID, BigDecimal> fieldsRow() {
        return (Row4) super.fieldsRow();
    }
}
