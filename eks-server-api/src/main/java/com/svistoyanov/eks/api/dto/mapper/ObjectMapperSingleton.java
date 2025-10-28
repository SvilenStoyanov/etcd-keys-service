package com.svistoyanov.eks.api.dto.mapper;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.core.SerializableString;
import com.fasterxml.jackson.core.io.CharacterEscapes;
import com.fasterxml.jackson.core.io.SerializedString;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.TimeZone;

public class ObjectMapperSingleton {

    /**
     * According to ECMAScript language specification there are characters that must be escaped when used
     * in string literals. Jackson is escaping the ASCII codes below 0xF0 by default, but U+2028 and U+2029 characters
     * must be replaced with their escape sequences manually
     */
    protected static final char[] CHARS_TO_ESCAPE = new char[] {
            '\u2028',
            '\u2029'
    };

    private static final ObjectMapper          objectMapper = new ObjectMapper();
    public static final  ObjectMapperSingleton instance     = new ObjectMapperSingleton();

    private ObjectMapperSingleton() {
        objectMapper.getFactory().setCharacterEscapes(new CustomCharacterEscapes());
        objectMapper
                .enable(SerializationFeature.INDENT_OUTPUT)
                .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
                .disable(SerializationFeature.WRITE_DATE_TIMESTAMPS_AS_NANOSECONDS)
                .setDateFormat(this.getISO8601DateFormat())
                .setSerializationInclusion(JsonInclude.Include.NON_NULL)
                .registerModule(new JavaTimeModule());
    }

    public ObjectMapper getObjectMapper() {
        return objectMapper;
    }

    private DateFormat getISO8601DateFormat() {
        final DateFormat df = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss'Z'");
        df.setTimeZone(TimeZone.getTimeZone("UTC"));
        return df;
    }

    private static class CustomCharacterEscapes extends CharacterEscapes {
        private final Map<Integer, String> customEscapes;

        CustomCharacterEscapes() {
            customEscapes = createCharacterEscapes();
        }

        @Override
        public int[] getEscapeCodesForAscii() {
            return CharacterEscapes.standardAsciiEscapesForJSON();
        }

        /**
         * Special escape for the other non-ascii
         *
         * @param ch character to escape
         * @return escaped character ( \\uXXXX form )
         */
        @Override
        public SerializableString getEscapeSequence(int ch) {
            final String escapedSequence = customEscapes.get(ch);
            if (escapedSequence == null) {
                return null;
            }

            return new SerializedString(escapedSequence);
        }

        private Map<Integer, String> createCharacterEscapes() {
            final Map<Integer, String> escapes = new HashMap<>();
            for (char character : CHARS_TO_ESCAPE) {
                int charIntValue = (int) character;
                final String escaped = "\\u" + String.format("%04X", charIntValue);
                escapes.put(charIntValue, escaped);
            }
            return escapes;
        }
    }
}
