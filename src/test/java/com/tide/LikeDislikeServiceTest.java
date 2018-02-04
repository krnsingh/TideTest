package com.tide;

import com.tide.service.LikeDislikeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LikeDislikeServiceTest {

    @Autowired
    private LikeDislikeService likeDislikeService;

    @Test
    public void likeNoIncTest() {
        final String announcementId = "NoInc";
        assertTrue("The like count should be 0", likeDislikeService.fetchCount(announcementId).getLikes() == 0);
    }

    @Test
    public void likeSingleIncTest() {
        final String announcementId = "single";
        incLikeForAnnouncement(announcementId, 1);
        assertTrue("The like count should be 1", likeDislikeService.fetchCount(announcementId).getLikes() == 1);
    }

    @Test
    public void likeMultiIncTest() {
        final String announcementId = "multi";
        incLikeForAnnouncement(announcementId, 2);
        assertTrue("The like count should be 2", likeDislikeService.fetchCount(announcementId).getLikes() == 2);
        incLikeForAnnouncement(announcementId, 3);
        assertTrue("The like count should be 5", likeDislikeService.fetchCount(announcementId).getLikes() == 5);
    }

    @Test
    public void dislikeNoIncTest() {
        final String announcementId = "NoInc";
        assertTrue("The like count should be 0", likeDislikeService.fetchCount(announcementId).getDislikes() == 0);
    }

    @Test
    public void dislikeSingleIncTest() {
        final String announcementId = "single";
        incDislikeForAnnouncement(announcementId, 1);
        assertTrue("The like count should be 1", likeDislikeService.fetchCount(announcementId).getDislikes() == 1);
    }

    @Test
    public void dislikeMultiIncTest() {
        final String announcementId = "multi";
        incDislikeForAnnouncement(announcementId, 2);
        assertTrue("The like count should be 2", likeDislikeService.fetchCount(announcementId).getDislikes() == 2);
        incDislikeForAnnouncement(announcementId, 3);
        assertTrue("The like count should be 5", likeDislikeService.fetchCount(announcementId).getDislikes() == 5);
    }

    private void incLikeForAnnouncement(final String announcementId, final int times) {
        for (int i = 0; i < times; i++) {
            likeDislikeService.registerLike(announcementId);
        }
    }

    private void incDislikeForAnnouncement(final String announcementId, final int times) {
        for (int i = 0; i < times; i++) {
            likeDislikeService.registerDislike(announcementId);
        }
    }
}
