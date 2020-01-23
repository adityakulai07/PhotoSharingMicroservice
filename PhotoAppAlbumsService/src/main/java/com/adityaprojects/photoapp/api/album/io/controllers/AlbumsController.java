package com.adityaprojects.photoapp.api.album.io.controllers;


import java.util.ArrayList;
import java.util.List;
import org.modelmapper.ModelMapper;
import java.lang.reflect.Type;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adityaprojects.photoapp.api.album.data.AlbumsEntity;
import com.adityaprojects.photoapp.api.album.service.AlbumsService;
import com.adityaprojects.photoapp.api.album.ui.model.AlbumsResponseModel;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@RequestMapping("/users/{id}/albums")
public class AlbumsController {
    
    @Autowired
    AlbumsService albumsService;
    
    
    Logger logger = LoggerFactory.getLogger(this.getClass());
    
    @GetMapping( 
            produces = { 
                MediaType.APPLICATION_JSON_VALUE,
                MediaType.APPLICATION_XML_VALUE,
            })
    public List<AlbumsResponseModel> userAlbums(@PathVariable String id) {

        List<AlbumsResponseModel> returnValue = new ArrayList<>();
        
        List<AlbumsEntity> albumsEntities = albumsService.getAlbums(id);
        
        if(albumsEntities == null || albumsEntities.isEmpty())
        {
            return returnValue;
        }
        
        Type listType = new TypeToken<List<AlbumsResponseModel>>(){}.getType();
 
        returnValue = new ModelMapper().map(albumsEntities, listType);
        logger.info("Returning " + returnValue.size() + " albums");
        return returnValue;
    }
}