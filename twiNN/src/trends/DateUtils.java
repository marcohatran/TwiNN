/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package trends;

import java.util.Calendar;
import java.text.SimpleDateFormat;

public class DateUtils {
  public static final String DATE_FORMAT_NOW = "yyyy-MM-dd HH:mm:ss";
public static final String DATE_FILE_NOW = "yyyy-MM-dd-HH-mm-ss";

  public static String now() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_NOW);
    return sdf.format(cal.getTime());

  }
  public static String nowfile() {
    Calendar cal = Calendar.getInstance();
    SimpleDateFormat sdf = new SimpleDateFormat(DATE_FILE_NOW);
    return sdf.format(cal.getTime());

  }
}