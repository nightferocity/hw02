package com.example.hw02.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Dashboard {

    @Id
    @NonNull
    @GeneratedValue
    private UUID id;

    @OneToMany(mappedBy = "dashboard")
    @ToString.Exclude
    private List<Settings> settings;

    protected Dashboard(){
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Dashboard dashboard = (Dashboard) o;
        return Objects.equals(id, dashboard.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
