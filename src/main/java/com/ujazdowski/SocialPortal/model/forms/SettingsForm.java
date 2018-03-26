package com.ujazdowski.SocialPortal.model.forms;

import com.ujazdowski.SocialPortal.model.tables.Language;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class SettingsForm {
    @Size(min = 2, max = 64, message = "{valid.register.firstName.size}")
    private String firstName;
    @Size(min = 2, max = 64, message = "{valid.register.secondName.size}")
    private String secondName;
    @NotNull
    private Language language;
}
