package am.aca.generactive.generactiveservlets.gen.controller;

import am.aca.generactive.generactiveservlets.gen.service.ItemService;
import am.aca.generactive.generactiveservlets.gen.service.dto.itemdto.ItemDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/item")
public class ItemController {

    private final ItemService itemService;

    public ItemController(ItemService itemService) {
        this.itemService = itemService;
    }

    @GetMapping
    public List<? extends ItemDTO> getAll() {
        return itemService.getAll();
    }

    @GetMapping("/search")
    public List<? extends ItemDTO> search(@RequestParam String name) {
        return itemService.find(name);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ItemDTO> getItem(@PathVariable Long id) {
        return ResponseEntity.of(itemService.getItem(id));
    }


    @PostMapping()
    public @ResponseBody ItemDTO create(@RequestBody @Valid ItemDTO itemDTO) {
        return itemService.create(itemDTO);
    }
}