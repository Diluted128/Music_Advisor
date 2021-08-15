# Music_Advisor
Personal music advisor that makes preference-based suggestions and even shares links to new releases and featured playlists. Program is based on HTTP client-server communication with Spotify REST API. To authenticate concrete user with Spotify was used OAuth protocol (basic information [here](https://www.youtube.com/watch?v=CPbvxxslDTU&ab_channel=InterSystemsLearningServices)). To parse JSON request from server was used [GSON library](https://howtodoinjava.com/gson/gson-jsonparser)

## Commands
To perform any actions other than authorization require the user has to be authorized with Spotify.

* `auth` Provides a link to the Spotify authorization page and waits until user confirms or rejects the authorization.
* `new` Shows list of new albums with artists and links on Spotify.
* `featured` Shows list of Spotify featured playlists with their links fetched from API.
* `categories` Shows list of all available categories on Spotify (just their names).
* `playlists` C_NAME where C_NAME - name of category. List contains playlists of this category and their links on Spotify.

## Output Example
```
> auth
https://accounts.spotify.com/authorize?client_id=1ac1274ae76e4beba32f969999b1392d&redirect_uri=http://localhost:8080&response_type=code
waiting for code...
CODE: code=AQDKpb9N0SJ3fQOfvnLoW-YjoLQODSROOJxJzhgI8aSekMO0S9q2splpWwgWT-Vkfa6AL8kd85CsmN4__jcsg6L8SRAyuO99-yTbG6mOAVdCJqDQeLF_T2uCtEgfh5IJe6xODtXbPK_StBKbBBRFrY30WciXdnr4PQ
code received
making http request for access_token...
response: 
TOKEN: BQDI8YElg71WkFmYMfCW1z72tC_4_IdyaC39YcIOR5yvuWKBNfrdXdMdkpMQrJ0MgqCRvivmB6xopk38-gBb6yeGDqfN23-Xc8OhK-wf9ihMmEr0GlBgQdygXDVSPsAsF7nfhJitNA
---SUCCESS---

> featured

Summer Throwbacks
https://open.spotify.com/playlist/37i9dQZF1DWZg863fGtALu


RapCaviar
https://open.spotify.com/playlist/37i9dQZF1DX0XUsuxWHRQd


Bliss
https://open.spotify.com/playlist/37i9dQZF1DX4bSrsRWE9cd


Gold Edition
https://open.spotify.com/playlist/37i9dQZF1DWXnexX7CktaI

...

> categories

Top Lists
Summer
Hip Hop
Pop
Mood
Chill
Party
At Home
Workout
EQUAL
Decades
Rock

 ... 
 
> playlists Rock

Full Volume
https://open.spotify.com/playlist/37i9dQZF1DWUv0cTKdT8jJ


Rock Classics
https://open.spotify.com/playlist/37i9dQZF1DWXRqgorJj26U


Walk Like A Badass
https://open.spotify.com/playlist/37i9dQZF1DX1tyCD9QhIWF


Soft Rock
https://open.spotify.com/playlist/37i9dQZF1DX6xOPeSOGone


Legendary
https://open.spotify.com/playlist/37i9dQZF1DWWGFQLoP9qlv


80s Rock Anthems
https://open.spotify.com/playlist/37i9dQZF1DX1spT6G94GFC

...


```
## Authrization Steps for developers
1. Go to the site of the corresponding API and create the application there to get `client_id` and `client_secret`.
2. After registered the application and received the client_id and client_secret, create an authorization link which will contain, in query parameters, the `client_id`, `redirect_uri` , `response_type`, and `scopes`.
3. The user follows this link and authorizes access, and is redirected back to the developer’s specified redirect_uri with the response.
4. The developer uses this response to get access_token.
## Useful links
[Spotify Authorization Guide](https://developer.spotify.com/documentation/general/guides/authorization-guide/)

[Spotify Dashboard](https://developer.spotify.com/dashboard/applications)

[Spotify Web Api Reference](https://developer.spotify.com/documentation/web-api/reference/)

[HTTP Server](https://examples.javacodegeeks.com/core-java/sun/net-sun/httpserver-net-sun/httpserver-net-sun-httpserver-net-sun/com-sun-net-httpserver-httpserver-example)

