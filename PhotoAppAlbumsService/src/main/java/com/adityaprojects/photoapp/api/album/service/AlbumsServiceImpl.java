package com.adityaprojects.photoapp.api.album.service;


import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;

import com.adityaprojects.photoapp.api.album.data.AlbumsEntity;

@Service
public class AlbumsServiceImpl implements AlbumsService {

    @Override
    public List<AlbumsEntity> getAlbums(String userId) {
        List<AlbumsEntity> returnValue = new ArrayList<>();
        
        // Hardcoded Album data
        AlbumsEntity albumEntity = new AlbumsEntity();
        albumEntity.setUserId(userId);
        albumEntity.setAlbumId("album1Id");
        albumEntity.setDescription("album 1 description");
        albumEntity.setId(1L);
        albumEntity.setName("album 1 name");
        
        AlbumsEntity albumEntity2 = new AlbumsEntity();
        albumEntity2.setUserId(userId);
        albumEntity2.setAlbumId("album2Id");
        albumEntity2.setDescription("album 2 description");
        albumEntity2.setId(2L);
        albumEntity2.setName("album 2 name");
        
        returnValue.add(albumEntity);
        returnValue.add(albumEntity2);
        
        return returnValue;
    }
    
}
