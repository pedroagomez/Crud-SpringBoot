package com.PrimerTP.PrimerTp.Repositorio;

import com.PrimerTP.PrimerTp.Model.Persona;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IRepository extends JpaRepository<Persona,Long> {

}
