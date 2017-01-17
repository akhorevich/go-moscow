package nortti.ru.gomoscow.models;

import nortti.ru.gomoscow.R;

public class Place {
    String name;
    Double latitude;
    Double longitude;
    int resId;
    String type;
    String description;
    public float radius;

    public Place() {
    }

    public Place(String name, Double latitude, Double longitude, String type, String descr) {
        this.name = name;
        this.latitude = latitude;
        this.longitude = longitude;
        this.type = type;
        this.description = descr;
    }

    public float getRadius() {
        return radius;
    }

    public String getDescription() {
        return description;
    }

    public String getType() {
        return type;
    }

    public String getName() {
        return name;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public int getResId() {

        switch (getType()){
            case "Museum":
                resId = R.drawable.museum;
                break;
            case "Theater":
                resId = R.drawable.theater;
                break;
            case "House":
                resId = R.drawable.home;
                break;
            case "Other":
                resId = R.drawable.info;
                break;

        }
        return resId;
    }

}
