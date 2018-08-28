package com.example.emp354.linear.DatabaseAssignmentMaccabi;

public class MaccabiLikesTable {
    public static final String TABLE_NAME="LikesTable";
    public static final String COLUMN_PROFILE="Profile";
    public static final String COLUMN_LIKE="Like";


    private int profile,liked;


    public static final String CREATE_TABLE="CREATE TABLE "+TABLE_NAME+" ( "+COLUMN_PROFILE+" INTEGER ,"
            +COLUMN_LIKE+" INTEGER "+" ) ";

    public MaccabiLikesTable()
    {

    }
    public MaccabiLikesTable(int profile,int liked)
    {
     this.profile=profile;
     this.liked=liked;
    }

    public int getProfile() {
        return profile;
    }

    public void setProfile(int profile) {
        this.profile = profile;
    }

    public int getLiked() {
        return liked;
    }

    public void setLiked(int liked) {
        this.liked = liked;
    }
}
