package com.servicesystem.api.application.services;

import java.util.Base64;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.servicesystem.api.domain.exceptions.ServiceUnavailableException;

@Service
public class ImageService {
    
    @Value("${freeimage-upload-key}")
	private String key;
	
	public String saveNuvem(String image){

		// Definir a URL do serviço de hospedagem de imagens.
		String url = "https://freeimage.host/api/1/upload?key="+key+"&format=json";

		// Configurar os cabeçalhos para uma requisição de upload de arquivo.
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.MULTIPART_FORM_DATA);

		//  Criar o corpo da requisição contendo a imagem codificada em Base64.
		MultiValueMap<String, Object> body = new LinkedMultiValueMap<>();
		body.add("source", base64(image));

		//  Criar a Entidade da Requisição
		HttpEntity<MultiValueMap<String, Object>> request = new HttpEntity<>(body, headers);

		//  Enviar a requisição POST para o serviço
		RestTemplate restTemplate = new RestTemplate();
		ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);
		
		// Verificar a resposta e extrair a URL da imagem hospedada a partir da resposta JSON
		String responseBody = response.getBody();

        if (responseBody == null) {
            throw new ServiceUnavailableException("O servidor de arquivos não retornou uma resposta. Tente novamente mais tarde!");
        }
		
		int start = responseBody.indexOf("https:");
        
	    if (start == -1) {
	        throw new ServiceUnavailableException("O servidor de arquivos não retornou uma resposta. Tente novamente mais tarde!"); // não encontrou uma URL na string
	    }
	    int end = responseBody.indexOf("\"", start);
	    if (end == -1) {
	        end = responseBody.length();
	    }
	    
	    return responseBody.substring(start, end);
		
	}
	
	public String base64(String image) {
		return image.substring(image.indexOf(",")+1, image.length());
	}

	public boolean isBase64(String image) {
		
        if (image == null || image.isEmpty()) {
            return false;
        }

        // Verifica se a string tem um comprimento que é múltiplo de 4 (padrão de base 64)
        if (image.length() % 4 != 0) {
            return false;
        }

        try {
            // Tenta decodificar a string
            Base64.getDecoder().decode(image);
            return true;
        } catch (IllegalArgumentException e) {
            // Se uma IllegalArgumentException for lançada, não é uma string Base64 válida
            return false;
        }
    }
}
