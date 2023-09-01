package com.example.jpamanytoone_1.controller;

import com.example.jpamanytoone_1.model.Kommune;
import com.example.jpamanytoone_1.model.Region;
import com.example.jpamanytoone_1.service.ApiServiceGetKommunerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class KommuneRestController {

    @Autowired
    ApiServiceGetKommunerImpl apiServiceGetKommuner;

    // Benyttes kun til at indhente Kommune data fra Dataforsyningen til lokal database
    @GetMapping("/getkommunerdataforsyningen")
    public List<Kommune> getKommuner() {
        List<Kommune> lstKommuner = apiServiceGetKommuner.getKommuner();
        return lstKommuner;
    }

    // MAPPING relateret til lokal MySQL database
    @GetMapping("/getkommuner")
    public List<Kommune> getAllKommuner() {
        return apiServiceGetKommuner.getAllKommuner();
    }

    @PostMapping("/addkommune")
    public Kommune addKommune(@RequestBody Kommune newKommune) {
        return apiServiceGetKommuner.saveKommune(newKommune);
    }

    @PutMapping("/updatekommune/{kode}")
    public void updateKommune(@PathVariable String kode, @RequestBody Kommune updatedKommune) {
        apiServiceGetKommuner.updateKommuneByKode(kode, updatedKommune);
    }

    @DeleteMapping("/deletekommune/{kode}")
    public void deleteKommune(@PathVariable String kode) {
        apiServiceGetKommuner.deleteKommuneByKode(kode);
    }

}
