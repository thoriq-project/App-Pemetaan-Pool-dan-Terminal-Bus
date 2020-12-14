package com.example.poolbismapping.Model;

public class Terminal {

    private String tid, nama, alamat, kecamatan, lat, longi, akap, dalkot, jabo, deskripsi, trayek, image;

    public Terminal(){}

    public Terminal (String tid, String nama, String alamat, String kecamatan, String lat, String longi, String akap, String dalkot, String jabo, String deskripsi, String trayek, String image){

        this.tid = tid;
        this.nama = nama;
        this.alamat = alamat;
        this.kecamatan = kecamatan;
        this.lat = lat;
        this.longi = longi;
        this.akap = akap;
        this.dalkot = dalkot;
        this.jabo = jabo;
        this.deskripsi = deskripsi;
        this.trayek = trayek;
        this.image = image;

    }

    public String getTid(){
        return tid;
    }

    public void setTid(String tid){

        this.tid = tid;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLongi() {
        return longi;
    }

    public void setLongi(String longi) {
        this.longi = longi;
    }

    public String getAkap() {
        return akap;
    }

    public void setAkap(String akap) {
        this.akap = akap;
    }

    public String getDalkot() {
        return dalkot;
    }

    public void setDalkot(String dalkot) {
        this.dalkot = dalkot;
    }

    public String getJabo() {
        return jabo;
    }

    public void setJabo(String jabo) {
        this.jabo = jabo;
    }

    public String getDeskripsi() {
        return deskripsi;
    }

    public void setDeskripsi(String deskripsi) {
        this.deskripsi = deskripsi;
    }

    public String getTrayek() {
        return trayek;
    }

    public void setTrayek(String trayek) {
        this.trayek = trayek;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }
}
