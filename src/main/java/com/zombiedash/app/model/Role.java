package com.zombiedash.app.model;

public enum Role {
   ATTENDEE(2),GAME_DESIGNER(1), ADMIN(0);

    private Integer val;

    private Role (Integer val){
        this.val = val;
    }
    public Integer getVal() {
        return val;
    }

    public static Role generateRole(String roleName) {
        if(roleName.equals("0") || roleName.equalsIgnoreCase("admin"))
            return ADMIN;
        if (roleName.equals("1") || roleName.equalsIgnoreCase("GameDesigner"))
            return GAME_DESIGNER;
        if (roleName.equals("2") || roleName.equalsIgnoreCase("Attendee"))
            return ATTENDEE;
        throw new RuntimeException("Invalid Role name.");
    }

    @Override
    public String toString() {
        switch(val){
            case 0:
                return "Administrator";
            case 1:
                return "Game Designer";
            default:
                return "Invalid Role";
        }
    }
}
