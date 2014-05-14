package org.museosdetenerife;

import get.data.rss.DownloadRSS;
import get.data.rss.ImportRSSMuseos;
import get.data.rss.Item;
import get.data.rss.RSS_Map;

import java.util.ArrayList;

import android.os.Bundle;
import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;
import android.app.Fragment;

public class EventosActivity extends Activity {
	
	static String id_museo;
	static String nombre = null;
	static String latitud = null;
	static String longitud = null;

	//static final String[] MUSEOS = new String[] { "HISTORIA", "COSMOS", "HOMBRE", "CEDOCAM" };
	
	static final String[] MUSEOS = new String[] { "6", "1", "9" , "12" , "5" , "7" };

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_eventos);
		
		Intent intent = getIntent();
		id_museo = intent.getStringExtra("ID");
		nombre = intent.getStringExtra("nombre");
		latitud = intent.getStringExtra("latitud");
		longitud = intent.getStringExtra("longitud");
		
		Globals g = (Globals)getApplication();
		g.setIdMuseo(id_museo);

		final ActionBar actionBar = getActionBar();

		DatosEstaticos titulo = new DatosEstaticos();
		actionBar.setTitle(titulo.getTitulo(id_museo));
		if(titulo.containsKeySubtitulo(id_museo))
			actionBar.setSubtitle(titulo.getSubtitulo(id_museo));
		
		View separator = findViewById(R.id.separator1);

		if(id_museo.equals("5") || id_museo.equals("7") || id_museo.equals("12"))
			separator.setBackgroundColor(0XFFB50937);
		else if(id_museo.equals("6"))
			separator.setBackgroundColor(0XFFFFAB18);
		else if(id_museo.equals("1"))
			separator.setBackgroundColor(0XFF5D9732);
		else if(id_museo.equals("9"))
			separator.setBackgroundColor(0XFF1D5FAD);

		 
	}
	
	public void selectFrag(View view) {
		
		Fragment fr = new EventosListaFragment();
		android.app.FragmentManager fm = getFragmentManager();
		FragmentTransaction fragmentTransaction = fm.beginTransaction();
		fragmentTransaction.replace(R.id.fragment_eventos, fr);
		fragmentTransaction.commit();	     
	}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.eventos, menu);
		return true;
	}
	
	public void InfoActivity(View view) {
	    
		Intent intent;
		
		intent = new Intent(this, MuseoActivity.class);
		intent.putExtra("ID", id_museo);
			
		startActivity(intent);

	}
	
	public void EventosActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, EventosActivity.class);
		intent.putExtra("ID", id_museo);
		intent.putExtra("latitud",latitud);
		intent.putExtra("longitud",longitud);
		intent.putExtra("nombre", nombre);
			
		startActivity(intent);
	}
	
	public void IrActivity(View view) {
		Intent intent;
		
		intent = new Intent(this, IrActivity.class);
		intent.putExtra("ID", id_museo);
		intent.putExtra("latitud",latitud);
		intent.putExtra("longitud",longitud);
		intent.putExtra("nombre", nombre);
			
		startActivity(intent);
	}
	
	public String getIdMuseo()
	{
		return id_museo;
	}

}
