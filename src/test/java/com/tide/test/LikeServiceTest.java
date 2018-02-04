package com.tide.test;

import com.tide.test.service.ReactionService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeServiceTest {

    @Autowired
    private ReactionService likeService;

    @Test
    public void likeNoIncTest() {
        final String announcementId = "NoInc";
        assertTrue("The like count should be 0", likeService.fetchReactionCount(announcementId) == 0);
    }

    @Test
    public void likeSingleIncTest() {
        final String announcementId = "single";
        incLikeForAnnouncement(announcementId, 1);
        assertTrue("The like count should be 1", likeService.fetchReactionCount(announcementId) == 1);
    }

    @Test
    public void likeMultiIncTest() {
        final String announcementId = "multi";
        incLikeForAnnouncement(announcementId, 2);
        assertTrue("The like count should be 2", likeService.fetchReactionCount(announcementId) == 2);
        incLikeForAnnouncement(announcementId, 3);
        assertTrue("The like count should be 5", likeService.fetchReactionCount(announcementId) == 5);
    }

    private void incLikeForAnnouncement(final String announcementId, final int times) {
        for (int i = 0; i < times; i++) {
            likeService.registerReaction(announcementId);
        }
    }
}