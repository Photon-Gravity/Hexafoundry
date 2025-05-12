package core.items;

import java.util.ArrayList;
import java.util.function.Predicate;

public class Qualification {
	public String name;

	Predicate<Item> predicate;
	public static ArrayList<Qualification> allQualifications = new ArrayList<>();

	public Qualification(String name, Predicate<Item> predicate){
		this.name = name;
		this.predicate = predicate;

		allQualifications.add(this);

	}

	public boolean qualifies(Item item){
		return predicate.test(item);
	}
}
