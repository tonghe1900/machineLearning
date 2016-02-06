package ml.decisiontree;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Set;

import ml.decisiontree.DecisionTree.Node;

public enum DecisionTreeEngine {
	INSTANCE;
	public DecisionTree createDecisionTree(String fullFilePath) {

		List<Record> data = new Data(fullFilePath).getRecords();
		return createDecisionTree(data);
	}

	public DecisionTree createDecisionTree(List<Record> data) {
		Node root = initRoot(data);
		Queue<DecisionTree.Node> queue = new LinkedList<>();

		queue.add(root);
		while (!queue.isEmpty()) {
			DecisionTree.Node node = queue.poll();
			String attribute = getBestClassifierAttribute(node);
			if (attribute != null) {
				Collection<DecisionTree.Node> children = generateChildren(node,
						attribute);
				children.stream().filter(DecisionTree.Node::whetherDivided)
						.forEach((e) -> queue.add(e));
				;
			}

		}
		return DecisionTree.INSTANCE;
	}

	public Node initRoot(List<Record> data) {
		Node root = DecisionTree.INSTANCE.getRoot();
		SampleDistribute sampleDis = root.getSampleDistribute();
		for (Record record : data) {
			sampleDis.acceptOneRecord(record);
		}
		root.calculteEntropy();
		root.setPotentialClassifierAttrs(Record.getDeterminingAttributes());
		return root;
	}

	public Collection<Node> generateChildren(Node node, String attribute) {

		Collection<Node> children = node.generateChildren(attribute);
		node.setChildren(new ArrayList<Node>(children));
		return children;
	}

	private String getBestClassifierAttribute(Node node) {
		String result = null;
		Set<String> sets = new HashSet<>(node.getPotentialClassifierAttrs());
		if (sets.isEmpty())
			return null;
		double min = Double.MAX_VALUE;
		for (String attribute : sets) {
			Collection<DecisionTree.Node> children = generateChildren(node,
					attribute);
			double entropy = calcEntropy(node, children);
			if (entropy < min) {
				min = entropy;
				result = attribute;
			}

		}

		return result;
	}

	private double calcEntropy(Node parent, Collection<Node> children) {
		int total = parent.getTotal();
		// return children.stream().map(e ->
		// (e.getTotal()/total)*e.calculteEntropy() ).reduce(0.0, (x, y)-> x+y);
		double result = 0.0;
		for (Node node : children) {
			result += (double) node.getTotal() / (double) total
					* node.getEntropy();
		}
		return result;

	}

}
