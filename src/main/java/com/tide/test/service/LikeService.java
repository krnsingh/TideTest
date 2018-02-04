package com.tide.test.service;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Service
public class LikeService implements ReactionService {

    Map<String, AtomicInteger> likeCountByAnnouncement = new ConcurrentHashMap<>();


    @Override
    public ResponseEntity registerReaction(final String announcementId) {
        likeCountByAnnouncement.compute(announcementId,
                (key, likeCount) -> likeCount == null ? new AtomicInteger(1) :
                        new AtomicInteger(likeCount.incrementAndGet()));
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @Override
    public long fetchReactionCount(String announcementId) {
        if (likeCountByAnnouncement.get(announcementId) != null)
            return likeCountByAnnouncement.get(announcementId).longValue();
        else
            return 0L;
    }
}

