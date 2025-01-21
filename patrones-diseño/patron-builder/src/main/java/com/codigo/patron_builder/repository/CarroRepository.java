package com.codigo.patron_builder.repository;

import com.codigo.patron_builder.model.Carro;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CarroRepository extends JpaRepository<Carro, Long> {
}
