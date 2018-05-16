package elzahr.magnolia.userdata;

public enum PrivacyLevel {
    LOW (1),
    MEDIUM (2),
    HIGH(3);

    private Integer level = 1;

    PrivacyLevel(Integer level) {
        this.level = level;
    }
}
