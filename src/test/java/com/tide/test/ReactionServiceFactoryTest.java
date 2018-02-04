package com.tide.test;

import com.tide.test.service.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.hamcrest.CoreMatchers.instanceOf;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ReactionServiceFactoryTest {

    @Autowired
    private ReactionServiceFactory reactionServiceFactory;

    @Test
    public void getLikeServiceTest() {
        ReactionService reactionService = reactionServiceFactory.getReactionService(Reaction.LIKE);
        assertThat(reactionService, instanceOf(LikeService.class));
    }

    @Test
    public void getDislikeServiceTest() {
        ReactionService reactionService = reactionServiceFactory.getReactionService(Reaction.DISLIKE);
        assertThat(reactionService, instanceOf(DislikeService.class));
    }
}