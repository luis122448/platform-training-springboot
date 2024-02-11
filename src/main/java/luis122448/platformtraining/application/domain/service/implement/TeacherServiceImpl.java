package luis122448.platformtraining.application.domain.service.implement;

import luis122448.platformtraining.application.domain.model.TeacherModel;
import luis122448.platformtraining.application.domain.service.service.TeacherService;
import luis122448.platformtraining.application.persistence.entity.key.TeacherKey;
import luis122448.platformtraining.application.persistence.mapper.TeacherMapper;
import luis122448.platformtraining.application.persistence.repository.TeacherRepository;
import luis122448.platformtraining.security.authentication.user.UserDetailsCustom;
import luis122448.platformtraining.util.exception.GenericObjectServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseObject;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class TeacherServiceImpl implements TeacherService {

    private final TeacherRepository teacherRepository;
    private final TeacherMapper teacherMapper;
    private final Long idCompany;
    private final Long idUser;

    public TeacherServiceImpl(TeacherRepository teacherRepository, TeacherMapper teacherMapper) {
        this.teacherRepository = teacherRepository;
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
    public ApiResponseObject<TeacherModel> findByIdTeacher(Long idTeacher) throws GenericObjectServiceException {
        TeacherModel teacherModel = this.teacherMapper.toTeacherModel(this.teacherRepository.findById(new TeacherKey(this.idCompany,idTeacher)).orElseThrow());
        return new ApiResponseObject<TeacherModel>(Optional.of(teacherModel));
    }

    @Override
    public ApiResponseObject<TeacherModel> save(TeacherModel teacherModel) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<TeacherModel> update(TeacherModel teacherModel) throws GenericObjectServiceException {
        return null;
    }

    @Override
    public ApiResponseObject<TeacherModel> delete(TeacherKey teacherKey) throws GenericObjectServiceException {
        return null;
    }

}
