package au.com.sommer.common.util;

/**
 * 
 * @author a1613564
 *
 */
public class GeneralUtils {

	public static boolean isOnlyOneTrue(boolean... bools) {
		boolean result = false;

		for(boolean bool : bools) {
			if(result) {
				if(bool) return false;
			} else {
				result |= result != bool;
			}
		}
		
		return result;
	}
	
}
