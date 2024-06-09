package luis122448.platformtraining.application.domain.service.implement;

import luis122448.platformtraining.application.domain.model.UserClassModel;
import luis122448.platformtraining.application.domain.service.service.CommentService;
import luis122448.platformtraining.application.domain.service.service.UserClassService;
import luis122448.platformtraining.application.persistence.entity.ClassEntity;
import luis122448.platformtraining.application.persistence.entity.UserClassEntity;
import luis122448.platformtraining.application.persistence.entity.key.UserClassKey;
import luis122448.platformtraining.application.persistence.mapper.TeacherMapper;
import luis122448.platformtraining.application.persistence.mapper.UserClassMapper;
import luis122448.platformtraining.application.persistence.repository.ClassRepository;
import luis122448.platformtraining.application.persistence.repository.TeacherRepository;
import luis122448.platformtraining.application.persistence.repository.UserClassRepository;
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
import java.util.NoSuchElementException;
import java.util.Optional;

import static luis122448.platformtraining.application.domain.constant.ErrorMessages.*;

@Service
public class UserClassServiceImpl implements UserClassService {

    private final UserClassRepository userClassRepository;
    private final TeacherRepository teacherRepository;
    private final ClassRepository classRepository;
    private final CommentService commentService;
    private final UserClassMapper userClassMapper;
    private final TeacherMapper teacherMapper;
    private final Long idCompany;
    private final Long idUser;

    public UserClassServiceImpl(UserClassRepository userClassRepository, TeacherRepository teacherRepository, ClassRepository classRepository, CommentService commentService, UserClassMapper userClassMapper, TeacherMapper teacherMapper) {
        this.userClassRepository = userClassRepository;
        this.teacherRepository = teacherRepository;
        this.classRepository = classRepository;
        this.commentService = commentService;
        this.userClassMapper = userClassMapper;
        this.teacherMapper = teacherMapper;
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
    public ApiResponseList<UserClassModel> findByCourse(Long idCourse) throws GenericListServiceException {
        List<UserClassModel> userClassModelList = this.userClassMapper.toUserClassModelList(this.userClassRepository.findByIdCourse(this.idCompany,this.idUser,idCourse));
        return new ApiResponseList<UserClassModel>(Optional.of(userClassModelList));
    }

    @Override
    public ApiResponseObject<UserClassModel> findByClass(Long idClass) throws GenericObjectServiceException, GenericListServiceException {
        UserClassModel userClassModel = this.userClassMapper.toUserClassModel(this.userClassRepository.findByIdClass(this.idCompany,this.idUser,idClass).orElseThrow(
                () -> new NoSuchElementException(String.format(USER_CLASS_NOT_FOUND,this.idUser.toString(),idClass.toString()))
        ));
        userClassModel.setTeacherModel(this.teacherMapper.toTeacherModel(this.teacherRepository.findByIdClass(this.idCompany,idClass).orElseThrow(
                () -> new NoSuchElementException(String.format(TEACHER_CLASS_NOT_FOUND,idClass.toString()))
        )));
        userClassModel.setCommentModelList(this.commentService.findByIdClass(idClass).getList().orElse(new ArrayList<>()));
        return new ApiResponseObject<UserClassModel>(Optional.of(userClassModel));
    }

    @Override
    public ApiResponseObject<UserClassModel> save(Long idClass, Long idUser) throws GenericObjectServiceException{
        UserClassEntity userClassEntity = UserClassEntity.builder()
                .idCompany(this.idCompany)
                .idUser(idUser)
                .idClass(idClass)
                .userRegister(this.idUser)
                .registerDate(LocalDate.now())
                .startDate(null)
                .endDate(null)
                .expirationDate(null)
                .begin(false)
                .progress(false)
                .finalized(false)
                .advance(BigDecimal.valueOf(0.00))
                .requiredTime(BigDecimal.valueOf(0.00))
                .build();
        userClassEntity.setStatus("Y");
        this.userClassRepository.save(userClassEntity);
        UserClassModel userClassModel = UserClassModel.builder()
                .idClass(idClass)
                .build();
        return new ApiResponseObject<UserClassModel>(Optional.of(userClassModel));
    }

    @Override
    public ApiResponseList<UserClassModel> generateByCourse(Long idCourse, Long idUser) throws GenericListServiceException {
        List<Long> idClassList = this.classRepository.findByIdCourse(this.idCompany,idCourse)
                .stream()
                .map(ClassEntity::getIdClass)
                .toList();
        List<UserClassModel> userClassModelList = new ArrayList<>();
        idClassList.forEach(idClass -> {
            try {
                userClassModelList.add(this.save(idClass,idUser).getObject().orElseThrow());
            } catch (GenericObjectServiceException e) {
                throw new RuntimeException(e);
            }
        });
        return new ApiResponseList<UserClassModel>(Optional.of(userClassModelList));
    }

    @Override
    public ApiResponseObject<UserClassModel> update(Long idClass, UserClassModel userClassModel) throws GenericObjectServiceException {
        UserClassEntity userClassEntity = this.userClassRepository.findById(new UserClassKey(this.idCompany,this.idUser,userClassModel.getIdClass())).orElseThrow();
        userClassEntity.setLocked(userClassModel.getLocked());
        userClassEntity.setBegin(userClassModel.getBegin());
        userClassEntity.setProgress(userClassModel.getProgress());
        userClassEntity.setFinalized(userClassModel.getFinalized());
        userClassEntity.setAdvance(userClassModel.getAdvance());
        userClassEntity.setRequiredTime(userClassModel.getRequiredTime());
        this.userClassRepository.save(userClassEntity);
        return new ApiResponseObject<UserClassModel>(Optional.empty());
    }

}
