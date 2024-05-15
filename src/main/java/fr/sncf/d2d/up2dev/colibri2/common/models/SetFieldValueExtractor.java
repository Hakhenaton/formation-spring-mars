package fr.sncf.d2d.up2dev.colibri2.common.models;

import jakarta.validation.valueextraction.ExtractedValue;
import jakarta.validation.valueextraction.ValueExtractor;

public class SetFieldValueExtractor implements ValueExtractor<SetField<@ExtractedValue ?>> {
    public void extractValues(SetField<?> originalValue, ValueReceiver receiver) {
        originalValue.ifProvided(value -> {
            receiver.value(null, value);
        });
    }
}
