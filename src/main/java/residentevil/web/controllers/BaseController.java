package residentevil.web.controllers;

import lombok.extern.java.Log;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;

import java.beans.PropertyEditorSupport;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.logging.Level;

@Log
public class BaseController {

    protected BaseController() {
    }

    protected static Optional<UUID> uuid(String id) {
        try {
            return Optional.of(UUID.fromString(id));
        } catch (IllegalArgumentException e) {
            log.log(Level.SEVERE, "String cannot be converted to UUID: " + id, e);
            return Optional.empty();
        }
    }

    protected static String redirect(String url) {
        return "redirect:" + url;
    }

    private static void preventTextModificationForFields(WebDataBinder binder, List<String> doNotTrimFieldsList) {
        PropertyEditorSupport noTrimPropertyEditor = new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) {
                super.setValue(text);
            }
        };

        doNotTrimFieldsList.forEach(field ->
                binder.registerCustomEditor(String.class, field, noTrimPropertyEditor));
    }

    @InitBinder
    private void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(String.class, new StringTrimmerEditor(isEmptyInputStringsNull()));

        preventTextModificationForFields(binder, getUnmodifiedTextFieldsList());
    }

    protected List<String> getUnmodifiedTextFieldsList() {
        return List.of();
    }

    protected boolean isEmptyInputStringsNull() {
        return false;
    }
}
