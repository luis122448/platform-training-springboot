package luis122448.platformtraining.application.domain.constant;

import java.nio.file.Path;
import java.nio.file.Paths;

public class DirectoryConstants {
    public static Path projectRoot = Paths.get(System.getProperty("user.dir"));
    public static final String FORMAT_CLASS_IMPORT = projectRoot + "/src/main/resources/archive/excel/FORMAT IMPORT CLASS.xlsx";
    public static final String REPORT_ERROR_IMPORT_A4_HORIZONTAL = projectRoot + "/src/main/resources/archive/jasper/error-import-a4-horizontal.jasper";
    public static final String REPORT_SUCCESS_IMPORT_A4_HORIZONTAL = projectRoot + "/src/main/resources/archive/jasper/success-import-a4-horizontal.jasper";
}
