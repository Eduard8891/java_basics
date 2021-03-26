
public class Connection {
    private Line line;
    private Station station;
    private Line lineFrom;
    private Station stationFrom;
    private Line lineTo;
    private Station stationTo;


    public Line getLine() {
        return line;
    }

    public void setLine(Line line) {
        this.line = line;
    }

    public Station getStation() {
        return station;
    }

    public void setStation(Station station) {
        this.station = station;
    }

    public Connection(Line line, Station station) {
        this.line = line;
        this.station = station;
    }


    public Line getLineFrom() {
        return lineFrom;
    }

    public void setLineFrom(Line lineFrom) {
        this.lineFrom = lineFrom;
    }

    public Station getStationFrom() {
        return stationFrom;
    }

    public void setStationFrom(Station stationFrom) {
        this.stationFrom = stationFrom;
    }

    public Line getLineTo() {
        return lineTo;
    }

    public void setLineTo(Line lineTo) {
        this.lineTo = lineTo;
    }

    public Station getStationTo() {
        return stationTo;
    }

    public void setStationTo(Station stationTo) {
        this.stationTo = stationTo;
    }

    public Connection(Line lineFrom, Station stationFrom, Line lineTo, Station stationTo) {
        this.lineFrom = lineFrom;
        this.stationFrom = stationFrom;
        this.lineTo = lineTo;
        this.stationTo = stationTo;
    }


}
