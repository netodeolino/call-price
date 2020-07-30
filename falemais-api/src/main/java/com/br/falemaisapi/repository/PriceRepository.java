package com.br.falemaisapi.repository;

import com.br.falemaisapi.model.entity.Price;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PriceRepository extends JpaRepository<Price, Long> {
    Optional<Price> findByDddOriginAndDddDestiny(Integer dddOrigin, Integer dddDestiny);
}
