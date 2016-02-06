package ml.decisiontree;

import java.util.ArrayList;
import java.util.List;

public class SampleDistribute {
	private List<Record> sample = new ArrayList<>();
	private int positive;
	private int negative;

	public List<Record> getSample() {
		return sample;
	}

	public int getPositive() {
		return positive;
	}

	public int getNegative() {
		return negative;
	}

	public SampleDistribute() {
		super();

	}

	private boolean isPositive(String result) {
		return result.equals("Play");
	}

	public void acceptOneRecord(Record record) {
		sample.add(record);
		Attribute attribute = record.getTargetAttribute();
		String value = attribute.getValue();
		if (isPositive(value)) {
			positive = positive + 1;

		} else {
			negative = negative + 1;
		}
	}

}
