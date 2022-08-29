package com.example.hw02.entities;

import lombok.Data;
import lombok.NonNull;

import javax.persistence.*;
import java.util.UUID;


@Data
@Entity
public class Settings {

    @Id
    @NonNull
    @GeneratedValue
    private UUID id;
    @NonNull
    private String value;

    protected Settings() {}

    public Settings(@NonNull String value){
        this.value = value;
    }
}
