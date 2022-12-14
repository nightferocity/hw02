package com.example.hw02.entities;

import lombok.*;
import org.hibernate.Hibernate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.Objects;
import java.util.UUID;


@Getter
@Setter
@ToString
@RequiredArgsConstructor
@Entity
public class Settings {

    @Id
    @NonNull
    @GeneratedValue
    private UUID id;
    @NonNull
    private String value;
    @ManyToOne
    private Dashboard dashboard;

    protected Settings() {}

    public Settings(@NonNull String value){
        this.value = value;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Settings settings = (Settings) o;
        return Objects.equals(id, settings.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }
}
