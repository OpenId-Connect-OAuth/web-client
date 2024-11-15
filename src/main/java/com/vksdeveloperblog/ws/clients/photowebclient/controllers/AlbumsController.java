package com.vksdeveloperblog.ws.clients.photowebclient.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.OAuth2AuthorizedClientService;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.security.oauth2.core.oidc.OidcIdToken;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.client.RestTemplate;

import com.vksdeveloperblog.ws.clients.photoappwebclient.response.AlbumRest;

@Controller
public class AlbumsController {

    @Autowired
    OAuth2AuthorizedClientService oAuth2AuthorizedClientService;

    @Autowired
    RestTemplate restTemplate;

    @GetMapping("/albums")
    public String getAlbums(Model model, @AuthenticationPrincipal OidcUser principal) {
        OAuth2AuthenticationToken oauthtoken = (OAuth2AuthenticationToken)SecurityContextHolder.getContext().getAuthentication();

        OAuth2AuthorizedClient oauth2Client = oAuth2AuthorizedClientService.loadAuthorizedClient(oauthtoken.getAuthorizedClientRegistrationId(), oauthtoken.getName());

        String jwtAccessToken = oauth2Client.getAccessToken().getTokenValue();

        System.out.println("JWT Access Toke: " + jwtAccessToken);

        System.out.println("Principal: " + principal);

        OidcIdToken uIdToken = principal.getIdToken();
        System.out.println("Token: " + uIdToken.getTokenValue());

        // Gateway path
        String url = "http://localhost:8082/albums";

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "Bearer " + jwtAccessToken);
        
        HttpEntity<List<AlbumRest>> httpEntity = new HttpEntity<>(headers);

        ResponseEntity<List<AlbumRest>> responseEntity = restTemplate.exchange(url, HttpMethod.GET, httpEntity, new ParameterizedTypeReference<List<AlbumRest>>() {});

        // List<AlbumRest> albums = new ArrayList<AlbumRest>(){
        //     {
        //         add(new AlbumRest().setAlbumId("1").setAlbumTitle("Title 1").setAlbumUrl("http://localhost:8082/albums/1"));
        //         add(new AlbumRest().setAlbumId("2").setAlbumTitle("Title 2").setAlbumUrl("http://localhost:8082/albums/2"));
        //     }
        // };

        model.addAttribute("albums", responseEntity.getBody());
        return "albums";
    }
}
