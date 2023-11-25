package com.maliha.communitymanagement.repository;

import com.maliha.communitymanagement.entity.CommentEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<CommentEntity,Long> {
}
