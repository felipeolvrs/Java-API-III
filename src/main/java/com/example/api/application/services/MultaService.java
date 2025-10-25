package com.example.api.application.services;

import com.example.api.application.base.BaseService;
import com.example.api.domain.models.Multa;
import com.example.api.domain.services.MultaServiceInterface;
import com.example.api.infrastructure.repository.springdata.MultaRepositorySD;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MultaService extends BaseService<Multa, Long> implements MultaServiceInterface {

    private final MultaRepositorySD multasRepository;

    public MultaService (MultaRepositorySD multasRepository) {
        super(multasRepository);
        this.multasRepository = multasRepository;
    }

    @Override
    public List<Multa> consultarMultasPorUsuario(Long usuarioId) {
        return List.of();
    }

    @Override
    public Multa registrarMulta(Long emprestimoId, long diasAtraso, double valorDiario) {
        return null;
    }

    //TODO: Implementar os serviços da interface
}
