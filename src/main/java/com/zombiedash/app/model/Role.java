package com.zombiedash.app.model;

public enum Role {
    GAME_DESIGNER(1);

    private Integer val;

    private Role (Integer val){
        this.val = val;
    }
    public Integer getVal() {
        return val;
    }

    public static Role generateRole(String roleName) {
        if (roleName.equals("GameDesigner"))
            return GAME_DESIGNER;
        throw new RuntimeException("Invalid Role name.");
    }
}
