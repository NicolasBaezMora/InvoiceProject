package com.proyectoTC.Taller_17_TC.repositories;

import com.proyectoTC.Taller_17_TC.models.Client;
import com.proyectoTC.Taller_17_TC.models.Wallet;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WalletRepository extends JpaRepository<Wallet, Long> {

    Optional<Wallet> findByClient(Client client);

    @Modifying
    @Query("UPDATE Wallet w SET w.balance = :balance WHERE w.id = :id")
    void updateWalletBalance(
            @Param(value = "id") Long id,
            @Param(value = "balance") Double balance
    );

}
