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
public class DislikeServiceTest {

    @Autowired
    private ReactionService dislikeService;

    @Test
    public void dislikeNoIncTest() {
        final String announcementId = "NoInc";
        assertTrue("The like count should be 0", dislikeService.fetchReactionCount(announcementId) == 0);
    }

    @Test
    public void dislikeSingleIncTest() {
        final String announcementId = "single";
        incDislikeForAnnouncement(announcementId, 1);
        assertTrue("The like count should be 1", dislikeService.fetchReactionCount(announcementId) == 1);
    }

    @Test
    public void dislikeMultiIncTest() {
        final String announcementId = "multi";
        incDislikeForAnnouncement(announcementId, 2);
        assertTrue("The like count should be 2", dislikeService.fetchReactionCount(announcementId) == 2);
        incDislikeForAnnouncement(announcementId, 3);
        assertTrue("The like count should be 5", dislikeService.fetchReactionCount(announcementId) == 5);
    }

    private void incDislikeForAnnouncement(final String announcementId, final int times) {
        for (int i = 0; i < times; i++) {
            dislikeService.registerReaction(announcementId);
        }
    }
}