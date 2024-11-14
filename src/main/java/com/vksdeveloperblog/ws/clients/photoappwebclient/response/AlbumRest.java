package com.vksdeveloperblog.ws.clients.photoappwebclient.response;

public class AlbumRest {
    private String userId;
    private String albumId;
    private String albumTitle;
    private String albumDescription;
    private String albumUrl;

    public String getUserId() {
        return this.userId;
    }

    public AlbumRest setUserId(String userId) {
        this.userId = userId;
        return this;
    }

    public String getAlbumId() {
        return this.albumId;
    }

    public AlbumRest setAlbumId(String albumId) {
        this.albumId = albumId;
        return this;
    }

    public String getAlbumTitle() {
        return this.albumTitle;
    }

    public AlbumRest setAlbumTitle(String albumTitle) {
        this.albumTitle = albumTitle;
        return this;
    }

    public String getAlbumDescription() {
        return this.albumDescription;
    }

    public AlbumRest setAlbumDescription(String albumDescription) {
        this.albumDescription = albumDescription;
        return this;
    }

    public String getAlbumUrl() {
        return this.albumUrl;
    }

    public AlbumRest setAlbumUrl(String albumUrl) {
        this.albumUrl = albumUrl;
        return this;
    }

}
