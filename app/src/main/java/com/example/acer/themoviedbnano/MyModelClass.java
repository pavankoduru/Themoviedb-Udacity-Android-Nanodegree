package com.example.acer.themoviedbnano;

class MyModelClass {
    String imgurl,title,overview,otitle,oimgurl,mrating,mreleaseDate;

    public String getOtitle() {
        return otitle;
    }

    public void setOtitle(String otitle) {
        this.otitle = otitle;
    }

    public String getOimgurl() {
        return oimgurl;
    }

    public void setOimgurl(String oimgurl) {
        this.oimgurl = oimgurl;
    }

    public String getImgurl() {
        return imgurl;
    }

    public void setImgurl(String imgurl) {
        this.imgurl = imgurl;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getMrating() {
        return mrating;
    }

    public void setMrating(String mrating) {
        this.mrating = mrating;
    }

    public String getMreleaseDate() {
        return mreleaseDate;
    }

    public void setMreleaseDate(String mreleaseDate) {
        this.mreleaseDate = mreleaseDate;
    }

    public MyModelClass(String imgurl, String title, String overview, String otitle, String oimgurl, String mrating, String mreleaseDate) {
        this.imgurl = imgurl;
        this.title = title;
        this.overview = overview;
        this.otitle = otitle;
        this.oimgurl = oimgurl;
        this.mrating = mrating;
        this.mreleaseDate = mreleaseDate;
    }
}
