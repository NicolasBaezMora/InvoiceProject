package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, Long> {
}
