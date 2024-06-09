package luis122448.platformtraining.application.domain.service.implement;

import luis122448.platformtraining.application.domain.model.UserClassModel;
import luis122448.platformtraining.application.domain.model.UserCourseModel;
import luis122448.platformtraining.application.domain.service.service.UserClassService;
import luis122448.platformtraining.application.domain.service.service.UserCourseService;
import luis122448.platformtraining.application.persistence.entity.CourseEntity;
import luis122448.platformtraining.application.persistence.entity.UserCourseEntity;
import luis122448.platformtraining.application.persistence.entity.key.CourseKey;
import luis122448.platformtraining.application.persistence.entity.key.UserCourseKey;
import luis122448.platformtraining.application.persistence.mapper.TeacherMapper;
import luis122448.platformtraining.application.persistence.mapper.UserCourseMapper;
import luis122448.platformtraining.application.persistence.repository.CourseRepository;
import luis122448.platformtraining.application.persistence.repository.TeacherRepository;
import luis122448.platformtraining.application.persistence.repository.UserCourseRepository;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseList;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserCourseServiceImpl implements UserCourseService {

    private final UserCourseRepository userCourseRepository;
    private final CourseRepository courseRepository;
    private final UserCourseMapper userCourseMapper;
    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final UserClassService userClassService;
    private final Long idCompany;
    private final Long idUser;

    public UserCourseServiceImpl(UserCourseRepository userCourseRepository, CourseRepository courseRepository, UserCourseMapper userCourseMapper, TeacherRepository teacherRepository, TeacherMapper teacherMapper, UserClassService userClassService) {
        this.userCourseRepository = userCourseRepository;
        this.courseRepository = courseRepository;
        this.userCourseMapper = userCourseMapper;
        this.teacherRepository = teacherRepository;
        this.teacherMapper = teacherMapper;
        this.userClassService = userClassService;
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.getPrincipal() instanceof UserDetailsCustom userDetailsCustom) {
            this.idCompany = userDetailsCustom.getIdCompany();
            this.idUser = userDetailsCustom.getIdUser();
        } else {
            this.idCompany = 0L;
            this.idUser = 0L;
        }
    }

    @Override
    public ApiResponseList<UserCourseModel> findByModule(Long idModule) throws GenericListServiceException {
        List<UserCourseModel> userCourseModelList = this.userCourseMapper.toUserCourseModelList(this.userCourseRepository.findByIdModule(this.idCompany,this.idUser,idModule));
        return new ApiResponseList<UserCourseModel>(Optional.of(userCourseModelList));
    }

    @Override
    public ApiResponseObject<UserCourseModel> findByCourse(Long idCourse) throws GenericObjectServiceException, GenericListServiceException {
        CourseEntity courseEntity = this.courseRepository.findById(new CourseKey(idCompany,idCourse)).orElseThrow(
                () -> new GenericObjectServiceException("COURSE NOT EXISTS!")
        );
        UserCourseModel userCourseModel = this.userCourseMapper.toUserCourseModel(this.userCourseRepository.findByIdCourse(this.idCompany,this.idUser,idCourse).orElseThrow());
        userCourseModel.setDescription(courseEntity.getDescription());
        userCourseModel.setMarkdownContent(courseEntity.getMarkdownContent());
        userCourseModel.setTeacherModel(this.teacherMapper.toTeacherModel(this.teacherRepository.findByIdCourse(this.idCompany, idCourse).orElseThrow()));
        List<UserClassModel> userClassModelList = this.userClassService.findByCourse(idCourse).getList().orElseThrow();
        userCourseModel.setUserClassModelList(userClassModelList);
        return new ApiResponseObject<UserCourseModel>(Optional.of(userCourseModel));
    }

    @Override
    public ApiResponseObject<UserCourseModel> save(Long idCourse, Long idUser) throws GenericObjectServiceException {
        UserCourseEntity userCourseEntity = UserCourseEntity.builder()
                .idCompany(this.idCompany)
                .idUser(idUser)
                .idCourse(idCourse)
                .userRegister(this.idUser)
                .registerDate(LocalDate.now())
                .startDate(null)
                .endDate(null)
                .expirationDate(null)
                .locked(false)
                .begin(false)
                .progress(false)
                .finalized(false)
                .advance(BigDecimal.valueOf(0.00))
                .requiredTime(BigDecimal.valueOf(0.00))
                .idLastExam(null)
                .build();
        userCourseEntity.setStatus("Y");
        this.userCourseRepository.save(userCourseEntity);
        UserCourseModel userCourseModel = UserCourseModel.builder()
                .idCourse(idCourse)
                .build();
        return new ApiResponseObject<UserCourseModel>(Optional.of(userCourseModel));
    }

    @Override
    public ApiResponseList<UserCourseModel> generateAll(Long idModule, Long idUser) throws GenericListServiceException {
        List<Long> idCourseList = this.courseRepository.findByIdModule(this.idCompany, idModule)
                .stream()
                .map(CourseEntity::getIdCourse)
                .toList();
        List<UserCourseModel> userCourseModelList = new ArrayList<>();
        idCourseList.forEach(idCourse ->{
            try {
                userCourseModelList.add(this.save(idCourse,this.idUser).getObject().orElseThrow());
            } catch (GenericObjectServiceException e) {
                throw new RuntimeException(e);
            }
        });
        return new ApiResponseList<UserCourseModel>(Optional.of(userCourseModelList));
    }

    @Override
    public ApiResponseObject<UserCourseModel> update(Long idCourse, UserCourseModel userCourseModel) throws GenericObjectServiceException {
        UserCourseEntity userCourseEntity = this.userCourseRepository.findById(new UserCourseKey(this.idCompany,this.idUser,idCourse)).orElseThrow();
        return new ApiResponseObject<UserCourseModel>(Optional.empty());
    }
}


