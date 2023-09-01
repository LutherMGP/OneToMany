package com.example.jpamanytoone_1.service;

import com.example.jpamanytoone_1.model.Region;

import java.util.List;

public interface ApiServiceGetRegioner {
    List<Region> getRegioner(); // Benyttes kun til at indhente Region data fra Dataforsyningen til lokal database
    List<Region> getAllRegioner();
}
