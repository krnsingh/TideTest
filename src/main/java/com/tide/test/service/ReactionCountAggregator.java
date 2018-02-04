package com.tide.test.service;

import com.tide.test.service.dto.ReactionCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class ReactionCountAggregator {

    final ReactionServiceFactory reactionServiceFactory;

    @Autowired
    public ReactionCountAggregator(ReactionServiceFactory reactionServiceFactory) {
        this.reactionServiceFactory = reactionServiceFactory;
    }

    public ResponseEntity<ReactionCounts> fetchReactionCount(final String announcementId) {
        long likeCount = reactionServiceFactory.getReactionService(Reaction.LIKE).fetchReactionCount(announcementId);
        long dislikeCount = reactionServiceFactory.getReactionService(Reaction.DISLIKE).fetchReactionCount(announcementId);
        return new ResponseEntity(new ReactionCounts(likeCount, dislikeCount), HttpStatus.OK);
    }


}
