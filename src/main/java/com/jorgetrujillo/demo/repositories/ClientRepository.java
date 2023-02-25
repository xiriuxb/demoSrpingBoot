package com.jorgetrujillo.demo.repositories;

import com.jorgetrujillo.demo.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {

    @Query(value = "SELECT * FROM client WHERE client.id_number LIKE %:search% OR client.names LIKE %:search%",nativeQuery = true)
    List<Client> findByNamesOrIdNumber(@Param("search") String search);

    @Query(value = "SELECT COUNT(*) FROM client WHERE client.id_number = :idNumber", nativeQuery = true)
    Long countIfExists(@Param("idNumber") String id);
}
