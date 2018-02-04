package com.tide.test.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class DislikeService implements ReactionService {

    Map<String, AtomicInteger> dislikeCountByAnnouncement = new ConcurrentHashMap<>();

    @Override
    public ResponseEntity registerReaction(final String announcementId) {
        dislikeCountByAnnouncement.compute(announcementId,
                (key, dislikeCount) -> dislikeCount == null ? new AtomicInteger(1) :
                        new AtomicInteger(dislikeCount.incrementAndGet()));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public long fetchReactionCount(String announcementId) {
        if (dislikeCountByAnnouncement.get(announcementId) != null)
            return dislikeCountByAnnouncement.get(announcementId).longValue();
        else
            return 0L;
    }
}
