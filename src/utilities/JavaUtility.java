package utilities;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class JavaUtility {

	public void checkCharsOfText(String name) {

		char[] nameToChar = name.toCharArray();
		char nameChars;
		String apha = null;
		String nums = null;
		String spChars = null;
		for (int i = 0; i < nameToChar.length - 1; i++) {
			nameChars = name.charAt(i);
			if ((nameChars > 'A' && nameChars < 'Z') || (nameChars > 'a' && nameChars < 'z')) {
				apha = "alphabets";
			}
			if (nameChars > '1' && nameChars < '9') {
				nums = "numbers";
			}
			if (nameChars == '!' || nameChars == '@' || nameChars == '#') {
				spChars = "special characters";
			}
		}
		if (apha == null || nums == null || spChars == null) {
			if (apha == null && nums == null) {
				System.out.println("Name contains only " + spChars);
			} else if (nums == null && spChars == null) {
				System.out.println("Name contains only " + apha);
			} else if (apha == null && spChars == null) {
				System.out.println("Name contains only " + nums);
			} else if (apha == null) {
				System.out.println("Name contains " + nums + " and " + spChars);
			} else if (nums == null) {
				System.out.println("Name contains " + apha + " and " + spChars);
			} else if (spChars == null) {
				System.out.println("Name contains " + apha + " and " + nums);
			}

		} else {
			System.out.println("Name contains " + apha + "," + nums + " and " + spChars);
		}

	}


		

	
}
