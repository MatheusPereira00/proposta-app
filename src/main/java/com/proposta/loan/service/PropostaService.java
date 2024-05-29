package com.proposta.loan.service;

import com.proposta.loan.dto.PropostaRequestDto;
import com.proposta.loan.dto.PropostaResponseDto;
import com.proposta.loan.entity.Proposta;
import com.proposta.loan.mapper.PropostaMapper;
import com.proposta.loan.repository.PropostaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PropostaService {

    private PropostaMapper propostaMapper;
    private PropostaRepository propostaRepository;

    public PropostaService(PropostaMapper propostaMapper, PropostaRepository propostaRepository) {
        this.propostaMapper = propostaMapper;
        this.propostaRepository = propostaRepository;
    }

    public PropostaResponseDto criar(PropostaRequestDto propostaRequestDto){
        Proposta proposta = propostaMapper.convertDtoToProposta(propostaRequestDto);
        propostaRepository.save(proposta);
        return propostaMapper.convertPropostaToDto(proposta);
    }

    public List<PropostaResponseDto> obterProposta(){
        return propostaMapper.convertListEntityToListDto(propostaRepository.findAll());
    }
}
