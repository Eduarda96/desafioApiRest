package com.eduarda.bortoletti.desafioApiRest.Repository;

import com.eduarda.bortoletti.desafioApiRest.model.Transferencia;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransferenciaRepository extends CrudRepository<Transferencia, Long> {
}
