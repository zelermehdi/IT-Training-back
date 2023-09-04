package com.fil.rouge.controllers;

import com.fil.rouge.models.SousTheme;
import com.fil.rouge.Repository.SousThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/sousthemes")
public class SousThemeController {
    @Autowired
    private SousThemeRepository sousThemeRepository;

    @GetMapping
    public List<SousTheme> getAllSousThemes() {
        return sousThemeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<SousTheme> getSousThemeById(@PathVariable Long id) {
        return sousThemeRepository.findById(id);
    }

    @PostMapping
    public SousTheme createSousTheme(@RequestBody SousTheme sousTheme) {
        return sousThemeRepository.save(sousTheme);
    }

    @PutMapping("/{id}")
    public SousTheme updateSousTheme(@PathVariable Long id, @RequestBody SousTheme updatedSousTheme) {
        Optional<SousTheme> existingSousTheme = sousThemeRepository.findById(id);
        if (existingSousTheme.isPresent()) {
            SousTheme sousTheme = existingSousTheme.get();
            sousTheme.setNom(updatedSousTheme.getNom());
            sousTheme.setDescription(updatedSousTheme.getDescription());
            return sousThemeRepository.save(sousTheme);
        }
        return null; 
    }

    @DeleteMapping("/{id}")
    public void deleteSousTheme(@PathVariable Long id) {
        sousThemeRepository.deleteById(id);
    }
}
