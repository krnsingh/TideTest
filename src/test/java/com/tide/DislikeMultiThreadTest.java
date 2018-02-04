package com.tide;

import com.google.testing.threadtester.*;
import com.tide.service.LikeDislikeService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DislikeMultiThreadTest {

    final String announcementId = "MultiThread";

    private LikeDislikeService likeDislikeService;

    @ThreadedBefore
    public void before() {
        likeDislikeService = new LikeDislikeService();
    }

    @ThreadedMain
    public void mainThread() {
        likeDislikeService.registerDislike(announcementId);
    }

    @ThreadedSecondary
    public void secondThread() {
        likeDislikeService.registerDislike(announcementId);
    }

    @ThreadedAfter
    public void after() {
        assertTrue("The like count should be 2 but " + likeDislikeService.fetchCount(announcementId).getDislikes(),
                likeDislikeService.fetchCount(announcementId).getDislikes() == 2);
    }

    @Test
    public void runMultiThreadTest() {
        AnnotatedTestRunner annotatedTestRunner = new AnnotatedTestRunner();
        annotatedTestRunner.runTests(this.getClass(), LikeDislikeService.class);
    }
}