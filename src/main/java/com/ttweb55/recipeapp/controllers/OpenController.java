package com.ttweb55.recipeapp.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ttweb55.recipeapp.models.*;
import com.ttweb55.recipeapp.services.AvatarService;
import com.ttweb55.recipeapp.services.RoleService;
import com.ttweb55.recipeapp.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.ui.ModelMap;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import springfox.documentation.annotations.ApiIgnore;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * The class allows access to endpoints that are open to all users regardless of authentication status.
 * Its most important function is to allow a person to create their own username
 */
@RestController
public class OpenController
{
    /**
     * A method in this controller adds a new user to the application so needs access to User Services to do this.
     */
    @Autowired
    private UserService userService;

    /**
     * A method in this controller adds a new user to the application with the role User so needs access to Role Services to do this.
     */
    @Autowired
    private RoleService roleService;

    @Autowired
    private AvatarService avatarService;

    /**
     * This endpoint always anyone to create an account with the default role of USER. That role is hardcoded in this method.
     *
     * @param httpServletRequest the request that comes in for creating the new user
     * @param newMinUser         A special minimum set of data that is needed to create a new user
     * @return The token access and other relevant data to token access. Status of CREATED. The location header to look up the new user.
     * @throws URISyntaxException we create some URIs during this method. If anything goes wrong with that creation, an exception is thrown.
     */
    @PostMapping(value = "/api/auth/register",
        consumes = {"application/json"},
        produces = {"application/json"})
    public ResponseEntity<?> addSelf(
        HttpServletRequest httpServletRequest,
        @Valid
        @RequestBody
            UserMinimum newMinUser)
        throws
        URISyntaxException
    {
        // Create the user
        User newUser = new User();

        newUser.setUsername(newMinUser.getUsername());
        newUser.setPassword(newMinUser.getPassword());
        newUser.setEmail(newMinUser.getEmail());

        // add the default role of user
        Set<UserRoles> newRoles = new HashSet<>();
        newRoles.add(new UserRoles(newUser,
            roleService.findByName("user")));
        newUser.setRoles(newRoles);

        newUser = userService.save(newUser);

        // set the location header for the newly created resource
        // The location comes from a different controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/api/user/{userId}")
            .buildAndExpand(newUser.getUserid())
            .toUri();
        responseHeaders.setLocation(newUserURI);

        // return the access token
        // To get the access token, surf to the endpoint /login just as if a client had done this.
        RestTemplate restTemplate = new RestTemplate();
        String requestURI = "http://localhost" + ":" + httpServletRequest.getLocalPort() + "/login";

        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(acceptableMediaTypes);
        headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
            System.getenv("OAUTHCLIENTSECRET"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type",
            "password");
        map.add("scope",
            "read write trust");
        map.add("username",
            newMinUser.getUsername());
        map.add("password",
            newMinUser.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
            headers);

        String theToken = restTemplate.postForObject(requestURI,
            request,
            String.class);

        return new ResponseEntity<>(theToken,
            responseHeaders,
            HttpStatus.CREATED);
    }

    /**
     * Prevents no favicon.ico warning from appearing in the logs. @ApiIgnore tells Swagger to ignore documenting this as an endpoint.
     */
    @ApiIgnore
    @GetMapping("favicon.ico")
    public void returnNoFavicon()
    {

    }

    @PostMapping(value = "/api/auth/register2",
        consumes = "multipart/form-data")
    public ResponseEntity<?> addSelf2(
            HttpServletRequest httpServletRequest,
            @RequestBody
            @ModelAttribute UserMinimumWithAvatar newMinUser
            ) throws JsonProcessingException {
        User newUser = new User();

        newUser.setUsername(newMinUser.getUsername());
        newUser.setPassword(newMinUser.getPassword());
        newUser.setEmail(newMinUser.getEmail());

        // add the default role of user
        Set<UserRoles> newRoles = new HashSet<>();
        newRoles.add(new UserRoles(newUser,
                roleService.findByName("user")));
        newUser.setRoles(newRoles);

        // handle avatar
        Avatar newUserAvatar = avatarService.save(newMinUser.getAvatar());

        newUser = userService.save(newUser);

        // set the location header for the newly created resource
        // The location comes from a different controller!
        HttpHeaders responseHeaders = new HttpHeaders();
        URI newUserURI = ServletUriComponentsBuilder.fromUriString(httpServletRequest.getServerName() + ":" + httpServletRequest.getLocalPort() + "/api/user/{userId}")
                .buildAndExpand(newUser.getUserid())
                .toUri();
        responseHeaders.setLocation(newUserURI);

        // return the access token
        // To get the access token, surf to the endpoint /login just as if a client had done this.
        RestTemplate restTemplate = new RestTemplate();
        String requestURI = "http://localhost" + ":" + httpServletRequest.getLocalPort() + "/api/auth/login";

        List<MediaType> acceptableMediaTypes = new ArrayList<>();
        acceptableMediaTypes.add(MediaType.APPLICATION_JSON);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
        headers.setAccept(acceptableMediaTypes);
        headers.setBasicAuth(System.getenv("OAUTHCLIENTID"),
                System.getenv("OAUTHCLIENTSECRET"));

        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("grant_type",
                "password");
        map.add("scope",
                "read write trust");
        map.add("username",
                newMinUser.getUsername());
        map.add("password",
                newMinUser.getPassword());

        HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map,
                headers);

        String theToken = restTemplate.postForObject(requestURI,
                request,
                String.class);

        Token userToken = new ObjectMapper().readValue(theToken, Token.class);
        UserWithToken userResponse = new UserWithToken(newUser, userToken);
        return new ResponseEntity<>(userResponse,
                responseHeaders,
                HttpStatus.CREATED);
    }
}
