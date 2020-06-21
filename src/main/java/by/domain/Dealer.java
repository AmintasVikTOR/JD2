package by.domain;

import java.sql.Timestamp;
import java.util.Date;
import java.util.Objects;

public class Dealer {
    private Long id;

    private String name;

    private String address;

    private int capacity;

    private Timestamp created;

    private Timestamp changed;

    private Date yearOfFoundation;

    public Dealer() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public Timestamp getChanged() {
        return changed;
    }

    public void setChanged(Timestamp changed) {
        this.changed = changed;
    }

    public Date getYearOfFoundation() {
        return yearOfFoundation;
    }

    public void setYearOfFoundation(java.sql.Date yearOfFoundation) {
        this.yearOfFoundation = yearOfFoundation;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Dealer dealer = (Dealer) o;
        return capacity == dealer.capacity &&
                Objects.equals(id, dealer.id) &&
                Objects.equals(name, dealer.name) &&
                Objects.equals(address, dealer.address) &&
                Objects.equals(created, dealer.created) &&
                Objects.equals(changed, dealer.changed) &&
                Objects.equals(yearOfFoundation, dealer.yearOfFoundation);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, address, capacity, created, changed, yearOfFoundation);
    }

    @Override
    public String toString() {
        return "Dealer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", capacity=" + capacity +
                ", created=" + created +
                ", changed=" + changed +
                ", yearOfFoundation=" + yearOfFoundation +
                '}';
    }
}
