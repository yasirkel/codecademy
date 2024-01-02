package contentItem;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ContentItem {
    private int contentItemID;
    private LocalDate publicationDate;
    private String status;

    public ContentItem() {
        this.contentItemID = contentItemID;
    }

    public int getContentItemID() {
        return contentItemID;
    }

    public boolean checkContentItemID(int inputContentItemID) {
        if (inputContentItemID == contentItemID) {
            return true;
        } else {
            return false;
        }
    }

    public void setContentItemID(int contentItemID) {
        this.contentItemID = contentItemID;
    }

    public LocalDate getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(LocalDate publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

}