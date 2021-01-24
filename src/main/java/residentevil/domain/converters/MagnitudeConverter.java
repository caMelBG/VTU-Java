package residentevil.domain.converters;

import residentevil.domain.enums.Magnitude;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class MagnitudeConverter implements AttributeConverter<Magnitude, String> {

    @Override
    public String convertToDatabaseColumn(Magnitude magnitude) {
        return magnitude == null ? null : magnitude.getLabel();
    }

    @Override
    public Magnitude convertToEntityAttribute(String label) {
        return Magnitude.fromLabel(label);
    }
}
