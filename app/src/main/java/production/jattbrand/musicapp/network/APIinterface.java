package production.jattbrand.musicapp.network;

import io.reactivex.Observable;
import production.jattbrand.musicapp.modals.BasicSongDetail;
import production.jattbrand.musicapp.modals.HomeDetails;
import production.jattbrand.musicapp.modals.SongDetail;
import production.jattbrand.musicapp.modals.TopSongResponse;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface APIinterface {


    //s = punjabi | hindi & c = recommended | top50
    @GET("getTopSongs.php")
    Observable<TopSongResponse> getTopSongs(@Query("language") String language,@Query("category") String category);

    @GET("getSongDetails.php")
    Observable<SongDetail> getSongDetails(@Query("name") String name, @Query("cover") String cover, @Query("page") String page);

    @GET("getHomeData.php")
    Observable<HomeDetails> getHomeDetails();
}