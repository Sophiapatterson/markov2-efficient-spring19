import java.util.*;

public class EfficientWordMarkov extends BaseWordMarkov{
	private Map<WordGram,ArrayList<String>> myMap;

	public EfficientWordMarkov(int order) {
		super(order);
		myMap = new HashMap<>();
			
		}
	public EfficientWordMarkov() {
		this(2);
	}
	@Override
	public void setTraining(String text) {
		myMap.clear();
		myWords = text.split("\\s+");
		for (int i=0; i<=myWords.length-myOrder; i++) {
			WordGram var = new WordGram(myWords, i, myOrder);
			myMap.putIfAbsent(var, new ArrayList<String>());
			if(i==myWords.length-myOrder) {
				myMap.get(var).add(PSEUDO_EOS);
			}
			else {
				myMap.get(var).add(myWords[i+myOrder]);
			}
		}
	}
	@Override
	public ArrayList<String> getFollows(WordGram key){
		if (! myMap.containsKey(key)) {
			throw new NoSuchElementException(key+" not in map");
		}
		else {
			return myMap.get(key);
		}
	}
}
