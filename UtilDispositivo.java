package com.kadroid.utilitarios.android.util;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.regex.Pattern;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;
import android.telephony.TelephonyManager;
import android.util.Patterns;
import android.view.ViewConfiguration;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

public class UtilDispositivo {
	
	public static void getOverflowMenu(Context context) {
		try {
			ViewConfiguration configuracion = ViewConfiguration.get(context);
			Field campo_menuKey = ViewConfiguration.class
					.getDeclaredField("sHasPermanentMenuKey");

			if (campo_menuKey != null) {
				campo_menuKey.setAccessible(true);
				campo_menuKey.setBoolean(configuracion, false);
			}
		} catch (Exception e) {
		}
	}
	
	public static void ocultarTeclado(Context context, EditText... editText) {
		InputMethodManager manager = (InputMethodManager) context
				.getSystemService(Context.INPUT_METHOD_SERVICE);

		for (int i = 0; i < editText.length; i++) {
			manager.hideSoftInputFromWindow(
					editText[i].getWindowToken(), 0);
		}

	}
	
	public static String obtenerNumeroDispositivo(Context context) {

		String numero_celular = "";
		TelephonyManager manager = (TelephonyManager) context
				.getSystemService(Context.TELEPHONY_SERVICE);
		numero_celular = manager.getLine1Number();

		return numero_celular;

	}
	
	public static ArrayList<String> obtenerEmailsDispositivo(Context context) {

		Pattern patron_email = Patterns.EMAIL_ADDRESS; // API level 8+
		Account[] cuentas = AccountManager.get(context).getAccounts();
		ArrayList<String> correos_electronicos = new ArrayList<String>();
		for (Account account : cuentas) {
			if (patron_email.matcher(account.name).matches()) {
				correos_electronicos.add(account.name);
				break;
			}
		}

		return correos_electronicos;

	}
}
