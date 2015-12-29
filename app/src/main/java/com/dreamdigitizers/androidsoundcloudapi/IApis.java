package com.dreamdigitizers.androidsoundcloudapi;

import com.dreamdigitizers.androidsoundcloudapi.models.App;
import com.dreamdigitizers.androidsoundcloudapi.models.Comment;
import com.dreamdigitizers.androidsoundcloudapi.models.Group;
import com.dreamdigitizers.androidsoundcloudapi.models.Playlist;
import com.dreamdigitizers.androidsoundcloudapi.models.Token;
import com.dreamdigitizers.androidsoundcloudapi.models.Track;
import com.dreamdigitizers.androidsoundcloudapi.models.User;
import com.dreamdigitizers.androidsoundcloudapi.models.WebProfile;

import java.util.List;

import retrofit.Call;
import retrofit.http.Field;
import retrofit.http.GET;
import retrofit.http.POST;
import retrofit.http.Path;
import retrofit.http.Query;

interface IApis {
    //-----------------Token-----------------//
    @POST("/oauth2/token")
    Call<Token> token(
            @Field("client_id") String pClientId,
            @Field("client_secret") String pClientSecret,
            @Field("redirect_uri") String pRedirectUri,
            @Field("grant_type") String pGrantType,
            @Field("code") String pCode);

    //-----------------User-----------------//
    @GET("/users")
    Call<List<User>> users(@Query("q") String pQ);

    @GET("/users/{id}")
    Call<User> user(@Path("id") int pId);

    @GET("/users/{id}/tracks")
    Call<List<Track>> userTracks(@Path("id") int pId);

    @GET("/users/{id}/playlists")
    Call<List<Playlist>> userPlaylists(@Path("id") int pId);

    @GET("/users/{id}/comments")
    Call<List<Comment>> userComments(@Path("id") int pId);

    @GET("/users/{id}/groups")
    Call<List<Group>> userGroups(@Path("id") int pId);

    @GET("/users/{id}/web-profiles")
    Call<List<WebProfile>> userWebProfiles(@Path("id") int pId);

    @GET("/users/{id}/followings")
    Call<List<User>> userFollowings(@Path("id") int pId);

    @GET("/users/{id}/followers")
    Call<List<User>> userFollowers(@Path("id") int pId);

    @GET("/users/{id}/favorites")
    Call<List<Track>> userFavorites(@Path("id") int pId);

    //-----------------Track-----------------//
    @GET("/tracks")
    Call<List<Track>> tracks(
            @Query("q") String pQ,
            @Query("tags") String pTags,
            @Query("filter") String pFilter,
            @Query("license") String pLicense,
            @Query("bpm[from]") String pBpmFrom,
            @Query("bpm[to]") String pBpmTo,
            @Query("duration[from]") String pDurationFrom,
            @Query("duration[to]") String pDurationTo,
            @Query("created_at[from]") String pCreatedAtFrom,
            @Query("created_at[to]") String pCreatedAtTo,
            @Query("ids") String pIds,
            @Query("genres") String pGenres,
            @Query("types") String pTypes);

    @GET("/tracks/{id}")
    Call<Track> track(@Path("id") int pId);

    @GET("/tracks/{id}/comments")
    Call<List<Comment>> trackComments(@Path("id") int pId);

    @GET("/tracks/{id}/favoriters")
    Call<List<User>> trackFavoriters(@Path("id") int pId);

    //-----------------Playlist-----------------//
    @GET("/playlists")
    Call<List<Playlist>> playlists(@Query("q") String pQ, @Query("representation") String pRepresentation);

    @GET("/playlists/{id}")
    Call<Playlist> playlist(@Path("id") int pId);

    //-----------------Group-----------------//
    @GET("/groups")
    Call<List<Group>> groups(@Query("q") String pQ);

    @GET("/groups/{id}")
    Call<Group> group(@Path("id") int pId);

    @GET("/groups/{id}/moderators")
    Call<List<User>> groupModerators(@Path("id") int pId);

    @GET("/groups/{id}/members")
    Call<List<User>> groupMembers(@Path("id") int pId);

    @GET("/groups/{id}/contributors")
    Call<List<User>> groupContributors(@Path("id") int pId);

    @GET("/groups/{id}/users")
    Call<List<User>> groupUsers(@Path("id") int pId);

    @GET("/groups/{id}/tracks")
    Call<List<Track>> groupTracks(@Path("id") int pId);

    //-----------------Comment-----------------//
    @GET("/comments")
    Call<List<Comment>> comments();

    @GET("/comments/{id}")
    Call<Comment> comment(@Path("id") int pId);

    //-----------------App-----------------//
    @GET("/apps")
    Call<List<App>> apps();

    @GET("/apps/{id}")
    Call<App> app(@Path("id") int pId);

    //-----------------Me-----------------//
    @GET("/me")
    Call<User> me();
}
