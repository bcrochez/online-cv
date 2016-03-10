package fr.upem.onlinecv.model;

public enum Privacy {
    PUBLIC(0), USERS(1), CONNECTIONS(2), PRIVATE(3);
    
    private final int value;
    
    private Privacy(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }
}