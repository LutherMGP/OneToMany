package com.example.jpamanytoone_1.service;

import com.example.jpamanytoone_1.model.Kommune;
import com.example.jpamanytoone_1.model.Region;
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
public class ApiServiceGetRegionerImpl implements ApiServiceGetRegioner {

    private final RestTemplate restTemplate;

    public ApiServiceGetRegionerImpl(RestTemplate restTemplate) { this.restTemplate = restTemplate;}

    String regionUrl = "https://api.dataforsyningen.dk/regioner";

    @Autowired
    RegionRepository regionRepository;

    private void saveRegioner(List<Region> regioner) { regioner.forEach(reg -> regionRepository.save(reg));}

    // Benyttes kun til at indhente Region data fra Dataforsyningen til lokal database
    @Override
    public List<Region> getRegioner() {
        List<Region> lst = new ArrayList<>();

        ResponseEntity<List<Region>> regionResponse =
                restTemplate.exchange(regionUrl,
                        HttpMethod.GET, null, new
                                ParameterizedTypeReference<List<Region>>(){
                        });

        List<Region> regioner = regionResponse.getBody();
        saveRegioner(regioner);
        return regioner;
    }

    // GET, POST, PUT & DELETE til brug for lokal database
    @Override
    public List<Region> getAllRegioner() {
        return regionRepository.findAll();
    }

    public Region saveRegion(Region region) {
        return regionRepository.save(region);
    }

    public void updateRegionByKode(String kode, Region updatedRegion) {
        Region existingRegion = regionRepository.findById(kode).orElse(null);
        if (existingRegion != null) {
            // Opdater felterne på den eksisterende region
            existingRegion.setNavn(updatedRegion.getNavn());
            existingRegion.setHref(updatedRegion.getHref());
            // Gem opdateringen
            regionRepository.save(existingRegion);
        }
    }

    public void deleteRegionByKode(String kode) {
        regionRepository.deleteById(kode);
    }

    // -------------------------------------------------------

    // Opgave 2: 'For at bruge vores OneToMany mapping i klassen Region
    // Så kod et endpoint i RegionRestController, der returnerer en liste af kommunenavne i denne region.
    // Skriv en metode i klassen Region der returnerer en liste af navne og brug denne.'

    public List<String> getKommunerInRegion(String regionKode) {
        Region region = regionRepository.findByKode(regionKode);
        List<Kommune> kommuner = new ArrayList<>(region.getKommuner());
        List<String> kommuneNavne = new ArrayList<>();
        for (Kommune kommune : kommuner) {
            kommuneNavne.add(kommune.getNavn());
        }
        return kommuneNavne;
    }

}
