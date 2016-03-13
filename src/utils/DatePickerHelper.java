package utils;

import java.text.ParseException;
import java.util.Calendar;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.widget.DatePicker;
import android.widget.EditText;

public class DatePickerHelper {
	static EditText editText;
	static Integer year;
	static Integer month;
	static Integer dayOfMonth;
	static boolean cancelled=false;
	static Calendar cal;

	public static DatePickerDialog genDatePicker(Context context, EditText _editText) {
		editText = _editText;
		
		cancelled=false;//don't call callback if cancel button clicked

		cal = Calendar.getInstance();
		try {
			cal.setTime(PrettyDateHelper.toDate(editText.getText().toString()));
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

	// this is the listener for the datepicker dialog
	private static DatePickerDialog.OnDateSetListener mDateSetListener = new DatePickerDialog.OnDateSetListener() {

		public void onDateSet(DatePicker view, int year, int monthOfYear,
				int dayOfMonth) {

			//prevent this from running if cancel button was pressed
			if(cancelled)return;
			
			//formulate output string
			String datestring = String.valueOf(year) + "-"
					+ String.valueOf(monthOfYear + 1) + "-"
					+ String.valueOf(dayOfMonth);
			datestring = PrettyDateHelper.toString(DateHelper
					.toDate(datestring));
			editText.setText(datestring);
		}
	};
	// public void setDate(int year, int month, int date) {
	// this.year=year;
	// this.month=month;
	// this.dayOfMonth=date;
	// }
}
