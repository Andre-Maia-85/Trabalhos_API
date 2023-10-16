package br.com.webank.webank.parser;

import br.com.webank.webank.dto.contaBancaria.ContaBancariaRequestDTO;
import br.com.webank.webank.dto.contaBancaria.ContaBancariaResponseDTO;
import br.com.webank.webank.model.ContaBancaria;


public class ContaBancariaParser {
    
    public static ContaBancaria converterContaBancariaRequestEmContaBancariaModel(ContaBancariaRequestDTO ContaBancariaRequest){
        ContaBancaria contaBancaria = new ContaBancaria ();
        contaBancaria.setId(01);
        contaBancaria.setNumero(ContaBancariaRequest.getNumero());
        contaBancaria.setSaldo(contaBancaria.getSaldo());
        contaBancaria.setTitular(contaBancaria.getTitular());

        return contaBancaria;
    }

    public static ContaBancariaResponseDTO converterContaBancariaModelEmContaBancariaResponseDTO(ContaBancaria contaBancariaModel){
        ContaBancariaResponseDTO contaBancaria = new  ContaBancariaResponseDTO();
        contaBancaria.setId(contaBancariaModel.getId());

        return contaBancaria;
    }
}
