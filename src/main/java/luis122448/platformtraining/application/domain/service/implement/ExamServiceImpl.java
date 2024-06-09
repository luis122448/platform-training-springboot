package luis122448.platformtraining.application.domain.service.implement;

import luis122448.platformtraining.application.domain.model.ExamModel;
import luis122448.platformtraining.application.domain.service.service.ExamService;
import luis122448.platformtraining.application.persistence.entity.CourseEntity;
import luis122448.platformtraining.application.persistence.entity.key.CourseKey;
import luis122448.platformtraining.application.persistence.entity.key.ExamKey;
import luis122448.platformtraining.application.persistence.mapper.ExamMapper;
import luis122448.platformtraining.application.persistence.repository.CourseRepository;
import luis122448.platformtraining.application.persistence.repository.ExamRepository;
import luis122448.platformtraining.security.application.domain.componnet.SecurityContextInitializer;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ExamServiceImpl implements ExamService {
    private final SecurityContextInitializer securityContextInitializer;
    private final ExamRepository examRepository;
    private final CourseRepository courseRepository;
    private final ExamMapper examMapper;
    public ExamServiceImpl(SecurityContextInitializer securityContextInitializer, ExamRepository examRepository, CourseRepository courseRepository, ExamMapper examMapper) {
        this.securityContextInitializer = securityContextInitializer;
        this.examRepository = examRepository;
        this.courseRepository = courseRepository;
        this.examMapper = examMapper;
    }
    @Override
    public ApiResponseObject<ExamModel> findByCourse(Long idCourse) throws GenericObjectServiceException {
        Long idCompany = securityContextInitializer.getIdCompany();
        CourseEntity courseEntity = this.courseRepository.findById(new CourseKey(idCompany,idCourse)).orElseThrow(
                () -> new GenericObjectServiceException("COURSE NOT EXISTS!")
        );
        ExamModel examModel = this.examMapper.toExamModel(this.examRepository.findByIdCourse(idCompany, courseEntity.getIdExam()).orElseThrow(
                () -> new GenericObjectServiceException("EXAM NOT EXISTS!")
        ));
        examModel.setCourseTitle(courseEntity.getTitle());
        examModel.setCourseUrlIcon(courseEntity.getUrlIcon());
        return new ApiResponseObject<>(Optional.of(examModel));
    }
}
