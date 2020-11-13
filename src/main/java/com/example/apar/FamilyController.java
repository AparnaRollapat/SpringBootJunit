package com.example.apar;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class FamilyController {

    @Autowired
    private FamilyService familyService;

    @RequestMapping("/families")
    public List<Family> family_list() {
        return familyService.getAllFamilyList();

    }

    @RequestMapping("/families/{id}")
    public Family getFamily(@PathVariable("id") String id) {
        return familyService.getFamily(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/families")
    public Family addFamily(@RequestBody Family family) {
        return familyService.addFamily(family);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/families/{id}")
    public void updateFamily(@RequestBody Family family ,@PathVariable("id") String id) {
         familyService.updateFamily(family, id);
    }
    @RequestMapping(method = RequestMethod.DELETE, value = "/families/{id}")
    public void deleteFamily(@PathVariable("id") String id) {
         familyService.deleteFamily(id);
    }
}
