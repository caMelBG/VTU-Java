package residentevil.web.converters;

import residentevil.domain.enums.Creator;
import org.springframework.core.convert.converter.Converter;

public class CreatorToStringConverter implements Converter<Creator, String> {

    @Override
    public String convert(Creator creator) {
        return creator.getLabel();
    }
}
