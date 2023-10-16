package br.com.webank.webank.service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.webank.webank.dto.contaBancaria.ContaBancariaRequestDTO;
import br.com.webank.webank.dto.contaBancaria.ContaBancariaResponseDTO;
import br.com.webank.webank.model.ContaBancaria;
import br.com.webank.webank.parser.ContaBancariaParser;
import br.com.webank.webank.repository.ContaBancariaRepository;

@Service
public class ContaBancariaService {
    
    @Autowired
    private ContaBancariaRepository contaBancariaRepository;

    

    @Autowired
    private ModelMapper mapper;

    public List<ContaBancariaResponseDTO> obterTodos(){

        List<ContaBancaria> contasBancarias = contaBancariaRepository.findAll();

        return contasBancarias.stream().map(contaBancaria -> mapper
            .map(contaBancaria, ContaBancariaResponseDTO.class))
            .collect(Collectors.toList());
        
    }

    public ContaBancariaResponseDTO obterPorId(long id){
        Optional<ContaBancaria> optContaBancaria = contaBancariaRepository.findById(id);

        if(optContaBancaria.isEmpty()){
            throw new RuntimeException("Nenhum registro encontrado para o ID: " + id);
        }

        return mapper.map(optContaBancaria.get(), ContaBancariaResponseDTO.class);
    }

    // O save serve tanto para adicionar quanto para atualizar.
    // se tiver id, ele atualiza, s enão tiver id ele adiciona.
    public ContaBancariaResponseDTO adicionar(ContaBancaria contaBancaria){
        
        ContaBancaria contaBancariaModel = mapper.map(contaBancaria, ContaBancaria.class);
        
        contaBancariaModel = contaBancariaRepository.save(contaBancariaModel);

        return mapper.map(contaBancariaModel, ContaBancariaResponseDTO.class);
    }

    public ContaBancariaResponseDTO atualizar(long id, ContaBancariaRequestDTO contaBancaria){

        // Se não lançar exception é porque o cara existe no banco.
        obterPorId(id);

        ContaBancaria contaBancariaModel = ContaBancariaParser.converterContaBancariaRequestEmContaBancariaModel(contaBancaria);
        contaBancariaModel.setId(id);

        contaBancariaModel = contaBancariaRepository.save(contaBancariaModel);

        return ContaBancariaParser.converterContaBancariaModelEmContaBancariaResponseDTO(contaBancariaModel);
    }

    public void deletar(Long id){
        obterPorId(id);

        contaBancariaRepository.deleteById(id);
    }

  

}
