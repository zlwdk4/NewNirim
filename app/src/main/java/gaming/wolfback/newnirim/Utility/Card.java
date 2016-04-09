package gaming.wolfback.newnirim.Utility;

/**
 * Created by ZWolf on 2/19/2016.
 */
public class Card {
    private String type, color;
    private int id;
    private Boolean isCardDrawn, isCardPlayed, isCardDiscarded;


    public Boolean getIsCardDrawn() {
        return isCardDrawn;
    }
//
    public void setIsCardDrawn(Boolean isCardDrawn) {
        this.isCardDrawn = isCardDrawn;
    }

    public Boolean getIsCardPlayed() {
        return isCardPlayed;
    }

    public void setIsCardPlayed(Boolean isCardPlayed) {
        this.isCardPlayed = isCardPlayed;
    }

    public Boolean getIsCardDiscarded() {
        return isCardDiscarded;
    }

    public void setIsCardDiscarded(Boolean isCardDiscarded) {
        this.isCardDiscarded = isCardDiscarded;
    }


    public int getId() {
        return id;
    }

    public String getType() {
        return type;
    }

    public String getColor() {
        return color;
    }

    public Card(int idCard, String cardColor, String cardType) {
        this.id = idCard;
        this.color = cardColor;
        this.type = cardType;
        this.isCardDrawn = false;
        this.isCardPlayed = false;
        this.isCardDiscarded = false;

    }
    public Card(){}

}
