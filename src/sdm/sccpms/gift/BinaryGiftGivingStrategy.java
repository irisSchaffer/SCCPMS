package sdm.sccpms.gift;

import java.util.LinkedList;
import java.util.List;

public class BinaryGiftGivingStrategy implements GiftGivingStrategyInterface {

	@Override
	public List<Gift> getGifts(List<Gift> gifts, float goodness) {
		if (goodness >= .5) {
			return gifts;
		} else {
			return new LinkedList<Gift>();
		}
	}

}
