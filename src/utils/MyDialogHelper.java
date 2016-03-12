package utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

public class MyDialogHelper {
	public static void popup(Context context, String message)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setTitle(message);
		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
		    @Override
		    public void onClick(DialogInterface dialog, int which) {
		    }
		});
		builder.setCancelable(false);
		builder.show();		
	}
//	public static void textInput(Context context, String message)
//	{
//		AlertDialog.Builder builder = new AlertDialog.Builder(context);
//		builder.setTitle(message);
//
//		// Set up the input
//		final EditText input = new EditText(context);
//		// Specify the type of input expected; this, for example, sets the input as a password, and will mask the text
//		input.setInputType(InputType.TYPE_CLASS_TEXT);
//		builder.setView(input);
//
//		// Set up the buttons
//		builder.setPositiveButton("OK", new DialogInterface.OnClickListener() { 
//		    @Override
//		    public void onClick(DialogInterface dialog, int which) {
//		    }
//		});
//		builder.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//		    @Override
//		    public void onClick(DialogInterface dialog, int which) {
//		    }
//		});
//
//		builder.show();		
//	}
}
