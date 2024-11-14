package com.vksdeveloperblog.ws.clients.photowebclient.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vksdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;

@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums(Model model) {
        List<AlbumRest> albums = new ArrayList<AlbumRest>(){
            {
                add(new AlbumRest().setAlbumId("1").setAlbumTitle("Title 1").setAlbumUrl("http://localhost:8082/albums/1"));
                add(new AlbumRest().setAlbumId("2").setAlbumTitle("Title 2").setAlbumUrl("http://localhost:8082/albums/2"));
            }
        };

        model.addAttribute("albums", albums);
        return "albums";
    }
}
