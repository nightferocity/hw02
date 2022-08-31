package com.example.hw02.controllers;

import com.example.hw02.entities.Settings;
import com.example.hw02.exceptions.SettingsNotFoundException;
import com.example.hw02.repositories.SettingsRepository;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.UUID;

@RestController
@Slf4j
public class SettingsController {

    @Autowired
    private SettingsRepository repository;
    @Autowired
    private HttpServletRequest request;

    @GetMapping("/settings")
    List<Settings> all(){
        List<Settings> settings = (List<Settings>) repository.findAll();
        log.info(String.format("User(%s) get: %s", request.getRemoteAddr(), repository.findAll()));
        return settings;
    }

    @PostMapping("/settings")
    Settings newSettings(@RequestBody Settings newSettings){
        log.info("Saved new settings:" + newSettings);
        return repository.save(newSettings);
    }

    @GetMapping("/settings/{id}")
    Settings one(@PathVariable UUID id) {
        Settings setting = repository.findById(id).orElseThrow(() -> new SettingsNotFoundException(id));
        log.info(String.format("Get setting (id:%s): %s", id, setting));
        return setting;
    }

    @PutMapping("/settings/{id}")
    Settings replaceSettings(@RequestBody Settings newSettings, @PathVariable UUID id) {
        Settings settings = repository.findById(id)
                .map(setting -> {
                    setting.setValue(newSettings.getValue());
                    return repository.save(setting);
                })
                .orElseGet(() -> {
                    newSettings.setId(id);
                    return repository.save(newSettings);
                });
        log.info(String.format("Update setting (id:%s): %s", id, settings));
        return settings;
    }

    @DeleteMapping("/settings/{id}")
    void deleteSetting(@PathVariable UUID id) {
        log.info("settings deleted id: " + id);
        repository.deleteById(id);
    }

}
