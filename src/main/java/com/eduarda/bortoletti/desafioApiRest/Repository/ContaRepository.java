package com.eduarda.bortoletti.desafioApiRest.Repository;

import com.eduarda.bortoletti.desafioApiRest.model.ContaCorrente;
import org.springframework.data.repository.CrudRepository;

public interface ContaRepository extends CrudRepository<ContaCorrente, Long> {

}
