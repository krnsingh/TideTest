package com.tide.test;

import com.google.testing.threadtester.*;
import com.tide.test.service.DislikeService;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class DislikeServiceSingleMultiThreadTest {

    final String announcementId = "MultiThread";
    private DislikeService dislikeService;

    @ThreadedBefore
    public void before() {
        dislikeService = new DislikeService();
    }

    @ThreadedMain
    public void mainThread() {
        dislikeService.registerReaction(announcementId);
    }

    @ThreadedSecondary
    public void secondThread() {
        dislikeService.registerReaction(announcementId);
    }

    @ThreadedAfter
    public void after() {
        System.out.println(dislikeService.fetchReactionCount(announcementId));
        assertTrue("The like count should be 2", dislikeService.fetchReactionCount(announcementId) == 2);
    }

    @Test
    public void runMultiThreadTest() {
        AnnotatedTestRunner annotatedTestRunner = new AnnotatedTestRunner();
        annotatedTestRunner.runTests(this.getClass(), DislikeService.class);
    }
}