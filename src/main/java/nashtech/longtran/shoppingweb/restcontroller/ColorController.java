package nashtech.longtran.shoppingweb.restcontroller;

import nashtech.longtran.shoppingweb.dto.ColorDTO;
import nashtech.longtran.shoppingweb.payload.request.ColorAddingRequest;
import nashtech.longtran.shoppingweb.payload.request.ColorEditRequest;
import nashtech.longtran.shoppingweb.services.implement.ColorServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping(value = "/api/v1/color/")
public class ColorController {
    @Autowired
    ColorServiceImp colorServiceImp;

    @GetMapping("/getAll")
    public ResponseEntity<?> getAll( @RequestParam Optional<Integer> page,
                                     @RequestParam Optional<Integer> offset){
        Pageable pageable = PageRequest.of(page.orElse(0), offset.orElse(10), Sort.by("name").ascending());
        return ResponseEntity.ok(colorServiceImp.getAll(pageable));
    }

    @PostMapping("/add")
    public ResponseEntity<?> addColor(
            @RequestBody @Valid ColorDTO request
    ){
        return ResponseEntity.ok(colorServiceImp.addNewColor(request));
    }

    @GetMapping("/edit")
    public ResponseEntity<?> editColor(
            @RequestBody @Valid ColorDTO request
            ){
        return ResponseEntity.ok(colorServiceImp.editColor(request));
    }
}
