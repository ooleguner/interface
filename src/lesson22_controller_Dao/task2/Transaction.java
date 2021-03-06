package lesson22_controller_Dao.task2;

import java.util.Date;
import java.util.Objects;

public class Transaction {
    private long id;
    private String city;
    private String description;
    private TransactionType type;
    private Date dateCreated;
    private int amount;

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", city='" + city + '\'' +
                ", description='" + description + '\'' +
                ", type=" + type +
                ", dateCreated=" + dateCreated +
                ", amount=" + amount +
                '}';
    }

    public int getAmount() {
        return amount;
    }

    public Transaction(long id, String city, int amount, String description, TransactionType type, Date dateCreated) {
        this.id = id;
        this.city = city;
        this.amount = amount;
        this.description = description;
        this.type = type;
        this.dateCreated = dateCreated;
    }

    public long getId() {
        return id;
    }

    public String getCity() {
        return city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Transaction that = (Transaction) o;
        return id == that.id &&
                amount == that.amount &&
                city.equals(that.city) &&
                description.equals(that.description) &&
                type.equals(that.type);
    }




    @Override
    public int hashCode() {

        return Objects.hash(id, city, description, type, amount);
    }

    public String getDescription() {

        return description;
    }

    public TransactionType getType() {
        return type;
    }

    public Date getDateCreated() {
        return dateCreated;
    }
}
