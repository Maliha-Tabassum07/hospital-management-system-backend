package com.research.repo;

import com.research.dto.ResearcherDto;
import com.research.entity.ResearcherEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ResearcherRepository extends JpaRepository<ResearcherEntity, Long> {
    Optional<ResearcherDto> findByEmail(String email);

    List<ResearcherEntity> findByIsTaken(boolean isTaken);
}
