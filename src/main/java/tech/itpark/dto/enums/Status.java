package tech.itpark.dto.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Getter
@RequiredArgsConstructor
public enum Status {
    POST_PRODUCTION("Post Production"),
    RUMORED("Rumored"),
    RELEASED("Released");

    private static final Map<String, Status> STATUSES = new HashMap<>();

    static {
        for (Status status : Status.values()) {
            STATUSES.put(status.getDescription(), status);
        }
    }

    private final String description;

    public static Status getStatus(String description) {
        return STATUSES.get(description);
    }
}
