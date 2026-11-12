
package com.vani.service;

import com.vani.dto.JournalRequest;
import com.vani.dto.JournalResponse;
import com.vani.entity.Journal;
import com.vani.entity.User;
import com.vani.exception.JournalNotFoundException;
import com.vani.exception.UserNotFoundException;
import com.vani.repository.JournalRepository;
import com.vani.repository.UserRepository;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class JournalService {

    private final JournalRepository journalRepository;
    private final UserRepository userRepository;

    public JournalService(
            JournalRepository journalRepository,
            UserRepository userRepository) {

        this.journalRepository = journalRepository;
        this.userRepository = userRepository;
    }

    // Identify currently logged-in user
    private User getCurrentUser() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        String userName = authentication.getName();

        return userRepository.findByUserName(userName)
                .orElseThrow(() ->
                        new UserNotFoundException("User not found"));
    }

    // Convert Journal entity into JournalResponse
    private JournalResponse toResponse(Journal journal) {

        return new JournalResponse(
                journal.getId(),
                journal.getTitle(),
                journal.getContent()
        );
    }

    // Find journal belonging to current user
    private Journal findOwnedJournal(String id, String userId) {

        return journalRepository.findByIdAndUserId(id, userId)
                .orElseThrow(() ->
                        new JournalNotFoundException("Journal not found"));
    }

    // Get all journals belonging to current user
    public List<JournalResponse> getAllJournals() {

        User user = getCurrentUser();

        return journalRepository.findByUserId(user.getId())
                .stream()
                .map(this::toResponse)
                .toList();
    }

    // Create journal
    public JournalResponse createJournal(JournalRequest request) {

        User user = getCurrentUser();

        Journal journal = new Journal();

        journal.setTitle(request.getTitle());
        journal.setContent(request.getContent());
        journal.setUserId(user.getId());

        Journal savedJournal = journalRepository.save(journal);

        return toResponse(savedJournal);
    }

    // Get journal by ID
    public JournalResponse getById(String id) {

        User user = getCurrentUser();

        Journal journal = findOwnedJournal(id, user.getId());

        return toResponse(journal);
    }

    // Update journal
    public JournalResponse updateJournal(
            String id,
            JournalRequest request) {

        User user = getCurrentUser();

        Journal existingJournal =
                findOwnedJournal(id, user.getId());

        existingJournal.setTitle(request.getTitle());
        existingJournal.setContent(request.getContent());

        Journal updatedJournal =
                journalRepository.save(existingJournal);

        return toResponse(updatedJournal);
    }

    // Delete journal
    public void deleteJournal(String id) {

        User user = getCurrentUser();

        Journal journal = findOwnedJournal(id, user.getId());

        journalRepository.delete(journal);
    }
}
