package Exercises.Advanced.LibrarySystem;

public abstract class LibraryItem {
    protected String id;
    protected String title;
    protected int publicationYear;
    protected boolean isAvailble;

    public LibraryItem(String id, String title, int publicationYear, boolean isAvailble) {
        this.id = id;
        this.title = title;
        this.publicationYear = publicationYear;
        this.isAvailble = true;

    }

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public int getPublicationYear() {
        return publicationYear;
    }

    public boolean getIsavailble() {
        return isAvailble;
    }

    public abstract double calculateLateFee(int daysLate);

    public abstract String getItemDetails();

    public boolean borrowItem() {
        if (!isAvailble) {
            throw new IllegalStateException("Da muon");
        }
        isAvailble = false;
        return true;
    }

    public boolean returnItem() {
        if (isAvailble) {
            throw new IllegalStateException("Item is not currently borrowed");

        }
        isAvailble = true;
        return true;

    }

}
