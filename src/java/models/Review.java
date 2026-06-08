package models;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Review extends Model<Review> {

    private String id;
    private String userId;       // foreign key ke Akun
    private String serviceId;    // foreign key ke ServiceLaundry
    private int rating;
    private String comment;

    public Review() {
        this.table = "review";
        this.primaryKey = "id";
    }

    public Review(String id, String userId, String serviceId, int rating, String comment) {
        this.table = "review";
        this.primaryKey = "id";
        this.id = id;
        this.userId = userId;
        this.serviceId = serviceId;
        this.rating = rating;
        this.comment = comment;
    }

    @Override
    public Review toModel(ResultSet rs) {
        try {
            return new Review(
                rs.getString("id"),
                rs.getString("userId"),
                rs.getString("serviceId"),
                rs.getInt("rating"),
                rs.getString("comment")
            );
        } catch (SQLException e) {
            System.out.println("Error: " + e.getMessage());
            return null;
        }
    }

    public void displayReview() {
        System.out.println("Review [" + id + "] | Rating: " + rating + "/5 | Komentar: " + comment);
    }

    public String getId() { return id; }
    public void setId(String id) { this.id = id; }

    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getServiceId() { return serviceId; }
    public void setServiceId(String serviceId) { this.serviceId = serviceId; }

    public int getRating() { return rating; }
    public void setRating(int rating) { this.rating = rating; }

    public String getComment() { return comment; }
    public void setComment(String comment) { this.comment = comment; }
}
