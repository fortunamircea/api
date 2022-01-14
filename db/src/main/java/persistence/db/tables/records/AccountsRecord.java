/*
 * This file is generated by jOOQ.
 */
package persistence.db.tables.records;


import java.time.LocalDateTime;

import org.jooq.Field;
import org.jooq.Record1;
import org.jooq.Record3;
import org.jooq.Row3;
import org.jooq.impl.UpdatableRecordImpl;

import persistence.db.tables.Accounts;


/**
 * This class is generated by jOOQ.
 */
@SuppressWarnings({ "all", "unchecked", "rawtypes" })
public class AccountsRecord extends UpdatableRecordImpl<AccountsRecord> implements Record3<Long, String, LocalDateTime> {

    private static final long serialVersionUID = 1L;

    /**
     * Setter for <code>public.accounts.id</code>.
     */
    public void setId(Long value) {
        set(0, value);
    }

    /**
     * Getter for <code>public.accounts.id</code>.
     */
    public Long getId() {
        return (Long) get(0);
    }

    /**
     * Setter for <code>public.accounts.type</code>.
     */
    public void setType(String value) {
        set(1, value);
    }

    /**
     * Getter for <code>public.accounts.type</code>.
     */
    public String getType() {
        return (String) get(1);
    }

    /**
     * Setter for <code>public.accounts.created_time</code>.
     */
    public void setCreatedTime(LocalDateTime value) {
        set(2, value);
    }

    /**
     * Getter for <code>public.accounts.created_time</code>.
     */
    public LocalDateTime getCreatedTime() {
        return (LocalDateTime) get(2);
    }

    // -------------------------------------------------------------------------
    // Primary key information
    // -------------------------------------------------------------------------

    @Override
    public Record1<Long> key() {
        return (Record1) super.key();
    }

    // -------------------------------------------------------------------------
    // Record3 type implementation
    // -------------------------------------------------------------------------

    @Override
    public Row3<Long, String, LocalDateTime> fieldsRow() {
        return (Row3) super.fieldsRow();
    }

    @Override
    public Row3<Long, String, LocalDateTime> valuesRow() {
        return (Row3) super.valuesRow();
    }

    @Override
    public Field<Long> field1() {
        return Accounts.ACCOUNTS.ID;
    }

    @Override
    public Field<String> field2() {
        return Accounts.ACCOUNTS.TYPE;
    }

    @Override
    public Field<LocalDateTime> field3() {
        return Accounts.ACCOUNTS.CREATED_TIME;
    }

    @Override
    public Long component1() {
        return getId();
    }

    @Override
    public String component2() {
        return getType();
    }

    @Override
    public LocalDateTime component3() {
        return getCreatedTime();
    }

    @Override
    public Long value1() {
        return getId();
    }

    @Override
    public String value2() {
        return getType();
    }

    @Override
    public LocalDateTime value3() {
        return getCreatedTime();
    }

    @Override
    public AccountsRecord value1(Long value) {
        setId(value);
        return this;
    }

    @Override
    public AccountsRecord value2(String value) {
        setType(value);
        return this;
    }

    @Override
    public AccountsRecord value3(LocalDateTime value) {
        setCreatedTime(value);
        return this;
    }

    @Override
    public AccountsRecord values(Long value1, String value2, LocalDateTime value3) {
        value1(value1);
        value2(value2);
        value3(value3);
        return this;
    }

    // -------------------------------------------------------------------------
    // Constructors
    // -------------------------------------------------------------------------

    /**
     * Create a detached AccountsRecord
     */
    public AccountsRecord() {
        super(Accounts.ACCOUNTS);
    }

    /**
     * Create a detached, initialised AccountsRecord
     */
    public AccountsRecord(Long id, String type, LocalDateTime createdTime) {
        super(Accounts.ACCOUNTS);

        setId(id);
        setType(type);
        setCreatedTime(createdTime);
    }
}
