package org.example.auth.exception;

/**
 * @Author: lejun
 * @project: cloud
 * @description:
 * @time: 2023/12/29 14:22
 */

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;

import java.io.IOException;


public class ExtendOAuth2ExceptionSerializer extends StdSerializer<ExtendOAuth2Exception> {

    public ExtendOAuth2ExceptionSerializer() {
        super(ExtendOAuth2Exception.class);
    }

    @Override
    public void serialize(ExtendOAuth2Exception e, JsonGenerator gen, SerializerProvider serializerProvider) throws IOException {
        gen.writeStartObject();
        gen.writeObjectField("code", String.valueOf(e.getHttpErrorCode()));
        gen.writeStringField("msg", e.getMessage());
        gen.writeStringField("data", e.getDataMsg());
        gen.writeEndObject();

    }
}
