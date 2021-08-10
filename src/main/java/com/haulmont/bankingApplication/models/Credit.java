package com.haulmont.bankingApplication.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="credit")
public class Credit implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "credit_uuid")
    private UUID uuid;

    @Column(name = "credit_name")
    @NotEmpty
    @Size(min = 5, max = 50)
    private String name;

    @Column(name = "credit_limit")
    @Min(value = 1)
    @Max(value = Long.MAX_VALUE)
    private long limit;

    @Column(name = "interest_rate")
    @DecimalMin("0")
    @DecimalMax("0.9999")
    private float interestRate;

    @OneToMany(mappedBy = "credit", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<CreditOffer> creditOffers;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "bank_uuid")
    private Bank bank;

    public Credit(String name, long limit, float interestRate, Bank bank) {
        this.name = name;
        this.limit = limit;
        this.interestRate = interestRate;
        this.bank = bank;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Credit credit = (Credit) o;
        return Objects.equals(uuid, credit.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
