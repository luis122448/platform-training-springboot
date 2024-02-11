package luis122448.platformtraining.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import org.hibernate.type.descriptor.WrapperOptions;
import org.hibernate.type.format.FormatMapper;
import org.hibernate.type.format.jackson.JacksonJsonFormatMapper;

public class JacksonJsonFormatMapperCustom implements FormatMapper {

    private final FormatMapper delegate;

    public JacksonJsonFormatMapperCustom() {
        ObjectMapper objectMapper = createObjectMapper();
        delegate = new JacksonJsonFormatMapper(objectMapper);
    }

    private static ObjectMapper createObjectMapper() {
        return new ObjectMapper()
                .registerModule(new JavaTimeModule())
                .configure(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS, false);
    }

    @Override
    public <T> T fromString(CharSequence charSequence, org.hibernate.type.descriptor.java.JavaType<T> javaType, WrapperOptions wrapperOptions) {
        return delegate.fromString(charSequence, javaType, wrapperOptions);
    }

    @Override
    public <T> String toString(T value, org.hibernate.type.descriptor.java.JavaType<T> javaType, WrapperOptions wrapperOptions) {
        return delegate.toString(value, javaType, wrapperOptions);
    }
}
