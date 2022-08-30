package com.example.hw02.controllers;

import com.example.hw02.entities.Settings;
import com.example.hw02.exceptions.SettingsNotFoundException;
import com.example.hw02.repositories.SettingsRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
public class SettingsController {

    @Autowired
    private SettingsRepository repository;

    @GetMapping("/settings")
    List<Settings> all(){
        return (List<Settings>) repository.findAll();
    }

    @PostMapping("/settings")
    Settings newSettings(@RequestBody Settings newSettings){
        return repository.save(newSettings);
    }

    @GetMapping("/settings/{id}")
    Settings one(@PathVariable UUID id) {

        return repository.findById(id)
                .orElseThrow(() -> new SettingsNotFoundException(id));
    }

    @PutMapping("/settings/{id}")
    Settings replaceSettings(@RequestBody Settings newSettings, @PathVariable UUID id) {

        return repository.findById(id)
                .map(settings -> {
                    settings.setValue(newSettings.getValue());
                    return repository.save(settings);
                })
                .orElseGet(() -> {
                    newSettings.setId(id);
                    return repository.save(newSettings);
                });
    }

    @DeleteMapping("/settings/{id}")
    void deleteSetting(@PathVariable UUID id) {
        repository.deleteById(id);
    }

}
