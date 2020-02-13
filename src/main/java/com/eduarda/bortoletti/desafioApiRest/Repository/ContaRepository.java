package com.eduarda.bortoletti.desafioApiRest.Repository;

import com.eduarda.bortoletti.desafioApiRest.model.Conta;
import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<Conta, Long> {
}
