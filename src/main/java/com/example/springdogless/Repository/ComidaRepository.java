package com.example.springdogless.Repository;

import com.example.springdogless.entity.Comida;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

public interface ComidaRepository extends JpaRepository<Comida, Integer>{


}

