package luis122448.platformtraining.application.domain.report;

import luis122448.platformtraining.application.persistence.entity.ClassEntity;
import luis122448.platformtraining.application.persistence.entity.enums.TypeClassEnum;
import luis122448.platformtraining.application.persistence.entity.key.CourseKey;
import luis122448.platformtraining.application.persistence.repository.ClassRepository;
import luis122448.platformtraining.application.persistence.repository.CourseRepository;
import luis122448.platformtraining.security.application.domain.componnet.SecurityContextInitializer;
import luis122448.platformtraining.util.exception.GenericByteServiceException;
import luis122448.platformtraining.util.exception.GenericListServiceException;
import luis122448.platformtraining.util.object.api.ApiResponseByte;
import luis122448.platformtraining.util.object.api.ApiResponseReport;
import luis122448.platformtraining.util.object.archive.ColumnInfo;
import luis122448.platformtraining.util.object.archive.ImportErrorModel;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import static luis122448.platformtraining.application.domain.constant.ArchiveConstants.*;
import static luis122448.platformtraining.application.domain.constant.ArchiveConstants.IMPORT_SHEET_PRINCIPAL;
import static luis122448.platformtraining.application.domain.constant.DirectoryConstants.FORMAT_CLASS_IMPORT;
import static luis122448.platformtraining.application.domain.constant.TitleConstants.TITLE_IMPORT_CLASS;
import static luis122448.platformtraining.util.component.Utils.*;

@Service
public class ClassImport {
    private final ClassRepository classRepository;
    private final CourseRepository courseRepository;
    private final GenericReport genericReport;
    private final SecurityContextInitializer securityContextInitializer;

    public ClassImport(ClassRepository classRepository, CourseRepository courseRepository, GenericReport genericReport, SecurityContextInitializer securityContextInitializer) {
        this.classRepository = classRepository;
        this.courseRepository = courseRepository;
        this.genericReport = genericReport;
        this.securityContextInitializer = securityContextInitializer;
    }

