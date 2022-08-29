package com.example.hw02.repositories;


import com.example.hw02.entities.Settings;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface SettingsRepository extends CrudRepository<Settings, UUID> {

}
