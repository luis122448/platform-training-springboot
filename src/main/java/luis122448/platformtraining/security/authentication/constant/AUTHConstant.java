package luis122448.platformtraining.security.authentication.constant;

public class AUTHConstant {

    public static final String API_AUTH = "/auth";
    public static final String PATH_LOGIN = "/login";
    public static final String PATH_VERIFY_CODE = "/verify-code";
    public static final String TAG_AUTH = "Auth";
    public static final String PATH_REFRESH_TOKEN = "/refresh-token";
    public static final String HEADER_AUTHORIZATION_KEY = "Authorization";
    public static final String HEADER_REFRESH_TOKEN_KEY 	= "RefreshToken";
    public static final String TOKEN_BEARER_PREFIX 		= "Bearer ";
    public static final String AUTHORITIES					= "authorities";
    public static final String ID_COMPANY = "idCompany";
    public static final String ID_USER = "idUser";
    public static final String COMPANY = "company";
    public static final String USERNAME = "username";
    public static final String TOTP_KEY = "KXJT7ADTPNAMIQQCOVBMVFIQNCOE2V6X";
    // JWT
    public static final String ISSUER_INFO = "http://www.galaxy.edu.pe/";
//    public static final SecretKey SUPER_SECRET_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS512);
    public static final long TOKEN_EXPIRATION_TIME_TOKEN = 15_000_000_00; // 1 day  86_400_000
    public static final long TOKEN_EXPIRATION_TIME_REFRESH_TOKEN = 600_000; // 1 day  86_400_000

    //    Deprecated https://stackoverflow.com/questions/40252903/static-secret-as-byte-key-or-string/40274325#40274325
        public static final String SUPER_SECRET_KEY = "1d01fae158548881ce4693dd080e7730108347a6094dc15ffaa18b21e872faf6a95c782962b6ea5bf8f7ba252afabedda6543a967cc7a6ce4b33996a12340d05"; //10737418243486784401
    //    https://www.allkeysgenerator.com/ Encryption key 512-bit

    // Message ALERT OR INFO
    public static final String ALERT_VERIFY_CODE = "PLEASE ENTER THE VERIFICATION CODE";

    // Message ERROR
    public static final String INVALID_VERIFY_CODE = "INVALID VERIFY CODE [%s]!";
    public static final String INVALID_CREDENTIALS = "INVALID USER OR PASSWORD, REVIEW!";
    public static final String INVALID_TOKEN = "TOKEN IS INVALID, PLEASE RE-LOGIN";

}
