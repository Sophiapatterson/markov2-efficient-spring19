import java.util.*;

public class EfficientMarkov extends BaseMarkov {
	private Map<String,ArrayList<String>> myMap;
	
	public EfficientMarkov(){
		this(3);
	}
	
	public EfficientMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
	}
	@Override
	public void setTraining(String text) {
		myText = text;
		myMap.clear();
		for (int i=0; i<=(myText.length() - myOrder); i++) {
			myMap.putIfAbsent(text.substring(i,i+myOrder), new ArrayList<String>());
			if (text.substring(i+myOrder)!= null){
				myMap.get(text.substring(i+myOrder)).add(text.substring(i+myOrder+1));
			}
			else {
				myMap.get(text.substring(i+myOrder)).add(PSEUDO_EOS);
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
