package com.maliha.communitymanagement.service;

import com.maliha.communitymanagement.entity.CommunityEntity;
import com.maliha.communitymanagement.entity.PostEntity;
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
import java.util.Optional;

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
    public CommunityEntity getCommunityById(Long id){
        return communityRepository.findById(id).orElseThrow(() -> new NullPointerException());
    }
    public Boolean joinCommunity(Long communityId){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String userEmail = authentication.getName();
        Integer patientId=patientFeignClient.getPatientByEmail(userEmail).getId();
        if (communityRepository.existsById(communityId)){
            if(communityRepository.findById(communityId).get().getMemberId().contains(patientId)){
                return false;
            }
            else {
                communityRepository.findById(communityId).get().getMemberId().add(patientId);
                return true;
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
            postEntity.setName(patientFeignDTO.getName());
            communityEntity.getPostEntities().add(postEntity);
            communityRepository.save(communityEntity);
            return postRepository.save(postEntity);
        }
        throw  new Exception("Not a member of the community");
    }

}
