package com.tide.test.service;

import org.springframework.http.ResponseEntity;

public interface ReactionService {

    ResponseEntity registerReaction(String announcementId);

    long fetchReactionCount(String announcementId);
}
