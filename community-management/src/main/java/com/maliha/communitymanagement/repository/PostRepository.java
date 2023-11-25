package com.maliha.communitymanagement.repository;

import com.maliha.communitymanagement.entity.PostEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<PostEntity,Long> {
}
