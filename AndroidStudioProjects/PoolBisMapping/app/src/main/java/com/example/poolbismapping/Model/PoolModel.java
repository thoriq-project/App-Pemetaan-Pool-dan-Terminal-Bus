package com.example.poolbismapping.Model;

public class PoolModel {

    private String pid, kecamatan, image, nama, notelp, harga, lat, longi, alamat, tujuan;

    public PoolModel (){}

    public PoolModel(String pid, String kecamatan, String image, String nama, String notelp, String harga, String lat, String longi, String alamat, String tujuan) {
        this.pid = pid;
        this.kecamatan = kecamatan;
        this.image = image;
        this.nama = nama;
        this.notelp = notelp;
        this.harga = harga;
        this.lat = lat;
        this.longi = longi;
        this.alamat = alamat;
        this.tujuan = tujuan;
    }

    public String getPid() {
        return pid;
    }

    public void setPid(String pid) {
        this.pid = pid;
    }

    public String getKecamatan() {
        return kecamatan;
    }

    public void setKecamatan(String kecamatan) {
        this.kecamatan = kecamatan;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNotelp() {
        return notelp;
    }

    public void setNotelp(String notelp) {
        this.notelp = notelp;
    }

    public String getHarga() {
        return harga;
    }

    public void setHarga(String harga) {
        this.harga = harga;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getTujuan() {
        return tujuan;
    }

    public void setTujuan(String tujuan) {
        this.tujuan = tujuan;
    }
}
