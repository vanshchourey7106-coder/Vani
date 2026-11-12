
package com.vani.controller;

import com.vani.dto.JournalRequest;
import com.vani.dto.JournalResponse;
import com.vani.service.JournalService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/journal")
public class JournalController {

    private final JournalService journalService;

    public JournalController(JournalService journalService) {
        this.journalService = journalService;
    }

    @PostMapping
    public JournalResponse createJournal(
            @Valid @RequestBody JournalRequest request) {

        return journalService.createJournal(request);
    }

    @GetMapping
    public List<JournalResponse> getAllJournals() {

        return journalService.getAllJournals();
    }

    @GetMapping("/{id}")
    public JournalResponse getJournalById(
            @PathVariable String id) {

        return journalService.getById(id);
    }

    @PutMapping("/{id}")
    public JournalResponse updateJournal(
            @PathVariable String id,
            @Valid @RequestBody JournalRequest request) {

        return journalService.updateJournal(id, request);
    }

    @DeleteMapping("/{id}")
    public String deleteJournal(@PathVariable String id) {

        journalService.deleteJournal(id);

        return "Entry Deleted";
    }
}