    public ApiResponseByte<?> exportByExcel(Long idCourse) throws GenericByteServiceException, GenericListServiceException {
        try{
            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
            XSSFWorkbook xssfWorkbook = this.exportXSSFWorkbook(idCourse);
            xssfWorkbook.write(byteArrayOutputStream);
            byte[] excelBytes = byteArrayOutputStream.toByteArray();
            return new ApiResponseByte<>(1,EXPORT_SUCCESS, Optional.of(excelBytes),TITLE_IMPORT_CLASS,EXCEL_FORMAT,EXCEL_EXTENSION);
        } catch ( FileNotFoundException e ) {
            throw new GenericByteServiceException("REPORTED NOT IMPLEMENT",e.getMessage(),e);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    public ApiResponseByte<?> importByExcel(Long idCourse, MultipartFile multipartFile) throws GenericByteServiceException {
        try {
            this.genericReport.genericValidArchive(multipartFile, TITLE_IMPORT_CLASS, EXCEL_EXTENSION);
            byte[] fileBytes = multipartFile.getBytes();
            ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(fileBytes);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(byteArrayInputStream);
            ApiResponseReport<?> tmp = this.readXSSFWorkbook(idCourse, xssfWorkbook);
            JasperPrint jasperPrint = tmp.getJasperPrint().orElseThrow();
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            JRPdfExporter exporter = new JRPdfExporter();
            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(new SimpleOutputStreamExporterOutput(outputStream));
            exporter.exportReport();
            byte[] pdfBytes = outputStream.toByteArray();
            return new ApiResponseByte<>(tmp.getStatus(),tmp.getMessage(), Optional.of(pdfBytes),TITLE_IMPORT_CLASS,PDF_FORMAT,PDF_EXTENSION);
        } catch (IOException | JRException e){
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    private XSSFWorkbook exportXSSFWorkbook(Long idCourse) throws GenericByteServiceException, GenericListServiceException {
        try{
            Long idCompany = securityContextInitializer.getIdCompany();
            File file = new File(FORMAT_CLASS_IMPORT);
            InputStream inputStream = new FileInputStream(file);
            XSSFWorkbook xssfWorkbook = new XSSFWorkbook(inputStream);
            XSSFSheet format = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);

            int startRow = IMPORT_START_ROW;
            List<ClassEntity> classEntityList = this.classRepository.findByIdCourse(idCompany, idCourse);

            for (int i = startRow; i < classEntityList.size() + startRow; i++) {
                Row rowData = format.createRow(i);

                Cell cellData = rowData.createCell(0);
                cellData.setCellValue(classEntityList.get(i - startRow).getIdCourse());

                cellData = rowData.createCell(1);
                cellData.setCellValue(classEntityList.get(i - startRow).getIdClass());

                cellData = rowData.createCell(2);
                cellData.setCellValue(classEntityList.get(i - startRow).getIdTeacher());

                cellData = rowData.createCell(3);
                cellData.setCellValue(classEntityList.get(i - startRow).getPosition().doubleValue());

                cellData = rowData.createCell(4);
                cellData.setCellValue(classEntityList.get(i - startRow).getTypeClass().name());

                cellData = rowData.createCell(5);
                cellData.setCellValue(classEntityList.get(i - startRow).getTitle());

                cellData = rowData.createCell(6);
                cellData.setCellValue(classEntityList.get(i - startRow).getDescription());

                cellData = rowData.createCell(7);
                cellData.setCellValue(classEntityList.get(i - startRow).getMarkdownContent());

                cellData = rowData.createCell(8);
                cellData.setCellValue(classEntityList.get(i - startRow).getUrlImage());

                cellData = rowData.createCell(9);
                cellData.setCellValue(classEntityList.get(i - startRow).getIdVideo());

                cellData = rowData.createCell(10);
                cellData.setCellValue(classEntityList.get(i - startRow).getUrlVideo());

                cellData = rowData.createCell(11);
                cellData.setCellValue(classEntityList.get(i - startRow).getStatus());
            }
            return xssfWorkbook;
        }
        catch ( FileNotFoundException e ) {
            throw new GenericByteServiceException("REPORTED NOT IMPLEMENT",e.getMessage(),e);
        } catch ( IOException e ) {
            throw new GenericByteServiceException(EXPORT_FAILED,e.getMessage(),e);
        }
    }

    public ApiResponseReport<?> readXSSFWorkbook(Long idCourse, XSSFWorkbook xssfWorkbook) throws IOException, JRException, GenericByteServiceException {
        Long idCompany = securityContextInitializer.getIdCompany();
        Long idUser = securityContextInitializer.getIdUser();
        List<ColumnInfo> columnInfoList = readHeaderXSSFWorkbook(xssfWorkbook);
        List<ClassEntity> classEntityList = new ArrayList<>();
        XSSFSheet formatSheet = xssfWorkbook.getSheet(IMPORT_SHEET_PRINCIPAL);
        List<ImportErrorModel> importErrorModelListGeneral = new ArrayList<>();

        int startRow = IMPORT_START_ROW;
        int lastRow = formatSheet.getLastRowNum();
        for (int i = startRow; i <= lastRow; i++) {
            ClassEntity classEntity = new ClassEntity();
            Row row = formatSheet.getRow(i);
            if(row == null){
                continue;
            }
            // Validate Required
            List<ImportErrorModel> importErrorModelList = validateFieldRequiredXSSFWorkbook(columnInfoList, row);
            importErrorModelListGeneral.addAll(importErrorModelList);
            // Register
            classEntity.setIdCompany(idCompany);
            classEntity.setIdCourse(getLongCellValue(row.getCell(0)));
            classEntity.setIdClass(getLongCellValue(row.getCell(1)));
            classEntity.setIdTeacher(getLongCellValue(row.getCell(2)));
            classEntity.setPosition(getBigDecimalCellValue(row.getCell(3)));
            // Validate Type Class - TypeClassEnum.CLASS
            String typeClass = getStringCellValue(row.getCell(4));
            if (TypeClassEnum.CLASS.name().equalsIgnoreCase(typeClass)){
                classEntity.setTypeClass(TypeClassEnum.CLASS);
            } else {
                ImportErrorModel importErrorModel = new ImportErrorModel(i, typeClass, "TYPE CLASS NOT MATCH");
                importErrorModelListGeneral.add(importErrorModel);
            }
            classEntity.setTitle(getStringCellValue(row.getCell(5)));
            classEntity.setDescription(getStringCellValue(row.getCell(6)));
            classEntity.setMarkdownContent(getStringCellValue(row.getCell(7)));
            classEntity.setUrlImage(getStringCellValue(row.getCell(8)));
            classEntity.setIdVideo(getStringCellValue(row.getCell(9)));
            classEntity.setUrlVideo(getStringCellValue(row.getCell(10)));
            classEntity.setStatus(getStringCellValue(row.getCell(11)));
            classEntity.setCreateBy(idUser);
            classEntity.setCreateAt(LocalDateTime.now());
            classEntity.setUpdateBy(idUser);
            classEntity.setUpdateAt(LocalDateTime.now());
            classEntityList.add(classEntity);
        }
        if (!importErrorModelListGeneral.isEmpty()){
            Integer numberRow = lastRow - (startRow - 1);
            return this.genericReport.genericResponseReport(importErrorModelListGeneral,TITLE_IMPORT_CLASS, numberRow);
        }
        return validateAndImportList(idCourse, classEntityList, startRow, lastRow);
    }

    public ApiResponseReport<?> validateAndImportList(Long idCourse, List<ClassEntity> classEntityList, Integer startRow, Integer lastRow) throws JRException,GenericByteServiceException {
        Long idcompany = securityContextInitializer.getIdCompany();
        List<ImportErrorModel> importErrorModelList = new ArrayList<>();
        Integer currentRow = startRow;
        for (ClassEntity entity : classEntityList) {
            if(!Objects.equals(entity.getIdCourse(), idCourse)){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow, entity.getIdCourse().toString(),"COURSE NOT MATCH");
                importErrorModelList.add(importErrorModel);
            }
            if(!this.courseRepository.existsById(new CourseKey(idcompany, entity.getIdCourse()))){
                ImportErrorModel importErrorModel = new ImportErrorModel(currentRow, entity.getIdCourse().toString(),"COURSE NOT EXISTS");
                importErrorModelList.add(importErrorModel);
            }
            currentRow = currentRow + 1;
        }
        Integer numberRow = lastRow - (startRow - 1);
        if (importErrorModelList.isEmpty()){
            this.classRepository.saveAll(classEntityList);
        }
        return this.genericReport.genericResponseReport(importErrorModelList,TITLE_IMPORT_CLASS, numberRow);
    }
}
