package com.maliha.communitymanagement.controller;

import com.maliha.communitymanagement.model.CommentDTO;
import com.maliha.communitymanagement.model.CommunityDTO;
import com.maliha.communitymanagement.model.PostDTO;
import com.maliha.communitymanagement.service.CommunityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/community")
public class CommunityController {
    @Autowired
    private CommunityService communityService;

    @PostMapping("/create")
    public ResponseEntity<?> createCommunity(@RequestBody CommunityDTO communityDTO) throws Exception{
        return new ResponseEntity<>(communityService.createCommunity(communityDTO), HttpStatus.CREATED);
    }
    @GetMapping("/get/all")
    public ResponseEntity<?> getAllCommunity() throws Exception{
        return new ResponseEntity<>(communityService.getAllCommunityNames(), HttpStatus.ACCEPTED);
    }
    @GetMapping("/{communityId}/get")
    public ResponseEntity<?> getCommunityById(@PathVariable Long communityId) throws Exception{
        return new ResponseEntity<>(communityService.getCommunityById(communityId), HttpStatus.CREATED);
    }
    @PutMapping("/{communityId}/join")
    public ResponseEntity<?> joinCommunityById(@PathVariable Long communityId) throws Exception{
        return new ResponseEntity<>(communityService.joinCommunity(communityId), HttpStatus.ACCEPTED);
    }
    @PostMapping("/{communityId}/post/create")
    public ResponseEntity<?> createPost(@PathVariable Long communityId,@RequestBody PostDTO postDTO) throws Exception{
        return new ResponseEntity<>(communityService.createPost(communityId,postDTO), HttpStatus.CREATED);
    }
    @GetMapping("/{communityId}/post/all")
    public ResponseEntity<?> getAllPostByCommunity(@PathVariable Long communityId) throws Exception{
        return new ResponseEntity<>(communityService.getAllPost(communityId), HttpStatus.ACCEPTED);
    }
    @PostMapping("/{postId}/comment/create")
    public ResponseEntity<?> createComment(@PathVariable Long postId,@RequestBody CommentDTO commentDTO) throws Exception{
        return new ResponseEntity<>(communityService.createComment(postId,commentDTO), HttpStatus.CREATED);
    }
}
