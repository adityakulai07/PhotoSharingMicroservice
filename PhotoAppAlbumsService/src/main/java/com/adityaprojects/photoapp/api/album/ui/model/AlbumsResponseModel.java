package com.adityaprojects.photoapp.api.album.ui.model;

public class AlbumsResponseModel {
	public class AlbumResponseModel {
	    private String albumId;
	    private String userId; 
	    private String name;
	    public String getAlbumId() {
			return albumId;
		}
		public void setAlbumId(String albumId) {
			this.albumId = albumId;
		}
		public String getUserId() {
			return userId;
		}
		public void setUserId(String userId) {
			this.userId = userId;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
		private String description;

	}
}
