package com.guideme.pattern;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

public class SingleResponsibilityClass {
	public static void main(String[] args) throws Exception {
		Journal j = new Journal();
		j.addEntry("I cried Today");
		j.addEntry("I ate a burger");
		j.addEntry("I slept 10 Hours today");
		System.out.println(j);

		Persistence p = new Persistence();
		String fileName = "c:\\dummy\\journal.txt";
		p.saveTOFile(j, fileName, true);
		System.out.println("File Created Successfully.");
		Runtime.getRuntime().exec("notepad.exe " +fileName);
	}
}

class Journal {
	private final List<String> entries = new ArrayList();
	private static int count = 0;

	public void addEntry(String text) {
		entries.add("" + (++count) + ": " + text);
	}

	public void removeEntry(int index) {
		entries.remove(index);
	}

	@Override
	public String toString() {
		return String.join(System.lineSeparator(), entries);
	}
}

class Persistence {
	public void saveTOFile(Journal journal, String fileName, boolean overwrite) {
		if (overwrite || new File(fileName).exists()) {
			try (PrintStream out = new PrintStream(fileName)) {
				out.println(journal.toString());

			} catch (FileNotFoundException e) {
				e.printStackTrace();
			}
		}
	}
}
