package by.talstaya.crackertracker.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * UserComment is a entity of comment
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class UserComment implements Entity {

    private int commentId;
    private LocalDateTime dateOfComment;
    private LocalDate mealDate;
    private int userId;
    private User commentator;
    private String comment;

    public UserComment() {
    }

    public int getCommentId() {
        return commentId;
    }

    public LocalDateTime getDateOfComment() {
        return dateOfComment;
    }

    public LocalDate getMealDate() {
        return mealDate;
    }

    public int getUserId() {
        return userId;
    }

    public User getCommentator() {
        return commentator;
    }

    public String getComment() {
        return comment;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof UserComment)) return false;
        UserComment comment = (UserComment) o;
        return commentId == comment.commentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

    public static class Builder {
        private UserComment userComment;

        public Builder() {
            userComment = new UserComment();
        }

        public Builder setCommentId(int commentId){
            userComment.commentId = commentId;
            return this;
        }

        public Builder setDateOfComment(LocalDateTime dateOfComment){
            userComment.dateOfComment = dateOfComment;
            return this;
        }

        public Builder setMealDate(LocalDate mealDate) {
            userComment.mealDate = mealDate;
            return this;
        }

        public Builder setUserId(int userId) {
            userComment.userId = userId;
            return this;
        }

        public Builder setCommentator(User commentator) {
            userComment.commentator = commentator;
            return this;
        }

        public Builder setComment(String comment) {
            userComment.comment = comment;
            return this;
        }

        public UserComment build() {
            return userComment;
        }

    }
}
