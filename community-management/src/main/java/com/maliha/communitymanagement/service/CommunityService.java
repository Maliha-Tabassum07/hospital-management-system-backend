package com.maliha.communitymanagement.service;

import com.maliha.communitymanagement.entity.CommentEntity;
import com.maliha.communitymanagement.entity.CommunityEntity;
import com.maliha.communitymanagement.entity.PostEntity;
import com.maliha.communitymanagement.model.CommentDTO;
import com.maliha.communitymanagement.model.CommunityDTO;
import com.maliha.communitymanagement.model.PatientFeignDTO;
import com.maliha.communitymanagement.model.PostDTO;
import com.maliha.communitymanagement.networkmanager.PatientFeignClient;
import com.maliha.communitymanagement.repository.CommentRepository;
import com.maliha.communitymanagement.repository.CommunityRepository;
import com.maliha.communitymanagement.repository.PostRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CommunityService {
    @Autowired
    private CommunityRepository communityRepository;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private CommentRepository commentRepository;
    @Autowired
    private PatientFeignClient patientFeignClient;

    public CommunityEntity createCommunity(CommunityDTO communityDTO){
        CommunityEntity communityEntity=new CommunityEntity();
        communityEntity.setName(communityDTO.getName());
        communityEntity.setRule(communityDTO.getRule());
        return communityRepository.save(communityEntity);
    }
    public List<CommunityDTO> getAllCommunityNames(){
        List<CommunityDTO> communityDTOList=new ArrayList<>();
        for (CommunityEntity communityEntity:communityRepository.findAll()){
            communityDTOList.add(new ModelMapper().map(communityEntity,CommunityDTO.class));
        }
        return communityDTOList;
    }
    public CommunityEntity getCommunityById(Long id)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer patientId=patientFeignClient.getPatientByEmail(userEmail).getId();
        if(communityRepository.findById(id).get().getMemberId().isEmpty()){
            throw  new Exception("Not a member of the community");

        }
        else if (communityRepository.findById(id).get().getMemberId().contains(patientId)){
            return communityRepository.findById(id).orElseThrow(() -> new NullPointerException());
        }
        throw new Exception("Not a member of the community");
    }
    public Boolean joinCommunity(Long communityId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer patientId=patientFeignClient.getPatientByEmail(userEmail).getId();
        List<Integer> patientIdList=new ArrayList<>();
        patientIdList.add(patientId);
        if (communityRepository.existsById(communityId)){
            CommunityEntity communityEntity=communityRepository.findById(communityId).get();
            if(communityEntity.getMemberId()==null){
                communityEntity.setMemberId(patientIdList);
                communityRepository.save(communityEntity);
                return true;
            }
            else if(communityEntity.getMemberId().contains(patientId)) {
                return false;
            }
            else if (!communityEntity.getMemberId().isEmpty()){
                communityRepository.findById(communityId).get().getMemberId().add(patientId);
                communityRepository.save(communityEntity);
            }
        }
        return false;
    }
    public PostEntity createPost(Long communityId,PostDTO postDTO)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        PatientFeignDTO patientFeignDTO=patientFeignClient.getPatientByEmail(userEmail);
        CommunityEntity communityEntity=communityRepository.findById(communityId).orElseThrow(() -> new NullPointerException());
        if (communityEntity.getMemberId().contains(patientFeignDTO.getId())) {
            PostEntity postEntity = new PostEntity();
            postEntity.setContent(postDTO.getContent());
            postEntity.setMemberId(patientFeignDTO.getId());
            postEntity.setMemberName(patientFeignDTO.getName());
            communityEntity.getPostEntities().add(postEntity);
            PostEntity savedPostEntity = postRepository.save(postEntity);
            communityRepository.save(communityEntity);
            return savedPostEntity;
        }
        throw  new Exception("Not a member of the community");
    }
    public List<PostEntity> getAllPost(Long communityId) throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        PatientFeignDTO patientFeignDTO=patientFeignClient.getPatientByEmail(userEmail);
        CommunityEntity communityEntity=communityRepository.findById(communityId).orElseThrow(() -> new NullPointerException());
        if (communityEntity.getMemberId().contains(patientFeignDTO.getId())) {
            return communityEntity.getPostEntities();
        }
        throw  new Exception("Not a member of the community");
    }
    public PostEntity createComment(Long postId, CommentDTO commentDTO)throws Exception{
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        PatientFeignDTO patientFeignDTO=patientFeignClient.getPatientByEmail(userEmail);
        PostEntity postEntity=postRepository.findById(postId).orElseThrow(() -> new NullPointerException());
        CommentEntity commentEntity=new CommentEntity();
        commentEntity.setContent(commentDTO.getContent());
        commentEntity.setMemberId(patientFeignDTO.getId());
        commentEntity.setMemberName(patientFeignDTO.getName());
        List<CommentEntity> commentEntities=new ArrayList<>();
        CommentEntity savedComment=commentRepository.save(commentEntity);
        commentEntities.add(savedComment);
        if (postEntity.getCommentEntityList().isEmpty()){
            postEntity.setCommentEntityList(commentEntities);
            postRepository.save(postEntity);
        }
        else {
            postEntity.getCommentEntityList().add(savedComment);
            postRepository.save(postEntity);
        }
        return postEntity;
    }
    //remove member
    //delete post
    //update community


}
