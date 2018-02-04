package com.tide.test.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ReactionServiceFactory {

    final private ReactionService likeService;
    final private ReactionService dislikeService;

    @Autowired
    public ReactionServiceFactory(ReactionService likeService,
                                  ReactionService dislikeService) {
        this.likeService = likeService;
        this.dislikeService = dislikeService;
    }

    public ReactionService getReactionService(final Reaction reaction) {
        switch (reaction) {
            case LIKE: return likeService;
            default: return dislikeService;
        }
    }

}
