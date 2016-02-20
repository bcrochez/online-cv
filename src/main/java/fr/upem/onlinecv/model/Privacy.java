package fr.upem.onlinecv.model;

public enum Privacy {
    PUBLIC(0), USERS(1), PRIVATE(2);
    
    private final int value;
    
    private Privacy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}