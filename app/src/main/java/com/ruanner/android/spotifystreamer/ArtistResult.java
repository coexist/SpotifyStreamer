package com.ruanner.android.spotifystreamer;

import kaaes.spotify.webapi.android.models.Artist;

public class ArtistResult {
    String name;
    String imageUrl;
    String id;

    public ArtistResult(Artist artist) {
        this.name = artist.name;
        this.id = artist.id;
        this.imageUrl = artist.images.get(0).url;
    }
}
