package sensor;

public class Location {

	private double latitude;
	private double longitude;
	
    public Location(double latitude, double longitude){
        this.latitude = latitude;
        this.longitude = longitude;
    }  // constructor

    public double getLatitude() {
		return latitude;
	} //getLatitude

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	} //setLatitude

	public double getLongitude() {
		return longitude;
	} //getLongitude

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	} //setLongitude
}
