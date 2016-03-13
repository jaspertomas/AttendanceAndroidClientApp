package utils;

import java.text.ParseException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;

import com.ikovac.timepickerwithseconds.view.MyTimePickerDialog;

public class DateTimePickerHelper {
	static EditText editText;
//	static Date date;
	static Integer year;
	static Integer month;
	static Integer dayOfMonth;
	static Integer hour;
	static Integer minute;
	static Integer second;
	static String ampm;
	static Context context;
	static String datestring,timestring;
	static boolean cancelled=false;
	static Calendar cal;

	//datepicker dialog generator
	public static DatePickerDialog genDatePicker(Context _context, EditText _editText) {
		context=_context;
//		date = _date;
		editText = _editText;

		cancelled=false;//don't call callback if cancel button clicked
		mFirst=true;//call callback only on first run - android bug fix

		cal = Calendar.getInstance();
		try {
			cal.setTime(PrettyDateTimeHelper.toDate(editText.getText().toString()));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		year = cal.get(Calendar.YEAR);
		month = cal.get(Calendar.MONTH);
		dayOfMonth = cal.get(Calendar.DAY_OF_MONTH);

		DatePickerDialog d = new DatePickerDialog(context, mDateSetListener,
				year, month, dayOfMonth);

		//add cancel button
		d.setButton(DialogInterface.BUTTON_NEGATIVE, "Cancel",
				new DialogInterface.OnClickListener() {
					public void onClick(DialogInterface dialog, int which) {
						if (which == DialogInterface.BUTTON_NEGATIVE) {
							cancelled = true;
						}
					}
				});	
		
		return d;
	}

	static boolean mFirst = true;
	// this is the listener for the datepicker dialog
	private static DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {
		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {
			//prevent this from running twice
			if(!mFirst)return;
			mFirst=false;

			//prevent this from running if cancel button was pressed
			if(cancelled)return;
			
			//formulate output string
			datestring = String.valueOf(year) + "-"
					+ String.valueOf(monthOfYear + 1) + "-"
					+ String.valueOf(dayOfMonth);
			datestring = PrettyDateHelper.toString(DateHelper
					.toDate(datestring));
			
			//show time picker
			genTimePicker(context).show();
		}
	};
	
	//Timepicker dialog generator
	public static MyTimePickerDialog genTimePicker(Context context) {
//		Calendar cal=Calendar.getInstance();
//		cal.setTime(date);
		hour=cal.get(Calendar.HOUR_OF_DAY);
		minute=cal.get(Calendar.MINUTE);
		second=cal.get(Calendar.SECOND);
		ampm = cal.get(Calendar.AM_PM) == Calendar.AM?"AM":"PM";
		MyTimePickerDialog mTimePicker = new MyTimePickerDialog(context, mTimeSetListener, hour, minute, second, false);
        return mTimePicker;
	}

	// this is the listener for the timepicker dialog	
	private static MyTimePickerDialog.OnTimeSetListener mTimeSetListener = new MyTimePickerDialog.OnTimeSetListener() {

		@Override
		public void onTimeSet(
				com.ikovac.timepickerwithseconds.view.TimePicker view,
				int hourOfDay, int minute, int seconds) {
		    ampm=hourOfDay<12?"AM":"PM";
		    if(hourOfDay==0)hourOfDay+=12;
		    if(hourOfDay>12)hourOfDay-=12;
			String timestring = 
					String.format("%02d", hourOfDay)+ ":"
					+ String.format("%02d", minute)+":"
					+ String.format("%02d", seconds)+" "
					+ ampm;
			editText.setText(datestring+" "+timestring);
		}

	};
	
}
