package luis122448.platformtraining.application.web.controller;

import luis122448.platformtraining.application.domain.service.service.ExamService;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import static luis122448.platformtraining.application.web.constant.APIConstant.API_EXAM;

@RestController
@RequestMapping(API_EXAM)
public class ExamController {
    private final ExamService examService;
    public ExamController(ExamService examService) {
        this.examService = examService;
    }
    @GetMapping("/by-course")
    public ResponseEntity<?> findById(@RequestParam(name = "idCourse") Long idCourse) throws GenericObjectServiceException {
        return ResponseEntity.ok(this.examService.findByCourse(idCourse));
    }
}
