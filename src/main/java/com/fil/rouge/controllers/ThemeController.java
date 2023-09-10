package com.fil.rouge.controllers;

import com.fil.rouge.models.Theme;
import com.fil.rouge.Repository.ThemeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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
    public ResponseEntity<Theme> getThemeById(@PathVariable Long id) {
        Optional<Theme> theme = themeRepository.findById(id);

        if (theme.isPresent()) {
            return ResponseEntity.ok(theme.get());
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public Theme createTheme(@Valid @RequestBody Theme theme) {
        return themeRepository.save(theme);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Theme> updateTheme(@PathVariable Long id, @Valid @RequestBody Theme updatedTheme) {
        Optional<Theme> existingTheme = themeRepository.findById(id);

        if (existingTheme.isPresent()) {
            Theme theme = existingTheme.get();
            theme.setNom(updatedTheme.getNom());
            theme.setDescription(updatedTheme.getDescription());
            return ResponseEntity.ok(themeRepository.save(theme));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTheme(@PathVariable Long id) {
        Optional<Theme> theme = themeRepository.findById(id);

        if (theme.isPresent()) {
            themeRepository.deleteById(id);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/{id}/themes")
    public ResponseEntity<Theme> getThemeWithSousThemes(@PathVariable Long id) {
        Optional<Theme> themeOptional = themeRepository.findById(id);

        if (themeOptional.isPresent()) {
            Theme theme = themeOptional.get();
            
            return ResponseEntity.ok(theme);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
