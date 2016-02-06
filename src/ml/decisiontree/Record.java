package ml.decisiontree;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Record {

	private static Set<String> determiningAttributes = null;
	private List<Attribute> attributes = new ArrayList<>();
	private Map<String, String> attributeMap = new HashMap<>();

	public static Set<String> getDeterminingAttributes() {
		return determiningAttributes;
	}

	public Record(List<Attribute> attributes) {
		super();
		this.attributes = attributes;
		initMap(attributes);

	}

	private void initMap(List<Attribute> attributes) {
		int size = attributes.size();
		for (int i = 0; i < size; i += 1) {
			Attribute attribute = attributes.get(i);
			attributeMap.put(attribute.getName(), attribute.getValue());

		}

		initDeterminingAttributes(attributes);

	}

	private void initDeterminingAttributes(List<Attribute> attributes) {
		if (determiningAttributes == null) {
			String targetAttrName = attributes.get(attributes.size() - 1)
					.getName();
			Set<String> attributesNames = new HashSet<>(attributeMap.keySet());
			attributesNames.remove(targetAttrName);
			determiningAttributes = attributesNames;
		}
	}

	public Record(String oneline) {
		loadAttributes(oneline);
		initDeterminingAttributes(attributes);
	}

	private void loadAttributes(String oneline) {
		String[] splits = oneline.split(",");
		for (int i = 0; i < splits.length; i++) {
			attributes.add(new Attribute(new Integer(i).toString(), splits[i]
					.trim()));
			attributeMap.put(new Integer(i).toString(), splits[i].trim());
		}
	}

	public List<Attribute> getAttributes() {
		return attributes;
	}

	public String getAttribute(String attribute) {
		return attributeMap.get(attribute);
	}

	public Attribute getTargetAttribute() {
		return attributes.get(attributes.size() - 1);
	}

}
