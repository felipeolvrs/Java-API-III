package com.example.api.infrastructure.repository.springdata;

import com.example.api.domain.enums.StatusPagamento;
import com.example.api.domain.models.Multa;
import com.example.api.domain.models.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MultaRepositorySD extends JpaRepository<Multa, Long> {

    boolean existsByUsuarioAndStatusPagamento(Usuario usuario, StatusPagamento status);

    List<Multa> findByUsuarioAndStatusPagamento(Usuario usuario, StatusPagamento status);

    @Query("SELECT COALESCE(SUM(m.valor), 0) FROM Multa m " +
            "WHERE m.usuario.id = :usuarioId " +
            "AND m.statusPagamento = :status")
    Double calcularTotalDividas(@Param("usuarioId") Long usuarioId, @Param("status") StatusPagamento status);

    List<Multa> findByUsuario_Id(Long usuarioId);

    boolean existsByEmprestimo_Id(Long emprestimoId);
}