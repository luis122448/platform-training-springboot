package luis122448.platformtraining.application.web.controller;

import luis122448.platformtraining.application.domain.service.service.ModuleService;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.platformtraining.application.web.constant.APIConstant.API_MODULE;

@RestController
@RequestMapping(API_MODULE)
public class ModuleController {
    private final ModuleService moduleService;
    public ModuleController(ModuleService moduleService) {
        this.moduleService = moduleService;
    }
    @GetMapping("/by-user")
    public ResponseEntity<?> findByUser() throws GenericListServiceException {
        return ResponseEntity.ok(this.moduleService.findByUser());
    }
}
