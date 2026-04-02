package page;

import lombok.Getter;

public enum AutomationOption {

    EMPTY(""),
    YES("Yes"),
    NO("No"),
    UNDECIDED("Undecided");

    @Getter
    private final String value;

    AutomationOption(String value) {
        this.value = value;
    }

}
