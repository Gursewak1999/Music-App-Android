package production.jattbrand.musicapp.modals;


import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;


public class TopSongResponse {

    @SerializedName("error")
    @Expose
    private Boolean error;
    @SerializedName("ar")
    @Expose
    private List<BasicSongDetail> ar = null;

    public TopSongResponse() {
    }

    public Boolean getError() {
        return error;
    }

    public void setError(Boolean error) {
        this.error = error;
    }

    public List<BasicSongDetail> getAr() {
        return ar;
    }

    public void setAr(List<BasicSongDetail> ar) {
        this.ar = ar;
    }

    @Override
    public String toString() {
        return "TopSongResponse{" +
                "error=" + error +
                ", ar=" + ar +
                '}';
    }
}