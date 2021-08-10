package com.haulmont.bankingApplication.models;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@Entity
@Table(name="credit_offer")
public class CreditOffer implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "credit_offer_uuid")
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "client_uuid")
    private Client client;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "credit_uuid")
    private Credit credit;

    @Column(name = "amount")
    @Min(value = 0)
    @Max(value = Long.MAX_VALUE)
    private long amount;

    @Column(name = "interest_total")
    @Min(value = 0)
    @Max(value = Long.MAX_VALUE)
    private long interestTotal;

    @Column(name = "credit_term")
    @Min(value = 1)
    @Max(value = 240)
    private int creditTerm;

    @Column(name = "first_payment_date")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;

    @OneToMany(mappedBy = "creditOffer", fetch = FetchType.EAGER, cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Payment> paymentList;

    public CreditOffer(UUID uuid, Client client, Credit credit, long amount, int creditTerm, LocalDate date) {
        this.uuid = uuid;
        this.client = client;
        this.credit = credit;
        this.amount = amount;
        this.creditTerm = creditTerm;
        this.date = date;
        this.paymentList = new ArrayList<>();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CreditOffer creditOffer = (CreditOffer) o;
        return Objects.equals(uuid, creditOffer.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }
}
