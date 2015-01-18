package sdm.sccpms.gift;

import java.util.List;

public interface GiftGivingStrategyInterface {
	public List<Gift> getGifts(List<Gift> gifts, float goodness);
}
