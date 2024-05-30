package com.proposta.loan.repository;

import com.proposta.loan.entity.Proposta;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PropostaRepository extends CrudRepository<Proposta, Long> {
    List<Proposta> findByIntegradaIsFalse();
}
