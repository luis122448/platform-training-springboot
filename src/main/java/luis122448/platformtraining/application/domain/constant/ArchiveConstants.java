package luis122448.platformtraining.application.domain.constant;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class ArchiveConstants {

    // MESSAGE
    public static final  String IMPORT_SUCCESS = "SUCCESS IMPORT";
    public static final  String IMPORT_FAILED = "FAILED IMPORT";
    public static final  String EXPORT_SUCCESS = "SUCCESS EXPORT";
    public static final  String EXPORT_FAILED = "FAILED EXPORT";
    // FORMAT
    public static final String PDF_FORMAT = "application/pdf";
    public static final String PDF_EXTENSION = "pdf";
    public static final String EXCEL_FORMAT = "application/vnd.openxmlformats-officedocument.spreadsheetml.sheet";
    public static final String EXCEL_EXTENSION = "xlsx";
    // STANDARD
    public static final Integer IMPORT_START_ROW = 5;
    public static final String IMPORT_SHEET_PRINCIPAL = "Format";
    // CONSTANT
    public static final Long MAX_SIZE_BYTE = (long) (100 * 1024 * 1024);
    // VALUES
    public static final Set<String> VALUE_YES_OR_NOT = new HashSet<>(Arrays.asList("Y", "N"));
}
