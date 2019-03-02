import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	public EfficientMarkov(){
		this(3);
	}
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for (int i=0; i<=myText.length() - myOrder; i++) {
			myMap.putIfAbsent(myText.substring(i,i+myOrder), new ArrayList<String>());
			if(i==myText.length() - myOrder) {
				myMap.get(myText.substring(i+myOrder)).add(PSEUDO_EOS);
			}
			else {
				myMap.get(myText.substring(i+myOrder)).add(myText.substring(i+myOrder, i+myOrder+1));
			}		
		}
	}
	@Override
	public ArrayList<String> getFollows(String key) {
		if (! myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}
		return myMap.get(key);
	}
}	
