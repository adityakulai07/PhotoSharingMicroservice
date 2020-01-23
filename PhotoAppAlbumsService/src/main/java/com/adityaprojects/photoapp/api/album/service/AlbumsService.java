package com.adityaprojects.photoapp.api.album.service;

import java.util.List;

import com.adityaprojects.photoapp.api.album.data.AlbumsEntity;

public interface AlbumsService {
    List<AlbumsEntity> getAlbums(String userId);
}
