/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package model;

/**
 *
 * @author USER
 */
public class Review {
    private String id;
    private Akun user;
    private ServiceLaundry service;
    private int rating;
    private String comment;

    public Review(String id, Akun user, ServiceLaundry service,
                  int rating, String comment) {
        this.id = id;
        this.user = user;
        this.service = service;
        this.rating = rating;
        this.comment = comment;
    }

    public String getId() { 
        return id; 
    }
    
    public Akun getUser() { 
        return user; 
    }
    
    public ServiceLaundry getService() { return service; }

    public int getRating() { 
        return rating; 
    }
    
    public void setRating(int rating) { 
        this.rating = rating; 
    }

    public String getComment() { 
        return comment; 
    }
    public void setComment(String comment) { 
        this.comment = comment; 
    }

    public void displayReview() {
        System.out.println("Review dari: " + user.getNama()
            + " | Rating: " + rating + "/5"
            + " | Komentar: " + comment);
    }
}
