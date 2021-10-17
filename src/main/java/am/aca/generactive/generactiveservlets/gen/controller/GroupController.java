package am.aca.generactive.generactiveservlets.gen.controller;

import am.aca.generactive.generactiveservlets.gen.service.GroupService;
import am.aca.generactive.generactiveservlets.gen.service.ItemService;
import am.aca.generactive.generactiveservlets.gen.service.dto.groupdto.GroupDTO;
import am.aca.generactive.generactiveservlets.gen.service.dto.itemdto.ItemDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/group")
public class GroupController {
    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @GetMapping
    public List<? extends GroupDTO> getAll() {
        return groupService.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> getItem(@PathVariable Long id) {
        return ResponseEntity.of(groupService.getGroup(id));
    }


    @PostMapping()
    public @ResponseBody
    GroupDTO create(@RequestBody @Valid GroupDTO groupDTO) {
        return groupService.create(groupDTO);
    }
}


