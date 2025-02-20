package com.example.travellerblog.utils;


import com.example.travellerblog.model.Blog;
import org.apache.commons.io.FilenameUtils;
import org.springframework.data.util.Pair;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class BlogForm {
  
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");

    private String userName;

    private String name;

    private String description;

    private LocalDate date;

    private String dateString;

    private MultipartFile coverImage;

    public BlogForm(Blog blog) {
        this.userName = blog.getUserName();
        this.name = blog.getName();
        this.description = blog.getDescription();
        this.date = blog.getDate();
        this.dateString = blog.getDate().format(formatter);
        this.coverImage = null;
    }

    public BlogForm() {
        this.userName = "";
        this.name = "";
        this.description = "";
        this.dateString = "";
        this.date = null;
        this.coverImage = null;
    }

    public Pair<Integer, String> validateForm () {

        // date is empty
        if (this.date == null) return Pair.of(1, "Date Field is empty");
        // cover Image not provided
        if (this.coverImage == null) return Pair.of(1, "Cover Image not supplied");
        // Character limit for name field exceeds
        if (this.name.length() > 100) return Pair.of(1, "Name Length exceeds 100 character limit");
        // Date validation
        if (this.date.isAfter(LocalDate.now())) return Pair.of(1, "Date exceeds the Current Date");
        // Cover Image File extension check
        if (!FilenameUtils.isExtension(this.coverImage.getOriginalFilename(),
                "jpeg", "jpg", "png")) return Pair.of(1, "Cover Image doesn't match following types : JPEG/JPG/PNG");

        // Checking Size of coverImage (Note: by default getSize() returns in bytes, so we convert it to Mega bytes)
        if (this.coverImage.getSize() * (9.5 * 1e-7) > 5.00) return Pair.of(1, "Cover Image size exceeds 5Mb limit");

        // Default OK validation
        return Pair.of(2, "Your Memory has been Created. Press on View Memories to view");
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public LocalDate getDate() {
        return date;
    }

    public MultipartFile getCoverImage() {
        return coverImage;
    }

    public String getDateString() {
        return dateString;
    }

    public String getFullDateString() {
        DateTimeFormatter fullFormatter = DateTimeFormatter.ofPattern("dd LLLL yyyy");
        return this.date.format(fullFormatter);
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setDateString(String dateString) {
        this.dateString = dateString;
        setDate(LocalDate.parse(dateString, formatter));
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setCoverImage(MultipartFile coverImage) {
        this.coverImage = coverImage;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        BlogForm blogForm = (BlogForm) o;
        return Objects.equals(getName(), blogForm.getName()) && Objects.equals(getDescription(), blogForm.getDescription()) && Objects.equals(getDate(), blogForm.getDate());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getDescription(), getDate());
    }

    @Override
    public String toString() {
        return "BlogForm{" +
                "formatter=" + formatter +
                ", userName='" + userName + '\'' +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", date=" + date +
                ", dateString='" + dateString + '\'' +
                ", coverImage=" + coverImage +
                '}';
    }
}
