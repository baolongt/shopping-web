package nashtech.longtran.shoppingweb.controllers;

import nashtech.longtran.shoppingweb.repository.ColorRepository;
import nashtech.longtran.shoppingweb.services.implement.BrandServiceImp;
import nashtech.longtran.shoppingweb.services.implement.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1/brand/")
public class ColorController {
    @Autowired
    ColorServiceImp colorServiceImp;

    @GetMapping("/add")
    public ResponseEntity<?> addCategory(
            @RequestParam String name,
            @RequestParam String hexColor
    ){
        return ResponseEntity.ok(colorServiceImp.addNewColor(name, hexColor));
    }

    @GetMapping("/edit")
    public ResponseEntity<?> editCategory(
            @RequestParam Integer id,
            @RequestParam String name,
            @RequestParam String hexColor
    ){
        return ResponseEntity.ok(colorServiceImp.editColor(id, name, hexColor));
    }
}
