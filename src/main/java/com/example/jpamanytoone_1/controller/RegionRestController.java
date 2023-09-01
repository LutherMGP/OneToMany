package com.example.jpamanytoone_1.controller;

import com.example.jpamanytoone_1.model.Region;
import com.example.jpamanytoone_1.repository.RegionRepository;
import com.example.jpamanytoone_1.service.ApiServiceGetRegionerImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class RegionRestController {

    @Autowired
    ApiServiceGetRegionerImpl apiServiceGetRegioner;

    // Benyttes kun til at indhente Region data fra Dataforsyningen til lokal database
    @GetMapping("/getregionerdataforsyningen")
    public List<Region> getRegioner() {
            List<Region> lstRegioner = apiServiceGetRegioner.getRegioner();
            return lstRegioner;
        }

    // MAPPING relateret til lokal MySQL database
    @GetMapping("/getregioner")
    public List<Region> getAllRegions() {
        return apiServiceGetRegioner.getAllRegioner();
    }

    @PostMapping("/addregion")
    public Region addRegion(@RequestBody Region newRegion) {
        return apiServiceGetRegioner.saveRegion(newRegion);
    }

    @PutMapping("/updateregion/{kode}")
    public void updateRegion(@PathVariable String kode, @RequestBody Region updatedRegion) {
        apiServiceGetRegioner.updateRegionByKode(kode, updatedRegion);
    }

    @DeleteMapping("/deleteregion/{kode}")
    public void deleteRegion(@PathVariable String kode) {
        apiServiceGetRegioner.deleteRegionByKode(kode);
    }

    // -------------------------------------------------------

    // Opgave 2: 'For at bruge vores OneToMany mapping i klassen Region
    // Så kod et endpoint i RegionRestController, der returnerer en liste af kommunenavne i denne region.
    // Skriv en metode i klassen Region der returnerer en liste af navne og brug denne.'

    @GetMapping("/getregion/{regionKode}/kommuner")
    public List<String> getKommunerInRegion(@PathVariable String regionKode) {
        return apiServiceGetRegioner.getKommunerInRegion(regionKode);
    }


}
