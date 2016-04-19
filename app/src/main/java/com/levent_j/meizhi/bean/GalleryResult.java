package com.levent_j.meizhi.bean;

/**
 * Created by levent_j on 16-4-19.
 */
public class GalleryResult {

    /**
     * count : 1810
     * fcount : 0
     * galleryclass : 1
     * id : 18
     * img : /ext/150714/e76407c9a23da57a0f30690aa7917f3e.jpg
     * list : [{"gallery":18,"id":260,"src":"/ext/150714/e76407c9a23da57a0f30690aa7917f3e.jpg"},{"gallery":18,"id":261,"src":"/ext/150714/6ca012a9644e7bed72e2f7cfcbd92cb9.jpg"},{"gallery":18,"id":262,"src":"/ext/150714/62c84af13e36cbb13c902a58a8e8ce81.jpg"},{"gallery":18,"id":263,"src":"/ext/150714/7cf66ef7494e13d3bb09a16bb7170328.jpg"},{"gallery":18,"id":264,"src":"/ext/150714/5b1c5cb5419f46d48ae768d569d69b37.jpg"},{"gallery":18,"id":265,"src":"/ext/150714/98b9b436a060f67b3cbbab15e0bb5d51.jpg"}]
     * rcount : 0
     * size : 6
     * status : true
     * time : 1436878500000
     * title : MiStar苏小曼姿势性感诱人私房照
     * url : http://www.tngou.net/tnfs/show/18
     */

    private int count;
    private int fcount;
    private int galleryclass;
    private int id;
    private String img;
    private int rcount;
    private int size;
    private boolean status;
    private long time;
    private String title;
    private String url;
    private Picture[] list;


    public Picture[] getList() {
        return list;
    }

    public void setList(Picture[] list) {
        this.list = list;
    }


//    public static final Parcelable.Creator<GalleryResult> CREATOR = new Creator<GalleryResult>() {
//        @Override
//        public GalleryResult createFromParcel(Parcel source) {
//            GalleryResult galleryResult = new GalleryResult();
//            galleryResult.count = source.readInt();
//            galleryResult.fcount = source.readInt();
//            galleryResult.galleryclass = source.readInt();
//            galleryResult.id = source.readInt();
//            galleryResult.img = source.readString();
//            galleryResult.rcount = source.readInt();
//            galleryResult.size = source.readInt();
//            galleryResult.status = source.readByte()!=0;
//            galleryResult.time = source.readLong();
//            galleryResult.title = source.readString();
//            galleryResult.url = source.readString();
//            return galleryResult;
//        }
//
//        @Override
//        public GalleryResult[] newArray(int size) {
//            return new GalleryResult[size];
//        }
//    };

//    @Override
//    public int describeContents() {
//        return 0;
//    }
//
//
//    @Override
//    public void writeToParcel(Parcel dest, int flags) {
//        dest.writeInt(count);
//        dest.writeInt(fcount);
//        dest.writeInt(galleryclass);
//        dest.writeInt(id);
//        dest.writeInt(rcount);
//        dest.writeInt(size);
//        dest.writeString(img);
//        dest.writeString(title);
//        dest.writeString(url);
//        dest.writeByte((byte) (status ? 1 : 0));
//        dest.writeLong(time);
//    }


    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getFcount() {
        return fcount;
    }

    public void setFcount(int fcount) {
        this.fcount = fcount;
    }

    public int getGalleryclass() {
        return galleryclass;
    }

    public void setGalleryclass(int galleryclass) {
        this.galleryclass = galleryclass;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public int getRcount() {
        return rcount;
    }

    public void setRcount(int rcount) {
        this.rcount = rcount;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public long getTime() {
        return time;
    }

    public void setTime(long time) {
        this.time = time;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }


}
