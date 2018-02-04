package com.tide.test.service.dto;

public class ReactionCounts {

    private long likes;
    private long dislikes;

    public ReactionCounts(long likes, long dislikes) {
        this.likes = likes;
        this.dislikes = dislikes;
    }

    public long getLikes() {
        return likes;
    }

    public long getDislikes() {
        return dislikes;
    }
}
