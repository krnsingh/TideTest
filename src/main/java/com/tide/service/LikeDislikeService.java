package com.tide.service;


import com.tide.dto.LikeDislikeCount;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.LongAdder;

@Component
public class LikeDislikeService {

    private final Map<String, LongAdder> likeCountByAnnouncement = new ConcurrentHashMap<>();
    private final Map<String, LongAdder> dislikeCountByAnnouncement = new ConcurrentHashMap<>();

    public void registerLike(final String announcementId) {
        likeCountByAnnouncement.computeIfAbsent(announcementId, key -> new LongAdder()).increment();
    }

    public void registerDislike(final String announcementId) {
        dislikeCountByAnnouncement.computeIfAbsent(announcementId, key -> new LongAdder()).increment();
    }

    public LikeDislikeCount fetchCount(final String announcementId) {
        long likeCount = likeCountByAnnouncement.get(announcementId) != null ?
                likeCountByAnnouncement.get(announcementId).longValue() : 0L;
        long dislikeCount = dislikeCountByAnnouncement.get(announcementId) != null ?
                dislikeCountByAnnouncement.get(announcementId).longValue() : 0L;
        return new LikeDislikeCount(likeCount, dislikeCount);
    }
}
