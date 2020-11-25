package org.roxfort.enaplo.repository.impl;

import org.roxfort.enaplo.model.House;
import org.roxfort.enaplo.repository.HouseRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class HouseRepositoryImpl implements HouseRepository {

    public List<House> getHouses() {
        return new ArrayList<>
                (Arrays.asList(
                        new House("Griffendél"),
                        new House("Mardekár"),
                        new House("Hugrabug"),
                        new House("Hollóhát")));
    }
}
