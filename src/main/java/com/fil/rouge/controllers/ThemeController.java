package com.fil.rouge.controllers;

import com.fil.rouge.models.Theme;
import com.fil.rouge.Repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/themes")
public class ThemeController {
    @Autowired
    private ThemeRepository themeRepository;

    @GetMapping
    public List<Theme> getAllThemes() {
        return themeRepository.findAll();
    }

    @GetMapping("/{id}")
    public Optional<Theme> getThemeById(@PathVariable Long id) {
        return themeRepository.findById(id);
    }

    @PostMapping
    public Theme createTheme(@RequestBody Theme theme) {
        return themeRepository.save(theme);
    }

    @PutMapping("/{id}")
    public Theme updateTheme(@PathVariable Long id, @RequestBody Theme updatedTheme) {
        Optional<Theme> existingTheme = themeRepository.findById(id);
        if (existingTheme.isPresent()) {
            Theme theme = existingTheme.get();
            theme.setNom(updatedTheme.getNom());
            theme.setDescription(updatedTheme.getDescription());
            return themeRepository.save(theme);
        }
        return null; 
    }

    @DeleteMapping("/{id}")
    public void deleteTheme(@PathVariable Long id) {
        themeRepository.deleteById(id);
    }
    
    @GetMapping("/{id}/sousthemes")
    public ResponseEntity<Theme> getThemeWithSousThemes(@PathVariable Long id) {
        Optional<Theme> theme = themeRepository.findById(id);

        if (theme.isPresent()) {
            Theme themeWithSousThemes = theme.get();
            return ResponseEntity.ok(themeWithSousThemes);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    
}
