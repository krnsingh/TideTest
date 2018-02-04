package com.tide.test;

import com.tide.test.service.DislikeService;
import com.tide.test.service.LikeService;
import com.tide.test.service.ReactionCountAggregator;
import com.tide.test.service.dto.ReactionCounts;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.notNullValue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactionCountAggregatorTest {

    @Autowired
    private LikeService likeService;

    @Autowired
    private DislikeService dislikeService;

    @Autowired
    private ReactionCountAggregator reactionCountAggregator;

    @Test
    public void reactionCountIncLikeTest() {
        final String announcementId = "testLike";
        likeService.registerReaction(announcementId);
        callAggregatorAndCompareCounts(announcementId, 1L, 0L);
    }

    private void callAggregatorAndCompareCounts(String announcementId, long likeCount, long dislikeCount) {
        ResponseEntity<ReactionCounts> reactionCountsResponseEntity = reactionCountAggregator.fetchReactionCount(announcementId);
        assertThat(reactionCountsResponseEntity, is(notNullValue()));
        assertThat(reactionCountsResponseEntity.getStatusCode(), is(HttpStatus.OK));
        ReactionCounts reactionCounts = reactionCountsResponseEntity.getBody();
        assertThat(reactionCounts.getLikes(), is(likeCount));
        assertThat(reactionCounts.getDislikes(), is(dislikeCount));
    }

    @Test
    public void reactionCountIncDisLikeTest() {
        final String announcementId = "testDislike";
        dislikeService.registerReaction(announcementId);
        callAggregatorAndCompareCounts(announcementId, 0L, 1L);
    }

    @Test
    public void reactionCountIncLikeDisLikeTest() {
        final String announcementId = "testLikeDislike";
        likeService.registerReaction(announcementId);
        dislikeService.registerReaction(announcementId);
        callAggregatorAndCompareCounts(announcementId, 1L, 1L);
    }

    @Test
    public void reactionCountMultiIncLikeDisLikeTest() {
        final String announcementId = "testMultiLikeDislike";
        likeService.registerReaction(announcementId);
        dislikeService.registerReaction(announcementId);
        callAggregatorAndCompareCounts(announcementId, 1L, 1L);
        likeService.registerReaction(announcementId);
        dislikeService.registerReaction(announcementId);
        callAggregatorAndCompareCounts(announcementId, 2L, 2L);
        likeService.registerReaction(announcementId);
        callAggregatorAndCompareCounts(announcementId, 3L, 2L);
    }

}
