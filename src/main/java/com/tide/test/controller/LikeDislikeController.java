package com.tide.test.controller;

import com.tide.test.service.Reaction;
import com.tide.test.service.ReactionCountAggregator;
import com.tide.test.service.ReactionServiceFactory;
import com.tide.test.service.dto.ReactionCounts;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
public class LikeDislikeController {

    final ReactionServiceFactory reactionServiceFactory;
    final ReactionCountAggregator reactionCountAggregator;

    @Autowired
    public LikeDislikeController(final ReactionServiceFactory reactionServiceFactory,
                                 final ReactionCountAggregator reactionCountAggregator) {
        this.reactionServiceFactory = reactionServiceFactory;
        this.reactionCountAggregator = reactionCountAggregator;
    }

    @RequestMapping(value = "/{announcementId}/like", method = RequestMethod.POST)
    public ResponseEntity registerLike(@PathVariable String announcementId) {
        return reactionServiceFactory.getReactionService(Reaction.LIKE).registerReaction(announcementId);
    }

    @RequestMapping(value = "/{announcementId}/dislike", method = RequestMethod.POST)
    public ResponseEntity registerDislike(@PathVariable String announcementId) {
        return reactionServiceFactory.getReactionService(Reaction.DISLIKE).registerReaction(announcementId);
    }

    @RequestMapping(value = "/{announcementId}/reactions", method = RequestMethod.GET)
    public ResponseEntity<ReactionCounts> likeCount(@PathVariable String announcementId) {
        return reactionCountAggregator.fetchReactionCount(announcementId);
    }

}
