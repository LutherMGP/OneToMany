package com.example.jpamanytoone_1.repository;

import com.example.jpamanytoone_1.model.Region;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegionRepository extends JpaRepository<Region, String> {

    // Opgave 2: 'For at bruge vores OneToMany mapping i klassen Region
    // Så kod et endpoint i RegionRestController, der returnerer en liste af kommunenavne i denne region.
    // Skriv en metode i klassen Region der returnerer en liste af navne og brug denne.'
    Region findByKode(String kode);

}
