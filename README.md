# Music_Advisor
Personal music advisor that makes preference-based suggestions and even shares links to new releases and featured playlists. Program is based on HTTP client-server communication with Spotify REST API. To authenticate concrete user with Spotify was used OAuth protocol (basic information [here](https://www.youtube.com/watch?v=CPbvxxslDTU&ab_channel=InterSystemsLearningServices)). To parse JSON request from server was used [GSON library](https://howtodoinjava.com/gson/gson-jsonparser)

## Commands
To perform any actions other than authorization require the user has to be authorized with Spotify.

* `auth` Provides a link to the Spotify authorization page and waits until user confirms or rejects the authorization.
* `new Shows` list of new albums with artists and links on Spotify.
* `featured Shows` list of Spotify featured playlists with their links fetched from API.
* `categories` Shows list of all available categories on Spotify (just their names).
* `playlists` C_NAME where C_NAME - name of category. List contains playlists of this category and their links on Spotify.

## Output Example
```
auth
https://accounts.spotify.com/authorize?client_id=1ac1274ae76e4beba32f969999b1392d&redirect_uri=http://localhost:8080&response_type=code
waiting for code...
code=AQDKpb9N0SJ3fQOfvnLoW-YjoLQODSROOJxJzhgI8aSekMO0S9q2splpWwgWT-Vkfa6AL8kd85CsmN4__jcsg6L8SRAyuO99-yTbG6mOAVdCJqDQeLF_T2uCtEgfh5IJe6xODtXbPK_StBKbBBRFrY30WciXdnr4PQ
code received
V: 
making http request for access_token...
response: 
BQDI8YElg71WkFmYMfCW1z72tC_4_IdyaC39YcIOR5yvuWKBNfrdXdMdkpMQrJ0MgqCRvivmB6xopk38-gBb6yeGDqfN23-Xc8OhK-wf9ihMmEr0GlBgQdygXDVSPsAsF7nfhJitNA
---SUCCESS---
featured

Summer Throwbacks
https://open.spotify.com/playlist/37i9dQZF1DWZg863fGtALu


RapCaviar
https://open.spotify.com/playlist/37i9dQZF1DX0XUsuxWHRQd


Bliss
https://open.spotify.com/playlist/37i9dQZF1DX4bSrsRWE9cd


Gold Edition
https://open.spotify.com/playlist/37i9dQZF1DWXnexX7CktaI


Summer Hits of the 90s
https://open.spotify.com/playlist/37i9dQZF1DX8SIpKv9qw6x


Guilty Pleasures
https://open.spotify.com/playlist/37i9dQZF1DX4pUKG1kS0Ac


All Out 10s
https://open.spotify.com/playlist/37i9dQZF1DX5Ejj0EkURtP


Lush Lofi
https://open.spotify.com/playlist/37i9dQZF1DXc8kgYqQLMfH


Rock Classics
https://open.spotify.com/playlist/37i9dQZF1DWXRqgorJj26U


Peaceful Piano
https://open.spotify.com/playlist/37i9dQZF1DX4sWSpwq3LiO


Dance Classics
https://open.spotify.com/playlist/37i9dQZF1DX8a1tdzq5tbM


I Love My '90s Hip-Hop
https://open.spotify.com/playlist/37i9dQZF1DX186v583rmzp

categories
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
RADAR
Alternative
Tokyo
Romance
Focus
Kids & Family
Dance/Electronic
Frequency
playlist Romance
playlists Romance
Unknown category name.
playlists Rock

Tylko Polski Rock
https://open.spotify.com/playlist/37i9dQZF1DX4x0y4AP3Q3A


Full Volume
https://open.spotify.com/playlist/37i9dQZF1DWUv0cTKdT8jJ


Rock Classics
https://open.spotify.com/playlist/37i9dQZF1DWXRqgorJj26U


Polski Rock: Legendy
https://open.spotify.com/playlist/37i9dQZF1DX7S9eVfUJ1bm


Rockowe hymny
https://open.spotify.com/playlist/37i9dQZF1DWUiWDS0oxpZg


Walk Like A Badass
https://open.spotify.com/playlist/37i9dQZF1DX1tyCD9QhIWF


Soft Rock
https://open.spotify.com/playlist/37i9dQZF1DX6xOPeSOGone


Legendary
https://open.spotify.com/playlist/37i9dQZF1DWWGFQLoP9qlv


80s Rock Anthems
https://open.spotify.com/playlist/37i9dQZF1DX1spT6G94GFC


00s Rock Anthems
https://open.spotify.com/playlist/37i9dQZF1DX3oM43CtKnRV


90s Rock Anthems
https://open.spotify.com/playlist/37i9dQZF1DX1rVvRgjX59F


Indie Rock Club
https://open.spotify.com/playlist/37i9dQZF1DX35DWKgAk2B5


Just Rock!
https://open.spotify.com/playlist/37i9dQZF1DX2IvZJK5xwFt


Rock This
https://open.spotify.com/playlist/37i9dQZF1DXcF6B6QPhFDv


Pure Rock & Roll
https://open.spotify.com/playlist/37i9dQZF1DWWRktbhJiuqL


Yacht Rock
https://open.spotify.com/playlist/37i9dQZF1DXb3m918yXHxA


Grunge Forever
https://open.spotify.com/playlist/37i9dQZF1DX11ghcIxjcjE


80s Soft Rock
https://open.spotify.com/playlist/37i9dQZF1DXccljMjoDjBT


70s Rock Anthems
https://open.spotify.com/playlist/37i9dQZF1DWWwzidNQX6jx


One More Rep
https://open.spotify.com/playlist/37i9dQZF1DX5OUjSS1OMgV


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

