package com.tide.controller;

import com.tide.dto.LikeDislikeCount;
import com.tide.service.LikeDislikeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/announcements")
public class LikeDislikeController {

    final LikeDislikeService likeDislikeService;

    @Autowired
    public LikeDislikeController(final LikeDislikeService likeDislikeService) {
        this.likeDislikeService = likeDislikeService;
    }

    @RequestMapping(value = "/{announcementId}/like", method = RequestMethod.POST)
    public ResponseEntity registerLike(@PathVariable String announcementId) {
        likeDislikeService.registerLike(announcementId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{announcementId}/dislike", method = RequestMethod.POST)
    public ResponseEntity registerDislike(@PathVariable String announcementId) {
        likeDislikeService.registerDislike(announcementId);
        return new ResponseEntity(HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{announcementId}/reactions", method = RequestMethod.GET, produces = "application/json")
    public ResponseEntity<LikeDislikeCount> likeCount(@PathVariable String announcementId) {
        return new ResponseEntity(likeDislikeService.fetchCount(announcementId), HttpStatus.OK);
    }

}
