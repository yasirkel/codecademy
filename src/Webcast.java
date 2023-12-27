import java.time.LocalDate;

public class Webcast {
    private String titleWebcast;
    private int lengthWebcast;
    private String datePublication;
    private String URL;
    private String nameSpeaker;
    private String organisationSpeaker;
    private int contentItemID;

    public String getTitleWebcast() {
        return titleWebcast;
    }

    public void setTitleWebcast(String titleWebcast) {
        this.titleWebcast = titleWebcast;
    }

    public int getLengthWebcast() {
        return lengthWebcast;
    }

    public void setLengthWebcast(int lengthWebcast) {
        this.lengthWebcast = lengthWebcast;
    }

    public String getDatePublication() {
        return datePublication;
    }

    public void setDatePublication(String date) {
        this.datePublication = date;
    }

    public String getURL() {
        return URL;
    }

    public void setURL(String uRL) {
        URL = uRL;
    }

    public String getNameSpeaker() {
        return nameSpeaker;
    }

    public void setNameSpeaker(String nameSpeaker) {
        this.nameSpeaker = nameSpeaker;
    }

    public String getOrganisationSpeaker() {
        return organisationSpeaker;
    }

    public void setOrganisationSpeaker(String organisationSpeaker) {
        this.organisationSpeaker = organisationSpeaker;
    }

    public int getContentItemID() {
        return contentItemID;
    }

    public void setContentItemID(int contentItemID) {
        this.contentItemID = contentItemID;
    }

}