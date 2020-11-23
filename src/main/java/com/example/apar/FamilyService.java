package com.example.apar;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class FamilyService {
    private List<Family> familyList = new ArrayList<>(Arrays.asList(

            new Family("1", "Vamsi", "Father"),
            new Family("2", "Aparna", "Mother"),
            new Family("3", "Maha", "Daughter"),
            new Family("4", "Karthi", "Son")


    ));

    public List<Family> getAllFamilyList() {
        return familyList;
    }

    public Family getFamily(String id) {
        return familyList.stream().filter(family -> family.getId().equalsIgnoreCase(id)).findFirst().get();
    }

    public Family addFamily(Family family) {
        familyList.add(family);
        return family;
    }

    public void updateFamily(Family family, String id) {
        int counter =0;
        for(Family family1 :familyList) {
            if(family1.getId().equals(id)){
                familyList.set(counter, family);
            }
            counter++;
        }
    }

    public void deleteFamily(String id) {
        familyList.removeIf(family -> family.getId().equals(id));
    }
}
