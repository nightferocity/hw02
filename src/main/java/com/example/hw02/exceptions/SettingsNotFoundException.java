package com.example.hw02.exceptions;

import org.springframework.boot.autoconfigure.h2.H2ConsoleProperties;
import org.springframework.data.crossstore.ChangeSetPersister;

import java.util.UUID;

public class SettingsNotFoundException extends RuntimeException {

    public SettingsNotFoundException(UUID id){
        super("Couldn't find settings: " + id);
    }
}
