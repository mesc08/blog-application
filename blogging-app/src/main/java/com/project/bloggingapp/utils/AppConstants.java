package com.project.bloggingapp.utils;

public class AppConstants {
    public static final String PAGE_NUMBER = "0";
    public static final String PAGE_SIZE = "10";
    public static final String SORT_BY = "postId";
    public static final String SORT_ORDER = "asc";
    public static final int OK_STATUS_CODE = 200;
    public static final int CREATED_STATUS_CODE = 202;
    public static final int BAD_STATUS_CODE = 400;
    public static final int NOTFOUND_STATUS_CODE = 400;
    public static final String SEARCH_KEYWORD_QUERY = "SELECT p from Post p where p.title like :key";
    public static final String USER_ADDED_SUCCESS = "User got created successfully";
    public static final String USER_UPDATED_SUCCESS = "User updated with %d succesfully";
    public static final String USER_DELETED_SUCCESS = "User Deleted Successfully";
    public static final String USER_ALL_SUCCESS = "Found all users";
    public static final String USER_GET_SUCCESS = "Found user by %d id";
    public static final String POST_SAVED_SUCCESS = "saved post";
    public static final String POST_GETBYUSER_SUCCESS = "got post by users";
    public static final String POST_GETBYCATEGORY_SUCCESS = "got post by category";
    public static final String POST_GETBYPOSTID_SUCCESS = "got post by postid";
    public static final String POST_GETALL_SUCCESS = "got all post";
    public static final String POST_UPDATED_SUCCESS = "updated successfully";
    public static final String POST_DELETED_SUCCESS = "deleted post by postid";
    public static final String POST_GETBYKEYWORD_SUCCESS = "got post by keyword %s";
    public static final String CATEGORY_ADDED_SUCCESS = "added category";
    public static final String CATEGORY_UPDATE_SUCCESS = "updated category";
    public static final String CATEGORY_DELETE_SUCCESS = "deleted_category";

    public static final String CATEGORY_GOT_SUCCCESS = "got category";
    public static final String CATEGORY_GOT_ALL_SUCCESS = "got all category";
    public static final String CATEGORY_TITLE = "title";
    public static final String CATEGORY_DESCRIPTION = "description";
    public static final String CATEGORY_MAPPED = "category";

    public static final String CATEGORY_TITLE_NOT_EMPTY = "category title cannot be empty";
    public static final String CATEGORY_TITLE_SIZE_MSG = "category size should be greater than 3";
    public static final int CATEGORY_TITLE_SIZE = 3;
    public static final String CATEGORY_DESCRIPTION_NOT_EMPTY = "category description cannot be empty";
    public static final String CATEGORY_DESCRIPTION_SIZE_MSG = "category description size should be greater than 8";
    public static final int CATEGORY_DESCRIPTION_SIZE = 8;
    public static final String CATEGORY_TABLE_NAME = "categories";
    public static final String POST_TABLE_NAME = "blog_posts";
    public static final String USER_TABLE_NAME = "users";

    public static final String USER_EMAIL_COLUMN = "username";
    public static final int USER_EMAIL_COLUMN_LENGTH = 100;

    public static final String USER_MAPPED = "user";
    public static final String USER_NAME_MSG = "Username should be of minsize 4";
    public static final int USER_NAME_SIZE = 4;
    public static final String USER_EMAIL_MSG = "given email address is not valid";
    public static final String USER_PASSWORD_NOT_EMPTY = "Password cannot be empty";
    public static final int USER_PASSWORD_SIZE = 8;
    public static final String USER_PASSWORD_SIZE_MSG = "Password minimum of length 8";
    public static final String USER_PASSWORD_REGEX = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z])(?=.*[a-zA-Z]).{8,}$";

    public static final String USER_CONFIRMPASSWORD_SIZE_MSG = "Password minimum of length 8";
    public static final String USER_PASSWORD_REGEX_MSG = "password is invalid not matching regex";
    public static final String USER_CONFIRMPASSWORD_REGEX_MSG = "confirm password is invalid not matching regex";
    public static final String USER_CONFIRMPASSWORD_NOT_EMPTY = "Confirm Password cannot be empty";
    public static final String USER_ABOUT_NOT_EMPTY = "about section cannot be empty";
    public static final String VALIDATION_ERROR = "Validation error";
    public static final int POST_CONTENT_COLUMN_SIZE = 100000;
    public static final int POST_TITLE_SIZE = 100;
    public static  final String POST_TITLE_COLUMN_NAME = "post_title";
    public static final String POST_USER_COLUMN_JOIN = "user_id";
    public static final String POST_CATEGORY_COLUMN_JOIN = "category_id";
    public static final String COMMENT_TABLE = "blog_comment";
    public static final String COMMENT_ADDED_MSG = "Comment added successfully";
    public static final String COMMENT_DELETED_MSG = "Comment deleted successfully";
}
