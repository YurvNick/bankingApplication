package com.haulmont.bankingApplication.models;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="bank")
public class Bank implements Serializable  {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    @Column(name = "bank_uuid")
    private UUID uuid;

    @Column(name = "bank_name")
    @NotEmpty
    private String name;

    @OneToMany(mappedBy="bank", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<Credit> credit;

    @ManyToMany
    @JoinTable(
            name = "bank_client",
            joinColumns = { @JoinColumn(name = "bank_uuid") },
            inverseJoinColumns = { @JoinColumn(name = "client_uuid") }
    )
    private Set<Client> clients;

    public Bank(String name) {
        this.name = name;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bank bank = (Bank) o;
        return Objects.equals(uuid, bank.uuid);
    }

    @Override
    public int hashCode() {
        return Objects.hash(uuid);
    }

    public void addClient(Client client){
        clients.add(client);
    }

}
