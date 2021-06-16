package production.jattbrand.musicapp.modals;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SongDetail {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("cover")
    @Expose
    private String cover;
    @SerializedName("details")
    @Expose
    private List<Detail> details = null;
    @SerializedName("download_links")
    @Expose
    private List<DownloadLink> downloadLinks = null;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public List<Detail> getDetails() {
        return details;
    }

    public void setDetails(List<Detail> details) {
        this.details = details;
    }

    public List<DownloadLink> getDownloadLinks() {
        return downloadLinks;
    }

    public void setDownloadLinks(List<DownloadLink> downloadLinks) {
        this.downloadLinks = downloadLinks;
    }

    @Override
    public String toString() {
        return "SongDetail{" +
                "name='" + name + '\'' +
                ", cover='" + cover + '\'' +
                ", details=" + details +
                ", downloadLinks=" + downloadLinks +
                '}';
    }


    public class Detail {

        @SerializedName("SINGERS")
        @Expose
        private String sINGERS;
        @SerializedName("LYRICIST")
        @Expose
        private String lYRICIST;
        @SerializedName("COMPOSER")
        @Expose
        private String cOMPOSER;

        public String getSINGERS() {
            return sINGERS;
        }

        public void setSINGERS(String sINGERS) {
            this.sINGERS = sINGERS;
        }

        public String getLYRICIST() {
            return lYRICIST;
        }

        public void setLYRICIST(String lYRICIST) {
            this.lYRICIST = lYRICIST;
        }

        public String getCOMPOSER() {
            return cOMPOSER;
        }

        public void setCOMPOSER(String cOMPOSER) {
            this.cOMPOSER = cOMPOSER;
        }

        @Override
        public String toString() {
            return "Detail{" +
                    "sINGERS='" + sINGERS + '\'' +
                    ", lYRICIST='" + lYRICIST + '\'' +
                    ", cOMPOSER='" + cOMPOSER + '\'' +
                    '}';
        }
    }

    public class DownloadLink {

        @SerializedName("48")
        @Expose
        private String _48;
        @SerializedName("128")
        @Expose
        private String _128;
        @SerializedName("192")
        @Expose
        private String _192;
        @SerializedName("320")
        @Expose
        private String _320;

        public String get48() {
            return _48;
        }

        public void set48(String _48) {
            this._48 = _48;
        }

        public String get128() {
            return _128;
        }

        public void set128(String _128) {
            this._128 = _128;
        }

        public String get192() {
            return _192;
        }

        public void set192(String _192) {
            this._192 = _192;
        }

        public String get320() {
            return _320;
        }

        public void set320(String _320) {
            this._320 = _320;
        }

        @Override
        public String toString() {
            return "DownloadLink{" +
                    "_48='" + _48 + '\'' +
                    ", _128='" + _128 + '\'' +
                    ", _192='" + _192 + '\'' +
                    ", _320='" + _320 + '\'' +
                    '}';
        }
    }

}