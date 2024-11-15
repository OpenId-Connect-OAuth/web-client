package com.vksdeveloperblog.ws.clients.photowebclient.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.vksdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;

@Controller
public class AlbumsController {

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {
        System.out.println("Principal: " + principal);

        OidcIdToken uIdToken = principal.getIdToken();
        System.out.println("Token: " + uIdToken.getTokenValue());
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
