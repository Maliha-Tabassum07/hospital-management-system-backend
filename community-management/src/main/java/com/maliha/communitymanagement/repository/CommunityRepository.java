package com.maliha.communitymanagement.repository;

import com.maliha.communitymanagement.entity.CommunityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommunityRepository extends JpaRepository<CommunityEntity,Long> {
}
