package com.example.jpamanytoone_1.service;

import com.example.jpamanytoone_1.model.Kommune;
import com.example.jpamanytoone_1.model.Region;
import com.example.jpamanytoone_1.repository.KommuneRepository;
import com.example.jpamanytoone_1.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

@Service
public class ApiServiceGetKommunerImpl implements ApiServiceGetKommuner {

    private final RestTemplate restTemplate;

    public ApiServiceGetKommunerImpl(RestTemplate restTemplate) { this.restTemplate = restTemplate;}

    String kommuneUrl = "https://api.dataforsyningen.dk/kommuner";

    @Autowired
    KommuneRepository kommuneRepository;

    private void saveKommuner(List<Kommune> kommuner) { kommuner.forEach(reg -> kommuneRepository.save(reg));}

    // Benyttes kun til at indhente Region data fra Dataforsyningen til lokal database
    @Override
    public List<Kommune> getKommuner() {
        List<Kommune> lst = new ArrayList<>();

        ResponseEntity<List<Kommune>> kommuneResponse =
                restTemplate.exchange(kommuneUrl,
                        HttpMethod.GET, null, new
                                ParameterizedTypeReference<List<Kommune>>(){
                                });

        List<Kommune> kommuner = kommuneResponse.getBody();
        saveKommuner(kommuner);
        return kommuner;
    }

    // GET, POST, PUT & DELETE til brug for lokal database
    @Override
    public List<Kommune> getAllKommuner() {
        return kommuneRepository.findAll();
    }

    public Kommune saveKommune(Kommune kommune) {
        return kommuneRepository.save(kommune);
    }

    public void updateKommuneByKode(String kode, Kommune updatedKommune) {
        Kommune existingKommune = kommuneRepository.findById(kode).orElse(null);
        if (existingKommune != null) {
            // Opdater felterne på den eksisterende kommune
            existingKommune.setNavn(updatedKommune.getNavn());
            existingKommune.setHref(updatedKommune.getHref());
            // Gem opdateringen
            kommuneRepository.save(existingKommune);
        }
    }

    public void deleteKommuneByKode(String kode) {
        kommuneRepository.deleteById(kode);
    }

}
