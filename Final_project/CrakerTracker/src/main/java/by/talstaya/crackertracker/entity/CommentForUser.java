package by.talstaya.crackertracker.entity;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

/**
 * CommentForUser is a entity of comment
 *
 * @author Anna Talstaya
 * @version 1.0
 */
public class CommentForUser implements Entity {

    private int commentId;
    private LocalDateTime dateOfComment;
    private LocalDate mealDate;
    private int userId;
    private User commentator;
    private String comment;

    public CommentForUser() {
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
        if (!(o instanceof CommentForUser)) return false;
        CommentForUser comment = (CommentForUser) o;
        return commentId == comment.commentId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentId);
    }

    public static class Builder {
        private CommentForUser commentForUser;

        public Builder() {
            commentForUser = new CommentForUser();
        }

        public Builder setCommentId(int commentId){
            commentForUser.commentId = commentId;
            return this;
        }

        public Builder setDateOfComment(LocalDateTime dateOfComment){
            commentForUser.dateOfComment = dateOfComment;
            return this;
        }

        public Builder setMealDate(LocalDate mealDate) {
            commentForUser.mealDate = mealDate;
            return this;
        }

        public Builder setUserId(int userId) {
            commentForUser.userId = userId;
            return this;
        }

        public Builder setCommentator(User commentator) {
            commentForUser.commentator = commentator;
            return this;
        }

        public Builder setComment(String comment) {
            commentForUser.comment = comment;
            return this;
        }

        public CommentForUser build() {
            return commentForUser;
        }

    }
}
