package uk.ac.cityofglasgowcollege.assessment_3;

/**
 * Created by 30055525 on 23/02/2018.
 */

///// TODO: 27/02/2018 Comment code

public class Comments {
    //private properties
    private String username,
            productId,
            comments;

    private int reviewRating;


    //public getter functions
    public final String getUsername() {
        return username;
    }

    //public setter methods
    public void setUsername(final String usernameIn) {
        username = usernameIn;
    }

    public final String getProductId() {
        return productId;
    }

    public void setProductId(final String productIdIn) {
        productId = productIdIn;
    }

    public final String getComments() {
        return comments;
    }

    public void setComments(final String commentsIn) {
        comments = commentsIn;
    }

    public final int getCommentRating() {
        return reviewRating;
    }

    public void setCommentRating(final int reviewRatingIn) {
        reviewRating = reviewRatingIn;
    }
}
