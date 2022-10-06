package com.wishihab.weflixjava.model.general.movie;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class MovieReviewResponse implements Serializable {

    @SerializedName("id")
    private Integer id;
    @SerializedName("page")
    private Integer page;
    @SerializedName("results")
    private Results[] results;
    @SerializedName("total_pages")
    private Integer totalPages;
    @SerializedName("total_results")
    private Integer totalResults;

    public static class Results{
        @SerializedName("author")
        private String author;
        @SerializedName("author_details")
        private AuthorDetail authorDetail;
        @SerializedName("content")
        private String content;
        @SerializedName("created_at")
        private String createdAt;
        @SerializedName("id")
        private String id;
        @SerializedName("updated_at")
        private String updatedAt;
        @SerializedName("url")
        private String url;

        public static class AuthorDetail{
            @SerializedName("name")
            private String name;
            @SerializedName("username")
            private String userName;
            @SerializedName("avatar_path")
            private String avatarPath;
            @SerializedName("rating")
            private Double rating;

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getUserName() {
                return userName;
            }

            public void setUserName(String userName) {
                this.userName = userName;
            }

            public String getAvatarPath() {
                return avatarPath;
            }

            public void setAvatarPath(String avatarPath) {
                this.avatarPath = avatarPath;
            }

            public Double getRating() {
                return rating;
            }

            public void setRating(Double rating) {
                this.rating = rating;
            }
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public AuthorDetail getAuthorDetail() {
            return authorDetail;
        }

        public void setAuthorDetail(AuthorDetail authorDetail) {
            this.authorDetail = authorDetail;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreatedAt() {
            return createdAt;
        }

        public void setCreatedAt(String createdAt) {
            this.createdAt = createdAt;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUpdatedAt() {
            return updatedAt;
        }

        public void setUpdatedAt(String updatedAt) {
            this.updatedAt = updatedAt;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Results[] getResults() {
        return results;
    }

    public void setResults(Results[] results) {
        this.results = results;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getTotalResults() {
        return totalResults;
    }

    public void setTotalResults(Integer totalResults) {
        this.totalResults = totalResults;
    }
}
