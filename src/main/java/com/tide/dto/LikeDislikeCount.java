package com.tide.dto;

public class LikeDislikeCount {

    private final long likes;
    private final long dislikes;

    public LikeDislikeCount(final long likes, final long dislikes) {
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
