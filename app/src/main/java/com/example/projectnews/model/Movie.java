package com.example.projectnews.model;

import java.util.Objects;

public class Movie {
    private int Id;
    private String Category;
    private String Title;
    private String Content;
    private String ImageLink;
    private String Author;
    private String CreateDate;

    public Movie() {}

    public Movie(int ID, String category, String title, String content, String imageLink, String author, String createDate) {
        Id = ID;
        Category = category;
        Title = title;
        Content = content;
        ImageLink = imageLink;
        Author = author;
        CreateDate = createDate;
    }

    public int getID() {
        return Id;
    }

    public void setID(int ID) {
        this.Id = ID;
    }

    public String getCategory() {
        return Category;
    }

    public void setCategory(String category) {
        Category = category;
    }

    public String getTitle() {
        return Title;
    }

    public void setTitle(String title) {
        Title = title;
    }

    public String getContent() {
        return Content;
    }

    public void setContent(String content) {
        Content = content;
    }

    public String getImageLink() {
        return ImageLink;
    }

    public void setImageLink(String imageLink) {
        ImageLink = imageLink;
    }

    public String getAuthor() {
        return Author;
    }

    public void setAuthor(String author) {
        Author = author;
    }

    public String getCreateDate() {
        return CreateDate;
    }

    public void setCreateDate(String createDate) {
        CreateDate = createDate;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "Id=" + Id +
                ", Category='" + Category + '\'' +
                ", Title='" + Title + '\'' +
                ", Content='" + Content + '\'' +
                ", ImageLink='" + ImageLink + '\'' +
                ", Author='" + Author + '\'' +
                ", CreateDate='" + CreateDate + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return Id == movie.Id && Objects.equals(Category, movie.Category) && Objects.equals(Title, movie.Title) && Objects.equals(Content, movie.Content) && Objects.equals(ImageLink, movie.ImageLink) && Objects.equals(Author, movie.Author) && Objects.equals(CreateDate, movie.CreateDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(Id, Category, Title, Content, ImageLink, Author, CreateDate);
    }
}
