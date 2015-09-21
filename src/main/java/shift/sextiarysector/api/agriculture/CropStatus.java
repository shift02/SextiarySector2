package shift.sextiarysector.api.agriculture;

import shift.sextiarysector.api.season.Season;

public class CropStatus {

    private boolean reHarvest;

    private int days[];

    public Season[] seasons;

    public CropStatus(String farmland, boolean reHarvest, int[] days, Season... seasons) {

        this.days = days;
        this.seasons = seasons;

        if (reHarvest) {
            if (days.length != 4) throw new IllegalArgumentException("Size of days is less  days.length = 4");
        } else {
            if (days.length != 3) throw new IllegalArgumentException("Size of days is less  days.length = 3");
        }

    }

    public int[] getDays() {
        return days;
    }

    private void setDay(int[] day) {
        this.days = day;
    }

    public Season[] getSeason() {
        return seasons;
    }

    public boolean isReHarvest() {
        return this.reHarvest;
    }

}
