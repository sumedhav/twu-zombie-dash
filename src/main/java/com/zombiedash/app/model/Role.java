package com.zombiedash.app.model;

public enum Role {
    GAME_DESIGNER(0);

    private Integer val;

    private Role (Integer val){
        this.val = val;
    }
    public Integer getVal() {
        return val;
    }
}
