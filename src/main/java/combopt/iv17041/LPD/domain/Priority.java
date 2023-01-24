package combopt.iv17041.LPD.domain;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "rank")
public class Priority {
    private int rank;

    public Priority() {}

    public Priority(int rank) {
        setRank(rank);
    }


    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    @Override
    public String toString() {
        return String.valueOf(this.getRank());
    }


}
