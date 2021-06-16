package production.jattbrand.musicapp.modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;

public class HomeDetails {

    @SerializedName("editors_choice")
    @Expose
    private ArrayList<BasicSongDetail> editorsChoice = null;
    @SerializedName("new_release")
    @Expose
    private ArrayList<BasicSongDetail> newRelease = null;
    @SerializedName("punjabi_albums")
    @Expose
    private ArrayList<BasicSongDetail> punjabiAlbums = null;
    @SerializedName("hindi_albums")
    @Expose
    private ArrayList<BasicSongDetail> hindiAlbums = null;
    @SerializedName("hindi_single_tracks")
    @Expose
    private ArrayList<BasicSongDetail> hindiSingleTracks = null;

    public ArrayList<BasicSongDetail> getEditorsChoice() {
        return editorsChoice;
    }

    public void setEditorsChoice(ArrayList<BasicSongDetail> editorsChoice) {
        this.editorsChoice = editorsChoice;
    }

    public ArrayList<BasicSongDetail> getNewRelease() {
        return newRelease;
    }

    public void setNewRelease(ArrayList<BasicSongDetail> newRelease) {
        this.newRelease = newRelease;
    }

    public ArrayList<BasicSongDetail> getPunjabiAlbums() {
        return punjabiAlbums;
    }

    public void setPunjabiAlbums(ArrayList<BasicSongDetail> punjabiAlbums) {
        this.punjabiAlbums = punjabiAlbums;
    }

    public ArrayList<BasicSongDetail> getHindiAlbums() {
        return hindiAlbums;
    }

    public void setHindiAlbums(ArrayList<BasicSongDetail> hindiAlbums) {
        this.hindiAlbums = hindiAlbums;
    }

    public ArrayList<BasicSongDetail> getHindiSingleTracks() {
        return hindiSingleTracks;
    }

    public void setHindiSingleTracks(ArrayList<BasicSongDetail> hindiSingleTracks) {
        this.hindiSingleTracks = hindiSingleTracks;
    }

    @Override
    public String toString() {
        return "HomeDetails{" +
                "editorsChoice=" + editorsChoice +
                ", newRelease=" + newRelease +
                ", punjabiAlbums=" + punjabiAlbums +
                ", hindiAlbums=" + hindiAlbums +
                ", hindiSingleTracks=" + hindiSingleTracks +
                '}';
    }
}
