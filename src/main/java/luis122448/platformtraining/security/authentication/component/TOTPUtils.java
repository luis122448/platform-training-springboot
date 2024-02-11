package luis122448.platformtraining.security.authentication.component;

import de.taimos.totp.TOTP;
import org.apache.commons.codec.binary.Base32;
import org.apache.commons.codec.binary.Hex;
import org.springframework.stereotype.Component;

@Component
public class TOTPUtils {

    public String generateCode(String secretCode) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretCode);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.getOTP(hexKey);
    }

    public Boolean verifyCode(String secretCode, String verifyCode) {
        Base32 base32 = new Base32();
        byte[] bytes = base32.decode(secretCode);
        String hexKey = Hex.encodeHexString(bytes);
        return TOTP.validate(hexKey, verifyCode);
    }

}
