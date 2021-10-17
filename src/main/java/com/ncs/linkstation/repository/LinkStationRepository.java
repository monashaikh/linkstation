package com.ncs.linkstation.repository;

import com.ncs.linkstation.model.LinkStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
@Transactional
public interface LinkStationRepository extends JpaRepository<LinkStation, Integer> {
}
