package com.tide.test;

import com.google.testing.threadtester.*;
import com.tide.test.service.LikeService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class LikeServiceSingleMultiThreadTest {

    final String announcementId = "MultiThread";
    private LikeService likeService;

    @ThreadedBefore
    public void before() {
        likeService = new LikeService();
    }

    @ThreadedMain
    public void mainThread() {
        likeService.registerReaction(announcementId);
    }

    @ThreadedSecondary
    public void secondThread() {
        likeService.registerReaction(announcementId);
    }

    @ThreadedAfter
    public void after() {
        System.out.println(likeService.fetchReactionCount(announcementId));
        assertTrue("The like count should be 2", likeService.fetchReactionCount(announcementId) == 2);
    }

    @Test
    public void runMultiThreadTest() {
        AnnotatedTestRunner annotatedTestRunner = new AnnotatedTestRunner();
        annotatedTestRunner.runTests(this.getClass(), LikeService.class);
    }
}