package com.guideme.pattern;

import java.util.ArrayList;
import java.util.List;

import org.javatuples.Triplet;

public class DependencyInversionPrinciple {
	public static void main(String[] args) {
		PersonDTO parent = new PersonDTO("John");
		PersonDTO child1 = new PersonDTO("Chris");
		PersonDTO child2 = new PersonDTO("Matt");

		Relationships rs = new Relationships();
		rs.addParentandChild(parent, child1);
		rs.addParentandChild(parent, child2);

		new Research(rs);
	}
}

enum Relationship {
	PARENT, CHILD, SIBLIN;
}

class PersonDTO {
	public String name;

	public PersonDTO(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "<Data><name>" + name + "</name></Data>";
	}
}

class Relationships {
	private List<Triplet<PersonDTO, Relationship, PersonDTO>> relations = new ArrayList<Triplet<PersonDTO, Relationship, PersonDTO>>();

	public void addParentandChild(PersonDTO parent, PersonDTO child) {
		relations.add(new Triplet<>(parent, Relationship.PARENT, child));
		relations.add(new Triplet<>(child, Relationship.CHILD, parent));
	}

	public List<Triplet<PersonDTO, Relationship, PersonDTO>> getRelations() {
		return relations;
	}

}

class Research {
	public Research(Relationships relationships) {
		List<Triplet<PersonDTO, Relationship, PersonDTO>> relations = relationships.getRelations();
		relations.stream().filter(p -> p.getValue0().name.equals("John") && p.getValue1() == Relationship.PARENT)
				.forEach(ch -> System.out.println("John has a child called :" + ch.getValue2().name));
	}
}